import javax.swing.*;
import java.awt.*;

public class Difficulty extends JFrame {

    public enum Level {
        Easy, Medium, Difficult
    }

    private Level level;
    private int numCells;
    private int numBombs;

    public Difficulty() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        mainPanel.setPreferredSize(new Dimension(300, 300));

        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 25));
        panel1.setPreferredSize(new Dimension(300, 50));
        JLabel question_level = new JLabel("Choose a difficulty to start the game:");
        panel1.add(question_level);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel2.setPreferredSize(new Dimension(300, 250));

        int i = 0;
        while(i < 3) {
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
            buttonPanel.setPreferredSize(new Dimension(300, 50));
            JButton buttonLevel = new JButton();
            if (i == 0) {
                buttonLevel.setText("Easy");
                buttonLevel.addActionListener(e -> {
                    setVisible(false);
                    level = Level.Easy;
                    numCells = 81;
                    numBombs = 9;
                    new Game(level, numCells, numBombs);
                });
            }
            else if (i == 1) {
                buttonLevel.setText("Medium");
                buttonLevel.addActionListener(e -> {
                    setVisible(false);
                    level = Level.Medium;
                    numCells = 144;
                    numBombs = 22;
                    new Game(level, numCells, numBombs);
                });
            }
            else if (i == 2){
                buttonLevel.setText("Difficult");
                buttonLevel.addActionListener(e -> {
                    setVisible(false);
                    level = Level.Difficult;
                    numCells = 256;
                    numBombs = 64;
                    new Game(level, numCells, numBombs);
                });
            }
            i++;
            buttonPanel.add(buttonLevel);
            panel2.add(buttonPanel);
        }

        mainPanel.add(panel1);
        mainPanel.add(panel2);

        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MineSweeper");
        setSize(300, 300);
        setVisible(true);
    }

}