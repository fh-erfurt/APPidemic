package javarumdennnicht;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class testUser {
    // for preventing duplicate Code in the Given sections
    User user= new User("Havoc17","Katze123", "havoc17@gmail.com","Hannes", "Volker", LocalDate.of(2001,12,24));
    User user2= new User("ElricE", "Alfons456", "ElricEdward@gmx.com", "Edward", "Elric", LocalDate.of(1998,02,12));
    UserList allUser = new UserList();


    @Test
    public void changing_a_password_successful(){


        // Given



        // When

        user.changePassword("Katze123","Hund123", "Hund123");

        // Then

        assertEquals("The Password should had been changed","Hund123",user.getPassword());
    }

    @Test
    public  void changing_a_password_but_the_old_password_is_wrong(){

        // Given



        // When

        user.changePassword("Fuchs123", "Hund123", "Hund123");

        // Then

        assertEquals("The Password should not be changed","Katze123",user.getPassword());
    }

    @Test
    public void changing_a_password_but_the_new_password_inputs_are_diffrent(){

        // Given



        //When

        user.changePassword("Katze123", "Hund123", "Hund321");

        // Then

        assertEquals("The password should not be changed", "Katze123",user.getPassword());

    }

    @Test
    public void changing_username_successful(){

        // Given

        allUser.getUserList().add(user);
        allUser.getUserList().add(user2);

        // When

        user.changeUsername(allUser, "Covah321");

        // Then

        assertEquals("The password should been changed", "Covah321", user.getUsername());

    }

    @Test
    public void changing_username_but_username_is_taken(){

        // Given

        allUser.getUserList().add(user);
        allUser.getUserList().add(user2);

        // When

        user.changeUsername(allUser, "ElricE");

        // Then

        assertEquals("The username should not be changed", "Havoc17", user.getUsername());
    }

    @Test
    public void changing_email_successful(){

        // Given

        allUser.getUserList().add(user);
        allUser.getUserList().add(user2);

        // When

        user.changeEMailAdress(allUser, "email@gmail.com");

        // Then

        assertEquals("The email should have been changed", "email@gmail.com", user.getMail());
    }

    @Test
    public void changing_email_but_email_is_already_taken(){

        // Given

        allUser.getUserList().add(user);
        allUser.getUserList().add(user2);

        // When

        user.changeEMailAdress(allUser, "ElricEdward@gmx.com");

        // Then

        assertEquals("The email should not be changed", "havoc17@gmail.com", user.getMail());
    }
}
