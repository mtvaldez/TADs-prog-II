package entities;

import tads.linkedlist.MyLinkedListImpl;
import tads.linkedlist.MyList;

public class HashTag {

    private long id;
    private String text;
    private MyList<Tweet> tweets;


    public MyList<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(MyList<Tweet> tweets) {
        this.tweets = tweets;
    }

    public HashTag(long id, String text) {
        this.id = id;
        this.text = text;
        this.tweets = new MyLinkedListImpl<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
