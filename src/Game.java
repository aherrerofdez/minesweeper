import javax.swing.*;

public class Game {

    static int boardSize;

    public Game(Difficulty.Level level, int numCells, int numBombs) {
        boardSize = (int) Math.sqrt(numCells);
        new Board(level, boardSize, numBombs);
    }

    static void gameEnded(boolean winnedGame) {
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
        Board.frame.dispose();
    }

}