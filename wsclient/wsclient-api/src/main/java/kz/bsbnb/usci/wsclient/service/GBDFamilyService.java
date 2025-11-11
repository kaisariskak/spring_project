package kz.bsbnb.usci.wsclient.service;

import kz.bsbnb.usci.model.exception.UsciException;
import gbdfm.ResponseDataType;




public interface GBDFamilyService {

    ResponseDataType sendMessage(String userId, String iin) throws UsciException;

}