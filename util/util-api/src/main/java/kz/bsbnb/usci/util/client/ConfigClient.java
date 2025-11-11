package kz.bsbnb.usci.util.client;

import kz.bsbnb.usci.model.util.ParentChildRespondentJson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

/**
 * @author Jandos Iskakov
 */

@FeignClient(name = "utils")
public interface ConfigClient {

    @GetMapping(value = "/config/getDigitalSigningOrgIds")
    Set<Long> getDigitalSigningOrgIds();

    @GetMapping(value = "/config/getQueueAlgorithm")
    String getQueueAlgorithm();

    @GetMapping(value = "/config/getPriorityRespondentIds")
    Set<Long> getPriorityRespondentIds();

    @GetMapping(value = "/config/getManifestXsd")
    byte[] getManifestXsd();

    @GetMapping(value = "/config/getConfirmText")
    String getConfirmText();

    @GetMapping(value = "/config/getChildRespondent")
    ParentChildRespondentJson getChildRespondent(@RequestParam(name = "bin") String bin);

}
