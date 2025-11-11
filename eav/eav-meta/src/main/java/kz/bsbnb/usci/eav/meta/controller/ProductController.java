package kz.bsbnb.usci.eav.meta.controller;

import kz.bsbnb.usci.eav.model.meta.json.MetaClassJson;
import kz.bsbnb.usci.eav.model.meta.json.ProductJson;
import kz.bsbnb.usci.eav.service.ProductService;
import kz.bsbnb.usci.model.adm.Position;
import kz.bsbnb.usci.model.batch.Approval;
import kz.bsbnb.usci.model.batch.Product;
import kz.bsbnb.usci.model.json.ApproveIterationJson;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Olzhas Kaliaskar
 */

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "getProductList")
    public List<ProductJson> getProductList() {
        return productService.getProducts().stream()
                // показываем все продукты кроме справочников
               // .filter(product -> !product.getCode().equals("DICT"))
                .map(ProductController::convertProductToJson)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "getProducts")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping(value = "getProductById")
    public Product getProductById(@RequestParam(name = "id") Long id) {
        return productService.getProductById(id);
    }

    @GetMapping(value = "getProductJsonById")
    public ProductJson getProductJsonById(@RequestParam(name = "id") Long id) {
        return convertProductToJson(productService.getProductById(id));
    }

    @GetMapping(value = "findProductByCode")
    public Product findProductByCode(@RequestParam(name = "code") String code) {
        return productService.findProductByCode(code).orElse(null);
    }

    @PostMapping(value = "saveProduct")
    public void saveProduct(@RequestBody ProductJson json) {
        if (json.getId() == null)
            productService.createProduct(json);
        else
            productService.updateProduct(json);

    }

    @GetMapping(value = "getMetaClasses")
    public List<MetaClassJson> getMetaClasses(@RequestParam(name = "productId") Long productId,
                                              @RequestParam(name = "available") boolean available) {
        return productService.getMetaClasses(productId, available);
    }

    @GetMapping(value = "getPositions")
    public List<Position> getPositions(@RequestParam(name = "productId") Long productId,
                                         @RequestParam(name = "available") boolean available) {
        return productService.getPositions(productId, available);
    }

    @PutMapping(value = "addProductMetaClass")
    public void addProductMetaClass(@RequestParam(name = "productId") Long productId,
                                    @RequestParam(name = "metaIds") List<Long> metaIds) {
        productService.addProductMetaClass(productId, metaIds);
    }

    @PostMapping(value = "deleteProductMetaClass")
    public void deleteProductMetaClass(@RequestParam(name = "productId") Long productId,
                                       @RequestParam(name = "metaIds") List<Long> metaIds) {
        productService.deleteProductMetaClass(productId, metaIds);
    }

    @PostMapping(value = "generateXsd")
    public void generateXsd() {
        productService.generateXsd();
    }

    @GetMapping(value = "getProductXsd")
    public byte[] getProductXsd(@RequestParam(name = "productId") long productId)  {
        return productService.getProductById(productId).getXsd();
    }

    @GetMapping(value = "getProductIdsByMetaClassId")
    public List<Long> getProductIdsByMetaClassId(@RequestParam(name = "metaClassId") Long metaClassId) {
        return productService.getProductIdsByMetaClassId(metaClassId);
    }

    //@PostMapping(value = "addProductPosition")
    @GetMapping(value = "addProductPosition")
    public void addProductPosition(@RequestParam(name = "productId") Long productId,
                                    @RequestParam(name = "posIds") List<Long> posIds) {
        productService.addProductPosition(productId, posIds);
    }

    //@PostMapping(value = "deleteProductPosition")
    @GetMapping(value = "deleteProductPosition")
    public void deleteProductPosition(@RequestParam(name = "productId") Long productId,
                                       @RequestParam(name = "posIds") List<Long> posIds) {
        productService.deleteProductPosition(productId, posIds);
    }

    @GetMapping(value = "getApproveIterations")
    public List<ApproveIterationJson> getApproveIterations(@RequestParam(name = "productId") Long productId,
                                                           @RequestParam(name = "metaClassId") Long metaClassId) {
        return productService.getApproveIterations(productId, metaClassId);
    }

    @PostMapping(value = "setApproveIterations")
    public void setApproveIterations(@RequestParam(name = "productId") Long productId,
                                     @RequestParam(name = "metaClassId") Long metaClassId,
                                     @RequestBody List<ApproveIterationJson> approveIterationJsonList) {
        productService.setApproveIterations(productId, metaClassId, approveIterationJsonList);
    }

    @GetMapping(value = "checkForApprove")
    public boolean checkForApprove(@RequestParam(name = "metaClassId") Long metaClassId,
                                   @RequestParam(name = "respondentTypeId") Long respondentTypeId,
                                   @RequestParam(name = "receiptDateMillis") Long receiptDateMillis,
                                   @RequestParam(name = "reportDateMillis") Long reportDateMillis) {
        return productService.checkForApprove(metaClassId, respondentTypeId, receiptDateMillis, reportDateMillis);
    }

    public static ProductJson convertProductToJson(Product product) {
        ProductJson json = new ProductJson();
        json.setId(product.getId());
        json.setCode(product.getCode());
        json.setName(product.getName());
        json.setCrosscheckPackageName(product.getCrosscheckPackageName());
        json.setConfirmWithApproval(product.isConfirmWithApproval());
        json.setConfirmWithSignature(product.isConfirmWithSignature());
        json.setConfirmPositionIds(product.getConfirmPositionIds());
        return json;
    }

    @PostMapping(value = "calcDeadLine")
    public void calcDeadLine(@RequestParam(name = "year") String year) {
        productService.calcDeadLine(year);
    }

    @GetMapping(value = "readApprovalList")
    public List<Approval> readApprovalList(@RequestParam(name = "metaClassId") Long metaClassId,
                                           @RequestParam(name = "respondentTypeId") Long respondentTypeId) {
        return productService.readApprovalList(metaClassId,respondentTypeId);
    }

    @GetMapping(value = "readApproval")
    public LocalDateTime readApproval(@RequestParam(name = "metaClassId") Long metaClassId,
                                      @RequestParam(name = "respondentTypeId") Long respondentTypeId,
                                      @RequestParam (name = "reportDate") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate reportDate) {
        return productService.readApproval(metaClassId,respondentTypeId, reportDate);
    }

    @PostMapping(value = "updateApproval")
    public void updateApproval(@RequestParam(name = "metaClassId") Long metaClassId,
                               @RequestParam(name = "respondentTypeId") Long respondentTypeId,
                               @RequestParam (name = "reportDate") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate reportDate,
                               @RequestParam (name = "deadLine") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss") LocalDateTime deadLine ) {
        Approval approval = new Approval();
        approval.setClassId(metaClassId);
        approval.setRespondentTypeId(respondentTypeId);
        approval.setReportDate(reportDate);
        approval.setDeadLine(deadLine);
        productService.updateApproval(approval);
    }
}
