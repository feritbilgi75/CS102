import java.util.ArrayList;
import java.util.Random;

/**
 * Unit
 */
public abstract class Unit {

    public String name;
    public int health;
    public int level;
    public boolean isDead;
    public int maxHealth;
    public int attack;
    public String actionString = "";

    public void damage(int damageAmount){
        health -= damageAmount;
    }

    public void increaseLevel(){
        if (isDead != true){
            level++;
            maxHealth++;
            attack++;
            //return that method to rewrite
            //You may write it with polymorpishm.
        }
    }

    public void decreaseLevel(){
        if (level > 1){
            level--;
            maxHealth--;
            attack--;
        }
    }

    public void revive(){
        if (isDead){
            isDead = false;
            decreaseLevel();           
        }
    }

    public void heal(int healAmount){
        if (!isDead){
            if (healAmount >= maxHealth - health){
                health = maxHealth;
            }
            else {
                health += healAmount;
            }
        }
    }

    public String getInfo(){
        String info = name + ", LVL: "+ level + ", ATK: "+ attack +", HEALTH: " + health + "/" + maxHealth;
        return info;
    }

    public void firstPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, 
    ArrayList<Unit> enemyWaiting){
        
    }

    public void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, 
    ArrayList<Unit> enemyWaiting){
        
    }

    public void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, 
    ArrayList<Unit> enemyWaiting){
        
    }


}