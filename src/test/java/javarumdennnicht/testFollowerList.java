package javarumdennnicht;

import org.junit.Test;
import static org.junit.Assert.*;


public class testFollowerList
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
        user2.setFirstName("Hans");
        user2.setLastName("Müller");
        user2.setUsername("hansmueller");

        Profile      profile1     = new Profile(user1);
        Profile      profile2     = new Profile(user2);
        FollowerList followerList = new FollowerList();

        // When
        followerList.addProfile(profile1);
        followerList.addProfile(profile2);

        // Then
        assertEquals("FollowerNumber should increase by 1 after a profile is added to it.", 2, followerList.getFollowerNumber());
    }
}
