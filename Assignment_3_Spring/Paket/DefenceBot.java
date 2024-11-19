package Paket;
import java.util.Random;

public class DefenceBot extends Robot {{

    
    this.health = 3;
    this.attack = 0.5;
    this.speed = 0.5;
    this.isRedTeam = false;}

    public DefenceBot(boolean isRedTeam){
        this.codeString = "D";
        this.prodNumber = "";
        this.isRedTeam = isRedTeam;
        Random random = new Random();
        health = random.nextDouble(3,6);
        attack = random.nextDouble(0.5,1);
        speed = random.nextDouble(0.5,1);
    }


    @Override
    public void attack(Simulation s) {
        Robot target = s.getHighestHealth(!isRedTeam);
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
            
            int index = s.blueTeam.indexOf(s.getHighestHealth(!isRedTeam));
            boolean isDead = s.getHighestHealth(!isRedTeam).getHitAndIsDestroyed(attack);
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
            int index = s.redTeam.indexOf(s.getHighestHealth(!isRedTeam));
            boolean isDead = s.getHighestHealth(!isRedTeam).getHitAndIsDestroyed(attack);
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

