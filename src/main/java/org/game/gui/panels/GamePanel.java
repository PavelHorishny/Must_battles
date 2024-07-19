package org.game.gui.panels;

import org.game.gui.MapCell;
import org.game.mockData.StandardMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel implements MouseListener {
    ArrayList<MapCell> map;
    BufferedImage image;
    BufferedImage imageTwo;

    {
        try {
            image = ImageIO.read(new File("src/main/resources/selected.png"));
            imageTwo = ImageIO.read(new File("src/main/resources/underattack.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public GamePanel(){
        setPreferredSize(new Dimension(960,690));
        addMouseListener(this);
        map = StandardMap.MAP;
    }
    private void drawMap(Graphics g){
        map.forEach(mapCell -> {
            g.setColor(mapCell.getColor());
            g.fillRect(mapCell.x,mapCell.y,mapCell.width,mapCell.height);
        });
    }
    private void drawVessels(Graphics g){
        g.drawImage(image,11*30,10*30,this);
        g.drawImage(imageTwo,12*30,10*30,this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMap(g);
        drawVessels(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(map.stream().anyMatch(mapCell -> mapCell.contains(e.getPoint()))){
            System.out.println("x:"+e.getPoint().getX()/30);
            System.out.println("y:"+e.getPoint().getY()/30);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
