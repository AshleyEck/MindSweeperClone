import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
/**
 * Write a description of class BombLog here.
 *
 * @author (Ashley)
 * @version (11/8/26)
 */
public class BombLog
{
    static int CountBomb (int row, int col) {
        int numberBombs =0;
        for (int dr= -1; dr<=1; dr++){
            for (int dc=-1; dc<=1; dc++){
                int c= col + dc;
                int r= row +dr;
                if (c >= 0 && r >= 0 && c < V2.size && r < V2.size) {
                    if (V2.button[r][c].isBomb()) {
                        numberBombs++;
                    }
                }
            }
        }
        return numberBombs; 
    }

    static void PlaceBomb (int count) {
        int placed = 0;
        while (placed < count) {
            int r = (int) (Math.random() * V2.size);
            int c = (int) (Math.random() * V2.size);
            if (!V2.button[r][c].isBomb()) {
                V2.button[r][c].setBomb(true);
                placed++;
            }
        }
    }

    static void FindZero (int row, int col) {

        Tile t = V2.button[row][col];

        if (t.isOpened() || t.isBomb() || t.isflag()) return;

        V2.click(row, col, String.valueOf(BombLog.CountBomb(row, col)));

        t.setOpened(true);  
        t.setLooked(true);  
        V2.click(row, col, String.valueOf(CountBomb(row, col)));

        if (CountBomb(row, col) == 0) {
            for (int dr= -1; dr<=1; dr++)
            {
                for (int dc=-1; dc<=1; dc++)
                {
                    int c= col + dc;
                    int r= row + dr;
                    if (r >= 0 && c >= 0 && r < V2.size && c < V2.size) {
                        FindZero(r, c);
                    }
                }
            }
        }
    }

    static void FirstClick (int row, int col) {
        for (int dr= -1; dr<=1; dr++)
        {
            for (int dc=-1; dc<=1; dc++)
            {
                int c= col + dc;
                int r= row + dr;
                if (r >= 0 && c >= 0 && r < V2.size && c < V2.size) {
                    if (V2.button[r][c].isBomb()) {
                        BombLog.PlaceBomb(1);
                        V2.button[r][c].filpbomb(); 
                    }
                }
            }
        }

        for (int dr= -1; dr<=1; dr++)
        {
            for (int dc=-1; dc<=1; dc++)
            {
                int c= col + dc;
                int r= row + dr;
                if (r >= 0 && c >= 0 && r < V2.size && c < V2.size) {
                    V2.button[row][col].setLooked(true);
                    if (V2.button[r][c].isBomb()) {
                        FirstClick(row, col);  
                    }
                }
            }
        }
    }

    static int CalcNumberBombs (int size){
        switch(size){
            case 9:
                return (int)(Math.random() * (11 - 9 + 1)) + 9;

            case 12:
                return (int)(Math.random() * (24 - 21 + 1)) + 21;

            case 14:
                return (int)(Math.random() * (39 - 35 + 1)) + 35;

            case 16:
                return (int)(Math.random() * (56 - 51 + 1)) + 51;

            default:
                return 0;
        }
    }
}