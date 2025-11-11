package kz.bsbnb.usci.model.job;

import kz.bsbnb.usci.model.persistence.Persistable;

public class JobConfig extends Persistable {

    private String jobName;
    private String jobTitle;
    private String cron;
    private boolean started = false;
    public Long userId;

    public JobConfig() {
        super();
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }


}
