import javax.swing.SwingUtilities;

public class Driver {

    /**
     * Main function to start the Pokemon game.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        PokemonGame pokemonGame = new PokemonGame(null);  // You can provide null or adjust the constructor as needed
        PokemonGameGUI pokemonGameGUI = new PokemonGameGUI(pokemonGame);  // Create an instance of PokemonGameGUI

        // Set the PokemonGame instance in PokemonGameGUI after creating it
        pokemonGameGUI.setPokemonGame(pokemonGame);

        pokemonGameGUI.setVisible(true);
    });
}
}
