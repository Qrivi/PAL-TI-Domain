package be.peerassistedlearningti.model;

/**
 * Class that specifies the type of a room
 */
public enum RoomType
{

    COMPUTER,
    CHEMISTRY,
    PLAIN;

    public static RoomType getByValue(String type){
        for(RoomType t :values()){
            if(t.toString().equalsIgnoreCase(type)){
                return t;
            }
        }
        return null;
    }

}
