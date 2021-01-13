package javarumdennnicht;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Profile
{
    private String        username;
    private String        firstName;
    private String        lastName;
    private String        biography;
    private LocalDate     birthdate;
    private User          relatedUser;
    private Post[]        relatedPosts;
    private FollowerList  followers;
    private FollowingList following;
    private boolean       privacySetting;   //???rename to "Show personal information"???


    //Constructor
    public Profile(String username, String firstName, String lastName, LocalDate birthdate, User relatedUser)
    {
        this.username       = username;
        this.firstName      = firstName;   //???warum hier nochmal? reicht das nicht in User???
        this.lastName       = lastName;
        this.birthdate      = birthdate;
        this.relatedUser    = relatedUser;
        this.biography      = "";
        this.relatedPosts   = null;       //???nötig???           //empty array: new array[] {}
        this.followers      = new FollowerList(0);   //???Array nötig???    ???new nötig???    ???ist das die richtige Syntax???
        this.following      = new FollowingList(0); //???setter und getter eigentlich sinnlos???
        this.privacySetting = false;
    }


    public String getFormattedBirthdate()
    {
        //LocalDate is in format yyyy-mm-dd, this function formats it into dd-mm-yyyy format
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return birthdate.format(dateFormat);
    }

    public void follow(){}      //???wie unfollow/wo - auf eigenem Profil oder auf Profil des anderen? weil Zahl vom ungefollowtem Account muss auch -1???

    public void newPost(){}

    public void createAlarm(){}


    //Getter & Setter
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }


    public String getFirstName()
    {
        return firstName;
    }
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }


    public String getLastName()
    {
        return lastName;
    }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }


    public String getBiography()
    {
        return biography;
    }
    public void setBiography(String biography)
    {
        this.biography = biography;
    }


    public LocalDate getBirthdate()
    {
        return birthdate;
    }
    public void setBirthdate(LocalDate birthdate)
    {
        this.birthdate = birthdate;
    }


    public User getRelatedUser()
    {
        return relatedUser;
    }
    public void setRelatedUser(User relatedUser)
    {
        this.relatedUser = relatedUser;
    }


    public Post[] getRelatedPosts()
    {
        return relatedPosts;
    }
    public void setRelatedPosts(Post[] relatedPosts)
    {
        this.relatedPosts = relatedPosts;
    }


    public FollowerList getFollowers()
    {
        return followers;
    }
    public void setFollowers(FollowerList followers)
    {
        this.followers = followers;
    }


    public FollowingList getFollowing()
    {
        return following;
    }
    public void setFollowing(FollowingList following)
    {
        this.following = following;
    }


    public boolean isPrivacySetting()
    {
        return privacySetting;
    }
    public void setPrivacySetting(boolean privacySetting)
    {
        this.privacySetting = privacySetting;
    }
}
