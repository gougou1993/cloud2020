package com.tree.springcloud.controller;

import com.tree.springcloud.entities.CommonResult;
import com.tree.springcloud.entities.Payment;
import com.tree.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

    //private static final String PAYMENT_URL = "http://127.0.0.1:8001";
    /**
     * 微服务的名称
     */
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return  restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }


    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return  restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }


    @GetMapping(value = "/consumer/payment/get_for_entity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else{
            return  new CommonResult<Payment>(400,"查询失败");
        }
    }

    /**
     * 这个要可以，必须把restTemplate的@LoadBalance 注
     * @return
     */
    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB() {

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances ==null || instances.isEmpty()){
            return  null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);

        URI uri = serviceInstance.getUri();

        String forObject = restTemplate.getForObject(uri+"/payment/lb", String.class);

        return forObject;

    }
}
