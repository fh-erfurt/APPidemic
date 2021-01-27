package javarumdennnicht;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Post
{
    //vielleicht noch nen private Profile author ?
    private ArrayList<Profile> taggedProfiles;
    private ArrayList<Profile> likedByProfiles;
    private int likes;
    private String imageDescription; // Da wir keine Bilder einfügen können tun wir so als wären das welche
    private String description;
    private String meetingPlace;
    private LocalDate meetingDate;
    private ArrayList<Comment> comments;




    // Constructor
    public Post()
    {
        this.taggedProfiles = new ArrayList<>();
        this.likedByProfiles = new ArrayList<>();
        this.likes = likedByProfiles.size();
        this.imageDescription = "";
        this.description = "";
        this.meetingPlace = "";
        this.meetingDate = LocalDate.of(1111,11,11);
        this.comments = new ArrayList<>();
    }


    // Von einem Profil geliket/ entliket werden
    public void addLike(Profile liker)
    {
        likedByProfiles.add(liker);
        likes = likedByProfiles.size();
    }
    public void removeLike(Profile removedLiker)
    {
        likedByProfiles.remove(removedLiker);
        likes = likedByProfiles.size();
    }

    // Ein Profil (eines Freundes oä) taggen oder enttaggen
    public void addTaggedProfile(Profile taggedProfile)
    {
        likedByProfiles.add(taggedProfile);
    }
    public void removeTaggedProfile(Profile taggedProfile)
    {
        likedByProfiles.remove(taggedProfile);
    }

    // Ein Kommentar schreiben oder entfernen
    public void addComment(Profile Commenter, String commentedText)
    {
        Comment newComment = new Comment(Commenter, commentedText);
        comments.add(newComment);
    }
    public void removeComment(Comment removedComment)
    {
        comments.remove(removedComment);
    }



    // Setter und Getter???
    public ArrayList<Profile> getTaggedProfiles()
    {
        return taggedProfiles;
    }
    public void setTaggedProfiles(ArrayList<Profile> newTaggedProfiles)
    {
        this.taggedProfiles = newTaggedProfiles;
    }

    public String getImageDescription()
    {
        return imageDescription;
    }
    public void setImageDescription(String imageDescription)
    {
        this.imageDescription = imageDescription;
    }

    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }


    public String getMeetingPlace() {
        return meetingPlace;
    }
    public void setMeetingPlace(String meetingPlace)
    {
        this.meetingPlace = meetingPlace;
    }

    public String getFormattedMeetingDate()
    {
        //define the pattern for the date
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return getMeetingDate().format(dateFormat);
    }
    public LocalDate getMeetingDate()
    {
        return meetingDate;
    }
    public void setMeetingDate(LocalDate meetingDate)
    {
        this.meetingDate = meetingDate;
    }

    public int getLikes()
    {
        return likedByProfiles.size();
    }
    // kein set likes, da das ja nicht so leicht beinflusst werden kann, ohne die ganze ArrayList zu ändern


    public ArrayList<Comment> getComments()
    {
        return comments;
    }
    public void setComments(ArrayList<Comment> comments)
    {
        this.comments = comments;
    }
}
