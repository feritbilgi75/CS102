package Paket;

public class Game {
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
    
        // Assuming a team size of 5 for demonstration
        simulation.initialize(5);
        simulation.printTeams();
        System.out.println("");
        double speedSumRed = 0;
        for (int i = 0; i < simulation.redTeam.size(); i++){
            speedSumRed += simulation.redTeam.get(i).speed;
        }
        System.out.println("Speed sum of Red: " + speedSumRed);

        double speedSumBlue = 0;
        for (int i = 0; i < simulation.blueTeam.size(); i++){
            speedSumBlue += simulation.blueTeam.get(i).speed;
        }
        System.out.println("Speed sum of Blue: " + speedSumBlue);

        if (speedSumBlue > speedSumRed){
            System.out.println("Blue starts first.");
            System.out.println();
        }
        else {
            System.out.println("Red starts first.");
            System.out.println();
        }
        simulation.simulate(); 
        
        
    }
    
}
