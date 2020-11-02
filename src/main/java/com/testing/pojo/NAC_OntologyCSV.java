package com.testing.pojo;

import com.testing.utils.ResrcUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

public class NAC_OntologyCSV {

    private String sFilePath;
    private CSVParser csvParser;
    private String sTestInit = "";

    // TODO : Should make a child Class of this CSVFile so as to better describe the overall specialization.
    private final Map<String,String> mClassAltLabelToClassLabel = new HashMap<>();
    private final Map<String,String> mIndividualLabelToClassLabel = new HashMap<>();
    private final Map<String,String> mIndividualAltLabelToIndividualLabel = new HashMap<>();
    private final Map<String,String> mLocationToPostalCode = new HashMap<>();
    private final Map<String,String> mPostalCodeRangeToRegion = new HashMap<>();
    private final Map<String, List<String>> mClassToListOfIndividual = new LinkedHashMap<>();
    private List<String> lIndividuals;
    private String class_label_prev = "";

    // Singleton = Single instance throughout the entire testing
    private static NAC_OntologyCSV singleInstance = null;
    public static NAC_OntologyCSV getInstance(){
        if(singleInstance == null){
            singleInstance = new NAC_OntologyCSV();
        }
        return singleInstance;
    }

    private void setCSVParser(){
        if(singleInstance != null){
            try {
                this.csvParser = CSVFormat.EXCEL
                        .withFirstRecordAsHeader()
                        .parse(new InputStreamReader(new FileInputStream(this.sFilePath)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void performCSVMapping(){
        this.csvParser.forEach(record->{
            String class_Label = record.get("class_label");
            String class_alt_label = record.get("class_alt_label");
            String individual_id = record.get("individual_id"); // Not needed for now
            String individual_label = record.get("individual_label");
            String individual_alt_label = record.get("individual_alt_label");
            String relation_hasSubTypeOf = record.get("relation_hasSubTypeOf");
            String annotation_postalCode = record.get("annotation_postalCode");
            String annotation_postalCodeRange = record.get("annotation_postalCodeRange");

            if(!this.class_label_prev.equals(class_Label) && !this.class_label_prev.isEmpty()){
                this.mClassToListOfIndividual.put(class_label_prev,this.lIndividuals);
                this.lIndividuals = new ArrayList<>();
            }
            if(this.class_label_prev.isEmpty()){
                this.lIndividuals = new ArrayList<>();
            }
            this.lIndividuals.add(individual_label);
            this.class_label_prev = class_Label;

            if(class_Label.equalsIgnoreCase("Location")){
                this.mLocationToPostalCode.put(individual_label,annotation_postalCode);
            } else if(class_Label.equalsIgnoreCase("Region")){
                Arrays.stream(annotation_postalCodeRange.split(Pattern.quote("|"))).forEach(postalCodeRegion->{
                    this.mPostalCodeRangeToRegion.put(postalCodeRegion.trim(),individual_label);
                });
            }
        });
        System.out.println();
    }

    public void init(String sFilePath){
        this.sFilePath = sFilePath;
        if(this.sTestInit.isEmpty()){
            setCSVParser();
            performCSVMapping();
            this.sTestInit = "Initialized";
        } else {
            System.out.println("Not going to re-initialized again");
        }
    }

    public Map<String, List<String>> getmClassToListOfIndividual(){
        return this.mClassToListOfIndividual;
    }
    public Map<String, String> getmIndividualLabelToClassLabel(){
        return this.mIndividualLabelToClassLabel;
    }
    public Map<String, String> getmIndividualAltLabelToIndividualLabel(){
        return this.mIndividualAltLabelToIndividualLabel;
    }
    public Map<String, String> getmClassAltLabelToClassLabel(){
        return this.getmIndividualAltLabelToIndividualLabel();
    }
    public Map<String, String> getmLocationToPostalCode(){
        return this.mLocationToPostalCode;
    }
    public Map<String, String> getmPostalCodeRangeToRegion(){
        return this.mPostalCodeRangeToRegion;
    }
}