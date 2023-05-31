package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private String userName;
    private String password;
    private String phoneNumber;
    private String email;
    private ArrayList<Game> games = new ArrayList<>();
    private ArrayList<User> friends = new ArrayList<>();
    private ArrayList<User> requests = new ArrayList<>();
    private Wallet wallet = new Wallet();;
    private Integer id;
    private static int nextId = 1;
    private Scanner scanner = new Scanner(System.in);
    private boolean isUserCreat = false;
    private static String input;
    private static ArrayList<User> filteredUsers = new ArrayList<>();

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.phoneNumber = null;
        this.email = null;
        this.id = nextId;
        nextId++;
    }

    public User() {
        this.id = nextId;
        nextId++;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public void setId(Integer id) {
        this.id = id + 1;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public Integer getId() {
        return id;
    }

    public void addFriend(User user) {
        friends.add(user);
        this.friends.add(user);
    }

    public void addRequest(User user) {
        requests.add(user);
    }

    public void removeRequest(User user) {
        requests.remove(user);
    }

    @Override
    public String toString() {

        return ("ID : " + this.getId() + "  Username : " + this.getUserName());
    }

    public void showInfo() {
        System.out.println("Username : " + getUserName());
        System.out.println("Password : " + getPassword());
        System.out.println("Email : " + getEmail());
        System.out.println("Phone Number : " + getPhoneNumber());
        System.out.println("Wallet's balance : " + getWallet().getBalance() + "\n");
    }

    public boolean creatUser(Store store) {
        isUserCreat = false;
        setNewUserName(store);
        if (isUserCreat) {
            store.addUser(this);
        }
        return isUserCreat;
    }

    public void setNewUserName(Store store) {
        do {
            System.out.println("Enter your name : ");
            System.out.println("\n0. Go back to previous menu\n");
            String userName = scanner.nextLine();
            System.out.println("---------------");
            if (userName.equals("0")) {
                System.out.println("Returning to previous menu...");
                break;
            }
            if (Utilities.isValidUserName(userName)) {
                if (Utilities.isUniqueUserName(userName, store.getUsers())) {
                    setUserName(userName);
                    setNewPassword(store);
                } else {
                    System.out.println("this username is already taken. ");
                    continue;
                }
            } else {
                System.out.println("Invalid Username. Please enter another username.");
                continue;
            }
        } while (!isUserCreat);
    }

    public void setNewPassword(Store store) {
        while (!isUserCreat) {
            System.out.println("Enter your password : ");
            System.out.println("\n0. Go back to previous menu\n");
            String newPassword = scanner.nextLine();
            System.out.println("---------------");
            if (newPassword.equals("0")) {
                System.out.println("Returning to previous menu...");
                break;
            }
            if (Utilities.isValidPassword(newPassword)) {
                setPassword(newPassword);
                setNewEmail(store);
            } else {
                System.out.println("Invalid password. Please enter another password.");
                continue;
            }
        }
    }

    public void setNewEmail(Store store) {
        while (!isUserCreat) {
            System.out.println("Enter your email : ");
            System.out.println("\n0. Go back to previous menu\n");
            String newEmail = scanner.nextLine();
            System.out.println("---------------");
            if (newEmail.equals("0")) {
                System.out.println("Returning to previous menu...");
                break;
            }
            if (Utilities.isValidEmail(newEmail)) {
                setEmail(newEmail);
                setNewPhoneNumber(store);
            } else {
                System.out.println("Invalid phone Email. Please check it.");
                continue;
            }

        }
    }

    public void setNewPhoneNumber(Store store) {
        while (!isUserCreat) {
            System.out.println("Enter your phone number : ");
            System.out.println("\n0. Go back to previous menu\n");
            String newPhoneNumber = scanner.nextLine();
            System.out.println("---------------");
            if (newPhoneNumber.equals("0")) {
                System.out.println("Returning to previous menu...");
                break;
            }
            if (Utilities.isValidPhoneNumber(newPhoneNumber)) {
                setPhoneNumber(newPhoneNumber);
                Wallet wallet = new Wallet();
                setWallet(wallet);

                isUserCreat = true;
            } else {
                System.out.println("Invalid phone number. Please check it.");
                continue;
            }
        }
    }

    public User loginUser(Store store) {
        User user = null;
        do {
            System.out.println("Enter your username : ");
            System.out.println("\n0. Go back to previous menu\n");
            String userName = scanner.nextLine();
            System.out.println("---------------");
            if (userName.equals("0")) {
                System.out.println("Returning to previous menu...");
                break;
            }
            if (!Utilities.isValidUserName(userName)) {
                System.out.println("Invalid Username.");
                continue;
            }

            if (null != Utilities.findUserName(userName, store.getUsers())) {
                boolean isCorrectPassword = enterPassword(Utilities.findUserName(userName, store.getUsers()));
                if (isCorrectPassword) {
                    System.out.println("your password is correct! ");
                    user = Utilities.findUserName(userName, store.getUsers());
                    return user;
                }
            }
            if (user == null) {
                System.out.println("this username does not exist! ");
                continue;
            }

        } while (true);
        return user;
    }

    public boolean enterPassword(User user) {
        while (true) {
            System.out.println("Enter your password : ");
            System.out.println("\n0. Go back to previous menu\n");
            String password = scanner.nextLine();
            System.out.println("---------------");

            if (password.equals("0")) {
                System.out.println("Returning to previous menu...");
                break;
            }
            if (user.getPassword().equals(password)) {
                return true;

            } else {
                System.out.println("your password is not correct! ");
                continue;
            }
        }
        return false;
    }

    public void showFriends() {
        for (User friend : friends) {
            System.out.println(friend.toString());
        }
    }

    public User findFriendByID(Integer ID) {
        for (User friend : friends) {
            if (friend.getId().equals(ID)) {
                return friend;
            }
        }
        return null;
    }

    public static User findByID(Integer ID, Store store) {
        for (User user : store.getUsers()) {
            if (user.getId().equals(ID)) {
                return user;
            }
        }
        return null;
    }

    
    public User findByUserNAme(String userNAme) {
        for (User friend : friends) {
            if (friend.getUserName().equals(userNAme)) {
                return friend;
            }
        }
        return null;
    }

    public static ArrayList<User> findGameByUserName(Scanner scanner, ArrayList<User> users) {
        System.out.println("Enter name.");
        input = scanner.nextLine();
        filteredUsers = new ArrayList<>();
        System.out.println("---------------");
        for (User user : users) {
            if (user.getUserName().startsWith(input)) {
                filteredUsers.add(user);
            }
        }
        return filteredUsers;

    }

    public void showRequests() {
        ArrayList<User> newFriends = new ArrayList<>();
        for (User request : requests) {
            System.out.println(request.getUserName() + " sent a friend request, do you want to accept it? ");
            System.out.println("0. NO ");
            System.out.println("1. Yes ");
            System.out.println("2. later");
            input = scanner.nextLine();
            System.out.println("---------------");
            switch (input) {
                case "0":
                    newFriends.add(request);
                    break;
                case "1":
                    newFriends.add(request);
                    addFriend(request);
                    break;
                case "2":
                    break;
                default:
                    System.out.println("invalid choose! ");
                    break;
            }

        }
        for (User friend : newFriends) {
            removeRequest(friend);
        }
    }

    public void buyGame(Game game) {
        if (this.getGames().contains(game)) {
            System.out.println("this game already purchase! ");
            return;
        }
        System.out.println("Do you want to buy it? ");
        System.out.println("0. No");
        System.out.println("1. Yes");
        input = scanner.nextLine();
        System.out.println("---------------");
        if (input.equals("0")) {
            return;
        }
        if (input.equals("1")) {
            if (game.getPrice() > getWallet().getBalance()) {
                System.out.println("Your balance is not enough! ");
            } else {
                games.add(game);
                game.addToComuunity(this);
                System.out.println("the game buy succesfully. ");
            }
        } else {
            System.out.println("invalid choose! ");
        }
    }

    public void seeGameInLibrary(Game game) {
        System.out.println("Do you want to buy it? ");
        System.out.println("0. No");
        System.out.println("1. Yes");
        input = scanner.nextLine();
        System.out.println("---------------");
        if (input.equals("0")) {
            return;
        }
        if (input.equals("1")) {
            if (game.getPrice() > getWallet().getBalance()) {
                System.out.println("Your balance is not enough! ");
            } else {
                games.add(game);
                game.addToComuunity(this);
                System.out.println("the game buy succesfully. ");
            }
        } else {
            System.out.println("invalid choose! ");
        }
    }

}
