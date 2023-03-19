 class Key extends Object{
    String typeKey;
    public Key(String name, String description,  String typeKey){
        super(name, 1, description);
        this.typeKey = typeKey;
    }
    public String getType(){
        return typeKey;
    }

}
