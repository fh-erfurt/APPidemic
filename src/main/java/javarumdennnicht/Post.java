package javarumdennnicht;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public final class Post
{
    private Profile author;
    private ArrayList<Profile> taggedProfiles;
    private ArrayList<Profile> likedByProfiles;
    private int likes;
    private String imageDescription;            // describes what you can see in the hypothetical picture
    private String postDescription;
    private String meetingPlace;
    private LocalDate meetingDate;
    private ArrayList<Comment> comments;




    // Constructor for the purpose of initializing an object with those passed parameter
    public Post(Profile author,String imageDescription, String postDescription, String meetingPlace, int meetingYear, int meetingMonth, int meetingDay)
    {
        this.author = author;
        this.taggedProfiles = new ArrayList<>();
        this.likedByProfiles = new ArrayList<>();
        this.likes = 0;
        this.imageDescription = imageDescription;
        this.postDescription = postDescription;
        this.meetingPlace = meetingPlace;
        this.meetingDate = LocalDate.of(meetingYear,meetingMonth,meetingDay);
        this.comments = new ArrayList<>();
    }

    // this method "submits" a post, which is used if the user wants to finish tagging people and uploads it to the side officially
    // it adds the post to the ArrayList of posts by the author and for every tagged profile it adds the post to the ArrayList of their taggedPosts (which are the posts they have been tagged in)
    public void submitPost()
    {
       this.getAuthor().addPost(this);

        for (Profile taggedProfile : this.taggedProfiles) {
            taggedProfile.addTaggedPost(this);
        }
    }

    // this method adds a like by the passed profile to the post
    // the like count rises and the liking profile is added to the likedBy ArrayList of the post
    public void addLike(Profile liker)
    {
        likedByProfiles.add(liker);
        this.setLikes(likedByProfiles.size());
    }
    public void removeLike(Profile removedLiker)
    {
        likedByProfiles.remove(removedLiker);
       this.setLikes(likedByProfiles.size());
    }

    // method used for tagging friends or others on a post, which adds them to the taggedProfiles list and
    // after using the submitPost method the post is posted on the tagged profiles
    public void addTaggedProfile(Profile taggedProfile)
    {
        taggedProfiles.add(taggedProfile);
    }
    // removes a tagged profile
    public void removeTaggedProfile(Profile untaggedProfile)
    {
        taggedProfiles.remove(untaggedProfile);
    }

    // used to write a comment with a given profile and adds it to the comments list, containing every commented comment :)
    public void addComment(Profile Commenter, String commentedText)
    {
        Comment newComment = new Comment(Commenter, commentedText);
        comments.add(newComment);
    }
    // removes a comment from the comments list                                                                                                                                     for when u get called out for your bigotry
    public void removeComment(Comment removedComment)
    {
        comments.remove(removedComment);
    }

    // Methode um alle Inhalte eines Posts leicht zu überprüfen
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



    // Setter und Getter
    public ArrayList<Profile> getTaggedProfiles()
    {
        return taggedProfiles;
    }
    public void setTaggedProfiles(ArrayList<Profile> newTaggedProfiles)
    {
        this.taggedProfiles = newTaggedProfiles;
    }

    public Profile getAuthor()
    {
        return author;
    }
    public void setAuthor(Profile author)
    {
        this.author = author;
    }

    public String getImageDescription()
    {
        return imageDescription;
    }
    public void setImageDescription(String imageDescription)
    {
        this.imageDescription = imageDescription;
    }

    public String getPostDescription()
    {
        return postDescription;
    }
    public void setPostDescription(String postDescription)
    {
        this.postDescription = postDescription;
    }


    public String getMeetingPlace() {
        return meetingPlace;
    }
    public void setMeetingPlace(String meetingPlace)
    {
        this.meetingPlace = meetingPlace;
    }

    public String getFormattedMeetingDate()
    {
        //define the pattern for the date
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return getMeetingDate().format(dateFormat);
    }
    public LocalDate getMeetingDate()
    {
        return meetingDate;
    }
    public void setMeetingDate(LocalDate meetingDate)
    {
        this.meetingDate = meetingDate;
    }

    public int getLikes()
    {
        return likedByProfiles.size();
    }
    public void setLikes(int likeCount)
    {
        this.likes = likeCount;
    }


    public ArrayList<Comment> getComments()
    {
        return comments;
    }
    public void setComments(ArrayList<Comment> comments)
    {
        this.comments = comments;
    }

}


