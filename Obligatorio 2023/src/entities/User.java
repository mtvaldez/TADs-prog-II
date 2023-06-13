package entities;

import tads.linkedlist.MyLinkedListImpl;
import tads.linkedlist.MyList;

public class User {

    private long id;
    private String name;
    private MyList<Tweet> tweets = new MyLinkedListImpl<>();;
    private boolean isVerified;


    public User(long id, String name, boolean isVerified) {
        this.id = id;
        this.name = name;
        this.isVerified = isVerified;
    }

    public MyList<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(MyList<Tweet> tweets) {
        this.tweets = tweets;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        boolean iguales = false;
        if (this.getClass() == obj.getClass()) {
            if (this.id == ((User) obj).id) {
                iguales = true;
            }
        }
        return iguales;
    }
}
