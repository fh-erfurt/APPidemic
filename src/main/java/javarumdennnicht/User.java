package javarumdennnicht;

import java.time.LocalDate;

public class User {
    private String username;
    private String password;
    private String eMail;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private Profile relatedProfile;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Profile getRelatedProfile() {
        return relatedProfile;
    }

    public void setRelatedProfile(Profile relatedProfile) {
        this.relatedProfile = relatedProfile;
    }


}
