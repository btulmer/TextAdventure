import java.util.*;
public class Object {
    private String name;
    private int weight;
    private String type;
    private String description;
    private boolean hasInspect;

    public Object(String name, int weight, String description){
        this.name = name;
        this.weight = weight;
        this.description = description;
        hasInspect = false;
    }


    public String getName(){
        return name;
    }

    public int getWeight(){
        return weight;
    }

    public void unlock(Room currentRoom){
        System.out.println(name + " can't be unlocked");
    }

    public String getType(){
        return type;
    }

    public void inspectObject(){
        hasInspect = true;
        System.out.println(description);
    }
    public boolean getHasInspect(){
        return hasInspect;
    }

}
