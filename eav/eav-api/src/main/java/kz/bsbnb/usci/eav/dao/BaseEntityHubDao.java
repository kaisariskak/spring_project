package kz.bsbnb.usci.eav.dao;

import kz.bsbnb.usci.eav.model.base.BaseEntity;
import kz.bsbnb.usci.eav.model.core.EavHub;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Jandos Iskakov
 */

public interface BaseEntityHubDao {

    void insert(List<EavHub> hubs);

    Optional<BaseEntity> find(EavHub eavHub, List<String> keys);

    void delete(EavHub eavHub);

    void update(EavHub eavHub);

    void updateDeleted(EavHub eavHub, List<String> keys);

    void updateHash(EavHub eavHub);

}
