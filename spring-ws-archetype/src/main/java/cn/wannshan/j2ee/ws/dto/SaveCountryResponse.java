//
// \u6b64\u6a94\u6848\u662f\u7531 JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 \u6240\u7522\u751f 
// \u8acb\u53c3\u95b1 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// \u4e00\u65e6\u91cd\u65b0\u7de8\u8b6f\u4f86\u6e90\u7db1\u8981, \u5c0d\u6b64\u6a94\u6848\u6240\u505a\u7684\u4efb\u4f55\u4fee\u6539\u90fd\u5c07\u6703\u907a\u5931. 
// \u7522\u751f\u6642\u9593: 2020.06.29 \u65bc 12:34:54 AM CST 
//


package cn.wannshan.j2ee.ws.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type \u7684 Java \u985e\u5225.
 * 
 * <p>\u4e0b\u5217\u7db1\u8981\u7247\u6bb5\u6703\u6307\u5b9a\u6b64\u985e\u5225\u4e2d\u5305\u542b\u7684\u9810\u671f\u5167\u5bb9.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name"
})
@XmlRootElement(name = "saveCountryResponse")
public class SaveCountryResponse {

    @XmlElement(required = true)
    protected String name;

    /**
     * \u53d6\u5f97 name \u7279\u6027\u7684\u503c.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * \u8a2d\u5b9a name \u7279\u6027\u7684\u503c.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
