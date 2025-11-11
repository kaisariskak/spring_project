package kz.bsbnb.usci.eav.service;

import kz.bsbnb.usci.eav.model.meta.json.MetaClassJson;
import kz.bsbnb.usci.eav.model.meta.json.ProductJson;
import kz.bsbnb.usci.model.adm.Position;
import kz.bsbnb.usci.model.batch.Approval;
import kz.bsbnb.usci.model.batch.Product;
import kz.bsbnb.usci.model.json.ApproveIterationJson;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Jandos Iskakov
 * @author Olzhas Kaliaskar
 * */

public interface ProductService {

    List<Product> getProducts();

    void generateXsd();

    Product getProductById(Long id);

    Optional<Product> findProductByCode(String code);

    long createProduct(ProductJson product);

    void updateProduct(ProductJson json);

    void addProductMetaClass(long productId, List<Long> metaIds);

    void deleteProductMetaClass(long productId, List<Long> metaIds);

    List<MetaClassJson> getMetaClasses(long productId, boolean available);

    boolean isOpenDictionary(Long id);

    List<Long> getProductIdsByMetaClassId(Long metaClassId);

    List<Position> getPositions(Long productId, boolean available);

    void addProductPosition(Long productId, List<Long> posIds);

    void deleteProductPosition(Long productId, List<Long> posIds);

    List<ApproveIterationJson> getApproveIterations(Long productId, Long metaClassId);

    void setApproveIterations(Long productId, Long metaClassId, List<ApproveIterationJson> approveIterationJsonList);

    boolean checkForApprove(Long metaClassId,Long respondentTypeId, Long receiptDateMillis, Long reportDateMillis) ;

    void updateApproval(Approval approval);

    List <Approval> readApprovalList(Long metaClassId,Long respondentTypeId);

    LocalDateTime readApproval(Long metaClassId, Long respondentTypeId, LocalDate reportDate);

    void calcDeadLine(String year);
}
