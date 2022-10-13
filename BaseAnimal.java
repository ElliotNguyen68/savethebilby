abstract class BaseAnimal {
    String id;
    public boolean is_alive;
    public double giving_birth_prob;
    
    abstract boolean giving_birth_now();
    abstract void force_die();
}

