package kz.bsbnb.usci.ws.service;
import kz.bsbnb.usci.ws.modal.crosscheck.CrossCheckResponse;
import kz.bsbnb.usci.ws.modal.info.ResponseInfo;
import kz.bsbnb.usci.ws.modal.info.UserInfoRequest;

public interface CrossCheckService {
    CrossCheckResponse getCrossCheckList(UserInfoRequest userInfoRequest);
    ResponseInfo callCrossCheck(UserInfoRequest userInfoRequest);
}
