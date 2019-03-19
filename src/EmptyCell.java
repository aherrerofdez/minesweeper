import java.awt.*;

public class EmptyCell extends Cell {

    public String cell_label;

    public EmptyCell(Point point) {
        super(point);
        cell_label = " ";
    }

}