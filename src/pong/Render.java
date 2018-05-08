package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class Render extends JPanel{
    
    public int player_score = 0;
    public int computer_score = 0;
    private Random random = new Random();
    public Bat player;
    public Bat computer;
    public Ball ball;
    private Pong reference;
    public int WINDOW_WIDTH = 1000;
    public int WINDOW_HEIGHT = 800;
    
    public Render(Pong game){
        this.player = new Bat(random.nextInt(game.getWidth() - Bat.BAT_WIDTH), random.nextInt(game.getHeight()), Color.BLACK);
        this.computer = new Bat(0, random.nextInt(game.getHeight()),Color.RED);
        this.ball = new Ball(random.nextInt(game.getWidth()), random.nextInt(game.getHeight()), Color.BLACK);
        this.reference = game;
        this.ball.velocityX = random.nextInt(Ball.BALL_MAX_VELOCITY * 2) - Ball.BALL_MAX_VELOCITY;
        this.ball.velocityY = random.nextInt(Ball.BALL_MAX_VELOCITY * 2) - Ball.BALL_MAX_VELOCITY;
    }
    
    public void resetGame(){
        this.ball.posX = this.getWidth() / 2;
        this.ball.posY = this.getHeight() / 2;
        this.ball.velocityX = 0;
        this.ball.velocityY = 0;
        while(this.ball.velocityY == 0 || this.ball.velocityY == 0){
            this.ball.velocityX = random.nextInt(Ball.BALL_MAX_VELOCITY * 2) - Ball.BALL_MAX_VELOCITY;
            this.ball.velocityY = random.nextInt(Ball.BALL_MAX_VELOCITY * 2) - Ball.BALL_MAX_VELOCITY;
        }
    }
    
    public void paint(Graphics g){
        super.paint(g);
        //draw ball
        g.setColor(ball.BALL_COLOR);
        g.fillOval(ball.posX, ball.posY, Ball.BALL_RADIUS, Ball.BALL_RADIUS);
        
        //draw player
        player.posX = this.getWidth() - Bat.BAT_WIDTH;
        g.setColor(player.BAT_COLOR);
        g.fillRect(this.getWidth() - Bat.BAT_WIDTH, player.posY, Bat.BAT_WIDTH, Bat.BAT_HEIGHT);
        
        //draw computer
        computer.posX = 0;
        g.setColor(computer.BAT_COLOR);
        g.fillRect(0, computer.posY, Bat.BAT_WIDTH, Bat.BAT_HEIGHT);
        
        //draw score
        g.setColor(Color.black);
        String comp_text = "computer: " + computer_score;
        String player_text = "player: " + player_score;
        g.drawChars(comp_text.toCharArray(), 0, comp_text.length(), 0, g.getFont().getSize());
        g.drawChars(player_text.toCharArray(), 0, player_text.length(), this.getWidth() - player_text.length() * g.getFont().getSize() / 2, g.getFont().getSize());
    }
    
    //Bat class
    public class Bat{
        
        public static final int BAT_HEIGHT = 100;
        public static final int BAT_WIDTH = 10;
        public Color BAT_COLOR;
        public int posX = 0;
        public int posY = 0;
        
        public Bat(int x, int y,Color c){
            this.posX = x;
            this.posY = y;
            this.BAT_COLOR = c;
        }
    }
    
    //Ball class
    public class Ball{
        
        public static final int BALL_MAX_VELOCITY = 3;
        public static final int BALL_RADIUS = 10;
        public Color BALL_COLOR;
        public int posX = 0;
        public int posY = 0;
        public int velocityX = 0;
        public int velocityY = 0;
        
        public Ball(int x,int y, Color c){
            this.posX = x;
            this.posY = y;
            BALL_COLOR = c;
        }
    }
}
