package javarumdennnicht;

// import ArrayList class
import java.util.ArrayList;


public class FollowingList
{
    private int followingNumber;
    private ArrayList<Profile> profiles;


    //Constructor
    public FollowingList()
    {
        this.followingNumber = 0;
        this.profiles        = new ArrayList<>();
    }


    //Getter & Setter
    public int getFollowingNumber()
    {
        return followingNumber;
    }
    public void setFollowingNumber(int followingNumber)
    {
        this.followingNumber = followingNumber;
    }


    public ArrayList<Profile> getProfiles()
    {
        return profiles;
    }

    public void addProfile(Profile profile)
    {
        this.profiles.add(profile);
        this.followingNumber++;
    }
}