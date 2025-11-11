package kz.bsbnb.usci.wsclient.service;

import gbdul.kz.bee.bip.SyncChannel.v10.Types.Response.SyncSendMessageResponse;
import kz.bsbnb.usci.model.exception.UsciException;
import kz.bsbnb.usci.wsclient.model.gbdul.GBDULEntity;

import java.util.List;

public interface GBDULService {

    List<String> getBinList();
    SyncSendMessageResponse sendMessage(String userId, String messageId, String requestorBin, String bin) throws UsciException;
    void saveGBDULInfo(GBDULEntity gbdulEntity);
}
