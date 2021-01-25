package javarumdennnicht;

// import ArrayList class
import java.util.ArrayList;


public class FollowerList
{
    private int followerNumber;
    private ArrayList<Profile> profiles;


    //Constructor
    public FollowerList()
    {
        this.followerNumber = 0;
        this.profiles       = new ArrayList<>();
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


    public ArrayList<Profile> getProfiles()
    {
        return profiles;
    }

    public void addProfile(Profile profile)
    {
        this.profiles.add(profile);
        this.followerNumber++;
    }
}
