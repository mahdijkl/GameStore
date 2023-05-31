package ir.ac.kntu;

import java.util.Scanner;
import java.util.ArrayList;

public class DashboardUser {
    private User user;
    private Game game;
    private Store store;

    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Game> gameList = null;

    public DashboardUser(Store store) {
        this.store = store;
    }

    public void logInUserMenu() {
        String input;
        outerloop: do {
            System.out.println("1. Sign up ");
            System.out.println("2. Sign in ");
            System.out.println("\n0. Go back to previous menu\n");

            input = scanner.nextLine();
            System.out.println("---------------");
            switch (input) {
                case "0":
                    System.out.println("Returning to previous menu...");
                    break outerloop;
                case "1":
                    signUpMenu();
                    break;
                case "2":
                    signInMenu();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (true);
    }

    public void signInMenu() {
        user = new User();
        user = user.loginUser(store);
        if (user != null) {
            userMenu(user);
        }

    }

    public void signUpMenu() {
        user = new User();
        boolean isUserCreat = user.creatUser(store);
        if (isUserCreat) {
            userMenu(user);
        }
    }

    public void userMenu(User user) {
        String input;
        outerloop: do {
            System.out.println("1. profile ");
            System.out.println("2. store ");
            System.out.println("3. library ");
            System.out.println("4. friends ");
            System.out.println("\n0. Go back to previous menu\n");
            input = scanner.nextLine();
            System.out.println("---------------");
            switch (input) {
                case "0":
                    System.out.println("Returning to previous menu...");
                    break outerloop;
                case "1":
                    profileMenu(user);
                    break;
                case "2":
                    storeMenu(user);
                    break;
                case "3":
                    libraryMenu(user);
                    break;
                case "4":
                    friendsMenu(user);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (true);
    }

    public void storeMenu(User user) {
        String input;
        outerloop: do {
            System.out.println("1. Show all game ");
            System.out.println("2. search ");
            System.out.println("3. Filter by price ");

            System.out.println("\n0. Go back to previous menu\n");
            input = scanner.nextLine();
            System.out.println("---------------");
            switch (input) {
                case "0":
                    System.out.println("Returning to previous menu...");
                    break outerloop;
                case "1":
                    System.out.println(store.getGames());
                    game = Game.findGameById(scanner, store.getGames());
                    if (game != null) {
                        game.showAllInfo();
                        user.buyGame(game);
                        break outerloop;
                    } else {
                        System.out.println("the ID does not exist! ");
                    }
                    break;
                case "2":
                    gameList = Game.findGameByUserName(scanner, store.getGames());
                    if (gameList.size() == 0) {
                        System.out.println("any game does not exist! ");
                        break;
                    }
                    System.out.println(gameList);
                    game = Game.findGameById(scanner, gameList);
                    if (game != null) {
                        game.showAllInfo();
                        user.buyGame(game);
                        break outerloop;
                    } else {
                        System.out.println("the ID does not exist! ");
                    }
                    break;
                case "3":
                    System.out.println(Game.filterByPrice(scanner, store.getGames()));
                    game = Game.findGameById(scanner, store.getGames());
                    if (game != null) {
                        game.showAllInfo();
                        user.buyGame(game);
                        break outerloop;
                    } else {
                        System.out.println("the ID does not exist! ");
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (true);

    }

    public void libraryMenu(User user) {
        String input;
        outerloop: do {
            System.out.println("1. Show all your game ");
            System.out.println("2. search ");
            System.out.println("3. Filter by price ");

            System.out.println("\n0. Go back to previous menu\n");
            input = scanner.nextLine();
            System.out.println("---------------");
            switch (input) {
                case "0":
                    System.out.println("Returning to previous menu...");
                    break outerloop;
                case "1":
                    System.out.println(user.getGames());
                    game = Game.findGameById(scanner, user.getGames());
                    if (game != null) {
                        game.showAllInfo();
                        game.showOption(user);
                        break outerloop;
                    } else {
                        System.out.println("the ID does not exist! ");
                    }
                    break;
                case "2":
                    gameList = Game.findGameByUserName(scanner, user.getGames());
                    if (gameList.size() == 0) {
                        System.out.println("any game does not exist! ");
                        break;
                    }
                    System.out.println(gameList);
                    game = Game.findGameById(scanner, gameList);
                    if (game != null) {
                        game.showAllInfo();
                        user.buyGame(game);
                        break outerloop;
                    } else {
                        System.out.println("the ID does not exist! ");
                    }
                    break;
                case "3":
                    System.out.println(Game.filterByPrice(scanner, user.getGames()));
                    game = Game.findGameById(scanner, user.getGames());
                    if (game != null) {
                        game.showAllInfo();
                        user.buyGame(game);
                        break outerloop;
                    } else {
                        System.out.println("the ID does not exist! ");
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (true);
    }

    public void friendsMenu(User user) {
        String input;
        outerloop: do {
            System.out.println("1. show all friends ");
            System.out.println("2. search ");
            System.out.println("3. find new friend ");
            System.out.println("4. requests ");
            System.out.println("\n0. Go back to previous menu\n");
            input = scanner.nextLine();
            System.out.println("---------------");
            switch (input) {
                case "0":
                    System.out.println("Returning to previous menu...");
                    break outerloop;
                case "1":
                    user.showFriends();
                    System.out.println("Enter friend's ID to see their game. ");
                    input = scanner.nextLine();
                    System.out.println("---------------");
                    if (user.findFriendByID(Integer.parseInt(input)) == null) {
                        System.out.println("this ID does not your friend! ");
                        break;
                    }
                    System.out.println(user.findFriendByID(Integer.parseInt(input)).getGames());
                    break;
                case "2":
                    System.out.println("Enter friend's UserName to see their game. ");
                    input = scanner.nextLine();
                    System.out.println("---------------");
                    if (user.findByUserNAme(input) == null) {
                        System.out.println("this ,UserName does not your friend! ");
                        break;
                    }
                    System.out.println(user.findByUserNAme(input).getGames());
                case "3":
                    System.out.println("Enter the Username that you want to sent to it Request. ");
                    input = scanner.nextLine();
                    System.out.println("---------------");
                    User friend = Utilities.findUserName(input, store.getUsers());
                    if (friend != null) {
                        friend.addRequest(user);
                    }
                    break;
                case "4":
                    user.showRequests();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (true);

    }

    public void profileMenu(User user) {
        String input;
        outerloop: do {
            user.showInfo();
            System.out.println("1. change  Username ");
            System.out.println("2. change  Password ");
            System.out.println("3. change  Email ");
            System.out.println("4. change  PhoneNumber ");
            System.out.println("5. deposit Wallet ");
            System.out.println("\n0. Go back to previous menu\n");
            input = scanner.nextLine();
            System.out.println("---------------");
            switch (input) {
                case "0":
                    System.out.println("Returning to previous menu...");
                    break outerloop;
                case "1":
                    System.out.println("Please enter new username");
                    System.out.println("\n0. Go back to previous menu\n");
                    input = scanner.nextLine();
                    System.out.println("---------------");
                    if (input.equals("0")) {
                        break;
                    }
                    if (!Utilities.isValidUserName(input)) {
                        System.out.println("Invalid Username. ");
                        break;
                    }
                    user.setUserName(input);
                    System.out.println("username changed succesfully. ");
                    break;
                case "2":
                    System.out.println("Please enter new Password");
                    System.out.println("\n0. Go back to previous menu\n");
                    input = scanner.nextLine();
                    System.out.println("---------------");
                    if (input.equals("0")) {
                        break;
                    }
                    if (!Utilities.isValidPassword(input)) {
                        System.out.println("Invalid password. ");
                        break;
                    }
                    user.setPassword(input);
                    System.out.println("Password changed succesfully. ");
                    break;
                case "3":
                    System.out.println("Please enter new Email");
                    System.out.println("\n0. Go back to previous menu\n");
                    input = scanner.nextLine();
                    System.out.println("---------------");
                    if (input.equals("0")) {
                        break;
                    }
                    if (!Utilities.isValidEmail(input)) {
                        System.out.println("Invalid Email. ");
                        break;
                    }
                    user.setEmail(input);
                    System.out.println("Email changed succesfully. ");
                    break;
                case "4":
                    System.out.println("Please enter new Phone number");
                    System.out.println("\n0. Go back to previous menu\n");
                    input = scanner.nextLine();
                    System.out.println("---------------");
                    if (input.equals("0")) {
                        break;
                    }
                    if (!Utilities.isValidPhoneNumber(input)) {
                        System.out.println("Invalid Phone number. ");
                        break;
                    }
                    user.setPhoneNumber(input);
                    System.out.println("phone number changed succesfully. ");
                    break;
                case "5":
                    System.out.println("Enter the amount you want to deposit : ");
                    input = scanner.nextLine();
                    System.out.println("---------------");
                    user.getWallet().deposit(Double.parseDouble(input));
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (true);
    }

}
