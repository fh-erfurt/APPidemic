package javarumdennnicht;

import java.time.LocalDate;
import java.util.ArrayList;

public class UserList {


    private ArrayList<User> userList;

    //Constructor
    public UserList(){
        this.userList= new ArrayList<>();
    }

    // Getter
    public ArrayList<User> getUserList() {
        return userList;
    }

    //
    // other methods
    //

    //this method adds an user to the user list while creating the new user
    public void addUser(String username, String password, String eMail, String firstName, String lastName, LocalDate birthdate){
        if (!checkUsernameExistance(username) && !checkEMailExistance(eMail)){
        userList.add(new User(username, password, eMail, firstName, lastName, birthdate));}
        else System.out.println("The username or eMail is already taken!");
    }

    // this method removes an existing user from the user list
    public void removeUser(String username){
        if(this.userList.size()>0){
            if (checkUsernameExistance(username)){
                for (User u: this.userList){
                    if (u.getUsername()==username){
                        userList.remove(u);
                        break;
                    }
                }
            }
        }
    }

    // this method checks if an username is already existing in the user list. It returns true if it does and false if it does not
    public boolean checkUsernameExistance(String username){
        if(this.userList.size()>0){

            for (User u: this.userList){
                if (u.getUsername()==username){
                    return true;}
            }
        }
        return false;
    }

    // this method checks if an email address is already existing in the user list. It returns true if it does and false if it does not
    public boolean checkEMailExistance(String eMail){
        if (this.userList.size() > 0) {
            for (User u: this.userList){
                if( u.getMail()==eMail){
                    return true;
                }
            }
        }
        return false;
    }
}
