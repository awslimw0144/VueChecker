package com.testing.pojo;

import java.util.TreeSet;

public interface ExcelTestable {
    void set_Header_Index(String sheetName,int rowIndex);
    void set_TestID_Index(String sheetName,String sHeaderTestIDCell);
    String getValue(String sSheetName, String sTestID, String sColOfInterest);
    TreeSet<String> getTestIDs(String sSheetName, String sSourceToFind);
}
