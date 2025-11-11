package kz.bsbnb.usci.receiver.audit;
import kz.bsbnb.usci.receiver.audit.model.AuditSendReceiver;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;


@Component
public class AuditClinetReceiver {
    // @Autowired
    private RestTemplate restTemplate;
    /*@Value("${audit.url}")
    String url;*/
    public AuditClinetReceiver(){}

    public AuditSendReceiver send(AuditSendReceiver auditSend) {
        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AuditSendReceiver> request = new HttpEntity<>(auditSend, headers);
        AuditSendReceiver auditSendResponse = restTemplate.postForObject("http://10.8.1.101:28765/api/audit/audit/insertAuditData", request, AuditSendReceiver.class);
        return auditSendResponse;
    }
}
