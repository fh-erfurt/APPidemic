package javarumdennnicht;

import java.time.LocalDate;
import java.util.ArrayList;

public class UserList {


    private ArrayList<User> userList;

    public UserList(){
        this.userList= new ArrayList<>();
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void addUser(String username, String password, String eMail, String firstName, String lastName, LocalDate birthdate){
        if (!checkUsernameExistance(username) && !checkEMailExistance(eMail)){
        userList.add(new User(username, password, eMail, firstName, lastName, birthdate));}
        else System.out.println("The username or eMail is already taken!");
    }

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

    public boolean checkUsernameExistance(String username){
        if(this.userList.size()>0){

            for (User u: this.userList){
                if (u.getUsername()==username){
                    return true;}
            }
        }
        return false;
    }

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
