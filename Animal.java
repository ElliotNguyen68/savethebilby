class Animal extends BaseAnimal {
    // using this static variable to create incremental id, whenever an object belong to Bilby class or extended from Bilby class is instanced, this variable will increased by 1.
    static int id_int=0;

    public Animal(){
        Animal.id_int+=1;
    }

    @Override
    void force_die() {
        this.is_alive = false;
    }


    @Override
    boolean giving_birth_now() {
        // TODO Auto-generated method stub
        if (this.is_alive == false) {
            return false;
        }
        return Math.random() <= this.giving_birth_prob;
    }

    public String get_id(){
        return this.id;
    }

    public boolean get_is_alive(){
        return this.is_alive;
    }

    public double get_giving_birth_prob(){
        return this.giving_birth_prob;
    }

    public void set_is_alive(boolean is_alive){
        this.is_alive=is_alive;
    }

    public void set_giving_birth_prob(double giving_birth_prob){
        if(giving_birth_prob<0| giving_birth_prob>1){
            // assertTrue("Not a valid giving birth probability!", false);
            assert false :"Not a valid giving birth probability!" ;
        }  ; 
        this.giving_birth_prob=giving_birth_prob;
    }
}