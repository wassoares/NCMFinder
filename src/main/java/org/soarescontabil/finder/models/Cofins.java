package org.soarescontabil.finder.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "COFINS")
public class Cofins {

    private CofinsNt cofinsNt;
    private CofinsOutr cofinsOutr;
    
    @XmlElement(name = "COFINSNT", required = false)
    public CofinsNt getCofinsNt() {
        if (cofinsNt == null) {
            cofinsNt = new CofinsNt();
        }
        return cofinsNt;
    }
    
    public void setCofinsNt(CofinsNt cofinsNt) {
        this.cofinsNt = cofinsNt;
    }
    
    @XmlElement(name = "COFINSOutr", required = false)
    public CofinsOutr getCofinsOutr() {
        if (cofinsOutr == null) {
            cofinsOutr = new CofinsOutr();
        }
        return cofinsOutr;
    }
    
    public void setCofinsOutr(CofinsOutr cofinsOutr) {
        this.cofinsOutr = cofinsOutr;
    }

    @Override
    public String toString() {
        return "Cofins {" 
        + cofinsNt + ", " 
        + cofinsOutr 
        + "}";
    }   
    
}
