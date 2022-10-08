package org.soarescontabil.finder;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.soarescontabil.finder.constants.Constant;
import org.soarescontabil.finder.core.WorksheetNCM;
import org.soarescontabil.finder.core.WriterCSV;
import org.soarescontabil.finder.models.Cofins;
import org.soarescontabil.finder.models.CofinsNt;
import org.soarescontabil.finder.models.CofinsOutr;
import org.soarescontabil.finder.models.Detail;
import org.soarescontabil.finder.models.Identification;
import org.soarescontabil.finder.models.Information;
import org.soarescontabil.finder.models.Invoice;
import org.soarescontabil.finder.models.Pis;
import org.soarescontabil.finder.models.PisNt;
import org.soarescontabil.finder.models.PisOutr;
import org.soarescontabil.finder.models.Process;
import org.soarescontabil.finder.models.Product;
import org.soarescontabil.finder.models.Record;
import org.soarescontabil.finder.models.Tribute;
import org.soarescontabil.finder.utils.Repository;
import org.soarescontabil.finder.utils.UnmarshallerXML;

public class Finder {
    
    static final String REPOSITORY_PATH = "dir/sample/nfe";
    static final String TOP_LINE = "EMISSAO, NUMERO, PRODUTO, NCM, CFOP, CST-PIS, CST-COFINS, LIQUIDO";

    public static void main(String[] args) throws JAXBException {
        
        // extractedAll();
        extractedSinglePhase();
    }

    private static void extractedAll() throws JAXBException {
        // TODO Fix this...
        System.out.println(TOP_LINE);

        double sum = 0.0;
        String value;

        List<Record> records = unmarshallFilesToRecords();
        for (Record record : records) {
            // TODO Fix this...
            System.out.println(record);

            value = replaceToUsFormat(record.getNetValue());
            sum += Double.valueOf(value);
        }
        // TODO Fix this...
        System.out.println("Soma Total: " + sum);

        WriterCSV.saveFile(Constant.DIR_SAMPLE_FILE_PATH, records);
    }

    private static void extractedSinglePhase() throws JAXBException {
        List<Record> result = new ArrayList<>();
        double sum = 0.0;
        String value;
        
        // TODO Fix this...
        System.out.println(TOP_LINE);
        
        List<String> contents = listDataFromSpreadsheet();
        List<Record> records = unmarshallFilesToRecords();
        for (Record record : records) {
            if (contents.contains(record.getCode())
                && (record.getOperation().equals("5102")
                || record.getOperation().equals(Constant.INSIDE_OPERATION_CODE)
                || record.getOperation().equals(Constant.OUTSIDE_OPERATION_CODE)))
            {
                // TODO Fix this...
                System.out.println(record);
                result.add(record);

                value = replaceToUsFormat(record.getNetValue());
                sum += Double.valueOf(value);
            }
        }   
        // TODO Fix this...
        System.out.println("Soma Total: "+ sum);

        WriterCSV.saveFile(Constant.DIR_SAMPLE_FILE_PATH, result);
    }

    private static void extractedNotSinglePhase() throws JAXBException {
        List<Record> result = new ArrayList<>();
        double sum = 0.0;
        String value;

        // TODO Fix this...
        System.out.println(TOP_LINE);
        
        List<String> contents = listDataFromSpreadsheet();
        List<Record> records = unmarshallFilesToRecords();
        for (Record record : records) {
            if (!contents.contains(record.getCode())
                && (record.getOperation().equals("5102")
                || record.getOperation().equals("5656")
                || record.getOperation().equals(Constant.INSIDE_OPERATION_CODE)
                || record.getOperation().equals(Constant.OUTSIDE_OPERATION_CODE)))
            {
                // TODO Fix this...
                System.out.println(record);
                result.add(record);

                value = replaceToUsFormat(record.getNetValue());
                sum += Double.valueOf(value);
            }
        }
        // TODO Fix this...
        System.out.println("Soma Total: "+ sum);

        WriterCSV.saveFile(Constant.DIR_SAMPLE_FILE_PATH, result);
    }

    private static List<String> listDataFromSpreadsheet() {
        WorksheetNCM spreadsheet = new WorksheetNCM(Constant.SPRADSHEET_PATH);
        return spreadsheet.getContentList();
    }

    private static List<Record> unmarshallFilesToRecords() throws JAXBException {
        
        List<Record> records = new ArrayList<>();
        Repository repository = new Repository(REPOSITORY_PATH);
        Collection<File> files = repository.getFiles().values();
        List<Process> processes = getProcessRecordsForm(files);
        
        for (Process process : processes) {
            
            Invoice invoice = process.getInvoice();
            Information information = invoice.getInformation();
            Identification identification = information.getIdentification();
            Collection<Detail> details = information.getDetails();
            
            // TODO Fix this...
            // System.out.println(identification.getNumber());
            
            for (Detail detail : details) {
                
                Product product = detail.getProduct();
                Tribute tribute = detail.getTribute();
                Pis pis = tribute.getPis();
                PisNt pisNt = pis.getPisNt();
                PisOutr pisOutr = pis.getPisOutr();
                String cstPis = pisNt.getCode() + pisOutr.getCode();
                Cofins cofins = tribute.getCofins();
                CofinsNt cofinsNt = cofins.getCofinsNt();
                CofinsOutr cofinsOutr = cofins.getCofinsOutr();
                String cstCofins = cofinsNt.getCode() + cofinsOutr.getCode();
                Double value = getFloatPointFrom(product.getValue());
                Double discount = getFloatPointFrom(product.getDiscount());
                Double netValue = value - discount;
                                
                Record record = Record.builder()
                    .emission(identification.getEmission())
                    .number(identification.getNumber())
                    .product(product.getName())
                    .code(product.getCode())
                    .operation(product.getOperation())
                    .pis(cstPis)
                    .cofins(cstCofins)
                    .netValue(formatForCurrency(netValue))
                    .build();

                records.add(record);
            }
        }
        return records;
    }

    private static String replaceToUsFormat(String value) {
        String amount = value.replace(Constant.DOT_CHARACTER, Constant.EMPTY_STRING);
        return amount.replace(Constant.COMMA_CHARACTER, Constant.DOT_CHARACTER);
    }

    private static String formatForCurrency(Double value) {
        return new DecimalFormat(Constant.CURRENCY_FORMAT).format(value);
    }

    private static Double getFloatPointFrom(String value) {
        String amount = value;
        if (amount == null) {
            amount = Constant.FLOAT_POINT_DOUBLE_ZERO;
        }
        return Double.parseDouble(amount);
    }

    private static List<Process> getProcessRecordsForm(Collection<File> files) throws JAXBException {
        List<Process> records = new ArrayList<>();
        for (File file : files) {
            
            // TODO Fix this...
            // System.out.println(file.getName());

            Process process = (Process) UnmarshallerXML.unmarshalFromFile(file, Process.class);
            records.add(process);
        }
        return records;
    }
    
}
