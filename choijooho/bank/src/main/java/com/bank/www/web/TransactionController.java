package com.bank.www.web;

import com.bank.www.config.auth.LoginUser;
import com.bank.www.dto.ResponseDto;
import com.bank.www.dto.transaction.TransactionRespDto.TransactionListRespDto;
import com.bank.www.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/s/account/{number}/transaction")
    public ResponseEntity<?> findTransactionList(@PathVariable Long number, @RequestParam(value = "gubun", defaultValue = "ALL") String gubun, @RequestParam(value = "page", defaultValue = "0") Integer page, @AuthenticationPrincipal LoginUser loginUser) {
        TransactionListRespDto transactionListRespDto = transactionService.입출금목록보기(loginUser.getUser().getId(), number, gubun, page);
        return ResponseEntity.ok().body(new ResponseDto<>(1, "입출금목록보기 성공", transactionListRespDto));
    }
}
