import controller.AuthenticationController;
import controller.JasaJokiMenu;

import java.util.Scanner;

public class JasaJokiApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthenticationController authController = new AuthenticationController();

        boolean loggedIn = false;

        while (!loggedIn) {
            clearScreen();
            System.out.println("+------------------------+");
            System.out.println("|       Menu Login       |");
            System.out.println("+------------------------+");
            System.out.println("| 1. Masuk               |");
            System.out.println("| 2. Registrasi          |");
            System.out.println("| 0. Keluar              |");
            System.out.println("+------------------------+");
            System.out.print("Masukkan pilihan Anda: ");

            int menu = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (menu) {
                case 1:
                    clearScreen();
                    System.out.println("+------------------------+");
                    System.out.println("|         Masuk          |");
                    System.out.print("| Masukkan nama pengguna: ");
                    String username = scanner.nextLine();
                    System.out.print("| Masukkan kata sandi: ");
                    String password = scanner.nextLine();
                    System.out.println("+------------------------+");

                    if (authController.authenticate(username, password)) {
                        loggedIn = true;
                        System.out.println("Login successful. Welcome!");

                        // Call the menu method from the JasaJokiMenu class
                        JasaJokiMenu.showMenu(scanner);
                    } else {
                        System.out.println("Invalid username or password. Please try again.");
                    }
                    break;

                case 2:
                    clearScreen();
                    System.out.println("+------------------------+");
                    System.out.println("|      Registrasi        |");
                    System.out.print("| Masukkan nama pengguna: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("| Masukkan kata sandi: ");
                    String newPassword = scanner.nextLine();
                    System.out.println("+------------------------+");

                    authController.registerUser(newUsername, newPassword);
                    break;

                case 0:
                    System.out.println("Thank you for using our system. Have a great day!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Error clearing screen: " + e.getMessage());
        }
    }
}
