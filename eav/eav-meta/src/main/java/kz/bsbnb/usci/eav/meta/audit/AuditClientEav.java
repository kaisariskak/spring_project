package kz.bsbnb.usci.eav.meta.audit;

import kz.bsbnb.usci.eav.meta.audit.model.AuditSendEav;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class AuditClientEav {

    private RestTemplate restTemplate;
    //@Value("${audit.url}")
    String url;
    public AuditClientEav(){}

    /*@Autowired
    public AuditClientEav(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }*/

    public AuditSendEav send(AuditSendEav auditSend) {
        restTemplate= new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AuditSendEav> request = new HttpEntity<>(auditSend, headers);
        AuditSendEav auditSendResponse = restTemplate.postForObject("http://10.8.1.101:28765/api/audit/audit/insertAuditData", request, AuditSendEav.class);
        return auditSendResponse;
    }
}
