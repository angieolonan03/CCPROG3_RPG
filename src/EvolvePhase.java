public class EvolvePhase {
    private Creature creature1;
    private Creature creature2;
    private Creature evolvedCreature;
    private Inventory currentInventory;

    public EvolvePhase(Creature creature1, Creature creature2, Inventory currentInventory) {
        this.creature1 = creature1;
        this.creature2 = creature2;
        this.currentInventory = currentInventory;
    }

    /**
    * Evolves the creatures if they can evolve.
    * Removes the creatures from the inventory.
    * Creates a new evolved creature.
    * Adds the evolved creature to the inventory.
    * Displays the selected creatures and the evolution prompt.
    *
    * @param  creature1 The first creature.
    * @param  creature2 The second creature.
    */
    public void evolve() {
        if (canEvolve(creature1, creature2)) {
            removeFromInventory(creature1);
            removeFromInventory(creature2);

            evolvedCreature = createEvolvedCreature(creature1.getFamily(), creature1.getEvolutionLevel() + 1);
            addToInventory(evolvedCreature);

            displaySelectedCreatures(creature1, creature2);
            displayEvolutionPrompt("SUCCESS");
            displayEvolvedCreature(evolvedCreature);
        } else {
            displaySelectedCreatures(creature1, creature2);
            displayEvolutionPrompt("FAIL");
        }
    }

    /**
    * Determines if two creatures can evolve.
    *
    * @param  creature1  the first creature
    * @param  creature2  the second creature
    * @return            true if both creatures can evolve, false otherwise
    */
    private boolean canEvolve(Creature creature1, Creature creature2){
        return creature1.getEvolutionLevel() == creature2.getEvolutionLevel() 
        && creature1.getEvolutionLevel() < 3
        && creature1.getFamily().equals(creature2.getFamily());
    }

    /**
    * Removes a creature from the inventory.
    *
    * @param  creature  the creature to be removed
    */
    private void removeFromInventory(Creature creature) {
        currentInventory.removeCreature(creature);
    }

    /**
    * Creates an evolved creature based on the given family and effective level.
    *
    * @param  family  the family of the creature
    * @param  EL      the effective level of the creature
    * @return         the evolved creature
    */
    private Creature createEvolvedCreature(String family, int EL) {
        // Logic to create the evolved creature
        return new Creature(family, 1);
    }

    /**
    * Adds a creature to the inventory.
    *
    * @param  creature  the creature to be added to the inventory
    */
    private void addToInventory(Creature creature) {
        currentInventory.addCreature(creature);
    }

    /**
    * A description of the entire Java function.
    *
    * @param  creature1   description of parameter
    * @param  creature2   description of parameter
    */
    private void displaySelectedCreatures(Creature creature1, Creature creature2) {
        System.out.println("Selected Creatures:");
        System.out.println(" " + creature1.getName() + " EL" +  creature1.getEvolutionLevel());
        System.out.println(" " + creature2.getName() + " EL" +  creature2.getEvolutionLevel());
    }

    /**
    * Displays the evolution prompt.
    *
    * @param  prompt  the prompt to be displayed
    */
    private void displayEvolutionPrompt(String prompt) {
        System.out.println("Evolutoin " + prompt);
    }

    /**
    * Displays an evolved creature.
    *
    * @param  creature  the creature to display
    */
    private void displayEvolvedCreature(Creature creature) {
       System.out.println("Evolved Creature:");
       System.out.println("  " + creature.getName() + " EL" + creature.getEvolutionLevel());
    }
}