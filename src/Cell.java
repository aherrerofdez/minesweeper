import javax.swing.JButton;
import java.awt.*;

public class Cell extends JButton{

    Point point;
    String text;
    boolean isBomb;

    public Cell(Point point){
        this.point = point;
        text = " ";
        isBomb = false;
    }

    public void setBomb(boolean isBomb) {
        this.isBomb = isBomb;
    }

    public void setString(String text) {
        this.text = text;
    }

    public Point getPoint() {
        return point;
    }

    public boolean getBomb() {
        return isBomb;
    }

}

