package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Scanner;

import ir.ac.kntu.Game.Type;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Game> games = new ArrayList<>();
        Store store = new Store();
        store.setGames(games);
        store.setUsers(users);
        User user1 = new User("Mahdi", "12345678");
        User user2 = new User("Ali", "12345678");
        User user3 = new User("Hasan", "12345678");
        User user4 = new User("Akbar", "12345678");
        Game game1 = new Game("Pes", "Football", 50, Type.SPORT);
        Game game2 = new Game("Fifa", "Football", 70, Type.SPORT);
        Game game3 = new Game("god of war", "action", 100, Type.ACTION);
        user4.addFriend(user2);
        user4.addFriend(user1);
        store.addUser(user1);
        store.addUser(user2);
        store.addUser(user3);
        store.addUser(user4);
        store.addGame(game1);
        store.addGame(game2);
        store.addGame(game3);
        Menu menu = new Menu(scanner, store);
        menu.startMenu();
    }
}