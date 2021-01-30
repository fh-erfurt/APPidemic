package javarumdennnicht.post;


import javarumdennnicht.profile.Profile;
import javarumdennnicht.user.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;

public class testComment
{
    User user1 = new User("hansmueller", "12345", "hansmueller@web.de", "Hans", "Müller", LocalDate.of(2000,1,10));


    // tests the constructor and the getter
    @Test
    public void creating_a_comment_and_getting_its_contents_should_deliver_the_given_attributes()
    {
        // Given
        Profile profile1 = user1.getRelatedProfile();

        // When
        Comment comment = new Comment(profile1,"Das ist ein sehr weltkritisches Kommentar, was Menschen zum Denken anregt.");

        // Then
        assertEquals("If you created a comment, the getText method should deliver the passed Commenttext: ", "Das ist ein sehr weltkritisches Kommentar, was Menschen zum Denken anregt.", comment.getText());
        assertEquals("If you created a comment, the getCommenter method should deliver the passed Commenter: ", profile1, comment.getCommenter());

    }
    // tests the setter
    @Test
    public void setting_new_parameters_for_a_comment_and_getting_its_contents_should_deliver_the_new_attributes()
    {
        // Given
        Profile profile1 = user1.getRelatedProfile();

        // When
        Comment comment = new Comment(profile1,"Das ist ein sehr weltkritisches Kommentar, was Menschen zum Denken anregt.");
        comment.setText("Ist doch nicht so poetisch, wie der Kommentator dachte, deswegen wurde es wieder geaendert.");

        // Then
        assertEquals("If you created a comment, the getText method should deliver the passed Commenttext: ", "Ist doch nicht so poetisch, wie der Kommentator dachte, deswegen wurde es wieder geaendert.", comment.getText());
    }
}
