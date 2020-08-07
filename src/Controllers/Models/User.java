package Controllers.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.json.simple.JSONObject;

public class User {

    private StringProperty name;
    private StringProperty username;
    private StringProperty email;
    private StringProperty country;
    private AccountType accountType;

    //Constructor
    public User(){

        this.name = new SimpleStringProperty("");
        this.username = new SimpleStringProperty("");
        this.email = new SimpleStringProperty("");
        this.country = new SimpleStringProperty("");
        this.accountType = AccountType.NORMAL;
    }

    public User(String name, String username, String email ,String country, AccountType type){

        this.name = new SimpleStringProperty(name);
        this.username = new SimpleStringProperty(username);
        this.email = new SimpleStringProperty(email);
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

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
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

    public JSONObject toJson(){

        JSONObject user = new JSONObject();

        user.put("Name",this.name);
        user.put("Username", this.username);
        user.put("Email", this.email);
        user.put("Country", this.country);
        user.put("AccountType", this.accountType.toString());

        return user;
    }
}
