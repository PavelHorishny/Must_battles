package org.game.gui.panels.game.components;

import org.game.GameComponentState;
import org.game.MapAreaState;
import org.game.gui.*;
import org.game.gui.panels.Mediator;
import org.game.gui.panels.Settings;
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
import java.util.Arrays;

public class MapArea extends GamePanelComponent implements MouseListener {
    //ArrayList<MapCell> map = StandardMap.MAP;
    MapAreaState state = new MapAreaState();
    ArrayList<GameUnit> fleet_st;
    ArrayList<MapCell> route;
    BufferedImage image;
    BufferedImage imageTwo;
    GameUnit selected;
    BufferedImage anchor;
    int start;
    int end;
    boolean point = false;
    Timer timer;
    int x;
    int y;

/*    {
        try {
            image = ImageIO.read(new File("src/main/resources/selected.png"));
            imageTwo = ImageIO.read(new File("src/main/resources/underattack.png"));
            anchor = ImageIO.read(new File("src/main/resources/anchor.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

    public MapArea(Settings settings){
        super(settings);
        //setPreferredSize(new Dimension(960,690));
        addMouseListener(this);
/*        timer = new Timer(100,e-> {
            selected.setX(selected.getX() + 1);
            repaint();
            if(end-selected.getX()==0) timer.stop();
        });*/
        //map = StandardMap.MAP; //13.14.15; 16
        //map[14][16].color=Constants.ROUTE;
        //map[15][16].color=Constants.ROUTE;
        //map[16][16].color=Constants.ROUTE;
        //fleet_st=StandardMap.FLEET_ST;
    }

    public MapArea(){
        //setPreferredSize(new Dimension(960,690));
        addMouseListener(this);
/*        timer = new Timer(100,e-> {
                selected.setX(selected.getX() + 1);
                repaint();
            if(end-selected.getX()==0) timer.stop();
        });*/
        //map = StandardMap.MAP; //13.14.15; 16
        //map[14][16].color=Constants.ROUTE;
        //map[15][16].color=Constants.ROUTE;
        //map[16][16].color=Constants.ROUTE;
        //fleet_st=StandardMap.FLEET_ST;
    }

    @Override
    public void setMediator(Mediator mediator) {
        super.setMediator(mediator);
    }

    @Override
    public void updateState(GameComponentState state) {
        this.state=(MapAreaState) state;
        repaint();
    }


    private void drawMap(Graphics g){
        /*Arrays.stream(map).forEach(mc->Arrays.stream(mc).forEach(e->{
            g.setColor(e.getColor());
            g.fillRect(e.x,e.y,e.width,e.height);*//*
          *//*  if(!e.getUnits().isEmpty()){
                g.drawImage(e.getUnits().getFirst().getCurrentIcon(),e.x,e.y,null);
            }*//*
        }));*/
       state.getMap().forEach(mapCell -> {
            g.setColor(mapCell.getColor());
            g.fillRect(mapCell.x,mapCell.y,mapCell.width,mapCell.height);
        });
    }
    private void drawVessels(Graphics g){
        fleet_st.forEach(e->g.drawImage(e.getCurrentIcon(),e.getX(),e.getY(),null));
        //g.drawImage(new GameUnit(Images.TWO_DECKER_SHIP_OF_LINE_ST,new Coordinates(11,10), StateType.PASSIVE).getCurrentIcon(),11*30,10*30,this);
        //g.drawImage(imageTwo,12*30,10*30,this);
    }
    
    private void move(){
        //ActionEvent event = new ActionEvent(AWTEvent.PAINT_EVENT_MASK);
        //end = selected.getX()+90;
        timer.start();
        //if(end-selected.getX()==0) timer.stop();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(state!=null) {
            drawMap(g);
        }else {
            g.setColor(Color.RED);
            g.drawString("Wait", 100, 100);
        }
       /* drawVessels(g);
        //grid(g);
        if(point) drawAnchor(g);*/
    }

    private void drawAnchor(Graphics g) {
        g.drawImage(anchor,x*Constants.CELL_SIZE,y*Constants.CELL_SIZE,null);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
 /*       if(e.getButton()==MouseEvent.BUTTON2){
            //start = selected.getX();
            end = selected.getX()+90;
            move();
        }
        if(e.getButton()==MouseEvent.BUTTON1){
            if(map[e.getX()/30][e.getY()/30].getUnits().isEmpty()){
                System.out.println("empty");
               *//* selected = null;
                point = false;
                repaint();*//*
                if(map[e.getX()/30][e.getY()/30].getColor().equals(Constants.ROUTE)&&selected!=null){
                    point=true;
                    x=e.getX()/30;
                    y=e.getY()/30;
                    repaint();
                }else{
                    point = false;
                    select();
                }
            }else {
               select(map[e.getX()/30][e.getY()/30].getUnits().getFirst());
            }*/
            //if(Arrays.stream(map).forEach(a->Arrays.stream(a).anyMatch(mapCell -> mapCell.contains(e.getPoint())))){}
            /*GameUnit u = map[e.getX()/30][e.getY()/30].getUnits().getFirst();
            if(u!=null){
                System.out.println("fdfdfdf");
            }else{
                System.out.println("empty");
            }*/
        }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
    //move();

    /*    if(map.stream().anyMatch(mapCell -> mapCell.contains(e.getPoint()))){
            *//*GameUnit gu = *//*fleet_st.stream().filter(
                    gameUnit -> gameUnit.getCoordinates().equals(new Coordinates(e.getX()/30,e.getY()/30)))
                    .findFirst().ifPresentOrElse(this::select, this::select);
            repaint();
            System.out.println("x:"+e.getX()/30);
            System.out.println("y:"+e.getY()/30);
        }

    }*/

/*    private void select(){
        if(selected!=null){
            selected.setActivity(StateType.PASSIVE);
        }
        selected = null;
        repaint();
    }*/
/*    private void select(GameUnit unit){
       *//* if(selected!=null){
            selected.setActivity(StateType.PASSIVE);
        }*//*
        select();
        unit.setActivity(StateType.SELECTED);
        selected=unit;
        repaint();
    }*/
/*    private void grid(Graphics g){
        for(int i = 0; i<=960;i=i+30){
            g.setColor(Color.BLACK);
            g.drawLine(i,0,i,690);
        }
        for(int i = 0; i<=690;i=i+30){
            g.setColor(Color.BLACK);
            g.drawLine(0,i,960,i);
        }
    }*/
}