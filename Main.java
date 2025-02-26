import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        // StringBuilder to accumulate output before writing to output file.
        StringBuilder writerToOutput = new StringBuilder();
        // Feed Manager to manage all methods
        Feed_Manager feedManager = new Feed_Manager();
        // Define input file from which commands are read.
        File file = new File(args[0]);
        // Scanner for reading input from the file.
        Scanner scanner = new Scanner(file);

        FileWriter writer1 = new FileWriter(args[1]);
        BufferedWriter writer = new BufferedWriter(writer1);
        HashMap<String,User> userhash = new HashMap<>();
        // HashMap to store users by ID.

        HashMap<String,Post> posthash = new HashMap<>();
        // HashMap to store posts by ID.

        while (scanner.hasNextLine()) {
            try{
                String Line = scanner.next();
                if (Line.equals("create_user")){
                    // Command to create user.
                    String user_ID = scanner.next();
                    feedManager.creating_user(user_ID,userhash,writerToOutput);
                } else if (Line.equals("follow_user")) {
                    // Command to follow user.
                    String user_ID1 = scanner.next();
                    String user_ID2 = scanner.next();
                    feedManager.following(user_ID1,user_ID2,userhash,writerToOutput);
                }else if (Line.equals("unfollow_user")) {
                    // Command to unfollow user.
                    String user_ID1 = scanner.next();
                    String user_ID2 = scanner.next();
                    feedManager.unFollowing(user_ID1,user_ID2,userhash,writerToOutput);
                }else if (Line.equals("create_post")) {
                    // Command to create post.
                    String userId = scanner.next();
                    String postId = scanner.next();
                    String content = scanner.next();
                    feedManager.createPost(userId,postId,content,userhash,writerToOutput,posthash);
                } else if (Line.equals("see_post")) {
                    // Command to see post.
                    String userId = scanner.next();
                    String postId = scanner.next();
                    feedManager.seeingAPost(userId,postId,userhash,writerToOutput,posthash);
                }else if (Line.equals("see_all_posts_from_user")){
                    // Command to see all posts.
                    String viewerId = scanner.next();
                    String viewedId = scanner.next();
                    feedManager.seeingAllPostsOfAUser(viewerId,viewedId,userhash,writerToOutput,posthash);

                }else if (Line.equals("toggle_like")){
                    // Command to toogle like.
                    String userId = scanner.next();
                    String postId = scanner.next();
                    feedManager.pressingTheLikeButton(userId,postId,userhash,writerToOutput,posthash);
                }else if (Line.equals("generate_feed")){
                    // Command to generate feed.
                    String userId = scanner.next();
                    int num = scanner.nextInt();
                    ArrayList<Post> maxlikes = new ArrayList<>();
                    feedManager.generatingFeed(userId,num,userhash,writerToOutput,posthash,maxlikes);

                }else if (Line.equals("scroll_through_feed")){
                    // Command to scroll through feed.
                    String userId = scanner.next();
                    int num = scanner.nextInt();
                    for (int i = 0;i<num;i++){
                        int like = scanner.nextInt();
                        int[] breaker = new int[2];
                        feedManager.scrollingThroughFeed(userId,num,like,userhash,writerToOutput,posthash,i,breaker);
                        if (breaker[0] == 1){
                            break;
                        }
                    }
                }else if (Line.equals("sort_posts")){
                    // Command to sort posts.
                    String userId = scanner.next();
                    feedManager.sortingPosts(userId,userhash,writerToOutput,posthash);
                }

            } catch (NoSuchElementException e) {
            }


        }

        writer.write(writerToOutput.toString());
        writer.flush();
        scanner.close();
        writer.close();
    }

}
