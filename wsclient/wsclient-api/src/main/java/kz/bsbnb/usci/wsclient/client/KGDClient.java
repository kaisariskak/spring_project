package kz.bsbnb.usci.wsclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "wsclient")
public interface KGDClient {

    @PostMapping(value = "/kgd/sendRequestToKGD")
    void sendRequestToKGD() ;
}
