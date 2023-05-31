package ir.ac.kntu;

import java.util.ArrayList;
import java.util.regex.Pattern;

import ir.ac.kntu.Game.Type;

import java.util.Scanner;

public class Utilities {

    public static void createNewGame(Scanner scanner, Game newGame) {
        String input;
        boolean isDone = false;
        do {
            System.out.println("enter a name: ");
            System.out.println("\n0. Go back to previous menu\n");
            input = scanner.nextLine();
            System.out.println("---------------");
            if (input.equals("0")) {
                break;
            }
            if (Game.isValidName(input)) {
                newGame = new Game();
                newGame.setName(input);
                setGamePrice(scanner, newGame, isDone);
                if (isDone) {
                    break;
                }
                continue;
            } else {
                System.out.println("Invalid name ! \ntry again.");
            }
        } while (true);
    }

    public static void setGamePrice(Scanner scanner, Game game, boolean isDone) {
        String input;
        do {
            System.out.println("enter price: ");
            System.out.println("\n0. Go back to previous menu\n");
            input = scanner.nextLine();
            System.out.println("---------------");
            if (input.equals("0")) {
                break;
            }
            if (Game.isValidPrice(input)) {
                game.setPrice(Integer.parseInt(input));
                setGameType(scanner, game, isDone);
                if (isDone) {
                    break;
                }
                continue;

            } else {
                System.out.println("Invalid name ! \ntry again.");
            }
        } while (true);
    }

    public static void setGameType(Scanner scanner, Game game, boolean isDone) {
        String input;
        outerloop: do {
            System.out.println("1. Puzzle ");
            System.out.println("2. Action ");
            System.out.println("3. Sport ");
            System.out.println("4. Mystry ");
            System.out.println("\n0. Go back to previous menu\n");
            input = scanner.nextLine();
            System.out.println("---------------");
            switch (input) {
                case "0":
                    break outerloop;
                case "1":
                    game.setType(Type.PUZZLE);
                    isDone = true;
                    break outerloop;
                case "2":
                    game.setType(Type.ACTION);
                    isDone = true;
                    break outerloop;
                case "3":
                    game.setType(Type.SPORT);
                    isDone = true;
                    break outerloop;
                case "4":
                    game.setType(Type.MYSTRY);
                    isDone = true;
                    break outerloop;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

        } while (true);
    }

    public static User findUserName(String userName, ArrayList<User> users) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    public static boolean isUniqueUserName(String userName, ArrayList<User> users) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return false;

            }
        }
        return true;
    }

    public static boolean isValidUserName(String userName) {
        String regex = "\\w+";
        boolean isValid = Pattern.matches(regex, userName);
        return isValid;
    }

    public static boolean isValidPassword(String password) {
        String regex = "[a-zA-Z0-9.@#]{8,}";
        boolean isValid = Pattern.matches(regex, password);
        return isValid;
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "09\\d{9}";
        boolean isValid = Pattern.matches(regex, phoneNumber);
        return isValid;
    }

    public static boolean isValidEmail(String Email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        boolean isValid = Pattern.matches(regex, Email);
        return isValid;
    }
}
