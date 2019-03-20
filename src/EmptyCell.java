import java.awt.*;

public class EmptyCell extends Cell {

    String label;

    public EmptyCell(Point point) {
        super(point);
        label = " ";
    }

}