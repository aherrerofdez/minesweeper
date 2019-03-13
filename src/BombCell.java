import java.awt.*;

public class BombCell extends Cell {

    String cell_label;

    public BombCell(Point point) {
        super(point);
        this.cell_label = "*";
    }

}
