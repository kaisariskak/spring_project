package kz.bsbnb.usci.eav.service;

import kz.bsbnb.usci.eav.model.meta.MetaClass;
import kz.bsbnb.usci.eav.model.meta.json.MetaAttributeJson;
import kz.bsbnb.usci.eav.model.meta.json.MetaClassJson;
import kz.bsbnb.usci.model.ui.UiConfig;
import kz.bsbnb.usci.util.json.ext.ExtJsTree;

import java.util.List;
import java.util.Map;

/**
 * @author Jandos Iskakov
 */

public interface MetaClassService {
    List<MetaClass> getMetaClasses();

    MetaClass getMetaClass(String name);

    MetaClass getMetaClass(Long metaId);

    MetaClassJson getMetaClassJson(Long metaId);

    boolean saveMetaAttribute(MetaAttributeJson metaAttributeJson,Long userId);

    boolean saveMetaClass(MetaClassJson metaClassJson);

    boolean delMetaClass(Long metaClassId);

    List<MetaClassJson> getMetaClassJsonList();

    List<MetaClassJson> getMetaClassJsonListByUserId(long userId);

    List<MetaClassJson> getDictionaryJsonList();

    List<MetaAttributeJson> getMetaClassAttributes(Long metaClassId);

    List<ExtJsTree> getMetaClassAttributes(String node);

    MetaAttributeJson getMetaAttribute(Long metaClassId, Long attributeId);

    void delMetaAttribute(String attrPathPart, String attrPathCode, Long userId);

    void syncWithDb();

    void resetCache();

    Map<Long, UiConfig> getUiConfigs();

    UiConfig getUiConfig(Long metaClassId);

    List<MetaClassJson> getMetaPositionList(boolean isHavePosition);
}
