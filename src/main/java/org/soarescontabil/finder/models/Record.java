package org.soarescontabil.finder.models;

import com.opencsv.bean.CsvBindByName;

public final class Record {
    
    @CsvBindByName(column = "EMISSAO")
    private final String emission;

    @CsvBindByName(column = "NUMERO")
    private final String number;

    @CsvBindByName(column = "PRODUTO")
    private final String product;

    @CsvBindByName(column = "NCM")
    private final String code;

    @CsvBindByName(column = "CFOP")
    private final String operation;

    @CsvBindByName(column = "CST-PIS")
    private final String pis;

    @CsvBindByName(column = "CST-COFINS")
    private final String cofins;

    @CsvBindByName(column = "LIQUIDO")
    private final String netValue;
    
    private Record(Builder builder) {
        this.emission = builder.emission;
        this.number = builder.number;
        this.product = builder.product;
        this.code = builder.code;
        this.operation = builder.operation;
        this.pis = builder.pis;
        this.cofins = builder.cofins;
        this.netValue = builder.netValue;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getEmission() {
        return emission;
    }
        
    public String getNumber() {
        return number;
    }
        
    public String getProduct() {
        return product;
    }
        
    public String getCode() {
        return code;
    }

    public String getOperation() {
        return operation;
    }
        
    public String getPis() {
        return pis;
    }
        
    public String getCofins() {
        return cofins;
    }
        
    public String getNetValue() {
        return netValue;
    }
    
    @Override
    public String toString() {
        return emission + ", "
        + number + ", "
        + product + ", " 
        + code + ", " 
        + operation + ", " 
        + pis + ", "
        + cofins + ", "
        + netValue;
    }
    
    public static class Builder {

        private String emission;
        private String number;
        private String product;
        private String code;
        private String operation;
        private String pis;
        private String cofins;
        private String netValue;

        public Builder() {}

        public Builder emission(String emission) {
            this.emission = emission;
            return this;
        }

        public Builder number(String number) {
            this.number = number;
            return this;
        }

        public Builder product(String product) {
            this.product = product;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder operation(String operation) {
            this.operation = operation;
            return this;
        }

        public Builder pis(String pis) {
            this.pis = pis;
            return this;
        }

        public Builder cofins(String cofins) {
            this.cofins = cofins;
            return this;
        }

        public Builder netValue(String netValue) {
            this.netValue = netValue;
            return this;
        }

        //Return the finally consrcuted Record object
        public Record build() {
			Record record =  new Record(this);
			validateUserObject(record);
			return record;
		}

        private void validateUserObject(Record record) {
			//Do some basic validations to check
			//if user object does not break any assumption of system
		}

    }
    
}
