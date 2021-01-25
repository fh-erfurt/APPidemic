package javarumdennnicht;

import java.time.LocalDate;

import org.junit.Test;
import static org.junit.Assert.*;


public class testProfile
{
    @Test
    public void formatted_birthdate_should_be_in_dd_mm_yyyy_format()
    {
        // Given
        User user = new User();                                                  //??? change when constructor ???
        user.setBirthdate(LocalDate.of(2000, 1, 10));
        Profile profile = new Profile(user);

        // When
        String formattedBirthDate = profile.getFormattedBirthdate();

        // Then
        assertEquals("Birthdate should be converted from yyyy-mm-dd format to dd-mm-yyyy.", "10-01-2000", formattedBirthDate);
    }


    @Test
    public void profile_can_access_user_data()
    {
        // Given
        User user = new User();                                                  //??? change when constructor ???
        user.setFirstName("Hans");
        user.setLastName("Müller");
        user.setUsername("hansmueller");

        Profile profile = new Profile(user);

        // When
        String firstName = profile.getRelatedUser().getFirstName();
        String lastName  = profile.getRelatedUser().getLastName();
        String username  = profile.getRelatedUser().getUsername();

        // Then
        assertEquals("Profile should be able to access the FirstName-attribute of his related user.", "Hans",        firstName);
        assertEquals("Profile should be able to access the LastName-attribute of his related user.",  "Müller",      lastName);
        assertEquals("Profile should be able to access the username-attribute of his related user.",  "hansmueller", username);
    }


    @Test
    public void f()
    {
        // Given

        // When

        // Then
        assertEquals("message", "expected", "actual");
    }

}