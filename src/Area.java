import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Area {
    private int[][] tiles;
    private int currentX, currentY;
    private int areaLevel;
    private List<String> availableCreatures;
    protected Random random;

    public Area(int nRow, int nCol) {
        tiles = new int[nRow][nCol];
        currentX = 0;
        currentY = 0;
    
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                tiles[i][j] = 0;
            }
        }
        // Set the initial player position in the tiles array
        tiles[currentY][currentX] = 1;
    }

    public Area(int areaLevel, List<String> availableCreatures) {
        this.areaLevel = areaLevel;
        this.availableCreatures = availableCreatures;
        this.random = new Random();
    }

    
    /**
     * Retrieves the value of the currentX variable.
     *
     * @return  the value of the currentX variable
     */
    public int getCurrentX() {
        return currentX;
    }

    
    /**
     * Sets the value of currentX.
     *
     * @param  currentX  the new value for currentX
     */
    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    
    /**
     * Retrieves the current value of the currentY variable.
     *
     * @return the current value of currentY
     */
    public int getCurrentY() {
        return currentY;
    }

    
    /**
     * Sets the value of the currentY variable.
     *
     * @param currentY  the new value for the currentY variable
     */
    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    
    /**
     * Retrieves the tiles from the class.
     *
     * @return  a 2D array of integers representing the tiles
     */
    public int[][] getTiles() {
        return tiles;
    }

    /**
     * Displays the current area, including the player's position.
     *
     * @param  None
     * @return None
     */
    public void displayArea() {
        System.out.println("Current Area:");
    
        for (int i = 0; i < tiles[0].length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (i == currentX && j == currentY) {
                    System.out.print("[X]"); // Player's position
                } else {
                    System.out.print("[ ]"); // Empty tile
                }
            }
            System.out.println(); // Move to the next row
        }
    
        System.out.println("Player's position: (" + currentX + ", " + currentY + ")");
    }

    /**
     * Determines if the object can move up.
     *
     * @return  true if the object can move up, false otherwise
     */
    public boolean canMoveUp(){
        int newCol = currentY - 1;
        return newCol >= 0;
    }

    /**
     * Moves the object up if it is possible.
     */
    public void moveUp(){
        if(canMoveUp()){
        currentY--;
        }
    }

    /**
     * Check if the current position can move down.
     *
     * @return  true if the current position can move down, false otherwise.
     */
    public boolean canMoveDown(){
        int newCol = currentY + 1;
        return newCol < tiles[0].length;
    }

    /**
     * Moves the object down if possible.
     */
    public void moveDown(){
        if(canMoveDown()){
            currentY++;
        }
    }

    /**
     * Determines if the object can move to the left.
     *
     * @return  true if the object can move to the left, false otherwise.
     */
    public boolean canMoveLeft(){
        int newRow = currentX - 1;
        return newRow >= 0;
    }

    /**
     * Moves the object to the left if possible.
     */
    public void moveLeft() {
         if(canMoveLeft()){
            currentX--;
        }
    }
    
    /**
     * Determines if the object can move to the right.
     *
     * @return true if the object can move to the right, false otherwise
     */
    public boolean canMoveRight(){
        int newRow = currentX + 1;
        return newRow < tiles.length;
    }

    /**
     * Moves the object to the right if it is able to.
     *
     * @param  None    This function does not take any parameters.
     * @return None    This function does not return anything.
     */
    public void moveRight(){
        if(canMoveRight()){
            currentX++;
        }
    }

    /**
     * Generates a string representation of the area.
     *
     * @return a string representing the area with the player's position marked with "[X]" and empty tiles marked with "[ ]"
     */
    public String getAreaString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tiles[0].length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (i == currentX && j == currentY) {
                    result.append("[X]"); // Player's position
                } else {
                    result.append("[ ]"); // Empty tile
                }
            }
            result.append("\n");
        }
    
        return result.toString();
    }

    /**
     * Returns a random creature from the given list of creatures.
     *
     * @param  creatures  the list of creatures to choose from
     * @return            a randomly selected creature from the list
     */
    public Creature getRandomCreature(ArrayList<Creature> creatures) {
        return getRandomCreature(creatures);
    }

    /**
     * Determine if a chance encounter with a creature occurs.
     *
     * @return  true if a chance encounter occurs, false otherwise
     */
    public boolean chanceEncounterCreature() {
        int chance = random.nextInt(100);
        return chance <= 40;
    }

    /**
     * Generates a random encounter with a creature.
     *
     * @return          The encountered creature.
     * @throws IllegalStateException    If the generated random number is unexpected.
     */
    public Creature encounterCreature() {
        int el = random.nextInt(3) + 1;

        switch (el) {
            case 1:
                return encounterCreatureEL1();
            case 2:
                return encounterCreatureEL2();
            case 3:
                return encounterCreatureEL3();
            default:
                throw new IllegalStateException("Unexpected value: " + el);
        }
    }

    
    /**
     * Returns a randomly selected Creature from a list of level creatures.
     *
     * @param  levelCreatures  the list of level creatures
     * @return                 the randomly selected Creature
     */
    protected Creature encounterCreatureEL1() {
        List<Creature> levelCreatures = new ArrayList<>();
        
        levelCreatures.addAll(Arrays.asList(
                        new Creature("Strawander", "Fire", "A", 1, 50, "Creatures/Family A/Strawander.jpg"),
                        new Creature("Chocowool", "Fire", "B", 1, 50, "Creatures/Family B/Chocowool.jpg"),
                        new Creature("Parfwit", "Fire", "C", 1, 50, "Creatures/Family C/Parfwit.jpg"),
                        new Creature("Brownisaur", "Grass", "D", 1, 50, "Creatures/Family D/Brownisaur.jpg"),
                        new Creature("Frubat", "Grass", "E", 1, 50, "Creatures/Family E/Frubat.jpg"),
                        new Creature("Malts", "Grass", "F", 1, 50, "Creatures/Family F/Malts.jpg"),
                        new Creature("Squirpie", "Water", "G", 1, 50, "Creatures/Family G/Squirpie.jpg"),
                        new Creature("Chocolite", "Water", "H", 1, 50, "Creatures/Family H/Chocolite.jpg"),
                        new Creature("Oshacone", "Water", "I", 1, 50, "Creatures/Family I/Oshocone.jpg")
                ));
                return getRandomCreature(levelCreatures);
    }
    
    /**
     * Returns a random Creature object from a list of level creatures.
     *
     * @param  levelCreatures    the list of level creatures
     * @return                   a random Creature object
     */
    protected Creature encounterCreatureEL2() {
        List<Creature> levelCreatures = new ArrayList<>();
                levelCreatures.addAll(Arrays.asList(
                    new Creature("Strawleon", "Fire", "A", 2, 50, "Creatures/Family A/Strawleon.jpg"),
                    new Creature("Chocofluff", "Fire", "B", 2, 50, "Creatures/Family B/Chocofluff.jpg"),
                    new Creature("Parfure", "Fire", "C", 2, 50, "Creatures/Family C/Parfure.jpg"),
                    new Creature("Chocosaur", "Grass", "D", 2, 50, "Creatures/Family D/Chocosaur.jpg"),
                    new Creature("Golberry", "Grass", "E", 2, 50, "Creatures/Family E/Golberry.jpg"),
                    new Creature("Kirlicake", "Grass", "F", 2, 50, "Creatures/Family F/Kirlicake.jpg"),
                    new Creature("Tartortle", "Water", "G", 2, 50, "Creatures/Family G/Tartortle.jpg"),
                    new Creature("Chocolish", "Water", "H", 2, 50, "Creatures/Family H/Chocolish.jpg"),
                    new Creature("Dewice", "Water", "I", 2, 50, "Creatures/Family I/Dewice.jpg")
                ));
                return getRandomCreature(levelCreatures);
    }
            
    /**
     * Retrieves a random creature from a list of level creatures.
     *
     * @param  levelCreatures  the list of level creatures
     * @return                 a random creature
     */
    protected Creature encounterCreatureEL3() {
        List<Creature> levelCreatures = new ArrayList<>();
                levelCreatures.addAll(Arrays.asList(
                    new Creature("Strawizard", "Fire", "A", 3, 50, "Creatures/Family A/Strawizard.jpg"),
                    new Creature("Candaros", "Fire", "B", 3, 50, "Creatures/Family B/Candaros.jpg"),
                    new Creature("Parfelure", "Fire", "C", 3, 50, "Creatures/Family C/Parfelure.jpg"),
                    new Creature("Fudgasaur", "Grass", "D", 3, 50, "Creatures/Family D/Fudgasaur.jpg"),
                    new Creature("Croberry", "Grass", "E", 3, 50, "Creatures/Family E/Croberry.jpg"),
                    new Creature("Velvevoir", "Grass", "F", 3, 50, "Creatures/Family F/Velvevoir.jpg"),
                    new Creature("Piestoise", "Water", "G", 3, 50, "Creatures/Family G/Piestoise.jpg"),
                    new Creature("Icesundae", "Water", "H", 3, 50, "Creatures/Family H/Icesundae.jpg"),
                    new Creature("Samurcone", "Water", "I", 3, 50, "Creatures/Family I/Samurcone.jpg")
                ));
                return getRandomCreature(levelCreatures);
    }
    
    /**
     * Generates a random creature from the given list of creatures.
     *
     * @param  creatures     the list of creatures to choose from
     * @return               the randomly selected creature
     */
    protected Creature getRandomCreature(List<Creature> creatures) {
        int randomIndex = random.nextInt(creatures.size());
        return creatures.get(randomIndex);
    }

    /**
     * Retrieves the area level.
     *
     * @return the area level
     */
    public int getAreaLevel() {
        return areaLevel;
    }
}
