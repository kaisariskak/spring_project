package kz.bsbnb.usci.ws.dao;

import java.util.List;
import java.util.Map;

public interface WsDao {
    Long getUserIdByName(String name);
    Integer checkUserProduct(Long userId, Long productId);
    List<Map<String, Object>> getUsersForUpload();
}
