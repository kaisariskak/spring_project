package kz.bsbnb.usci.wsclient.service;

import kz.bsbnb.usci.wsclient.jaxb.ctr.Entities;
import kz.bsbnb.usci.wsclient.model.ctrkgd.Request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface KGDService {

    void testRequestKgd();

    void ctrRequest(LocalDate reportDate, LocalDate sendDate, Long id , int operationId, boolean isUpdate);

    List<Request> getCtrRequestList(LocalDate reportDate);

}
