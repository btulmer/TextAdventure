import java.util.*;
public class Engine {
    private Room[][] layout;
    private int currentRow;
    private int currentCol;
    private Room currentRoom;
    private Player player;
    private boolean hasRead;


    public Engine(){
        createDefaultMap();
        createDefaultObjects();
        createDefaultPlayer();
    }

    public void createDefaultMap(){

        //create room layoutRoom[][]
        Room startRoom = new Room("A torchlit passage lies north. ", false);
        Room room2 = new Room("You can go north and south.", false);
        Room filler = new Room("A torchlit passage lies northward", true);
        Room room4 = new Room("The room slowly narrows into an ominous passageway. In the dim light, you can barely make out a message on a sign that is posted on a doorway eastward: \n MAZE ENTRANCE", false);
        Room room5 = new Room("You are in a cold, dark, musty room. You can see a passage to the left, right forward, and back.", false);
        Room room6 = new Room("A sleeping dragon rests in the center of the room. As you look closer, you realize that the dragon is lying on a huge pile of gold. The only exit is south.", false);
        Room room7 = new Room("You enter a small, musty room. It is empty besides a passage to the north and east.", false);
        Room room8 = new Room("You enter a brightly lit, cavernous room. A small shred of paper lies on the ground near the center of the room. A sword rests next to it.", false);
        Room room9 = new Room("You can go east and south.", false);
        Room room10 = new Room("You can go north, east, and west", false);
        Room room11 = new Room("You reach a dead end. You can go south", false);
        Room room12 = new Room("You reach a dead end. You can go north", false);
        Room room13 = new Room("You, can go north, south, and west",false);
        Room room14 = new Room("You reached a dead end. You can go south", false);
        Room room15 = new Room("You can go east and north", false);
        Room room16 = new Room("You reached the end! A blue chest lies dramatically in the center of the room.", false);

        //layout = new Room[][];
        layout = new Room[][]{
                //todo: list rooms in numerical order, so grid is completely filled. Create filler rooms that don't exist.

                {filler, filler, filler, filler, room11, room14, filler},
                {filler, room6, filler, room9, room10, room13, filler},
                {filler, room7, room4, room2, filler, room15, room16},
                {filler, room8, filler, room12, filler, filler, filler},
                {filler, startRoom, filler, filler, filler, filler, filler}


        };

        currentRow = 4;
        currentCol = 1;
        layout[currentRow][currentCol].describe();


    }
    //todo: Create player in map, call in constructor, use player to call take mthod in loop

    public void createDefaultObjects(){
        Object sword = new Weapon("sword", 10, "A pointy object.", 20 );
        Object gold = new Object("gold", 1, "A shiny gold piece.");
        Object chest = new Chest("A blue chest that requires a key.","blue", gold);

        Object paper = new Object("paper", 0, "The paper has a riddle on it: They don't care for reasons, they live for the war. And they cut so much deeper than all of your words. \n" +
                "Say your answer out loud.");
        //Object
        //int row = (int)(Math.random()*3)+1;
        //int col = (int)(Math.random()*3)+1;

        getRoom(3,1).addItem(sword);
       // layout[row][col].addItem(sword);
        getRoom(3,1).addItem(paper);
        getRoom(2,6).addItem(chest);
    }
    public void createDefaultPlayer(){
        player = new Player(100);
    }

