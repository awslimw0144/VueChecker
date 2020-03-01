package com.taiger.data;

import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ExcelObject {
    private XSSFWorkbook xWrkBk;
    private File file;
    private FileInputStream fIP;
    Map<String, Integer> mapHdrNoFrmHdrNme;
    Map<String, Integer> mapRowNoFrmHdrNme;
    private Map<String, Map<String, Integer>> mapSht_CellValues = new HashMap<>();

    public ExcelObject(String filePath) {
        file = new File(filePath);
        try {
            fIP = new FileInputStream(file);
            xWrkBk = new XSSFWorkbook(fIP);
            if (file.isFile() && file.exists()) {
                setWShtToCellValuesMapping();
            } else {
                System.out.println("File not found");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fIP.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // ONLY FOR INTERNAL CLASS TO USE
    private void setWShtToCellValuesMapping(){
        int wSheetCount = xWrkBk.getNumberOfSheets();
        for (int i = 0; i < wSheetCount; i++) {
            String sShtNme = xWrkBk.getSheetName(i);
            System.out.println("Commence Setting up Header Name Mapping for sheet " + sShtNme);
            setHdrNoFrmHdrNme(sShtNme);
            setRowNoFromCtxID(sShtNme);
            System.out.println("Status for setup of sheet " + sShtNme + " DONE");
        }
    }
    private void setHdrNoFrmHdrNme(String wShtNme) {
        XSSFSheet xWSht = xWrkBk.getSheet(wShtNme);
        mapHdrNoFrmHdrNme = new HashMap<>();
        int hSize = xWSht.getRow(0).getLastCellNum();
        int intHdrNo;

        for (intHdrNo = 0; intHdrNo < hSize; intHdrNo++) {
            String value = xWSht.getRow(0).getCell(intHdrNo).getStringCellValue();
            mapHdrNoFrmHdrNme.put(value, intHdrNo);
        }
        mapSht_CellValues.put(wShtNme+"_ForHdr",mapHdrNoFrmHdrNme);
    }
    private void setRowNoFromCtxID(String wShtNme) {
        XSSFSheet xWSht = xWrkBk.getSheet(wShtNme);
        mapRowNoFrmHdrNme = new HashMap<>();
        int rSize = xWSht.getLastRowNum();
        int intRowNo;

        for (intRowNo = 0; intRowNo < rSize; intRowNo++) {
            String value = xWSht.getRow(intRowNo).getCell(0).getStringCellValue();
            mapRowNoFrmHdrNme.put(value, intRowNo);
        }
        mapSht_CellValues.put(wShtNme+"_ForRow",mapRowNoFrmHdrNme);
    }

    // GET HEADER NUMBER BASED ON HEADER NAME
    public String getWSValue(String sShtNme, String sHdrNme, String sContextName){
        if(mapSht_CellValues!=null){
            // FIND WORKSHEET
            Map<String,Integer> mapHdr = mapSht_CellValues.get(sShtNme+"_ForHdr");
            Map<String,Integer> mapRow = mapSht_CellValues.get(sShtNme+"_ForRow");
            int rNo = mapRow.get(sContextName);
            int hNo = mapHdr.get(sHdrNme);
            // FIND VALUE
            XSSFSheet xWSht = xWrkBk.getSheet(sShtNme);
            String getValue = xWSht.getRow(rNo).getCell(hNo).getStringCellValue();
            if (getValue.toUpperCase().equals("OUT OF SCOPE")) return "";
            else return getValue;
        }
        return "No Value extracted";
    }
}
