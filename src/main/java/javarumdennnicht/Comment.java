package main;

public class Comment
{

    private String text;
    private Profile commenter;

    // leerer constructor hehe
    public Comment()
    {
    }
    // ordentlicher constructor
    public Comment(Profile commenter, String text)
    {
        this.commenter = commenter;
        this.text = text;
    }



    // Setter und Getter
    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public Profile getCommenter()
    {
        return commenter;
    }

    // brauchen wir da überhaupt nen setter?
    public void setCommenter(Profile commenter)
    {
        this.commenter = commenter;
    }
}
