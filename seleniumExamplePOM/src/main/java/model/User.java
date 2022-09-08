package model;

public class User {
    String userName;
    String passWord;

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }


    public String getUserName() {
        return userName;
    }



    public String getPassWord() {
        return passWord;
    }


}
