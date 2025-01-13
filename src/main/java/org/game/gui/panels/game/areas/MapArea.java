package org.game.gui.panels.game.areas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.game.state.GameComponentState;
import org.game.state.MapAreaState;
import org.game.gui.*;
import org.game.gui.panels.Mediator;
import org.game.gui.panels.Settings;
import org.game.unit.GUIUnit;
import org.game.unit.UnitType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class MapArea extends GamePanelComponent implements MouseListener {
    public static final Logger logger = LogManager.getLogger(MapArea.class);
    boolean looser;
    boolean grid = false;
    MapAreaState state;
    GUIUnit selected;
    GUIUnit target;
    BufferedImage anchor;
    Coordinates destination;

    boolean point = false;
    Timer timer;

/*    {
        try {
            anchor = ImageIO.read(Objects.requireNonNull(getClass().getResource("src/main/resources/img/Anchor.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

    public MapArea(Settings settings){

        super(settings);
        addMouseListener(this);
        logger.trace("runs");
        try {
            anchor = ImageIO.read(Objects.requireNonNull(getClass().getResource("/img/Anchor.png")));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public MapArea(){
        addMouseListener(this);
    }

    @Override
    public void setMediator(Mediator mediator) {
        super.setMediator(mediator);
    }

    @Override
    public void updateState(GameComponentState state) {
        this.state=(MapAreaState) state;

        if(this.state.isLost()){
            this.looser = true;
        }else {
            Optional.ofNullable(this.state.getSelectedID()).ifPresentOrElse(coordinates -> selected=this.state.getFleet().get(coordinates),()->selected=null);
            Optional.ofNullable(this.state.getTargetID()).ifPresentOrElse(coordinates -> target=this.state.getFleet().get(coordinates),()->target=null);
            if(this.state.getVesselInStorm()!=null){
                selected=this.state.getFleet().get(this.state.getVesselInStorm());
                destination = this.state.getStormDestination();
                move(selected,destination);
            }
        }
        repaint();
    }


    private void drawMap(Graphics graphics){
        Graphics2D g = (Graphics2D) graphics;
       Arrays.stream(state.getMap()).forEach(subArray-> Arrays.stream(subArray).forEach(mapCell -> {
           g.setColor(mapCell.getColor());
           g.fillRect(mapCell.x,mapCell.y,mapCell.width,mapCell.height);
        }));
    }
    private void drawVessels(Graphics g, GUIUnit unit){
        g.drawImage(unit.getCurrentIcon(),unit.getX(),unit.getY()+7,null);
    }

    private void drawFortification(Graphics g, GUIUnit unit){
        g.drawImage(unit.getCurrentIcon(),unit.getCoordinates().axisX()*30,unit.getCoordinates().axisY()*30,null);
    }
    
    private void move(GUIUnit unit, Coordinates destination){
        point = false;
        int incrementX = getIncrement(unit.getCoordinates().axisX(),destination.axisX());
        int incrementY = getIncrement(unit.getCoordinates().axisY(),destination.axisY());
        mediator.movementStarts(selected.getId());
        point=false;
        timer = new Timer(30, actionEvent -> {

            state.getFleet().get(selected.getCoordinates()).setX(state.getFleet().get(selected.getCoordinates()).getX()+incrementX);
            state.getFleet().get(selected.getCoordinates()).setY(state.getFleet().get(selected.getCoordinates()).getY()+incrementY);

            if(state.getFleet().get(selected.getCoordinates()).getX()==destination.axisX()*30&&state.getFleet().get(selected.getCoordinates()).getY()==destination.axisY()*30) {
                timer.stop();
                mediator.movementEnds(selected.getId(),destination);
            }
            repaint();
        });
        timer.start();
    }

    private int getIncrement(int start, int end){
        return Integer.compare(end - start, 0);
    }

    @Override
    protected void paintComponent(Graphics g) {
        logger.trace("painting started");
        super.paintComponent(g);
        if(state!=null) {
            if (looser){
                g.setColor(Color.RED);
                g.drawString(this.state.getLooser(), 250, 250);
            }else{
                drawMap(g);
                drawFleet(g);
                if(point) drawAnchor(g, destination);
            }
        }else {
            g.setColor(Color.RED);
            g.drawString("Wait", 100, 100);
        }
        if(grid) grid(g);
    }

    private void drawAnchor(Graphics g, Coordinates destination) {
        g.drawImage(anchor,destination.axisX()*Constants.CELL_SIZE+4,destination.axisY()*Constants.CELL_SIZE+4,null);

    }
    private void drawFleet(Graphics g){
        state.getFleet().values().forEach(guiUnit -> {if(guiUnit.getType().equals(UnitType.FORTIFICATION)){
            drawFortification(g,guiUnit);
        }else {
            drawVessels(g,guiUnit);
        }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Optional<GUIUnit> t;
        MapCell cell = state.getMap()[e.getX()/30][e.getY()/30];
        System.out.println(cell.getCoordinates());
        if(e.getButton()==MouseEvent.BUTTON1){
            if(new Coordinates(e.getX()/30,e.getY()/30).equals(destination)&& point&&selected!=null) {
                point = false;
                repaint();
                move(selected, destination);
            }else {
                point = false;
                t = Optional.ofNullable(state.getFleet().get(new Coordinates(e.getX() / 30, e.getY() / 30)));
                t.ifPresentOrElse(unit -> {
                    mediator.unitSelected(unit.getId());
                    repaint();
                }, () -> {
                    if (selected != null) {
                        if (state.getRoute()!=null&&state.getRoute().contains(cell.getCoordinates())) {
                            destination = cell.getCoordinates();
                            point = true;
                            repaint();
                        } else {
                            destination = null;
                            point = false;
                            mediator.unitSelected("");
                            repaint();
                        }
                    } else {
                        mediator.unitSelected("");
                        destination = null;
                        point = false;
                        repaint();
                    }
                });
            }
        }
        if(e.getButton()==MouseEvent.BUTTON2){
            if(selected!=null){
                t = Optional.ofNullable(state.getFleet().get(new Coordinates(e.getX() / 30, e.getY() / 30)));
                t.ifPresent(unit -> {
                    if(unit.getStateType().equals(StateType.AIMED)){
                        System.out.println("SALVO SHOT!");
                        mediator.shot(selected.getId(),unit.getId(),"salvo");
                    }
                });
            }
        }
        if(e.getButton()==MouseEvent.BUTTON3){
            if(selected!=null){
               t = Optional.ofNullable(state.getFleet().get(new Coordinates(e.getX() / 30, e.getY() / 30)));
               t.ifPresent(unit -> {
                   if(unit.getStateType().equals(StateType.AIMED)){
                       System.out.println("SHOT!");
                       mediator.shot(selected.getId(),unit.getId(),"single");
                   }
               });
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        System.out.println("pressed");
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(mouseEvent.getButton()==MouseEvent.BUTTON3) {
            System.out.println("released");
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    private void grid(Graphics g){
        for(int i = 0; i<=1020;i=i+30){
            g.setColor(new Color(0,128,128));
            g.drawLine(i,0,i,960);
        }
        for(int i = 0; i<=960;i=i+30){
            g.setColor(new Color(0,128,128));
            g.drawLine(0,i,1020,i);
        }
    }
    public void enableGrid(){
        grid=!grid;
        repaint();
    }
}