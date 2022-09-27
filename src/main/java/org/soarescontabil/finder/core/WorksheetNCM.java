package org.soarescontabil.finder.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.soarescontabil.finder.constants.Constant;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class WorksheetNCM {
    
    private Workbook workbook;

    public WorksheetNCM(String path) {
        try {
            this.workbook = Workbook.getWorkbook(new File(path));
        } catch (BiffException | IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getContentList() {
        int column = Constant.FIRST_COLUMN;
        Cell cell = null;
        Sheet sheet = workbook.getSheet(column);
        List<String> contentList = new ArrayList<String>();
        for(int index = 0; index < sheet.getRows(); index++) {
            cell = sheet.getCell(column, index);
            contentList.add(cell.getContents()
                .replaceAll(Constant.REGEX_ALL_SPECIAL_CHARACTERS, 
                Constant.EMPTY_STRING));
        }
        return contentList;
    }
    
}
