package cave.neuraldigit;

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
	
	static public double and(double x, double x2) {
		return neuron(new double[] {x, x2}, new double[] {1,1}, -1);
	}
	
	static public double or(double x, double x2) {
		return neuron(new double[] {x, x2}, new double[] {1,1}, 0);
	}
	static public double xor(double x, double x2) {
		return and(or(x, x2), nand(x, x2));
	}
	static public double nor(double x, double x2) {
		return neuron(new double[] {x, x2}, new double[] {-1,-1}, 1);
	}
	
	static public double nand(double x, double x2) {
		return neuron(new double[] {x, x2}, new double[] {-1,-1}, 2);
	}
	
	static public double xnor(double x, double x2) {
		return or(and(x, x2), nor(x, x2));
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
