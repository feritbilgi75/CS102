package Paket;
public class Robot {
    double health = 0;
    double speed = 0;
    double attack = 0;
    String codeString = "N";
    String prodNumber = "";

    boolean isRedTeam = false;

    public void attack(Simulation s){
        if (isRedTeam == true){           
            int index = 0;
            boolean isDead = s.blueTeam.get(index).getHitAndIsDestroyed(attack);
            System.out.println(this.prodNumber + " attacks  with " + String.format("%.3f", this.attack) + " damage.");

            if (isDead == true){
                s.blueTeam.remove(s.blueTeam.get(index));
            }
        }
        else{
            int index = 0;
            boolean isDead = s.redTeam.get(index).getHitAndIsDestroyed(attack);
            System.out.println(this.prodNumber + " attacks  with " + String.format("%.3f", this.attack) + " damage.");

            if (isDead == true){
                s.blueTeam.remove(s.redTeam.get(index));
            }
        }
    }
    
    public boolean getHitAndIsDestroyed(double damage){
        boolean isDead = false;
        health -= damage;
        if (health <= 0){
            isDead = true;
        }
        return isDead;
    }
    public String toString(){
        return prodNumber + " Health: " + String.format("%.3f", health)
        + " Attack: " + String.format("%.3f", attack) + " Speed: " + String.format("%.3f", speed);
    }
} 
