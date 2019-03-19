import javax.swing.*;
import java.awt.*;

public class Board extends JFrame {

    private JPanel mainPanel;
    private JButton cell;

    public Board(Difficulty.Level level) {
        initializeGui(level);
    }

    public void initializeGui(Difficulty.Level level) {
        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JPanel new_row;

        switch(level) {
            case Easy:
                mainPanel.setPreferredSize(new Dimension(325, 325));
                boardPanel.setPreferredSize(new Dimension(300, 300));
                setSize(350, 350);
                for (int i = 0; i < 9; i++) {
                    new_row = new JPanel();
                    for (int j = 0; j < 9; j++){
                        Point point = new Point(i,j);
                        cell = new Cell(point);
                        cell.setPreferredSize(new Dimension(25, 25));
                        new_row.add(cell);
                        new_row.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
                    }
                    boardPanel.add(new_row);
                }
                break;
            case Medium:
                mainPanel.setPreferredSize(new Dimension(400, 400));
                boardPanel.setPreferredSize(new Dimension(375, 375));
                setSize(425, 425);
                for (int i = 0; i < 12; i++) {
                    new_row = new JPanel();
                    for (int j = 0; j < 12; j++){
                        Point point = new Point(i,j);
                        cell = new Cell(point);
                        cell.setPreferredSize(new Dimension(25, 25));
                        new_row.add(cell);
                        new_row.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
                    }
                    boardPanel.add(new_row);
                }
                break;
            case Difficult:
                mainPanel.setPreferredSize(new Dimension(525, 525));
                boardPanel.setPreferredSize(new Dimension(500, 500));
                setSize(550, 550);
                for (int i = 0; i < 16; i++) {
                    new_row = new JPanel();
                    for (int j = 0; j < 16; j++){
                        Point point = new Point(i,j);
                        cell = new Cell(point);
                        cell.setPreferredSize(new Dimension(25, 25));
                        new_row.add(cell);
                        new_row.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
                    }
                    boardPanel.add(new_row);
                }
        }

        mainPanel.add(boardPanel);

        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MineSweeper");
        setVisible(true);

    }
}
