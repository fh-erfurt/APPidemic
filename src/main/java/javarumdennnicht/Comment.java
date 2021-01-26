package javarumdennnicht;

public class Comment
{

    private String commenttext;
    private Profile commenter;

    // Constructor
    public Comment(Profile commenter, String text)
    {
        this.commenter = commenter;
        this.commenttext = text;
    }

    // Setter und Getter
    public String getText()
    {
        return commenttext;
    }
    public void setText(String text)
    {
        this.commenttext = text;
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
