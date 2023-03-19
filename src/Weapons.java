class Weapon extends Object {
    private int damage;


    public Weapon(String name, int weight, String description, int damage){
        super(name, weight, description);
        this.damage = damage;
    }
}
