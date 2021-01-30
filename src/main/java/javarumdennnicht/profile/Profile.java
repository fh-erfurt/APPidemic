package javarumdennnicht.profile;


//import own classes
import javarumdennnicht.user.User;
import javarumdennnicht.post.Post;

//import ArrayList class
import java.util.ArrayList;
//import HashMap<> for personalInformation
import java.util.HashMap;
//import date-formatter
import java.time.format.DateTimeFormatter;
//import iterator for for-each loops
import java.util.Iterator;



/*
* CLASS PROFILE
* This class contains the non-user relevant information. It is the social face of the person that made the account.
* You can decide which personal information is shown publicly and which is not, except your username.
* It is possible to follow/unfollow other people and create posts with or without tagged profiles.
* Most importantly you can create an alarm in case you get tested positive with Covid-19. This will notify all persons
* you tagged in your post and people that tagged you in their post. This only works for profiles that you are befriended
* with (you follow them and they follow you) to protect abuse of this feature.
* */
public class Profile
{
    // ===================== //
    // ===== VARIABLES ===== //
    // ===================== //

    public enum PrivacySetting { PUBLIC, PRIVATE }

    private final HashMap<String, PrivacySetting> privacyStatusOfPersonalInformation;

    private final User            relatedUser;
    private       String          biography;
    private       ProfileList     followerList;
    private       ProfileList     followingList;
    private       ArrayList<Post> posts;
    private       ArrayList<Post> taggedPosts;
    private       PrivacySetting  privacySetting;
    private       boolean         alarmIsTriggered;



    // ======================= //
    // ===== CONSTRUCTOR ===== //
    // ======================= //

    public Profile(User relatedUser)
    {
        this.relatedUser      = relatedUser;
        this.biography        = "";
        this.followerList     = new ProfileList();
        this.followingList    = new ProfileList();
        this.posts            = new ArrayList<>();
        this.taggedPosts      = new ArrayList<>();
        this.privacySetting   = PrivacySetting.PRIVATE;
        this.alarmIsTriggered = false;

        this.privacyStatusOfPersonalInformation = new HashMap<>();
        setPersonalInformationToPrivate();
    }



    // ============================= //
    // ===== GENERAL FUNCTIONS ===== //
    // ============================= //

    //LocalDate is in format yyyy-mm-dd
    //this function formats it into dd-mm-yyyy format
    public String getFormattedBirthdate()
    {
        //define the pattern for the date
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return getRelatedUser().getBirthdate().format(dateFormat);
    }


    //when a new profile is created all personal information is set to private
    private void setPersonalInformationToPrivate()
    {
        this.privacyStatusOfPersonalInformation.put("firstname", PrivacySetting.PRIVATE);
        this.privacyStatusOfPersonalInformation.put("lastname",  PrivacySetting.PRIVATE);
        this.privacyStatusOfPersonalInformation.put("birthdate", PrivacySetting.PRIVATE);
        this.privacyStatusOfPersonalInformation.put("email",     PrivacySetting.PRIVATE);
    }



    // ============================= //
    // ===== PROFILE FUNCTIONS ===== //
    // ============================= //

