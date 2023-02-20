package kineticnetwork.net.chatfilter.DataBase;

public class Statements {
    public String readInfraction(String uuid){
        String  Query= "Select infractions from `chatfilter`.`chatfilter` where `UUID` ="+uuid;
        return  Query;
    }
    public String addInfraction(String uuid,String infraction){
        String  Query= "UPDATE `chatfilter`.`chatfilter` SET `Muted` = true WHERE `UUID` = "+uuid;
        return  Query;
    }

    public String mutePlayer(String uuid){
        String  Query= "UPDATE `chatfilter`.`chatfilter` SET `Muted` = true WHERE `UUID` = "+uuid;
        return  Query;
    }
    public String unmutePlayer(String uuid){
        String  Query= "UPDATE `chatfilter`.`chatfilter` SET `Muted` = false WHERE `UUID` = "+uuid;
        return  Query;
    }
    public String checkPlayer(String uuid){

        String  Query= "Select * from chatfilter where uuid ="+uuid;
        return  Query;
    }
    private String checkIfExists(String uuid){
        String  Query= "Select * from chatfilter where uuid ="+uuid;
        return Query;
    }
}
// Step Guide for database querys
/*
Mute Player
1. Checks if user exists
2. If user exists updates mute & infactions (This needs to be read and added on to)/ If not create new record
3. Loads database

Flag player
1. Checks if user exists
2. If user exists update flag & infractions
3. Loads database

Check Player
1. Checks if user exists
2. Gets player infractions



 */