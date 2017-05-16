/**
 *
 * @author Robin Rikken, Stijn Getteman
 */
package hellotvxlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import org.dvb.ui.DVBColor;
import org.havi.ui.HComponent;

public class Player extends HComponent {

    boolean active;
    int x=0, y=0,width=0,height=0,score=0;
    String text;
Image img;

    public Player(String text,String imgString, int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width=width;
        this.height=height;
        this.text = text;
        this.img = this.getToolkit().getImage(imgString);
        Deactivate();
      }

    public void paint(Graphics g) {
        if (active==true) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.WHITE);
        }
        g.fillRect(0, 0, width, height);
        g.drawImage(img, 5, 13, this);
        g.setColor(Color.ORANGE);
        g.drawString(text, 45, 40);
        g.drawString(Integer.toString(score), 45, 70);
    }

    public void Scored() {
        score++;
        repaint();
    }

    public void Activate() {
        active = true;
        repaint();
    }

    public void Deactivate() {
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
