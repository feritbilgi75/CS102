import java.util.ArrayList;

public class Warrior extends Unit {
    public Warrior(){
        name = "Warrior";
        level = 1;
        health = level + 2;
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
        actionString = (name + " does nothing.");
    }

    public void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, 
    ArrayList<Unit> enemyWaiting){
        if (arenaOpponent.health > 0){
            arenaOpponent.health -= attack;
            actionString = "Warrior damaged arena opponent by " + attack;
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
        if (arenaOpponent.health > 0){
            arenaOpponent.health -= attack;
            actionString = "Warrior damaged arena opponent by " + attack;
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
}
