package javarumdennnicht.post;


//import own classes
import javarumdennnicht.profile.Profile;



public class Comment
{
    // ===================== //
    // ===== VARIABLES ===== //
    // ===================== //

    private String  commentText;
    private Profile commenter;



    // ======================= //
    // ===== CONSTRUCTOR ===== //
    // ======================= //

    public Comment(Profile commenter, String text)
    {
        this.commenter   = commenter;
        this.commentText = text;
    }



    // =========================== //
    // ===== GETTER & SETTER ===== //
    // =========================== //

    public String getText()
    {
        return this.commentText;
    }
    public void setText(String text)
    {
        this.commentText = text;
    }

    public Profile getCommenter()
    {
        return this.commenter;
    }
    public void setCommenter(Profile commenter)
    {
        this.commenter = commenter;
    }
}