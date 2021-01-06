package javarumdennnicht;


import de.diafreak.java1test.Profile;

public class FollowerList
{
    //Constructor
    public FollowerList() {}


    private int followerNumber;
    private Profile[] profiles;


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

    public void setProfiles(Profile[] profiles)
    {
        //get last index then insert there
        this.profiles = profiles;
    }
}
