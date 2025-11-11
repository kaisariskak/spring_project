package kz.bsbnb.usci.jobstore.audit;

import kz.bsbnb.usci.jobstore.audit.model.AuditSendJob;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuditClientJob {

    private RestTemplate restTemplate;
    //@Value("${audit.url}")
    //String url;
    public AuditClientJob(){}

    /*@Autowired
    public AuditClinetReceiver(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }*/

    public AuditSendJob send(AuditSendJob auditSend) {
        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AuditSendJob> request = new HttpEntity<>(auditSend, headers);
        AuditSendJob auditSendResponse = restTemplate.postForObject("http://10.8.1.101:28765/api/audit/audit/insertAuditData", request, AuditSendJob.class);
        return auditSendResponse;
    }
}
