package com.bank.www.service;

import com.bank.www.domain.account.Account;
import com.bank.www.domain.account.AccountRepository;
import com.bank.www.domain.transaction.Transaction;
import com.bank.www.domain.transaction.TransactionRepository;
import com.bank.www.dto.transaction.TransactionRespDto.TransactionListRespDto;
import com.bank.www.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionListRespDto 입출금목록보기(Long userId, Long accountNumber, String gubun, int page) {
        Account accountPS = accountRepository.findByNumber(accountNumber).orElseThrow(() -> new CustomApiException("해당 계좌를 찾을 수 없습니다"));

        accountPS.checkOwner(userId);

        List<Transaction> transactionListPS = transactionRepository.findTransactionList(accountPS.getId(), gubun, page);
        return new TransactionListRespDto(transactionListPS, accountPS);
    }
}
