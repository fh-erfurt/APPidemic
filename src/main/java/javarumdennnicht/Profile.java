package javarumdennnicht;


//import date-formatter
import java.time.format.DateTimeFormatter;


public class Profile
{
    enum PrivacySetting { PUBLIC, PRIVATE }

    private       String         biography;
    private       User           relatedUser;
    private       Post[]         relatedPosts;    //!!! array list or constant !!!
    private final ProfileList    followerList;
    private final ProfileList    followingList;
    private       PrivacySetting privacySetting;



    // ======================= //
    // ===== Constructor ===== //
    // ======================= //
    public Profile(User relatedUser)
    {
        this.relatedUser    = relatedUser;
        this.biography      = "";
        this.relatedPosts   = null;
        this.followerList   = new ProfileList();
        this.followingList  = new ProfileList();
        this.privacySetting = PrivacySetting.PRIVATE;
    }



    // =============================== //
    // ===== Functions / Methods ===== //
    // =============================== //

    //LocalDate is in format yyyy-mm-dd
    //this function formats it into dd-mm-yyyy format
    public String getFormattedBirthdate()
    {
        //define the pattern for the date
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return relatedUser.getBirthdate().format(dateFormat);
    }


    public void follow(Profile followedProfile)
    {
        if (profileIsNotAlreadyInFollowingList(followedProfile))
        {
            //add your current profile to the FollowerList of the profile you want to follow
            addOwnProfileToFollowerListOfFollowedProfile(followedProfile);
            //add the profile you are following to your own FollowingList
            addFollowedProfileToOwnFollowingList(followedProfile);
        }
        else
        {
            System.out.println("You're already following this profile!");                               //??? Something else ???
        }
    }


    public void unfollow(Profile unfollowedProfile)                                                     //!!! add check if you're not following the given profile !!!
    {
        //remove your current profile from the FollowerList of the profile you are unfollowing
        removeOwnProfileFromFollowedListOfUnfollowedProfile(unfollowedProfile);
        //remove the profile you are unfollowing from your own FollowingList
        removeUnfollowedProfileFromOwnFollowingList(unfollowedProfile);
    }

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
    private void setRelatedUser(User relatedUser)                                                   //??? private or public ???
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



    // ============================= //
    // ===== Extracted Methods ===== //
    // ============================= //

    private boolean profileIsNotAlreadyInFollowingList(Profile followedProfile)
    {
        return !this.followingList.isProfileAlreadyInList(followedProfile);
    }


    private void addOwnProfileToFollowerListOfFollowedProfile(Profile followedProfile)
    {
        followedProfile.setFollower(this.relatedUser.getRelatedProfile());
    }


    private void addFollowedProfileToOwnFollowingList(Profile followedProfile)
    {
        this.relatedUser.getRelatedProfile().setFollowingList(followedProfile);
    }


    private void removeOwnProfileFromFollowedListOfUnfollowedProfile(Profile unfollowedProfile)
    {
        unfollowedProfile.getFollowerList().removeProfile(this.getRelatedUser().getRelatedProfile());       // ??? better solution | function currentProfile() ???
    }


    private void removeUnfollowedProfileFromOwnFollowingList(Profile unfollowedProfile)
    {
        this.getRelatedUser().getRelatedProfile().getFollowingList().removeProfile(unfollowedProfile);
    }
}
