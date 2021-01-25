package javarumdennnicht;

import java.time.LocalDate;
import java.util.ArrayList;

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
    public void following_a_profile_should_add_your_profile_to_their_follower_list_and_their_profile_to_your_following_list()
    {
        // Given
        User user1 = new User();                                                  //??? change when constructor ???
        user1.setFirstName("Hans");
        user1.setLastName("Müller");
        user1.setUsername("hansmueller");
        User user2 = new User();                                                  //??? change when constructor ???
        user2.setFirstName("Hans");
        user2.setLastName("Müller");
        user2.setUsername("hansmueller");

        Profile profile1 = new Profile(user1);
        Profile profile2 = new Profile(user2);
        user1.setRelatedProfile(profile1);
        user2.setRelatedProfile(profile2);

        // When
        profile1.follow(profile2);

        ArrayList<Profile> followersProfile2 = profile2.getFollowerList().getProfiles();
        ArrayList<Profile> followingProfile1 = profile1.getFollowingList().getProfiles();

        // Then
        assertEquals("When you follow a profile your profile should be in their FollowerList.",  profile1, followersProfile2.get(0));
        assertEquals("When you follow a profile their profile should be in your FollowingList.", profile2, followingProfile1.get(0));
    }

}