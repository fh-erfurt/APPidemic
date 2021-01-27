package javarumdennnicht;


import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;



public class testProfileList
{
    //users used for all the tests, to prevent duplicated code they are declared here so every test can use them
    User user1 = new User("hansmueller",  "12345", "hansmueller@web.de",  "Hans",   "Müller", LocalDate.of(2000,1,10));
    User user2 = new User("tomvogt",      "98765", "tomvogt@web.de",      "Tom",    "Vogt",   LocalDate.of(1987,3, 4));
    User user3 = new User("kalterdieter", "45454", "kalterdieter@web.de", "Dieter", "Kalt",   LocalDate.of(2001,6,13));

    @Test
    public void get_profile_should_return_null_if_the_index_is_out_of_bounds_or_the_list_is_empty()
    {
        // Given
        Profile     profile1           = user1.getRelatedProfile();
        Profile     profile2           = user2.getRelatedProfile();
        ProfileList filledFollowerList = new ProfileList();
        ProfileList emptyFollowerList  = new ProfileList();

        // When
        filledFollowerList.addProfile(profile1);
        filledFollowerList.addProfile(profile2);

        // Then
        assertNull("Accessing an index higher than the ArrayList-size should return 'null'.", filledFollowerList.getProfile(5));
        assertNull("Accessing an empty ArrayList should return 'null'.",                      emptyFollowerList.getProfile(3));
        assertNull("Accessing an index lower than 0 should return 'null'.",                   filledFollowerList.getProfile(-1));
    }



    @Test
    public void follower_number_should_increase_by_1_when_a_profile_follows()
    {
        // Given
        Profile     profile1     = user1.getRelatedProfile();
        Profile     profile2     = user2.getRelatedProfile();
        ProfileList followerList = new ProfileList();

        // When
        followerList.addProfile(profile1);
        followerList.addProfile(profile2);

        // Then
        assertEquals("FollowerNumber should increase by 1 after a profile is added to it.", 2, followerList.getNumberOfProfiles());
    }


    @Test
    public void following_number_should_increase_by_1_when_you_follow_a_profile()
    {
        // Given
        Profile     profile1      = user1.getRelatedProfile();
        Profile     profile2      = user2.getRelatedProfile();
        ProfileList followingList = new ProfileList();

        // When
        followingList.addProfile(profile1);
        followingList.addProfile(profile2);

        // Then
        assertEquals("FollowingNumber should increase by 1 after a profile is added to it.", 2, followingList.getNumberOfProfiles());
    }



    @Test
    public void removing_a_profile_should_leave_no_empty_entries_in_the_array_list()
    {
        // Given
        Profile     profile1     = user1.getRelatedProfile();
        Profile     profile2     = user2.getRelatedProfile();
        Profile     profile3     = user3.getRelatedProfile();
        ProfileList followerList = new ProfileList();

        followerList.addProfile(profile1);
        followerList.addProfile(profile2);
        followerList.addProfile(profile3);

        // When
        followerList.removeProfile(profile2);

        // Then
        assertEquals("Removing a profile should delete all empty entries.", profile1, followerList.getProfile(0));
        assertEquals("Removing a profile should delete all empty entries.", profile3, followerList.getProfile(1));
    }


    @Test
    public void removing_a_profile_should_decrease_the_profile_number_by_1()
    {
        // Given
        Profile     profile1     = user1.getRelatedProfile();
        Profile     profile2     = user2.getRelatedProfile();
        Profile     profile3     = user3.getRelatedProfile();
        ProfileList followerList = new ProfileList();

        followerList.addProfile(profile1);
        followerList.addProfile(profile2);
        followerList.addProfile(profile3);

        // When
        followerList.removeProfile(profile1);
        followerList.removeProfile(profile3);

        // Then
        assertEquals("Removing a profile should decrease the profile-number by 1.", 1, followerList.getNumberOfProfiles());
    }
}
