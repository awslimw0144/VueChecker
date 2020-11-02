package com.testing.utils;

import com.testing.pojo.NAC_OntologyCSV;
import java.util.*;

public class ExtractTagField {
    /**
     * @param sTagName @Description This is based on NAC-iSearch NLP Rule
     * @return
     */
    public static List<String> of(String sTagName){
        List<String> list = new ArrayList<>();
        if(!(sTagName.equalsIgnoreCase("Region")||sTagName.equalsIgnoreCase("Location"))){
            NAC_OntologyCSV.getInstance().getmClassToListOfIndividual().entrySet()
                    .stream()
                    .filter(map-> map.getKey().equals(sTagName))
                    .forEach(entry->{
                        list.addAll(entry.getValue());
                    });
            return list;
        }
        return null;
    }

    public static Map<String,String> ofLocation(){
        return NAC_OntologyCSV.getInstance().getmLocationToPostalCode();
    }
    public static Map<String,String> ofPostalRegion(){
        return NAC_OntologyCSV.getInstance().getmPostalCodeRangeToRegion();
    }
}