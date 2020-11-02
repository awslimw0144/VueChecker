package com.testing.data;

import com.testing.pojo.NAC_DataBase;
import com.testing.pojo.ExcelTestable;
import com.testing.utils.ResrcUtils;

public class Application_ExcelObject {
    public static void main(String... args){
        String filePath = ResrcUtils.getFilePath(Application_ExcelObject.class,"Nac-database-20200908-Sep-Accuracy-Report2.xlsx");
        ExcelTestable nacDataBase = new NAC_DataBase(filePath);
        nacDataBase.set_Header_Index("DataSet",0);
        nacDataBase.set_TestID_Index("DataSet","id");
        String sPath = nacDataBase.getValue("Dataset","screen1020e","path");
        String sContent = nacDataBase.getValue("Dataset","screen1020e","content");
        System.out.printf("URL is %s%n",sPath);
    }
}