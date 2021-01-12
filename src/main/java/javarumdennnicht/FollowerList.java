package javarumdennnicht;


public class FollowerList
{
    private int followerNumber;
    private Profile[] profiles;


    //Constructor
    public FollowerList(int followerNumber)
    {
        this.followerNumber = followerNumber;
        this.profiles       = null;             //???nötig???
    }


    //Getter & Setter
    public int getFollowerNumber()
    {
        return followerNumber;
    }
    public void setFollowerNumber(int followerNumber)
    {
        this.followerNumber = followerNumber;
    }


    public Profile[] getProfiles()
    {
        return profiles;
    }

    public void setProfiles(Profile profile)
    {
        //was machen wenn Lücke?
        this.profiles[this.followerNumber] = profile;
    }
}
