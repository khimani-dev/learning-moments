import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
  static final int SCREEN_WIDTH=600;
  static final int SCREEN_HEIGHT=600;
    static final int UNIT_SIZE=25;//Size of objects in the game screen
    static final int GAME_UNITS=(SCREEN_WIDTH *SCREEN_HEIGHT)/UNIT_SIZE; //No. of elements that can fit in screen
    static final int DELAY =70; //speed of the snake movement
    final int x[] =new int[GAME_UNITS];//holds game-units on x-axis
    final int y[] =new int[GAME_UNITS];//holds game-units on y-axis
    int bodyParts=5; //initially the snake starts with 5 pieces
    int applesEaten;
    int appleX; //apple appears on the x-axis
    int appleY;//apple appears on y-axis
    char direction='R';//snake starts moving in right direction
    Boolean running;
    Timer timer;
    Random random;

  GamePanel(){
      random=new Random();
      this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
      this.setBackground(Color.BLACK);
      this.setFocusable(true);
      this.addKeyListener(new MyKeyAdapter());
      startGame();
  }

    public void startGame(){
       newApple();//creates the apple to be eaten
        running = true; //make the snake move since it has been static
        timer =new Timer(DELAY,this); //determines the speed of the game
        timer.start();
    }
    public void paintComponent(Graphics g){
      super.paintComponent(g);
      draw(g);

    }
   public  void draw(Graphics g){
     for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++){
     g.drawLine(i*UNIT_SIZE,0,i*UNIT_SIZE,SCREEN_HEIGHT);
      g.drawLine(0,i*UNIT_SIZE,SCREEN_WIDTH,i*UNIT_SIZE);}}
    public void newApple(){

    }
    public void move(){

   }
   public void checkApple(){

    }
    public void checkCollision(){

    }
   public void gameOver(Graphics g){

}
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public  class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){

        }
    }
}
