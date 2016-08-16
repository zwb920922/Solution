import java.util.*;

public class Twitter {

    private int timer = 0;

    private Map<Integer, User> map;

    private class Tweet {
        int ID;
        int time;
        Tweet next;
        Tweet(int i) { ID = i; time = timer++; }
    }

    private class User {
        int ID;
        Tweet tweet;
        Set<User> followee;
        User(int i) {
            ID = i;
            followee = new HashSet<>();
            follow(this);
        }
        public void follow (User u) {
            followee.add(u);
        }
        public void unfollow (User u) {
            followee.remove(u);
        }
        public void post(int id) {
            Tweet tmp = new Tweet(id);
            tmp.next = tweet;
            tweet = tmp;
        }
    }


    /** Initialize your data structure here. */
    public Twitter() {
        map = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!map.containsKey(userId)) {
            map.put(userId, new User(userId));
        }
        map.get(userId).post(tweetId);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> ret = new ArrayList<>();
        if (!map.containsKey(userId)) return ret;
        User u = map.get(userId);
        PriorityQueue<Tweet> q = new PriorityQueue<>(u.followee.size(), (a, b) -> (b.time - a.time));
        for (User usr: u.followee) {
            if (usr.tweet != null)
                q.add(usr.tweet);
        }
        for (int i = 0; i < 10 && !q.isEmpty(); i++) {
            Tweet t = q.poll();
            ret.add(t.ID);
            if (t.next != null)
                q.add(t.next);
        }
        return ret;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(!map.containsKey(followerId)){
            User u = new User(followerId);
            map.put(followerId, u);
        }
        if(!map.containsKey(followeeId)){
            User u = new User(followeeId);
            map.put(followeeId, u);
        }
        map.get(followerId).follow(map.get(followeeId));
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(!map.containsKey(followerId) || followerId==followeeId)
            return;
        map.get(followerId).unfollow(map.get(followeeId));
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */