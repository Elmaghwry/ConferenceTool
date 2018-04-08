package org.scci_cu.conferencetool.Models;

public class UserModel 
{
    String username,password,Type;

    public UserModel(String userName, String password, String type) {
        username = userName;
        password = password;
        Type = type;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        password = password;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
