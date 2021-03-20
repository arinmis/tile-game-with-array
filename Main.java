import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 *  @author Mustafa Arinmis
 *  @since 15.11.2020
 *
 * */

public class Main {

    public static void main(String[] args) throws IOException {

        // reading 3 variables from var.cfg
        File varFile = new File("var.cfg");

        // I assigned 10 to each variable as a placeholder
        int height = 10, width = 10, numOfPeople = 300, numOfObstacle = 10;
        try {
            Scanner configInput = new Scanner(varFile);
            while(configInput.hasNext()){
                String variable = configInput.next();
                if(variable.equals("height")){
                    // reading colon, we don't need to keep that
                    configInput.next();
                    height = configInput.nextInt();
                }else if(variable.equals("width")) {
                    configInput.next();
                    width = configInput.nextInt();
                }
                else if(variable.equals("numberOfPeople")) {
                    configInput.next();
                    numOfPeople = configInput.nextInt();
                }
                else{
                    configInput.next();
                    numOfObstacle = configInput.nextInt();
                }
            }
        }
        catch(FileNotFoundException ex){
            System.out.println("Main.java and var.cfg files must be in same directory");
            System.exit(0);
        }
        catch (InputMismatchException ex) {
            System.out.println("var.cfg file must be in this format:\n" +
                    "height : 5000 \n" +
                    "width : 5000\n" +
                    "numberOfPeople : 500\n" +
                    "numberOfObstacle : 2000" );

            System.exit(1);
        }

        Scanner input = new Scanner(System.in);
        //creatting terrain
        Terrain terrain = new Terrain(height, width, numOfPeople, numOfObstacle);

        //Assinging avatars
        Obstacle.placeObstacles(terrain);
        Player.placePlayer(terrain);
        FinishTile finishTile = FinishTile.placeFinishTile(terrain);

        // turn counter
        int count = 1;

        // Writing both: console and file
        File file = new File("Turns");
        //creating 'Turns' folder where 'Main.java' executed to put each turn's .txt file
        if (file.mkdir()) {
            System.out.println("'Turns' folder created");
        } else {
            System.out.println("Warning: Folder creation failed, please create empty 'Turns' folder");
        }

        //Writing both: console and file
        terrain.printStatus(count);
        terrain.turnWriter(count++);

        System.out.println("Shall players move one step or until the end? Type \'Y\' or \'N\': ");
        String decision = input.next().toLowerCase();

        while (true) {
            moveAllPlayer(terrain);



            if(finishTile.isFinished()) {
                System.out.println("========================== Game over ==========================");
                terrain.printStatus(count);
                terrain.turnWriter( count++);
                break;
            }

            //Writing both: console and file
            terrain.printStatus(count);
            terrain.turnWriter(count++);

            if (decision.equals("y")) {
                System.out.println("Shall players move one step or until the end? Type \'Y\' or \'N\': ");
                decision = input.next().toLowerCase();
            }

        }
    }

    public static void moveAllPlayer(Terrain terrain) {
        // All player move in order to players array in terrain
        // if any player has moved yet after 1000 trial
        // We assumed that player stuck because of obstacles
        //Note: This assumption might be wrong odds of 1.15e-123 percent
        int numOfMoveTry = 0;
        for(int i=0; i< terrain.getNumOfPlayer(); i++ ) {
                if(!terrain.getPlayers()[i].move(terrain, i)) {
                    i--;
                    numOfMoveTry++;
                }
                else
                    numOfMoveTry = 0;

                if(numOfMoveTry == 1000) {
                    System.out.println("Player might be stuck, give another try");
                    System.exit(3);
                }
        }
    }

}
