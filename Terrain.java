import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 *  @author Mustafa Arinmis
 *  @since 15.11.2020
 *
 * */

public class Terrain {

    private int numOfPlayer;
    private int numOfObstacle;

    private Tile[][] tiles;
    private Player[] players;

    public Terrain(int height, int width, int numOfPlayer, int numOfObstacle) {
        tiles = new Tile[height][width];
        for(int i=0; i<height; i++) {
            for(int j=0; j<width; j++) {
                tiles[i][j] = new Tile(i, j);
            }
        }
        this.numOfPlayer = numOfPlayer;
        this.numOfObstacle = numOfObstacle;

        players = new Player[numOfPlayer];

    }


    public void printStatus(int turn) {
        // print terrain with turn number
        System.out.println("Turn " + turn + ":");
        for(int i=0; i<this.tiles.length; i++) {
            for(int j=0; j<this.tiles[0].length;j++) {
                System.out.print(this.tiles[i][j]);
            }
            System.out.println();
        }
    }

    public void turnWriter(int turn) throws IOException {
        //FileWriter file = new FileWriter("TurnOutput.txt");
        PrintWriter output = new PrintWriter("Turns/Turn-" + turn + ".txt");

        for(int i=0; i<this.tiles.length; i++) {
            for(int j=0; j<this.tiles[0].length;j++) {
                output.write(String.valueOf(this.tiles[i][j]));
            }
            output.write("\n");
        }
        output.close();
    }

    public int getNumOfPlayer() {
        return numOfPlayer;
    }

    public int getNumOfObstacle() {
        return numOfObstacle;
    }

    public int getHeight() {
        return this.tiles.length;
    }

    public int getWidght() {
        return this.tiles[0].length;
    }

    public int getTerrainIBound() {
        return tiles.length - 1;
    }

    public int getTerrainJBound() {
        return tiles[0].length - 1;
    }

    public Tile[][] getTiles() {
        return this.tiles;
    }

    public Player[] getPlayers() {
        return this.players;
    }





}
