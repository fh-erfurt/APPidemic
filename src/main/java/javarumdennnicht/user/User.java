package javarumdennnicht.user;


//import own classes
import javarumdennnicht.profile.Profile;

//import LocalDate
import java.time.LocalDate;



//This class contains all information about a User. Such as the username or firstname and lastname
public class User
{
    // ===================== //
    // ===== VARIABLES ===== //
    // ===================== //

    private       String    username;
    private       String    password;
    private       String    eMail;
    private       String    firstName;
    private       String    lastName;
    private       LocalDate birthdate;
    private final Profile   relatedProfile;



    // ======================= //
    // ===== CONSTRUCTOR ===== //
    // ======================= //

    public User(String username, String password, String eMail, String firstName, String lastName, LocalDate birthdate)
    {
        this.username       = username;
        this.password       = password;
        this.eMail          = eMail;
        this.firstName      = firstName;
        this.lastName       = lastName;
        this.birthdate      = birthdate;
        this.relatedProfile = new Profile(this);
    }



    // ========================== //
    // ===== USER FUNCTIONS ===== //
    // ========================== //

    // this method changes the password of the user by first checking of having the password and then setting the new password by typing it in two times
    public void changePassword(String oldPassword, String password1, String password2)
    {
        if (oldPassword.equals(this.getPassword()))
        {
            if (password1.equals(this.getPassword()))
            {
                System.out.println("This is already the set password");
            }
            else if (password1.equals(password2))
            {
                this.setPassword(password1);
                System.out.println("The password has been successfully changed");
            }
            else
            {
                System.out.println("The first and the seconds entrance are different");
            }
        }
        else System.out.println("The old password is incorrect");
    }


    // this method changes the username of a profile by first checking if it already exists in the user list
    public void changeUsername(UserList userList, String username)
    {
        if(!userList.checkUsernameExistence(username))
        {
            this.setUsername(username);
        }
        else System.out.println("The username already exists");
    }


    // this method changes the email address of a profile by first checking if it already exists in the user list
    public void changeEMailAddress(UserList userList, String eMail){

        if(!userList.checkEMailExistence(eMail))
        {
            this.setEMail(eMail);
        }
        else System.out.println("The eMail is already taken");
    }



    // =========================== //
    // ===== GETTER & SETTER ===== //
    // =========================== //

    public String getUsername()
    {
        return this.username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }


    public String getPassword()
    {
        return this.password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }


    public String getEMail()
    {
        return this.eMail;
    }
    public void setEMail(String eMail)
    {
        this.eMail = eMail;
    }


    public String getFirstName()
    {
        return this.firstName;
    }
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }


    public String getLastName()
    {
        return this.lastName;
    }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }


    public LocalDate getBirthdate()
    {
        return this.birthdate;
    }
    public void setBirthdate(LocalDate birthdate)
    {
        this.birthdate = birthdate;
    }


    public Profile getRelatedProfile()
    {
        return this.relatedProfile;
    }
}
