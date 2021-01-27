package javarumdennnicht;

import org.junit.Test;

import java.time.LocalDate;
import java.util.spi.LocaleServiceProvider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class testPost{

    @Test
    public void formatted_meetingDate_should_be_in_dd_mm_yyyy_format() {
        // Given
        Post post = new Post();
        post.setMeetingDate(LocalDate.of(2010, 9, 20));

        // When
        String formattedMeetingDate = post.getFormattedMeetingDate();

        // Then
        assertEquals("MeetingDate should be converted from yyyy-mm-dd format to dd-mm-yyyy.", "20-09-2010", formattedMeetingDate);
    }


    @Test
    public void setting_and_getting_attributes_in_the_Post_should_work_just_fine() {
        // Given
        Post post = new Post();
        post.setImageDescription("Hund, an Leine, in Park");
        post.setDescription("Hundespaziergang im Park");
        post.setMeetingPlace("Berliner Park");
        post.setMeetingDate(LocalDate.of(2018, 6, 16));

        // When
        String imageDescription = post.getImageDescription();
        String description = post.getDescription();
        String meetingPlace = post.getMeetingPlace();
        String meetingDate = post.getFormattedMeetingDate();

        // Then
        assertEquals("Getting the image description in the Post should deliver: ", "Hund, an Leine, in Park", imageDescription);
        assertEquals("Getting the description of the Post should deliver: ", "Hundespaziergang im Park", description);
        assertEquals("Getting the meeting place of the Post should deliver: ", "Berliner Park", meetingPlace);
        assertEquals("Getting the meeting date of the Post should deliver: ", "16-06-2018", meetingDate);
    }


    @Test
    public void tagging_a_profile_should_add_that_profile_to_the_taggedProfiles_ArrayList() {
        // Given
        // When
        // Then
    }
}
