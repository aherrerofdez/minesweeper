import java.awt.*;

public class BombCell extends Cell {

    public BombCell(Point point) {
        super(point);
        super.setString("*");
        super.setBomb(true);
    }

}