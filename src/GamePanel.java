import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
  static final int SCREEN_WIDTH=600;
  static final int SCREEN_HEIGHT=600;
    static final int UNIT_SIZE=25;//Size of objects in the game screen
    static final int GAME_UNITS=(SCREEN_WIDTH *SCREEN_HEIGHT)/UNIT_SIZE; //No. of elements that can fit in screen
    static final int DELAY =100; //speed of the snake movement
    final int x[] =new int[GAME_UNITS];//holds game-units on x-axis
    final int y[] =new int[GAME_UNITS];//holds game-units on y-axis
    int bodyParts=6; //initially the snake starts with 6 pieces
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
   public  void draw(Graphics g) {
       if (running) {
          for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
               g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
               g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
           }
           g.setColor(Color.red);  //color of the apple
           g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE); //shape and size of the apple

           for (int i = 0; i < bodyParts; i++) {
               if (i == 0) {
                   g.setColor(Color.green);
                   g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
               } else {
                   g.setColor(new Color(45, 180, 0));
                   g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
               }
           }
           //displays the score of the game
           g.setColor(Color.red);
           g.setFont(new Font("Ink free",Font.BOLD,35));
           FontMetrics metrics=getFontMetrics(g.getFont()); //aligns the text centre
           g.drawString("Score :" +applesEaten,(SCREEN_WIDTH - metrics.stringWidth("Score :" +applesEaten))/2, g.getFont().getSize());
       }
      else {
          gameOver(g);
      }
  }
    public void newApple() {
       appleX = random.nextInt((int) (SCREEN_WIDTH/UNIT_SIZE)) * UNIT_SIZE;//places the apple along x-axis
        appleY = random.nextInt((int) (SCREEN_HEIGHT/UNIT_SIZE)) * UNIT_SIZE; //places the apple along y-axis

    }
    public void move() {
        for (int i = bodyParts; i > 0; i--) { //the loop is for changing the direction along y and x-axis
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
         switch (direction){  // snake direction change
             case 'U':
                 y[0]=y[0] -UNIT_SIZE; // y[0] is the head of the snake
                 break;
             case 'D':
                 y[0]=y[0] + UNIT_SIZE;
                 break;
             case 'L':
                 x[0]=x[0] -UNIT_SIZE;
                 break;
             case 'R':
                 x[0]=x[0] + UNIT_SIZE;
                 break;
    }
  }
   public void checkApple(){
      if ((x[0]==appleX) && y[0] ==appleY) {
          bodyParts++;
          applesEaten++;
          newApple();
       }
    }
    public void checkCollision() {
        //checks collision of the snake with the body
        for (int i = bodyParts; i < 0; i--) { //the loop iterates thru the body parts
            if ((x[0] == x[i]) && (y[0] == y[i]));
            {
                running = false;
            }
        }
        //collision when the snake hits the left border
      if (x[0]<0) {
          running = false;
      }
      //collision of the snake on the right border
      if (x[0]>SCREEN_WIDTH){
          running=false;
      }
      //collision on the top border
      if(y[0]<0){
          running=false;
      }
      //collision on the bottom border
      if (y[0]>SCREEN_HEIGHT){
          running=false;
      }
      if (!running){
          timer.stop();
      }
    }
   public void gameOver(Graphics g){
      //score over the gameOver display
       g.setColor(Color.red);
       g.setFont(new Font("Ink free",Font.BOLD,35));
       FontMetrics metrics1=getFontMetrics(g.getFont()); //aligns the text centre
       g.drawString("Score :" +applesEaten,(SCREEN_WIDTH - metrics1.stringWidth("Score :" +applesEaten))/2, g.getFont().getSize());

      //game over text display
       g.setColor(Color.red);
       g.setFont(new Font("Ink free",Font.BOLD,70));
       FontMetrics metrics2=getFontMetrics(g.getFont()); //aligns the text centre
       g.drawString("Game Over",(SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2); //centres the GameOver text at the middle of the screen
}
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running){
            move();
            checkApple();
            checkCollision();
    }
        repaint();
  }
    public  class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                if (direction !='R'){ //ensure 90deg turning only
                    direction='L';
                }
                break;

                case KeyEvent.VK_RIGHT:
                    if (direction !='L'){ //ensure 90deg turning only
                        direction='R';
                    }
                    break;

                case KeyEvent.VK_UP:
                    if (direction !='D'){ //ensure 90deg turning only
                        direction='U';
                    }
                    break;

                case KeyEvent.VK_DOWN:
                    if (direction !='U'){ //ensure 90deg turning only
                        direction='D';
                    }
                    break;
                   }
              }
         }
      }




