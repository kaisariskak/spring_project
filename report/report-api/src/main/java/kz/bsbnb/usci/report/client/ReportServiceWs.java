package kz.bsbnb.usci.report.client;

import kz.bsbnb.usci.model.ws.OutputForm;
import kz.bsbnb.usci.model.ws.UserReportInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "report",
            url = "https://10.8.1.58:50006"
           )
public interface ReportServiceWs {
     @PostMapping(value = "/report/executeReportWs")
     void executeReportWs(@RequestParam(name = "respondentId") Long respondentId,
                          @RequestParam(name = "productId") Long productId,
                          @RequestParam(name = "userId") Long userId,
                          @RequestParam(name = "reportDate")  String reportDate,
                          @RequestParam(name = "vitrina") String vitrina) ;
    @GetMapping(value = "/report/getOutputFormList")
    List<OutputForm> getOutputFormList(@RequestParam(name = "userId") Long userId) ;
    @GetMapping(value = "/report/getReportListWs")
    List<UserReportInfo> getReportListWs(@RequestParam(name = "userId") Long userId,
                                         @RequestParam(name = "respondentId") Long respondentId,
                                         @RequestParam(name = "productId") Long productId,
                                         @RequestParam(name = "reportDate") String reportDate,
                                         @RequestParam(name = "vrepName") String vrepName);

}
