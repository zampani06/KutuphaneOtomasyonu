package kütüphaneotomasyonu.data;

public class Admins {

    private int adminNumber;
    private String name;
    private String surname;
    private String userName;
    private String userPassword;

    public Admins(int adminNumber, String name, String surname, String userName, String userPassword) {
        this.adminNumber=adminNumber;
        this.setName(name);
        this.setSurname(surname);
        this.setUserName(userName);
        this.setUserPassword(userPassword);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getAdminNumber() {
        return adminNumber;
    }

    public void setAdminNumber(int adminNumber) {
        this.adminNumber = adminNumber;
    }

}
