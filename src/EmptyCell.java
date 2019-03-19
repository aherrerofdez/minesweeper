import java.awt.*;

public class EmptyCell extends Cell {

    String empty_label;

    public EmptyCell(Point point) {
        super(point);
        empty_label = " ";
    }

}