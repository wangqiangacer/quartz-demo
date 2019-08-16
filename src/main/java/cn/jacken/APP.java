package cn.jacken;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class APP {
    public static void main(String[] args) throws Exception {
        //创建Scheduler工厂
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        //从工厂中获取任务调度器是咧
        Scheduler scheduler = stdSchedulerFactory.getScheduler();
        //创建jobdetails
       JobDetail jobBuilder= JobBuilder.newJob(MyJob.class).withDescription("this is a job").withIdentity("ramJob","ramGroup").build();
        //执行任务的时间
        long time = System.currentTimeMillis() + 3 * 1000L;
        Date date = new Date(time);
        //创建Trigger
        CronTrigger ramTrigger = TriggerBuilder.newTrigger().withDescription("").withIdentity("ramTrigger").
                startAt(date).withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?")).build();//两秒执行一次
//注册任务和定时器
        scheduler.scheduleJob( jobBuilder,ramTrigger);

        scheduler.start();
    }
}
