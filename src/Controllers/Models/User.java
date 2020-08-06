package Controllers.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {

    private StringProperty name;
    private StringProperty username;
    private StringProperty country;
    private AccountType accountType;

    //Constructor
    public User(){

        this.name = new SimpleStringProperty("");
        this.username = new SimpleStringProperty("");
        this.country = new SimpleStringProperty("");
        this.accountType = AccountType.NORMAL;
    }

    public User(String name, String username, String country, AccountType type){

        this.name = new SimpleStringProperty(name);
        this.username = new SimpleStringProperty(username);
        this.country = new SimpleStringProperty(country);
        this.accountType = type;
    }

    //Setters & Getters


    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}
