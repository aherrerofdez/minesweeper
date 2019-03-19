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

        int board_dimension;
        int frame_dimension;

        switch(level) {
            case Easy:
                board_dimension = 9;
                frame_dimension = 350;
                createBoard(board_dimension, frame_dimension);
                break;

            case Medium:
                board_dimension = 12;
                frame_dimension = 450;
                createBoard(board_dimension, frame_dimension);
                break;

            case Difficult:
                board_dimension = 16;
                frame_dimension = 550;
                createBoard(board_dimension, frame_dimension);
                break;
        }

        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MineSweeper");
        setVisible(true);

    }

    public void createBoard(int board_dimension, int frame_dimension) {

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JPanel new_row;

        mainPanel.setPreferredSize(new Dimension(frame_dimension, frame_dimension));
        boardPanel.setPreferredSize(new Dimension(frame_dimension, frame_dimension));
        setSize(frame_dimension, frame_dimension);
        for (int i = 0; i < board_dimension; i++) {
            new_row = new JPanel();
            for (int j = 0; j < board_dimension; j++){
                Point point = new Point(i,j);
                cell = new Cell(point);
                cell.setPreferredSize(new Dimension(25, 25));
                new_row.add(cell);
                new_row.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
            }
            boardPanel.add(new_row);
        }
        mainPanel.add(boardPanel);
    }
}
