import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

public class Board extends JFrame implements ClickObserver {

    private JPanel mainPanel;
    private Cell cell;
    private HashMap<Point, Cell> cellHashMap = new HashMap<>();
    private Game game;

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
                frame_dimension = 500;
                createBoard(board_dimension, frame_dimension);
                placeBombs(num_bombs, board_dimension);
                addAction();
                break;

            case Medium:
                board_dimension = (int) Math.sqrt(num_cells);
                frame_dimension = 450;
                createBoard(board_dimension, frame_dimension);
                placeBombs(num_bombs, board_dimension);
                addAction();
                break;

            case Difficult:
                board_dimension = (int) Math.sqrt(num_cells);
                frame_dimension = 550;
                createBoard(board_dimension, frame_dimension);
                placeBombs(num_bombs, board_dimension);
                addAction();
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
                cellHashMap.put(point, cell);
                cell.setPreferredSize(new Dimension(45, 45));
                new_row.add(cell);
                new_row.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
            }
            boardPanel.add(new_row);
        }
        mainPanel.add(boardPanel);

    }

    public void placeBombs(int num_bombs, int board_dimension) {
        boolean isBomb;
        int bombs_counter = 0;
        Random random = new Random();
        while(bombs_counter < num_bombs) {
            int x = random.nextInt(board_dimension);
            int y = random.nextInt(board_dimension);
            Point current_point = new Point(x, y);
            isBomb = cellHashMap.get(current_point).getBomb();
            if (!isBomb){
                cellHashMap.get(current_point).setBomb(true);
                cell = new BombCell(current_point);
                cellHashMap.put(current_point, cell);
                bombs_counter++;
            }
            System.out.println("Mine created at: " + current_point);
        }

    }

    @Override
    public Point cellClicked(Cell cell) {
        return cell.getPoint();
    }

    public void addAction(){
        for (Point point : cellHashMap.keySet()) {
            Cell current_cell = cellHashMap.get(point);
            String cell_class = current_cell.getClass().getName();
            System.out.println(cell_class);
            current_cell.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (current_cell instanceof EmptyCell){
                        current_cell.setText("_");
                    }
                    else {
                        current_cell.setText("*");
                        game.gameEnded(true);
                    }
                }
            });
        }

    }

}
