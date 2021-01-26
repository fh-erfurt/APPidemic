package javarumdennnicht;


import org.junit.Test;
import static org.junit.Assert.*;



public class testProfileList
{
    @Test
    public void follower_number_should_increase_by_1_when_a_profile_follows()
    {
        // Given
        User user1 = new User();                                                  //??? change when constructor ???
        user1.setFirstName("Hans");
        user1.setLastName("Müller");
        user1.setUsername("hansmueller");
        User user2 = new User();                                                  //??? change when constructor ???
        user2.setFirstName("Tom");
        user2.setLastName("Vogt");
        user2.setUsername("tomvogt");

        Profile     profile1     = new Profile(user1);
        Profile     profile2     = new Profile(user2);
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
        User user1 = new User();                                                  //??? change when constructor ???
        user1.setFirstName("Hans");
        user1.setLastName("Müller");
        user1.setUsername("hansmueller");
        User user2 = new User();                                                  //??? change when constructor ???
        user2.setFirstName("Hans");
        user2.setLastName("Müller");
        user2.setUsername("hansmueller");

        Profile      profile1       = new Profile(user1);
        Profile      profile2       = new Profile(user2);
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
        User user1 = new User();                                                  //??? change when constructor ???
        user1.setFirstName("Hans");
        user1.setLastName("Müller");
        user1.setUsername("hansmueller");
        User user2 = new User();                                                  //??? change when constructor ???
        user2.setFirstName("Tom");
        user2.setLastName("Vogt");
        user2.setUsername("tomvogt");
        User user3 = new User();                                                  //??? change when constructor ???
        user3.setFirstName("Dieter");
        user3.setLastName("Kalt");
        user3.setUsername("kalterdieter");

        Profile     profile1     = new Profile(user1);
        Profile     profile2     = new Profile(user2);
        Profile     profile3     = new Profile(user3);
        ProfileList followerList = new ProfileList();

        followerList.addProfile(profile1);
        followerList.addProfile(profile2);
        followerList.addProfile(profile3);

        // When
        followerList.removeProfile(profile2);

        // Then
        assertEquals("Removing a profile should delete all empty entries.", profile1, followerList.getProfiles().get(0));
        assertEquals("Removing a profile should delete all empty entries.", profile3, followerList.getProfiles().get(1));
    }
}
