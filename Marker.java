import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Marker
{
    private Color col;
    Location loc;
    public static final int SIZE = 20;
    
    public Marker(boolean hit, int x, int y){
        if(hit)
            col = Color.RED;
        else
            col = Color.WHITE;
        loc = new Location(x,y);
    }
    
    public Marker(boolean hit, Location next){
        if(hit)
            col = Color.RED;
        else
            col = Color.WHITE;
        loc = next;
    }

    public void draw( Graphics page )
    {
        page.setColor(col);
        page.fillOval(loc.getXVal(), loc.getYVal(), SIZE, SIZE);
    }
    
    public void drawMini(Graphics page){
        page.setColor(col);
        System.out.println("bonjour " + loc);
        page.fillOval(Board.MINI_LEFT + 7 + Location.getColVal(loc.getGridX())*Board.MINI_SIDE, Board.MINI_TOP + loc.getGridY()*Board.MINI_SIDE + 7, 5, 5); 
    }
}