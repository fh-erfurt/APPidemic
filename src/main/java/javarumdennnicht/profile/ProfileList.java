package javarumdennnicht.profile;


//import ArrayList class
import java.util.ArrayList;



//CLASS PROFILE-LIST
//This class is a basically an extension of an ArrayList, but we decided making an own class would make the code easier to read
//It also makes accessing items in the list easier through it custom methods (e.g. ".getProfile(index)" instead of ".getProfiles.get(index)")
public class ProfileList
{
    // ===================== //
    // ===== VARIABLES ===== //
    // ===================== //

    private       int                numberOfProfiles;
    private final ArrayList<Profile> profiles;



    // ======================= //
    // ===== CONSTRUCTOR ===== //
    // ======================= //

    public ProfileList()
    {
        this.profiles = new ArrayList<>();
    }



    // ============================= //
    // ===== GENERAL FUNCTIONS ===== //
    // ============================= //

    //gets an index and returns the profile on that index in the profiles-ArrayList
    //if the ArrayList has no elements or the index is higher than the elements in the array the function returns 'null'
    //it is saver to use than "get()" from the ArrayList, because "get()" cannot handle "out of bounds"-access
    public Profile getProfile(int index)
    {
        if (listHasNoElements() || indexIsHigherThanListSize(index) || indexIsLowerThanZero(index))
        {
            return null;
        }

        return this.getProfiles().get(index);
    }


    //gets a profile and checks if it is already in the profiles-ArrayList
    public boolean isProfileAlreadyInList(Profile profile)
    {
        return this.getProfiles().contains(profile);
    }


    //gets a profile and removes it from the profiles-ArrayList
    public void removeProfile(Profile profile)
    {
        this.getProfiles().remove(profile);
        setNumberOfProfiles();
    }


    //sets the current size of the profiles-ArrayList as the numberOfProfiles
    public void setNumberOfProfiles()
    {
        this.numberOfProfiles = this.getProfiles().size();
    }


    //gets a profile and adds it to the profiles-ArrayList
    public void addProfile(Profile profile)
    {
        this.getProfiles().add(profile);
        setNumberOfProfiles();
    }



    // =========================== //
    // ===== GETTER & SETTER ===== //
    // =========================== //

    public int getNumberOfProfiles()
    {
        return this.numberOfProfiles;
    }


    public ArrayList<Profile> getProfiles()
    {
        return this.profiles;
    }



    // =============================== //
    // ===== EXTRACTED FUNCTIONS ===== //
    // =============================== //

    private boolean listHasNoElements()
    {
        return this.getProfiles().size() == 0;
    }


    private boolean indexIsHigherThanListSize(int index)
    {
        return index > this.getProfiles().size();
    }


    private boolean indexIsLowerThanZero(int index)
    {
        return index < 0;
    }
}