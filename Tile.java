import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
/**
 * Titles for the game of mind, they are buttons that can be true for bomb or not 
 *
 * @author (Ashley)
 * @version (11/8/26)
 */
public class Tile extends JButton
{
    private boolean bomb;

    private boolean opened = false;
    private boolean looked = false;
    private boolean flag = false;

    private Color defaultColor = getBackground(); 

    /**
     * Constructor for objects of class Tile
     */
    public Tile(boolean bomb, boolean looked)
    {
        super();
        this.bomb = bomb;
        this.looked = looked;
        this.flag = flag;
    }

    /**
     * 
     */
    public boolean isBomb()
    {
        return bomb;
    }

    public void setBomb(boolean bomb)
    {
        this.bomb = bomb;
    }

    /**
     * 
     */
    public void filpbomb()
    {
        bomb = !bomb;
    }

    public boolean isOpened()
    {
        return opened;
    }

    public void setOpened(boolean opened)
    {
        this.opened = opened;
    }

    public boolean isLooked()
    {
        return looked;
    }

    public void setLooked(boolean looked)
    {
        this.looked = looked;
        if (looked) {
            setBackground(Color.LIGHT_GRAY);
        } 
    }

    public void setFlag(boolean flag)
    {
        this.flag = flag;
    }

    public boolean isflag()
    {
        if (flag == true) {
            setBackground(Color.PINK);
            setText("🚩");
        }
        else {
            setBackground(defaultColor);
            setText("");
        }
        return flag;
    }
}