package javarumdennnicht;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Profile
{
    private String          username;
    private String          firstName;
    private String          lastName;
    private String          biography;
    private LocalDate       birthdate;
    private User            relatedUser;
    private Post[]          relatedPosts;
    private FollowerList[]  followers;
    private FollowingList[] following;
    private boolean         privacySetting;

    //Constructor
    public Profile()
    {

    }


    public String getFormattedBirthdate()
    {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return birthdate.format(dateFormat);
    }

    public void follow(){}

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


    public boolean isPrivacySetting()
    {
        return privacySetting;
    }
    public void setPrivacySetting(boolean privacySetting)
    {
        this.privacySetting = privacySetting;
    }
}
