import javax.swing.*;
import java.awt.*;

public class BattlePhaseGUI extends JFrame {
    private static Creature activeCreature;
    private static Creature enemyCreature;
    private static Inventory currentInventory;

    private BattlePhase battlePhase;
    //private PokemonGame pokemon;

    private JProgressBar playerHealthBar;
    private JProgressBar enemyHealthBar;

    private JLabel enemyImageLabel;
    private JLabel enemyNameLabel;

    private JLabel playerImageLabel;
    private JLabel playerNameLabel;

    public BattlePhaseGUI(BattlePhase battlePhase) {
        this.battlePhase = battlePhase;

        setTitle("BattlePhase GUI");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create main layout
        JPanel root = new JPanel(new BorderLayout());
        root.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create enemy creature display
        JPanel enemyCreaturePanel = createCreaturePanel(battlePhase.enemyCreature, "Enemy Creature");
        root.add(enemyCreaturePanel, BorderLayout.NORTH);

        // Create player creature display
        JPanel playerCreaturePanel = createCreaturePanel(battlePhase.userCreature, "Active Creature");
        root.add(playerCreaturePanel, BorderLayout.SOUTH);

        // Create action buttons
        JPanel actionButtonsPanel = createActionButtons();
        root.add(actionButtonsPanel, BorderLayout.EAST);

        add(root);
        setVisible(true);
    }

    private JPanel createCreaturePanel(Creature creature, String label) {
        JPanel creaturePanel = new JPanel(new BorderLayout());
        creaturePanel.setLayout(new BoxLayout(creaturePanel, BoxLayout.Y_AXIS));
        creaturePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Display creature image
        ImageIcon creatureImage = new ImageIcon(creature.getImagePath());
        
        JLabel imageLabel = new JLabel(creatureImage);
        creaturePanel.add(imageLabel, BorderLayout.CENTER);

        // Display health bar
        JProgressBar healthBar = new JProgressBar();

        int healthValue = creature.getHealth();
        healthBar.setValue(healthValue);
        healthBar.setStringPainted(true);
        healthBar.setForeground(Color.GREEN);
        creaturePanel.add(healthBar);

        // Display creature name and evolution level
        JLabel creatureInfo = new JLabel(creature.getName() + " (Level " + creature.getEvolutionLevel() + ", Type: " + creature.getType() + ")");
        creatureInfo.setFont(new Font("Arial", Font.PLAIN, 16));
        creaturePanel.add(creatureInfo);

        // Display label above the creature name
        JLabel nameLabel = new JLabel(label);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        creaturePanel.add(nameLabel);

        // Set health bars and labels for player and enemy creatures
        if (creature.equals(battlePhase.userCreature)) {
            playerHealthBar = healthBar;
            playerImageLabel = imageLabel;
            playerNameLabel = creatureInfo;
        } else if (creature.equals(battlePhase.enemyCreature)) {
            enemyHealthBar = healthBar;
            enemyImageLabel = imageLabel;
            enemyNameLabel = creatureInfo;
        }

        return creaturePanel;
    }

    private JPanel createActionButtons() {
        JPanel actionButtonsPanel = new JPanel();
        actionButtonsPanel.setLayout(new BoxLayout(actionButtonsPanel, BoxLayout.Y_AXIS));
        actionButtonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Attack button
        JButton attackButton = new JButton("Attack");
        attackButton.addActionListener(e -> {
            battlePhase.performAttack(battlePhase.userCreature, battlePhase.enemyCreature);
            updateHealthBar();  // Update the health bar after the attack
        });
        actionButtonsPanel.add(attackButton);

        // Swap button
        JButton swapButton = new JButton("Swap");
        swapButton.addActionListener(e -> battlePhase.performSwap(battlePhase.getCurrentInventory()));
        actionButtonsPanel.add(swapButton);

        // Capture button
        JButton captureButton = new JButton("Capture");
        captureButton.addActionListener(e -> battlePhase.performCatch(battlePhase.enemyCreature));
        actionButtonsPanel.add(captureButton);

        // Escape button
        JButton escapeButton = new JButton("Run");
        escapeButton.addActionListener(e -> {
            // Handle escape action (e.g., return to main menu)
        });
        actionButtonsPanel.add(escapeButton);

        return actionButtonsPanel;
    }

    // Update health bar, creature info labels, and images after each action
    private void updateHealthBar() {
        updateHealthBar(battlePhase.userCreature, playerHealthBar);
        updateHealthBar(battlePhase.enemyCreature, enemyHealthBar);

        updateCreatureInfoLabels(battlePhase.userCreature, playerNameLabel, playerImageLabel);
        updateCreatureInfoLabels(battlePhase.enemyCreature, enemyNameLabel, enemyImageLabel);
    }

    private void updateHealthBar(Creature creature, JProgressBar healthBar) {
        int healthValue = creature.getHealth();
        healthBar.setValue(healthValue);
    }

    private void updateCreatureInfoLabels(Creature creature, JLabel nameLabel, JLabel imageLabel) {
        nameLabel.setText(creature.getName() + " (Level " + creature.getEvolutionLevel() + ", Type: " + creature.getType() + ")");

        ImageIcon creatureImage = new ImageIcon(creature.getImagePath());
        imageLabel.setIcon(creatureImage);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BattlePhase battlePhase = new BattlePhase(activeCreature, enemyCreature, currentInventory);
            BattlePhaseGUI battlePhaseGUI = new BattlePhaseGUI(battlePhase);
            battlePhaseGUI.setVisible(true);
        });
    }
}