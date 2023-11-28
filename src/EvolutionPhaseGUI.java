import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EvolutionPhaseGUI extends JFrame {

    private Creature creature1;
    private Creature creature2;
    private EvolvePhase evolvePhase;

    private JComboBox<Creature> creatureChoiceBox1;
    private JComboBox<Creature> creatureChoiceBox2;

    private JLabel creatureImageView1;
    private JLabel creatureImageView2;

    public EvolutionPhaseGUI() {
        // Sample creatures
        creature1 = new Creature("Strawander", "Fire", "Family A", 1, 100, "Strawander.jpg");
        creature2 = new Creature("Straweleon", "Fire", "Family A", 2, 100, "Straweleon.jpg");

        // Initialize EvolvePhase
        evolvePhase = new EvolvePhase(creature1, creature2, new Inventory());

        initComponents();
    }

    /**
     * Initializes the components of the Evolution Screen.
     *
     * @param  None
     * @return None
     */
    private void initComponents() {
        setTitle("Evolution Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        creatureChoiceBox1 = createCreatureChoiceBox();
        creatureChoiceBox2 = createCreatureChoiceBox();

        creatureImageView1 = createCreatureImageView(creature1);
        creatureImageView2 = createCreatureImageView(creature2);

        JButton chooseCreature1Button = new JButton("Choose Creature ONE");
        chooseCreature1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseCreature(creatureChoiceBox1, creatureImageView1);
            }
        });

        JButton chooseCreature2Button = new JButton("Choose Creature TWO");
        chooseCreature2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseCreature(creatureChoiceBox2, creatureImageView2);
            }
        });

        JButton evolveButton = new JButton("Start Evolution");
        evolveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evolvePhase.evolve();
            }
        });

        JPanel creatureBox1 = new JPanel();
        creatureBox1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        creatureBox1.add(creatureChoiceBox1);
        creatureBox1.add(creatureImageView1);
        creatureBox1.add(chooseCreature1Button);

        JPanel creatureBox2 = new JPanel();
        creatureBox2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        creatureBox2.add(creatureChoiceBox2);
        creatureBox2.add(creatureImageView2);
        creatureBox2.add(chooseCreature2Button);

        JPanel evolveButtonBox = new JPanel();
        evolveButtonBox.setLayout(new FlowLayout(FlowLayout.CENTER));
        evolveButtonBox.add(evolveButton);

        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        root.add(creatureBox1);
        root.add(creatureBox2);
        root.add(evolveButtonBox);

        add(root);

        setVisible(true);
    }

    /**
     * Creates a JComboBox of Creature objects.
     *
     * @return         	The created JComboBox of Creature objects.
     */
    private JComboBox<Creature> createCreatureChoiceBox() {
        JComboBox<Creature> choiceBox = new JComboBox<>();
        // Add user-owned creatures to the choice box
        choiceBox.addItem(new Creature("Bulbasaur", "Grass", "D", 1, 100, "bulbasaur.jpg"));
        choiceBox.addItem(new Creature("Squirtle", "Water", "G", 1, 100, "squirtle.jpg"));
        return choiceBox;
    }

    /**
     * Creates a JLabel component containing the image of the given creature.
     *
     * @param  creature  the creature object from which to retrieve the image path
     * @return           the created JLabel component
     */
    private JLabel createCreatureImageView(Creature creature) {
        ImageIcon imageIcon = new ImageIcon(creature.getImagePath());
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

        JLabel imageView = new JLabel(scaledImageIcon);
        return imageView;
    }

    /**
     * Updates the image and other information for the selected creature.
     *
     * @param  choiceBox   the JComboBox containing the selectable creatures
     * @param  imageView   the JLabel used to display the creature image
     */
    private void chooseCreature(JComboBox<Creature> choiceBox, JLabel imageView) {
        Creature selectedCreature = (Creature) choiceBox.getSelectedItem();
        if (selectedCreature != null) {
            // Update the image and other information for the selected creature
            ImageIcon imageIcon = new ImageIcon(selectedCreature.getImagePath());
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

            imageView.setIcon(scaledImageIcon);
            // Add more updates based on your requirements
        }
    }

    /**
     * This function is the entry point for the Java program.
     *
     * @param  args  the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EvolutionPhaseGUI();
        });
    }
}
