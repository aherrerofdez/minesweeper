import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

public class Cell extends JButton implements ActionListener {

    private Point point;
    private String text;
    private boolean isBomb;
    static HashMap<Point, Cell> cellHashMap = new HashMap<>();
    private ArrayList<Point> points = new ArrayList<>();

    public Cell(Point point){
        this.point = point;
        text = " ";
        isBomb = false;
        setFont(new Font("Arial", Font.BOLD, 16));
        addActionListener(this);
        addMouseListener(mouseListener);
    }

    Point getPoint(){
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
        if (!isBomb) {
            setEnabled(false);
            Point currentPoint = getPoint();
            points = checkSurroundingCells(currentPoint);
            countSurroundingBombs(points);
        }
    }

    private ArrayList<Point> checkSurroundingCells(Point currentPoint){
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
            for (Point p : points) {
                if (cellHashMap.get(p).isEnabled()) {
                    cellHashMap.get(p).doClick();
                }
            }
        }
        else {
            Color [] colors = {Color.CYAN, Color.GREEN, Color.YELLOW, Color.MAGENTA, Color.RED,
                    Color.BLUE, Color.BLACK , Color.DARK_GRAY};
            setBackground(colors[bombsSurroundingCounter-1]);
            setText(Integer.toString(bombsSurroundingCounter));
        }
    }

    private MouseListener mouseListener= new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getButton() == MouseEvent.BUTTON3){
                Difficulty.numBombs--;
                if (Difficulty.numBombs > -1){
                    Board.bombsCounterLabel.setText("Number of Bombs: " + Difficulty.numBombs);
                    try {
                        Image imgFlag = ImageIO.read(getClass().getResource("resources/flag.png"));
                        Image flagIcon = imgFlag.getScaledInstance(getWidth(), getHeight(), java.awt.Image.SCALE_SMOOTH );
                        setIcon(new ImageIcon(flagIcon));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    setBackground(Color.ORANGE);
                    setEnabled(false);
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