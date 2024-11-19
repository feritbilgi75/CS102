import java.util.ArrayList;
import java.util.Random;

public class Archer extends Unit {
    public Archer(){
        name = "Archer";
        level = 1;
        health = level + 1;
        attack = level + 1;
        maxHealth = health;
        actionString = "";

    }
    

    public int getAttack(){
        return attack;
    }

    public int getMaxHealth(){
        return maxHealth;
    }

    public void firstPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, 
    ArrayList<Unit> enemyWaiting){
        if (arenaOpponent.health > 0){
            arenaOpponent.health -= attack;
            actionString = "Archer damaged arena opponent by " + attack;
            if (arenaOpponent.health <= 0){
                arenaOpponent.isDead = true;
                arenaOpponent.health = 0;
                level++;
                maxHealth++;
                attack++;
                System.out.println(arenaOpponent.name + " is dead now.");
                System.out.println(name + " levels up.");
            }
        }
    }

    public void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, 
    ArrayList<Unit> enemyWaiting){
        Random random = new Random();
        while (true){
            int index = random.nextInt(7);
            if (enemyWaiting.get(index).health != 0 && index != enemyWaiting.indexOf(arenaOpponent)){
                enemyWaiting.get(index).health -= attack;
                actionString = "Archer damaged waiting "+ enemyWaiting.get(index).name +" by " + attack;
                if (enemyWaiting.get(index).health <= 0){
                    enemyWaiting.get(index).isDead = true;
                    enemyWaiting.get(index).health = 0;
                    level++;
                maxHealth++;
                attack++;
                System.out.println(enemyWaiting.get(index).name + " is dead now.");
                System.out.println(name + " levels up.");
            }
                break;
            }
        }
        
    }

    public void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, 
    ArrayList<Unit> enemyWaiting){
        
        heal(1);
        actionString = "Archer healed self by 1";
    }
}
