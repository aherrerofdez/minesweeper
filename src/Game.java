import javax.swing.*;

public class Game {

    Difficulty.Level level;
    int numCells;
    int numBombs;
    int boardSize;

    public Game(Difficulty.Level level, int numCells, int numBombs) {
        this.level = level;
        this.numCells = numCells;
        this.numBombs = numBombs;
        boardSize = (int) Math.sqrt(numCells);
        new Board(level, boardSize, numBombs);
    }

    public void gameEnded(boolean winnedGame) {
        if (!winnedGame){
            String titleBar = "Game Over:";
            String infoMessage = "The game has ended, sorry!";
            JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            String titleBar = "Victory:";
            String infoMessage = "You won this game, congratulations!";
            JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
        }
    }

}