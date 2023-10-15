import java.util.List;
import java.util.Random;
public class Creature {
    private String name, type, family;
    private int evolutionLevel, health;

    public Creature(String name, String type, String family, int evolutionLevel, int health){
        this.name = name;
        this.type = type;
        this.family = family;
        
        if(evolutionLevel >= 1 && evolutionLevel <= 3){
            this.evolutionLevel = evolutionLevel;
        } else {
            this.evolutionLevel = 1;
        }
        this.health = 100;
    }

    /**
    * Creates a new instance of the `Creature` class with the given `family2` and `i` values.
    * This constructor is used for a specific purpose and does not contain any additional logic.
    *
    * @param  family2  the family value for the creature
    * @param  i        the integer value for the creature
    */
    public Creature(String family2, int i) {   
    }

    /**
     * Get the name of the object.
     *
     * @return the name of the object
     */
    public String getName(){
        return name;
    }

    /**
     * Retrieves the type of the object.
     *
     * @return         	the type of the object as a String
     */
    public String getType(){
        return type;
    }

    /**
     * Retrieves the value of the family attribute.
     *
     * @return  the value of the family attribute
     */
    public String getFamily(){
        return family;
    }

    /**
     * Retrieves the evolution level of the object.
     *
     * @return the evolution level of the object
     */
    public int getEvolutionLevel(){
        return evolutionLevel;
    }

    /**
     * Retrieves the health value of the object.
     *
     * @return the current health value
     */
    public int getHealth(){
        return health;
    }

    /**
     * Sets the health of the object.
     *
     * @param  health  the new health value to be set
     */
    public void setHealth(int health){
        this.health = health;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return  a string representation of the object
     */
    public String toString(){
        return "Name: " + name + "\nType " + type + "\nFamily: " + family + "\nEvolution: " + evolutionLevel +
                "\nHealth: " + health;
    }

     /**
     * Generates a random creature from a given list of creatures.
     *
     * @param  creatures  the list of creatures to choose from
     * @return            a randomly selected creature from the list
     */
    public static Creature getRanCreatureFromList(List<Creature> creatures){
        Random random = new Random();
        int randomIndex = random.nextInt(creatures.size());
        return creatures.get(randomIndex);
    }
}
