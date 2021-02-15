package ui;

import model.*;

import java.text.DecimalFormat;
import java.util.Scanner;

//This is the UI for the FastBite App. Users are given an Order form and a list of FastFoods to pick from.
//The user can also view their order form and choose if they want to remove anything from the list
//The user can then finally finalize and checkout their order, where they are prompted to put
//their address for the items to be shipped at.
public class FastBiteApp {
    //fields
    private Order order;
    private FastFood f1;
    private FastFood f2;
    private FastFood f3;
    private Scanner input;

    //Constructor
    // EFFECTS: Calls the runFastBite method (creates the while loop)
    public FastBiteApp() {
        runFastBite();
    }

    // MODIFIES: this
    // EFFECTS: Welcomes the user, then establishes the loop to enter the main menu after every interaction
    //Also, this method modifies the input field as it re-initializes it to a new Scanner (to clear previous inputs)
    //After the while loop is terminated, it says goodbye to the user
    private void runFastBite() {
        System.out.println("Welcome to the FastBite App! Hope you enjoy!");
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            input = new Scanner(System.in);
            displayMenu();
            command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("Thank you for using the FastBite App! Goodbye!");
    }

    // EFFECTS: The main menu; which gives the user the option to either add food to their order
    // by picking from a number of popular fast food restaurants, view the order form, finalize and checkout
    // the order, or quit
    private void displayMenu() {
        System.out.println("\nSelect from the following:");
        System.out.println("\tfood -> Get food from a list of fast food restaurants");
        System.out.println("\tview -> View your current order");
        System.out.println("\tcheckout -> Finalize your order and checkout");
        System.out.println("\tquit -> quit the application");
    }

