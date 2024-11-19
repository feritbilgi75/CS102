package Paket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Simulation {
    ArrayList<Robot> redTeam = new ArrayList<>();
    ArrayList<Robot> blueTeam = new ArrayList<>();
    Random random = new Random();
    int counter = 1;

    public void initialize(int teamSize) {
        System.out.println("Enters.");
        for (int i = 0; i < teamSize; i++) {
            
            // For red team
            Robot redRobot = createRandomRobot(true);
            redRobot.prodNumber = redRobot.codeString + Integer.toString(counter);
            counter++;
            redTeam.add(redRobot);
            

            // For blue teams
            Robot blueRobot = createRandomRobot(false);
            blueRobot.prodNumber = blueRobot.codeString + Integer.toString(counter);
            counter++;
            blueTeam.add(blueRobot);
        }
        

        // Sort the teams by speed
        Comparator<Robot> bySpeed = Comparator.comparingDouble(r -> r.speed);
        Collections.sort(redTeam, bySpeed.reversed()); // Higher speed first
        Collections.sort(blueTeam, bySpeed.reversed()); // Higher speed first
    }

    private Robot createRandomRobot(boolean isRedTeam) {
        // Randomly selects a robot type to create
        int type = random.nextInt(6); // Assuming 6 types: 0 to 5
        switch (type) {
            case 0: return new SimpleBot(isRedTeam);
            case 1: return new PredatorBot(isRedTeam);
            case 2: return new DefenceBot(isRedTeam);
            case 3: return new SpeedBot(isRedTeam);
            case 4: return new SpreadBot(isRedTeam);
            case 5: return new OneBot(isRedTeam);
            default: return new SimpleBot(isRedTeam); // Default case, should not happen
        }
    }

    public void simulate() {
        boolean isRedTurn = calculateInitialTurn();
        int attackCountRed = 0;
        int attackCountBlue = 0;
        
        while (!redTeam.isEmpty() && !blueTeam.isEmpty()) {
            if (isRedTurn) {
                
                
                if (attackCountRed < redTeam.size()){
                    attackTurn(redTeam, blueTeam, attackCountRed);
                    attackCountRed++;
                }
                else{
                    attackCountRed = 0;
                    attackTurn(redTeam, blueTeam, attackCountRed);
                    attackCountRed++;
                }
                
            } else {
                
                if (attackCountBlue < blueTeam.size()){
                    attackTurn(blueTeam, redTeam,attackCountBlue);
                    attackCountBlue++;
                }
                else{
                    attackCountBlue = 0;
                    attackTurn(blueTeam, redTeam, attackCountBlue);
                    attackCountBlue++;
                }
                
            }
            isRedTurn = !isRedTurn; // Alternate turns between teams
        }
        System.out.println("");
        printWinner(); // Assume this method announces the winning team
    }

    private boolean calculateInitialTurn() {
        double redSpeedSum = redTeam.stream().mapToDouble(r -> r.speed).sum();
        double blueSpeedSum = blueTeam.stream().mapToDouble(r -> r.speed).sum();

        // Red team starts first if the speed sums are equal
        return redSpeedSum >= blueSpeedSum;
    }
    
    private void attackTurn(ArrayList<Robot> attackingTeam, ArrayList<Robot> defendingTeam, int attackCount) {
        if (attackingTeam.isEmpty()) return;
        
        
        Robot attacker = attackingTeam.get(attackCount); 
        
        attacker.attack(this); 
    }

    public Robot getRandomTarget(boolean isRedTeam){
        
        if (isRedTeam == true){
            if (redTeam.size() > 0){
                int index = random.nextInt(redTeam.size());
                return redTeam.get(index);
            }
            else{
                return redTeam.get(0);
            }
            
        }
        else{
            if (blueTeam.size() > 0){
                int index = random.nextInt(blueTeam.size());
                return blueTeam.get(index);
            }
            else{
                return blueTeam.get(0);
            }
            
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
            counter++;
            findingArrayList.remove(index);
            
        }
        else{
            int index = 0;
            findingArrayList = blueTeam;

            for (int i = 0; i < blueTeam.size(); i++){
                if (blueTeam.get(i).health < blueTeam.get(index).health){
                    index = i;
                }
            }
            lowestOnes[counter] = findingArrayList.get(index);
            counter++;
            findingArrayList.remove(index);
        }
    }
    return lowestOnes;
    }
    // Helper method to remove a robot from a team
    public void removeRobot(Robot robot, boolean isRedTeam) {
        if (isRedTeam) {
            redTeam.remove(robot);
        } else {
            blueTeam.remove(robot);
        }
    }

    // After initializing teams in the Simulation class
public void printTeams() {
    System.out.println("Red team:");
    for (Robot robot : redTeam) {
        System.out.println(robot);
    }
    System.out.println("\nBlue team:");
    for (Robot robot : blueTeam) {
        System.out.println(robot);
    }
}
    // In the Simulation class, after the simulate method concludes the battle
public void printWinner() {
    String winningTeam = redTeam.isEmpty() ? "Blue" : "Red";
    ArrayList<Robot> winners = redTeam.isEmpty() ? blueTeam : redTeam;

    System.out.println(winningTeam + " team wins! Remaining robots:");
    for (Robot robot : winners) {
        System.out.println(robot);
    }
}



    
    // Getters for the teams (might be used in the Robot attack implementations)
    public ArrayList<Robot> getRedTeam() {
        return redTeam;
    }

    public ArrayList<Robot> getBlueTeam() {
        return blueTeam;
    }

    public Simulation(){
        System.out.println("Constructor works.");
    }
}
