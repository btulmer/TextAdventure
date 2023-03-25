import java.util.*;
 class Chest extends Object{
    private boolean locked;
    private String chestType;
    private Object loot;

    public Chest(String description, String chestType, Object loot){
        super("chest", 100, description);
        this.chestType = chestType;
        this.loot = loot;
        locked = true;
    }

    public void tryUnlock(ArrayList<Object> playerInventory, Room currentRoom) {
        if (!locked) {
            System.out.println("The chest is already unlocked!");
            return;
        }
        for (Object playerItem : playerInventory) {
            if (playerItem.getName().equals("key")) {
                if (playerItem.getType().equals(chestType)) {
                    locked = false;
                    System.out.println("You unlocked the chest!");
                    currentRoom.addItem(loot);
                    System.out.println("It dropped: " + loot.getName());
                    return;
                }

            }
        }
        System.out.println("You need the right key.");
    }
 }


