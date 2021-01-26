package javarumdennnicht;


// import ArrayList class
import java.util.ArrayList;
// import for "Arrays.asList()"
import java.util.Arrays;



public class ProfileList
{
    private int                numberOfProfiles;
    private ArrayList<Profile> profiles;


    //Constructor
    public ProfileList()
    {
        this.profiles = new ArrayList<>();
    }


    public void removeProfile(Profile profile)
    {
        for (int index = 0; index <= profiles.size(); index++)
        {
            if (profile == profiles.get(index))
            {
                profiles.remove(index);
                break;
            }
        }

        this.numberOfProfiles = profiles.size();
    }


    //Getter & Setter
    public int getNumberOfProfiles()
    {
        return numberOfProfiles;
    }


    public ArrayList<Profile> getProfiles()
    {
        return profiles;
    }

    public void addProfile(Profile profile)
    {
        this.profiles.add(profile);
        this.numberOfProfiles = profiles.size();
    }
}