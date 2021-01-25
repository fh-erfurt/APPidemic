package javarumdennnicht;

import java.time.format.DateTimeFormatter;


public class Profile
{
    enum PrivacySetting { PUBLIC, PRIVATE }

    private String         biography;
    private User           relatedUser;
    private Post[]         relatedPosts;
    private FollowerList   followerList;
    private FollowingList  followingList;
    private PrivacySetting privacySetting;


    //Constructor
    public Profile(User relatedUser)
    {
        this.relatedUser    = relatedUser;
        this.biography      = "";
        this.relatedPosts   = null;
        this.followerList   = new FollowerList();
        this.followingList  = new FollowingList();
        this.privacySetting = PrivacySetting.PRIVATE;
    }


    public String getFormattedBirthdate()
    {
        //LocalDate is in format yyyy-mm-dd, this function formats it into dd-mm-yyyy format
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return relatedUser.getBirthdate().format(dateFormat);
    }

    public void follow(Profile followedUser)    // !!! no double follow !!!
    {
        followedUser.setFollower(this.relatedUser.getRelatedProfile());        //add your current profile to the FollowingList of the profile you want to follow
        this.relatedUser.getRelatedProfile().setFollowingList(followedUser);   //add the profile you're following to your own FollowingList
    }

    public void unfollow() {} //ArrayList al=new ArrayList(); | al.removeAll(Arrays.asList(null,""));

    public void newPost(){}

    public void createAlarm(){}

    //function changePrivacySettings

                        //??? function displayPersonalInformation ???

    //Getter & Setter
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


    public FollowerList getFollowerList()
    {
        return followerList;
    }

    public void setFollower(Profile follower)
    {
        this.followerList.addProfile(follower);
    }


    public FollowingList getFollowingList()
    {
        return followingList;
    }

    public void setFollowingList(Profile following)
    {
        this.followingList.addProfile(following);
    }


    public PrivacySetting getPrivacySetting()
    {
        return privacySetting;
    }
    public void setPrivacySetting(PrivacySetting privacySetting)
    {
        this.privacySetting = privacySetting;
    }
}
