
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
/**
 * MindSweeper start screen
 *
 * @author (Ashley)
 * @version (11/3/26)
 */
public class Start extends JFrame
{
    public static void main(String[] args)

    {
        startscreen();
    }

    static public void startscreen()
    {
        JFrame Frame = new JFrame("Mind Sweeper");
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setSize(850,950); 
        Frame.setLocationRelativeTo(null);

        JLabel title = new JLabel("Mind Sweeper");
        title.setFont(new Font("Arial", Font.BOLD, 64));

        JLabel Name = new JLabel("Ashley's");
        Name.setFont(new Font("Arial", Font.BOLD, 52));

        JButton play = new JButton("Play");
        play.setFont(new Font("Papyrus", Font.BOLD, 88));

        JButton size9 = new JButton("9x9");
        size9.setFont(new Font("Papyrus", Font.BOLD, 30));

        JButton size12 = new JButton("12x12");
        size12.setFont(new Font("Papyrus", Font.BOLD, 30));

        JButton size14 = new JButton("14x14");
        size14.setFont(new Font("Papyrus", Font.BOLD, 30));

        JButton size16 = new JButton("16x16");
        size16.setFont(new Font("Papyrus", Font.BOLD, 30));

        JPanel sizeRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));  // 15px gap between buttons
        sizeRow.add(size9);
        sizeRow.add(size12);
        sizeRow.add(size14);
        sizeRow.add(size16);

        sizeRow.setMaximumSize(sizeRow.getPreferredSize());
        size9.addActionListener(e -> V2.size = 9);
        size12.addActionListener(e -> V2.size = 12);
        size14.addActionListener(e -> V2.size = 14);
        size16.addActionListener(e -> V2.size = 16);

        play.addActionListener(e -> {
                    V2.windowMind();
                    Frame.dispose();
            });


        JPanel Main = new JPanel(new FlowLayout());
        Main.setLayout(new BoxLayout(Main, BoxLayout.Y_AXIS));

        Name.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        play.setAlignmentX(Component.CENTER_ALIGNMENT);

        Main.add(Box.createVerticalGlue());  
        Main.add(Name);
        Main.add(Box.createVerticalStrut(10));
        Main.add(title);
        Main.add(Box.createVerticalStrut(20));
        Main.add(play);
        Main.add(Box.createVerticalStrut(20));
        Main.add(sizeRow);
        Main.add(Box.createVerticalGlue());

        Frame.add(Main);
        Frame.setLocationRelativeTo(null);
        Frame.setVisible(true);
    }
}