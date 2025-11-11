
package kz.bsbnb.usci.ws.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://usci.bsbnb.kz/ws/schema}responseInfo"/>
 *         &lt;element name="generatedBatchName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "responseInfo",
    "generatedBatchName"
})
@XmlRootElement(name = "GetUsciEntitiesResponse", namespace = "http://usci.bsbnb.kz/ws/schema")
public class GetUsciEntitiesResponse {

    @XmlElement(namespace = "http://usci.bsbnb.kz/ws/schema", required = true)
    protected ResponseInfo responseInfo;
    @XmlElement(namespace = "http://usci.bsbnb.kz/ws/schema")
    protected String generatedBatchName;

    /**
     * Gets the value of the responseInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseInfo }
     *     
     */
    public ResponseInfo getResponseInfo() {
        return responseInfo;
    }

    /**
     * Sets the value of the responseInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseInfo }
     *     
     */
    public void setResponseInfo(ResponseInfo value) {
        this.responseInfo = value;
    }

    /**
     * Gets the value of the generatedBatchName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeneratedBatchName() {
        return generatedBatchName;
    }

    /**
     * Sets the value of the generatedBatchName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeneratedBatchName(String value) {
        this.generatedBatchName = value;
    }

}
