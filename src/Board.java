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
        mainPanel.setPreferredSize(new Dimension(300, 300));

        JPanel panel1 = new JPanel();
        switch(level) {
            case Easy:
                for (int i = 0; i < 81; i++){
                    cell = new JButton();
                    cell.setPreferredSize(new Dimension(10, 10));
                    panel1.add(cell);
                }
                break;
            case Medium:
                for (int i = 0; i < 256; i++){
                    cell = new JButton();
                    cell.setPreferredSize(new Dimension(10, 10));
                    panel1.add(cell);
                }
                break;
            case Difficult:
                for (int i = 0; i < 625; i++){
                    cell = new JButton();
                    cell.setPreferredSize(new Dimension(10, 10));
                    panel1.add(cell);
                }
        }

        mainPanel.add(panel1);

        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MineSweeper");
        setSize(300, 300);
        setVisible(true);

    }
}
