import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

public class Board implements ClickObserver, ActionListener {

    static JFrame frame = new JFrame();
    private JPanel mainPanel;
    private int widthFrame;
    private int heightFrame;
    private JPanel counterPanel;
    private JPanel boardPanel;
    private Cell cell;
    static JLabel bombsCounterLabel;
    private boolean firstClick = true;

    public Board(Difficulty.Level level, int boardSize, int numBombs) {
        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        Random randomOne = new Random();

        switch (level) {
            case Easy:
                widthFrame = 420;
                heightFrame = 370;
                createBoard(boardSize, numBombs, widthFrame, heightFrame, randomOne);
                break;
            case Medium:
                widthFrame = 550;
                heightFrame = 480;
                createBoard(boardSize, numBombs, widthFrame, heightFrame, randomOne);
                break;
            case Difficult:
                widthFrame = 725;
                heightFrame = 650;
                createBoard(boardSize, numBombs, widthFrame, heightFrame, randomOne);
                break;
        }

        frame.add(mainPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("MineSweeper");
        frame.setVisible(true);
    }

    private void createBoard(int boardSize, int numBombs, int widthFrame, int heightFrame, Random random) {

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                Point point = new Point(i, j);
                cell = new EmptyCell(point);
                Cell.cellHashMap.put(point, cell);
            }
        }

        placeBombs(boardSize, numBombs, random);

        counterPanel = new JPanel();
        counterPanel.setPreferredSize(new Dimension(widthFrame, 25));
        bombsCounterLabel = new JLabel("Number of Bombs: " + numBombs);
        counterPanel.add(bombsCounterLabel);

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(boardSize, boardSize));
        boardPanel.setPreferredSize(new Dimension(widthFrame - 30, heightFrame - 80));
        frame.setSize(widthFrame, heightFrame);
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                Point point = new Point(i, j);
                cell = Cell.cellHashMap.get(point);
                cell.addActionListener(this);
                boardPanel.add(cell);
            }
        }
        mainPanel.add(counterPanel);
        mainPanel.add(boardPanel);
    }

    private void placeBombs(int boardSize, int numBombs, Random random) {
        boolean isBomb;
        int bombsCounter = 0;

        while (bombsCounter < numBombs) {
            int x = random.nextInt(boardSize);
            int y = random.nextInt(boardSize);
            Point currentPoint = new Point(x, y);
            isBomb = Cell.cellHashMap.get(currentPoint).getBomb();
            if (!isBomb) {
                Cell.cellHashMap.get(currentPoint).setBomb(true);
                cell = new BombCell(currentPoint);
                Cell.cellHashMap.put(currentPoint, cell);
                bombsCounter++;
            }
        }
    }

    @Override
    public void cellClicked(Cell cell) {
        if (cell instanceof BombCell) {
            try {
                Image imgBomb = ImageIO.read(getClass().getResource("resources/bomb.png"));
                Image bombIcon = imgBomb.getScaledInstance(cell.getWidth(), cell.getHeight(), java.awt.Image.SCALE_SMOOTH );
                cell.setIcon(new ImageIcon(bombIcon));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Game.gameEnded(false);
        }
        else {
            int counter = 0;
            for (Map.Entry c : Cell.cellHashMap.entrySet()) {
                Point pt = (Point) c.getKey();
                if((Cell.cellHashMap.get(pt) instanceof  EmptyCell) && (Cell.cellHashMap.get(pt).isEnabled())){
                    counter++;
                }
            }
            System.out.println(counter);
            if (counter == 1) {
                cell.setEnabled(false);
                Game.gameEnded(true);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Point pt = ((Cell)e.getSource()).getPoint();
        if (firstClick) {
            if (e.getSource() instanceof BombCell) {
                System.out.println("BOMB!");
                mainPanel.remove(counterPanel);
                mainPanel.remove(boardPanel);
                mainPanel.revalidate();
                mainPanel.repaint();
                Random randomTwo = new Random();
                createBoard(Game.boardSize, Difficulty.numBombs, widthFrame, heightFrame, randomTwo);
                Cell.cellHashMap.get(pt).doClick();
            }
            firstClick = false;
        }
        else {
            cellClicked((Cell) e.getSource());
        }
    }
}