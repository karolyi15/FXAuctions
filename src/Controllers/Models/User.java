package Controllers.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.json.simple.JSONObject;

public class User {

    private StringProperty name;
    private StringProperty username;
    private StringProperty email;
    private Countries country;
    private AccountType accountType;

    //Constructor
    public User(){

        this.name = new SimpleStringProperty("");
        this.username = new SimpleStringProperty("");
        this.email = new SimpleStringProperty("");
        this.country = Countries.DEFAULT;
        this.accountType = AccountType.NORMAL;
    }

    public User(String name, String username, String email ,String country, AccountType type){

        this.name = new SimpleStringProperty(name);
        this.username = new SimpleStringProperty(username);
        this.email = new SimpleStringProperty(email);
        this.country = Countries.valueOf(country);
        this.accountType = type;
    }

    public User(JSONObject userData){

        this.name = new SimpleStringProperty((String) userData.get("Name"));
        this.username = new SimpleStringProperty((String) userData.get("Username"));
        this.email = new SimpleStringProperty((String) userData.get("Email"));
        this.country = Countries.valueOf((String) userData.get("Country"));
        this.accountType = AccountType.valueOf((String) userData.get("AccountType"));
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
        return this.username.getValue();
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

    public Countries getCountry() {
        return country;
    }

    public void setCountry(Countries country) {
        this.country = country;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public JSONObject toJson(){

        JSONObject user = new JSONObject();

        user.put("Name",this.name.getValue());
        user.put("Username", this.username.getValue());
        user.put("Email", this.email.getValue());
        user.put("Country", this.country.toString());
        user.put("AccountType", this.accountType.toString());

        return user;
    }
}
