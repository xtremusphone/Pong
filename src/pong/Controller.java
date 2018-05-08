package pong;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener{
    
    private Pong reference;
    
    public Controller(Pong game){
        this.reference = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_S)
            reference.core.player.posY += 10;
        if(e.getKeyCode() == KeyEvent.VK_W)
            reference.core.player.posY -= 10;
        if(e.getKeyCode() == KeyEvent.VK_Q)
            reference.core.resetGame();
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
