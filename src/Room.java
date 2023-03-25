import java.util.*;
public class Room {
    private ArrayList<Object> roomItems;
    private String description;
    private boolean filler;
    private boolean hasVisited;

    //todo: add objects to room constructor
    public Room(String description, boolean filler)
    {
        this.description = description;
        this.filler = filler;
        roomItems = new ArrayList<Object>();
        hasVisited = false;
    }

    public boolean getFiller(){
        return filler;

    }

    public void removeItem(Object item){
        roomItems.remove(item);
    }
    public void addItem(Object item){
        roomItems.add(item);
    }
    public void describe(){
        System.out.println(description);
    }

    public String objectPrint(){
        if(roomItems.size() > 0) {
            String line = "";
            for (Object i : roomItems) {
                line += i.getName() + " ";
            }
            return " Inside the room are the following objects: " + line;
        }
        else{
            return "";
        }
    }

    public ArrayList<Object> getRoomItems(){
        return roomItems;
    }



    public boolean getHasVisited(){
        return hasVisited;
    }
    public boolean setHasVisited(){
        return hasVisited = true;
    }







}
