package kz.bsbnb.usci.jobstore.controller;


import kz.bsbnb.usci.jobstore.audit.AuditClientJob;
import kz.bsbnb.usci.jobstore.audit.model.AuditSendJob;
import kz.bsbnb.usci.jobstore.repository.ScheduleRepository;
import kz.bsbnb.usci.jobstore.service.ScheduleService;
import kz.bsbnb.usci.model.job.JobConfig;
import kz.bsbnb.usci.util.json.ext.ExtJsList;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/job")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final ScheduleRepository scheduleRepository;
    public AuditClientJob auditClient;
    public AuditSendJob auditSend;
    public String auKnCode;

    public ScheduleController(ScheduleService scheduleService, ScheduleRepository scheduleRepository) {
        this.scheduleService = scheduleService;
        this.scheduleRepository = scheduleRepository;
    }

    @GetMapping(value = "getJobList")
    public ExtJsList getJobList() {
        return new ExtJsList(scheduleService.getJobList());
    }

    @PostMapping(value = "startJob")
    public void startJob(@RequestBody JobConfig jobConfig) {
        auditClient = new AuditClientJob();
        if(jobConfig.getId()==1)
            auKnCode="STARTJOBNSIAUKN";
        else auKnCode="STARTJOBKGDAUKN";

        auditSend = new AuditSendJob(null,auKnCode,null,jobConfig.userId," JOB NAME = "+jobConfig.getJobName()+ " ;  OLD CORN = "+jobConfig.getCron());
        auditSend=  auditClient.send(auditSend);

        scheduleRepository.start(jobConfig);
        scheduleService.saveJobConfig(jobConfig.getId(), jobConfig.getCron(), jobConfig.isStarted());
    }

    //@PostMapping(value = "stopJob")
    @GetMapping(value = "stopJob")
    public void stopJob(@RequestParam Long id,@RequestParam Long userId) {
        auditClient = new AuditClientJob();
        if(id==1){
            auditSend = new AuditSendJob(null,"STOPJOBNSIAUKN",null,userId,"NSI");
            auditSend=  auditClient.send(auditSend);
        }else{
            auditSend = new AuditSendJob(null,"STOPJOBKGDAUKN",null,userId,"KGD");
            auditSend=  auditClient.send(auditSend);
        }
        scheduleRepository.stop(id);
        scheduleService.saveJobConfig(id, null, false);
    }
}
