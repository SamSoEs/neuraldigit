package neuraldigit;

/**
 * Hello world!
 */
public class App {
	
	static double neuron(double[] x, double[] w, double b) {
		 double z = 0.0;
		 for(int i = 0; i < x.length; i++) {
	        	z += x[i] * w[i];
	     }
	        
        z += b;    	        
        return z > 0 ? 1.0 : 0.0;
	}
	
	static public double and(double x1, double x2) {
		return neuron(new double[] {x1, x2}, new double[] {1,1}, -1);
	}
	
	static public double or(double x1, double x2) {
		return neuron(new double[] {x1, x2}, new double[] {1,1}, 0);
	}
	static public double xor(double x1, double x2) {
		return and(or(x1, x2), nand(x1, x2));
	}
	static public double nor(double x1, double x2) {
		return neuron(new double[] {x1, x2}, new double[] {-1,-1}, 1);
	}

	static public double nand(double x1, double x2) {
		return neuron(new double[] {x1, x2}, new double[] {-1,-1}, 2);
	}
	
	static public double xnor(double x1, double x2) {
		return or(and(x1, x2), nor(x1, x2));
	}
	
    public static void main(String[] args) {
    	
    	for(int i=0; i < 4; i++) {
    		double x1 = i/2;
    		double x2 = i%2;
    		
    		double output = xnor(x1, x2);
    		
            System.out.printf("%d%d\t%d\n", (int)x1, (int)x2, (int)output);

    	}
        
    }
}
