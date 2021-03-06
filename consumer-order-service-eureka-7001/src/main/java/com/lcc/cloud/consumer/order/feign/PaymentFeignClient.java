package com.lcc.cloud.consumer.order.feign;

import com.lcc.cloud.domain.CommonResult;
import com.lcc.cloud.domain.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author lcc
 * @version 2020/9/22
 */
@FeignClient(value = "PAYMENT-SERVICE", fallback = PaymentFallbackService.class)
@Component
public interface PaymentFeignClient {

  @PostMapping("/payment")
  CommonResult<?> create(Payment payment);

  @GetMapping("/payment/{id}")
  CommonResult<?> query(@PathVariable("id") Long id);

  @GetMapping("/payment/circuitBreaker/{num}")
  CommonResult<Payment> circuitBreaker(@PathVariable("num") Integer num);
}
