/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
package cars;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
public class work extends JPanel implements ActionListener{
    private int space;
    private int width=80;
    private int height=90;
    private int speed;
    private int WIDTH=500;
    private int HEIGHT=700;
    private ArrayList<Rectangle>ocars;//for opponent cars
    private Rectangle car;
    private Random rand;
    public work(){
        rand=new Random();
        ocars=new ArrayList<Rectangle>();
        car=new Rectangle(WIDTH/2-90,HEIGHT-100,width,height);
        space=100;
        speed=2;
        addocars(true);
    }
    public void addocars(boolean first){
        int positionx=rand.nextInt()%2;
        int x=0;
        int y=0;
        int Width=width;
        int Height=height;
        if(positionx==0)
        {
            x=width/2-90;//cars will be appearing either from right side or from left side
        }
        else {
            x=width/2%10;
        }
        if(first){
            ocars.add(new Rectangle(x,y-100-(ocars.size()*space),Width,Height));
        }
        else{
            ocars.add(new Rectangle(x,ocars.get(ocars.size()-1).x+space,Width,Height));//get()to get position of the last car
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        g.setColor(Color.cyan);
        g.fillRect(0,0,WIDTH,HEIGHT);
        g.setColor(Color.orange);
        g.fillRect(WIDTH/2-100,0,200,HEIGHT);
        g.setColor(Color.red);
        g.fillRect(car.x, car.y, car.width, car.height);
        g.setColor(Color.blue);
        g.drawLine(WIDTH/2,0,WIDTH/2,HEIGHT);
        g.setColor(Color.MAGENTA);
        for(Rectangle rect:ocars){
            g.drawRect(rect.x,rect.y,rect.width,rect.height);
        }
       
    }
    public void actionPerformed(ActionEvent e){
        Rectangle rect;
        for(int i=0;i<ocars.size();i++){
            rect=ocars.get(i);
            rect.y+=speed;
        }
        repaint();
    }
    
}
