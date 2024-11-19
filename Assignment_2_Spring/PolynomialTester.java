public class PolynomialTester {
    public static void main(String[] args) {
        
        
        double[] dizi = {0,0,-2,0,9,0};

        Polynomial polinom = new Polynomial(dizi);

        System.out.println(polinom.toString(dizi));

        System.out.println(polinom.eval(2));
        System.out.println(polinom.eval2(2));

        System.out.println("Degree: " + polinom.getDegree());
        System.out.println("Coef: " + polinom.getCoef(3));

        Polynomial pol2 = new Polynomial(2,3);
        System.out.println(pol2.eval(2));
        
    }
}
