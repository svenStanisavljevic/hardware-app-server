package hr.tvz.stanisavljevic.hardwareapp.jobs;

import hr.tvz.stanisavljevic.hardwareapp.hardware.Hardware;
import hr.tvz.stanisavljevic.hardwareapp.hardware.HardwareDTO;
import hr.tvz.stanisavljevic.hardwareapp.hardware.HardwareService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.ArrayList;
import java.util.List;

public class AvailableStockJob extends QuartzJobBean {

    @Autowired
    private HardwareService hardwareService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("These are currently available hardware");
        System.out.println("--------------------------------------");
        List<HardwareDTO> hardware = hardwareService.findAll();
        for (HardwareDTO h : hardware) {
            if (h.getStock() > 0) {
                System.out.println(h.getName() + " - " + h.getStock());
            }
        }
        System.out.println("--------------------------------------");
    }
}
