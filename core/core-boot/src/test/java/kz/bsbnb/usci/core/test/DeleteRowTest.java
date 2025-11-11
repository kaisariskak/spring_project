package kz.bsbnb.usci.core.test;


import kz.bsbnb.usci.eav.model.base.BaseEntity;
import kz.bsbnb.usci.eav.model.base.OperType;
import kz.bsbnb.usci.eav.model.meta.MetaClass;
import kz.bsbnb.usci.eav.service.BaseEntityProcessor;
import kz.bsbnb.usci.eav.service.MetaClassService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeleteRowTest {

    @Autowired
    private BaseEntityProcessor baseEntityProcessor;
    @Autowired
    private MetaClassService metaClassService;

    @Test
    public  void test0() {

        MetaClass metaClass = metaClassService.getMetaClass(30L);
        BaseEntity baseEntity = new BaseEntity();
        baseEntity.setOperation(OperType.DELETE_ROW);
        baseEntity.setId(50000508L);
        baseEntity.setBatchId(3288L);
        baseEntity.setRespondentId(25L);
        baseEntity.setReportDate(LocalDate.of(2019, 7, 12));
        baseEntity.setMetaClass(metaClass);

        baseEntityProcessor.processBaseEntity(baseEntity);


    }


}
