# CMPE-Instagram-Feed
## Project Overview
The goal of this project is to develop a Java-based Feed Manager for an early version of Instagram. The system should track users, posts, likes, and feeds while efficiently handling user interactions and sorting content based on popularity.

## Project Scope 
- User Management : Users can be created, follow/unfollow others, and interact with posts.
- Post Management: Users can create posts and like/unlike existing posts.
- Feed Generation: Users receive a personalized feed based on their followed accounts and post interactions.
- Sorting Mechanism: Posts are sorted by the number of likes and lexicographical order.

## Key Features
1. User Operations:
- Create users with unique IDs.
- Follow and unfollow other users.

2. Post Operations:
- Create posts with unique IDs and content.
- Like/unlike posts; liking a post also marks it as "seen."

3. Feed Generation:
- Generate a ranked list of posts based on likes.
- Ensure feeds only include unseen posts from followed users.

4. Sorting:
- Posts are sorted by likes (descending).
- If likes are equal, sort by lexicographical order of post IDs.

##  Technical Requirements
- Language: Java
- Performance Constraints:
    - Execution times are measured on Intel i7-11800H (32GB RAM).
    - Must handle up to 500,000 users and 350,000 posts efficiently.
 
 ## How to Run
 Compile the code:

javac *.java

Run the program with input/output files:

java Main <land_file> <travel_time_file> <mission_file>
