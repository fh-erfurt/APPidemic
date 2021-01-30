package javarumdennnicht.post;


//import own classes
import javarumdennnicht.profile.Profile;

//import ArrayList class
import java.util.ArrayList;
//import LocalDate
import java.time.LocalDate;
//import date-formatter
import java.time.format.DateTimeFormatter;



public class Post
{
    // ===================== //
    // ===== VARIABLES ===== //
    // ===================== //

    private Profile            author;
    private ArrayList<Profile> taggedProfiles;
    private ArrayList<Profile> likedByProfiles;
    private int                likes;
    private String             imageDescription;    // describes what you can see in the hypothetical picture
    private String             postDescription;
    private String             meetingPlace;
    private LocalDate          meetingDate;
    private ArrayList<Comment> comments;



    // ======================= //
    // ===== CONSTRUCTOR ===== //
    // ======================= //

    // Constructor for the purpose of initializing an object with those passed parameter
    public Post(Profile author,String imageDescription, String postDescription, String meetingPlace, int meetingYear, int meetingMonth, int meetingDay)
    {
        this.author           = author;
        this.taggedProfiles   = new ArrayList<>();
        this.likedByProfiles  = new ArrayList<>();
        this.likes            = 0;
        this.imageDescription = imageDescription;
        this.postDescription  = postDescription;
        this.meetingPlace     = meetingPlace;
        this.meetingDate      = LocalDate.of(meetingYear,meetingMonth,meetingDay);    // fuses the given Integers to a date
        this.comments         = new ArrayList<>();
    }



    // ============================= //
    // ===== GENERAL FUNCTIONS ===== //
    // ============================= //

    public String getFormattedMeetingDate()
    {
        //define the pattern for the date
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return getMeetingDate().format(dateFormat);
    }



    // ========================== //
    // ===== POST FUNCTIONS ===== //
    // ========================== //

    // this method "submits" a post, which is used if the user wants to finish tagging people and uploads it to the side officially
    // it adds the post to the ArrayList of posts by the author and for every tagged profile it adds the post to the ArrayList of their taggedPosts (which are the posts they have been tagged in)
    public void submitPost()
    {
       this.getAuthor().addPost(this);

        for (Profile taggedProfile : this.getTaggedProfiles())
        {
            taggedProfile.addTaggedPost(this);
        }
    }


    // this method adds a like by the passed profile to the post
    // the like count rises and the liking profile is added to the likedBy ArrayList of the post
    public void addLike(Profile liker)
    {
        // liking a profile again will undo the like
        if(this.getLikedByProfiles().contains(liker))
        {
            this.getLikedByProfiles().remove(liker);
        }
        else
        {
            this.getLikedByProfiles().add(liker);
            this.setLikes(this.getLikedByProfiles().size());
        }
    }


    public void removeLike(Profile removedLiker)
    {
        this.getLikedByProfiles().remove(removedLiker);
        this.setLikes(getLikedByProfiles().size());
    }


    // method used for tagging friends or others on a post, which adds them to the taggedProfiles list and
    // after using the submitPost method the post is posted on the tagged profiles
    public void addTaggedProfile(Profile taggedProfile)
    {
        if(this.getTaggedProfiles().contains(taggedProfile))
        {
            System.out.println("Profile has already been tagged.");
        }
        else
        {
            getTaggedProfiles().add(taggedProfile);
        }
    }


    // removes a tagged profile
    public void removeTaggedProfile(Profile untaggedProfile)
    {
        this.getTaggedProfiles().remove(untaggedProfile);
    }


    // used to write a comment with a given profile and adds it to the comments list, containing every commented comment :)
    public void addComment(Profile Commenter, String commentedText)
    {
        Comment newComment = new Comment(Commenter, commentedText);
        this.getComments().add(newComment);
    }


    // removes a comment from the comments list
    public void removeComment(Comment removedComment)
    {
        this.getComments().remove(removedComment);
    }


    // shows a good overview of the post in the console log
    public void viewPost(Post post)
    {
        System.out.println(this.getAuthor().getRelatedUser().getUsername());
        System.out.println("------------------------------------");
        System.out.println( post.getImageDescription());
        System.out.println("------------------------------------");
        System.out.println( post.getPostDescription());
        System.out.println("------------------------------------");
        System.out.println(post.getMeetingPlace());
        System.out.println("------------------------------------");
        System.out.println(post.getFormattedMeetingDate());
    }



    // =========================== //
    // ===== GETTER & SETTER ===== //
    // =========================== //

    public Profile getAuthor()
    {
        return this.author;
    }
    public void setAuthor(Profile author)
    {
        this.author = author;
    }


    public ArrayList<Profile> getTaggedProfiles()
    {
        return this.taggedProfiles;
    }
    public void setTaggedProfiles(ArrayList<Profile> newTaggedProfiles)
    {
        this.taggedProfiles = newTaggedProfiles;
    }


    public ArrayList<Profile> getLikedByProfiles()
    {
        return this.likedByProfiles;
    }
    public void setLikedByProfiles(ArrayList<Profile> newLikedByProfiles)
    {
        this.likedByProfiles = newLikedByProfiles;
    }


    public String getImageDescription()
    {
        return this.imageDescription;
    }
    public void setImageDescription(String imageDescription)
    {
        this.imageDescription = imageDescription;
    }


    public String getPostDescription()
    {
        return this.postDescription;
    }
    public void setPostDescription(String postDescription)
    {
        this.postDescription = postDescription;
    }


    public String getMeetingPlace() {
        return this.meetingPlace;
    }
    public void setMeetingPlace(String meetingPlace)
    {
        this.meetingPlace = meetingPlace;
    }


    public LocalDate getMeetingDate()
    {
        return this.meetingDate;
    }
    public void setMeetingDate(LocalDate meetingDate)
    {
        this.meetingDate = meetingDate;
    }


    public int getLikes()
    {
        return this.likedByProfiles.size();
    }
    public void setLikes(int likeCount)
    {
        this.likes = likeCount;
    }


    public ArrayList<Comment> getComments()
    {
        return this.comments;
    }
    public void setComments(ArrayList<Comment> comments)
    {
        this.comments = comments;
    }
}