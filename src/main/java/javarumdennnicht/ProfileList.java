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
    //it is saver to use than "get()" from the ArrayList, because "get()" cannot handle "out of bounds"-access
    public Profile getProfile(int index)
    {
        if (listHasNoElements() || indexIsHigherThanListSize(index) || indexIsLowerThanZero(index))
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
    public void removeProfile(Profile profile)
    {
        for (Profile p: this.profiles)
        {
            if (p == profile)
            {
                this.profiles.remove(p);
                break;
            }
        }

        setNumberOfProfiles();
    }


    //sets the current size of the profiles-ArrayList as the numberOfProfiles
    public void setNumberOfProfiles()
    {
        this.numberOfProfiles = this.profiles.size();
    }


    //gets a profile and adds it to the profiles-ArrayList
    public void addProfile(Profile profile)
    {
        this.profiles.add(profile);
        setNumberOfProfiles();
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


    private boolean indexIsLowerThanZero(int index)
    {
        return index < 0;
    }
}