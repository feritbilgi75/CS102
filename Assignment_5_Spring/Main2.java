public class Main2 {

    char[][] array;
    int countStart = 0;
    int countEnd = 0;

    public void createArray(int numRows, int numColumns) {
        array = new char[numRows][numColumns];
        this.countEnd = numRows;  // Set after initializing the array
    }

    public void fillChar(char c, int rowStart, int rowEnd, int columnStart, int columnEnd) {
        if (rowStart > rowEnd || columnStart > columnEnd) {
            return;
        }
        fillRow(c, rowStart, columnStart, columnEnd);
        fillChar(c, rowStart + 1, rowEnd, columnStart, columnEnd);
    }

    private void fillRow(char c, int row, int columnStart, int columnEnd) {
        if (columnStart > columnEnd) {
            return;
        }
        array[row][columnStart] = c;
        fillRow(c, row, columnStart + 1, columnEnd);
    }
    int pattern1Count = 0;
    public void pattern1() {
        if ((array.length-pattern1Count <= array.length/2 || array.length-pattern1Count-1 <= array.length/2)) {
            if(array.length < array[0].length && (array[0].length % 2 == 1)){
                if(array.length % 2 == 0){
                    fillChar('*', array.length/2, array.length/2, 0 + pattern1Count, array[0].length - pattern1Count-1);

                }
                else{
                    fillChar('*', (int)array.length/2 + 1, (int)array.length/2 + 1, 0 + pattern1Count, array[0].length - pattern1Count-1);

                }
            }
            if(array.length > array[0].length && (array.length % 2 == 1)){
                if (array[0].length % 2 == 0){
                    fillChar('*', 0 + pattern1Count, array.length-pattern1Count-1, array[0].length/2, array[0].length/2);

                }
                else{
                    fillChar('*', 0 + pattern1Count, array.length-pattern1Count-1, (int)array[0].length/2 + 1, (int)array[0].length/2 + 1);

                }
            }
            return;
        }
        fillChar('*', 0 + pattern1Count, array.length-pattern1Count-1, 0 + pattern1Count, array[0].length - pattern1Count-1);
        fillChar('#', 1 + pattern1Count, array.length-pattern1Count-2, 1 + pattern1Count, array[0].length - pattern1Count - 2);
        pattern1Count+=2;  // decrement to change the range of filling in the next call
        pattern1();
    }

    public void pattern2(int fillWidth, int shiftAmount) {
        recursiveFillPattern2(0, 0, fillWidth, shiftAmount);  // Start from the first row and column 0
    }
    
    private void recursiveFillPattern2(int row, int startColumn, int fillWidth, int shiftAmount) {
        if (row >= array.length) {
            return; // Base case: All rows are processed
        }
    
        // Reset the entire row to '#' before filling with '*'
        fillRow('#', row, 0, array[row].length - 1);
    
        // Now fill the row starting from `startColumn` with '*'
        if(startColumn + fillWidth - 1 <= array[0].length-1){
            fillRow('*', row, startColumn, startColumn + fillWidth - 1);

        }
        else{
            fillRow('*', row, startColumn, array[0].length-1);
            row++;
            int difference = fillWidth - (array[0].length-1 - startColumn + 2); //I do not know how I solved that calculation. In start I did fillWidth - (array[0].length-1 - startColumn) calculation and I found the true one with trial.  
            fillRow('*', row, 0,difference);
            fillRow('#', row, difference+1,array[0].length-1);
            row++;
        }
    
        // Calculate the start column for the next row with wrapping
        int nextStartColumn = (startColumn + shiftAmount) % array[row].length;
    
        // Recursive call for the next row
        recursiveFillPattern2(row + 1, nextStartColumn, fillWidth, shiftAmount);
    }

    public void pattern3(){
        if (array == null || array.length == 0 || array[0].length == 0){
            return;
        } 
        recursiveFillPattern3(0,0);
    }
    private void recursiveFillPattern3(int row, int startColumn){
        if (row >= array.length || startColumn >= array[0].length) {
            return;
        }

        // Fill the first row and the first column with '1'
        if (row == 0 || startColumn == 0) {
            array[row][startColumn] = '1';
        } else {
            // Calculate the value of the current cell based on the values of the upper and left cells
            int up = array[row - 1][startColumn] - '0'; // Convert char to integer
            int left = array[row][startColumn - 1] - '0'; // Convert char to integer
            int value = (up + left) % 10; // Sum modulo 10
            array[row][startColumn] = (char) ('0' + value); // Convert integer back to char
        }

        // Recursively fill the next cell in the row
        recursiveFillPattern3(row, startColumn + 1);

        // After finishing a row, start filling the next row from the beginning
        if (startColumn == 0) {
            recursiveFillPattern3(row + 1, 0);
        }
    }

    public int findMaxRowSum() {
        return findMaxRowSumHelper(0, Integer.MIN_VALUE);
    }

    // Helper method to recursively find the maximum row sum
    private int findMaxRowSumHelper(int row, int maxSum) {
        if (row >= array.length) {
            return maxSum; // Return the maximum found so far
        }

        int currentRowSum = findRowSum(row, 0); // Calculate sum of the current row
        if (currentRowSum > maxSum) {
            maxSum = currentRowSum; // Update maxSum if current row's sum is greater
        }
        return findMaxRowSumHelper(row + 1, maxSum); // Recursively process the next row
    }

    // Recursive method to calculate the sum of a specific row
    private int findRowSum(int row, int col) {
        if (col >= array[row].length) {
            return 0; // Base case: when col exceeds the row length
        }
        int currentValue = array[row][col] - '0'; // Convert the char value to an integer
        return currentValue + findRowSum(row, col + 1); // Recursive call to sum the rest of the row
    }

    // Recursive print method for the 2D array
    public static void print(char[][] arr) {
        recursivePrint(arr, 0, 0);
    }

    // Helper recursive method to print the array
    private static void recursivePrint(char[][] arr, int row, int col) {
        if (row >= arr.length) {
            return; // Base case for rows: if row index is out of bounds, stop recursion
        }
        
        if (col >= arr[row].length) {
            System.out.println(); // Print a newline when the end of a row is reached
            recursivePrint(arr, row + 1, 0); // Recurse with the next row
            return;
        }

        // Print the current element followed by a space
        System.out.print(arr[row][col] + " ");
        recursivePrint(arr, row, col + 1); // Move to the next column in the same row
    }

    public static void main(String[] args) {
        Main2 m = new Main2();
        m.createArray(5, 5);
        //m.fillChar('x', 1, 3, 1, 4);
        //print(m.array);
        //Until there, it works.
        

        //m.pattern1();
        //print(m.array);
        

        m.pattern2(2, 2);

        //Pattern3() also works
        //m.pattern3();
        //System.out.println(m.findMaxRowSum());
        print(m.array);
    }
}
