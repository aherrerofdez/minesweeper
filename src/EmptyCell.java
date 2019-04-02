import java.awt.*;

public class EmptyCell extends Cell {

    public EmptyCell(Point point) {
        super(point);
        super.setString("_");
        super.setBomb(false);
    }

}