import java.util.ArrayList;

public class Wizard extends Unit {
    public Wizard(){
        name = "Wizard";
        level = 1;
        health = level + 2;
        attack = 1;
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
        actionString = (name + " does nothing.");
    }

    public void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, 
    ArrayList<Unit> enemyWaiting){
        for (int i = 0; i < enemyWaiting.size(); i++){
            if (enemyWaiting.get(i) != arenaOpponent){
                enemyWaiting.get(i).health -= attack;
                if (enemyWaiting.get(i).health <= 0){
                    enemyWaiting.get(i).isDead = true;
                    enemyWaiting.get(i).health = 0;
                    level++;
                maxHealth++;
                attack++;
                
                    System.out.println(enemyWaiting.get(i).name + " is dead now.");
                    System.out.println(name + " levels up.");
                }
            }
        }
    }
}
