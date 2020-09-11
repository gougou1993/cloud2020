package com.tree.springcloud.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_OK,id:"+id+"哈哈";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_Timeout,id:"+id+"哈哈";

    }
}
