package entities;

import java.time.LocalDateTime;

public class Tweet {

    private long id;
    private String content;
    private String source;
    private boolean isRetweet;
    private HashTag hashTag;
    private User user;
    private  LocalDateTime date;


    public Tweet(Long id, String content, String source, boolean isRetweet, HashTag hashTag, User user) {
        this.id = id;
        this.content = content;
        this.source = source;
        this.isRetweet = isRetweet;
        this.hashTag = hashTag;
        this.user = user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HashTag getHashTag() {
        return hashTag;
    }

    public void setHashTag(HashTag hashTag) {
        this.hashTag = hashTag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isRetweet() {
        return isRetweet;
    }

    public void setRetweet(boolean retweet) {
        isRetweet = retweet;
    }

    @Override
    public boolean equals(Object obj) {
        boolean iguales = false;
        if (this.getClass() == obj.getClass()) {
            if (this.id == ((Tweet) obj).id){
                iguales = true;
            }
        }
        return iguales;
    }

}
