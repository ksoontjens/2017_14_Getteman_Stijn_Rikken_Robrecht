/**
 *
 * @author Robin Rikken, Stijn Getteman
 */
package hellotvxlet;

import java.awt.Color;
import java.awt.Graphics;
import org.dvb.ui.DVBColor;
import org.havi.ui.HComponent;

public class Player extends HComponent {

    boolean active;
    int x=0, y=0,width=0,height=0,score=0;
    String text;

    public Player(String text, int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width=width;
        this.height=height;
        this.text = text;
        Deactive();
      }

    public void Paint(Graphics g) {
        if (active==true) {
            g.setColor(new DVBColor(0, 151, 0, 255));
        } else {
            g.setColor(Color.BLUE);
        }
        g.fillRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        g.drawString(text, 10, 40);
        g.drawString(Integer.toString(score), 10, 70);
    }

    public void Scored() {
        score=+1;
        repaint();
    }

    public void Active() {
        active = true;
        repaint();
    }

    public void Deactive() {
        active = false;
        repaint();
    }
    
  

    public void ResetScore()
    {
        score = 0;
    }
    
    public int GetPlayerScore()
    {
        return score;
    }
   
    

    public void SetPlayerScore(int playerScore)
    {
        score = playerScore;
    }
        
    
}
