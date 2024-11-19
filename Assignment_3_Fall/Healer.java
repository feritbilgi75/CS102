import java.util.ArrayList;
import java.util.Random;

public class Healer extends Unit {
    public Healer(){
        name = "Healer";
        level = 1;
        health = level + 2;
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
        //Heals
        if (health == maxHealth){
            Random random = new Random();
            ArrayList<Unit> allyDamaged = new ArrayList<Unit>();
            //It will shot characters to list if health is less than maxHealth.
            for (int i = 0; i < allyWaiting.size(); i++){
                if (allyWaiting.get(i).health < allyWaiting.get(i).maxHealth){
                    allyDamaged.add(allyWaiting.get(i));
                }
            }
            if (allyDamaged.size() > 0){
                while (true){
                    int index = random.nextInt(allyDamaged.size());
                    if (allyDamaged.get(index) != this){
                        int oldIndex = allyWaiting.indexOf(allyDamaged.get(index));
                        allyDamaged.get(index).heal(level);
                        allyWaiting.set(oldIndex, allyDamaged.get(index));
                        actionString = "Healer healed " + allyDamaged.get(index).name + " by " + level;
                        break;
                    }
                }
                
         }
         else {
            actionString = "Healer does nothing.";
         }
        
            }
        else{
            heal(level);
            actionString = name + " healed self by " + level;
        }
    }

    public void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, 
    ArrayList<Unit> enemyWaiting){
        if (arenaOpponent.health > 0){
            arenaOpponent.health -= attack;
            actionString = "Healer damaged arena opponent by " + attack;
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
        
        //Revives
        Random random = new Random();
            ArrayList<Unit> allyDead = new ArrayList<Unit>();
            //Canı max healthten küçük olanları listeye atsın ordan seçsin.
            for (int i = 0; i < allyWaiting.size(); i++){
                if (allyWaiting.get(i).health == 0){
                    allyDead.add(allyWaiting.get(i));
                }
            }
            if (allyDead.size() > 0){
                while (true){
                    int index = random.nextInt(allyDead.size());
                    if (allyDead.get(index) != this){
                        int oldIndex = allyWaiting.indexOf(allyDead.get(index));
                        allyDead.get(index).revive();
                        allyWaiting.set(oldIndex, allyDead.get(index));
                        actionString = "";
                        break;
                    }
                }
                
         }


         
        }
    }

