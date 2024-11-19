package Paket;
import java.util.Random;

public class SpreadBot extends Robot {

    public SpreadBot(boolean isRedTeam){
        this.codeString = "K";
        this.prodNumber = "";
        this.isRedTeam = isRedTeam;
        Random random = new Random();
        health = random.nextDouble(2,3);
        attack = random.nextDouble(0.5,1);
        speed = random.nextDouble(0.5,1.5);
    }


    @Override
    public void attack(Simulation s) {
        // TODO Auto-generated method stub
        if (isRedTeam == true){
            
            if (s.blueTeam.size() > 3){
                Robot[] willBeAttacked = new Robot[3];
                willBeAttacked = s.getLowestHealth3(!isRedTeam);
                for (int i = 0; i < willBeAttacked.length; i++){
                    
                    boolean isDead = willBeAttacked[i].getHitAndIsDestroyed(attack);
                    System.out.println(this.prodNumber + " attacks " + willBeAttacked[i].prodNumber + " with " + String.format("%.3f", this.attack) + " damage.");
                    if (isDead == true){
                        System.out.println(willBeAttacked[i].prodNumber + " is destroyed!");
    
                        s.blueTeam.remove(willBeAttacked[i]);
                }
                    else {
                        System.out.println(willBeAttacked[i].prodNumber + " remaining health: " + String.format("%.3f", willBeAttacked[i].health));
                    }
                }
            }
            else{
                for (int i = 0; i < s.blueTeam.size(); i++){
                    boolean isDead = s.blueTeam.get(i).getHitAndIsDestroyed(attack);
                    System.out.println(this.prodNumber + " attacks " + s.blueTeam.get(i).prodNumber + " with " + String.format("%.3f", this.attack) + " damage.");
                    if (isDead == true){
                        System.out.println(s.blueTeam.get(i).prodNumber + " is destroyed!");
    
                        s.blueTeam.remove(s.blueTeam.get(i));
                }
                    else {
                        System.out.println(s.blueTeam.get(i).prodNumber + " remaining health: " + String.format("%.3f", s.blueTeam.get(i).health));
                    }
                }
                }
            }

            else{
                if (s.redTeam.size() > 3){
                    Robot[] willBeAttacked = new Robot[3];
                    willBeAttacked = s.getLowestHealth3(!isRedTeam);
                    for (int i = 0; i < willBeAttacked.length; i++){
                        boolean isDead = willBeAttacked[i].getHitAndIsDestroyed(attack);
                        System.out.println(this.prodNumber + " attacks " + willBeAttacked[i].prodNumber + " with " + String.format("%.3f", this.attack) + " damage.");
                        if (isDead == true){
                            System.out.println(willBeAttacked[i].prodNumber + " is destroyed!");
        
                            s.redTeam.remove(willBeAttacked[i]);
                    }
                        else {
                            System.out.println(willBeAttacked[i].prodNumber + " remaining health: " + String.format("%.3f", willBeAttacked[i].health));
                        }
                    }
                }
                else{
                    for (int i = 0; i < s.redTeam.size(); i++){
                        boolean isDead = s.redTeam.get(i).getHitAndIsDestroyed(attack);
                        System.out.println(this.prodNumber + " attacks " + s.redTeam.get(i).prodNumber + " with " + String.format("%.3f", this.attack) + " damage.");
                        if (isDead == true){
                            System.out.println(s.redTeam.get(i).prodNumber + " is destroyed!");
        
                            s.redTeam.remove(s.redTeam.get(i));
                    }
                        else {
                            System.out.println(s.redTeam.get(i).prodNumber + " remaining health: " + String.format("%.3f", s.redTeam.get(i).health));
                        }
                    }
                    }
            }
            
            
            
            
    }
    

    public String toString(){
        return prodNumber + " Health: " + String.format("%.3f", health)
        + " Attack: " + String.format("%.3f", attack) + " Speed: " + String.format("%.3f", speed);
    }
    
}
