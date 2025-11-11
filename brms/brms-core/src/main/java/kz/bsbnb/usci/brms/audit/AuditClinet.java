package kz.bsbnb.usci.brms.audit;

import kz.bsbnb.usci.brms.audit.model.AuditSend;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuditClinet {
    //@Autowired
    private RestTemplate restTemplate;
    // @Value("${audit.url}")
    String url;
    public AuditClinet(){}

    /*@Autowired
    public AuditClinet(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
*/
    public AuditSend send(AuditSend auditSend) {
        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AuditSend> request = new HttpEntity<>(auditSend, headers);
        AuditSend auditSendResponse = restTemplate.postForObject("http://10.8.1.101:28765/api/audit/audit/insertAuditData", request, AuditSend.class);
        return auditSendResponse;
    }
}
