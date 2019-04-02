import javax.swing.*;
import java.awt.*;

public class Cell extends JButton {

    Point point;
    String text;
    boolean isBomb;

    public Cell(Point point){
        this.point = point;
        text = " ";
        isBomb = false;
        addActionListener(e -> setText(getString()));
    }

    public void setString(String text) {
        this.text = text;
    }

    public String getString() {
        return this.text;
    }

    public void setBomb(boolean isBomb) {
        this.isBomb = isBomb;
    }

    public boolean getBomb() {
        return isBomb;
    }
}