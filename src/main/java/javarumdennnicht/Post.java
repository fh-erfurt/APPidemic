package javarumdennnicht;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Post
{
    //vielleicht noch nen private Profile author ?
    private Profile author;
    private ArrayList<Profile> taggedProfiles;
    private ArrayList<Profile> likedByProfiles;
    private int likes;
    private String imageDescription; // Da wir keine Bilder einfügen können tun wir so als wären das welche
    private String postDescription;
    private String meetingPlace;
    private LocalDate meetingDate;
    private ArrayList<Comment> comments;




    // Constructor
    public Post(Profile author,String imageDescription, String postDescription, String meetingPlace, int meetingYear, int meetingMonth, int meetingDay)
    {
        this.author = author;
        this.taggedProfiles = new ArrayList<>();
        this.likedByProfiles = new ArrayList<>();
        this.likes = 0;
        this.imageDescription = imageDescription;
        this.postDescription = postDescription;
        this.meetingPlace = meetingPlace;
        this.meetingDate = LocalDate.of(meetingYear,meetingMonth,meetingDay);
        this.comments = new ArrayList<>();
    }

    public void submitPost(Profile author)
    {
       author.addPost(this);

        for (Profile taggedProfile : this.taggedProfiles) {
            taggedProfile.addTaggedPost(this);
        }
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
        taggedProfiles.add(taggedProfile);
    }
    public void removeTaggedProfile(Profile untaggedProfile)
    {
        taggedProfiles.remove(untaggedProfile);
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

    // Methode um den Inhalt eines Posts leicht zu überprüfen
    public void viewPost(Post post)
    {
        System.out.println( this.getAuthor().getRelatedUser().getUsername()+
                            "\n------------------------------------\n"+
                            post.getImageDescription()+
                            "\n------------------------------------\n"+
                            post.getPostDescription()+
                            "\n------------------------------------\n"+
                            post.getMeetingPlace()+
                            "\n------------------------------------\n"+
                            post.getFormattedMeetingDate());
    }



    // Setter und Getter
    public ArrayList<Profile> getTaggedProfiles()
    {
        return taggedProfiles;
    }
    public void setTaggedProfiles(ArrayList<Profile> newTaggedProfiles)
    {
        this.taggedProfiles = newTaggedProfiles;
    }

    public Profile getAuthor()
    {
        return author;
    }
    public void setAuthor(Profile author)
    {
        this.author = author;
    }

    public String getImageDescription()
    {
        return imageDescription;
    }
    public void setImageDescription(String imageDescription)
    {
        this.imageDescription = imageDescription;
    }

    public String getPostDescription()
    {
        return postDescription;
    }
    public void setPostDescription(String postDescription)
    {
        this.postDescription = postDescription;
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


