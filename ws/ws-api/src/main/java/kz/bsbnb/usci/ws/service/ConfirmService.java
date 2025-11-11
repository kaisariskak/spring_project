package kz.bsbnb.usci.ws.service;

import kz.bsbnb.usci.ws.modal.confirm.ConfrimApproveRequest;
import kz.bsbnb.usci.ws.modal.confirm.ConfrimResponse;
import kz.bsbnb.usci.ws.modal.info.ResponseInfo;
import kz.bsbnb.usci.ws.modal.info.UserInfoRequest;

public interface ConfirmService {
    ConfrimResponse getConfirmList(UserInfoRequest userInfoRequest);
    ResponseInfo confrimApprove(ConfrimApproveRequest confrimApproveRequest);
}
