import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Cell extends JButton implements ActionListener{

    Point point;
    String text;
    boolean isBomb;
    Board board;
    static HashMap<Point, Cell> cellHashMap = new HashMap<>();

    public Cell(Point point){
        this.point = point;
        text = " ";
        isBomb = false;
        addActionListener(this);
    }

    public Point getPoint(){
        return point;
    }

    public void setString(String text) {
        this.text = text;
    }

    public String getString() {
        return text;
    }

    public void setBomb(boolean isBomb) {
        this.isBomb = isBomb;
    }

    public boolean getBomb() {
        return isBomb;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setText(getString());
        if (getString() == "*") {
            Game.gameEnded(false);
            Board.frame.dispose();
            new Difficulty();
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
                if ((x > 0) && (y > 0) && (x < Game.boardSize) && (y < Game.boardSize)) {
                    for (int i = x-1; i <= x+1; i++) {
                        for (int j = y-1; j <= y+1; j++) {
                            Point p = new Point(i, j);
                            if (cellHashMap.get(p).getString() == "*"){
                                bombsSurroundingCounter++;
                            }
                        }
                    }
                }
                else
                setText(Integer.toString(bombsSurroundingCounter));

            }
        }
        setEnabled(false);
    }
}