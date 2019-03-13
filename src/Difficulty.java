import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Difficulty extends JFrame {

    public enum Level {
        Easy, Medium, Difficult;
    }

    public Level level;

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
        mainPanel.setPreferredSize(new Dimension(300, 300));

        final JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 25));
        panel1.setPreferredSize(new Dimension(300, 50));
        question_level = new JLabel("Choose a difficulty to start");
        panel1.add(question_level);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel2.setPreferredSize(new Dimension(300, 175));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel3.setPreferredSize(new Dimension(200, 50));
        easy_level = new JButton("Easy");
        easy_level.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                level = Level.Easy;
                Game game = new Game(level);
            }
        });
        panel3.add(easy_level);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel4.setPreferredSize(new Dimension(200, 50));
        medium_level = new JButton("Medium");
        medium_level.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                level = Level.Medium;
                Game game = new Game(level);
            }
        });
        panel4.add(medium_level);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel5.setPreferredSize(new Dimension(200, 50));
        difficult_level = new JButton("Difficult");
        panel5.add(difficult_level);
        difficult_level.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                level = Level.Difficult;
                Game game = new Game(level);
            }
        });

        panel2.add(panel3);
        panel2.add(panel4);
        panel2.add(panel5);

        mainPanel.add(panel1);
        mainPanel.add(panel2);

        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MineSweeper");
        setSize(300, 300);
        setVisible(true);
    }

}
