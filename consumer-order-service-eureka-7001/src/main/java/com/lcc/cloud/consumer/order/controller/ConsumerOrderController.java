package com.lcc.cloud.consumer.order.controller;

import com.lcc.cloud.consumer.order.feign.PaymentFeignClient;
import com.lcc.cloud.domain.CommonResult;
import com.lcc.cloud.domain.Payment;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author lcc
 * @version 2020/8/23
 */
@RestController
@RequestMapping("/web/order")
@Slf4j
public class ConsumerOrderController {

  @Autowired
  private PaymentFeignClient paymentFeignClient;

  @PostMapping("/payment/create")
  public CommonResult<?> create(Payment payment) {
    log.info("创建支付信息：" + payment);
    return paymentFeignClient.create(payment);
  }

  @GetMapping("/payment2/{id}")
  public CommonResult<?> getPayment2(@PathVariable("id") Long id) {
    log.info("查询支付信息，id：" + id);
    return paymentFeignClient.query(id);
  }

  //第一版请求支付微服务
  public static final String MICRO_SERVER_URL = "http://PAYMENT-SERVICE";
  @Autowired
  private DiscoveryClient discoveryClient;
  @Autowired
  private RestTemplate restTemplate;

  @GetMapping("/payment1/{id}")
  public CommonResult<?> getPayment1(@PathVariable("id") Long id) {
    log.info("查询支付信息，id：" + id);
    List<String> services = discoveryClient.getServices();
    for (String service : services) {
      log.info("服务：" + service);
    }

    List<ServiceInstance> instances = discoveryClient.getInstances("PAYMENT-SERVICE");
    for (ServiceInstance instance : instances) {
      log.info("服务：" + instance);
    }
    return restTemplate.getForObject(MICRO_SERVER_URL + "/payment/" + id, CommonResult.class);
  }
}
