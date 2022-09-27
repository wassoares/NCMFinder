package org.soarescontabil.finder.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.soarescontabil.finder.constants.Constant;

@XmlRootElement(name = "PISOutr")
public class PisOutr {
    
    private String code;
    
    @XmlElement(name = "CST", required = true)
    public String getCode() {
        if (code == null) {
            code = Constant.EMPTY_STRING;
        }
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "PISOutr{"
        + "CST=" + code
        + "}";
    }
}
