import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Computer extends Player{
    private ArrayList<Marker> computerMarkers = new ArrayList<Marker>();
    private Location lastLoc;
    private boolean found;
    private Ship[] playerShips;
    private ArrayList<Location> guesses;

    public Computer(){
        super();
        randomize();
        found = false;
        playerShips = new Ship[NUMSHIPS];
        guesses = new ArrayList<Location>();
        lastLoc = getRandomGuess();
    }
    
    public void setPlayerShips(Ship[] player){
        for(int i = 0; i < player.length; i ++)
            playerShips[i] = player[i];
    }
    
    public Location getRandomGuess(){
        int x = (int)(Math.random()*(Board.NUM_COLS*Board.SIDE))+Board.TOP;
        int y = (int)(Math.random()*(Board.NUM_COLS*Board.SIDE))+Board.TOP;
        while(!new Location(x,y).checkBounds() && alreadyGuessed(new Location(x,y))){
            x = (int)(Math.random()*(Board.NUM_COLS*Board.SIDE))+Board.TOP;
            y = (int)(Math.random()*(Board.NUM_COLS*Board.SIDE))+Board.TOP;
        }
        guesses.add(new Location(x,y));
        System.out.println("y " + y);
        return new Location(x, y);
    }
    
    public boolean alreadyGuessed(Location check){
        for(Location next : guesses)
            if(check.equals(next))
                return true;
        return false;
    }

    public void randomize(){
        for(int i = 0; i < Player.NUMSHIPS; i ++){
            int x = (int)(Math.random()*((11-getShip(i).getLength())*Board.SIDE))+Board.LEFT;
            int y = (int)(Math.random()*(Board.NUM_COLS*Board.SIDE))+Board.TOP;
            while(shipOverlap(new Location(x,y),i+1)){
                x = (int)(Math.random()*((11-getShip(i).getLength())*Board.SIDE))+Board.LEFT;
                y = (int)(Math.random()*(Board.NUM_COLS*Board.SIDE))+Board.TOP;
                System.out.println("change");
            }
            getShip(i).move(x,y);
        }
        createLocs();
    }
    
    // public void placeHit(String x, int y)
    // {
        // computerMarkers.add(new Marker(true, x,y));
    // }
    
    public void setLastLoc(Location next){
        lastLoc = next;
    }
    
    public Location getLastLoc(){
        return lastLoc;
    }
    public boolean pointOverlap(Location next){
        if(!next.checkBounds())
            return false;
            for(int i = 0; i < playerShips.length; i++){
            String storedX = playerShips[i].getLoc().getGridX();
            int storedY = playerShips[i].getLoc().getGridY();
            if(next.getGridY() == storedY){
                for(int check = 0; check < playerShips[i].getLength(); check++){
                    if(next.getGridX().equals(storedX))
                        return true;
                    storedX = Location.increment(storedX);
                } 
            }
        }
        return false;
    }

    public boolean getFound()
    {
        return found;
    }

}
