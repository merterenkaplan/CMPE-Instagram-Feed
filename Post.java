import java.util.ArrayList;

public class Post implements Comparable<Post> {
    String userId;

    String postID;

    int numberOfLikes = 0;

    ArrayList<User> whoLiked;

    ArrayList<User> whoSaw;
    String content;

    // Constructor to initialize a post with an userID, content, postID,numberOfLikes,whoSaw and whoLiked


    public Post(String userId,String postID, String content) {
        this.userId = userId;
        this.content = content;
        this.postID = postID;
        this.numberOfLikes = 0;
        this.whoSaw = new ArrayList<>();
        this.whoLiked = new ArrayList<>();
    }

    //this method helps us to compare two post

    public int compareTo(Post o) {

        if (numberOfLikes!=o.numberOfLikes){
            return numberOfLikes-o.numberOfLikes;    //this return when number of likes are different and this compare to number of likes

        }else {
            return postID.compareTo(o.postID); //this return when number of likes are same and this compare to post ID's
        }
    }

}
