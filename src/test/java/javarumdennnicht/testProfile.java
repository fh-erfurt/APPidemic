package javarumdennnicht;


import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;

//import for println()-assertEquals-check
//Source:   https://coderanch.com/t/587280/java/assertEquals-println
//          https://www.techiedelight.com/print-newline-java/
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;



public class testProfile
{
    //users used for all the tests, to prevent duplicated code they are declared here so every test can use them
    private final User user1 = new User("hansmueller",  "12345", "hansmueller@web.de",  "Hans",   "Müller", LocalDate.of(2000,1,10));
    private final User user2 = new User("tomvogt",      "98765", "tomvogt@web.de",      "Tom",    "Vogt",   LocalDate.of(1987,3, 4));
    private final User user3 = new User("kalterdieter", "45454", "kalterdieter@web.de", "Dieter", "Kalt",   LocalDate.of(2001,6,13));



    // ======================================== //
    // ===== TEST getFormattedBirthdate() ===== //
    // ======================================== //
    @Test
    public void formatted_birthdate_should_be_in_dd_mm_yyyy_format()
    {
        // GIVEN
        Profile profile1 = user1.getRelatedProfile();


        // WHEN
        String formattedBirthDate = profile1.getFormattedBirthdate();


        // THEN
        assertEquals("Birthdate should be converted from yyyy-mm-dd format to dd-mm-yyyy.", "10-01-2000", formattedBirthDate);
    }



    // ============================ //
    // ===== TEST relatedUser ===== //
    // ============================ //
    @Test
    public void profile_can_access_user_data()
    {
        // GIVEN
        Profile profile1 = user1.getRelatedProfile();


        // WHEN
        String firstName = profile1.getRelatedUser().getFirstName();
        String lastName  = profile1.getRelatedUser().getLastName();
        String username  = profile1.getRelatedUser().getUsername();


        // THEN
        assertEquals("Profile should be able to access the FirstName-attribute of his related user.", "Hans",        firstName);
        assertEquals("Profile should be able to access the LastName-attribute of his related user.",  "Müller",      lastName);
        assertEquals("Profile should be able to access the username-attribute of his related user.",  "hansmueller", username);
    }



    // ========================= //
    // ===== TEST follow() ===== //
    // ========================= //
    @Test
    public void following_a_profile_should_add_your_profile_to_their_follower_list_and_their_profile_to_your_following_list()
    {
        // GIVEN
        Profile profile1 = user1.getRelatedProfile();
        Profile profile2 = user2.getRelatedProfile();


        // WHEN
        profile1.follow(profile2);


        // THEN
        assertEquals("When you follow a profile your profile should be in their FollowerList.",  profile1, profile2.getFollowerList().getProfile(0));
        assertEquals("When you follow a profile their profile should be in your FollowingList.", profile2, profile1.getFollowingList().getProfile(0));
    }



    @Test
    public void you_should_not_be_able_to_follow_a_profile_twice()
    {
        // GIVEN
        Profile profile1 = user1.getRelatedProfile();
        Profile profile2 = user2.getRelatedProfile();


        // WHEN
        profile1.follow(profile2);
        profile1.follow(profile2);
        profile1.follow(profile2);


        // THEN
        assertEquals("You shouldn't be able to follow a profile twice.", 1, profile2.getFollowerList().getNumberOfProfiles());
    }



    // =========================== //
    // ===== TEST unfollow() ===== //
    // =========================== //
    @Test
    public void unfollow_a_profile_should_remove_your_profile_from_their_follower_list_and_their_profile_from_your_following_list()
    {
        // GIVEN
        Profile profile1 = user1.getRelatedProfile();
        Profile profile2 = user2.getRelatedProfile();
        Profile profile3 = user3.getRelatedProfile();

        profile1.follow(profile3);
        profile2.follow(profile3);


        // WHEN
        profile1.unfollow(profile3);
        profile1.unfollow(profile3);    //trying to unfollow a profile you aren't following


        // THEN
        assertEquals("When you unfollow a profile your profile should be removed from their FollowerList.", profile2, profile3.getFollowerList().getProfile(0));
        assertEquals("When you unfollow a profile their FollowerCount should decrease by 1.", 1, profile3.getFollowerList().getNumberOfProfiles());

        assertNull(  "When you unfollow a profile their profile should be removed from your FollowingList.", profile1.getFollowingList().getProfile(0));
        assertEquals("When you unfollow a profile their FollowerCount should decrease by 1.", 0, profile1.getFollowerList().getNumberOfProfiles());
    }



