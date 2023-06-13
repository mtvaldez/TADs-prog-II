package entities;


import exceptions.HashTagAlreadyExists;
import exceptions.HashTagDoesNotExist;
import exceptions.UserAlreadyExists;
import exceptions.UserDoesNotExist;
import tads.exceptions.NodeNotFound;
import tads.linkedlist.MyLinkedListImpl;
import tads.linkedlist.MyList;

public class ElTweeter {

    private MyList<User> users = new MyLinkedListImpl();
    private MyList<Tweet> tweets = new MyLinkedListImpl<>();
    private MyList<HashTag> hashTags = new MyLinkedListImpl<>();

    public ElTweeter() {
    }

    public MyList<User> getUsers() {
        return users;
    }

    public void setUsers(MyList users) {
        this.users = users;
    }

    public MyList<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(MyList<Tweet> tweets) {
        this.tweets = tweets;
    }

    public MyList<HashTag> getHashTags() {
        return hashTags;
    }

    public void setHashTags(MyList<HashTag> hashTags) {
        this.hashTags = hashTags;
    }


    public void postTweet(String content, String source, boolean isRetweet, HashTag hashTag, User user) throws UserDoesNotExist, HashTagDoesNotExist {

        if (!users.contains(user)){
            throw new UserDoesNotExist("User does not exist");
        }
        if (!hashTags.contains(hashTag)){
            throw new HashTagDoesNotExist("HashTag does not exist");
        }

        Tweet newTweet = new Tweet((long) (tweets.size()+1), content, source, isRetweet, hashTag, user);
        user.getTweets().add(newTweet);
        hashTag.getTweets().add(newTweet);
        this.tweets.add(newTweet);
    }
    public void addUser(String username, boolean isVerified) throws UserAlreadyExists {
        User newUser = new User(this.users.size()+1, username, isVerified);
        if (users.contains(newUser)){
            throw new UserAlreadyExists("User already exists");
        }
        this.users.add(newUser);

    }
    public void addHashTag(String text) throws HashTagAlreadyExists {
        HashTag newHashTag = new HashTag(this.hashTags.size()+1, text);
        if (hashTags.contains(newHashTag)){
            throw new HashTagAlreadyExists("HashTag already exists");
        }
        this.hashTags.add(newHashTag);
    }


    public MyLinkedListImpl<User> topUsersByTweets() {
        MyLinkedListImpl<User> topUsers = new MyLinkedListImpl<>();
        MyLinkedListImpl<User> clone = this.users.clone();
        while (topUsers.size() < 15){
           User temp = clone.get(0);
           for (int i = 0; i < users.size();i++){
               if (users.get(i).getTweets().size() > temp.getTweets().size()){
                   temp = users.get(i);
               }
           }
           topUsers.add(temp);
           clone.remove(temp);
        }
        return topUsers;
    }



    //    Top 15 usuarios con más tweets. Este listado debe incluir el nombre de usuario, la
//    cantidad de tweets, y si el usuario está verificado o no, ordenado por cantidad de
//    tweets en orden destadcendente. Se espera que esta operación sea de orden n en
//    notación Big O
}
