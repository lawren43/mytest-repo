//
// \u6b64\u6a94\u6848\u662f\u7531 JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 \u6240\u7522\u751f 
// \u8acb\u53c3\u95b1 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// \u4e00\u65e6\u91cd\u65b0\u7de8\u8b6f\u4f86\u6e90\u7db1\u8981, \u5c0d\u6b64\u6a94\u6848\u6240\u505a\u7684\u4efb\u4f55\u4fee\u6539\u90fd\u5c07\u6703\u907a\u5931. 
// \u7522\u751f\u6642\u9593: 2020.06.29 \u65bc 12:34:54 AM CST 
//


package cn.wannshan.j2ee.ws.dto;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cn.wannshan.j2ee.ws.dto package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cn.wannshan.j2ee.ws.dto
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SaveCountryRequest }
     * 
     */
    public SaveCountryRequest createSaveCountryRequest() {
        return new SaveCountryRequest();
    }

    /**
     * Create an instance of {@link Country }
     * 
     */
    public Country createCountry() {
        return new Country();
    }

    /**
     * Create an instance of {@link SaveCountryResponse }
     * 
     */
    public SaveCountryResponse createSaveCountryResponse() {
        return new SaveCountryResponse();
    }

    /**
     * Create an instance of {@link GetCountryRequest }
     * 
     */
    public GetCountryRequest createGetCountryRequest() {
        return new GetCountryRequest();
    }

    /**
     * Create an instance of {@link GetCountryResponse }
     * 
     */
    public GetCountryResponse createGetCountryResponse() {
        return new GetCountryResponse();
    }

}
