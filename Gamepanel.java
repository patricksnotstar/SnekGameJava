import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import javax.swing.JPanel;
import java.util.Random;


import java.util.ArrayList;

public class Gamepanel extends JPanel implements Runnable, KeyListener{
    
    private static final long serialVersionUID = 1L;
 
    public static final int WIDTH = 400, HEIGHT = 400;
   
    private Thread thread;
    private boolean running = false;
 
    private BodyPart b;
    private ArrayList<BodyPart> snake;
 
    private Food food;
    private ArrayList<Food> foods;
   
    private Random r;
   
    private int xCoor = 10, yCoor = 10;
    private int size = 5;
 
    private boolean right = true, left = false, up = false, down =false;
   
    private int ticks = 0;
   
    public Gamepanel() {
        setFocusable(true);
       
        addKeyListener(this);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
 
        r = new Random();
       
        snake = new ArrayList<BodyPart>();
        foods = new ArrayList<Food>();
       
        start();
    }
 
    public void tick() {
        if (snake.size() == 0) {
            b = new BodyPart(xCoor, yCoor, 10);
            snake.add(b);
        }
        if(foods.size() == 0) {
            int xCoor = r.nextInt(39);
            int yCoor = r.nextInt(39);
           
            food = new Food(xCoor, yCoor, 10);
            foods.add(food);
        }
       
        for(int i = 0; i < foods.size(); i++) {
            if(xCoor == foods.get(i).getxCoor() && yCoor == foods.get(i).getyCoor()) {
                size++;
                foods.remove(i);
                i++;
            }
        }
       
        if(xCoor < 0 || xCoor > 39 || yCoor < 0 || yCoor > 39) {
            stop();
        }
       
       
        ticks++;
       
        if(ticks > 550000) {
            if(right) xCoor++;
            if(left) xCoor--;
            if(up) yCoor--;
            if(down) yCoor++;
           
            ticks = 0;
           
            b = new BodyPart(xCoor, yCoor, 10);
            snake.add(b);
           
            if(snake.size() > size) {
                snake.remove(0);
            }
        }
    }
 
    public void paint(Graphics g) {
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
       
        g.setColor(Color.BLUE);
        for (int i = 0; i < WIDTH / 10; i++) {
            g.drawLine(i * 10, 0, i * 10, HEIGHT);
        }
        for (int i = 0; i < HEIGHT / 10; i++) {
            g.drawLine(0, i * 10, WIDTH, i * 10);
        }
        g.setColor(Color.WHITE);
        for (int i = 0; i < snake.size(); i++) {
            snake.get(i).draw(g);
        }
        for(int i = 0; i < foods.size(); i++) {
            foods.get(i).draw(g);
        }
 
    }
 
    public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }
 
    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
 
    public void run() {
        while (running) {
            tick();
            repaint();
        }
    }
 
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT && !left) {
            up = false;
            down = false;
            right = true;
        }
        if(key == KeyEvent.VK_LEFT && !right) {
            up = false;
            down = false;
            left = true;
        }
        if(key == KeyEvent.VK_UP && !down) {
            left = false;
            right = false;
            up = true;
        }
        if(key == KeyEvent.VK_DOWN && !up) {
            left = false;
            right = false;
            down = true;
        }
    }
    @Override
    public void keyReleased(KeyEvent arg0) {   
    }
    public void keyTyped(KeyEvent arg0) {  
    }      
}