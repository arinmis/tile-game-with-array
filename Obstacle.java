/**
 *
 *  @author Mustafa Arinmis
 *  @since 15.11.2020
 *
 * */

public class Obstacle extends Tile {
    public Obstacle(int i, int j) {
        super(i, j);
    }

    public static void placeObstacles(Terrain terrain) {
        int tempI, tempJ;

        for(int i=0; i<terrain.getNumOfObstacle(); i++) {
            //valid i interval: [0, height - 1]
            tempI =    (int)(Math.random() * terrain.getHeight());
            //valid j interval: [0, width - 1]
            tempJ =    (int)(Math.random() * terrain.getWidght());
            if(!(terrain.getTiles()[tempI][tempJ] instanceof Obstacle))
                terrain.getTiles()[tempI][tempJ] = new Obstacle(tempI, tempJ);
            else
                i--;//that mean we couldn't  put our obstacle to valid place
        }

    }


    @Override
    public String toString() {
        //Obstacle represented "1"
        return "1";
    }
}
