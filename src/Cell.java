import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cell extends JButton implements ActionListener{

    Point point;
    String text;
    boolean isBomb;

    public Cell(Point point){
        this.point = point;
        text = " ";
        isBomb = false;
        addActionListener(this);
    }

    public void setString(String text) {
        this.text = text;
    }

    public String getString() {
        return this.text;
    }

    public void setBomb(boolean isBomb) {
        this.isBomb = isBomb;
    }

    public boolean getBomb() {
        return isBomb;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setText(getString());
        if (getString() == "*") {
            Game.gameEnded(false);
            Board.frame.dispose();
            new Difficulty();
        }
        else {
            Board.emptyCellsCounter--;
            if (Board.emptyCellsCounter == 0) {
                Game.gameEnded(true);
            }
            System.out.println(Board.emptyCellsCounter);
        }
        setEnabled(false);
    }
}