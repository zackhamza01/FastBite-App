package ui.gui;

import model.*;
import persistence.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

// This class is the main JFrame for this GUI. It welcomes the user to the app and gives
// the user the option to order food or view their order. Credit goes to the many Java Swing
// tutorials and the creator of the elevator music that is utilized in this app
public class FastBiteGUI extends JFrame {
    //fields
    public static final int width = 500;
    public static final int height = 500;
    public static final String MUSIC_FILE = "./data/elevatormusic.wav";
    private static final String JSON_STORE = "./data/order.json";
    private Order order;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JSplitPane splitPane;
    private JPanel welcomePanel;
    private JPanel mainPanel;

    // EFFECTS: Responsible for initializing the main menu for this GUI.
    // Calls methods that creates the frame, panels, and loads music
    public FastBiteGUI() {
        initialize();
        music(MUSIC_FILE);

        setTitle("FastBite Main Menu");
        splitPane = new JSplitPane();
        getContentPane().add(splitPane);

        welcomePanel = new JPanel();
        mainPanel = new JPanel();

        setPanels();
        createWelcomeLabel();
        createButtons();

        initializeSplitPane(splitPane, welcomePanel, mainPanel);

        confirmSave();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(width,height);
        setUndecorated(false);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Creates a new order, jsonWriter, and jsonReader (stored in the JSON file in ./data)
    private void initialize() {
        order = new Order();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        confirmLoad();
    }

    // MODIFIES: this
    // EFFECTS: Sets the layout and the background colors for the welcome Panel and main Panel
    private void setPanels() {
        welcomePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        welcomePanel.setBackground(new Color(255,0,0));
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        mainPanel.setBackground(new Color(255,0,0));
    }

    // MODIFIES: this
    // EFFECTS: Creates the "Welcome to FastBite" label that is seen at the top of the main JFrame
    // The title is in white
    private void createWelcomeLabel() {
        JLabel welcomelabel = new JLabel("Welcome to FastBite! Hope you enjoy!");
        welcomelabel.setFont(new Font("Serif", Font.BOLD, 25));
        welcomelabel.setForeground(Color.WHITE);
        welcomePanel.add(welcomelabel);
    }

    // MODIFIES: this
    // EFFECTS: Creates the buttons that lets the user choose between ordering food or viewing their order
    private void createButtons() {
        JButton foodButton = new JButton("Order Food");
        foodButtonListener(foodButton);
        JButton orderButton = new JButton("View your Order");
        orderButtonListener(orderButton);
        mainPanel.add(foodButton);
        mainPanel.add(orderButton);
    }

    // MODIFIES: this
    // EFFECTS: The ActionListener for the foodButton. If that button is pushed, it takes the user
    // to the FoodGUI JFrame
    private void foodButtonListener(JButton foodButton) {
        foodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FoodGUI(order);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: The ActionListener for the orderButton. If that button is pushed, it takes the user
    // to the OrderGUI JFrame
    private void orderButtonListener(JButton orderButton) {
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OrderGUI(order);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: Formats the splitPane format for this main JFrame, then adds it to the JFrame
    private void initializeSplitPane(JSplitPane splitPane, JPanel welcomePanel, JPanel mainPanel) {
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(200);
        splitPane.setTopComponent(welcomePanel);
        splitPane.setBottomComponent(mainPanel);
        add(splitPane);
    }

    // MODIFIES: this
    // EFFECTS: Asks the user when they first load this application if they want to load their order
    // from a previous session.
    private void confirmLoad() {
        int confirmed = JOptionPane.showConfirmDialog(null,
                "Do you want to load your order from a previous session?", "Load Data", JOptionPane.YES_NO_OPTION);

        if (confirmed == JOptionPane.YES_OPTION) {
            try {
                order = jsonReader.readOrder();
                JOptionPane.showMessageDialog(null, "Order loaded successfully!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Unable to load order due to an error.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Order was not loaded.");
        }
    }

    // MODIFIES: this
    // EFFECTS: Asks the user when they are quitting if they want to save their order before leaving. If yes,
    // it saves the program, and then it terminates it
    private void confirmSave() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Do you want to save your order before quitting?", "Save Data", JOptionPane.YES_NO_OPTION);
                if (confirmed == JOptionPane.YES_OPTION) {
                    try {
                        jsonWriter.open();
                        jsonWriter.write(order);
                        jsonWriter.close();
                        JOptionPane.showMessageDialog(null, "Thank you for using FastBite!");
                    } catch (FileNotFoundException x) {
                        JOptionPane.showMessageDialog(null, "Unable to save order due to error.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Order was not saved.");
                }
                System.exit(0);
            }
        });
    }

    // EFFECTS: Creates the music that is heard in the background of this app (Elevator Music)
    private void music(String filepath) {
        try {
            File musicPath = new File(filepath);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                JOptionPane.showMessageDialog(null, "Error: Unable to run music");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: Unable to run music");
        }
    }

}
