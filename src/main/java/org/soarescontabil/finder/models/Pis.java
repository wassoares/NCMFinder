package org.soarescontabil.finder.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PIS")
public class Pis {

    private PisNt pisNt;
    private PisOutr pisOutr;
    
    @XmlElement(name = "PISNT", required = false)
    public PisNt getPisNt() {
        if (pisNt == null) {
            pisNt = new PisNt();
        }
        return pisNt;
    }
    
    public void setPisNt(PisNt pisNt) {
        this.pisNt = pisNt;
    }
    
    @XmlElement(name = "PISOutr", required = false)
    public PisOutr getPisOutr() {
        if (pisOutr == null) {
            pisOutr = new PisOutr();
        }
        return pisOutr;
    }
    
    public void setPisOutr(PisOutr pisOutr) {
        this.pisOutr = pisOutr;
    }
    
    @Override
    public String toString() {
        return "PIS {"
        + pisNt + ", "
        + pisOutr
        + "}";
    }
            
}
