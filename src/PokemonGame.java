import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class PokemonGame {
    private Inventory currentInventory;
    private Area currentArea;
    private Scanner scan;
    private Area area1;
    private Area area2;
    private Area area3;

    public PokemonGame() {
        currentInventory = new Inventory();
        currentArea = new Area(5,1);
        scan = new Scanner(System.in);

        area1 = new Area(5, 1);
        area2 = new Area(3, 3);
        area3 = new Area(4, 4);
    }

    /**
     * Executes the main menu of the game.
     *
     * @param  None
     * @return None
     */
    public void mainMenu() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("\nPRESS ANY KEY TO START THE GAME");
            scanner.nextLine();
    
            System.out.println("「 ✦ Welcome to the World of Pokemon! ✦ 」\n");
    
            selectStarterCreature();
    
            while (true) {
                System.out.println("✦•······················•✦•······················•✦");
                System.out.println("\nMain Menu\n");
                System.out.println("1: View Inventory");
                System.out.println("2: Explore Area");
                System.out.println("3: Evolve Creature");
                System.out.println("4: Exit Game");
                System.out.println("\n✦•······················•✦•······················•✦");
    
                int userChoice = userInput.getUserChoice(1, 4); // only 1,2,3,4 options are allowed
    
                switch (userChoice) {
                    case 1:
                        viewInventory();
                        break;
                    case 2:
                        exploreArea();
                        break;
                    case 3:
                        evolveCreature();
                        break;
                    case 4:
                        exitGame();
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        }
    }
    
    /**
     * Selects a starter creature for the player.
     *
     * @param  None
     * @return None
     */
    private void selectStarterCreature() {
        int i;
        Creature starterCreature1 = new Creature("Strawander", "Fire", "A", 1, 100);
        Creature starterCreature2 = new Creature("Chocowool", "Fire", "B", 1, 100);
        Creature starterCreature3 = new Creature("Parfwit", "Fire", "C", 1, 100);
        Creature starterCreature4 = new Creature("Brownisaur", "Grass", "D", 1, 100);
        Creature starterCreature5 = new Creature("Frubat", "Grass", "E", 1, 100);
        Creature starterCreature6 = new Creature("Malts", "Grass", "F", 1, 100);
        Creature starterCreature7 = new Creature("Squirpie", "Water", "G", 1, 100);
        Creature starterCreature8 = new Creature("Chocolite", "Water", "H", 1, 100);
        Creature starterCreature9 = new Creature("Oshacone", "Water", "I", 1, 100);

        // Display a list of EL1 creatures for the user to choose from
        ArrayList<Creature> el1Creatures = new ArrayList<>();
        el1Creatures.add(starterCreature1);
        el1Creatures.add(starterCreature2);
        el1Creatures.add(starterCreature3);
        el1Creatures.add(starterCreature4);
        el1Creatures.add(starterCreature5);
        el1Creatures.add(starterCreature6);
        el1Creatures.add(starterCreature7);
        el1Creatures.add(starterCreature8);
        el1Creatures.add(starterCreature9);

        System.out.println("\nSelect a Level 1 (EL1) starter creature:");
        for (i = 0; i < el1Creatures.size(); i++) {
            System.out.println((i + 1) + ": " + el1Creatures.get(i).getName());
        }
    
        int starterChoice = userInput.getUserChoice(1, el1Creatures.size());
        Creature starterCreature = el1Creatures.get(starterChoice - 1);

        // Add the starter creature to the inventory and set it as active
        currentInventory.addCreature(starterCreature);
        currentInventory.setActiveCreature(starterCreature);

        System.out.println("You selected " + starterCreature.getName() + " as your starter creature!\n");
    }

    // -----------------------------VIEW INVENTORY-----------------------------

    /**
     * View the inventory and display the active creature, captured creatures,
     * and options to change the active creature or go back to the main menu.
     *
     * @param  None
     * @return None
     */
    private void viewInventory() {
        ArrayList<Creature> allCreatures = currentInventory.getAllCreatures();
        Creature activeCreature = currentInventory.getActiveCreature();// Get current active creature
        int i;

        System.out.println("\nINVENTORY:\n");
        if(activeCreature != null) {
            System.out.println("\nActive Creature:\n");
            System.out.println(activeCreature.toString());
        } else {
            System.out.println("\nNo Active Creatures.");
        }

        if(allCreatures.isEmpty()) {
            System.out.println("\nEmpty Inventory");
        } else {
            System.out.println("\nCaptured Creatures:");

            for(i = 0; i < allCreatures.size(); i++) {
                Creature creature = allCreatures.get(i);
                System.out.println((i + 1) + ": " + creature.getName() + " (EL" + creature.getEvolutionLevel() + ")");
            }
        }

            System.out.println("\n");
            System.out.println("[0] Change Active Creature\n");
            System.out.println("[1] Back to Main Menu\n");

            int choice = userInput.getUserChoice(0, 1);

            if(choice == 0){
                changeActiveCreature();
            }
    }

    /**
     * Changes the active creature in the inventory.
     *
     * @return         	void
     */
    private void changeActiveCreature() {
        System.out.println("\nSelect a new active creature from your inventory:");
    
        int optionNumber = 1;
        List<Creature> availableCreatures = new ArrayList<>();

    
        for (Creature creature : currentInventory.getAllCreatures()) {
            if (!creature.equals(currentInventory.getActiveCreature())) {
                availableCreatures.add(creature);
                System.out.println("[" + optionNumber + "] " + creature.getName());
                optionNumber++;
            }
        }
    
        if (availableCreatures.isEmpty()) {
            System.out.println("\nNo other creatures available for swapping.");
            return;
        }
    
        int choice = userInput.getUserChoice(1, optionNumber - 1);
    
        if (choice >= 1 && choice <= availableCreatures.size()) {
            Creature newActiveCreature = availableCreatures.get(choice - 1);
            System.out.println("\nYou selected " + newActiveCreature.getName() + " as your new active creature.");
            currentInventory.setActiveCreature(newActiveCreature);
        } else {
            System.out.println("\nInvalid choice. Please select a valid option.");
        }
    }

// -----------------------------EXPLORE AREA-----------------------------
    
    /**
     * Executes the exploreArea function.
     *
     * @param  None
     * @return None
     */
    private void exploreArea() {
        System.out.println("✦•······················•✦•······················•✦");
        System.out.println("\nSelect an area to explore:");
        System.out.println("[1] Area 1 (5x1)");
        System.out.println("[2] Area 2 (3x3)");
        System.out.println("[3] Area 3 (4x4)");
        System.out.println("\n✦•······················•✦•······················•✦");
        System.out.print("\nEnter your choice: ");
        
        int areaChoice = scan.nextInt();
        
        Area selectedArea;
        
        switch (areaChoice) {
            case 1:
                selectedArea = area1;
                break;
            case 2: 
                selectedArea = area2;
                break;
            case 3:
                selectedArea = area3;
                break;
            default:
                System.out.println("Invalid choice. Returning to the main menu...");
                return;
            }
            
            boolean exploring = true;

            System.out.println("\nExploring Area...\n");
            selectedArea.displayArea();
            
            while(exploring){
                Direction moveUser = getPlayerMoveDirection();
                
                if(moveUser == null){
                    System.out.println("Exiting the area...");
                    return;
                }
                movePlayer(moveUser, selectedArea);
                selectedArea.displayArea();

                if(selectedArea.chanceEncounterCreature()){
                    handleCreatureEncounter();
                    exploring = false;
                }
            }
    }

    /**
     * Retrieves the current creature in the area based on the current area level.
     *
     * @param  creatures  the list of all creatures
     * @return            the current creature in the area or null if the current area level is invalid
     */
    private Creature getCurrentAreaCreature(ArrayList<Creature> creatures) {
        int currentAreaLevel = currentArea.getAreaLevel();
        
        switch (currentAreaLevel) {
        case 1:
            return getRandomCreatureFromList(creatures, area1);
        case 2:
            return getRandomCreatureFromList(creatures, area2);
        case 3:
            return getRandomCreatureFromList(creatures, area3);
        default:
            return null;
        }
    }

    /**
     * Returns a random Creature object from the given ArrayList of Creature objects using the getRandomCreature method of the specified Area object.
     *
     * @param  creatures  the ArrayList of Creature objects from which to select a random creature
     * @param  area       the Area object used to generate the random creature
     * @return            the randomly selected Creature object
     */
    private Creature getRandomCreatureFromList(ArrayList<Creature> creatures, Area area) {
        return area.getRandomCreature(creatures);
    }

    /**
    * Initializes a creature encounter in the game. 
    * 
    * @param  None 
    *         This function does not take any parameters.
    * @return None 
    *         This function does not return any value.
    */
    private void handleCreatureEncounter() {
        ArrayList<Creature> creatures = new ArrayList<>();  // Initialize the creatures variable
    
        PokemonGame pokemonGame = new PokemonGame();  // Create an instance of the PokemonGame class
        pokemonGame.getCurrentAreaCreature(creatures);
    
        System.out.println("✦•······················•✦•······················•✦");
        System.out.println("\nCreature Encountered!");
        System.out.println("\n✦•······················•✦•······················•✦");

        Creature userCreature = currentInventory.getActiveCreature();
    
        // Initialize currentArea with the required arguments
        List<String> availableCreatures = new ArrayList<>();  // Provide the available creatures
        int areaLevel = 1;  // Provide the area level
        Area currentArea = new Area(areaLevel, availableCreatures);
    
        Creature enemyCreature = currentArea.encounterCreature();
    
        // Start a battle between the user's creature and the enemy creature
        BattlePhase battle = new BattlePhase(userCreature, enemyCreature, currentInventory);
        battle.beginBattle(enemyCreature);
    }
    
// -----------------------------VIEW INVENTORY-----------------------------
    
    /**
    * Evolves a creature by selecting two creatures from the inventory and initiating the evolution phase.
    *
    * @return     void
    */
    private void evolveCreature(){
        ArrayList<Creature> allCreatures = currentInventory.getAllCreatures();

        if(currentInventory.getAllCreatures().size() < 2){
            System.out.println("Insufficient creatures in the inventory to evolve.");
            return;
        }
        System.out.println("\nSelect 2 creatures to evolve");
        displayCreaturesInventory();

        int creatureChoice1 = userInput.getUserChoice(1, allCreatures.size()) - 1;
        int creatureChoice2 = userInput.getUserChoice(1, allCreatures.size()) - 1;

        Creature creature1 = allCreatures.get(creatureChoice1);
        Creature creature2 = allCreatures.get(creatureChoice2);

        EvolvePhase evolvePhase = new EvolvePhase(creature1, creature2, currentInventory);
        evolvePhase.evolve();
    }

    /**
     * Display the inventory of creatures.
     *
     * @param  none
     * @return none
     */
    public void displayCreaturesInventory(){
        ArrayList<Creature> allCreatures = currentInventory.getAllCreatures();
        int i = 1;
        for(Creature creature : allCreatures){
            System.out.println("[" + i + "]" + creature.getName() + " EL" + creature.getEvolutionLevel());
            i++;
        }
    }

// ---------------------------------USER MOVE---------------------------------
    
    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }

    /**
     * Retrieves the player's move direction based on user input.
     *
     * @return Direction  the chosen direction (UP, DOWN, LEFT, RIGHT)
     */
    private Direction getPlayerMoveDirection() {
        Direction chosenDirection = null;
        
        while (chosenDirection == null) {
            System.out.println("\nMake a Move:");
            System.out.print("[1] Move UP\n[2] Move DOWN\n[3] Move LEFT\n[4] Move RIGHT\n[5] Exit Area\n");
            System.out.print("Enter your move: ");
            
            int input = scan.nextInt();
            
            switch (input) {
                case 1:
                    chosenDirection = Direction.UP;
                    break;
                case 2:
                    chosenDirection = Direction.DOWN;
                    break;
                case 3:
                    chosenDirection = Direction.LEFT;
                    break;
                case 4:
                    chosenDirection = Direction.RIGHT;
                    break;
                case 5:
                    return null; // Return null to indicate the user's choice to exit the area
                default:
                    System.out.println("Invalid direction. Out of bounds!");
                    break;
            }
        }
        return chosenDirection;
    }


    private void movePlayer(Direction moveUser, Area selectedArea) {
        switch (moveUser) {
            case UP:
                if (selectedArea.canMoveUp()) {
                    selectedArea.moveUp();
                } else {
                    System.out.println("Cannot move up. Out of bounds!");
                }
                break;

            case DOWN:
                if (selectedArea.canMoveDown()) {
                    selectedArea.moveDown();
                } else {
                    System.out.println("Cannot move down. Out of bounds!");
                }
                break;

            case LEFT:
                if (selectedArea.canMoveLeft()) {
                    selectedArea.moveLeft();
                } else {
                    System.out.println("Cannot move left. Out of bounds!");
                }
                break;

            case RIGHT:
                if (selectedArea.canMoveRight()) {
                    selectedArea.moveRight();
                } else {
                    System.out.println("Cannot move right. Out of bounds!");
                }
                break;
        }
    }

// -----------------------------EXIT-----------------------------
    /**
    * A description of the entire Java function.
    *
    * @param  None        No parameters.
    * @return void        No return value.
    */
    private void exitGame() {
        System.out.println("Exiting the game. Goodbye!");
        scan.close();
        System.exit(0); 
    }
}
