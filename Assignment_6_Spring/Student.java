/**
 * Student
 */
public class Student {

    private String name;
    private String surname;
    private String schoolID;
    private int age;
    

    Grade[] grades = new Grade[1];

    public String toString(){
        return schoolID + ", " + name + " " + surname;
    }


    
    
    */
    public Student(String schoolID, String name, String surname, int age){
        this.schoolID = schoolID;
        this.name = name;
        this.surname = surname;
        this.age = age;
        
    }



public Grade[] addGrade(Grade grade) {
    // Create a new array with one additional slot
    Grade[] newGrades = new Grade[grades.length + 1];

    // Copy existing grades into the new array
    for (int i = 0; i < grades.length; i++) {
        newGrades[i] = grades[i];
    }

    // Add the new grade at the last position, which is the original length of grades
    newGrades[grades.length] = grade;

    // Assign the new array back to the grades field
    grades = newGrades;

    return grades;
}


    public String getID(){
        return schoolID;
    }

    public String name(){
        return name;
    }

    public String surname(){
        return surname;
    }

    public int getAge(){
        return age;
    }


    

    public void setGrade(String examName, float weight, float points) {
        // Validate the exam name length
        if (examName == null || examName.length() <= 3) {
            throw new IllegalArgumentException("Exam name must be longer than 3 characters");
        }
    
        // Search for an existing grade with the same exam name
        boolean isFound = false;
        for (int i = 0; i < grades.length; i++) {
            if (grades[i] != null && grades[i].getExamName().equals(examName)) {
                // Update the existing grade
                grades[i].setWeight(weight);
                grades[i].setPoints(points);
                isFound = true;
                break;
            }
        }
    
        // If no existing grade is found, add a new grade
        if (!isFound) {
            addGrade(new Grade(examName, weight, points));
            System.out.println("New grade has been declared. Exam Name: " + examName);
        }
    }
    

    public Long getSchoolID() {
        return Long.parseLong(schoolID);
    }

    public Grade[] getGrades() {
        return grades;
    }




}