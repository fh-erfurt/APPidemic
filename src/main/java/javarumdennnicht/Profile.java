package javarumdennnicht;


//import ArrayList class
import java.util.ArrayList;
//import date-formatter
import java.time.format.DateTimeFormatter;


final class Profile
{
    enum PrivacySetting { PUBLIC, PRIVATE }                                     //??? auslagern ???

    private       String          biography;
    private final User            relatedUser;
    private final ArrayList<Post> posts;
    private final ArrayList<Post> taggedPosts;
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
        this.posts          = new ArrayList<>();
        this.taggedPosts    = new ArrayList<>();
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
        return getRelatedUser().getBirthdate().format(dateFormat);
    }


    public void follow(Profile followedProfile)
    {
        //only enable to follow a profile if you aren't already following it
        if (profileIsNotAlreadyInFollowingList(followedProfile))
        {
            //add your current profile to the FollowerList of the profile you want to follow
            addOwnProfileToFollowerListOfFollowedProfile(followedProfile);
            //add the profile you are following to your own FollowingList
            addFollowedProfileToOwnFollowingList(followedProfile);
        }
        else
        {
            //replacement for "Follow"-button displaying "Unfollow"
            System.out.println("You are already following this profile!");
        }
    }


    public void unfollow(Profile unfollowedProfile)                                                     //!!! add check if you're not following the given profile !!!
    {
        if (profileIsFollowed(unfollowedProfile))
        {
            //remove your current profile from the FollowerList of the profile you are unfollowing
            removeOwnProfileFromFollowedListOfUnfollowedProfile(unfollowedProfile);
            //remove the profile you are unfollowing from your own FollowingList
            removeUnfollowedProfileFromOwnFollowingList(unfollowedProfile);
        }
        else
        {
            System.out.println("A profile that you are not following cannot be unfollowed!");
        }
    }


    public void createAlarm()
    {

    }


    //this method either displays or hides your posts from a profile that wants to access your posts
    //if your PrivacySetting is on PUBLIC everyone can see your posts
    //if your PrivacySetting is on PRIVATE only people that follow you can see your posts
    public void displayPosts(Profile accessingProfile)
    {
        boolean profileIsInFollowingList = false;

        //check if you are following the profile that wants to access your posts
        for (Profile p: this.getFollowerList().getProfiles())
        {
            if (p == accessingProfile)
            {
                profileIsInFollowingList = true;
                break;
            }
        }

        if (    privacySettingIsPrivate() && profileIsInFollowingList
             || privacySettingIsPublic() )
        {
            for(Post p: this.getPosts())
            {
                //replacement for showing the pictures on the profile
                System.out.println("Bild: " + p.getImageDescription());
            }
        }
        else
        {
            System.out.println("You cannot view the posts of this profile.");
        }
    }



    //function changePrivacySettings

    //??? function displayPersonalInformation ???



    // ========================== //
    // ===== Post Functions ===== //
    // ========================== //

    public void newPost(String imageDescription, String postDescription, String meetingPlace, int meetingYear, int meetingMonth, int meetingDay)
    {
        Post post = new Post(this, imageDescription, postDescription, meetingPlace, meetingYear, meetingMonth, meetingDay);           //??? andere Lösung für tagged people ???
        post.submitPost(this);
    }


    public void addPost(Post post)
    {
        this.posts.add(post);
    }

    public void removePost(Post post)
    {
        this.posts.remove(post);
    }

    public Post getPost(int index)
    {
        return this.posts.get(index);
    }


    public void addTaggedPost(Post taggedPost)
    {
        this.taggedPosts.add(taggedPost);                                                       //??? sicherung ???
    }

    public void removeTaggedPost(Post taggedPost)
    {
        this.taggedPosts.remove(taggedPost);                                                     //??? sicherung ???
    }



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
        return this.relatedUser;
    }


    public ArrayList<Post> getPosts()
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
    public void setFollowing(Profile followedProfile)
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
        return !this.getFollowingList().isProfileAlreadyInList(followedProfile);
    }

    private boolean profileIsFollowed(Profile unfollowedProfile)
    {
        ArrayList<Profile> ownFollowingList = this.getFollowingList().getProfiles();

        for (Profile p: ownFollowingList)
        {
            if (p == unfollowedProfile)
            {
                return true;
            }
        }

        return false;
    }


    private void addOwnProfileToFollowerListOfFollowedProfile(Profile followedProfile)
    {
        followedProfile.setFollower(this);
    }

    private void addFollowedProfileToOwnFollowingList(Profile followedProfile)
    {
        this.setFollowing(followedProfile);
    }


    private void removeOwnProfileFromFollowedListOfUnfollowedProfile(Profile unfollowedProfile)
    {
        unfollowedProfile.getFollowerList().removeProfile(this);
    }

    private void removeUnfollowedProfileFromOwnFollowingList(Profile unfollowedProfile)
    {
        this.getFollowingList().removeProfile(unfollowedProfile);
    }



    private boolean privacySettingIsPrivate()
    {
        return this.getPrivacySetting() == PrivacySetting.PRIVATE;
    }

    private boolean privacySettingIsPublic()
    {
        return this.getPrivacySetting() == PrivacySetting.PUBLIC;
    }

}