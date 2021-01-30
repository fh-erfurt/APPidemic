package javarumdennnicht.post;

//import classes
import javarumdennnicht.profile.Profile;



public class Comment
{

    private String commentText;
    private Profile commenter;

    // Constructor
    public Comment(Profile commenter, String text)
    {
        this.commenter = commenter;
        this.commentText = text;
    }

    // Setter und Getter
    public String getText()
    {
        return commentText;
    }
    public void setText(String text)
    {
        this.commentText = text;
    }

    public Profile getCommenter()
    {
        return commenter;
    }
    public void setCommenter(Profile commenter)
    {
        this.commenter = commenter;
    }
}
