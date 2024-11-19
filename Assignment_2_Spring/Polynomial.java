/**
 * Polynomial
 */
public class Polynomial {

    private double[] array1;

    public Polynomial(int d, double c){
        //İlki derecesi ikincisi katsayı olacak şekilde arraya yükle. 0 polinomu
        
        array1 = new double[d+1];
        for (int i = 0; i < array1.length-1; i++){
            array1[i] = 0;
        }
        array1[array1.length-1] = c;
        
       
    }

    public Polynomial(double[] array){
        array1 = array;
    }
    public double getCoef(int x){
        double degree = 0;
        if (x > array1.length){
            System.out.println("It cannot be found");
        }
        else{
            degree = array1[x];
        }
        return degree;
    }

    public double getDegree(){

        double degree = 0;

        if (array1.length == 2){
            degree = array1[0];
        }

        else{
            for (int i = array1.length-1; i > 0; i--){
                if (array1[i] != 0){
                    degree = i;
                    break;
                }
            }
        }
        

        

        return degree;
    }

    public String toString(double[] array){
        String polynom = "";
        if (array[0] > 0 || array[0] < 0){
            polynom = Double.toString(array[0]);
        }
        

        for (int i = 1; i < array.length; i++){
            if (array[i] > 0){
                if (polynom.equals("")){
                    polynom += Double.toString(array[i]) + "x";
                    if (i > 1){
                        polynom += "^" + Integer.toString(i);
                    }
                }
                else{
                    polynom += " + " + Double.toString(array[i]) + "x";
                    if (i > 1){
                        polynom += "^" + Integer.toString(i);
                    }
                }
                
            }
            else if (array[i] < 0){
                polynom += Double.toString(array[i]) + "x";
                if (i > 1){
                    polynom += "^" + Integer.toString(i);
                }
            }
            else{

            }
        }
        return polynom;
    }
    

    public double eval(double x){
        //It uses Math.pow to calculate pol value with x integer and power that is included in polynom.
        double result = 0;
        for (int i = 0; i < array1.length; i++){
            result += Math.pow(x, i) * array1[i];
        }
        return result;

    }

    

    public double eval2(double x){
        //It evaluates from inner paranthesis to outer.
        double result = (x * array1[array1.length-1] + array1[array1.length-2]);

        if (array1.length > 2){
            for (int i = array1.length-3; i >= 0; i--){
                result = (result * x) + array1[i];
            }
        }

        


        return result;
    }

}