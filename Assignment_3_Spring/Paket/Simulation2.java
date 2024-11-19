package Paket;
import java.util.ArrayList;
import java.util.Random;

/**
 * Simulation
 */
public class Simulation2 {

    ArrayList<Robot> redTeam = new ArrayList<Robot>();
    ArrayList<Robot> blueTeam = new ArrayList<Robot>();

    Random random = new Random();


    public void initialize(int teamSize){

        
    }

    public Robot getRandomTarget(boolean isRedTeam){
        
        if (isRedTeam == true){
            int index = random.nextInt(redTeam.size());
            return redTeam.get(index);
        }
        else{
            int index = random.nextInt(blueTeam.size());
            return redTeam.get(index);
        }
    }

    public Robot getHighestHealth(boolean isRedTeam){
        
        if (isRedTeam == true){
            int index = 0;
            for (int i = 0; i < redTeam.size(); i++){
                if (redTeam.get(i).health > redTeam.get(index).health){
                    index = i;
                }
            }
            return redTeam.get(index);
        }
        else{
            int index = 0;
            for (int i = 0; i < blueTeam.size(); i++){
                if (blueTeam.get(i).health > blueTeam.get(index).health){
                    index = i;
                }
            }
            return blueTeam.get(index);
        }
    }

    public Robot getLowestHealth(boolean isRedTeam){
        
        if (isRedTeam == true){
            int index = 0;
            for (int i = 0; i < redTeam.size(); i++){
                if (redTeam.get(i).health < redTeam.get(index).health){
                    index = i;
                }
            }
            return redTeam.get(index);
        }
        else{
            int index = 0;
            for (int i = 0; i < blueTeam.size(); i++){
                if (blueTeam.get(i).health < blueTeam.get(index).health){
                    index = i;
                }
            }
            return blueTeam.get(index);
        }
    }

    public Robot getLowestSpeed(boolean isRedTeam){
        
        if (isRedTeam == true){
            int index = 0;
            for (int i = 0; i < redTeam.size(); i++){
                if (redTeam.get(i).speed < redTeam.get(index).speed){
                    index = i;
                }
            }
            return redTeam.get(index);
        }
        else{
            int index = 0;
            for (int i = 0; i < blueTeam.size(); i++){
                if (blueTeam.get(i).speed < blueTeam.get(index).speed){
                    index = i;
                }
            }
            return blueTeam.get(index);
        }
    }

    public Robot getLowestAttack(boolean isRedTeam){
        
        if (isRedTeam == true){
            int index = 0;
            for (int i = 0; i < redTeam.size(); i++){
                if (redTeam.get(i).attack < redTeam.get(index).attack){
                    index = i;
                }
            }
            return redTeam.get(index);
        }
        else{
            int index = 0;
            for (int i = 0; i < blueTeam.size(); i++){
                if (blueTeam.get(i).attack < blueTeam.get(index).attack){
                    index = i;
                }
            }
            return blueTeam.get(index);
        }
    }

    public Robot[] getLowestHealth3(boolean isRedTeam){
        
        Robot[] lowestOnes = new Robot[3];
        ArrayList<Robot> findingArrayList = new ArrayList<Robot>();
        int counter = 0;
        while (counter < 3){
        if (isRedTeam == true){
            int index = 0;
            findingArrayList = redTeam;
            for (int i = 0; i < redTeam.size(); i++){
                if (findingArrayList.get(i).health < findingArrayList.get(index).health){
                    index = i;
                }
            }
            lowestOnes[counter] = findingArrayList.get(index);
            findingArrayList.remove(index);
            
        }
        else{
            int index = 0;
            for (int i = 0; i < blueTeam.size(); i++){
                if (blueTeam.get(i).health < blueTeam.get(index).health){
                    index = i;
                }
            }
            lowestOnes[counter] = findingArrayList.get(index);
            findingArrayList.remove(index);
        }
    }
    return lowestOnes;
    }



}