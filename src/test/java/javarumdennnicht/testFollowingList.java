package javarumdennnicht;

import org.junit.Test;
import static org.junit.Assert.*;


public class testFollowingList
{
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
        FollowingList followingList = new FollowingList();

        // When
        followingList.addProfile(profile1);
        followingList.addProfile(profile2);

        // Then
        assertEquals("FollowingNumber should increase by 1 after a profile is added to it.", 2, followingList.getFollowingNumber());
    }
}
