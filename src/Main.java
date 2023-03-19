import java.util.*;
public class Main {
    private Player player;
    public static void main(String[] args) {
        startGame();

    }
    public static void moveRoom(Room newRoom){
        Room currentRoom = newRoom;
        currentRoom = newRoom;
        currentRoom.describe();
    }
    public static void startGame(){
       System.out.println("                             ________    _____   ____ _____________________.____     ______________________                            \n" +
               "                            /  _____/   /  _  \\ |    |   \\      \\__    ___/|    |    \\_   _____/\\__    ___/                            \n" +
               "  ______   ______   ______ /   \\  ___  /  /_\\  \\|    |   /   |   \\|    |   |    |     |    __)_   |    |      ______   ______   ______ \n" +
               " /_____/  /_____/  /_____/ \\    \\_\\  \\/    |    \\    |  /    |    \\    |   |    |___  |        \\  |    |     /_____/  /_____/  /_____/ \n" +
               "                            \\______  /\\____|__  /______/\\____|__  /____|   |_______ \\/_______  /  |____|                               \n" +
               "                                   \\/         \\/                \\/                 \\/        \\/                                        \n" +
               "                                                                                                                                       \n" +
               "                                                                                                                                       \n" +
               "                                                                                                                                       \n" +
               "                                                                                                                                       \n" +
               "                                                                                                                                    ");





        System.out.println("TUTORIAL: \n" +
                "Type a direction to move between rooms (east, south, west, north) \n" +
                "Type: take + object to pick up objects \n" +
                "Type: drop + object to drop objects \n" +
                "Type: display/inventory to see your inventory \n" +
                "Type: map to see the map (buggy) \n" +
                "Type: inspect + object to inspect an object \n " +
                "Type: unlock + object to unlock the object");
        System.out.println();
        System.out.println();
        System.out.println("You awaken, dazed and disoriented in a dimly-lit room. As you look around, you realize you appear to be in some sort of dungeon... ");
        Map map = new Map();
        Room current_room = map.getCurrentRoom();
        Scanner input = new Scanner(System.in);


        //todo: Update to while(player is alive) hp > 0
        while(6==6) {
            String userInput = input.nextLine();
            String lowerUserInput = userInput.toLowerCase();
            String[] listPut = lowerUserInput.split(" ");

            boolean processed;
            processed = map.checkMove(lowerUserInput);
            if(processed) {
                current_room = map.getCurrentRoom();
                current_room.setHasVisited();
                continue;
            }
            map.checkInteraction(listPut);




        } //while loop
    } //method

}