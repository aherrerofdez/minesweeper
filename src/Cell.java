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
        setText(getString());
        isBomb = getBomb();
        if (isBomb) {
            Game.gameEnded(false);
            Board.frame.dispose();
        }
        else {
            Board.emptyCellsCounter--;
            if (Board.emptyCellsCounter == 0) {
                Game.gameEnded(true);
            }
            else {
                int bombsSurroundingCounter = 0;
                int x = getPoint().x;
                int y = getPoint().y;
                //Top Left Corner
                if((x == 0) && (y == 0)) {
                    Point p1 = new Point(x, (y+1));
                    Point p2 = new Point((x+1), (y+1));
                    Point p3 = new Point((x+1), y);
                    if (cellHashMap.get(p1).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p2).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p3).isBomb) {
                        bombsSurroundingCounter++;
                    }
                }
                //Top Right Corner
                if((x == 0) && (y == (Game.boardSize -1))) {
                    Point p1 = new Point((x+1), y);
                    Point p2 = new Point((x+1), (y-1));
                    Point p3 = new Point(x, (y-1));
                    if (cellHashMap.get(p1).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p2).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p3).isBomb) {
                        bombsSurroundingCounter++;
                    }
                }
                //Bottom Left Corner
                if((x == (Game.boardSize -1)) && (y == 0)) {
                    Point p1 = new Point((x-1), y);
                    Point p2 = new Point((x-1), (y+1));
                    Point p3 = new Point(x, (y+1));
                    if (cellHashMap.get(p1).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p2).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p3).isBomb) {
                        bombsSurroundingCounter++;
                    }
                }
                //Bottom Right Corner
                if((x == (Game.boardSize -1)) && (y == (Game.boardSize -1))) {
                    Point p1 = new Point((x-1), y);
                    Point p2 = new Point((x-1), (y-1));
                    Point p3 = new Point(x, (y-1));
                    if (cellHashMap.get(p1).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p2).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p3).isBomb) {
                        bombsSurroundingCounter++;
                    }
                }
                //First row
                if((x == 0) && (y != 0) && (y < (Game.boardSize - 1))) {
                    Point p1 = new Point(x, (y+1));
                    Point p2 = new Point((x+1), (y+1));
                    Point p3 = new Point((x+1), y);
                    Point p4 = new Point((x+1), (y-1));
                    Point p5 = new Point(x, (y-1));
                    if (cellHashMap.get(p1).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p2).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p3).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p4).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p5).isBomb) {
                        bombsSurroundingCounter++;
                    }
                }
                //Last row
                if((x == (Game.boardSize - 1)) && (y != 0) && (y < (Game.boardSize - 1))) {
                    Point p1 = new Point(x, (y-1));
                    Point p2 = new Point((x-1), (y-1));
                    Point p3 = new Point((x-1), y);
                    Point p4 = new Point((x-1), (y+1));
                    Point p5 = new Point(x, (y+1));
                    if (cellHashMap.get(p1).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p2).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p3).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p4).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p5).isBomb) {
                        bombsSurroundingCounter++;
                    }
                }
                //First column
                if((y == 0) && (x != 0) && (x < (Game.boardSize - 1))) {
                    Point p1 = new Point((x-1), y);
                    Point p2 = new Point((x-1), (y+1));
                    Point p3 = new Point(x, (y+1));
                    Point p4 = new Point((x+1), (y+1));
                    Point p5 = new Point((x+1), y);
                    if (cellHashMap.get(p1).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p2).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p3).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p4).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p5).isBomb) {
                        bombsSurroundingCounter++;
                    }
                }
                //Last column
                if((y == (Game.boardSize - 1)) && (x != 0) && (x < (Game.boardSize - 1))) {
                    Point p1 = new Point((x+1), y);
                    Point p2 = new Point((x+1), (y-1));
                    Point p3 = new Point(x, (y-1));
                    Point p4 = new Point((x-1), (y-1));
                    Point p5 = new Point((x-1), y);
                    if (cellHashMap.get(p1).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p2).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p3).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p4).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p5).isBomb) {
                        bombsSurroundingCounter++;
                    }
                }
                //Inside Cells
                if ((x != 0) && (x != (Game.boardSize - 1)) && (y != 0) && (y != (Game.boardSize - 1))) {
                    Point p1 = new Point((x-1), (y-1));
                    Point p2 = new Point((x-1), y);
                    Point p3 = new Point((x-1), (y+1));
                    Point p4 = new Point(x, (y+1));
                    Point p5 = new Point((x+1), (y+1));
                    Point p6 = new Point((x+1), y);
                    Point p7 = new Point((x+1), (y-1));
                    Point p8 = new Point(x, (y-1));
                    if (cellHashMap.get(p1).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p2).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p3).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p4).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p5).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p6).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p7).isBomb) {
                        bombsSurroundingCounter++;
                    }
                    if (cellHashMap.get(p8).isBomb) {
                        bombsSurroundingCounter++;
                    }
                }
                if(bombsSurroundingCounter == 0){
                    setText(getString());
                }
                else {
                    setText(Integer.toString(bombsSurroundingCounter));
                }
            }
        }
        setEnabled(false);
    }
}