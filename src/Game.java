import javax.swing.*;

public class Game {

    Difficulty.Level level;
    int num_cells;
    int num_bombs;

    public Game(Difficulty.Level level, int num_cells, int num_bombs){
        this.level = level;
        this.num_cells = num_cells;
        this.num_bombs = num_bombs;
        new Board(level, num_cells, num_bombs);
    }

    public void gameEnded(boolean wonGame) {
        if (wonGame == false){
            String titleBar = "Game Over";
            String infoMessage = "The game has ended, sorry!";
            JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
