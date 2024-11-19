package Paket;
import java.util.Random;

public class SimpleBot extends Robot {

    public SimpleBot(boolean isRedTeam){
        this.codeString = "S";
        this.prodNumber = "";
        this.isRedTeam = isRedTeam;
        Random random = new Random();
        health = random.nextDouble(2,3);
        attack = random.nextDouble(1,2);
        speed = random.nextDouble(1,2);
    }


    @Override
    public void attack(Simulation s) {
        // TODO Auto-generated method stub

        Robot target = s.getRandomTarget(!isRedTeam);
        boolean isDead = target.getHitAndIsDestroyed(attack);
        System.out.println(this.prodNumber + " attacks " + target.prodNumber + " with " + String.format("%.3f", this.attack) + " damage.");

        if (isDead == true){
            System.out.println(target.prodNumber + " is destroyed!");

            s.removeRobot(target, !isRedTeam);
        }
        else {
            System.out.println(target.prodNumber + " remaining health: " + String.format("%.3f", target.health));
        }

        /* 
        if (isRedTeam == true){
            Random random = new Random();
            int index = random.nextInt(s.blueTeam.size());
            boolean isDead = s.blueTeam.get(index).getHitAndIsDestroyed(attack);
            System.out.println(this.prodNumber + " attacks " + s.blueTeam.get(index).prodNumber + " with " + String.format("%.3f", this.attack) + " damage.");
            if (isDead == true){
                System.out.println(s.blueTeam.get(index).prodNumber + " is destroyed!");
                s.blueTeam.remove(s.blueTeam.get(index));
            }
            else {
                System.out.println(s.blueTeam.get(index).prodNumber + " remaining health: " + String.format("%.3f", s.blueTeam.get(index).health));
            }
        }
        else{
            Random random = new Random();
            int index = random.nextInt(s.redTeam.size());
            boolean isDead = s.redTeam.get(index).getHitAndIsDestroyed(attack);
            System.out.println(this.prodNumber + " attacks " + s.redTeam.get(index).prodNumber + " with " + String.format("%.3f", this.attack) + " damage.");
            if (isDead == true){
                System.out.println(s.redTeam.get(index).prodNumber + " is destroyed!");

                s.redTeam.remove(s.redTeam.get(index));
            }
            else {
                System.out.println(s.redTeam.get(index).prodNumber + " remaining health: " + String.format("%.3f", s.redTeam.get(index).health));
            }
        }
        */
    }
    

    public String toString(){
        return prodNumber + " Health: " + String.format("%.3f", health)
        + " Attack: " + String.format("%.3f", attack) + " Speed: " + String.format("%.3f", speed);
    }
    
}
