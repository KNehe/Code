import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
//this class acts as a modal class holding the getters,setters,and properties
public class modalTable {

    private final StringProperty firstName;
    private final StringProperty secondName;
    private final StringProperty userName;
    private final StringProperty passwords;
    private final StringProperty roles;
    private final StringProperty genders;
    private final StringProperty emails;

    //default constructor

    public modalTable(String firstName, String secondName, String userName, String passwords, String roles, String genders, String emails) {
        this.firstName =new SimpleStringProperty(firstName);
        this.secondName = new SimpleStringProperty(secondName);
        this.userName = new SimpleStringProperty(userName);
        this.passwords = new SimpleStringProperty(passwords);
        this.roles = new SimpleStringProperty(roles);
        this.genders =new SimpleStringProperty(genders);
        this.emails = new SimpleStringProperty(emails);
    }
    //Getters and setters

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getSecondName() {
        return secondName.get();
    }

    public StringProperty secondNameProperty() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName.set(secondName);
    }

    public String getUserName() {
        return userName.get();
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public String getPasswords() {
        return passwords.get();
    }

    public StringProperty passwordsProperty() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords.set(passwords);
    }

    public String getRoles() {
        return roles.get();
    }

    public StringProperty rolesProperty() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles.set(roles);
    }

    public String getGenders() {
        return genders.get();
    }

    public StringProperty gendersProperty() {
        return genders;
    }

    public void setGenders(String genders) {
        this.genders.set(genders);
    }

    public String getEmails() {
        return emails.get();
    }

    public StringProperty emailsProperty() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails.set(emails);
    }
}