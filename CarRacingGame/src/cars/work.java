package cars;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
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
     private ArrayList<Rectangle> line;
    private Rectangle car;
    private Random rand;
    BufferedImage bg;
    BufferedImage road;
     BufferedImage user;
      BufferedImage op1;
       BufferedImage op2;
       
    
    Boolean linef=true;
    Timer t;
    public work() throws IOException{
        user = ImageIO.read(new File("C:\\Users\\HP\\Desktop\\CarRacing\\src\\cars\\user1.png"));
         op1 = ImageIO.read(new File("C:\\Users\\HP\\Desktop\\CarRacing\\src\\cars\\op1.png"));
         op2 = ImageIO.read(new File("C:\\Users\\HP\\Desktop\\CarRacing\\src\\cars\\op2.png"));
        bg=ImageIO.read(new File("C:\\Users\\HP\\Desktop\\CarRacing\\src\\cars\\bg.jpeg"));
        road=ImageIO.read(new File("C:\\Users\\HP\\Desktop\\CarRacing\\src\\cars\\road.png"));
        t = new Timer(20,this);
        rand=new Random();
        ocars=new ArrayList<Rectangle>();
        line = new ArrayList<Rectangle>();
        car=new Rectangle(WIDTH/2-90,HEIGHT-100,width,height);
        space=500;
        speed=2;
        addKeyListener(this);
        setFocusable(true);
        addocars(true);
        addocars(true);
        addocars(true);
        addocars(true);
       addlines(true);
         addlines(true);
        addlines(true);
         addlines(true);
         addlines(true);
         addlines(true);
         addlines(true);
         addlines(true);
        
        t.start();
    }
    public void addlines(boolean first){
        int x=WIDTH/2-2;
        int y =700;
        int width=4;
        int height = 100;
        int sp = 130;
        if(first){
            line.add(new Rectangle(x,y-(line.size()*space),width,height));
        } else{
            line.add(new Rectangle(x,line.get(line.size()-1).y-space,width,height));
                    }
    }
    public void addocars(boolean first){
        int positionx=rand.nextInt()%2;
        int x=0;
        int y=0;
        int Width=width;
        int Height=height;
        if(positionx==0)
        {
            x=WIDTH/2-100;//cars will be appearing either from right side or from left side
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
      
        g.drawImage(bg,0,0,null);
        g.drawImage(road, WIDTH/2-125, 0, null);
       g.setColor(Color.WHITE);
        for(Rectangle rect:ocars){
            g.fillRect(rect.x,rect.y,rect.width,rect.height);
        }
        
     
          //g.drawLine(WIDTH/2,0,WIDTH/2,HEIGHT);
          g.drawImage(user,car.x,car.y,null);
      
        g.setColor(Color.MAGENTA);
        for(Rectangle rect:ocars){
            if(rand.nextInt()%2 ==0){
               g.drawImage(op1,rect.x,rect.y,null);
            }else{
                g.drawImage(op2,rect.x,rect.y,null);
            }
            g.fillRect(rect.x,rect.y,rect.width,rect.height);
        }
       
    }
    public void actionPerformed(ActionEvent e){
        Rectangle rect;
        count++;
        for(int i=0;i<ocars.size();i++){
            rect=ocars.get(i);
            if(count%1000==0){
                if(move<10){
                    speed++;
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
         for(int i=0;i<line.size();i++){
            rect=line.get(i);
            if(count%1000==0){
                speed++;
                
            }
            rect.y+=speed;
        }
        
        for(int i=0;i<line.size();i++){
          rect = line.get(i);
          if(rect.y > HEIGHT){
              line.remove(rect);
            
              addlines(false);
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