    // EFFECTS: Processes the command from the main menu that the user chose after reading it
    // If the command is invalid, then program will tell user to try again
    private void processCommand(String command) {
        if (command.equals("food")) {
            addItemsInFastFood(fastFoodSelection());
        } else if (command.equals("view")) {
            if (viewOrder().equals("yes")) {
                removeItemFromOrder();
            } else {
                System.out.println("Going back to main menu.");
            }
        } else if (command.equals("checkout")) {
            if (checkoutSelection().equals("yes")) {
                checkout();
            } else {
                System.out.println("Going back to main menu.");
            }
        } else {
            System.out.println("Selection not valid. Try again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: Initalizes Order, the 3 top FastFood restaurants, and input to a Scanner object.
    // Then calls addItemsToRestaurantInit method
    private void init() {
        order = new Order();
        f1 = new FastFood("McDonald's");
        f2 = new FastFood("Freshslice Pizza");
        f3 = new FastFood("Dairy Queen");
        input = new Scanner(System.in);
        addItemsToRestaurantInit();
    }

    // MODIFIES: this
    // EFFECTS: Adds a big number of  Food and Drinks to the 3 FastFood restaurants
    private void addItemsToRestaurantInit() {
        f1.addItemToRestaurant(new Food("Big Mac", 6.50, 800));
        f1.addItemToRestaurant(new Food("McChicken", 5.20, 700));
        f1.addItemToRestaurant(new Food("Egg McMuffin", 4.59, 290));
        f1.addItemToRestaurant(new Drink("Coca-Cola", 2.60, 100, 400));
        f1.addItemToRestaurant(new Drink("Sprite", 3.00, 250, 500));
        f2.addItemToRestaurant(new Food("Butter Chicken Feast Pizza", 15.50, 1980));
        f2.addItemToRestaurant(new Food("BBQ Chicken Wings", 7.30, 1120));
        f2.addItemToRestaurant(new Food("Pepperoni Pizza", 12.70, 1510));
        f2.addItemToRestaurant(new Drink("Chocolate Milk", 3.70, 200, 250));
        f2.addItemToRestaurant(new Drink("Pepsi", 3.20, 220, 400));
        f3.addItemToRestaurant(new Food("Blizzard Treat", 4.50, 270));
        f3.addItemToRestaurant(new Food("Oreo Cookie Blizzard", 6.80, 790));
        f3.addItemToRestaurant(new Food("Poutine", 7.60, 1170));
        f3.addItemToRestaurant(new Drink("Chocolate Shake", 5.30, 780, 500));
        f3.addItemToRestaurant(new Drink("Banana Shake", 3.90, 590, 400));
    }

    // EFFECTS: Helper method for selecting one of the 3 FastFood restaurants. Returns the FastFood restaurant object
    private FastFood fastFoodSelection() {
        System.out.println("Select from the following fast food restaurants to order items from:");
        System.out.println("\t1 -> McDonald's");
        System.out.println("\t2 -> Freshslice Pizza");
        System.out.println("\t3 -> Dairy Queen");
        int selection = input.nextInt();
        while (selection != 1 && selection != 2 && selection != 3) {
            System.out.println("Invalid selection! Try again.");
            System.out.println("Select from the following fast food restaurants to order items from:");
            System.out.println("\t1 -> McDonald's");
            System.out.println("\t2 -> Freshslice Pizza");
            System.out.println("\t3 -> Dairy Queen");
            selection = input.nextInt();
        }
        if (selection == 1) {
            return f1;
        } else if (selection == 2) {
            return f2;
        } else {
            return f3;
        }
    }

    // MODIFIES: this
    // EFFECTS: Adds an item to the selected FastFood restaurant or goes back to main menu, depending on user input
    // If the selection is invalid, it will go back to the main menu and try again
    private void addItemsInFastFood(FastFood f) {
        System.out.println("Pick from the following items:");
        for (int i = 0; i < f.getFoodList().size(); i++) {
            System.out.println("\t" + (i + 1) + " -> " + f.getFoodList().get(i).getDescription());
        }
        System.out.println("\t0 -> Go back");
        int selection = input.nextInt() - 1;
        if (selection < f.getFoodList().size() && selection >= 0) {
            order.addItemToOrder(f.getFoodList().get(selection));
            System.out.println("Successfully added " + f.getFoodList().get(selection).getDescription() + " to order!");
        } else if (selection == -1) {
            System.out.println("Going back!");
        } else {
            System.out.println("Invalid selection. Going back to main menu.");
        }
    }

    // EFFECTS: Allows the user to view the Order form.
    // Also prompts the user if they want to remove any items from the Order
    // If the Order form is empty, then it encourages the user to add some items in the Order
    private String viewOrder() {
        if (!order.getOrderList().isEmpty()) {
            System.out.println("Your current order:");
            for (Item item: order.getOrderList()) {
                System.out.println("\t" + item.getDescription());
            }
            System.out.println(order.getTotalAmountString());
            System.out.println("Do you want to remove any of these items from your order? (yes/no)");
            String choice = input.nextLine();
            choice = choice.toLowerCase();
            return choice;
        } else {
            System.out.println("Your order is empty. Try adding some items to first!");
            return "no";
        }
    }

    // MODIFIES: this
    // EFFECTS: Removes a selected item from the Order form or goes back to main menu, depending on user input
    // If the selection is invalid, it goes back to the main menu and user tries again
    private void removeItemFromOrder() {
        System.out.println("Select from the following items:");
        for (int i = 0; i < order.getOrderList().size(); i++) {
            System.out.println("\t" + (i + 1) + " -> " + order.getOrderList().get(i).getDescription());
        }
        System.out.println("\t0 -> Go back");
        int selection = input.nextInt() - 1;
        if (selection < order.getOrderList().size() && selection >= 0) {
            Item item = order.getOrderList().get(selection);
            order.removeItemFromOrder(item);
            System.out.println("Successfully removed " + item.getDescription() + " from order!");
        } else if (selection == -1) {
            System.out.println("Going back!");
        } else {
            System.out.println("Invalid selection. Going back to main menu.");
        }
    }

    // EFFECTS: Once checkout is selected, it asks the user if they want to finalize the order and checkout.
    // If the order form is empty, the user cannot checkout, and is encouraged to add some items before checking out
    private String checkoutSelection() {
        if (!order.getOrderList().isEmpty()) {
            System.out.println("Your current order:");
            for (Item item: order.getOrderList()) {
                System.out.println("\t" + item.getDescription());
            }
            System.out.println(order.getTotalAmountString());
            System.out.println("Do you want to finalize this order and checkout? (yes/no)");
            String choice = input.nextLine();
            choice = choice.toLowerCase();
            return choice;
        } else {
            System.out.println("Your order is empty. Try adding some items to first before checking out!");
            return "no";
        }
    }

    // EFFECTS: Asks the user for their address, and processes the payment to their account.
    // Then tells user that their order is shipped to their address and says  (terminates program)
    private void checkout() {
        System.out.println("What is the address that you want to ship your order to?");
        String address = input.nextLine();
        DecimalFormat f = new DecimalFormat("##.00");
        System.out.println("...\nYour order has succesfully been charged to your account.");
        System.out.println("Your order will be shipped to " + address + " shortly!");
        System.out.println("Thank for using the FastBite App! Goodbye!");
        System.exit(0);
    }

}