package org.soarescontabil.finder.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ide")
@XmlAccessorType(XmlAccessType.NONE)
public class Identification {
    
    private String number;
    private String emission;
    
    @XmlElement(name = "nNF", required = true)
    public String getNumber() {
        return number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
    
    @XmlElement(name = "dhEmi", required = true)
    public String getEmission() {
        return emission;
    }
    
    public void setEmission(String emission) {
        this.emission = emission;
    }

    @Override
    public String toString() {
        return "ide{" 
        + "nNF=" + number + ", "
        + "dhEmi=" + emission
        + "}";
    }
    
}
