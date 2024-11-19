import java.util.ArrayList;
import java.util.Random;

public class Bard extends Unit {
    public Bard(){
        name = "Bard";
        level = 1;
        health = level;
        attack = level;
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
            actionString = "Bard damaged arena opponent by " + attack;
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
        heal(level);
        actionString = (name + " healed self by " + level);
    }

    public void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, 
    ArrayList<Unit> enemyWaiting){
        Random random = new Random();
        while (true){
            int index = random.nextInt(7);
            if (allyWaiting.get(index) != this){
                allyWaiting.get(index).level++;
                allyWaiting.get(index).attack++;
                allyWaiting.get(index).maxHealth++;
                actionString = "Bard leveled up waiting " + allyWaiting.get(index).name;
                

                break;
            }
        }
    }
}
