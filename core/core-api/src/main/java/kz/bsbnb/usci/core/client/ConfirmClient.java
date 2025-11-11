package kz.bsbnb.usci.core.client;

import kz.bsbnb.usci.model.ws.ConfirmWs;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "core",
        url = "https://10.8.1.58:50003"
)
public interface ConfirmClient {
    @GetMapping(value = "/confirm/getConfirmWsList")
    List<ConfirmWs> getConfirmWs(@RequestParam(required = false, name = "userId") Long userId,
                                 @RequestParam(required = false, name = "respondentId") Long respondentId,
                                 @RequestParam(required = false, name = "reportDate") String  reportDate);
    @PostMapping(value = "/confirm/approve")
    void approve(@RequestParam(name = "userId") Long userId,
                 @RequestParam(name = "confirmId") Long confirmId,
                 @RequestParam(name = "documentHash") String documentHash,
                 @RequestParam(name = "signature") String signature,
                 @RequestParam(name = "userName") String userName,
                 @RequestParam(name = "signType")  String signType);
}
