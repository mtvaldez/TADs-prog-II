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

        if (!users.contains(user)) {
            throw new UserDoesNotExist("User does not exist");
        }
        if (!hashTags.contains(hashTag)) {
            throw new HashTagDoesNotExist("HashTag does not exist");
        }

        Tweet newTweet = new Tweet((long) (tweets.size() + 1), content, source, isRetweet, hashTag, user);
        user.getTweets().add(newTweet);
        hashTag.getTweets().add(newTweet);
        this.tweets.add(newTweet);
    }

    public void addUser(String username, boolean isVerified) throws UserAlreadyExists {
        User newUser = new User(this.users.size() + 1, username, isVerified);
        if (users.contains(newUser)) {
            throw new UserAlreadyExists("User already exists");
        }
        this.users.add(newUser);

    }

    public void addHashTag(String text) throws HashTagAlreadyExists {
        HashTag newHashTag = new HashTag(this.hashTags.size() + 1, text);
        if (hashTags.contains(newHashTag)) {
            throw new HashTagAlreadyExists("HashTag already exists");
        }
        this.hashTags.add(newHashTag);
    }

    /* Consulta 1
    Listar los 10 pilotos activos en la temporada 2023 más mencionados en los tweets
    en un mes (este mes será ingresado como 2 parámetros separados, mes y año, por
            consola). Este listado debe incluir el nombre de los pilotos y la cantidad de
    ocurrencias para cada uno de manera ordenada. Se espera que esta operación sea
    de orden n en notación Big O.*/
    public toptweetes(int mes, int año) {
        MyLinkedListImpl<User> Corredoresf1 = new MyLinkedListImpl<>("Max Verstappen", "Sergio Pérez", "Charles Leclerc", "Carlos Sainz", "Lewis Hamilton", "George Russell", "Fernando Alonso", "Lance Stroll", "Lando Norris", "Oscar Piastri", "Pierre Gasly", "Esteban Ocon", "Nyck de Vries", "Yuki Tsunoda", "Alexander Albon", "Logan Sargeant", "Guanyu Zhou", "Valtteri Bottas", "Nico Hülkenberg", "Kevin Magnussen", );
        MyLinkedListImpl<User> clone = this.users.clone();
        MyBSTimpl aux = new MyBSTimpl();
        for (int i = 0; i < clone.size(); i++) {
            if (clone.gettweet(i).getdate().getmonth() == mes && clone.gettweet.getdate().getyear() == año) {
                MyBSTimpl aux = add(Corredoresf1.get(i), tweetsWithWord(Corredoresf1.get(i)));
            }
        }
        for (int i; i < 10; i++) {
            System.out.println(findmaxlefttree(aux));
            aux = delete(findmaxlefttree(aux));
        }
    }

    //  Consulta 2
    public MyLinkedListImpl<User> topUsersByTweets() {
        MyLinkedListImpl<User> topUsers = new MyLinkedListImpl<>();
        MyLinkedListImpl<User> clone = this.users.clone();
        while (topUsers.size() < 15 && clone.size() > 0) {
            User temp = clone.get(0);
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getTweets().size() > temp.getTweets().size()) {
                    temp = users.get(i);
                }
            }
            topUsers.add(temp);
            clone.remove(temp);
        }
        return topUsers;
    }

    // Consulta 3
    public

    // Consulta 4
    public MyLinkedListImpl<User> topUsersByFavorite() {
        MyLinkedListImpl<User> topUsers = new MyLinkedListImpl<>();
        MyLinkedListImpl<User> clone = this.users.clone();
        while (topUsers.size() < 7 && clone.size() > 0) {
            User temp = clone.get(0);
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getFavorites() > temp.getFavorites()) {
                    temp = users.get(i);
                }
            }
            topUsers.add(temp);
            clone.remove(temp);
        }
        return topUsers;
    }

    // Consulta 5
    public int tweetsWithWord(String word) {
        int count = 0;
        for (int i = 0; i < tweets.size(); i++) {
            if (tweets.get(i).getContent().contains(word)) {
                count++;
            }
        }
        return count;
    }

// Consulta 6
    public
}
