import java.lang.reflect.Array;
import java.util.*;
public class Player {
    private ArrayList<Object> inventory;
    private int hp;


    public Player(int hp) {
        inventory = new ArrayList<Object>();
        this.hp = hp;

    }

    public void take(Object item, Room currentRoom) {
        if (item.getWeight() < 50) {
            //add to player inventory
            inventory.add(item);
            System.out.println("You picked up the " + item.getName());
            //add to currentRoom
            currentRoom.removeItem(item);
        } else {
            System.out.println("It weighs too much! " + (item.getWeight()));
        }
    }

    public void drop(Object item, Room currentRoom) {
        currentRoom.addItem(item);
        inventory.remove(item);
        System.out.println("You dropped the " + item.getName());

    }

    public void displayInventory() {
        System.out.print("You are currently holding: ");
        if (inventory.size() > 0) {
            for (Object i : inventory) {
                System.out.print(i.getName() + " ");
            }
        }
        else{
            System.out.println(" nothing :( ");
        }
    }
    public ArrayList<Object> getInventory(){
        return inventory;
    }

    public int setHp(Player player){
        return hp -= 100;
    }
    public int checkHp(){
        return hp;
    }
    }
