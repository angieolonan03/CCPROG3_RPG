import java.awt.*;
import javax.swing.*;

public class AreaDisplayGUI extends JFrame {
    private Area currentArea;
    private JPanel gridPanel;
    private JLabel[][] gridLabels;

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        EXIT_AREA
    }

    public AreaDisplayGUI() {
        this.currentArea = selectArea();

        setTitle("Explore Area");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createMoveButton("Up", Direction.UP));
        buttonPanel.add(createMoveButton("Down", Direction.DOWN));
        buttonPanel.add(createMoveButton("Left", Direction.LEFT));
        buttonPanel.add(createMoveButton("Right", Direction.RIGHT));
        buttonPanel.add(createMoveButton("Exit Area", Direction.EXIT_AREA));

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Use the dimensions of the selected area for the initial grid
        gridPanel = new JPanel(new GridLayout(currentArea.getTiles().length, currentArea.getTiles()[0].length));

        gridLabels = new JLabel[currentArea.getTiles().length][currentArea.getTiles()[0].length];

        for (int i = 0; i < currentArea.getTiles().length; i++) {
            for (int j = 0; j < currentArea.getTiles()[0].length; j++) {
                gridLabels[i][j] = new JLabel();
                gridPanel.add(gridLabels[i][j]);
            }
        }


        mainPanel.add(gridPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        setVisible(true);
        updateGridPanel();
    }

    /**
     * Creates a JButton with the given text and direction.
     *
     * @param  text       the text to be displayed on the button
     * @param  direction  the direction associated with the button
     * @return            the created JButton
     */
    private JButton createMoveButton(String text, Direction direction) {
        JButton button = new JButton(text);
        button.addActionListener(e -> {
            if (direction == Direction.EXIT_AREA) {
                dispose();
            } else {
                performMove(direction);
                updateGridPanel();
            }
        });
        return button;
    }

    /**
     * Selects an area to explore based on user input.
     *
     * @return  the selected Area object based on user input
     */
    private Area selectArea() {
        String[] options = {"Area 1", "Area 2", "Area 3"};
        int choice = JOptionPane.showOptionDialog(
                this,
                "Select an area to explore:",
                "Area Selection",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        switch (choice) {
            case 0:
                return new FirstArea(1, 5);
            case 1:
                return new SecondArea(3, 3);
            case 2:
                return new ThirdArea(4, 4);
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

    /**
     * Performs a move in the specified direction.
     *
     * @param  direction	the direction to move
     */
    private void performMove(Direction direction) {
        switch (direction) {
            case UP:
                currentArea.moveUp();
                break;
            case DOWN:
                currentArea.moveDown();
                break;
            case LEFT:
                currentArea.moveLeft();
                break;
            case RIGHT:
                currentArea.moveRight();
                break;
            default:
                break;
        }
    }

    /**
     * Updates the grid panel based on the current area's tiles and the player's position.
     *
     * @param  None
     * @return None
     */
    public void updateGridPanel() {
        int[][] tiles = currentArea.getTiles();
    
        for (int i = 0; i < gridLabels.length; i++) {
            for (int j = 0; j < gridLabels[i].length; j++) {
                gridLabels[i][j].setText("[ ]");
            }
        }
    
        int playerX = currentArea.getCurrentX();
        int playerY = currentArea.getCurrentY();
    
        // Check if the area is 1D or 2D
        if (tiles.length > 0 && tiles[0].length > 0) {
            int numRows = tiles.length;
            int numCols = tiles[0].length;
    
            // Check if the player's position is within the bounds
            if (playerY >= 0 && playerY < numRows && playerX >= 0 && playerX < numCols) {
                gridLabels[playerY][playerX].setText("[X]");
            }
        } else {
            // For 1D arrays, check if the player's position is within the bounds
            if (playerX >= 0 && playerX < gridLabels[0].length) {
                gridLabels[0][playerX].setText("[X]");
            }
        }
    
        for (int i = 0; i < gridLabels.length; i++) {
            for (int j = 0; j < gridLabels[i].length; j++) {
                gridLabels[i][j].setHorizontalAlignment(JLabel.CENTER);
                gridLabels[i][j].setVerticalAlignment(JLabel.CENTER);
                gridLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }
        }
    }
    
    
    

    /**
     * Executes the main entry point of the Java application.
     *
     * @param  args  the command-line arguments passed to the application
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AreaDisplayGUI areaDisplayGUI = new AreaDisplayGUI();
            areaDisplayGUI.setVisible(true);
        });
    }
}