package ui.gui;

import model.*;
import ui.gui.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// This class is responsible for the Order JFrame, which shows the user their current order (list of items with their
// price and the total amount at the end)
public class OrderGUI extends JFrame {
    //fields
    private Order order;
    private JSplitPane splitPane;
    private JPanel orderTitlePanel;
    private JPanel orderInfoPanel;

    // EFFECTS: Responsible for initializing the order
    public OrderGUI(Order order) {
        this.order = order;
        initialize();
    }

    // MODIFIES: this
    // EFFECTS: Initializes almost all the remaining fields (creates the splitPane, two JPanels, and displays this JFrame)
    private void initialize() {
        setTitle("FastBite Order Form");
        splitPane = new JSplitPane();
        getContentPane().add(splitPane);

        orderTitlePanel = new JPanel();
        orderInfoPanel = new JPanel();

        setPanels();
        createOrderTitleLabel();
        initializeList();
        initializeCheckoutButton();

        initializeSplitPane(splitPane, orderTitlePanel, orderInfoPanel);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(FastBiteGUI.width,FastBiteGUI.height);
        setUndecorated(false);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Sets the layout and background color for the title panel and info panel
    private void setPanels() {
        orderTitlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        orderTitlePanel.setBackground(Color.BLUE);
        orderInfoPanel.setLayout(null);
        orderInfoPanel.setBackground(Color.BLUE);
    }

    // MODIFIES: this
    // EFFECTS: Creates the "Your Order" label and adds it to the title panel
    private void createOrderTitleLabel() {
        JLabel orderTitleLabel = new JLabel("Your Order");
        orderTitleLabel.setFont(new Font("Serif", Font.BOLD, 25));
        orderTitleLabel.setForeground(Color.WHITE);
        orderTitlePanel.add(orderTitleLabel);
    }

    // MODIFIES: this
    // EFFECTS: Responsible for configuring the splitPane in this JFrame (adds it to the JFrame after tweaking)
    private void initializeSplitPane(JSplitPane splitPane, JPanel orderTitlePanel, JPanel orderInfoPanel) {
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(50);
        splitPane.setTopComponent(orderTitlePanel);
        splitPane.setBottomComponent(orderInfoPanel);
        add(splitPane);
    }

    // MODIFIES: this
    // EFFECTS: Initializes the list of items in the order and then adds the items to it
    private void initializeList() {
        DefaultListModel<String> list = new DefaultListModel<>();
        for (Item item: order.getOrderList()) {
            list.addElement(item.getDescription());
        }
        list.addElement(order.getTotalAmountString());
        JList<String> orderlist = new JList<>(list);
        orderlist.setBounds(0,0, 250,300);
        orderlist.setBackground(Color.YELLOW);
        orderInfoPanel.add(orderlist);
    }

    // MODIFIES: this
    // EFFECTS: Creates the checkout button for the user
    private void initializeCheckoutButton() {
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setBounds(0, 300, 150,45);
        checkoutButtonListener(checkoutButton);
        orderInfoPanel.add(checkoutButton);
    }

    // EFFECTS: ActionListener for the checkoutButton, if checkout is pressed, then it asks user if they want to confirm,
    // then asks the user for their address, and finally terminates the program
    private void checkoutButtonListener(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null,
                        order.getTotalAmountString() + ". Are you sure you want to checkout?",
                        "Checkout", JOptionPane.YES_NO_OPTION);
                if (confirmed == JOptionPane.YES_OPTION) {
                    checkout();
                } else {
                    JOptionPane.showMessageDialog(null, "Checkout cancelled.");
                }
            }
        });
    }

    // EFFECTS: Asks user for their address, then exits the program (Helper method)
    private void checkout() {
        String address = JOptionPane.showInputDialog("What is the address you want to ship your order to?");
        JOptionPane.showMessageDialog(null, "Your order will be shipped to " + address
                + " shortly! Thank you for using FastBite!");
        System.exit(0);
    }
}
