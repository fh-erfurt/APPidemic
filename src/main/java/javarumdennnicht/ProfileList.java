package javarumdennnicht;


//import ArrayList class
import java.util.ArrayList;



public class ProfileList
{
    private int                numberOfProfiles;
    private ArrayList<Profile> profiles;


    //Constructor
    public ProfileList()
    {
        this.profiles = new ArrayList<>();
    }


    //gets a profile and checks if it is already in the profiles-ArrayList
    public boolean isProfileAlreadyInList(Profile profile)
    {
        for (Profile p: this.profiles)
        {
            if (p == profile)
            {
                return true;
            }
        }

        return false;
    }


    //gets a profile and removes it from the profiles-ArrayList
    //cannot use a for-each loop because the index is required to remove the correct profile
    public void removeProfile(Profile profile)
    {
        for (int index = 0; index <= this.profiles.size(); index++)
        {
            if (profile == this.profiles.get(index))
            {
                this.profiles.remove(index);
                break;
            }
        }

        setProfileNumber();
    }


    //sets the current size of the profiles-ArrayList as the numberOfProfiles
    public void setProfileNumber()
    {
        this.numberOfProfiles = this.profiles.size();
    }


    public void addProfile(Profile profile)
    {
        this.profiles.add(profile);
        setProfileNumber();
    }


    // =========================== //
    // ===== Getter & Setter ===== //
    // =========================== //
    public int getNumberOfProfiles()
    {
        return this.numberOfProfiles;
    }


    public ArrayList<Profile> getProfiles()
    {
        return this.profiles;
    }
}