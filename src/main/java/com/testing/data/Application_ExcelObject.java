package com.testing.data;

public class Application_ExcelObject {
    public static void main(String... args){
        ExcelObject BS_DATA_VUE = new ExcelObject("C:\\Users\\angel\\Desktop\\Vic\\BS_Data_VUE.xlsx");
//        Map<String,Integer> mapHdrNameHdrCnt = BS_DATA_VUE.getHdrNoFrmHdrNme("BS_DATA_VUE");
//        for(Map.Entry<String,Integer> entry : mapHdrNameHdrCnt.entrySet()){
//            System.out.println("Header name : " + entry.getKey());
//            System.out.println("Header count :" + entry.getValue());
//        }
        String sShtNme = "BS_DATA_VUE";
        String sHdrNme = "ListOfToDos";
        String sContextNme = "VUE_002_UserShouldBeAbleToDeleteToDos";
        String cellValue = BS_DATA_VUE.getWSValue(sShtNme,sHdrNme,sContextNme);
        System.out.println("The value extracted is : " +cellValue);
    }
}
