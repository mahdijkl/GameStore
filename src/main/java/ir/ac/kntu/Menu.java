package ir.ac.kntu;

import java.util.Scanner;

public class Menu {
    private Store store;
    private Scanner scanner = new Scanner(System.in);
    private DashboardAdmin dashboardAdmin;
    private DashboardUser dashboardUser;

    public Menu(Scanner scanner, Store store) {
        this.scanner = scanner;
        this.store = store;
        dashboardAdmin = new DashboardAdmin(store);
        dashboardUser = new DashboardUser(store);
    }

    public Store getStore() {
        return store;
    }

    public void startMenu() {
        String input = "0";
        outerloop: do {
            System.out.println("Please select a menu option:");
            System.out.println("1. : Login as an admin ");
            System.out.println("2. : Login as a user ");
            System.out.println("0. : Exit");

            input = scanner.nextLine();
            System.out.println("---------------");
            switch (input) {
                case "1":
                    dashboardAdmin.logInAdminMenu();
                    break;
                case "2":
                    dashboardUser.logInUserMenu();
                    break;
                case "0":
                    System.out.println("Exiting program...");
                    break outerloop;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (true);
    }

}
