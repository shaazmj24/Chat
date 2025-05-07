package model;

public class User { 
    private String name;  
    private boolean online; 


    // EFEFCT: construct user with its name and set online to false 
    public User(String n) {   
        this.name = n;   
        online = false; 
    }

    public boolean isOnline() { 
        return online; 
    }

    public void setOnline(boolean o) { 
        online = o; 
    }

    public String getName() { 
        return name; 
    }
 
    public void setName(String n) { 
        name = n; 
    }


}
