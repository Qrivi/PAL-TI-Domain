package be.peerassistedlearningti.model;

/**
 * Class that specifies a campus
 */
public enum Campus
{

    PROXIMUS;

    public static Campus getByValue(String campus){
        for(Campus c :values()){
            if(c.toString().equalsIgnoreCase(campus)){
                return c;
            }
        }
        return null;
    }
}
