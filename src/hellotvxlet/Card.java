package hellotvxlet;

import java.awt.Color;
import java.awt.Image;
import java.awt.MediaTracker;
import org.havi.ui.HGraphicButton;
import org.havi.ui.HState;
import org.havi.ui.HVisible;

/**
 *
 * @author Robin Rikken, Stijn Getteman
 */
public class Card extends HGraphicButton
{
    Image front, back;
    boolean blocked, turned = false;
    int pair, width,height;
    
    public Card(int x, int y, String imgstring, int pair) {
        this.pair = pair;
        this.width = 54;
        this.height = 75;
        this.setLocation(x, y);
        this.setSize(width, height);
        this.setBackground(Color.WHITE);
        this.setBackgroundMode(HVisible.BACKGROUND_FILL);
        this.setForeground(Color.BLACK);
        front = this.getToolkit().getImage(imgstring);
        back = this.getToolkit().getImage("images/Cards_0000_CardBack.jpg");
        MediaTracker mt = new MediaTracker(this);
        mt.addImage(front, 0);
        mt.addImage(back, 1);
        try {
            mt.waitForAll();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.setGraphicContent(back, HState.NORMAL_STATE);
        this.setGraphicContent(front, HState.ACTIONED_FOCUSED_STATE);
    }
    
    //We choose the card to it faces the players
    public void click() 
    {
        this.setGraphicContent(front, HState.NORMAL_STATE);
        turned = true;
    }

    //The card did not form a pair so it needs to be flipped back down
    public void reset()
    {
        if (!blocked) {
            this.setGraphicContent(back, HState.NORMAL_STATE);
            turned = false;
        }
    }

    //Card is no longer available because it got paired.
    public void block()
    {
        this.blocked = true;
    }
}