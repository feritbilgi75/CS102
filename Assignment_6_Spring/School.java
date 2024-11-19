import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;



public class School {
    int realLength;
    Student[] students;
    
    public School(){
        students = new Student[10];
        realLength = 0;
        
    }


    public Student getStudent(String schoolID) {
        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i].getID());
            if (this.students[i].getID().equals(schoolID)) {
                return this.students[i];
            }
        }
        throw new IllegalArgumentException("No such student with the id " + schoolID + "!");
    }

    public void printStudents() {
        for (int i = 0; i < realLength; i++) {
            System.out.println(students[i].toString());
        }
    }




 
    public void checkArraySize(){
        if (realLength >= students.length){
            Student[] students2 = new Student[students.length*2];
            System.arraycopy(students, 0, students2, 0, students.length);

            
            students = students2;
        }
    }
  

     
     public void addStudent(String schoolID, String name, String surname, int age) {
        // First, create the student object
        Student student = new Student(schoolID, name, surname, age);
    
        // Handle the case when no students are in the array yet
        if (this.students[0] == null) {
            this.students[0] = student;
            return;
        }
    
        int realLength = 0; // To keep track of the number of students currently in the array
    
        // Determine the actual number of students (realLength)
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                realLength = i;
                break;
            }
        }
    
        // If the array is full, increase its size
        if (realLength == students.length) {
            checkArraySize(); // Method to double the size of the array and copy old elements
        }
    
        // Check for duplicate IDs and find the insertion point
        for (int i = 0; i < realLength; i++) {
            if (students[i].getID().equals(schoolID)) {
                throw new IllegalArgumentException("Duplicate ID: " + schoolID);
            }
            if (Long.parseLong(schoolID) < Long.parseLong(students[i].getID())) {
                // Shift elements to the right to make room for the new student
                for (int j = realLength; j > i; j--) {
                    students[j] = students[j - 1];
                }
                // Insert the new student at the correct position
                students[i] = student;
                return; // Exit after placing the student
            }
        }
    
        // If the new student has the highest ID, add them at the end of the filled part of the array
        students[realLength] = student;
    }
    


    public void printStudentsByNameOrder() {
        if (realLength == 0) {
            System.out.println("No students to display.");
            return;
        }
    
        // Creating a copy of the students array to sort
        Student[] studentsByName = new Student[realLength];
        System.arraycopy(students, 0, studentsByName, 0, realLength);
    
        // QuickSort algorithm implementation to sort students by name
        quickSortByName(studentsByName, 0, realLength - 1);
    
        // Printing sorted students
        for (Student student : studentsByName) {
            System.out.println(student.toString());
        }
    }
    
    private void quickSortByName(Student[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSortByName(array, low, pivotIndex - 1);
            quickSortByName(array, pivotIndex + 1, high);
        }
    }
    
    private int partition(Student[] array, int low, int high) {
        Student pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            // Compare each student's name and surname
            if (array[j].name().compareTo(pivot.name()) < 0 ||
                (array[j].name().equals(pivot.name()) && array[j].surname().compareTo(pivot.surname()) < 0)) {
                i++;
                // Swapping students
                Student temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
    
        // Swap the pivot element to its correct position
        Student temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }
    

    public float getGradeAverage(Student s){
        
        float allWeights = 0;
        float allPoints = 0;
    
        for(int i = 0; i < s.getGrades().length; i++){
            allPoints += (s.getGrades()[i].weight * s.getGrades()[i].points); 
            allWeights += s.getGrades()[i].weight;
        }
        return allPoints/allWeights;
    }


    public void printStudentGradeAverages() {
        // Assume that students is an array containing all the students
        Student[] sortedStudents = Arrays.copyOf(students, realLength);
        Arrays.sort(sortedStudents, (a, b) -> {
            double avgA = getGradeAverage(a);
            double avgB = getGradeAverage(b);
            if (avgA == avgB) {
                return a.getSchoolID().compareTo(b.getSchoolID());
            }
            return Double.compare(avgB, avgA);
        });
    
        for (Student student : sortedStudents) {
            if (student.getGrades() != null && student.getGrades().length > 0) {
                double average = getGradeAverage(student);
                System.out.printf("%s - Average: %.1f\n", student.toString(), average);
            }
        }
    }
    

    public void printGradesOf(String schoolID) {
        try {
            Student student = getStudent(schoolID);
            System.out.printf("Student: %s\nGrades:\n", student.toString());
            for (Grade grade : student.getGrades()) {
                System.out.printf(" %s (Weight: %.1f) %.1f\n", grade.getExamName(), grade.getWeight(), grade.getPoints());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    


    public void processTextFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    if (line.startsWith("Student:")) {
                        String[] parts = line.split(":")[1].trim().split(",");

                        String nameSurname = parts[0].trim();
                        
                        
                        System.out.println(parts[0].trim());
                        System.out.println(parts[1].trim());
                        
                        String ageString = parts[1].trim();
                        int age = Integer.parseInt(ageString); // This should correctly parse age as an integer
                        
                        String schoolID = (String)parts[2].trim();
                        System.out.println(parts[2].trim());
                        String[] nameSplit = nameSurname.split(" ");
                        String name = nameSplit[0].trim();
                        String surname = nameSplit[1].trim();

                        addStudent(schoolID, name, surname, age);

                    } else if (line.startsWith("Grade:")) {
                        for (int i = 0; i < students.length; i++){
                            System.out.println(students[i].toString());
                        }
                        String[] parts = line.split(":")[1].trim().split(",");
                        String schoolID = parts[0].trim();
                        String examName = parts[1].trim();
                        float weight = Float.parseFloat(parts[2].trim()); // This should correctly parse weight as a float
                        float grade = Float.parseFloat(parts[3].trim()); // This should correctly parse grade as a float
                        Student student = getStudent(schoolID);
                        student.setGrade(examName, weight, grade);
                    } else {
                        // Handle other commands
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error processing line: " + line);
                    System.out.println("Invalid number format: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Error processing line: " + line);
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
        }
    }
    
    










}
