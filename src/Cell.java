import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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
        addMouseListener(mouseListener);
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
        }
        else {
            Board.emptyCellsCounter--;
            if (Board.emptyCellsCounter == 0) {
                setEnabled(false);
                Game.gameEnded(true);
            }
            else {
                ArrayList<Point> points = new ArrayList<>();
                Point currentPoint = getPoint();
                points = checkSurroundingCells(points, currentPoint);
                countSurroundingBombs(points);
            }
            setEnabled(false);
        }
    }
    private ArrayList<Point> checkSurroundingCells(ArrayList<Point> points, Point currentPoint){
        int x = (int) currentPoint.getX();
        int y = (int) currentPoint.getY();
        //Top Left Corner
        if((x == 0) && (y == 0)) {
            for (int i = x; i <= (x+1); i++){
                for (int j = y; j <= (y+1); j++){
                    Point p = new Point(i, j);
                    points.add(p);
                }
            }
        }
        //Top Right Corner
        if((x == 0) && (y == (Game.boardSize -1))) {
            for (int i = x; i <= (x+1); i++){
                for (int j = (y-1); j <= y; j++){
                    Point p = new Point(i, j);
                    points.add(p);
                }
            }
        }
        //Bottom Left Corner
        if((x == (Game.boardSize -1)) && (y == 0)) {
            for (int i = (x-1); i <= x; i++){
                for (int j = y; j <= (y+1); j++){
                    Point p = new Point(i, j);
                    points.add(p);
                }
            }
        }
        //Bottom Right Corner
        if((x == (Game.boardSize -1)) && (y == (Game.boardSize -1))) {
            for (int i = (x-1); i <= x; i++){
                for (int j = (y-1); j <= y; j++){
                    Point p = new Point(i, j);
                    points.add(p);
                }
            }
        }
        //First row
        if((x == 0) && (y != 0) && (y < (Game.boardSize - 1))) {
            for (int i = x; i <= (x+1); i++){
                for (int j = (y-1); j <= (y+1); j++){
                    Point p = new Point(i, j);
                    points.add(p);
                }
            }
        }
        //Last row
        if((x == (Game.boardSize - 1)) && (y != 0) && (y < (Game.boardSize - 1))) {
            for (int i = (x-1); i <= x; i++){
                for (int j = (y-1); j <= (y+1); j++){
                    Point p = new Point(i, j);
                    points.add(p);
                }
            }
        }
        //First column
        if((y == 0) && (x != 0) && (x < (Game.boardSize - 1))) {
            for (int i = (x-1); i <= (x+1); i++){
                for (int j = y; j <= (y+1); j++){
                    Point p = new Point(i, j);
                    points.add(p);
                }
            }
        }
        //Last column
        if((y == (Game.boardSize - 1)) && (x != 0) && (x < (Game.boardSize - 1))) {
            for (int i = (x-1); i <= (x+1); i++){
                for (int j = (y-1); j <= y; j++){
                    Point p = new Point(i, j);
                    points.add(p);
                }
            }
        }
        //Inside Cells
        if ((x != 0) && (x != (Game.boardSize - 1)) && (y != 0) && (y != (Game.boardSize - 1))) {
            for (int i = (x-1); i <= (x+1); i++){
                for (int j = (y-1); j <= (y+1); j++){
                    Point p = new Point(i, j);
                    points.add(p);
                }
            }
        }
        return points;
    }

    private void countSurroundingBombs(ArrayList<Point> points) {
        int bombsSurroundingCounter = 0;
        for (Point p : points) {
            if (cellHashMap.get(p).isBomb) {
                bombsSurroundingCounter++;
            }
        }
        if(bombsSurroundingCounter == 0){
            setText(getString());
            System.out.println(getString());
        }
        else {
            setText(Integer.toString(bombsSurroundingCounter));
        }
    }

    MouseListener mouseListener= new MouseListener() {
       @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getButton() == MouseEvent.BUTTON3){
                Difficulty.numBombs--;
                if (Difficulty.numBombs > -1){
                    Board.bombsCounterLabel.setText("Number of Bombs: " + Difficulty.numBombs);
                    setText("F");
                }
            }
        }
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
    };
}