package com.example.paymentservice.payment.adapter.`in`.web.api

import com.example.paymentservice.common.WebAdapter
import com.example.paymentservice.payment.adapter.`in`.web.request.TossPaymentConfirmRequest
import com.example.paymentservice.payment.adapter.`in`.web.response.ApiResponse
import com.example.paymentservice.payment.adapter.out.web.toss.executor.TossPaymentExecutor
import com.example.paymentservice.payment.application.port.`in`.PaymentConfirmCommand
import com.example.paymentservice.payment.application.port.`in`.PaymentConfirmUseCase
import com.example.paymentservice.payment.domain.PaymentConfirmationResult
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import reactor.core.publisher.Mono

@WebAdapter
@RequestMapping("/v1/toss")
@Controller
class TossPaymentController(
    // private val tossPaymentExecutor: TossPaymentExecutor
    private val paymentConfirmUseCase: PaymentConfirmUseCase
) {
    @PostMapping("/confirm")
//    fun confirm(@RequestBody request: TossPaymentConfirmRequest): Mono<ResponseEntity<ApiResponse<String>>> {
    fun confirm(@RequestBody request: TossPaymentConfirmRequest): Mono<ResponseEntity<ApiResponse<PaymentConfirmationResult>>> {
//        return tossPaymentExecutor.execute(
//            paymentKey = request.paymentKey,
//            orderId = request.orderId,
//            amount = request.amount.toString()
//        ).map {
//            ResponseEntity.ok().body(ApiResponse.with(HttpStatus.OK, "", it))
//        }
        val command = PaymentConfirmCommand(
            paymentKey = request.paymentKey,
            orderId = request.orderId,
            amount = request.amount.toLong()
        )

        return paymentConfirmUseCase.confirm(command)
                                    .map { ResponseEntity.ok().body(ApiResponse.with(HttpStatus.OK, "", it)) }
    }
}
