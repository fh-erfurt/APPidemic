package javarumdennnicht;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class testPost
{

    User user1 = new User("hansmueller", "12345", "hansmueller@web.de", "Hans", "Müller", LocalDate.of(2000,1,10));
    User user2 = new User("tomvogt", "98765", "tomvogt@web.de", "Tom", "Vogt", LocalDate.of(1987,3,4));
    User user3 = new User("kalterdieter", "45454", "kalterdieter@web.de", "Dieter", "Kalt", LocalDate.of(2001,6,13));

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

     //Hier wollte ich einen output testen, hab aber keine möglichkeit gefunden ein Systemoutprintln abzugleichen
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
                        "------------------------------------"+ newLine +
                        "06-08-2018" + newLine, outContent.toString());
    }

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
