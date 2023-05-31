package ir.ac.kntu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Game {
    enum Type {
        ACTION, SPORT, PUZZLE, MYSTRY
    }

    private String name;
    private String description;
    private ArrayList<String> comments = new ArrayList<>();
    private int price;
    private Map<Integer, Float> points = new HashMap<>();
    private ArrayList<User> community = new ArrayList<>();
    private Type type;
    private Integer id;
    private static int nextId = 1;
    private float point;
    private static Scanner scanner = new Scanner(System.in);
    private static boolean isGameCreat = false;
    private static String input;
    private static Integer maxPrice;
    private static Integer minPrice;
    private static ArrayList<Game> filteredGames = new ArrayList<>();

    public Game(String name, String description, int price, Type type) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
        this.point = 0F;
        this.id = nextId;
        nextId++;
    }

    public Game() {
        this.id = nextId;
        nextId++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPoints(Map<Integer, Float> points) {
        this.points = points;
    }

    public void setPoint(Float point) {
        this.point = point;
    }

    public void setCommunity(ArrayList<User> community) {
        this.community = community;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public int getPrice() {
        return price;
    }

    public Map<Integer, Float> getPoints() {
        return points;
    }

    public float getPoint() {
        calculatePoint();
        return this.point;
    }

    public ArrayList<User> getCommunity() {
        return community;
    }

    public Type getType() {
        return type;
    }

    public Integer getId() {
        return id;
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    public void calculatePoint() {
        float sum = 0F;
        for (float point : points.values()) {
            sum += point;
        }

        this.point = sum / ((float) points.size());
    }

    @Override
    public String toString() {
        return "ID : " + getId() + " Name : " + getName() + "\n";
    }

    public static Game creatGame() {
        String input;
        isGameCreat = false;
        Game game = new Game();
        while (!isGameCreat) {
            System.out.println("\n0. Go back to previous menu\n");
            System.out.println("Enter name for game : ");
            input = scanner.nextLine();
            System.out.println("---------------");
            if (input.equals("0")) {
                break;
            }
            if (isValidName(input)) {
                game.setName(input);
                setNewGamePrice(game);
                if (isGameCreat) {
                    return game;
                }

            } else {
                System.out.println("invalid username! ");
            }
        }
        return null;
    }

    public static void setNewGamePrice(Game game) {
        String input;

        while (!isGameCreat) {
            System.out.println("\n0. Go back to previous menu\n");
            System.out.println("Enter Price for game : ");
            input = scanner.nextLine();
            System.out.println("---------------");
            if (input.equals("0")) {
                break;
            }
            if (isValidPrice(input)) {
                game.setPrice(Integer.parseInt(input));
                setNewGameDescription(game);

            } else {
                System.out.println("invalid Price! ");
            }
        }
    }

    public static void setNewGameDescription(Game game) {
        String input;

        while (!isGameCreat) {
            System.out.println("\n0. Go back to previous menu\n");
            System.out.println("Enter description for game : ");
            input = scanner.nextLine();
            System.out.println("---------------");
            if (input.equals("0")) {
                break;
            }
            if (isValidDescription(input)) {
                game.setDescription(input);
                setNewGameType(game);

            } else {
                System.out.println("invalid description! ");
            }
        }
    }

    public static void setNewGameType(Game game) {
        String input;

        outerloop: while (!isGameCreat) {
            System.out.println("\n0. Go back to previous menu\n");
            System.out.println("1. Sport ");
            System.out.println("2. Action ");
            System.out.println("3. Puzzle ");
            System.out.println("4. Mystry ");
            input = scanner.nextLine();
            System.out.println("---------------");
            switch (input) {
                case "0":
                    System.out.println("Returning to previous menu...");
                    break outerloop;
                case "1":
                    game.setType(Type.SPORT);
                    isGameCreat = true;
                    System.out.println("the game creat succesfully. ");
                    break;
                case "2":
                    game.setType(Type.ACTION);
                    isGameCreat = true;
                    System.out.println("the game creat succesfully. ");
                    break;
                case "3":
                    game.setType(Type.PUZZLE);
                    isGameCreat = true;
                    System.out.println("the game creat succesfully. ");
                    break;
                case "4":
                    game.setType(Type.MYSTRY);
                    isGameCreat = true;
                    System.out.println("the game creat succesfully. ");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public void showAllInfo() {
        System.out.println("ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Price: " + getPrice());
        System.out.println("Discription: " + getDescription());
        System.out.println("Type: " + getType());
        System.out.println("Point: " + getPoint() + "\n\n");
    }

    public void showOption(User user) {
        while (true) {
            System.out.println("\n0. Go back to previous menu\n");
            System.out.println("1. Community ");
            System.out.println("2. Add comment ");
            System.out.println("3. Rate the game \n");

            input = scanner.nextLine();
            System.out.println("---------------");
            if (input.equals("0")) {
                break;
            }

            if (input.equals("1")) {
                for (String comment : comments) {
                    System.out.println(comment);

                }
                continue;
            }
            if (input.equals("2")) {
                System.out.println("Enter your comment: ");
                input = scanner.nextLine();
                System.out.println("---------------");
                comments.add(input);
                System.out.println("your comment added succesfully. ");
                continue;
            }
            if (input.equals("3")) {
                System.out.println("Enter your rate: ");
                input = scanner.nextLine();
                System.out.println("---------------");
                points.put(user.getId(), Float.parseFloat(input));
                System.out.println("your rate added succesfully. ");
                continue;
            } else {
                System.out.println("Invalid choice. ");
            }
        }

    }

    public void addToComuunity(User user) {
        community.add(user);
    }

    public void changeInfo() {

        String input;
        outerloop: do {
            this.showAllInfo();
            System.out.println("1. change  Name ");
            System.out.println("2. change  description ");
            System.out.println("3. change  Price ");
            System.out.println("4. change  Type ");
            System.out.println("\n0. Go back to previous menu\n");
            input = scanner.nextLine();
            System.out.println("---------------");
            switch (input) {
                case "0":
                    System.out.println("Returning to previous menu...");
                    break outerloop;
                case "1":
                    System.out.println("Please enter new Name");
                    System.out.println("\n0. Go back to previous menu\n");
                    input = scanner.nextLine();
                    System.out.println("---------------");
                    if (input.equals("0")) {
                        break;
                    }
                    if (!isValidName(input)) {
                        System.out.println("Invalid name. ");
                        break;
                    }
                    this.setName(input);
                    System.out.println("Name changed succesfully. ");
                    break;
                case "2":
                    System.out.println("Please enter new description ");
                    System.out.println("\n0. Go back to previous menu\n");
                    input = scanner.nextLine();
                    System.out.println("---------------");
                    if (input.equals("0")) {
                        break;
                    }
                    if (!isValidDescription(input)) {
                        System.out.println("Invalid description. ");
                        break;
                    }
                    this.setDescription(input);
                    System.out.println("Description changed succesfully. ");
                    break;
                case "3":
                    System.out.println("Please enter new price");
                    System.out.println("\n0. Go back to previous menu\n");
                    input = scanner.nextLine();
                    System.out.println("---------------");
                    if (input.equals("0")) {
                        break;
                    }
                    if (!isValidPrice(input)) {
                        System.out.println("Invalid price. ");
                        break;
                    }
                    this.setPrice(Integer.parseInt(input));
                    System.out.println("Price changed succesfully. ");
                    break;
                case "4":
                    System.out.println("1. Sport ");
                    System.out.println("2. Action ");
                    System.out.println("3. Puzzle ");
                    System.out.println("4. Mystry ");
                    input = scanner.nextLine();
                    System.out.println("---------------");
                    switch (input) {
                        case "1":
                            this.setType(Type.SPORT);
                            break;
                        case "2":
                            this.setType(Type.ACTION);
                            break;
                        case "3":
                            this.setType(Type.PUZZLE);
                            break;
                        case "4":
                            this.setType(Type.MYSTRY);
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (true);
    }

    public static Game findGameById(Scanner scanner, ArrayList<Game> games) {
        System.out.println("Enter your game's ID that you want to see its information. ");
        input = scanner.nextLine();
        System.out.println("---------------");
        for (Game game : games) {
            if (game.getId().equals(Integer.parseInt(input))) {
                return game;
            }
        }
        return null;

    }

    public static ArrayList<Game> findGameByUserName(Scanner scanner, ArrayList<Game> games) {
        System.out.println("Enter name.");
        input = scanner.nextLine();
        filteredGames = new ArrayList<>();
        System.out.println("---------------");
        for (Game game : games) {
            if (game.getName().startsWith(input)) {
                filteredGames.add(game);
            }
        }
        return filteredGames;

    }

    public static ArrayList<Game> filterByPrice(Scanner scanner, ArrayList<Game> games) {
        System.out.println("Enter your max price");
        filteredGames = new ArrayList<Game>();
        input = scanner.nextLine();
        maxPrice = Integer.parseInt(input);
        System.out.println("---------------");
        System.out.println("Enter your min price");
        input = scanner.nextLine();
        minPrice = Integer.parseInt(input);
        System.out.println("---------------");
        for (Game game : games) {
            if (game.getPrice() > minPrice && game.getPrice() < maxPrice) {
                filteredGames.add(game);
            }
        }
        return Game.filteredGames;

    }

    public static boolean isValidName(String name) {
        String regex = "\\w+";
        boolean isValid = Pattern.matches(regex, name);
        return isValid;
    }

    public static boolean isValidPoint(String point) {
        String regex = "\\w+";
        boolean isValid = Pattern.matches(regex, point);
        return isValid;
    }

    public static boolean isValidPrice(String price) {
        String regex = "\\d+";
        boolean isValid = Pattern.matches(regex, price);
        return isValid;
    }

    public static boolean isValidDescription(String description) {
        String regex = "\\w+";
        boolean isValid = Pattern.matches(regex, description);
        return isValid;
    }
}
