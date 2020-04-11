package com.lr.quartzschedule.job;

import org.springframework.stereotype.Component;

import java.util.Date;

//无法传参
@Component
public class FirstJob {
    public void hello() {
        System.out.print("first job say  apple:"+new Date());
    }
}
