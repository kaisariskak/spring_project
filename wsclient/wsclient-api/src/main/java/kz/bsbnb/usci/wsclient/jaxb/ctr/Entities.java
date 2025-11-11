
package kz.bsbnb.usci.wsclient.jaxb.ctr;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;


/**
 * <p>Java class for entities complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="entities">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ctr_transaction" type="{}ctr_transaction" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "entities", propOrder = {
    "ctrTransaction"
})

@XmlRootElement(name = "entities")
public class Entities {

    @XmlElement(name = "ctr_transaction", required = true)
    protected List<CtrTransaction> ctrTransaction;

    /**
     * Gets the value of the ctrTransaction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ctrTransaction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCtrTransaction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CtrTransaction }
     * 
     * 
     */
    public List<CtrTransaction> getCtrTransaction() {
        if (ctrTransaction == null) {
            ctrTransaction = new ArrayList<CtrTransaction>();
        }
        return this.ctrTransaction;
    }

}
