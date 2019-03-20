import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

public class Board extends JFrame implements ActionListener, ClickObserver {

    private JPanel mainPanel;
    private JButton cell;
    private HashMap<Point, Cell> cellHashMap = new HashMap<>();

    public Board(Difficulty.Level level, int num_cells, int num_bombs) {
        initializeGui(level, num_cells, num_bombs);
    }

    public void initializeGui(Difficulty.Level level, int num_cells, int num_bombs) {
        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        int board_dimension;
        int frame_dimension;

        switch(level) {
            case Easy:
                board_dimension = (int) Math.sqrt(num_cells);
                frame_dimension = 350;
                createBoard(board_dimension, frame_dimension);
                placeBombs(num_bombs, board_dimension);
                break;

            case Medium:
                board_dimension = (int) Math.sqrt(num_cells);
                frame_dimension = 450;
                createBoard(board_dimension, frame_dimension);
                placeBombs(num_bombs, board_dimension);
                break;

            case Difficult:
                board_dimension = (int) Math.sqrt(num_cells);
                frame_dimension = 550;
                createBoard(board_dimension, frame_dimension);
                placeBombs(num_bombs, board_dimension);
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
                cell = new EmptyCell(point);
                cell.addActionListener(this);
                cell.setPreferredSize(new Dimension(25, 25));
                new_row.add(cell);
                new_row.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
            }
            boardPanel.add(new_row);
        }
        mainPanel.add(boardPanel);

    }

    public void placeBombs(int num_bombs, int board_dimension) {
        int bombs_counter = 0;
        Random random = new Random();
        while(bombs_counter < num_bombs) {
            int x = random.nextInt(board_dimension);
            int y = random.nextInt(board_dimension);
            Point current_point = new Point(x, y);
            cell = new BombCell(current_point);
            bombs_counter ++;
            System.out.println("Mine created at: " + current_point);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void cellClicked(Cell cell) {

    }
}
