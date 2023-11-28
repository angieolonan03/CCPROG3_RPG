import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CreatureInventoryApp extends JFrame {
    private Inventory inventory;
    private JList<String> creatureListView;
    private JLabel creatureImageView;
    private JPanel detailsPanel;
    private PokemonGame pokemonGame;

    public CreatureInventoryApp(Inventory inventory) {
        this.inventory = inventory;
        this.pokemonGame = pokemonGame;

        // Add some sample creatures to the inventory
       inventory.addCreature(new Creature("Strawander", "Fire", "Family A", 1, 100, "Strawander.jpg"));
       inventory.addCreature(new Creature("Strawander", "Fire", "Family A", 1, 100, "Strawander.jpg"));
       inventory.addCreature(new Creature("Straweleon", "Fire", "Family A", 2, 100, "Straweleon.jpg"));

        initComponents();
    }

    /**
     * Initializes the components used in the Java function.
     */
    private void initComponents() {
        creatureListView = new JList<>();
        creatureImageView = new JLabel();
        detailsPanel = new JPanel(); // Panel to display creature details

        JButton changeActiveButton = new JButton("Change Active Creature");
        JButton goBackButton = new JButton("Go Back");

        // Populate the JList with creature names
        updateCreatureList();

        // Set the initial active creature
        setImageViewForActiveCreature();

        // JList selection listener
        creatureListView.addListSelectionListener(e -> {
            Creature selectedCreature = inventory.getAllCreatures().get(creatureListView.getSelectedIndex());
            inventory.setActiveCreature(selectedCreature);
            setImageViewForActiveCreature();
            displayCreatureDetails(selectedCreature);
        });

        // Change Active Creature button action
        changeActiveButton.addActionListener(e -> showChangeActiveCreatureDialog());

        // Go Back button action
        goBackButton.addActionListener(e -> {
            this.setVisible(false);
            // You might want to add more logic here to handle the "Go Back" action.
            // For example, if you want to return to the main menu or a previous screen.
        });

        // Layout
        JPanel root = new JPanel();
        root.setLayout(new BorderLayout(10, 10));

        // Background color
        root.setBackground(Color.BLACK); // Set background color to black

        JPanel creatureInfoBox = new JPanel();
        creatureInfoBox.setLayout(new GridLayout(1, 2, 10, 10));
        creatureInfoBox.setBackground(Color.BLACK); // Set background color to black
        creatureInfoBox.add(new JScrollPane(creatureListView));
        creatureInfoBox.add(creatureImageView);
        root.add(creatureInfoBox, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(changeActiveButton);
        buttonPanel.add(goBackButton);
        root.add(buttonPanel, BorderLayout.SOUTH);

        // Button styling
        styleButton(changeActiveButton);
        styleButton(goBackButton);

        // Frame
        setTitle("Creature Inventory");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(root);
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    /**
     * Updates the creature list in the GUI.
     *
     * @param  None No parameters required.
     * @return None This function does not return any value.
     */
    private void updateCreatureList() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Creature creature : inventory.getAllCreatures()) {
            listModel.addElement(creature.getName()); // Display only the names in the JList
        }
        creatureListView.setModel(listModel);
        creatureListView.setBackground(Color.BLACK); // Set background color to black
        creatureListView.setForeground(Color.PINK); // Set font color to pink
    }

    /**
     * Display the details of a creature.
     *
     * @param creature The creature object containing the details to be displayed.
     */
    private void displayCreatureDetails(Creature creature) {
        // Clear the previous details
        detailsPanel.removeAll();
    
        // Display creature details
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.BLACK); // Set background color to black
    
        JLabel nameLabel = new JLabel("Name: " + creature.getName());
        JLabel healthLabel = new JLabel("Health: " + creature.getHealth());
        JLabel levelLabel = new JLabel("Level: " + creature.getEvolutionLevel());
        JLabel familyLabel = new JLabel("Family: " + creature.getFamily());
        JLabel typeLabel = new JLabel("Type: " + creature.getType());
    
        // Customize the font and foreground color of the labels
        Font labelFont = new Font("Arial", Font.PLAIN, 16);
        nameLabel.setFont(labelFont);
        healthLabel.setFont(labelFont);
        levelLabel.setFont(labelFont);
        familyLabel.setFont(labelFont);
        typeLabel.setFont(labelFont);
    
        nameLabel.setForeground(Color.PINK); // Set font color to pink
        healthLabel.setForeground(Color.PINK); // Set font color to pink
        levelLabel.setForeground(Color.PINK); // Set font color to pink
        familyLabel.setForeground(Color.PINK); // Set font color to pink
        typeLabel.setForeground(Color.PINK); // Set font color to pink
    
        // Add labels to the panel
        detailsPanel.add(nameLabel);
        detailsPanel.add(healthLabel);
        detailsPanel.add(levelLabel);
        detailsPanel.add(familyLabel);
        detailsPanel.add(typeLabel);
    
        // Create a titled border
        TitledBorder border = BorderFactory.createTitledBorder("Creature Details");
        border.setTitleFont(new Font("Arial", Font.BOLD, 18));
        border.setTitleColor(Color.PINK); // Set title font color to pink
        detailsPanel.setBorder(border);
    
        // Add the details panel to the root panel with a vertical space
        add(Box.createVerticalStrut(10));
        add(detailsPanel, BorderLayout.SOUTH);
    
        // Set the content pane to display creature details
        revalidate();
        repaint();
    }

    /**
     * Sets the image view for the active creature.
     *
     * @param  None
     * @return None
     */
    private void setImageViewForActiveCreature() {
        Creature activeCreature = inventory.getActiveCreature();
        if (activeCreature != null) {
            String imagePath = "C:\\Users\\angieolonan\\Desktop\\CCPROG3MP\\Creatures\\" +
                    activeCreature.getFamily() + "\\" +
                    activeCreature.getImagePath();

            try {
                ImageIcon imageIcon = new ImageIcon(imagePath);
                Image image = imageIcon.getImage();
                Image scaledImage = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

                creatureImageView.setIcon(scaledImageIcon); // Update the existing JLabel
                creatureImageView.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // Add left padding

                System.out.println("Image Path: " + imagePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        creatureImageView.revalidate();
        creatureImageView.repaint();
    }

    /**
     * Show the change active creature dialog.
     *
     * @return         	none
     */
    private void showChangeActiveCreatureDialog() {
        List<Creature> availableCreatures = getAvailableCreatures();

        if (availableCreatures.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No other creatures available for swapping.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        List<String> choices = new ArrayList<>();
        for (Creature creature : availableCreatures) {
            choices.add(creature.getName());
        }

        String[] choicesArray = choices.toArray(new String[0]);

        String result = (String) JOptionPane.showInputDialog(
                this,
                "Select a new active creature:",
                "Change Active Creature",
                JOptionPane.QUESTION_MESSAGE,
                null,
                choicesArray,
                choicesArray[0]
        );

        if (result != null) {
            Creature newActiveCreature = getCreatureByName(result);
            JOptionPane.showMessageDialog(this, "You selected " + newActiveCreature.getName() + " as your new active creature.", "Information", JOptionPane.INFORMATION_MESSAGE);
            inventory.setActiveCreature(newActiveCreature);
            updateCreatureList(); // Update the JList with new data
            setImageViewForActiveCreature();
            displayCreatureDetails(newActiveCreature);
            // Trigger the method in PokemonGame to handle navigation back to the main menu
            pokemonGame.goBackToMainMenu();
        }
    }

    /**
     * Retrieves a list of available creatures.
     *
     * @return  a list of available creatures
     */
    private List<Creature> getAvailableCreatures() {
        List<Creature> availableCreatures = new ArrayList<>();

        for (Creature creature : inventory.getAllCreatures()) {
            if (!creature.equals(inventory.getActiveCreature())) {
                availableCreatures.add(creature);
            }
        }
        return availableCreatures;
    }

    /**
     * Returns a Creature object with the specified name if it exists in the inventory.
     *
     * @param  name  the name of the Creature to search for
     * @return       the Creature object with the specified name, or null if not found
     */
    private Creature getCreatureByName(String name) {
        for (Creature creature : inventory.getAllCreatures()) {
            if (creature.getName().equals(name)) {
                return creature;
            }
        }
        return null;
    }

    /**
     * Styles a button by customizing its appearance.
     *
     * @param  button   the button to be styled
     */
    private void styleButton(JButton button) {
        // Customize the button appearance
        button.setBackground(Color.PINK); // Set background color to pink
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    /**
     * Sets up the UI for the Java function.
     *
     * @param  none  No parameters are required.
     * @return       No return value.
     */
    public void setupUI() {
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
        });
    }

    /**
     * A description of the entire Java function.
     *
     * @param  args  an array of strings representing the command line arguments
     */
    public static void main(String[] args) {
        CreatureInventoryApp app = new CreatureInventoryApp(new Inventory());
        app.setupUI();
    }
}
