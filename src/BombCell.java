import java.awt.*;

public class BombCell extends Cell {

    String label;

    public BombCell(Point point) {
        super(point);
        label = "*";
    }

}