    public boolean checkMove(String userInput){
        if(userInput.equals("map")){
            displayMap();
            return true;
        }
        if(userInput.indexOf("north") > -1){
            if(currentRow > 0) {
                if(!layout[currentRow -1][currentCol].getFiller()) {
                    currentRow -= 1;
                    Main.moveRoom(layout[currentRow][currentCol]);
                }
                else{
                    System.out.println("You can't go that way" );
                }
            }
            else{
                System.out.println("You ran into a wall.");
            }
            return true;
        }
        else if (userInput.indexOf("east") > -1){
            if(currentCol < layout[0].length-1) {
                if(!layout[currentRow][currentCol +1].getFiller()) {
                    currentCol += 1;
                    Main.moveRoom(layout[currentRow][currentCol]);
                }
                else{
                    System.out.println("You can't go that way");
                }
            }
            else{
                System.out.println("You ran into a wall.");
            }
            return true;
        }
        else if (userInput.indexOf("south") > -1){
            if(currentRow < layout.length-1) {
                if(!layout[currentRow +1][currentCol].getFiller()) {
                    currentRow += 1;
                    Main.moveRoom(layout[currentRow][currentCol]);
                }
                else{
                    System.out.println("You can't go that way");
                }
            }
            else{
                System.out.println("You ran into a wall.");
            }
            return true;
        }
        else if (userInput.indexOf("west") > -1){
            if(currentCol > 0) {
                if(!layout[currentRow][currentCol -1].getFiller()) {
                    currentCol -= 1;
                    Main.moveRoom(layout[currentRow][currentCol]);
                }
                else{
                    System.out.println("You can't go that way");
                }
            }
            else{
                System.out.println("You ran into a wall.");
            }
            return true;
        }

        return false;
    }

