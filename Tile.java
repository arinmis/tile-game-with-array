/**
 *
 *  @author Mustafa Arinmis
 *  @since 15.11.2020
 *
 * */

public class Tile {
    private int i;
    private int j;
    //Tile can keep more than one player
    //Capacity is 3 for now
    Player[] tilePlayers = new Player[2];
    private int playerCount = 0;

    public Tile(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public void putPlayer(Player player) {
        if(this.playerCount == this.tilePlayers.length)
            tilePlayers = this.extendArray();
        //player.setIndex(playerCount);
        tilePlayers[playerCount++] = player;
        player.setI(this.i);
        player.setJ(this.j);
    }

    public boolean isEmpty() {
        return this.playerCount == 0;
    }

    public void removePlayer(Player player) {
        // remove given player from tilePlayer
        this.playerCount--;

        for(int i=0; i<tilePlayers.length - 1; i++) {
            if(tilePlayers[i] == player) {
                for(int j=i; j<tilePlayers.length - 1; j++) {
                    if(tilePlayers[j+1] == null)
                        break;
                    tilePlayers[j] = tilePlayers[j+1];
                }
                break;
            }
        }
        this.tilePlayers[playerCount] = null;

    }

    public int getPlayerCount() {
        return this.playerCount;
    }

    public Player[] extendArray() {
        // this method will add one extra space to
        // tilePlayer array when extra space needed
        Player[] temp = new Player[tilePlayers.length + 1];
        System.arraycopy(this.tilePlayers, 0, temp, 0, tilePlayers.length);

        return temp;
    }


    @Override
    public String toString() {
        // each tile assumed free space
        //when there is not player
        if(playerCount == 0)
            return "0";
        else
            return "p";

    }

    //getters and setters
    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }


}
