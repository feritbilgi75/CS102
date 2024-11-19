public class Grade {
    String examName;
    float weight;
    float points;


    public String toString(){
        return "Exam Name: " + examName + "\n"
        + "Weight: " + weight + "\n"
        + "Grade: " + points + "\n";
    }

    public Grade(String examName, float weight, float points){
        this.examName = examName;
        this.weight = weight;
        this.points = points;
    }

    public Object getExamName() {
        return examName;
    }

    public Object getWeight() {
        return getWeight();
    }

    public Object getPoints() {
        return points;
    }

    public void setWeight(float weight2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setWeight'");
    }

    public void setPoints(float points2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPoints'");
    }
}
