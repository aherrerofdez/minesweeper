import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Cell extends JButton implements ActionListener{

    private Point point;
    private String text;
    private boolean isBomb;
    static HashMap<Point, Cell> cellHashMap = new HashMap<>();

    public Cell(Point point){
        this.point = point;
        text = " ";
        isBomb = false;
        addActionListener(this);
    }

    private Point getPoint(){
        return point;
    }

    void setString(String text) {
        this.text = text;
    }

    private String getString() {
        return text;
    }

    void setBomb(boolean isBomb) {
        this.isBomb = isBomb;
    }

    boolean getBomb() {
        return isBomb;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        isBomb = getBomb();
        if (isBomb) {
            setText(getString());
            Game.gameEnded(false);
            Board.frame.dispose();
        }
        else {
            Board.emptyCellsCounter--;
            if (Board.emptyCellsCounter == 0) {
                setEnabled(false);
                Game.gameEnded(true);
            }
            else {
                int bombsSurroundingCounter = 0;
                int x = getPoint().x;
                int y = getPoint().y;
                bombsSurroundingCounter = countSurroundingBombs(bombsSurroundingCounter, x, y);
                if(bombsSurroundingCounter == 0){
                    setText(getString());
                }
                else {
                    setText(Integer.toString(bombsSurroundingCounter));
                }
            }
            setEnabled(false);
        }
    }
    private int countSurroundingBombs(int bombsSurroundingCounter, int x, int y){
        //Top Left Corner
        if((x == 0) && (y == 0)) {
            for (int i = x; i <= (x+1); i++){
                for (int j = y; j <= (y+1); j++){
                    bombsSurroundingCounter = addBombsToCounter(bombsSurroundingCounter, i, j);
                }
            }
        }
        //Top Right Corner
        if((x == 0) && (y == (Game.boardSize -1))) {
            for (int i = x; i <= (x+1); i++){
                for (int j = (y-1); j <= y; j++){
                    bombsSurroundingCounter = addBombsToCounter(bombsSurroundingCounter, i, j);
                }
            }
        }
        //Bottom Left Corner
        if((x == (Game.boardSize -1)) && (y == 0)) {
            for (int i = (x-1); i <= x; i++){
                for (int j = y; j <= (y+1); j++){
                    bombsSurroundingCounter = addBombsToCounter(bombsSurroundingCounter, i, j);
                }
            }
        }
        //Bottom Right Corner
        if((x == (Game.boardSize -1)) && (y == (Game.boardSize -1))) {
            for (int i = (x-1); i <= x; i++){
                for (int j = (y-1); j <= y; j++){
                    bombsSurroundingCounter = addBombsToCounter(bombsSurroundingCounter, i, j);
                }
            }
        }
        //First row
        if((x == 0) && (y != 0) && (y < (Game.boardSize - 1))) {
            for (int i = x; i <= (x+1); i++){
                for (int j = (y-1); j <= (y+1); j++){
                    bombsSurroundingCounter = addBombsToCounter(bombsSurroundingCounter, i, j);
                }
            }
        }
        //Last row
        if((x == (Game.boardSize - 1)) && (y != 0) && (y < (Game.boardSize - 1))) {
            for (int i = (x-1); i <= x; i++){
                for (int j = (y-1); j <= (y+1); j++){
                    bombsSurroundingCounter = addBombsToCounter(bombsSurroundingCounter, i, j);
                }
            }
        }
        //First column
        if((y == 0) && (x != 0) && (x < (Game.boardSize - 1))) {
            for (int i = (x-1); i <= (x+1); i++){
                for (int j = y; j <= (y+1); j++){
                    bombsSurroundingCounter = addBombsToCounter(bombsSurroundingCounter, i, j);
                }
            }
        }
        //Last column
        if((y == (Game.boardSize - 1)) && (x != 0) && (x < (Game.boardSize - 1))) {
            for (int i = (x-1); i <= (x+1); i++){
                for (int j = (y-1); j <= y; j++){
                    bombsSurroundingCounter = addBombsToCounter(bombsSurroundingCounter, i, j);
                }
            }
        }
        //Inside Cells
        if ((x != 0) && (x != (Game.boardSize - 1)) && (y != 0) && (y != (Game.boardSize - 1))) {
            for (int i = (x-1); i <= (x+1); i++){
                for (int j = (y-1); j <= (y+1); j++){
                    bombsSurroundingCounter = addBombsToCounter(bombsSurroundingCounter, i, j);
                }
            }
        }
        return bombsSurroundingCounter;
    }

    private int addBombsToCounter(int bombsSurroundingCounter, int i, int j) {
        Point p = new Point(i, j);
        if (cellHashMap.get(p).isBomb) {
            bombsSurroundingCounter++;
        }
        return bombsSurroundingCounter;
    }
}