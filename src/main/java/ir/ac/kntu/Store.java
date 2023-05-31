package ir.ac.kntu;

import java.util.ArrayList;

public class Store {
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Game> games = new ArrayList<>();

    final String ADMIN_USERNAME = "admin";
    final String ADMIN_PASSWORD = "admin";
    private User admin = new User(ADMIN_USERNAME, ADMIN_PASSWORD);


    public User getAdmin() {
        return admin;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public void removeGame(Game game) {
        games.remove(game);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);

    }


}
