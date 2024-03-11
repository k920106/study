package com.bank.www.service;

import com.bank.www.domain.account.Account;
import com.bank.www.domain.account.AccountRepository;
import com.bank.www.domain.transaction.Transaction;
import com.bank.www.domain.transaction.TransactionEnum;
import com.bank.www.domain.transaction.TransactionRepository;
import com.bank.www.domain.user.User;
import com.bank.www.domain.user.UserRepository;
import com.bank.www.dto.account.AccountReqDto.AccountDepositReqDto;
import com.bank.www.dto.account.AccountReqDto.AccountSaveReqDto;
import com.bank.www.dto.account.AccountRespDto.AccountDepositRespDto;
import com.bank.www.dto.account.AccountRespDto.AccountListRespDto;
import com.bank.www.dto.account.AccountRespDto.AccountSaveRespDto;
import com.bank.www.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AccountService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountListRespDto 계좌목록보기_유저별(Long userId) {
        User userPS = userRepository.findById(userId).orElseThrow(() -> new CustomApiException("유저를 찾을 수 없습니다"));

        // 유저의 모든 계좌목록
        List<Account> accountListPS = accountRepository.findByUser_id(userId);
        return new AccountListRespDto(userPS, accountListPS);
    }

    @Transactional
    public AccountSaveRespDto 계좌등록(AccountSaveReqDto accountSaveReqDto, Long userId) {
        // User가 DB에 있는지 검증 겸 유저 엔티티 가져오기
        User userPS = userRepository.findById(userId).orElseThrow(() -> new CustomApiException("유저를 찾을 수 없습니다"));

        // 해당 계좌가 DB에 있는 중복여부를 체크
        Optional<Account> accountOP = accountRepository.findByNumber(accountSaveReqDto.getNumber());
        if (accountOP.isPresent()) {
            throw new CustomApiException("해당 계좌가 이미 존재합니다");
        }

        // 계좌 등록
        Account accountPS = accountRepository.save(accountSaveReqDto.toEntity(userPS));

        // DTO를 응답
        return new AccountSaveRespDto(accountPS);
    }

    @Transactional
    public void 계좌삭제(Long number, Long userId) {
        // 1. 계좌 확인
        Account accountPS = accountRepository.findByNumber(number).orElseThrow(() -> new CustomApiException("계좌를 찾을 수 없습니다"));

        // 2. 계좌 소유자 확인
        accountPS.checkOwner(userId);

        // 3. 계좌 삭제
        accountRepository.deleteById(accountPS.getId());
    }

    // ATM -> 누군가의 계좌
    // 인증이 필요 없다.
    @Transactional
    public AccountDepositRespDto 계좌입금(AccountDepositReqDto accountDepositReqDto) {
        // 0원 체크
        if (accountDepositReqDto.getAmount() <= 0L) {
            throw new CustomApiException("0원 이하의 금액을 입금할 수 없습니다");
        }

        // 입금계좌 확인
        Account depositAccountPS = accountRepository.findByNumber(accountDepositReqDto.getNumber()).orElseThrow(() -> new CustomApiException("계좌를 찾을 수 없습니다"));

        // 입금 (해당 계좌 balance 조정 - update문 - 더티체킹)
        depositAccountPS.deposit(accountDepositReqDto.getAmount());

        // 거래내역 남기기
        Transaction transaction = Transaction.builder()
                                             .withdrawAccount(null)
                                             .depositAccount(depositAccountPS)
                                             .withdrawAccountBalance(null)
                                             .depositAccountBalance(depositAccountPS.getBalance())
                                             .amount(accountDepositReqDto.getAmount())
                                             .gubun(TransactionEnum.DEPOSIT)
                                             .sender("ATM")
                                             .receiver(accountDepositReqDto.getNumber() + "")
                                             .tel(accountDepositReqDto.getTel())
                                             .build();

        Transaction transactionPS = transactionRepository.save(transaction);
        return new AccountDepositRespDto(depositAccountPS, transactionPS);
    }
}
