package hr.tvz.stanisavljevic.hardwareapp.jobs;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.table.TableRowSorter;

@Configuration
public class SchedulerConfig {

    @Bean
    public JobDetail stockJobDetail () {
        return JobBuilder.newJob(AvailableStockJob.class).withIdentity("stockJobDetail").storeDurably().build();
    }

    @Bean
    public Trigger stockJobTrigger () {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever();
        return TriggerBuilder.newTrigger().forJob(stockJobDetail()).withIdentity("stockJobDetail").withSchedule(scheduleBuilder).build();
    }


}
