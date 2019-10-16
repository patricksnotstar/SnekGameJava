import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;

public class BodyPart{
    
    private int xCoor, yCoor, width, height;

    public BodyPart(int xCoor, int yCoor, int tileSize){
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        width = tileSize;
        height = tileSize;
    }
    public void tick(){

    }

    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(xCoor * width, yCoor * height, width, height);
    }

    public int getxCoor(){
        return xCoor;
    }

    public void setxCoor(int xCoor){
        this.xCoor = xCoor;
    }

    public int getyCoor(){
        return yCoor;
    }

    public void getyCoor(int yCoor){
        this.yCoor = yCoor;
    }
}