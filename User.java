import java.util.ArrayList;

public class User {
    //users ıd
    public String Id ;

    //users that user follows them
    public HashMap<User,String> Following =  new HashMap<User,String>();

    //user's posts
    ArrayList<Post> posts;


    // Constructor to initialize a user with an ID and followings, and posts

    public User(String id) {
        Id = id;
        Following = new HashMap<User,String>();
        this.posts = new ArrayList<>();
    }

    //follow method to add following hash

    public String follow(User user){
        if (user == null){
            return "Some error occurred in follow_user.";//that returns when there is not an user in that ID
        }
        if (Following.get(user)==null) {
         Following.put(user, user.Id);
            return Id+" followed "+user.Id+".";//that returns when there is an user in that ID and that user does not follow he

        }
        if (user.Id==Id){
            return   "Some error occurred in follow_user.";//that returns when the users are same
        }
        if (Following.get(user)!=null){
            return "Some error occurred in follow_user.";//that returns when there is an user in that ID but that user follows he
        }
        return "";
    }


    //follow method to remove following hash
    public String unFollow(User user){
        if (user == null){
            return "Some error occurred in unfollow_user";//that returns when there is not an user in that ID
        }
        if (Following.get(user)!=null) {
            Following.remove(user);
            return Id+" unfollowed "+user.Id+".";//that returns when there is an user in that ID but that user follows he
        }
        if (user.Id==Id){
            return   "Some error occurred in unfollow_user.";//that returns when the users are same
        }
        if (Following.get(user)==null){
            return "Some error occurred in unfollow_user.";//that returns when there is an user in that ID and that user does not follow he
        }
        return "";
    }
}





