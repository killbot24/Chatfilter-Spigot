package kineticnetwork.net.chatfilter.DataBase;

public class Statements {
    public void mutePlayer(String uuid){


    }
    public String unmutePlayer(){
        return "";
    }
    public String checkPlayer(){
        return "";
    }
    private boolean checkIfExists(String uuid){
        String a= "Select * from chatfilter where uuid ="+uuid;
        return false;
    }
}
