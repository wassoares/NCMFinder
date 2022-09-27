package org.soarescontabil.finder.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "NFe")
public class Invoice {
    
    private Information information;
    
    @XmlElement(name = "infNFe", required = true)
    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    @Override
    public String toString() {
        return "NFe{" + information + "}";
    }
    
}
