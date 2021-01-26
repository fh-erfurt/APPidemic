package javarumdennnicht;


//import ArrayList class
import java.util.ArrayList;



public class ProfileList
{
    private       int                numberOfProfiles;
    private final ArrayList<Profile> profiles;



    // ======================= //
    // ===== Constructor ===== //
    // ======================= //
    public ProfileList()
    {
        this.profiles = new ArrayList<>();
    }



    // =============================== //
    // ===== Functions / Methods ===== //
    // =============================== //

    //gets an index and returns the profile on that index in the profiles-ArrayList
    //if the ArrayList has no elements or the index is higher than the elements in the array the function returns 'null'
    public Profile getProfile(int index)
    {
        if (listHasNoElements() || indexIsHigherThanListSize(index))
        {
            return null;
        }

        return this.profiles.get(index);
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
        for (int index = 0; index < this.profiles.size(); index++)
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


    //gets a profile and adds it to the profiles-ArrayList
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



    // ============================= //
    // ===== Extracted Methods ===== //
    // ============================= //
    private boolean listHasNoElements()
    {
        return this.profiles.size() == 0;
    }


    private boolean indexIsHigherThanListSize(int index)
    {
        return index > this.profiles.size();
    }
}