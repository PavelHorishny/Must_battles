package org.game.gui.panels.game.components;

import org.game.state.GameComponentState;
import org.game.state.MapAreaState;
import org.game.gui.*;
import org.game.gui.panels.Mediator;
import org.game.gui.panels.Settings;
import org.game.unit.GUIUnit;
import org.game.unit.UnitType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Optional;

public class MapArea extends GamePanelComponent implements MouseListener {
    //ArrayList<MapCell> map = StandardMap.MAP;
    MapAreaState state;
    ArrayList<GUIUnit> fleet_st;
    ArrayList<MapCell> route;
    BufferedImage image;
    BufferedImage imageTwo;
    GUIUnit selected;
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
    private void drawVessels(Graphics g, GUIUnit unit){
        //fleet_st.forEach(e->g.drawImage(e.getCurrentIcon(),e.getCoordinates().axisX(),e.getCoordinates().axisX(),null));
        g.drawImage(unit.getCurrentIcon(),unit.getCoordinates().axisX()*30,unit.getCoordinates().axisY()*30+7,null);
        //g.drawImage(new GameUnit(Images.TWO_DECKER_SHIP_OF_LINE_ST,new Coordinates(11,10), StateType.PASSIVE).getCurrentIcon(),11*30,10*30,this);
        //g.drawImage(imageTwo,12*30,10*30,this);
    }
    private void drawFortification(Graphics g, GUIUnit unit){
        g.drawImage(unit.getCurrentIcon(),unit.getCoordinates().axisX()*30,unit.getCoordinates().axisY()*30,null);
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
            drawFleet(g);
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
    private void drawFleet(Graphics g){
        //state.getFleet().values().forEach(e->g.drawImage(e.getCurrentIcon(),e.getCoordinates().axisX()*30,e.getCoordinates().axisY()*30,null));
        state.getFleet().values().forEach(guiUnit -> {if(guiUnit.getType().equals(UnitType.FORTIFICATION)){
            drawFortification(g,guiUnit);
        }else {
            drawVessels(g,guiUnit);
        }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            //Coordinates target = new Coordinates(e.getX()/30,e.getY()/30);
            Optional<GUIUnit> t = Optional.ofNullable(state.getFleet().get(new Coordinates(e.getX() / 30, e.getY() / 30)));
            t.ifPresent(guiUnit -> {
                mediator.unitSelected(guiUnit.getId());
                System.out.println(guiUnit.getId());
            });
            if(t.isEmpty()) mediator.unitSelected(null);
           /* System.out.println("pressed");
            System.out.println(e.getX()+" "+e.getY());
            System.out.println(state.getFleet().get(new Coordinates(e.getX()/30,e.getY()/30)));*/

        }
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