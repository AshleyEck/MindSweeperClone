import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
/**
 * WinOrLose
 *
 * @author (Ashley)
 * @version (11/11/26)
 */
public class WinOrLose
{
    static public boolean didYouWin(int size, int numBombs, int seenTiles) {
        return seenTiles == ((size * size)-1) - numBombs;
    }

    static public boolean DidYouLose(){
        return true;
    }
}