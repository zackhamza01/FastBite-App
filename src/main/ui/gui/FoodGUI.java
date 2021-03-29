package ui.gui;

import model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// This class is responsible for giving the user a list of items they want to add from popular fast
// food restaurants (in its own JFrame)
public class FoodGUI extends JFrame {
    //fields
    private Order order;
    private JSplitPane splitPane;
    private JPanel foodTitlePanel;
    private JPanel foodInfoPanel;
    private FastFood f1;
    private FastFood f2;
    private FastFood f3;
    private DefaultListModel<FastFood> fastFood;

    // EFFECTS: Responsible for initializing the order and calling initializing helper methods
    public FoodGUI(Order order) {
        this.order = order;
        initializeFastFood();
        initialize();
    }

    // MODIFIES: this
    // EFFECTS: Sets up the JFrame and JPanels that create this new frame
    private void initialize() {
        setTitle("FastBite Fast Food Menu");
        splitPane = new JSplitPane();
        getContentPane().add(splitPane);

        foodTitlePanel = new JPanel();
        foodInfoPanel = new JPanel();

        setPanels();
        createFoodTitleLabel();
        //addFastFoodLabels();
        addButtons();

        initializeSplitPane(splitPane, foodTitlePanel, foodInfoPanel);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(FastBiteGUI.width,FastBiteGUI.height);
        setUndecorated(false);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Sets up the layout and background colors for the title and info panels
    private void setPanels() {
        foodTitlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        foodTitlePanel.setBackground(Color.orange);
        foodInfoPanel.setLayout(new FlowLayout());
        foodInfoPanel.setBackground(Color.orange);
    }

    // MODIFIES: this
    // EFFECTS: Creates the title label then adds it to the JFrame
    private void createFoodTitleLabel() {
        JLabel foodTitleLabel = new JLabel("Order Food from Fast Food Restaurants");
        foodTitleLabel.setFont(new Font("Serif", Font.BOLD, 25));
        foodTitleLabel.setForeground(Color.WHITE);
        foodTitlePanel.add(foodTitleLabel);
    }

    // MODIFIES: this
    // EFFECTS: Configues the splitPane for this JFrame, then adds it to the JFrame
    private void initializeSplitPane(JSplitPane splitPane, JPanel foodTitlePanel, JPanel foodInfoPanel) {
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(50);
        splitPane.setTopComponent(foodTitlePanel);
        splitPane.setBottomComponent(foodInfoPanel);
        add(splitPane);
    }

    // MODIFIES: this
    // EFFECTS: Initializes the list of fast food restaurants then adds food to each restaurant (data)
    private void initializeFastFood() {
        f1 = new FastFood("McDonald's");
        f2 = new FastFood("Freshslice Pizza");
        f3 = new FastFood("Dairy Queen");
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

    // MODIFIES: this
    // EFFECTS: Creates the labels for the fast food restaurants
    private void addFastFoodLabels() {
        JLabel f1label = new JLabel(f1.getName() + " | ");
        JLabel f2label = new JLabel(f2.getName() + " | ");
        JLabel f3label = new JLabel(f3.getName() + " | ");
        foodInfoPanel.add(f1label, FlowLayout.LEFT);
        foodInfoPanel.add(f2label, FlowLayout.CENTER);
        foodInfoPanel.add(f3label, FlowLayout.RIGHT);
        addButtons();

    }

    // MODIFIES: this
    // EFFECTS: Creates the buttons for the items in each fast food restaurants
    private void addButtons() {
        for (Item item: f1.getFoodList()) {
            JButton button = new JButton(item.getDescription());
            foodInfoPanel.add(button, FlowLayout.LEFT);
            foodButtonListener(button, item);
        }

        for (Item item: f2.getFoodList()) {
            JButton button = new JButton(item.getDescription());
            foodInfoPanel.add(button, FlowLayout.CENTER);
            foodButtonListener(button, item);
        }

        for (Item item: f3.getFoodList()) {
            JButton button = new JButton(item.getDescription());
            foodInfoPanel.add(button, FlowLayout.RIGHT);
            foodButtonListener(button, item);
        }
    }

    // MODIFIES: this
    // EFFECTS: ActionListener for each item. If it is pressed, then it
    // adds the item to the order and lets the user know
    private void foodButtonListener(JButton button, Item item) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to add this item to your order?",
                        "Add Item", JOptionPane.YES_NO_OPTION);
                if (confirmed == JOptionPane.YES_OPTION) {
                    order.addItemToOrder(item);
                    JOptionPane.showMessageDialog(null, "Successfully added " + item.getName() + " to your order!");
                } else {
                    JOptionPane.showMessageDialog(null, "Did not add " + item.getName() + " to your order.");
                }
            }
        });
    }



}
