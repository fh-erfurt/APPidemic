package javarumdennnicht;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;



public class testUserList {


    @Test
    public void userlist_can_add_a_new_user(){

        // Given

        UserList allUser = new UserList();

        // When

        allUser.addUser("havoc17", "1234qwe", "havoc@gmail.com", "Hannes", "Volker", LocalDate.of(2004,12,23));

        // Then

        assertEquals("The user hasn't been added to the list", 1, allUser.getUserList().size());
    }

    @Test
    public void userlist_can_remove_existing_user_by_username(){

        // Given

        UserList allUser = new UserList();
        allUser.addUser("havoc17", "1234qwe", "havoc@gmail.com", "Hannes", "Volker", LocalDate.of(2004,12,23));

        // When

        allUser.removeUser("havoc17");

        // Then

        assertEquals("The user hasn't been removed",0,allUser.getUserList().size());

    }

    @Test
    public void trying_to_add_an_existing_username_should_not_work(){

        // Given

        UserList allUser = new UserList();
        allUser.addUser("havoc17", "1234qwe", "havoc@gmail.com", "Hannes", "Volker", LocalDate.of(2004,12,23));

        // When

        allUser.addUser("havoc17", "miaukatze234", "Simba@gmail.com", "John", "Wany", LocalDate.of(1997,12,21));

        // Then

        assertEquals("They added the new user",1,allUser.getUserList().size());

    }

    @Test
    public void trying_to_add_an_existing_eMail_should_not_work(){

        // Given

        UserList allUser = new UserList();
        allUser.addUser("havoc17", "1234qwe", "havoc@gmail.com", "Hannes", "Volker", LocalDate.of(2004,12,23));

        // When

        allUser.addUser("havoc115", "miaukatze234", "havoc@gmail.com", "John", "Wany", LocalDate.of(1997,12,21));

        // Then

        assertEquals("They added the new user",1,allUser.getUserList().size());
    }
}
