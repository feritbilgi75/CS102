import java.util.ArrayList;
import java.util.Random;

public class Necromancer extends Unit {
    public Necromancer(){
        name = "Necromancer";
        level = 1;
        health = level + 1;
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
            actionString = "Necromancer damaged arena opponent by " + attack;
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
        ArrayList<Unit> allyDead = new ArrayList<Unit>();
        for (int i = 0; i < allyDead.size(); i++){
            if (allyWaiting.get(i).isDead){
                allyDead.add(allyDead.get(i));
            }
            if (allyDead.size() == 0){
                for (int j = 0; j < allyWaiting.size(); j++){
                    allyWaiting.get(j).health--;
                    actionString = "Necromancer damaged waiting units by 1";
                }
            }
            else{
                Random random = new Random();
                int reviveInt = random.nextInt(allyDead.size());
                allyWaiting.get(allyWaiting.indexOf(allyDead.get(reviveInt))).revive();
                actionString = "Necromancer revived " + allyWaiting.get(allyWaiting.indexOf(allyDead.get(reviveInt))).name;
            }
        }
    }

    public void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, 
    ArrayList<Unit> enemyWaiting){
        if (arenaOpponent.level > 1){
            arenaOpponent.level--;
            arenaOpponent.maxHealth--;
            arenaOpponent.attack--;
            
            actionString = "Necromancer damaged arena opponent by " + 1;
            
        }
        
    }
}
