package cars;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
public class work extends JPanel implements ActionListener,KeyListener{
    private int space;
    private int width=80;
    private int height=90;
    private int speed;
    private int WIDTH=500;
    private int HEIGHT=700;
    private int move = 20;
    private int count=1;
    private ArrayList<Rectangle>ocars;//for opponent cars
    private Rectangle car;
    private Random rand;
    Timer t;
    public work(){
        t = new Timer(20,this);
        rand=new Random();
        ocars=new ArrayList<Rectangle>();
        car=new Rectangle(WIDTH/2-90,HEIGHT-100,width,height);
        space=300;
        speed=2;
        addKeyListener(this);
        setFocusable(true);
        addocars(true);
        addocars(true);
        addocars(true);
        addocars(true);
       
        
        t.start();
    }
    public void addocars(boolean first){
        int positionx=rand.nextInt()%2;
        int x=0;
        int y=0;
        int Width=width;
        int Height=height;
        if(positionx==0)
        {
            x=WIDTH/2-90;//cars will be appearing either from right side or from left side
        }
        else {
            x=WIDTH/2+10;
        }
        if(first){
            ocars.add(new Rectangle(x,y-100-(ocars.size()*space),Width,Height));
        }
        else{
            ocars.add(new Rectangle(x,ocars.get(ocars.size()-1).y-500,Width,Height));//get()to get position of the last car
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        g.setColor(Color.cyan);
        g.fillRect(0,0,WIDTH,HEIGHT);
        g.setColor(Color.gray);
        g.fillRect(WIDTH/2-100,0,200,HEIGHT);
        g.setColor(Color.red);
        g.fillRect(car.x, car.y, car.width, car.height);
        g.setColor(Color.blue);
        g.drawLine(WIDTH/2,0,WIDTH/2,HEIGHT);
        g.setColor(Color.MAGENTA);
        for(Rectangle rect:ocars){
            g.fillRect(rect.x,rect.y,rect.width,rect.height);
        }
       
    }
    public void actionPerformed(ActionEvent e){
        Rectangle rect;
        count++;
        for(int i=0;i<ocars.size();i++){
            rect=ocars.get(i);
            if(count%1000==0){
                speed++;
                if(move<50){
                    move+=10;
                }
            }
            rect.y+=speed;
        }
        //car crashing with oponents
        for(Rectangle r:ocars){
            if(r.intersects(car)){
                car.y = r.y+height;
            }
        }
        for(int i=0;i<ocars.size();i++){
          rect = ocars.get(i);
          if(rect.y+rect.height > HEIGHT){
              ocars.remove(rect);
              addocars(false);
          }
        }
        repaint();
    }
    
    //moving up
    public void moveup(){
         if(car.y-move<0){
             System.out.println("\b");
         }else{
             car.y-= move;
         }
    }
    public void movedown(){
         if(car.y+move+car.height>HEIGHT-1){
            System.out.println("\b");
                  
         }else{
             car.y +=move;
         }
    }
 public void moveleft(){
         if(car.x-move<WIDTH/2-90){
             System.out.println("\b");
         }else{
             car.x-= move;
         }
    }
    public void moveright(){
         if(car.x+move>WIDTH/2+10){
             System.out.println("\b");
         }else{
             car.x+= move;
         }
    }


    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyReleased(KeyEvent e) {
      //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
      int key=e.getKeyCode();
      switch(key){
          case KeyEvent.VK_UP -> moveup();
          case KeyEvent.VK_DOWN -> movedown();
          case KeyEvent.VK_LEFT -> moveleft();
          case KeyEvent.VK_RIGHT -> moveright();
          default -> {
            }
      }
    }
    
}
