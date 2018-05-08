package pong;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import pong.Render.Ball;
import pong.Render.Bat;

public class Pong extends JFrame{
    
    private final String GAME_TITLE = "Pong";
    public int WINDOW_WIDTH = 1000;
    public int WINDOW_HEIGHT = 800;
    public boolean shouldRender = true;
    
    public Render core;
    private Controller control;
    
    public Pong(){
        this.setSize(this.WINDOW_WIDTH, this.WINDOW_HEIGHT);
        this.setTitle(GAME_TITLE);
        this.show();
        core = new Render(this);
        control = new Controller(this);
        this.addKeyListener(control);
        this.add(core);
        this.show();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        Runnable running = new Runnable() {
            @Override
            public void run() {
                gameLoop();
            }
        };
        Thread thread = new Thread(running);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowClosing(WindowEvent e) {
                thread.stop();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowIconified(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowActivated(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        thread.start();
    }
    
    public void gameLoop(){
        long start_time = System.currentTimeMillis();
        while(shouldRender){
            long current_time = System.currentTimeMillis();
            long delta = current_time - start_time;
            
            if(delta > 10){
                gamePhysics();
                gameLogic();
                core.repaint();
                start_time = current_time;
            }
        }
    }
    
    public void gameLogic(){
        if(core.ball.posX > core.player.posX){
            core.computer_score += 10;
            core.resetGame();
        }
        if(core.ball.posX < Bat.BAT_WIDTH){
            core.player_score += 10;
            core.resetGame();
        }
    }
    
    public void gamePhysics(){
        if(core.ball.posY + core.ball.velocityY > core.getHeight() || core.ball.posY + core.ball.velocityY < 0)
            core.ball.velocityY *= -1;
        
        if(Math.abs(core.ball.posX - core.player.posX) <=  Bat.BAT_WIDTH && Math.abs(core.ball.posY - core.player.posY) < Ball.BALL_RADIUS + Bat.BAT_HEIGHT ){
            core.ball.velocityX *= -1;
            if(core.ball.posY < core.player.posY + Bat.BAT_HEIGHT / 2){
                //bounce to top
                if(core.ball.velocityY > 0)
                    core.ball.velocityY *= -1;
            }
            else{
                //bounce to bot
                if(core.ball.velocityY < 0)
                    core.ball.velocityY *= -1;
            }
        }
        else if(Math.abs(core.ball.posX - core.computer.posX) <=  Bat.BAT_WIDTH && Math.abs(core.ball.posY - core.computer.posY) < Ball.BALL_RADIUS + Bat.BAT_HEIGHT){
            core.ball.velocityX *= -1;
            if(core.ball.posY < core.player.posY + Bat.BAT_HEIGHT / 2){
                //bounce to top
                if(core.ball.velocityY > 0)
                    core.ball.velocityY *= -1;
            }
            else{
                //bounce to bot
                if(core.ball.velocityY < 0)
                    core.ball.velocityY *= -1;
            }
        }
        core.ball.posX += core.ball.velocityX;
        core.ball.posY += core.ball.velocityY;
        core.computer.posY = core.ball.posY - Bat.BAT_HEIGHT / 2;
    }
    
    public static void main(String[] args) {
        new Pong();
    }
    
}
