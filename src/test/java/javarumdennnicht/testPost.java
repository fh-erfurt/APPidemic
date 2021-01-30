package javarumdennnicht;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class testPost
{

   private final User user1 = new User("hansmueller", "12345", "hansmueller@web.de", "Hans", "Müller", LocalDate.of(2000,1,10));
   private final User user2 = new User("tomvogt", "98765", "tomvogt@web.de", "Tom", "Vogt", LocalDate.of(1987,3,4));
   private final User user3 = new User("kalterdieter", "45454", "kalterdieter@web.de", "Dieter", "Kalt", LocalDate.of(2001,6,13));

    // tests the formatter of the date, because dd_mm_yyyy is just way better
    @Test
    public void formatted_meetingDate_should_be_in_dd_mm_yyyy_format()
    {
        // Given
        Post post = new Post(user1.getRelatedProfile(), "Hund, an Leine, in Park", "Hundespaziergang im Park", "Berliner Park", 2010, 9, 20);
        // When
        String formattedMeetingDate = post.getFormattedMeetingDate();

        // Then
        assertEquals("MeetingDate should be converted from yyyy-mm-dd format to dd-mm-yyyy.", "20-09-2010", formattedMeetingDate);
    }

    // tests setter and getter
    @Test
    public void setting_and_getting_attributes_in_the_Post_should_work_just_fine()
    {
        // Given
        Post post = new Post(user1.getRelatedProfile(), "Hund, an Leine, in Park", "Hundespaziergang im Park", "Berliner Park", 2018, 6, 16);

        // When
        String imageDescription = post.getImageDescription();
        String description = post.getPostDescription();
        String meetingPlace = post.getMeetingPlace();
        String meetingDate = post.getFormattedMeetingDate();

        // Then
        assertEquals("Getting the image description in the Post should deliver: ", "Hund, an Leine, in Park", imageDescription);
        assertEquals("Getting the description of the Post should deliver: ", "Hundespaziergang im Park", description);
        assertEquals("Getting the meeting place of the Post should deliver: ", "Berliner Park", meetingPlace);
        assertEquals("Getting the meeting date of the Post should deliver: ", "16-06-2018", meetingDate);
    }
    // tests the addTaggedProfile function
    @Test
    public void tagging_profiles_should_add_them_to_the_taggedProfiles_ArrayList()
    {
        // Given
        Profile profile1 = user1.getRelatedProfile();
        Profile profile2 = user2.getRelatedProfile();
        Profile profile3 = user3.getRelatedProfile();
        Post post = new Post(profile1, "3 Menschen, in Bar, trinken Alkohol", "Schön in kleiner runde gehoben ;-)","Arche Noah, Erfurt", 2018, 8, 6);

        // When
        post.addTaggedProfile(profile2);
        post.addTaggedProfile(profile3);
        // Then
        assertEquals("Looking at the size of the taggedProfiles Array should deliver one entry: ", 2, post.getTaggedProfiles().size());
    }
    // tests if the removeTaggedProfile works
    @Test
    public void tagging_a_profile_and_then_untagging_it_should_result_in_an_empty_ArrayList_in_taggedProfiles()
    {
        // Given
        Profile profile1 = user1.getRelatedProfile();
        Profile profile2 = user2.getRelatedProfile();
        Post post = new Post(profile1, "3 Menschen, in Bar, trinken Alkohol", "Schön in kleiner runde gehoben ;-)","Arche Noah, Erfurt", 2018, 8, 6);

        // When
        post.addTaggedProfile(profile2);
        post.removeTaggedProfile(profile2);
        // Then
        assertEquals("Looking at the size of the taggedProfiles Array should deliver an empty Array: ", 0, post.getTaggedProfiles().size());
    }

     // tests if the viewPost function puts out the right attributes at the right place
    @Test
    public void viewing_a_post_should_deliver_a_rough_view_of_what_it_contains()
    {
        //preparation for System.out.println-check
        //compares what is printed out from "System.out.println" with an expected string
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        //newLine contains the line-separator that "println()" creates after a line is printed
        String newLine = System.getProperty("line.separator");


        // Given
        Profile profile1 = user1.getRelatedProfile();
        Post post = new Post(profile1, "3 Menschen, in Bar, trinken Alkohol", "Schön in kleiner runde gehoben ;-)","Arche Noah, Erfurt", 2018, 8, 6);

        // When
        post.viewPost(post);

        // Then
        assertEquals("Following and non-following profiles should have access to posts of public profiles.",
                "hansmueller" + newLine +
                        "------------------------------------" + newLine +
                        "3 Menschen, in Bar, trinken Alkohol" + newLine +
                        "------------------------------------" + newLine +
                        "Schön in kleiner runde gehoben ;-)" + newLine +
                        "------------------------------------" + newLine +
                        "Arche Noah, Erfurt" + newLine +
                        "------------------------------------" + newLine +
                        "06-08-2018" + newLine, outContent.toString());
    }

    // tests if not submitting a post really prevents the post from appearing in the respective ArrayLists
    @Test
    public void befor_submittig_a_post_it_will_not_appear_in_the_Posts_and_taggedPosts_ArrayLists_respectively()
    {
        // Given
        Profile profile1 = user1.getRelatedProfile();
        Profile profile2 = user2.getRelatedProfile();
        Post post = new Post(profile1, "3 Menschen, in Bar, trinken Alkohol", "Schön in kleiner runde gehoben ;-)","Arche Noah, Erfurt", 2018, 8, 6);

        //When
        post.addTaggedProfile(profile2);

        //Then
        assertEquals("The post should not yet be in the posts ArrayList for the author(profile1), since it wasnt submitted yet: ", 0,profile1.getPosts().size());
        assertEquals("The post should not yet be in the taggedPosts ArrayList for the tagged profile, since it wasnt submitted yet: ", 0,profile2.getTaggedPosts().size());
    }
    // tests if the submitPost works and puts the post in the respective ArrayLists
    @Test
    public void after_submitting_a_post_it_should_appear_in_the_ArrayLists_Posts_for_the_author_and_taggedPosts_for_the_taggedProfiles()
    {
        // Given
        Profile profile1 = user1.getRelatedProfile();
        Profile profile2 = user2.getRelatedProfile();
        Profile profile3 = user3.getRelatedProfile();
        Post post = new Post(profile1, "3 Menschen, in Bar, trinken Alkohol", "Schön in kleiner runde gehoben ;-)","Arche Noah, Erfurt", 2018, 8, 6);

        //When
        post.addTaggedProfile(profile2);
        post.addTaggedProfile(profile3);
        post.submitPost();

        // Then
        assertEquals("The post should now be in the posts ArrayList for the author(profile1), since it has been submitted: ", 1,profile1.getPosts().size());
        assertEquals("The post should now be in the taggedPosts ArrayList for the first tagged profile, since it has been submitted: ", 1,profile2.getTaggedPosts().size());
        assertEquals("The post should now be in the taggedPosts ArrayList for the second tagged profile, since it has been submitted: ", 1,profile3.getTaggedPosts().size());
    }
    // tests if the addLike function adds the profile to the likes and puts out the right like number
    @Test
    public void liking_a_post_should_add_the_liking_profile_to_the_likedBy_ArrayList_and_raise_the_likeAmount()
    {
        // Given
        Profile profile1 = user1.getRelatedProfile();
        Profile profile2 = user2.getRelatedProfile();
        Profile profile3 = user3.getRelatedProfile();
        Post post = new Post(profile1, "3 Menschen, in Bar, trinken Alkohol", "Schön in kleiner runde gehoben ;-)","Arche Noah, Erfurt", 2018, 8, 6);

        // When
        post.submitPost();
        post.addLike(profile2);
        post.addLike(profile3);

        // Then
        assertEquals("The like variable, which is the size of the likedByArray, should show a 2 for the two profiles in the likedByArray: ", 2, post.getLikes());
    }

    // tests if the removeLike function works as intended (removing the unliking profile from the ArrayList and lowering the like amount)
    @Test
    public void liking_a_post_and_then_unliking_it_again_should_remove_the_unliking_profile_from_the_likedBy_ArrayList_and_lower_the_likes_number_again()
    {
        // Given
        Profile profile1 = user1.getRelatedProfile();
        Profile profile2 = user2.getRelatedProfile();
        Profile profile3 = user3.getRelatedProfile();
        Post post = new Post(profile1, "3 Menschen, in Bar, trinken Alkohol", "Schön in kleiner runde gehoben ;-)","Arche Noah, Erfurt", 2018, 8, 6);

        // When
        post.submitPost();
        post.addLike(profile2);
        post.addLike(profile3);
        post.removeLike(profile3);

        // Then
        assertEquals("The like variable, which is the size of the likedByArray, should show a 1, because there is only one profile left liking the post: ", 1, post.getLikes());
    }

    // tests if the addLike prevents you from liking multiple times with the same profile (like pressing the like button again after liking removes the like, like on youtube)
    @Test
    public void trying_to_like_a_post_more_than_once_should_remove_the_like_by_the_profile()
    {
        // Given
        Profile profile1 = user1.getRelatedProfile();
        Profile profile2 = user2.getRelatedProfile();
        Profile profile3 = user3.getRelatedProfile();
        Post post = new Post(profile1, "3 Menschen, in Bar, trinken Alkohol", "Schön in kleiner runde gehoben ;-)","Arche Noah, Erfurt", 2018, 8, 6);

        // When
        post.submitPost();
        // profile2 likes the post
        post.addLike(profile2);
        // profile2 does it again and it should remove the like
        post.addLike(profile2);
        // profile2 does it again and it should add the like again
        post.addLike(profile2);
        // profile3 adds its like
        post.addLike(profile3);
        // profile3 adds it again, which should remove the like
        post.addLike(profile3);

        // Then
        assertEquals("The like variable, which is the size of the likedByArray, should show a 1 for the one profile that is left in the likedByArray: ", 1, post.getLikes());
    }

    // tests if the addComment function enables commenting under posts
    @Test
    public void creating_a_comment_will_add_the_comment_with_its_given_attributes_to_the_comments_ArrayList_of_the_post()
    {
        // Given
        Profile profile1 = user1.getRelatedProfile();
        Profile profile2 = user2.getRelatedProfile();
        Post post = new Post(user1.getRelatedProfile(), "Hund, an Leine, in Park", "Hundespaziergang im Park", "Berliner Park", 2018, 6, 16);

        // When
        post.addComment(profile1,"Ist mein Hundi nicht toll?!");
        post.addComment(profile2,"Haha wie sßüß loL!");

        // Then
        assertEquals("The added comment1 should be in the comments ArrayList with the given commentedText: ", "Ist mein Hundi nicht toll?!", post.getComments().get(0).getText());
        assertEquals("The added comment2 should be in the comments ArrayList with the given commentedText: ", "Haha wie sßüß loL!", post.getComments().get(1).getText());
    }

}
