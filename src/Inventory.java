import java.util.*; 
public class Inventory {
    private ArrayList<Creature> creatures;  // stores creatures
    private Creature activeCreature;        // represents the current active creature

    public Inventory(){
        creatures = new ArrayList<>();
        activeCreature = null;              // set active creature to null for now
    }

    /**
    * Adds a creature to the list of creatures.
    *
    * @param  creature  the creature to be added
    */
    public void addCreature(Creature creature){
        creatures.add(creature);        
    }

    /**
    * Removes a creature from the list of creatures.
    *
    * @param  creature  the creature to be removed
    */
    public void removeCreature(Creature creature){
        creatures.remove(creature);     
        
        if(creature == activeCreature){
            setActiveCreature(null);
        }
    }

    /**
    * Returns the active creature.
    *
    * @return the active creature
    */
    public Creature getActiveCreature(){
        return activeCreature;
    }

    /**
    * Sets the active creature.
    *
    * @param  creature  the creature to set as active
    */
    public void setActiveCreature(Creature creature){
        if(creatures.contains(creature))
        {
            activeCreature = creature;
        }
    }

    /**
    * Retrieves all creatures.
    *
    * @return  an ArrayList of Creature objects
    */
    public ArrayList<Creature> getAllCreatures() {
        return new ArrayList<>(creatures);
    }

    /**
    * Retrieves the size of the creatures list.
    *
    * @return the size of the creatures list
    */
    public int getSize(){
        return creatures.size();
    }
}