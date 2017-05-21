package hellotvxlet;

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
import org.havi.ui.event.HActionListener;
import java.awt.Image;


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
    MediaTracker mt;
    int countTurned = -1;
     
   
        CardCombo[] combos = {
        new CardCombo(1, "images/Cards_0001_Kakuna.jpg"), new CardCombo(2, "images/Cards_0002_Charmander.jpg"),
        new CardCombo(3, "images/Cards_0003_Caterpie.jpg"), new CardCombo(4, "images/Cards_0004_Bulbasaur.jpg"),
        new CardCombo(5, "images/Cards_0005_Horsea.jpg"), new CardCombo(6, "images/Cards_0006_Umbreon.jpg"),
        new CardCombo(7, "images/Cards_0007_Hoothoot.jpg"), new CardCombo(8, "images/Cards_0008_Clefable.jpg"),
        new CardCombo(9, "images/Cards_0009_Krabby.jpg"), new CardCombo(10, "images/Cards_0010_Igglypuff.jpg"),
        new CardCombo(11, "images/Cards_0011_Omanyte.jpg"), new CardCombo(12, "images/Cards_0012_Beedrill.jpg"),
        new CardCombo(13, "images/Cards_0013_Beautifly.jpg"), new CardCombo(14, "images/Cards_0014_Marill.jpg"),
        new CardCombo(15, "images/Cards_0015_Onyx.jpg"), new CardCombo(16, "images/Cards_0016_Parasect.jpg"),
        new CardCombo(17, "images/Cards_0017_Pichu.jpg"), new CardCombo(18, "images/Cards_0018_Pikachu.jpg"),
        new CardCombo(19, "images/Cards_0019_Sandslash.jpg"), new CardCombo(20, "images/Cards_0020_Sandshrew.jpg"),
        new CardCombo(21, "images/Cards_0021_Seadra.jpg"), new CardCombo(22, "images/Cards_0022_Wooper.jpg"),
        new CardCombo(23, "images/Cards_0023_Tentacool.jpg"), new CardCombo(24, "images/Cards_0024_Tentacruel.jpg"),
         new CardCombo(1, "images/Cards_0001_Kakuna.jpg"), new CardCombo(2, "images/Cards_0002_Charmander.jpg"),
        new CardCombo(3, "images/Cards_0003_Caterpie.jpg"), new CardCombo(4, "images/Cards_0004_Bulbasaur.jpg"),
        new CardCombo(5, "images/Cards_0005_Horsea.jpg"), new CardCombo(6, "images/Cards_0006_Umbreon.jpg"),
        new CardCombo(7, "images/Cards_0007_Hoothoot.jpg"), new CardCombo(8, "images/Cards_0008_Clefable.jpg"),
        new CardCombo(9, "images/Cards_0009_Krabby.jpg"), new CardCombo(10, "images/Cards_0010_Igglypuff.jpg"),
        new CardCombo(11, "images/Cards_0011_Omanyte.jpg"), new CardCombo(12, "images/Cards_0012_Beedrill.jpg"),
        new CardCombo(13, "images/Cards_0013_Beautifly.jpg"), new CardCombo(14, "images/Cards_0014_Marill.jpg"),
        new CardCombo(15, "images/Cards_0015_Onyx.jpg"), new CardCombo(16, "images/Cards_0016_Parasect.jpg"),
        new CardCombo(17, "images/Cards_0017_Pichu.jpg"), new CardCombo(18, "images/Cards_0018_Pikachu.jpg"),
        new CardCombo(19, "images/Cards_0019_Sandslash.jpg"), new CardCombo(20, "images/Cards_0020_Sandshrew.jpg"),
        new CardCombo(21, "images/Cards_0021_Seadra.jpg"), new CardCombo(22, "images/Cards_0022_Wooper.jpg"),
        new CardCombo(23, "images/Cards_0023_Tentacool.jpg"), new CardCombo(24, "images/Cards_0024_Tentacruel.jpg")
    };
   
         Card[] cards = new Card[combos.length];
         Card[] turnedCards = new Card[2];
 
   
 
    class CardCombo 
    {
        int pair;
        String imgString;
        
        public CardCombo(int pair, String imgString)
        {
            this.pair = pair;
            this.imgString = imgString;
        }
      
    }

    public void initXlet(XletContext ctx) throws XletStateChangeException 
    {
        scene = HSceneFactory.getInstance().getDefaultHScene();
        background = this.getToolkit().getImage("images/Background.jpg");
        scene.setBackgroundImage(background);
        scene.setRenderMode(HScene.IMAGE_CENTER);
       
        //Mix cards
        Collections.shuffle(Arrays.asList(combos));
        
        //Build the array for the cards
        int x = 70, y = 10;
        for (int i = 0; i < combos.length; i++)
        {
            x += 74; //margin between cards
            if (i % 8 == 0)//start next row
            {
                x = 70;
                y += 80;
            }
            cards[i] = new Card(x, y, combos[i].imgString, combos[i].pair);
            cards[i].setActionCommand(Integer.toString(i));
            cards[i].addHActionListener(this);
        }
        
        // sort navigation
        int[] nextArray = {7, 15, 23, 31, 39, 47}; //indicates end of rows
        for (int i = 0; i < cards.length; i++)
        {
            int prev = i - 1;
            if (i % 8 == 0) {
                prev = i + 7;
            } else
            {
                if (prev < 0)
                {
                    prev = cards.length - 1;
                }
            }
            boolean foundIndex = false;
            int next = i + 1;
            for (int j = 0; j < nextArray.length; j++)
            {
                if (nextArray[j] == i)
                {
                    foundIndex = true;
                    next = i - 7;
                }
            }
            if (!foundIndex) {
                if (next > cards.length - 1) 
                {
                    next = 0;
                }
            }
            int down = i + 8;
            if (down >= cards.length)
            {
                down -= 48;
            }
            int up = i - 8;
            if (up < 0) 
            {
                up += 48;
            }
            cards[i].setFocusTraversal(cards[up], cards[down], cards[prev], cards[next]);
            scene.add(cards[i]);
        }
        // define players
          players[0] = new Player("Player 1","images/Player1.png", 72, 5, 125, 80);
        players[1] = new Player("Player 2","images/Player2.png", 500, 5, 125, 80);
        
          //add players to scene
        scene.add(players[0]);
        scene.add(players[1]);
        scene.validate();
        scene.setVisible(true);
        //Focus first card, so e can start navigating
        cards[0].requestFocus();
        players[activePlayer].Activate();
        
    }

    public void pauseXlet() {
    }

    public void startXlet() throws XletStateChangeException {
    }

    public void destroyXlet(boolean unconditional) throws XletStateChangeException {
    }

    public void actionPerformed(ActionEvent event) 
    {
     int pressedCard = Integer.parseInt(event.getActionCommand());
     if(!cards[pressedCard].blocked && !cards[pressedCard].turned)
     {
         cards[Integer.parseInt(event.getActionCommand())].click();
         countTurned++;
         turnedCards[countTurned] = cards[pressedCard];
         if(countTurned == 1)
         {
             countTurned = -1;
             if(turnedCards[0].pair== turnedCards[1].pair)
             {
                 players[activePlayer].Scored();
                 turnedCards[0].block();
                 turnedCards[1].block();
             }
             else
             {
                 players[activePlayer].Deactivate();
                 activePlayer++;
                 if(activePlayer >= players.length)
                 {
                     activePlayer = 0;
                 }
                 players[activePlayer].Activate();
                 try
                 {
                     Thread.sleep(500);
                 }
                 catch(InterruptedException exception)
                 {
                     Thread.currentThread().interrupt();
                 }
                 turnedCards[0].reset();
                 turnedCards[1].reset();
             }
         }
     }
    }
  }
        
    