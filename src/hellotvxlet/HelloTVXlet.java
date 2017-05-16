package hellotvxlet;

import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Collections;
import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;
import org.havi.ui.HComponent;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HVisible;
import org.havi.ui.event.HActionListener;
import java.awt.Image;
import org.havi.ui.HBackgroundDevice;
import org.havi.ui.HBackgroundImage;
import org.havi.ui.HScreen;

/**
 *
 * @author Robin Rikken, Stijn Getteman
 */
public class HelloTVXlet extends HComponent implements Xlet, HActionListener
{
    HScene scene;
    Player[] players = new Player[2];
    int activePlayer = 0;
   Image background; 
   
   
   
   
 
    class CardCombo {
      
    }

    public void initXlet(XletContext ctx) throws XletStateChangeException 
    {
        scene = HSceneFactory.getInstance().getDefaultHScene();
        background = this.getToolkit().getImage("images/Background.jpg");
        scene.setBackgroundImage(background);
        scene.setRenderMode(HScene.IMAGE_CENTER);
       
        
        // spelers aanmaken
          players[0] = new Player("Player 1","images/Player1.png", 72, 5, 125, 80);
        players[1] = new Player("Player 2","images/Player2.png", 500, 5, 125, 80);
        
          //spelers aan scene toevoegen
        scene.add(players[0]);
        scene.add(players[1]);
        scene.validate();
        scene.setVisible(true);
        players[activePlayer].Activate();
        
    }

    public void pauseXlet() {
    }

    public void startXlet() throws XletStateChangeException {
    }

    public void destroyXlet(boolean unconditional) throws XletStateChangeException {
    }

    public void actionPerformed(ActionEvent event) {
        

                }
            }
        
    
