import java.awt.*;

public class EmptyCell extends Cell {

    String cell_label;

    public EmptyCell(Point point) {
        super(point);
        this.cell_label = "";
    }

}