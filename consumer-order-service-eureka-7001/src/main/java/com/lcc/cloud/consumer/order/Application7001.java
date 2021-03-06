package com.lcc.cloud.consumer.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author lcc
 * @version 2020/8/23
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "ribbonTest")
@EnableFeignClients
@EnableHystrix
public class Application7001 {

  public static void main(String[] args) {
    SpringApplication.run(Application7001.class, args);
  }
}
