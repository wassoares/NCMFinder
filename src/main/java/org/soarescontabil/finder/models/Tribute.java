package org.soarescontabil.finder.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "imposto")
@XmlAccessorType(XmlAccessType.NONE)
public class Tribute {
    
    private Pis pis;
    private Cofins cofins;
    
    @XmlElement(name = "PIS", required = false)
    public Pis getPis() {
        if (pis == null) {
            pis = new Pis();
        }
        return pis;
    }
    
    public void setPis(Pis pis) {
        this.pis = pis;
    }
    
    @XmlElement(name = "COFINS", required = false)
    public Cofins getCofins() {
        if (cofins == null) {
            cofins = new Cofins();
        }
        return cofins;
    }

    public void setCofins(Cofins cofins) {
        this.cofins = cofins;
    }

    @Override
    public String toString() {
        return "imposto{"
        + pis + ", "
        + cofins
        + "}";
    }
   
}
