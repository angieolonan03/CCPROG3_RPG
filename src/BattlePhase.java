import java.util.ArrayList;
import java.util.Random;

public class BattlePhase {
    private Creature userCreature;
    private Creature enemyCreature;
    private Inventory currentInventory;
    private int maxActions = 3; // max actions
    private Random random = new Random();

    public BattlePhase(Creature activeCreature, Creature enemyCreature, Inventory currentInventory){
        this.userCreature = activeCreature;
        this.enemyCreature = enemyCreature;
        this.currentInventory = currentInventory;
    }
    
    /**
    * Begins a battle with the given enemy creature.
    *
    * @param  enemyCreature  the enemy creature to battle against
    */
    public void beginBattle(Creature enemyCreature) {
        if(enemyCreature != null){
            System.out.println("\nLet the Battle begin!");
            
            int actionsRemaining = maxActions;

            enemyCreature.setHealth(50);
            
            while (actionsRemaining > 0) {
                System.out.println("\nAction Remaining: " + actionsRemaining);
                displayBattleStatus();
                System.out.println("\nSelect an Action:");
                System.out.println("[1] ATTACK");
                if (currentInventory.getSize() > 1) {
                    System.out.println("[2] SWAP");
                }
                System.out.println("[3] CATCH");
                System.out.println("[4] RUN AWAY");
                
                int choice = userInput.getUserChoice(1, 4);
                
                switch (choice) {
                    case 1: // Attack
                        performAttack(userCreature, enemyCreature);
                        break;
                    case 2: // Swap
                        performSwap(currentInventory);
                        break;
                    case 3: // Catch
                        if (performCatch(enemyCreature)) {
                        return;
                        }
                        break;
                    case 4: // Run away
                        System.out.println("You ran away from the battle.");
                        return; // End the battle phase and return to the area screen
                    default:
                        System.out.println("Invalid action choice.");
                        break;
                }
                actionsRemaining--;
            }
            // Check if the enemy is defeated
            if (enemyCreature.getHealth() <= 0) {
                System.out.println(enemyCreature.getName() + " is defeated!");
            } else {
                System.out.println("\nThe enemy has run away!");
            }
        }
    }

    /**
    * Displays the battle status.
    *
    * @param  None
    * @return None
    */
    private void displayBattleStatus() {
        System.out.println("\nBattle Status:");
        System.out.println(currentInventory.getActiveCreature().getName() + " (Health: " + currentInventory.getActiveCreature().getHealth() + ")");
        System.out.println(enemyCreature.getName() + " (Health: " + enemyCreature.getHealth() + ")");
    }

// -----------------------------PERFROM ATTACK-----------------------------
    /**
    * Performs an attack between two creatures.
    *
    * @param  attacker  the creature performing the attack
    * @param  defender  the creature being attacked
    */
    private void performAttack(Creature attacker, Creature defender){
        int damage = calculateUserDamage((attacker), defender);

        defender.setHealth(defender.getHealth() - damage);

        System.out.println(attacker.getName() + " attacks " + defender.getName() + " for " + damage + " damage.");

        if(enemyCreature.getHealth() <= 0){
            System.out.println("You've defeated " + enemyCreature.getName() + "!");
            return;
        }
    }

// -----------------------------PERFORM SWAP-----------------------------
    /**
    * Performs a swap of the active creature in the user's inventory.
    *
    * @param  currentInventory  the user's current inventory
    */
    public void performSwap(Inventory currentInventory) {
        // Get the list of creatures in the user's inventory
        ArrayList<Creature> inventoryCreatures = this.currentInventory.getAllCreatures();
    
        // Check if there are creatures available for swapping
        if (inventoryCreatures.isEmpty()) {
            System.out.println("You cannot swap creatures as there are no other creatures in your inventory.");
            return;
        }
    
        // Display the available swap options
        System.out.print("Choose a creature to swap with: ");
        for (int i = 0; i < inventoryCreatures.size(); i++) {
            Creature creature = inventoryCreatures.get(i);
            System.out.println("[" + (i + 1) + "] " + creature.getName());
        }
    
        // Prompt the user to select a creature to swap with
        int userChoice = userInput.getUserChoice(1, inventoryCreatures.size());
    
        // Get the selected creature from the swap options
        Creature selectedCreature = inventoryCreatures.get(userChoice - 1);
    
        // Swap the active creature with the selected creature
        this.currentInventory.setActiveCreature(selectedCreature);
    
        System.out.println("You swapped your active creature with " + selectedCreature.getName() + ".");
    }

// -----------------------------PERFORM CATCH-----------------------------
    /**
    * Performs a catch attempt on an enemy creature.
    *
    * @param  enemyCreature  the enemy creature to perform the catch attempt on
    * @return                true if the catch is successful and the enemy creature is added to the player's inventory, false otherwise
    */
    public boolean performCatch(Creature enemyCreature) {
        int catchRate = calculateCatchRate(enemyCreature.getHealth());
        int randomValue = random.nextInt(100); // Generate a random value between 0 and 99

        boolean catchSuccessful = randomValue < catchRate;

        // If the catch is successful, add the enemyCreature to the player's inventory
        if (catchSuccessful) {
            currentInventory.addCreature(enemyCreature);   
            System.out.println("You've successfully captured " + enemyCreature.getName() + "!");
            return true;
        } else {
            System.out.println("You failed to capture " + enemyCreature.getName() + ".");
              return false; 
        }
    }
    
    /**
    * Calculates the damage inflicted by an attacker Creature on a defender Creature.
    *
    * @param  attacker  the attacking Creature
    * @param  defender  the defending Creature
    * @return           the calculated damage value
    */
    private int calculateUserDamage(Creature attacker, Creature defender) {
        Random random = new Random();
        int damage = random.nextInt(10) + 1; // Random damage between 1 and 10

        damage *= attacker.getEvolutionLevel();

        // Check type advantage
        if (isTypeStrongAgainst(attacker.getType(), defender.getType())) {
            damage *= 1.5; // Type advantage: damage is increased by 50%
        }
        return damage;
    }

    /**
    * Calculate the damage inflicted by an enemy creature.
    *
    * @param  attacker  the creature performing the attack
    * @param  defender  the creature being attacked
    * @return           the amount of damage inflicted
    */
    public int calculateEnemyDamage(Creature attacker, Creature defender) {
        Random random = new Random();
        int damage = random.nextInt(10) + 1; 

        return damage;
    }

    /**
    * Determines if a given attacker type is strong against a defender type.
    *
    * @param  attackerType  the type of the attacker
    * @param  defenderType  the type of the defender
    * @return               true if the attacker type is strong against the defender type, false otherwise
    */
    private boolean isTypeStrongAgainst(String attackerType, String defenderType) {
        if (attackerType.equals("FIRE") && defenderType.equals("GRASS")) {
            return true;
        } else if (attackerType.equals("GRASS") && defenderType.equals("WATER")) {
            return true;
        } else if (attackerType.equals("WATER") && defenderType.equals("FIRE")) {
            return true;
        }
        return false; 
    }

    /**
     * Calculates the catch rate of an enemy based on its health.
     *
     * @param  enemyHealth   the health of the enemy
     * @return               the catch rate of the enemy
     */
    private int calculateCatchRate(int enemyHealth) {
        return 40 + 50 - enemyHealth;
    }

    /**
     * Retrieves the current inventory.
     *
     * @return the current inventory
     */
    public Inventory getCurrentInventory() {
        return currentInventory;
    }
}
