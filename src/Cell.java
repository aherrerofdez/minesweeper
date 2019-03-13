import javax.swing.JButton;
import java.awt.*;

public class Cell extends JButton{

    Point point;
    String cell_label;

    public Cell(Point point){
        this.point = point;
        cell_label = "label";
    }

}

