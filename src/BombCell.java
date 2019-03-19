import java.awt.*;

public class BombCell extends Cell {

    public String bomb_label;

    public BombCell(Point point) {
        super(point);
        bomb_label = "*";
    }

}
