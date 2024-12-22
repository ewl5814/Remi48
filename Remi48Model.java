package model;

import java.util.LinkedList;
import java.util.List;

public class Remi48Model {
    public static String UPDATE = "Updated";
    public static String GAME_OVER = "Game Over";
    private Board board;
    private final List< Observer< Remi48Model, String > > observers;
    public Remi48Model() {
        board = new Board();
        observers = new LinkedList<>();
    }
    public Board getBoard() {
        return board;
    }
    public boolean isSolution() {return board.isSolution();}
    public boolean gameOver() {return board.gameOver();}

    public void newGame() {
        board = new Board();
        int x1 = (int)(Math.random()*4);
        int y1 = (int)(Math.random()*4);
        int x2 = (int)(Math.random()*4);
        int y2 = (int)(Math.random()*4);
        if (x1 == x2 && y1 == y2) {
            board.getGrid()[x1][y1] = 4;
        }
        else {
            board.getGrid()[x1][y1] = 2;
            board.getGrid()[x2][y2] = 2;
        }
        announce(UPDATE);
    }
    public void shiftBoard(String direction) {
        //int[][] temp = new int[4][4];
        //for (int i = 0; i < 4; i++) {
        //    System.arraycopy(board.getGrid()[i], 0, temp[i], 0,4);
        //}
        switch (direction) {
            case "N" -> {
                board.shiftNorth(board.getGrid());
                announce(UPDATE);
            }
            case "S" -> {
                board.shiftSouth(board.getGrid());
                announce(UPDATE);
            }
            case "W" -> {
                board.shiftWest(board.getGrid());
                announce(UPDATE);
            }
            case "E" -> {
                board.shiftEast(board.getGrid());
                announce(UPDATE);
            }
        }
    }
    public void addObserver( Observer< Remi48Model, String > obs ) {
        this.observers.add( obs );
    }
    private void announce( String arg ) {
        for ( var obs : this.observers ) {
            obs.update( this, arg );
        }
    }
}
