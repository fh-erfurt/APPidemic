package javarumdennnicht;


//import ArrayList class
import java.util.ArrayList;
//import HashMap<> for personalInformation
import java.util.HashMap;
//import date-formatter
import java.time.format.DateTimeFormatter;



public class Profile
{
    enum PrivacySetting { PUBLIC, PRIVATE }

    private final HashMap<String, PrivacySetting> privacyStatusOfPersonalInformation;

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

        this.privacyStatusOfPersonalInformation = new HashMap<>();
        setPersonalInformationToPrivate();
    }



    // ============================= //
    // ===== General Functions ===== //
    // ============================= //

    //LocalDate is in format yyyy-mm-dd
    //this function formats it into dd-mm-yyyy format
    public String getFormattedBirthdate()
    {
        //define the pattern for the date
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return getRelatedUser().getBirthdate().format(dateFormat);
    }


    private void setPersonalInformationToPrivate()
    {
        this.privacyStatusOfPersonalInformation.put("firstname", PrivacySetting.PRIVATE);
        this.privacyStatusOfPersonalInformation.put("lastname",  PrivacySetting.PRIVATE);
        this.privacyStatusOfPersonalInformation.put("birthdate", PrivacySetting.PRIVATE);
        this.privacyStatusOfPersonalInformation.put("email",     PrivacySetting.PRIVATE);
    }



    // ============================= //
    // ===== Profile Functions ===== //
    // ============================= //

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


    public void unfollow(Profile unfollowedProfile)
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
            //replacement for "Unfollow"-button displaying "Follow"
            System.out.println("A profile that you are not following cannot be unfollowed!");
        }
    }


    //this function is used for warning all persons you tagged in your posts, or tagged you in their posts
    //in case you get tested positive with Corona
    public void createAlarm()
    {

    }



    // ============================= //
    // ===== Privacy Functions ===== //
    // ============================= //

    public void changePrivacyStatusOfPersonalInformation(PrivacySetting firstname, PrivacySetting lastname, PrivacySetting birthdate, PrivacySetting email)
    {
        //for-each-loop iterates through the keys of the personalInformation-HashMap ("firstname", "lastname" ...)
        for (String attribute: getPrivacyStatusOfPersonalInformation().keySet())
        {
            switch(attribute)
            {
                case "firstname":
                    getPrivacyStatusOfPersonalInformation().put(attribute, firstname);
                    break;

                case "lastname":
                    getPrivacyStatusOfPersonalInformation().put(attribute, lastname);
                    break;

                case "birthdate":
                    getPrivacyStatusOfPersonalInformation().put(attribute, birthdate);
                    break;

                case "email":
                    getPrivacyStatusOfPersonalInformation().put(attribute, email);
                    break;

                default:
                    System.out.println("This personal information does not exist.");
                    break;
            }
        }
    }


    //override-function if you want change the privacy-setting of a specific attribute
    public void changePrivacyStatusOfPersonalInformation(String attribute, PrivacySetting setting)
    {
        for (String key: getPrivacyStatusOfPersonalInformation().keySet())
        {
            //check if the given attribute is a valid key in the privacyStatusOfPersonalInformation-HashMap
            if (key.equals(attribute))
            {
                getPrivacyStatusOfPersonalInformation().put(attribute, setting);
                //exit the function if the attribute is a valid key
                return;
            }
        }
        //print error if the attribute was not a valid key
        System.out.println("This personal attribute does not exist.");
    }



    public void displayPersonalInformation()
    {

    }



    // ========================== //
    // ===== Post Functions ===== //
    // ========================== //

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

    public ArrayList<Post> getTaggedPosts()
    {
        return this.taggedPosts;
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


    public HashMap<String, PrivacySetting> getPrivacyStatusOfPersonalInformation()
    {
        return this.privacyStatusOfPersonalInformation;
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