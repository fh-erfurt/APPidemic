package javarumdennnicht;

public class Post
{
    private Profile[] taggedProfiles;
    private Profile[] likedByProfiles;
    // private picture; --> Bilder sind kein Datentyp? Void ist bei Variablen illegal :(
    private String description;
    private String meetingPlace;
    private LocalDate meetingDate;
    private int likes = 0;
    private Comment[] comments;


    // addPicture blabla

    // das ist eins zu eins ne setter methode?
    /*public void addDescription(String description)
    {
        this.description = description;
    }
    */
    // die anderen auch lol

    public void addLike(Profile liker)
    {
        // ArrayLists nochmal anschauen
    }



    // Setter und Getter???
    public Profile[] getTaggedProfiles()
    {
        return taggedProfiles;
    }

    public void setTaggedProfiles(Profile[] taggedProfiles)
    {
        this.taggedProfiles = taggedProfiles;
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

    public String getMeetingDate()
    {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate)
    {
        this.meetingDate = meetingDate;
    }

    public int getLikes()
    {
        return likes;
    }

    public void setLikes(int likes)
    {
        this.likes = likes;
    }

    public Comment[] getComments()
    {
        return comments;
    }

    public void setComments(Comment[] comments)
    {
        this.comments = comments;
    }
}
