import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
/**
 *MindSweeper 
 *
 * @author (Ashley)
 * @version (11/3/26)
 */
public class V2 extends JFrame 
{
    public static int size = 9;
    static Tile[][] button = new Tile[size][size];
    static int NumSeenTile;
    static int flagnum;
    static JLabel flagLabel; 
    static JLabel NumSeenTiles; 

    static boolean loss;

    static public void windowMind()
    {
        button = new Tile[size][size];
        NumSeenTile = 0;
        flagnum = 0;
        loss = false;

        //BombNum
        int NumberBombs = BombLog.CalcNumberBombs(size);

        JFrame Frame = new V2();
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setSize(850,950); 
        Frame.setTitle("Mind Sweeper");
        Frame.setLocationRelativeTo(null);

        //layout 
        Frame.setLayout(new BorderLayout());
        JPanel grid = new JPanel(new GridLayout(size, size, 5, 5));
        flagLabel = new JLabel("Flags: " + flagnum);

        JPanel top = new JPanel(new BorderLayout());
        top.add(flagLabel, BorderLayout.WEST);

        //lost/won
        JLabel YouWin;
        YouWin = new JLabel("YouWin");

        JLabel Youlose;
        Youlose = new JLabel("Youlose");

        //bottons for this 
        JButton play = new JButton("Play");
        play.addActionListener(e -> {
                    V2.windowMind();
                    Frame.dispose();
            });

        JButton Main = new JButton("Back");
        Main.addActionListener(e -> {
                    Start.startscreen();
                    Frame.dispose();
            });

        JPanel endButtons = new JPanel(new FlowLayout());
        endButtons.add(play);
        endButtons.add(Main);

        Frame.add(grid, BorderLayout.CENTER);
        Frame.add(top, BorderLayout.NORTH);   

        //Tiles
        for(int R= 0 ; R<size; R++) {
            for (int C =0; C<size; C++) {
                button[R][C] = new Tile(false, false);
                grid.add(button[R][C]);

                final int row = R;
                final int col = C;

                button[R][C].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseReleased(MouseEvent e) {
                            if (SwingUtilities.isRightMouseButton(e)) {
                                Tile t = button[row][col];
                                if (t.isLooked() == false && NumSeenTile > 0) {              // can't flag an opened tile
                                    if (button[row][col].isflag() == true) {
                                        t.setFlag(false);
                                        t.isflag();
                                        flagnum--;
                                    }
                                    else {
                                        t.setFlag(true);
                                        t.isflag();
                                        flagnum++ ; 
                                    }
                                    flagLabel.setText("Flags: " + flagnum);
                                }
                            }
                        }
                    });

                button[R][C].addActionListener(new ActionListener() 
                    {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // 4. Change the value dynamically when clicked
                            if (loss || WinOrLose.didYouWin(size, NumberBombs, NumSeenTile)) return;{
                                if (button[row][col].isflag() == false){
                                    //first click
                                    if (NumSeenTile == 0){
                                        BombLog.FirstClick(row, col);
                                    }

                                    if (button[row][col].isBomb()) {
                                        click(row, col, "💣");
                                        button[row][col].setBackground(Color.RED);
                                        loss = true;
                                    } else {
                                        click(row, col, String.valueOf(BombLog.CountBomb(row, col)));
                                    }

                                    if (BombLog.CountBomb(row, col) == 0){
                                        BombLog.FindZero(row, col);
                                    }

                                    if (WinOrLose.didYouWin(size, NumberBombs, NumSeenTile)){
                                        top.add(YouWin, BorderLayout.CENTER);
                                        top.add(endButtons, BorderLayout.EAST);
                                    }

                                    if (loss){
                                        top.add(Youlose, BorderLayout.CENTER);
                                        top.add(endButtons, BorderLayout.EAST);
                                    }
                                }
                            }
                        }
                    });
            }
        }

        BombLog.PlaceBomb(NumberBombs);

        Frame.setVisible(true);
    }

    static public void click(int row, int col, String newValue) {
        if (row >= 0 && row < size && col >= 0 && col < size) {
                button[row][col].setText(newValue); // Updates the text
                if (!button[row][col].isLooked()) {                  // first time this tile is opened
                    NumSeenTile++;
                }

                button[row][col].setLooked(true);
                button[row][col].isLooked();
                if (newValue.equals("0")) {
                    button[row][col].setBackground(Color.ORANGE);
                    button[row][col].setText("");
                }
            }
        }
    }