package javarumdennnicht;

import java.time.LocalDate;
import java.util.ArrayList;

public class UserList {
    private ArrayList<User> userList;

    public void addUser(String username, String password, String eMail, String firstName, String lastName, LocalDate birthdate){
        if (!checkUsernameExistance(username)){
        userList.add(new User(username, password, eMail, firstName, lastName, birthdate));}
        else System.out.println("The username is already taken!");
    }

    public boolean checkUsernameExistance(String username){
        for (User u: this.userList){
            if (u.getUsername()==username){
                return true;}
        }
        return false;
    }
}
