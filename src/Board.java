import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Random;

public class Board implements ClickObserver {

    static JFrame frame = new JFrame();
    JPanel mainPanel;
    Cell cell;
    HashMap<Point, Cell> cellHashMap = new HashMap<>();
    static int emptyCellsCounter;

    public Board(Difficulty.Level level, int boardSize, int numBombs) {
        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        int widthFrame;
        int heightFrame;

        switch(level) {
            case Easy:
                widthFrame = 450;
                heightFrame = 430;
                createBoard(boardSize, numBombs, widthFrame, heightFrame);
                break;
            case Medium:
                widthFrame = 600;
                heightFrame = 540;
                createBoard(boardSize, numBombs, widthFrame, heightFrame);
                break;
            case Difficult:
                widthFrame = 800;
                heightFrame = 690;
                createBoard(boardSize, numBombs, widthFrame, heightFrame);
                break;
        }

        frame.add(mainPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("MineSweeper");
        frame.setVisible(true);

    }

    private void createBoard(int boardSize, int numBombs, int widthFrame, int heightFrame) {
        emptyCellsCounter = (boardSize*boardSize) - numBombs;

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                Point point = new Point(i, j);
                cell = new EmptyCell(point);
                cellHashMap.put(point, cell);
            }
        }

        placeBombs(boardSize, numBombs);

        JPanel counterPanel = new JPanel();
        counterPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));
        counterPanel.setPreferredSize(new Dimension(300, 50));
        JLabel bombsCounterLabel = new JLabel("Number of Bombs: " + numBombs);
        counterPanel.add(bombsCounterLabel);

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        boardPanel.setPreferredSize(new Dimension(widthFrame, heightFrame));
        frame.setSize(widthFrame, heightFrame);
        JPanel new_row;
        for (int i = 0; i < boardSize; i++) {
            new_row = new JPanel();
            for (int j = 0; j < boardSize; j++){
                Point point = new Point(i,j);
                cell = cellHashMap.get(point);
                cell.setPreferredSize(new Dimension(40, 30));
                cell.setFont(new Font("Arial", Font.PLAIN, 10));
                new_row.add(cell);
                new_row.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
            }
            boardPanel.add(new_row);
        }
        mainPanel.add(counterPanel);
        mainPanel.add(boardPanel);
    }

    private void placeBombs(int boardSize, int numBombs) {
        boolean isBomb;
        int bombsCounter = 0;
        Random random = new Random();
        while(bombsCounter < numBombs) {
            int x = random.nextInt(boardSize);
            int y = random.nextInt(boardSize);
            Point current_point = new Point(x, y);
            isBomb = cellHashMap.get(current_point).getBomb();
            if (!isBomb){
                cellHashMap.get(current_point).setBomb(true);
                cell = new BombCell(current_point);
                cellHashMap.put(current_point, cell);
                bombsCounter++;
            }
        }
    }

    @Override
    public void cellClicked() {

    }
}

interface ClickObserver {
    void cellClicked();
}