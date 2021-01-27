package javarumdennnicht;

import java.time.LocalDate;


public class User {
    private String username;
    private String password;
    private String eMail;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private final Profile relatedProfile;

    // Constructor

    public User(String username, String password, String eMail, String firstName, String lastName, LocalDate birthdate){
        this.username = username;
        this.password = password;
        this.eMail = eMail;
        this.firstName = firstName;
        this.lastName = lastName;
        this. birthdate = birthdate;
        this.relatedProfile = new Profile(this);
    }

    // Setter and Getter

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

    public String getMail() {
        return eMail;
    }

    public void setMail(String eMail) {
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

    // Other Functions

    public void changePassword(String password1, String password2){

        if(password1==this.password){
            System.out.println("This is already the set password");}

        else if (password1==password2) {
            this.setPassword(password1);
            System.out.println("The password has been successfully changed");
        }

        else {
            System.out.println("The first and the seconds entrance are different");
        }

    }

    public void changeUsername(UserList userList, String username){

        if(!userList.checkUsernameExistance(username)){
            this.username=username;
        }

        else System.out.println("The username already exists");
    }
}
