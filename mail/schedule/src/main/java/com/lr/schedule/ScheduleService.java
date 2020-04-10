package com.lr.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ScheduleService {
    //第一次任务结束，延迟多少毫秒进行下一个
    //@Scheduled(fixedDelay = 5000)
    public void delay() {
        System.out.println("fixedDelay------->"+new Date());
    }


    //俩次定时任务间隔多少毫秒，第一次没结束也不管，间隔时间到就开启
    //@Scheduled(fixedRate = 5000)
    public void fixedRate() {
        System.out.println("fixedRate------->"+new Date());
    }

    @Scheduled(cron ="0/5 35 * * * ?" )
    public void cron() {
        System.out.println("cron表达式"+new Date());
    }
}
