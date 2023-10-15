import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Area {
   private int[][] tiles; 
   private int currentX, currentY; // Current X and Y position 
   private int i, j; 
   private Creature chosenCreature;
   private int areaLevel;
   private List<String> availableCreatures;

   public Area(int areaLevel, List<String> availableCreatures){
    this.areaLevel = areaLevel;
    this.availableCreatures = availableCreatures;
   }

    public Area(int nRow, int nCol){
        tiles = new int[nRow][nCol];
        currentX = 0; // starting X position
        currentY = 0; // starting Y position

        for(i = 0; i < nRow; i++){
            for(j = 0; j < nCol; j++){
                tiles[i][j] = 0;
            }
        }
    }

    /**
     * Retrieves the area level.
     *
     * @return the area level
     */
    public int getAreaLevel(){
        return areaLevel;
    }

    /**
     * Sets the chosen creature.
     *
     * @param  chosenCreature  the creature to be set as the chosen creature
     */
    public void setChosenCreature(Creature chosenCreature) {
        this.chosenCreature = chosenCreature;
    }
    
    /**
     * Returns the chosen creature.
     *
     * @return  the chosen creature
     */
    public Creature getChosenCreature() {
        return chosenCreature;
    }

    /**
     * Returns an array of available creature levels based on the area level.
     *
     * @return an array of available creature levels
     */
    public int[] getAvailableCreatureLevels(){
        int areaLevel = getAreaLevel();
        if(areaLevel == 1){
            return new int[] { 1 };
        } else if (areaLevel == 2){
            return new int[] { 1, 2 };
        } else if (areaLevel  == 3){
            return new int[] { 1, 2, 3 };
        } else {
            return new int[0];
        }
    }
    
    /**
    * Retrieves the current value of the X coordinate.
    *
    * @return the current value of the X coordinate
    */
    public int getCurrentX(){
        return currentX;
    }

    /**
    * Sets the value of the currentX variable.
    *
    * @param  currentX  the new value for currentX
    */
    public void setCurrentX(int currentX){
        this.currentX = currentX;
    }
        
    /**
    * Retrieves the current value of the `currentY` variable.
    *
    * @return the current value of the `currentY` variable.
    */
    public int getCurrentY(){
        return currentY;
    }
            
    /**
    * Sets the value of the currentY variable.
    *
    * @param  currentY  the new value for the currentY variable
    */
    public void setCurrentY(int currentY){
        this.currentY = currentY;
    }

    /**
    * Retrieves the tiles from the object.
    *
    * @return  the tiles as a 2D array of integers
    */
    public int[][] getTiles(){
        return tiles;
    }

    /**
    * Displays the current area.
    *
    * @param  None
    * @return None
    */
    public void displayArea(){
        int i, j;         
        
        System.out.println("\nCurrent Area:");
        
        for (j = 0; j < tiles[0].length; j++) {
            for (i = 0; i < tiles.length; i++) {
                if (i == currentX && j == currentY) {
                    System.out.print("[X]"); // Player's position
                } else {
                    System.out.print("[ ]"); // Empty tile
                }
            }
            System.out.println(); 
        }
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
    * Determines if the object can move down.
    *
    * @return     true if the object can move down, false otherwise
    */
    public boolean canMoveDown(){
        int newCol = currentY + 1;
        return newCol < tiles[0].length;
    }

    /**
    * Determines if the object can move left.
    *
    * @return  true if the object can move left, false otherwise
    */
    public boolean canMoveLeft(){
        int newRow = currentX - 1;
        return newRow >= 0;
    }

    /**
    * Determines if the object can move right.
    *
    * @return  true if the object can move right, false otherwise
    */
    public boolean canMoveRight(){
        int newRow = currentX + 1;
        return newRow < tiles.length;
    }

    /**
    * Moves the object up if it is possible to do so.
    *
    * @param  None   No parameters are required.
    * @return        No return value.
    */
    public void moveUp(){
        if(canMoveUp()){
        currentY--;
        }
    }

    /**
    * Moves the object down if it is able to.
    *
    * @param  None
    * @return None
    */
    public void moveDown(){
        if(canMoveDown()){
            currentY++;
        }
    }

    /**
    * Moves the current position to the left if possible.
    */
    public void moveLeft(){
        if(canMoveLeft()){
            currentX--;
        }
    }

    /**
    * Moves the object to the right if it is able to.
    *
    */
    public void moveRight(){
        if(canMoveRight()){
            currentX++;
        }
    }

    /**
    * Generates a random creature from the given list of creatures.
    *
    * @param  creatures  the list of creatures to choose from
    * @return            the randomly selected creature
    */

    public Creature getRandomCreature(ArrayList<Creature> creatures){
        return Creature.getRanCreatureFromList(creatures);
    }

    /**
    * Generates a random chance encounter with a creature.
    *
    * @return true if the chance encounter occurs, false otherwise
    */
    public boolean chanceEncounterCreature() {
        Random random = new Random();
        int chance = random.nextInt(100); 
        
        return chance <= 40; 
    }


    /**
    * Generates a random encounter with a creature.
    *
    * @return         	The encountered creature, or null if no creatures are available.
    */
    public Creature encounterCreature() {
        List<Creature> possibleCreatures = new ArrayList<>();
        
        if(!availableCreatures.isEmpty()){
            for(String creatureName : availableCreatures){
                for(Creature creature : encounterCreaturesForAreaLevel(areaLevel)){
                    if(creature.getName().equals(creatureName)){
                        possibleCreatures.add(creature);
                    }
                }
            }
        
        } else {
            possibleCreatures.addAll(encounterCreaturesForAreaLevel(areaLevel));
        }

        if (possibleCreatures.isEmpty()) {
            // Handle the case where no creatures are available
            return null;
        } else {
            Random random = new Random();
            int randomIndex = random.nextInt(possibleCreatures.size());
            return possibleCreatures.get(randomIndex);
        }
    }

    /**
     * Generates a list of creatures for a given area level.
     *
     * @param  level  the level of the area
     * @return        a list of creatures for the given area level
     */
    private List<Creature> encounterCreaturesForAreaLevel(int level) {
        List<Creature> creatures = new ArrayList<>();
        
        switch (level) {
            case 1:
                addCreaturesForLevelOne(creatures);
                break;
            case 2:
                addCreaturesForLevelOne(creatures);
                addCreaturesForLevelTwo(creatures);
                break;
            case 3:
                addCreaturesForLevelOne(creatures);
                addCreaturesForLevelTwo(creatures);
                addCreaturesForLevelThree(creatures);
                break;
            default:
                throw new IllegalArgumentException("Invalid area level");
        }
        return creatures;
    }
        
    /**
    * Adds creatures for level one.
    *
    * @param  creatures  the list of creatures to add
    */
    private void addCreaturesForLevelOne(List<Creature> creatures) {
        creatures.addAll(Arrays.asList(
            new Creature("Strawander", "Fire", "A", 1, 50),
            new Creature("Chocowool", "Fire", "B", 1, 50),
            new Creature("Parfwit", "Fire", "C", 1, 50),
            new Creature("Brownisaur", "Grass", "D", 1, 50),
            new Creature("Frubat", "Grass", "E", 1, 50),
            new Creature("Malts", "Grass", "F", 1, 50),
            new Creature("Squirpie", "Water", "G", 1, 50),
            new Creature("Chocolite", "Water", "H", 1, 50),
            new Creature("Oshacone", "Water", "I", 1, 50)
        ));
    }
        
    /**
    * Adds creatures for level two.
    *
    * @param  creatures  the list of creatures to add
    */
    private void addCreaturesForLevelTwo(List<Creature> creatures) {
        creatures.addAll(Arrays.asList(
            new Creature("Strawleon", "Fire", "A", 2, 50),
            new Creature("Chocofluff", "Fire", "B", 2, 50),
            new Creature("Parfure", "Fire", "C", 2, 50),
            new Creature("Chocosaur", "Grass", "D", 2, 50),
            new Creature("Golberry", "Grass", "E", 2, 50),
            new Creature("Kirlicake", "Grass", "F", 2, 50),
            new Creature("Tartortle", "Water", "G", 2, 50),
            new Creature("Chocolish", "Water", "H", 2, 50),
            new Creature("Dewice", "Water", "I", 2, 50)
        ));
    }
        
    /**
    * Adds creatures for level three.
    *
    * @param creatures  the list of creatures to add
    */
    private void addCreaturesForLevelThree(List<Creature> creatures) {
    creatures.addAll(Arrays.asList(
            new Creature("Strawizard", "Fire", "A", 3, 50),
            new Creature("Candaros", "Fire", "B", 3, 50),
            new Creature("Parfelure", "Fire", "C", 3, 50),
            new Creature("Fudgasaur", "Grass", "D", 3, 50),
            new Creature("Croberry", "Grass", "E", 3, 50),
            new Creature("Velvevoir", "Grass", "F", 3, 50),
            new Creature("Piestoise", "Water", "G", 3, 50),
            new Creature("Icesundae", "Water", "H", 3, 50),
            new Creature("Samurcone", "Water", "I", 3, 50)
            ));
    }   
}
