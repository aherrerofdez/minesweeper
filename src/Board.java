import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Board {

    static JFrame frame = new JFrame();
    private JPanel mainPanel;
    private Cell cell;
    static int emptyCellsCounter;
    static JLabel bombsCounterLabel;

    public Board(Difficulty.Level level, int boardSize, int numBombs) {
        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        int widthFrame;
        int heightFrame;

        switch(level) {
            case Easy:
                widthFrame = 420;
                heightFrame = 370;
                createBoard(boardSize, numBombs, widthFrame, heightFrame);
                break;
            case Medium:
                widthFrame = 550;
                heightFrame = 480;
                createBoard(boardSize, numBombs, widthFrame, heightFrame);
                break;
            case Difficult:
                widthFrame = 725;
                heightFrame = 650;
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
                Cell.cellHashMap.put(point, cell);
            }
        }

        placeBombs(boardSize, numBombs);

        JPanel counterPanel = new JPanel();
        counterPanel.setPreferredSize(new Dimension(widthFrame, 25));
        bombsCounterLabel = new JLabel("Number of Bombs: " + numBombs);
        counterPanel.add(bombsCounterLabel);

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(boardSize, boardSize));
        boardPanel.setPreferredSize(new Dimension(widthFrame-30, heightFrame-80));
        frame.setSize(widthFrame, heightFrame);
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++){
                Point point = new Point(i,j);
                cell = Cell.cellHashMap.get(point);
                cell.setFont(new Font("Arial", Font.PLAIN, 14));
                boardPanel.add(cell);
            }
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
            isBomb = Cell.cellHashMap.get(current_point).getBomb();
            if (!isBomb){
                Cell.cellHashMap.get(current_point).setBomb(true);
                cell = new BombCell(current_point);
                Cell.cellHashMap.put(current_point, cell);
                bombsCounter++;
            }
        }
    }
}