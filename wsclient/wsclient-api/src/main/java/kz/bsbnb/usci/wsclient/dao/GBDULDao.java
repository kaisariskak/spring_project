package kz.bsbnb.usci.wsclient.dao;

import kz.bsbnb.usci.wsclient.model.gbdul.GBDULEntity;

import java.util.List;

public interface GBDULDao {

    void saveGBDULInfo(GBDULEntity gbdulEntity);
    List<String> getBinList();
    void updateCheck(String bin);
}
