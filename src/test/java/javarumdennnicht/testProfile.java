package javarumdennnicht;


import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;



public class testProfile
{
    //users used for all the tests, to prevent duplicated code they are declared here so every test can use them
    User user1 = new User("hansmueller",  "12345", "hansmueller@web.de",  "Hans",   "Müller", LocalDate.of(2000,1,10));
    User user2 = new User("tomvogt",      "98765", "tomvogt@web.de",      "Tom",    "Vogt",   LocalDate.of(1987,3, 4));
    User user3 = new User("kalterdieter", "45454", "kalterdieter@web.de", "Dieter", "Kalt",   LocalDate.of(2001,6,13));


    @Test
    public void formatted_birthdate_should_be_in_dd_mm_yyyy_format()
    {
        // Given
        Profile profile1 = user1.getRelatedProfile();

        // When
        String formattedBirthDate = profile1.getFormattedBirthdate();

        // Then
        assertEquals("Birthdate should be converted from yyyy-mm-dd format to dd-mm-yyyy.", "10-01-2000", formattedBirthDate);
    }


    @Test
    public void profile_can_access_user_data()
    {
        // Given
        Profile profile1 = user1.getRelatedProfile();

        // When
        String firstName = profile1.getRelatedUser().getFirstName();
        String lastName  = profile1.getRelatedUser().getLastName();
        String username  = profile1.getRelatedUser().getUsername();

        // Then
        assertEquals("Profile should be able to access the FirstName-attribute of his related user.", "Hans",        firstName);
        assertEquals("Profile should be able to access the LastName-attribute of his related user.",  "Müller",      lastName);
        assertEquals("Profile should be able to access the username-attribute of his related user.",  "hansmueller", username);
    }


    @Test
    public void following_a_profile_should_add_your_profile_to_their_follower_list_and_their_profile_to_your_following_list()
    {
        // Given
        Profile profile1 = user1.getRelatedProfile();
        Profile profile2 = user2.getRelatedProfile();

        // When
        profile1.follow(profile2);

        // Then
        assertEquals("When you follow a profile your profile should be in their FollowerList.",  profile1, profile2.getFollowerList().getProfile(0));
        assertEquals("When you follow a profile their profile should be in your FollowingList.", profile2, profile1.getFollowingList().getProfile(0));
    }



    @Test
    public void you_should_not_be_able_to_follow_a_profile_twice()
    {
        // Given
        Profile profile1 = user1.getRelatedProfile();
        Profile profile2 = user2.getRelatedProfile();

        // When
        profile1.follow(profile2);
        profile1.follow(profile2);
        profile1.follow(profile2);

        // Then
        assertEquals("You shouldn't be able to follow a profile twice.", 1, profile2.getFollowerList().getNumberOfProfiles());
    }



    @Test
    public void unfollow_a_profile_should_remove_your_profile_from_their_follower_list_and_their_profile_from_your_following_list()
    {
        // Given
        Profile profile1 = user1.getRelatedProfile();
        Profile profile2 = user2.getRelatedProfile();
        Profile profile3 = user3.getRelatedProfile();

        profile1.follow(profile3);
        profile2.follow(profile3);

        // When
        profile1.unfollow(profile3);

        // Then
        assertEquals("When you unfollow a profile your profile should be removed from their FollowerList.", profile2, profile3.getFollowerList().getProfile(0));
        assertEquals("When you unfollow a profile their FollowerCount should decrease by 1.", 1, profile3.getFollowerList().getNumberOfProfiles());

        assertNull("When you unfollow a profile their profile should be removed from your FollowingList.", profile1.getFollowingList().getProfile(0));
        assertEquals("When you unfollow a profile their FollowerCount should decrease by 1.", 0, profile1.getFollowerList().getNumberOfProfiles());
    }
}