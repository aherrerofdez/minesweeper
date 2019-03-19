import java.awt.*;

public class BombCell extends Cell {

    public String cell_label;

    public BombCell(Point point) {
        super(point);
        cell_label = "*";
    }

}
