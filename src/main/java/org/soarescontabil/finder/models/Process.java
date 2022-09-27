package org.soarescontabil.finder.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
    name = "nfeProc", 
    namespace = "http://www.portalfiscal.inf.br/nfe"
 )
public class Process {
    
    private Invoice invoice;
    
    @XmlElement(name = "NFe", required = true)
    public Invoice getInvoice() {
        return invoice;
    }
    
    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    public String toString() {
        return "nfeProc{" + invoice + "}";
    }

}
