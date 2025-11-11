package kz.bsbnb.usci.report.client;

import kz.bsbnb.usci.model.ws.CrossCheckWs;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name = "report",
        url = "https://10.8.1.58:50006"
)
public interface CrossCheckServiceWs {
    @GetMapping(value = "/crosscheck/getCrossCheckWs")
    List<CrossCheckWs> getCrossCheckWs(@RequestParam(name = "respondentId") Long respondentId,
                                       @RequestParam(name = "reportDate")  String reportDate,
                                       @RequestParam(name = "productId") Long productId);
    @PostMapping(value = "/crosscheck/executeCrossCheckAllWs")
    void crossCheckWs(@RequestParam(name = "userId")  Long userId,
                      @RequestParam(name = "respondentId")  Long respondentId,
                      @RequestParam(name = "productId")  Long productId,
                      @RequestParam(name = "reportDate")  String reportDate);
}
