package org.soarescontabil.finder.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "infNFe")
public class Information {
    
    private Identification identification;
    private Collection<Detail> details = new ArrayList<Detail>();
    
    @XmlElement(name = "ide", required = true)
    public Identification getIdentification() {
        return identification;
    }
    
    public void setIdentification(Identification identification) {
        this.identification = identification;
    }
    
    @XmlElement(name = "det", required = false)
    public Collection<Detail> getDetails() {
        return details;
    }

    public void setDetails(Collection<Detail> details) {
        this.details = details;
    }
    
    @Override
    public String toString() {
        return "infNFe{"
        + identification + ","
        + details
        + "}";
    }
    
}
