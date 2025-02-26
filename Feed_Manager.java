import java.util.ArrayList;

public class Feed_Manager {

    // Create a new user
    public void creating_user(String ID,HashMap userhash,StringBuilder writerToOutput){
        if (userhash.get(ID)==null){
            User user = new User(ID);// Creating new user
            userhash.put(ID,user);// putting him yo hash
            writerToOutput.append("Created user with Id " + ID+"." +System.lineSeparator());// writing to output
        }else {
            writerToOutput.append("Some error occurred in create_user."+ System.lineSeparator());// writing to output
        }
    }

    //following a user
    public void following(String user1,String user2,HashMap<String,User> userhash,StringBuilder writerToOutput){
        if (userhash.get(user1)==null||userhash.get(user2)==null||user1.equals(user2)){//this works when users are not in the system
            writerToOutput.append("Some error occurred in follow_user."+ System.lineSeparator());// writing to output
        }else {
            String result = userhash.get(user1).follow(userhash.get(user2));//following user
            writerToOutput.append(result+ System.lineSeparator());// writing to output
        }
    }
    public void unFollowing(String user1,String user2,HashMap<String,User> userhash,StringBuilder writerToOutput){
        if (userhash.get(user1)==null||userhash.get(user2)==null){//this works when users are not in the system
            writerToOutput.append("Some error occurred in unfollow_user."+ System.lineSeparator());// writing to output
        }else {
            String result = userhash.get(user1).unFollow(userhash.get(user2));//unfollowing user
            writerToOutput.append(result+ System.lineSeparator());// writing to output
        }
    }

    //creating post
    public void createPost(String userId,String postId,String content,HashMap<String,User> userhash,StringBuilder writerToOutput,HashMap<String,Post> posthash){
        if (userhash.get(userId)==null){//this works when users is not in the system
            writerToOutput.append("Some error occurred in create_post."+ System.lineSeparator());// writing to output
        }else if (posthash.get(postId)!=null){//this works when post  in the system
            writerToOutput.append("Some error occurred in create_post."+ System.lineSeparator());// writing to output
        }else {
            Post newpost = new Post(userId,postId,content);//creating post
            userhash.get(userId).posts.add(newpost);//adding post to users posts
            posthash.put(postId,newpost);//adding post posthash
            writerToOutput.append(userId + " created a post with Id "+ postId +"."+ System.lineSeparator());// writing to output
        }
    }

    //seeing post
    public void seeingAPost(String userId,String postId,HashMap<String,User> userhash,StringBuilder writerToOutput,HashMap<String,Post> posthash){
        if (userhash.get(userId)==null){//this works when users is not in the system
            writerToOutput.append("Some error occurred in see_post."+ System.lineSeparator());// writing to output
        }else if (posthash.get(postId)==null){//this works when post is not in the system
            writerToOutput.append("Some error occurred in see_post."+ System.lineSeparator());// writing to output
        }else {
            if(!posthash.get(postId).whoSaw.contains(userhash.get(userId))){//this works when post is not viewed from user
                posthash.get(postId).whoSaw.add(userhash.get(userId));//adding user to whosaw
            }
            writerToOutput.append(userId + " saw "+ postId +"."+ System.lineSeparator());// writing to output
        }
    }

    //seeing all post
    public void seeingAllPostsOfAUser(String userId1,String userId2,HashMap<String,User> userhash,StringBuilder writerToOutput,HashMap<String,Post> posthash){
        if (userhash.get(userId1)==null||userhash.get(userId2)==null){//this works when users are not in the system
            writerToOutput.append("Some error occurred in see_all_posts_from_user."+ System.lineSeparator());// writing to output
        } else {
            ArrayList<Post> allPostsOfUser2 = userhash.get(userId2).posts;//getting all post of user 2
            for (int i = 0; i<allPostsOfUser2.size();i++){//traversing all posts
                allPostsOfUser2.get(i).whoSaw.add(userhash.get(userId1));//adding user1 to whosaw

            }

            writerToOutput.append(userId1 + " saw all posts of "+ userId2 +"."+ System.lineSeparator());// writing to output
        }


    }

    //pressing the like button
    public void  pressingTheLikeButton(String userId,String postId,HashMap<String,User> userhash,StringBuilder writerToOutput,HashMap<String,Post> posthash){
        if (userhash.get(userId)==null||posthash.get(postId)==null){//this works when users are not in the system or post is not in the system
            writerToOutput.append("Some error occurred in toggle_like."+ System.lineSeparator());// writing to output
        }else {
            if (posthash.get(postId).whoLiked.contains(userhash.get(userId))){//this works when user already liked this post
                posthash.get(postId).whoLiked.remove(userhash.get(userId));//removing to wholiked
                posthash.get(postId).numberOfLikes--;//reducing numbe rof likes
                writerToOutput.append(userId + " unliked "+ postId +"."+ System.lineSeparator());// writing to output

            }else {
                posthash.get(postId).whoLiked.add(userhash.get(userId));//adding to wholiked
                posthash.get(postId).whoSaw.add(userhash.get(userId));//adding to whosaw
                posthash.get(postId).numberOfLikes++;//increasing numbe rof likes
                writerToOutput.append(userId + " liked "+ postId +"."+ System.lineSeparator());// writing to output
            }
        }
    }