    // ================================== //
    // ===== TEST Privacy-Functions ===== //
    // ================================== //
    @Test
    public void privacy_status_of_personal_information_can_be_all_changed_at_once()
    {
        // GIVEN
        Profile profile = user1.getRelatedProfile();

        // WHEN
        profile.changePrivacyStatusOfPersonalInformation(Profile.PrivacySetting.PRIVATE, Profile.PrivacySetting.PUBLIC, Profile.PrivacySetting.PUBLIC, Profile.PrivacySetting.PRIVATE);

        // THEN
        assertEquals("You should be able to change the privacy of a personal attribute.", Profile.PrivacySetting.PRIVATE, profile.getPrivacyStatusOfPersonalInformation().get("firstname"));
        assertEquals("You should be able to change the privacy of a personal attribute.", Profile.PrivacySetting.PUBLIC,  profile.getPrivacyStatusOfPersonalInformation().get("lastname"));
        assertEquals("You should be able to change the privacy of a personal attribute.", Profile.PrivacySetting.PUBLIC,  profile.getPrivacyStatusOfPersonalInformation().get("birthdate"));
        assertEquals("You should be able to change the privacy of a personal attribute.", Profile.PrivacySetting.PRIVATE, profile.getPrivacyStatusOfPersonalInformation().get("email"));
    }



    // ============================ //
    // ===== TEST showPosts() ===== //
    // ============================ //
    @Test
    public void accessing_posts_of_a_public_profile_should_always_display_their_posts()
    {
        //preparation for System.out.println-check
        //compares what is printed out from "System.out.println" with an expected string
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        //newLine contains the line-separator that "println()" creates after a line is printed
        String newLine = System.getProperty("line.separator");


        // GIVEN
        Profile profileFollowing    = user1.getRelatedProfile();
        Profile profileNotFollowing = user2.getRelatedProfile();
        Profile profilePublic       = user3.getRelatedProfile();

        //set profilePublic's privacySetting on PUBLIC because initially it is PRIVATE
        profilePublic.setPrivacySetting(Profile.PrivacySetting.PUBLIC);

        //let profileFollowing follow profilePublic so we can make sure followers and non-followers can see the posts
        profileFollowing.follow(profilePublic);

        //make a post for profilePublic that all profiles should see
        profilePublic.newPost("Bild ProfilePublic", "PublicProfile", "Moon", 2000, 1, 1);


        // WHEN
        profilePublic.displayPosts(profileFollowing);
        profilePublic.displayPosts(profileNotFollowing);


        // THEN
        assertEquals("Following and non-following profiles should have access to posts of public profiles.",
                     "Bild: Bild ProfilePublic" + newLine + "Bild: Bild ProfilePublic" + newLine,
                     outContent.toString());


        //Reset System.out to print in console again:
        //System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }



    @Test
    public void accessing_posts_of_a_private_profile_should_only_display_their_posts_if_you_are_following_their_profile()
    {
        //preparation for System.out.println-check
        //compares what is printed out from "System.out.println" with an expected string
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        //newLine contains the line-separator that "println()" creates after a line is printed
        String newLine = System.getProperty("line.separator");


        // GIVEN
        Profile profileFollowing    = user1.getRelatedProfile();
        Profile profileNotFollowing = user2.getRelatedProfile();
        Profile profilePrivate      = user3.getRelatedProfile();

        //let profileFollowing follow profilePrivate so we can make sure only followers can see the posts
        profileFollowing.follow(profilePrivate);

        //make a post for profilePrivate that only profileFollowing should see
        profilePrivate.newPost("Bild ProfilePrivate", "PrivateProfile", "Earth", 1990, 12, 12);


        // WHEN
        profilePrivate.displayPosts(profileFollowing);
        profilePrivate.displayPosts(profileNotFollowing);


        // THEN
        assertEquals("Only following profiles should have access to posts of private profiles.",
                     "Bild: Bild ProfilePrivate" + newLine + "You cannot view the posts of this profile." + newLine,
                     outContent.toString());
    }
}