    public void follow(Profile followedProfile)
    {
        //only enable to follow a profile if you aren't already following it and if the profile is not itself
        if (profileIsNotAlreadyInFollowingList(followedProfile) && profileIsNotItself(followedProfile))
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


    public void setFollower(Profile follower)
    {
        this.followerList.addProfile(follower);
    }
    public void setFollowing(Profile followedProfile)
    {
        this.followingList.addProfile(followedProfile);
    }



    // =========================== //
    // ===== ALARM FUNCTIONS ===== //
    // =========================== //

    //creates a warning for all and only befriended profiles you tagged in your posts, or that tagged you in
    //their posts in case you get tested positive with Corona
    //"befriended": profiles that you follow and they follow you back
    //@taggedProfiles  stores all profiles that are tagged in your own posts and tagged in the same posts as you
    public void createAlarm()
    {
        ArrayList<Profile> taggedProfiles   = new ArrayList<>();
        ArrayList<Profile> followedProfiles = this.getFollowingList().getProfiles();
        ArrayList<Profile> followers        = this.getFollowerList().getProfiles();
        ArrayList<Post>    myPosts          = this.getPosts();
        ArrayList<Post>    postsIAmTaggedIn = this.getTaggedPosts();


        //loop through post of own profile
        getTaggedProfilesFromOwnPosts(myPosts, taggedProfiles);


        //loop through posts you are tagged in and are not your own posts
        getTaggedProfilesFromPostsImTaggedIn(postsIAmTaggedIn, taggedProfiles);


        //remove all profiles from @taggedProfiles that I am not befriended with
        removeProfilesThatIAmNotBefriendedWith(followedProfiles, followers, taggedProfiles);


        //trigger the alarm on all befriended profiles that are tagged
        for (Profile befriended: taggedProfiles)
        {
            befriended.triggerAlarm();
        }
    }


    //if a profile created an alarm and you are befriended with it, you will get a notification
    private void triggerAlarm()
    {
        String username = this.getRelatedUser().getUsername();

        System.out.println(username + ": ALARM");
        this.setAlarmIsTriggered(true);
    }

    //replacement for: if you get the alarm and press a button with e.g. "okay"
    public void resetAlarm()
    {
        this.setAlarmIsTriggered(false);
    }



    // ====================================== //
    // ===== HELPER FUNCTIONS FOR ALARM ===== //
    // ====================================== //

    private void getTaggedProfilesFromOwnPosts(ArrayList<Post> myPosts, ArrayList<Profile> taggedProfiles)
    {
        //loop through posts of own profile
        for (Post post: myPosts)
        {
            //get all tagged profiles from current post
            for (Profile tagged: post.getTaggedProfiles())
            {
                //to avoid duplicate entries only add taggedProfiles that are not already in the list
                if (!taggedProfiles.contains(tagged))
                {
                    taggedProfiles.add(tagged);
                }
            }
        }
    }


    private void getTaggedProfilesFromPostsImTaggedIn(ArrayList<Post> postsIAmTaggedIn, ArrayList<Profile> taggedProfiles)
    {
        for (Post taggedPost: postsIAmTaggedIn)
        {
            //get all tagged profiles from current post
            for (Profile tagged: taggedPost.getTaggedProfiles())
            {
                //avoid duplicate entries
                if (!taggedProfiles.contains(tagged))
                {
                    taggedProfiles.add(tagged);
                }
            }

            //get author of the post you're tagged in
            Profile author = taggedPost.getAuthor();

            if (!taggedProfiles.contains(author))
            {
                taggedProfiles.add(author);
            }
        }
    }


    private void removeProfilesThatIAmNotBefriendedWith(ArrayList<Profile> followedProfiles, ArrayList<Profile> followers, ArrayList<Profile> taggedProfiles)
    {
        //set iterator for taggedProfiles so .remove() can be used properly
        Iterator<Profile> i_profiles = taggedProfiles.iterator();

        removeProfilesIAmNotFollowing(i_profiles, followedProfiles);

        //pass the iterator the new taggedProfiles-list
        i_profiles = taggedProfiles.iterator();

        //after that only befriended profiles are in @taggedProfiles
        removeProfilesIAreNotFollowingMe(i_profiles, followers);
    }


    private void removeProfilesIAmNotFollowing(Iterator<Profile> i_profiles, ArrayList<Profile> followedProfiles)
    {
        //check which of the tagged profiles I am following and remove all tagged profiles I am not following
        while (i_profiles.hasNext())
        {
            //must be called here so profile.remove() can be used
            Profile p = i_profiles.next();

            //if my FollowingList does not contain the profile from the taggedProfiles it gets removed
            if (!followedProfiles.contains(p))
            {
                i_profiles.remove();
            }
        }
    }


    private void removeProfilesIAreNotFollowingMe(Iterator<Profile> i_profiles, ArrayList<Profile> followers)
    {
        //check which of the taggedProfiles I am following are following me back
        while (i_profiles.hasNext())
        {
            Profile p = i_profiles.next();

            //if my FollowerList does not contain the profile from the taggedProfiles it gets removed
            if (!followers.contains(p))
            {
                i_profiles.remove();
            }
        }
    }



    // ============================= //
    // ===== PRIVACY FUNCTIONS ===== //
    // ============================= //

    //change the privacy setting of all personal information at once
    //to change the privacy setting of only one attribute, use the override-method below this one
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


    //override-method if you want change the privacy-setting of a specific personal attribute
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


    //display your personal information depending on their privacy setting
    //if all personal information is private, only the username will be displayed
    public void displayPersonalInformation()
    {
        User user = this.getRelatedUser();
        HashMap<String,PrivacySetting> personalInformation = this.getPrivacyStatusOfPersonalInformation();

        System.out.println("User: " + user.getUsername());

        //iterates through the keys of the privacyStatusOfPersonalInformation-list ("firstname", "lastname" ...)
        for (String attribute: personalInformation.keySet())
        {
            //takes the key from the for-each-loop and gets the associated privacySetting for it
            if (privacySettingIsPublic(attribute))
            {
                switch(attribute)
                {
                    case "firstname":
                        System.out.println("First Name: " + user.getFirstName());
                        break;

                    case "lastname":
                        System.out.println("Last Name: " + user.getLastName());
                        break;

                    case "birthdate":
                        System.out.println("Birthdate: " + user.getBirthdate());
                        break;

                    case "email":
                        System.out.println("E-Mail: " + user.getEMail());
                        break;

                    default:
                        System.out.println("This personal attribute does not exist.");
                        break;
                }
            }
        }
    }



    // ========================== //
    // ===== POST FUNCTIONS ===== //
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


    //creates a new post, tags the given profiles on it and "uploads" the post on your profile and on all profiles you tagged
    //if you want to tag profiles, they have to be passed in an ArrayList, if no tags are needed the override-method below can be used
    public void newPost(String imageDescription, String postDescription, String meetingPlace, int meetingYear, int meetingMonth, int meetingDay,
                        ArrayList<Profile> taggedProfiles)
    {
        Post post = new Post(this, imageDescription, postDescription, meetingPlace, meetingYear, meetingMonth, meetingDay);
        post.setTaggedProfiles(taggedProfiles);
        post.submitPost();
    }

    //override-method if you don't want to tag profiles in your post
    public void newPost(String imageDescription, String postDescription, String meetingPlace, int meetingYear, int meetingMonth, int meetingDay)
    {
        Post post = new Post(this, imageDescription, postDescription, meetingPlace, meetingYear, meetingMonth, meetingDay);
        post.submitPost();
    }


    public void addPost(Post post)
    {
        this.getPosts().add(post);
    }

    public void removePost(Post post)
    {
        this.getPosts().remove(post);
    }

    public Post getPost(int index)
    {
        return this.getPosts().get(index);
    }


    public void addTaggedPost(Post taggedPost)
    {
        this.getTaggedPosts().add(taggedPost);
    }

    public void removeTaggedPost(Post taggedPost)
    {
        this.getTaggedPosts().remove(taggedPost);
    }



    // =========================== //
    // ===== GETTER & SETTER ===== //
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
    public void setPosts(ArrayList<Post> posts)
    {
        this.posts = posts;
    }


    public ArrayList<Post> getTaggedPosts()
    {
        return this.taggedPosts;
    }
    public void setTaggedPosts(ArrayList<Post> taggedPosts)
    {
        this.taggedPosts = taggedPosts;
    }


    public ProfileList getFollowerList()
    {
        return this.followerList;
    }
    public void setFollowerList(ProfileList followerList)
    {
        this.followerList = followerList;
    }


    public ProfileList getFollowingList()
    {
        return this.followingList;
    }
    public void setFollowingList(ProfileList followingList)
    {
        this.followingList = followingList;
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

    public boolean getAlarmIsTriggered()
    {
        return this.alarmIsTriggered;
    }
    public void setAlarmIsTriggered(boolean alarmIsTriggered)
    {
        this.alarmIsTriggered = alarmIsTriggered;
    }



    // =============================== //
    // ===== EXTRACTED FUNCTIONS ===== //
    // =============================== //

    private boolean profileIsNotItself(Profile followedProfile)
    {
        return !(followedProfile == this);
    }

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


    private boolean privacySettingIsPublic(String attribute)
    {
        return this.getPrivacyStatusOfPersonalInformation().get(attribute) == PrivacySetting.PUBLIC;
    }
}