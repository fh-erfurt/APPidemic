package javarumdennnicht.user;

//import LocalDate
import java.time.LocalDate;
//import ArrayList class
import java.util.ArrayList;



public class UserList
{
    // ===================== //
    // ===== VARIABLES ===== //
    // ===================== //

    private final ArrayList<User> userList;



    // ======================= //
    // ===== CONSTRUCTOR ===== //
    // ======================= //

    public UserList()
    {
        this.userList= new ArrayList<>();
    }



    // =============================== //
    // ===== USER-LIST FUNCTIONS ===== //
    // =============================== //

    //this method adds an user to the user list while creating the new user
    public void addUser(String username, String password, String eMail, String firstName, String lastName, LocalDate birthdate)
    {
        if (!checkUsernameExistence(username) && !checkEMailExistence(eMail))
        {
            this.getUserList().add(new User(username, password, eMail, firstName, lastName, birthdate));
        }
        else System.out.println("The username or eMail is already taken!");
    }


    // this method removes an existing user from the user list
    public void removeUser(String username)
    {
        if (this.getUserList().size() > 0)
        {
            if (checkUsernameExistence(username))
            {
                for (User u: this.getUserList())
                {
                    if (u.getUsername().equals(username))
                    {
                        this.getUserList().remove(u);
                        break;
                    }
                }
            }
        }
    }


    // this method checks if an username is already existing in the user list. It returns true if it does and false if it does not
    public boolean checkUsernameExistence(String username)
    {
        if (this.getUserList().size() > 0)
        {
            for (User u: this.getUserList())
            {
                if (u.getUsername().equals(username))
                {
                    return true;
                }
            }
        }
        return false;
    }


    // this method checks if an email address is already existing in the user list. It returns true if it does and false if it does not
    public boolean checkEMailExistence(String eMail)
    {
        if (this.getUserList().size() > 0)
        {
            for (User u: this.getUserList())
            {
                if (u.getEMail().equals(eMail))
                {
                    return true;
                }
            }
        }
        return false;
    }



    // =========================== //
    // ===== GETTER & SETTER ===== //
    // =========================== //

    public ArrayList<User> getUserList()
    {
        return this.userList;
    }
}
