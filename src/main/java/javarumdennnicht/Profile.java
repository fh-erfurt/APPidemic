package javarumdennnicht;


//import ArrayList class
import java.util.ArrayList;
//import date-formatter
import java.time.format.DateTimeFormatter;


public class Profile
{
    enum PrivacySetting { PUBLIC, PRIVATE }

    private       String          biography;
    private final User            relatedUser;
    private       ArrayList<Post> posts;
    private       ArrayList<Post> taggedPosts;
    private final ProfileList     followerList;
    private final ProfileList     followingList;
    private       PrivacySetting  privacySetting;



    // ======================= //
    // ===== Constructor ===== //
    // ======================= //
    public Profile(User relatedUser)
    {
        this.relatedUser    = relatedUser;
        this.biography      = "";
        this.followerList   = new ProfileList();
        this.followingList  = new ProfileList();
        this.posts = new ArrayList<>();
        this.taggedPosts = new ArrayList<>();
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


    public void addPost(Post post)
    {
        this.posts.add(post);
    }
    public void removePost(Post post)
    {
        this.posts.remove(post);
    }
    public void getPost(int index)
    {
        this.posts.get(index);
    }


    public void addTaggedPost(Post taggedPost)
    {
        this.taggedPosts.add(taggedPost);
    }


    public void createAlarm(){}

    //function changePrivacySettings

                        //??? function displayPersonalInformation ???



    // =========================== //
    // ===== Getter & Setter ===== //
    // =========================== //

    public String getBiography()
    {
        return this.biography;
    }
    public void setBiography(String biography)
    {
        this.biography = biography;
    }


    public User getRelatedUser()
    {
        return relatedUser;
    }


    public ArrayList<Post> getRelatedPosts()
    {
        return this.posts;
    }


    public ProfileList getFollowerList()
    {
        return this.followerList;
    }
    public void setFollower(Profile follower)
    {
        this.followerList.addProfile(follower);
    }


    public ProfileList getFollowingList()
    {
        return this.followingList;
    }
    public void setFollowingList(Profile followedProfile)
    {
        this.followingList.addProfile(followedProfile);
    }


    public PrivacySetting getPrivacySetting()
    {
        return this.privacySetting;
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
        followedProfile.setFollower(this);
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
