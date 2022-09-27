package org.soarescontabil.finder.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "prod")
@XmlAccessorType(XmlAccessType.NONE)
public class Product {
    
    private String name;
    private String code;
    private String operation;
    private String value;
    private String discount;
    
    @XmlElement(name = "xProd", required = true)
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @XmlElement(name = "NCM", required = true)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlElement(name = "CFOP", required = true)
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
    
    @XmlElement(name = "vProd", required = true)
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    @XmlElement(name = "vDesc", required = false)
    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "prod{"
        + "xProd=" + name + ", "
        + "NCM=" + code + ", "
        + "CFOP" + operation + ", "
        + "vProd=" + value + ", "
        + "vDesc=" + discount
        + "}";
    }
    
}
