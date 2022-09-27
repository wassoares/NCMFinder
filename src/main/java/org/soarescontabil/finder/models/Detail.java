package org.soarescontabil.finder.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "det")
@XmlAccessorType(XmlAccessType.NONE)
public class Detail {
    
    private Integer item;
    private Product product;
    private Tribute tribute;
    
    @XmlAttribute(name = "nItem", required = true)
    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    @XmlElement(name = "prod", required = true)
    public Product getProduct() {
        return product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }
    
    @XmlElement(name = "imposto", required = true)
    public Tribute getTribute() {
        return tribute;
    }

    public void setTribute(Tribute tribute) {
        this.tribute = tribute;
    }
    
    @Override
    public String toString() {
        return "det nItem=" + item + "{" 
        + product + "," 
        + tribute
        + "}";
    }

}
