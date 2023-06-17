package entities;


import exceptions.HashTagAlreadyExists;
import exceptions.HashTagDoesNotExist;
import exceptions.UserAlreadyExists;
import exceptions.UserDoesNotExist;
import tads.BinarySearchTree.NodeBST;
import tads.exceptions.NodeAlreadyExists;
import tads.linkedlist.MyLinkedListImpl;
import tads.linkedlist.MyList;
import tads.BinarySearchTree.MyBST;
import tads.BinarySearchTree.MyBSTImpl;

public class ElTweeter {

    private MyList<User> users = new MyLinkedListImpl();
    private MyList<Tweet> tweets = new MyLinkedListImpl<>();
    private MyList<HashTag> hashTags = new MyLinkedListImpl<>();
    private MyList<String> drivers = new MyLinkedListImpl<>();


    public ElTweeter() {
        String[] conduccionistas = {"Max Verstappen", "Sergio Pérez", "Charles Leclerc", "Carlos Sainz", "Lewis Hamilton", "George Russell", "Fernando Alonso", "Lance Stroll", "Lando Norris", "Oscar Piastri", "Pierre Gasly", "Esteban Ocon", "Nyck de Vries", "Yuki Tsunoda", "Alexander Albon", "Logan Sargeant", "Guanyu Zhou", "Valtteri Bottas", "Nico Hülkenberg", "Kevin Magnussen"};
        drivers.addAll(conduccionistas);
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



    // Consulta 1
    public MyList topMencionesPilots(int mes, int año) {
        boolean mismaTweetCount = false;
        MyList<Tweet> tuitsGuenos = new MyLinkedListImpl<>();
        MyBST<Integer,MyList<String>>  enlistamealospibes = new MyBSTImpl(null);

        for (int i = 0; i < this.tweets.size(); i++) {
            if (this.tweets.get(i).getDate().getMonthValue() == mes && this.tweets.get(i).getDate().getYear() == año) {
                tuitsGuenos.add(this.tweets.get(i));
            }
        }

        for (int i = 0; i < drivers.size(); i++) {
            int tweetCount = tweetsWithWord(drivers.get(i));

            MyList<String> palabraIdentifcadoraDePersonas = new MyLinkedListImpl<>();
            palabraIdentifcadoraDePersonas.add(drivers.get(i));

            try {
                enlistamealospibes.insert(tweetCount, palabraIdentifcadoraDePersonas);
            } catch (NodeAlreadyExists e) {
                MyList<String> aux = (MyList<String>) enlistamealospibes.findNode(tweetCount, enlistamealospibes.getRoot()).getData();
                aux.add(drivers.get(i));
                mismaTweetCount = true;
            }

        }

        MyList topDrivers = enlistamealospibes.inOrder();
        MyList result = new MyLinkedListImpl();

        if (!mismaTweetCount){
            while (topDrivers.size() > 10) {
                topDrivers.remove(topDrivers.get(0));
            }
            result = topDrivers;
        }else {
            int repetidos = 10;
            while (result.size() < repetidos) {
                NodeBST<Integer, MyList<String>> ultimo = (NodeBST<Integer, MyList<String>>) topDrivers.get(topDrivers.size() - 1);
                repetidos -= ultimo.getData().size();
                result.add(ultimo);
                topDrivers.remove(topDrivers.get(topDrivers.size() - 1));
            }
        }

        return result;
    }


    //  Consulta 2
    public MyLinkedListImpl<User> topUsersByTweets() {
        MyLinkedListImpl<User> topUsers = new MyLinkedListImpl<>();
        MyLinkedListImpl<User> clone = this.users.clone();
        while (topUsers.size() < 15 && clone.size() > 0){
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


    // Consulta 3
    public int hashtagsUsadosEnUnDia(int YYYY, int MM, int DD){

        MyList<HashTag> hashtagsUsados = new MyLinkedListImpl<>();
        int count = 0;

        for (int i = 0; i < this.tweets.size(); i++) {

            if (this.tweets.get(i).getDate().getMonthValue() == MM && this.tweets.get(i).getDate().getYear() == YYYY && this.tweets.get(i).getDate().getDayOfMonth() == DD) {

                if (!hashtagsUsados.contains(this.tweets.get(i).getHashTag())){

                    hashtagsUsados.add(this.tweets.get(i).getHashTag());
                    count++;
                }

            }
        }
        return count;
    }


    // Consulta 4
    public HashTag hashTagMasUsadoEnUnDia(int YYYY, int MM, int DD){
        MyList<HashTag> hashtagsUsadosClone = this.hashTags.clone();
        for (int i = 0; i < this.tweets.size(); i++) {
            if (this.tweets.get(i).getDate().getMonthValue() == MM && this.tweets.get(i).getDate().getYear() == YYYY && this.tweets.get(i).getDate().getDayOfMonth() == DD) {
                tweets.get(i).getHashTag().setCount(tweets.get(i).getHashTag().getCount()+1);
            }
        }

        if (hashtagsUsadosClone.get(0).getText() == "#f1"){
            hashtagsUsadosClone.remove(hashtagsUsadosClone.get(0));
        }

        HashTag temp = hashtagsUsadosClone.get(0);
        for (int i = 0; i < hashtagsUsadosClone.size(); i++) {

            if (hashtagsUsadosClone.get(i).getText() == "#f1"){
                hashtagsUsadosClone.remove(hashtagsUsadosClone.get(i));
            }

            if (hashtagsUsadosClone.get(i).getCount() > temp.getCount()){
                temp = hashtagsUsadosClone.get(i);
            }
        }
        return temp;
    }


    // Consulta 5
    public MyLinkedListImpl<User> topUsersByFavorite() {
        MyLinkedListImpl<User> topUsers = new MyLinkedListImpl<>();
        MyLinkedListImpl<User> clone = this.users.clone();
        while (topUsers.size() < 7 && clone.size() > 0){
            User temp = clone.get(0);
            for (int i = 0; i < users.size();i++){
                if (users.get(i).getFavorites() > temp.getFavorites()){
                    temp = users.get(i);
                }
            }
            topUsers.add(temp);
            clone.remove(temp);
        }
        return topUsers;
    }


    // Consulta 6
    public int tweetsWithWord(String word) {
        int count = 0;
        for (int i = 0; i < tweets.size(); i++){
            if (tweets.get(i).getContent().contains(word)){
                count++;
            }
        }
        return count;
    }



}
