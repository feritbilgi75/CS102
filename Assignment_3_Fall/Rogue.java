import java.util.ArrayList;
import java.util.Random;

public class Rogue extends Unit {
    public Rogue(){
        name = "Rogue";
        level = 1;
        health = level;
        attack = level + 2;
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
            actionString = "Rogue damaged arena opponent by " + attack;
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
        if (arenaOpponent.health > 0){
            arenaOpponent.health -= attack;
            actionString = "Rogue damaged arena opponent by " + attack;
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

    public void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, 
    ArrayList<Unit> enemyWaiting){
        Random random = new Random();
        while (true){
            int index = random.nextInt(7);
            if (enemyWaiting.get(index).health != 0 && index != enemyWaiting.indexOf(arenaOpponent)){
                enemyWaiting.get(index).health -= attack;
                if (arenaOpponent.health == 0){
                    arenaOpponent.isDead = true;
                    level++;
                maxHealth++;
                attack++;
                    System.out.println(arenaOpponent.name + " is dead now.");
                    System.out.println(name + " levels up.");
                }
                break;
            }
        }
    }
}