    //generating feed
    public void generatingFeed(String userId,int num,HashMap<String,User> userhash,StringBuilder writerToOutput,HashMap<String,Post> posthash,ArrayList<Post> maxlikes){
        Heap heapForFeed = new Heap();//creating heap to sort

        if (userhash.get(userId)==null){//this works when users are not in the system
            writerToOutput.append("Some error occurred in generate_feed."+ System.lineSeparator());// writing to output
        }else {
            writerToOutput.append("Feed for "+userId+":"+ System.lineSeparator());// writing to output
            ArrayList<User> allFollowings = userhash.get(userId).Following.traverse();//getting all followings in an arraylist

            for (User user : allFollowings) {//traversing all followings
                if (user.posts.isEmpty()) {//this works when post is empty

                } else {
                    for (Post post : user.posts) {//traversing all posts of following user
                        if (post.whoSaw.contains(userhash.get(userId))) {//this works when main user already saw the post
                        } else {
                            heapForFeed.add(post);//adding post to heap
                        }
                    }
                }
            }

            for (int i = 0; i < num; i++) {// this for loop for sorting
                if (heapForFeed.isEmpty()){//this works when heap is empty
                    writerToOutput.append("No more posts available for "+userId+"." + System.lineSeparator());// writing to output
                    break;
                } else if (!heapForFeed.isEmpty()){//this works when heap is not empty
                    Post maxlike = heapForFeed.poll();// finding maxliked post
                    maxlikes.add(maxlike);//adding it to maxlikes
                    writerToOutput.append("Post ID: "+maxlike.postID + ", Author: " + maxlike.userId + ", Likes: " + maxlike.numberOfLikes + System.lineSeparator());// writing to output
                }
            }


        }

    }
    //scrolling Through Feed

    public void  scrollingThroughFeed(String userId,int num,int like,HashMap<String,User> userhash,StringBuilder writerToOutput,HashMap<String,Post> posthash,int count,int[] breaker){

        Heap heapForFeed = new Heap();//creating heap to sort

        if (userhash.get(userId)==null){//this works when users are not in the system
            writerToOutput.append("Some error occurred in scroll_through_feed."+ System.lineSeparator());// writing to output
            breaker[0] = 1;//this helps to stopping for loop in main class
        }else {
            ArrayList<User> allFollowings = userhash.get(userId).Following.traverse();//getting all followings in an arraylist

            for (User user : allFollowings) {//traversing all followings
                if (user.posts.isEmpty()) {//this works when post is empty

                } else {
                    for (Post post : user.posts) {//traversing all posts of following user
                        if (post.whoSaw.contains(userhash.get(userId))) {//this works when main user already saw the post

                        } else {
                            heapForFeed.add(post);//adding post to heap
                        }
                    }
                }
            }


        }
        if (breaker[0]!=1&&count==0){//this works when the main method did not break and if it is in the first traverse
            writerToOutput.append(userId+" is scrolling through feed:"+ System.lineSeparator());// writing to output
        }

        if (heapForFeed.isEmpty()){//this works when heap is empty
            if (userhash.get(userId)!=null){//this works when user is in the system
            breaker[0] = 1;//this helps to stopping for loop in main class

            writerToOutput.append("No more posts in feed."+ System.lineSeparator());}// writing to output

        }else if (!heapForFeed.isEmpty()){//this works when heap is not empty

            if (like == 0) {//this works when he does not click the like button
                Post myPost = heapForFeed.poll();//finding max liked post
                myPost.whoSaw.add(userhash.get(userId));//updating whosaw
                writerToOutput.append(userId + " saw " + myPost.postID + " while scrolling." + System.lineSeparator());// writing to output
            }
            if (like == 1) {//this works when he clicks the like button
                Post myPost = heapForFeed.poll();//finding max liked post

                myPost.whoSaw.add(userhash.get(userId));//updating whosaw
                myPost.numberOfLikes++;//updating number of likes
                myPost.whoLiked.add(userhash.get(userId));//updating wholiked

                writerToOutput.append(userId + " saw " + myPost.postID + " while scrolling and clicked the like button." + System.lineSeparator());// writing to output
            }
        }
    }
    //sorting Posts
    public void  sortingPosts(String userId,HashMap<String,User> userhash,StringBuilder writerToOutput,HashMap<String,Post> posthash){
        Heap heapForFeed = new Heap();//creating heap to sort
        if (userhash.get(userId)==null){//this works when user is not in the system
            writerToOutput.append("Some error occurred in sort_posts."+ System.lineSeparator());// writing to output
        }else if (userhash.get(userId).posts.isEmpty()){//this works when post is not in the system
            writerToOutput.append("No posts from"+userId+"."+ System.lineSeparator());// writing to output
        }else {
            writerToOutput.append("Sorting "+userId+"'s posts:"+ System.lineSeparator());// writing to output
            for (Post post: userhash.get(userId).posts){//traversing all posts of user
                heapForFeed.add(post);//adding post to heap
            }
            for (int i = 0 ; i<userhash.get(userId).posts.size();i++){//traversing all posts of user
                Post a = heapForFeed.poll();//finding max liked post of heap
                writerToOutput.append(a.postID+", Likes: "+a.numberOfLikes+ System.lineSeparator());// writing to output
            }
        }
    }
}
