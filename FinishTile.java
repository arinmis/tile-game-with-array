/**
 *
 *  @author Mustafa Arinmis
 *  @since 15.11.2020
 *
 * */
public class FinishTile extends  Tile{

    //Capacity is 10 for now
    Player[] finishPlayers = new Player[2];
    private int playerCount = 0;

    public FinishTile(int i, int j) {
        super(i, j);
    }



    public static FinishTile placeFinishTile(Terrain terrain) {
        /* this method place finish tile and return it
            to use when game finish */

        int tempI, tempJ;

        while(true) {    //valid i interval: [0, height - 1]
            tempI = (int) (Math.random() * terrain.getHeight());
            //valid j interval: [0, width - 1]
            tempJ = (int) (Math.random() * terrain.getWidght());
            if (!(terrain.getTiles()[tempI][tempJ] instanceof Obstacle || !terrain.getTiles()[tempI][tempJ].isEmpty() ) ) {
                terrain.getTiles()[tempI][tempJ] = new FinishTile(tempI, tempJ);
                break;
            }

        }
        return (FinishTile) terrain.getTiles()[tempI][tempJ];

    }

    public void putPlayer(Player player) {
        if(this.playerCount == this.finishPlayers.length)
            finishPlayers = this.extendArray();

        finishPlayers[playerCount++] = player;
        player.setI(super.getI());
        player.setJ(super.getJ());
    }

    public Player[] extendArray() {
        // this method will add one extra space to
        // tilePlayer array when extra space needed
        Player[] temp = new Player[finishPlayers.length + 1];
        System.arraycopy(this.finishPlayers, 0, temp, 0, finishPlayers.length);
        return temp;
    }

    public boolean isFinished() {
        return playerCount > 0;
    }
    @Override
    public String toString() {
        // each tile assumed free space
        //when there is not player
        if(playerCount == 0)
            return "F";
        else
            return "p";
    }
}
