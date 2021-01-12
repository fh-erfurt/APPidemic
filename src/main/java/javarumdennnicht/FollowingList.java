package javarumdennnicht;


public class FollowingList
{
    private int followingNumber;
    private Profile[] profiles;


    //Constructor
    public FollowingList(int followingNumber)
    {
        this.followingNumber = followingNumber;
        this.profiles        = null;             //???nötig???
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


    public Profile[] getProfiles()
    {
        return profiles;
    }

    public void setProfiles(Profile profile)
    {
        //was machen wenn Lücke? ArrayList mit Funktion das zu schieben? oder einfachen Array?
        this.profiles[this.followingNumber] = profile;
        //mit schleife schauen ob die Stelle frei ist und sonst so lange +1 bis freie Stelle gefunden ist? (zur Sicherheit)
    }
}
