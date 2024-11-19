package Paket;
import java.util.Random;

public class SpeedBot extends Robot {

    public SpeedBot(boolean isRedTeam){
        this.codeString = "X";
        this.prodNumber = "";
        this.isRedTeam = isRedTeam;
        Random random = new Random();
        health = random.nextDouble(1,2);
        attack = random.nextDouble(1,2);
        speed = random.nextDouble(3,4);
    }


    @Override
    public void attack(Simulation s) {

        Robot target = s.getLowestAttack(!isRedTeam);
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
            
            int index = s.blueTeam.indexOf(s.getLowestAttack(!isRedTeam));
            boolean isDead = s.getLowestAttack(!isRedTeam).getHitAndIsDestroyed(attack);
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
            int index = s.redTeam.indexOf(s.getLowestAttack(!isRedTeam));
            boolean isDead = s.getLowestAttack(!isRedTeam).getHitAndIsDestroyed(attack);
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
