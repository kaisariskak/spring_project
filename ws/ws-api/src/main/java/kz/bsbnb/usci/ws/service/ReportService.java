package kz.bsbnb.usci.ws.service;
import kz.bsbnb.usci.ws.modal.info.ReportInfo;
import kz.bsbnb.usci.ws.modal.info.ResponseInfo;
import kz.bsbnb.usci.ws.modal.info.UserInfoRequest;
import kz.bsbnb.usci.ws.modal.report.OutputFormResponse;
import kz.bsbnb.usci.ws.modal.report.RunReportResponse;
import kz.bsbnb.usci.ws.modal.report.UserReportResponse;

public interface ReportService {
    ResponseInfo callRunReport(ReportInfo reportInfo);
    OutputFormResponse getOutputFormList(UserInfoRequest userInfoRequest);
    UserReportResponse getUserReport(ReportInfo reportInfo);
}
