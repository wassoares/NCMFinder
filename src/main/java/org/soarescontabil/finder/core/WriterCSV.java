package org.soarescontabil.finder.core;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.collections4.comparators.FixedOrderComparator;
import org.soarescontabil.finder.constants.Constant;
import org.soarescontabil.finder.models.Record;

import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public final class WriterCSV {
    
    private WriterCSV() {}

    public static void saveFile(String path, List<Record> records) {
        try {
            Writer writer = Files.newBufferedWriter(Paths.get(path));
            builderCSV(writer, records);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void builderCSV(Writer writer, List<Record> records) {
        try {
            StatefulBeanToCsv<Record> beanToCsv = new StatefulBeanToCsvBuilder<Record>(writer)
                .withMappingStrategy(doMappingStrategyForColumnHeader())
                .build();
            beanToCsv.write(records);
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
    }

    private static HeaderColumnNameMappingStrategy<Record> doMappingStrategyForColumnHeader() {
        HeaderColumnNameMappingStrategy<Record> mappingStrategy;
        mappingStrategy = new HeaderColumnNameMappingStrategy<Record>();
        mappingStrategy.setType(Record.class);
        mappingStrategy.setColumnOrderOnWrite(
            new FixedOrderComparator<>(
                Constant.HEADER_COLUMN_NAME_ZERO_POSITION, 
                Constant.HEADER_COLUMN_NAME_FIRST_POSITION, 
                Constant.HEADER_COLUMN_NAME_SECOND_POSITION, 
                Constant.HEADER_COLUMN_NAME_THIRD_POSITION, 
                Constant.HEADER_COLUMN_NAME_FOURTH_POSITION, 
                Constant.HEADER_COLUMN_NAME_FIFTH_POSITION, 
                Constant.HEADER_COLUMN_NAME_SIXTH_POSITION, 
                Constant.HEADER_COLUMN_NAME_SEVENTH_POSITION
            )
        );
        return mappingStrategy;
    }

}
