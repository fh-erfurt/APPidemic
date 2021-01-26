package javarumdennnicht;


//import date-formatter
import java.time.format.DateTimeFormatter;


public class Profile
{
    enum PrivacySetting { PUBLIC, PRIVATE }

    private String         biography;
    private User           relatedUser;
    private Post[]         relatedPosts;    //!!! array list or constant !!!
    private ProfileList    followerList;
    private ProfileList    followingList;
    private PrivacySetting privacySetting;


    //Constructor
    public Profile(User relatedUser)
    {
        this.relatedUser    = relatedUser;
        this.biography      = "";
        this.relatedPosts   = null;
        this.followerList   = new ProfileList();
        this.followingList  = new ProfileList();
        this.privacySetting = PrivacySetting.PRIVATE;
    }


    //LocalDate is in format yyyy-mm-dd, this function formats it into dd-mm-yyyy format
    public String getFormattedBirthdate()
    {
        //Define the pattern for the date
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return relatedUser.getBirthdate().format(dateFormat);
    }


    public void follow(Profile followedProfile) //profile1(profile2)
    {
        if (profileIsNotAlreadyInFollowingList(followedProfile))
        {
            addOwnProfileToFollowedListOfFollowedProfile(followedProfile);     //add your current profile to the FollowingList of the profile you want to follow
            addFollowedProfileToOwnFollowingList(followedProfile);             //add the profile you're following to your own FollowingList
        }
        else
        {
            System.out.println("You're already following this profile!");
        }
    }


    public void unfollow() {}

    public void newPost(){}

    public void createAlarm(){}

    //function changePrivacySettings

                        //??? function displayPersonalInformation ???

    // =========================== //
    // ===== Getter & Setter ===== //
    // =========================== //
    public String getBiography()
    {
        return biography;
    }
    public void setBiography(String biography)
    {
        this.biography = biography;
    }


    public User getRelatedUser()
    {
        return relatedUser;
    }
    private void setRelatedUser(User relatedUser)       //??? private or public ???
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


    public ProfileList getFollowerList()
    {
        return followerList;
    }
    public void setFollower(Profile follower)
    {
        this.followerList.addProfile(follower);
    }


    public ProfileList getFollowingList()
    {
        return followingList;
    }
    public void setFollowingList(Profile followedProfile)
    {
        this.followingList.addProfile(followedProfile);
    }


    public PrivacySetting getPrivacySetting()
    {
        return privacySetting;
    }
    public void setPrivacySetting(PrivacySetting privacySetting)
    {
        this.privacySetting = privacySetting;
    }


    // ============================== //
    // ===== Refactored methods ===== //
    // ============================== //
    private boolean profileIsNotAlreadyInFollowingList(Profile followedProfile)
    {
        return !this.followingList.isProfileAlreadyInList(followedProfile);
    }

    private void addOwnProfileToFollowedListOfFollowedProfile(Profile followedProfile)
    {
        followedProfile.setFollower(this.relatedUser.getRelatedProfile());
    }

    private void addFollowedProfileToOwnFollowingList(Profile followedProfile)
    {
        this.relatedUser.getRelatedProfile().setFollowingList(followedProfile);
    }
}
