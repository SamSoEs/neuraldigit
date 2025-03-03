package neuraldigit;


import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;

import matrix.Matrix;

public class Engine implements Serializable {
	private static final long serialVersionUID = 1L;
	private LinkedList<Transform> transforms = new LinkedList<>();
	private LinkedList<Matrix> weights = new LinkedList<>();
	private LinkedList<Matrix> biases = new LinkedList<>();
	
	

	
	Matrix runForwards(Matrix input) {
		Matrix output = input;
		
		int denseIndex = 0;
		for(var t: transforms) {
			if(t == Transform.DENSE) {
				Matrix weight = weights.get(denseIndex);
				Matrix bias = biases.get(denseIndex);
				
				output = weight.multiply(output).modify((row, col, value) -> value + bias.get(col));
				denseIndex++;
			}
			else if(t == Transform.RELU) {
				output = output.modify(value -> value > 0 ? value: 0);
			}
			else if(t == Transform.SOFTMAX) {
				output = output.softmax();
			}
		}
		
		return output;
	}

	public void add(Transform transform, double... params) {
		
		Random random = new Random();
		
		if(transform == Transform.DENSE) {
			int numberNeurons = (int)params[0];
			int weightsPerNeuron = weights.size() == 0 ? (int)params[1]: weights.getLast().getRows();
			
			Matrix weight = new Matrix(numberNeurons, weightsPerNeuron, i->random.nextGaussian());
			Matrix bias = new Matrix(numberNeurons, 1, i->0);
			
			weights.add(weight);
			biases.add(bias);
		}
		transforms.add(transform);
	}
	

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		
		sb.append("\nTransforms:\n");

		int weightIndex = 0;
		for (var t : transforms) {
			
			sb.append(t);
			
			if(t == Transform.DENSE) {
				sb.append(" ").append(weights.get(weightIndex).toString(false));
				
				weightIndex++;
			}
			
			sb.append("\n");
		}

		return sb.toString();
	}
}