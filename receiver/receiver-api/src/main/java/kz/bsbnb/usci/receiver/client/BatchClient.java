package kz.bsbnb.usci.receiver.client;

import kz.bsbnb.usci.model.batch.Product;
import kz.bsbnb.usci.model.ws.EntityError;
import kz.bsbnb.usci.model.ws.Protocol;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author Jandos Iskakov
 */

@FeignClient(name = "receiver",
             url = "https://10.8.1.58:50001",
             value = "receiver")
public interface BatchClient {

    @PostMapping(value = "/batch/endBatch")
    void endBatch(@RequestParam(name = "batchId") Long batchId);

    @PostMapping(value = "/batch/incrementActualCounts")
    boolean incrementActualCounts(@RequestBody Map<Long, Long> batchesToUpdate);

    @PostMapping(value = "/batch/receiveBatchFromWebservice")
    void receiveBatchFromWebservice(@RequestParam(name = "userId") Long userId,
                                    @RequestParam(name = "respondentId") Long respondentId,
                                    @RequestParam(name = "batchName") String batchName,
                                    @RequestParam(name = "filePath") String filePath,
                                    @RequestParam(name = "fileContent") byte[] fileContent,
                                    @RequestParam(name = "reportDate") String reportDate,
                                    @RequestParam(name = "sign") String sign,
                                    @RequestParam(name = "signInfo") String signInfo,
                                    @RequestParam(name = "signTime") String  signTime,
                                    @RequestBody Product product);

    @PostMapping(value = "/batch/checkSignatureWs")
    Map<String, Object> checkSignatureWs(@RequestParam(name = "signature") String signature);

    @GetMapping(value = "/batch/getBatchListWs")
    List<Protocol> getBatchListWs(@RequestParam(name = "respondentId") Long respondentId,
                                  @RequestParam(name = "productId") Long productId,
                                  @RequestParam(name = "userId") Long userId,
                                  @RequestParam(name = "reportDate")   String reportDate);

    @GetMapping(value = "/batch/getEntityErrorList")
    List<EntityError> getEntityErrorList(@RequestParam(name = "respondentId") Long respondentId,
                                         @RequestParam(name = "productId") Long productId,
                                         @RequestParam(name = "userId") Long userId,
                                         @RequestParam(name = "reportDate")  String reportDate);

}
