package com.testing.utils;

import com.testing.pojo.NAC_OntologyCSV;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OntologyFieldManager {

    // Singleton = Single instance throughout the entire testing
    private static OntologyFieldManager singleInstance = null;

    // TODO : Delete these Lists
    // List Of Global Variables
    private List<String> lAltWords_Dance;
    private List<String> lAltWords_Film;
    private List<String> lAltWords_Literary;
    private List<String> lAltWords_Music;
    private List<String> lAltWords_Photography;
    // --> Surprise Me will not be classified as it is just to say that there is another Genre(s) tied to current
    private List<String> lAltWords_Theatre;
    private List<String> lAltWords_Visual;

    private static Map<String,Integer> mapAltWords_Individual;

    public static OntologyFieldManager getInstance(){
        if (singleInstance == null){
            singleInstance = new OntologyFieldManager();
//            mapAltWords_Individual = new HashMap<>();
//            singleInstance.setlAltWords_Dance();
//            singleInstance.setlAltWords_Film();
//            singleInstance.setlAltWords_Literary();
//            singleInstance.setlAltWords_Music();
//            singleInstance.setlAltWords_Photography();
//            singleInstance.setlAltWords_Theatre();
//            singleInstance.setlAltWords_Visual();
        }
        return singleInstance;
    }

    private void setlAltWords_Dance(){
        this.lAltWords_Dance  = new ArrayList<>();
        this.lAltWords_Dance.add("Dance");
        this.lAltWords_Dance.add("Dancers");
        this.lAltWords_Dance.add("Dancer");
    }
    private void setlAltWords_Film(){
        this.lAltWords_Film  = new ArrayList<>();
        this.lAltWords_Film.add("Film");
        this.lAltWords_Film.add("Cinema");
        this.lAltWords_Film.add("Films");
    }
    private void setlAltWords_Literary(){
        this.lAltWords_Literary = new ArrayList<>();
        this.lAltWords_Literary.add("Literary");
    }
    private void setlAltWords_Music(){
        this.lAltWords_Music  = new ArrayList<>();
        this.lAltWords_Music.add("Music");
        this.lAltWords_Music.add("Concert");
        this.lAltWords_Music.add("Musical");
        this.lAltWords_Music.add("Brass Ensemble");
        this.lAltWords_Music.add("Composers");
        this.lAltWords_Music.add("Concerto");
        this.lAltWords_Music.add("Musician");
        this.lAltWords_Music.add("Piano");
        this.lAltWords_Music.add("Symphony");
        this.lAltWords_Music.add("Trumpet");
        this.lAltWords_Music.add("Composer");
        this.lAltWords_Music.add("Concerts");
        this.lAltWords_Music.add("Musicians");
        this.lAltWords_Music.add("Singing");
    }
    private void setlAltWords_Photography(){
        this.lAltWords_Photography = new ArrayList<>();
        this.lAltWords_Photography.add("Photography");
    }
    private void setlAltWords_Theatre(){
        this.lAltWords_Theatre  = new ArrayList<>();
        this.lAltWords_Theatre.add("Theatre");
        this.lAltWords_Theatre.add("Actor");
        this.lAltWords_Theatre.add("Theatrical");
        this.lAltWords_Theatre.add("Performers");
        this.lAltWords_Theatre.add("Playwright");
        this.lAltWords_Theatre.add("Actors");
        this.lAltWords_Theatre.add("Theater");
        this.lAltWords_Theatre.add("Performer");
    }
    private void setlAltWords_Visual(){
        this.lAltWords_Visual = new ArrayList<>();
        this.lAltWords_Visual.add("Visual");
    }

    public List<String> getAltWords_Dance(){
        return this.lAltWords_Dance;
    }
    public List<String> getAltWords_Film(){
        return this.lAltWords_Film;
    }
    public List<String> getAltWords_Literary(){
        return this.lAltWords_Literary;
    }
    public List<String> getAltWords_Music(){
        return this.lAltWords_Music;
    }
    public List<String> getAltWords_Photography(){
        return this.lAltWords_Photography;
    }
    public List<String> getAltWords_Theatre(){
        return this.lAltWords_Theatre;
    }
    public List<String> getAltWords_Visual(){
        return this.lAltWords_Visual;
    }

    public static void main(String...args){
        NAC_OntologyCSV.getInstance().init(ResrcUtils.getFilePath(NAC_OntologyCSV.class,"nac-ontology.csv"));
        List<String> lGenre = ExtractTagField.of("Genre");
        Map<String, String> lPostalRegion = ExtractTagField.ofPostalRegion();
        Map<String, String> lLocation = ExtractTagField.ofLocation();
        System.out.println(lGenre.toString());
        //lPostalRegion.get(lLocation.get("Esplanade").substring(0,2));

    }

}