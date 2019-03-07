import javax.swing.*;
import java.awt.*;

public class Difficulty extends JFrame {

    private JPanel mainPanel;
    private JLabel question_level;
    private JButton difficult_level;
    private JButton medium_level;
    private JButton easy_level;

    public Difficulty() {
        initializeGui();
    }

    private void initializeGui() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        mainPanel.setPreferredSize(new Dimension(450, 450));

        final JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.setPreferredSize(new Dimension(400, 50));
        question_level = new JLabel("Choose a difficulty to start");
        panel1.add(question_level);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel2.setPreferredSize(new Dimension(400, 200));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel3.setPreferredSize(new Dimension(200, 50));
        difficult_level = new JButton("Difficult");
        panel3.add(difficult_level);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel4.setPreferredSize(new Dimension(200, 50));
        medium_level = new JButton("Medium");
        panel4.add(medium_level);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel5.setPreferredSize(new Dimension(200, 50));
        easy_level = new JButton("Easy");
        panel5.add(easy_level);

        panel2.add(panel3);
        panel2.add(panel4);
        panel2.add(panel5);

        mainPanel.add(panel1);
        mainPanel.add(panel2);

        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MineSweeper");
        setSize(500, 500);
        setVisible(true);
    }

}
