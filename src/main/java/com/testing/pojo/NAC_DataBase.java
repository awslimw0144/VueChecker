package com.testing.pojo;

import org.apache.poi.ss.usermodel.Sheet;

import java.util.*;

public class NAC_DataBase extends Excel implements ExcelTestable {

    private Map<String, Integer> mapHdrNoFrmHdrNme = new HashMap<>();
    private Map<String, Integer> mapRowNoFrmHdrNme = new HashMap<>();

    public NAC_DataBase(String filePath) {
        super(filePath);
    }

    @Override
    public void set_Header_Index(String sheetName, int iRowIndex) {
        Sheet sheet = super.getWorksheet(sheetName);
        int hSize = sheet.getRow(0).getLastCellNum();
        int intHdrNo;

        for (intHdrNo = 0; intHdrNo < hSize; intHdrNo++) {
            String value = sheet.getRow(iRowIndex).getCell(intHdrNo).getStringCellValue();
            this.mapHdrNoFrmHdrNme.put(value, intHdrNo);
        }
    }

    @Override
    public void set_TestID_Index(String sheetName, String sHeaderTestIDCell) {
        Sheet sheet = super.getWorksheet(sheetName);
        int rSize = sheet.getLastRowNum();
        int intRowNo;

        for (intRowNo = 0; intRowNo < rSize; intRowNo++) {
            String value = sheet.getRow(intRowNo).getCell(this.mapHdrNoFrmHdrNme.get(sHeaderTestIDCell)).getStringCellValue();
            this.mapRowNoFrmHdrNme.put(value, intRowNo);
        }
    }

    @Override
    public String getValue(String sSheetName, String sTestID, String sColOfInterest) {
        if(super.getWorksheet(sSheetName)
                .getRow(this.mapRowNoFrmHdrNme.get(sTestID))
                .getCell(this.mapHdrNoFrmHdrNme.get(sColOfInterest)) == null) return "";

        return super.getWorksheet(sSheetName)
                .getRow(this.mapRowNoFrmHdrNme.get(sTestID))
                .getCell(this.mapHdrNoFrmHdrNme.get(sColOfInterest))
                .getStringCellValue();
    }

    @Override
    public TreeSet<String> getTestIDs(String sSheetName, String sSourceOfInterest) {
        TreeSet<String> treeSet = new TreeSet<>();
        this.mapRowNoFrmHdrNme.forEach((sTestID, index)->{
            String source = super.getWorksheet(sSheetName)
                    .getRow(this.mapRowNoFrmHdrNme.get(sTestID))
                    .getCell(this.mapHdrNoFrmHdrNme.get("source"))
                    .getStringCellValue();

            boolean testCellIsNull = (super.getWorksheet(sSheetName)
                    .getRow(this.mapRowNoFrmHdrNme.get(sTestID))
                    .getCell(this.mapHdrNoFrmHdrNme.get("Tested Sample")) == null);

            if(!testCellIsNull){
                boolean testCellIsChecked = (super.getWorksheet(sSheetName)
                        .getRow(this.mapRowNoFrmHdrNme.get(sTestID))
                        .getCell(this.mapHdrNoFrmHdrNme.get("Tested Sample"))
                        .getStringCellValue().equals("✅"));

                if(((testCellIsChecked)&&(source.equals(sSourceOfInterest)))){
                    treeSet.add(sTestID);
                }
            }
        });
        return treeSet;
    }
}