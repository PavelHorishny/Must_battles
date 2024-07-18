package org.game.gui.panels;

import org.game.gui.MapCell;
import org.game.mockData.StandardMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GamePanel extends JPanel implements MouseListener {
    ArrayList<MapCell> map;
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMap(g);
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
