/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zooprogram;

/**
 *
 * @author Mark
 */
public class User {
    String hashedPass = new String();
    String userName = new String();
    String userType = new String();
    
    public User(){
    }
    
    public User(String userName, String hashedPass,String userType){
        this.userName = userName;
        this.hashedPass = hashedPass;
        this.userType = userType;
    }
    
   
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHashedPass() {
        return hashedPass;
    }

    public void setHashedPass(String hashedPass) {
        this.hashedPass = hashedPass;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    
}