    public void checkInteraction(String[] listPut){
        currentRoom = layout[currentRow][currentCol];

        if (listPut[0].equals("take")) {
            ArrayList<Object> list = currentRoom.getRoomItems();
            for (int i = 0; i < list.size(); i++) {
                if (listPut[1].equals(list.get(i).getName())) {
                    player.take(list.get(i), currentRoom);
                    return;
                }
            }
        } else if(listPut[0].equals("drop")) {
            ArrayList<Object> list = player.getInventory();
            for (Object item : list) {
                if (item.getName().equals(listPut[1])) {
                    player.drop(item, currentRoom);
                    return;
                }
            }
        } else if (listPut[0].equals("inventory")  || listPut[0].equals("display")) {
                    player.displayInventory();
                }
        else if(listPut[0].equals("unlock")) {
            ArrayList<Object> roomItems = currentRoom.getRoomItems();
            ArrayList<Object> playerItems = player.getInventory();
            if (listPut[1].equals("chest")) {
                for (Object roomItem : roomItems) {
                    if (roomItem.getName().equals("chest")) {
                        ((Chest) roomItem).tryUnlock(playerItems, currentRoom);
                        return;
                        //remove .i after unlock
                    }
                }
                System.out.println("I can't unlock that.");
            }
        }
        else if(listPut[0].equals("look")){
            currentRoom.describe();
        }
        else if(listPut[0].equals("inspect")){
            for(Object playerItem : player.getInventory()){
                if(playerItem.getName().equals(listPut[1])){
                    playerItem.inspectObject();
                    return;
                }
            }
        }
        else if(listPut[0].equals("say") && listPut[1].equals("sword")){
            for(Object playerItem : player.getInventory()) {
                if(playerItem.getHasInspect()) {
                    Object key = new Key("key", "A shiny blue key.", "blue");
                    getRoom(currentRow, currentCol).addItem(key);
                    System.out.println("A blue key appeared in the room.");
                    return;
                }
            }
        }
        else if(layout[currentRow][currentCol] == layout[1][1]){
            for(String input : listPut) {
                if (input.equals("say") || input.equals("wake") || input.equals("fight") || input.equals("north") || input.equals("east") || input.equals("west") || input.equals("attack") || input.equals("pet") || input.equals("touch") || input.equals("take") || input.equals("gold")) {
                    System.out.println("The dragon scorched you. Maybe that was a bad idea. \n " +
                            "                                                 /===-_---~~~~~~~~~------____\n" +
                            "                                                |===-~___                _,-'\n" +
                            "                 -==\\\\                         `//~\\\\   ~~~~`---.___.-~~\n" +
                            "             ______-==|                         | |  \\\\           _-~`\n" +
                            "       __--~~~  ,-/-==\\\\                        | |   `\\        ,'\n" +
                            "    _-~       /'    |  \\\\                      / /      \\      /\n" +
                            "  .'        /       |   \\\\                   /' /        \\   /'\n" +
                            " /  ____  /         |    \\`\\.__/-~~ ~ \\ _ _/'  /          \\/'\n" +
                            "/-'~    ~~~~~---__  |     ~-/~         ( )   /'        _--~`\n" +
                            "                  \\_|      /        _)   ;  ),   __--~~\n" +
                            "                    '~~--_/      _-~/-  / \\   '-~ \\\n" +
                            "                   {\\__--_/}    / \\\\_>- )<__\\      \\\n" +
                            "                   /'   (_/  _-~  | |__>--<__|      |\n" +
                            "                  |0  0 _/) )-~     | |__>--<__|      |\n" +
                            "                  / /~ ,_/       / /__>---<__/      |\n" +
                            "                 o o _//        /-~_>---<__-~      /\n" +
                            "                 (^(~          /~_>---<__-      _-~\n" +
                            "                ,/|           /__>--<__/     _-~\n" +
                            "             ,//('(          |__>--<__|     /                  .----_\n" +
                            "            ( ( '))          |__>--<__|    |                 /' _---_~\\\n" +
                            "         `-)) )) (           |__>--<__|    |               /'  /     ~\\`\\\n" +
                            "        ,/,'//( (             \\__>--<__\\    \\            /'  //        ||\n" +
                            "      ,( ( ((, ))              ~-__>--<_~-_  ~--____---~' _/'/        /'\n" +
                            "    `~/  )` ) ,/|                 ~-_~>--<_/-__       __-~ _/\n" +
                            "  ._-~//( )/ )) `                    ~~-'_/_/ /~~~~~~~__--~\n" +
                            "   ;'( ')/ ,)(                              ~~~~~~~~~~\n" +
                            "  ' ') '( (/\n" +
                            "    '   '  `");
                    System.out.println("YOU DIED  " +
                            "                           ,..oooooooooob..\n" +
                            "                       ,.dodOOOO\"\"\"\"\"\":\"ooPO88bo..\n" +
                            "                     .o8O\"\"\" '            \"'\"\"\"PO8b.\n" +
                            "                 .dd8P'\"                       ''::Y8o.\n" +
                            "               ,d8Po'                             \"':7Ob;\n" +
                            "              d8P::'                                 ';:8b.\n" +
                            "            ;d8''\"                                     ';Y8;\n" +
                            "          ,d8O:'                                        ';:8b.\n" +
                            "         ,88o:'                                           ';Yb.\n" +
                            "        ,8P::'                                           . ';Yb\n" +
                            "       ,8o;:'                                          ,;'  ':8b\n" +
                            "      ,8:::'                                           ;:    :;8b\n" +
                            "     d8o;::                                            o:     ::8,\n" +
                            "    ,8':::                                            :::     :;Y8\n" +
                            "    8'oo:'                                            :::     :::8:\n" +
                            "   dP;:YO                                             ':::;.;;:::Y8.\n" +
                            "  ,8:::;Yb                                            :b::::::::::8b\n" +
                            "  dO;::::8b                                           'Yb::::::::::8.\n" +
                            " ,8;:::::O8,                                           'Y88::::::::8:\n" +
                            " 8P;::::::88                                             `8O::::::::O\n" +
                            " d::::::::88:                                             O8;:::::::8\n" +
                            " 8:::::::888:                                             88b:::::::O:\n" +
                            ",8::::::::88:                                            :888Oooo::;Y:\n" +
                            "dO:::::::bO8:             ..:.::::::::::...:             :888888P;::db\n" +
                            "OP:::::::O88:         ..o8888:::::::::::::)8888bo..       O8888O:::::8\n" +
                            "O;::::::::88'    ..od888888888::::\"\"\"\":::88888888888oo;   `8888;:::::8\n" +
                            "O:::ob:::;8:  ,d88888888888888::       ':88888888888888b;  '\"88;:::::8\n" +
                            "OO::;Yo::OP' d888888888888888O:'      ,.;8888888888888888b  ,88::::::8\n" +
                            "YO:::;Y::Ob ,8888888888888888;::       :;88888888888888888  :88::::::8\n" +
                            " 8;::::b;8' :8888888888888888o::        ':8888888888888888  :888d::)88\n" +
                            " Y:::::88P   888888888888888888'         'O888888888888888  :88888888P\n" +
                            " `b:::;8O    d888888888888888P'          ,8888888888888888  '88888888:\n" +
                            "  Y::::8:   ,88888888888888P:      ..    '8888888888888888   Y8888888:\n" +
                            "  8O::;8'   :88888888888888:      d88,   ':Y88888888888888   '8888888:\n" +
                            "  'YbooO    :8888888888P:8P:     :8888:    '':Y8888888888P    \"Y888YP\n" +
                            "   '888:     8888888P:;'8O:'     :8P88b       '\"O888888P\"       ;:;o'\n" +
                            "    `88:     \"oOOo:.::)O:;:      :8:888.        :8b'            :::'\n" +
                            "     88:      '\"\"\"\" ,do;:'      ,88bO88b     ,.o::PO:;.;.      :::'\n" +
                            "     88';           :' `Yb      d88O`888b    O8\"::::o::::::::::o:;\n" +
                            "     8O::b         ;:   ''     d88Po O888    :8;:\"\"\" '\";88::::o:::\n" +
                            "     YO:;Yb    :o.;::          O8P.: O888     'Y::    :YO8b:::::O'\n" +
                            "      Y:::8b  o;O:::;.         OO;:: OO;'       ''\"  .;bO88\"d:od'\n" +
                            "      `b::;8: :8bo::::;.       OO::: OK:          ,;:8888P',8OP\"\n" +
                            "       Yb:;OO  O888b::::;.     'O:O\",;\"'          ;o8888P  :O\"\n" +
                            "       '`Y888. :88888::::::     ':' db           ;o8888P   8P\n" +
                            "          '`8;  O8888::::::        '8'          :o8888P   :8'\n" +
                            "            OO: :8888::::::         Y:         ,::;888'   :8\n" +
                            "            `8,  o888\"::::'          '         ;:::88:    OP\n" +
                            "            ,8:   O888O8POYOOO\"OPOOPYO8OO8OO888888888'    O:\n" +
                            "            88:   '888o::o':Y: d  O  'Y:'8: O\"Y:`K:8o;    8'\n" +
                            "           88O;    ;88o;:::: : :  :   '  `:   '  ,:8 :   :8\n" +
                            "           O8O:.  ,:OP\"8bd;':: d...      ,.  .db.'\"8 ::  :O  -hrr-\n" +
                            "          ,88O::. ;:O: O\"'\"YP\"YPYP'YO\"\"\"`8K`O\"O `b:O.:;  :O\n" +
                            "          888o:::.:;Ob : :::: :::: :P ,  OO : :  O;:::: ;:O\n" +
                            "          888O::::::::`dbd::b.d::: :: db ::,d.8o;O;::::::dP\n" +
                            "           888::::::::;:\"\"\"\"\"Y8888od8d88o8P\"\"'\"  ':::::'d'\n" +
                            "           \"Y88Odo:\"::::         `\"\"\"\"'           ':::)P'\n" +
                            "             \"\"88888O:::                          ;::dP\n" +
                            "                '\"\"88O:::                   oo;  ;O88'\n" +
                            "                   `Y8O::.  ,.      .      '8b::O88'\n" +
                            "                     Y8b::. ,)O   ,;'        ::O88'\n" +
                            "                      '88d:..:   ,d:        ;'d88'\n" +
                            "                       'Y88d::;.;:8b,   ,..:O88P'\n" +
                            "                         \"\"88oodO888O::::bd88P'\n" +
                            "                           '`8888888888888P\"'\n" +
                            "                                 \"\"\"\"\"\"\"\"'\n" +
                            "\n" +
                            "------------------------------------------------\n" +
                           "YOU DIED");

                    player.setHp(player);
                    System.exit(1);
                    return;
                }
            }
        }

            }


    private Room getRoom(int row, int col){
        return layout[row][col];
    }

    public Room getCurrentRoom(){
        return getRoom(currentRow, currentCol);
    }

    public void displayMap(){
        for(int r = 0; r < layout.length; r++){
            for(int c = 0; c < layout[0].length; c++) {
                if (r == currentRow && c == currentCol) {
                    System.out.print("x");
                    continue;
                }

                 if(getRoom(r,c).getFiller()){
                    System.out.print("?");
                }
                else{
                    if(layout[r][c].getHasVisited()){
                        System.out.print(" ");}
                    else{
                        System.out.print("?");
                    }
                }
            }
            System.out.println();

        }
    }
    public Player getPlayer(){
        return player;
    }
}

