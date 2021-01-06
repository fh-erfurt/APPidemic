package javarumdennnicht;


public class FollowingList
{
    //Constructor
    public FollowingList() {}


    private int followingNumber;
    private Profile[] profiles;


    //Getter & Setter
    public int getFollowingNumber()
    {
        return followingNumber;
    }
    public void setFollowingNumber(int followingNumber)
    {
        this.followingNumber = followingNumber;
    }


    public Profile[] getProfiles()
    {
        return profiles;
    }

    public void setProfiles(Profile[] profiles)
    {
        //get last index then insert there
        this.profiles = profiles;
    }
}
