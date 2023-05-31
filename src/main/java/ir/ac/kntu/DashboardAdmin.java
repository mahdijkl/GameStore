package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Scanner;

public class DashboardAdmin {
    private Scanner scanner = new Scanner(System.in);
    private Store store;
    private Game game;
    private User user;
    private User admin;
    private DashboardUser dashboardUser = new DashboardUser(store);
    private ArrayList<Game> gameList = new ArrayList<>();
    private ArrayList<User> userList = new ArrayList<>();

    public DashboardAdmin(Store store) {
        this.store = store;
    }

    public void logInAdminMenu() {
        String input;
        admin = store.getAdmin();
        do {
            System.out.println("\n0. Go back to previous menu\n");
            System.out.println("Please  enter your username:");
            input = scanner.nextLine();
            System.out.println("---------------");
            if (input.equals("0")) {
                System.out.println("Returning to previous menu...");
                break;
            } else if (admin.getUserName().equals(input)) {
                do {
                    System.out.println("\n0. Go back to previous menu\n");
                    System.out.println("Please  enter your password: ");
                    input = scanner.nextLine();
                    System.out.println("---------------");
                    if (input.equals("0")) {
                        System.out.println("Returning to previous menu...");
                        break;
                    }
                    if (admin.getPassword().equals(input)) {
                        adminMenu();
                    } else {
                        System.out.println("Invalid password ! \ntry again. ");
                    }
                } while (true);
            } else {
                System.out.println("Invalid username ! \ntry again. ");
            }
        } while (true);
    }

    public void adminMenu() {
        String input;
        outerloop: do {
            System.out.println("1. Users ");
            System.out.println("2. Games ");
            System.out.println("\n0. Go back to previous menu\n");
            input = scanner.nextLine();
            System.out.println("---------------");
            switch (input) {
                case "0":
                    System.out.println("Returning to previous menu...");
                    break outerloop;
                case "1":
                    handleTheUsers();
                    break;
                case "2":
                    handleTheGames();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (true);
    }

    public void handleTheGames() {
        String input;
        outerloop: do {
            System.out.println("1. creat new game ");
            System.out.println("2. change a game ");
            System.out.println("3. remove a game ");
            System.out.println("4. see information of a game ");
            System.out.println("5. Search ");
            System.out.println("\n0. Go back to previous menu\n");
            input = scanner.nextLine();
            System.out.println("---------------");
            switch (input) {
                case "0":
                    System.out.println("Returning to previous menu...");
                    break outerloop;
                case "1":
                    game = Game.creatGame();
                    if (game != null) {
                        store.addGame(game);
                    }
                    break;
                case "2":
                    System.out.println(store.getGames());
                    System.out.println("Enter game's ID that you want to change. ");
                    input = scanner.nextLine();
                    System.out.println("---------------");
                    game = Game.findGameById(scanner, store.getGames());
                    if (game != null) {
                        game.changeInfo();
                        break;
                    }
                    System.out.println("the ID does not exist! ");
                    break;
                case "3":
                    System.out.println(store.getGames());
                    System.out.println("Enter game's ID that you want to remove. ");
                    input = scanner.nextLine();
                    System.out.println("---------------");
                    game = Game.findGameById(scanner, store.getGames());
                    if (game != null) {
                        store.removeGame(game);
                        break;
                    }
                    System.out.println("the ID does not exist! ");
                    break;
                case "4":
                    System.out.println(store.getGames());
                    input = scanner.nextLine();
                    System.out.println("---------------");
                    game = Game.findGameById(scanner, store.getGames());
                    if (game != null) {
                        game.showAllInfo();
                        break;
                    }
                    System.out.println("the ID does not exist! ");
                    break;

                case "5":
                    gameList = Game.findGameByUserName(scanner, store.getGames());
                    if (gameList.size() == 0) {
                        System.out.println("any game does not exist! ");
                        break;
                    }
                    System.out.println(gameList);
                    game = Game.findGameById(scanner, gameList);
                    if (game != null) {
                        System.out.println("1. Show info ");
                        System.out.println("2. change game ");
                        System.out.println("3. remove game ");
                        input = scanner.nextLine();
                        System.out.println("---------------");
                        if (input.equals("1"))
                            game.showAllInfo();
                        if (input.equals("2"))
                            game.changeInfo();
                        if (input.equals("3"))
                            store.removeGame(game);
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

    public void handleTheUsers() {
        String input;
        outerloop: do {
            System.out.println("1. creat new user ");
            System.out.println("2. change a user ");
            System.out.println("3. remove a user ");
            System.out.println("4. see information of a user ");
            System.out.println("5. Search ");

            System.out.println("\n0. Go back to previous menu\n");
            input = scanner.nextLine();
            System.out.println("---------------");
            switch (input) {
                case "0":
                    System.out.println("Returning to previous menu...");
                    break outerloop;
                case "1":
                    user = new User();
                    user.creatUser(store);
                    break;
                case "2":
                    System.out.println(store.getUsers());
                    System.out.println("Enter user's ID that you want to change. ");
                    input = scanner.nextLine();
                    System.out.println("---------------");
                    if (User.findByID(Integer.parseInt(input), store) != null) {
                        dashboardUser.profileMenu(User.findByID(Integer.parseInt(input), store));
                        break;
                    }
                    System.out.println("the ID does not exist! ");
                    break;
                case "3":

                    System.out.println(store.getUsers());
                    System.out.println("Enter user's ID that you want to remove. ");
                    input = scanner.nextLine();
                    System.out.println("---------------");
                    if (User.findByID(Integer.parseInt(input), store) != null) {
                        store.removeUser(User.findByID(Integer.parseInt(input), store));
                        break;
                    }
                    System.out.println("the ID does not exist! ");
                    break;
                case "4":
                    System.out.println(store.getUsers());
                    System.out.println("Enter user's ID that you want to see. ");
                    input = scanner.nextLine();
                    System.out.println("---------------");
                    if (User.findByID(Integer.parseInt(input), store) != null) {
                        User.findByID(Integer.parseInt(input), store).showInfo();
                        break;
                    }
                    System.out.println("the ID does not exist! ");
                    break;
                case "5":
                    userList = User.findGameByUserName(scanner, store.getUsers());
                    if (userList.size() == 0) {
                        System.out.println("any User does not exist! ");
                        break;
                    }
                    System.out.println(userList);
                    System.out.println("Enter ID : ");
                    input = scanner.nextLine();
                    System.out.println("---------------");
                    user = User.findByID(Integer.parseInt(input), store);
                    if (user != null) {
                        System.out.println("1. Show info ");
                        System.out.println("2. change User ");
                        System.out.println("3. remove User ");
                        input = scanner.nextLine();
                        System.out.println("---------------");
                        if (input.equals("1"))
                            user.showInfo();
                        if (input.equals("2"))
                            dashboardUser.profileMenu(user);
                        if (input.equals("3"))
                            store.removeUser(user);
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

}
