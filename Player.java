/**
 *
 *  @author Mustafa Arinmis
 *  @since 15.11.2020
 *
 * */


public class Player extends Tile {


    public Player(int i, int j) {
        super(i, j);
    }
    public Player(int i, int j, int count) {
        super(i, j);
    }


    public boolean move(Terrain terrain, int index) {
        /*index for determining which player will move in players array in terrain
         * this method return true if movement have accoplished succesfuly
         * There is 4 possible movement:
         * rigth, left, up and down
         * I represent those movements 0, 1, 2, 3 in order
         * */

        int way;
        while (true) {
            way = (int) (Math.random() * 4) ;
            // 1 step down: (i+1, j)
            if (way == 0) {
                if (super.getI() + 1 <= terrain.getTerrainIBound()) {
                    // if there is no obstacle player move there
                    if (!(terrain.getTiles()[super.getI() + 1][super.getJ()] instanceof Obstacle)) {

                        //move player
                        terrain.getTiles()[super.getI() + 1][super.getJ()].putPlayer(terrain.getPlayers()[index]);

                        if (terrain.getTiles()[super.getI() - 1][super.getJ()].getPlayerCount() > 1)
                            terrain.getTiles()[super.getI() - 1][super.getJ()].removePlayer(terrain.getPlayers()[index]);
                        else
                            terrain.getTiles()[super.getI() - 1][super.getJ()] = new Tile(super.getI() - 1, super.getJ());
                        return true;
                    }
                    // there is obstacle in (i+1, j)
                    else
                        return false;
                    //(i+1, j) outside of terrain
                } else
                    return false;
            }

            // 1 step up
            else if (way == 1) {
                if (super.getI() - 1 >= 0) {
                    // if there is no obstacle player move there
                    if (!(terrain.getTiles()[super.getI() - 1][super.getJ()] instanceof Obstacle)) {

                        //move player
                        terrain.getTiles()[super.getI() - 1][super.getJ()].putPlayer(terrain.getPlayers()[index]);
                        //if there is player in backwarda tile remove it
                        if (terrain.getTiles()[super.getI() + 1][super.getJ()].getPlayerCount() > 1)
                                terrain.getTiles()[super.getI() + 1][super.getJ()].removePlayer(terrain.getPlayers()[index]);
                        else //else we need to put empty tile to backward place
                            terrain.getTiles()[super.getI() + 1][super.getJ()] =
                                    new Tile(super.getI() + 1, super.getJ());
                        return true;
                    }// there is obstacle in (i-1, j)
                    else
                        return false;
                }//(i-1, j) outside of terrain
                else
                    return false;
            }

            // 1 step right
            else if (way == 2) {
                if (super.getJ() + 1 <= terrain.getTerrainJBound()) {
                    // if there is no obstacle player move there
                    if (!(terrain.getTiles()[super.getI()][super.getJ() + 1] instanceof Obstacle)) {

                        //move player
                        terrain.getTiles()[super.getI()][super.getJ() + 1].putPlayer(terrain.getPlayers()[index]);

                        //if there is player in backwarda tile remove it
                        if (terrain.getTiles()[super.getI()][super.getJ() - 1].getPlayerCount() > 1){
                            terrain.getTiles()[super.getI()][super.getJ() - 1].removePlayer(terrain.getPlayers()[index]);
                        }
                            else//put empty tile backward place
                                terrain.getTiles()[super.getI()][super.getJ() - 1] =
                                        new Tile(super.getI(), super.getJ() - 1);

                        return true;
                    }// there is obstacle in (i, j+1)
                    else
                        return false;
                }//(i, j+1) outside of terrain
                else
                    return false;
            }

            //step left
            else {
                if (super.getJ() - 1 >= 0) {
                        // if there is no obstacle player move there
                        if (!(terrain.getTiles()[super.getI()][super.getJ() - 1] instanceof Obstacle)) {
                            //move player
                            terrain.getTiles()[super.getI()][super.getJ() - 1].putPlayer(terrain.getPlayers()[index]);

                            //if there is player in backward tile remove it
                            if (terrain.getTiles()[super.getI()][super.getJ() + 1].getPlayerCount() > 1)
                                terrain.getTiles()[super.getI()][super.getJ() + 1].removePlayer(terrain.getPlayers()[index]);
                            else//put empty tile backward place
                                terrain.getTiles()[super.getI()][super.getJ() + 1] = new Tile(super.getI(), super.getJ() + 1);

                            return true;
                        }// there is obstacle in (i, j-1)
                        else
                            return false;
                    }//(i, j-1) outside of terrain
                    else
                        return false;
                }
            }
            //return true;
        }

    public static void placePlayer(Terrain terrain) {
        int tempI, tempJ;

        for (int i = 0; i < terrain.getNumOfPlayer(); i++) {
            //valid i interval: [0, height - 1]
            tempI = (int) (Math.random() * terrain.getHeight());
            //valid j interval: [0, width - 1]
            tempJ = (int) (Math.random() * terrain.getWidght());
            if (!(terrain.getTiles()[tempI][tempJ] instanceof Obstacle || !terrain.getTiles()[tempI][tempJ].isEmpty())) {

                Player player = new Player(tempI, tempJ);
                terrain.getTiles()[tempI][tempJ].putPlayer(player);
                terrain.getPlayers()[i] = player;

            } else
                i--;//that mean we couldn't  put our player to valid place
        }

    }


}
