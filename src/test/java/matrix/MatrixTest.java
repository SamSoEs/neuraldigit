package matrix;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

class MatrixTest {
	private Random random = new Random();
	
	@Test
	public void testGetGreatesRowNumbers() {
		double[] values = {2, -6, 7, 7, 2, -6, 11, -1, 1};
		Matrix m = new Matrix(3, 3, i-> values[i]);
		
		Matrix result = m.getGreatestRowNumbers();
		
		double[] expectedValues = {2, 1, 0};
		Matrix expected = new Matrix(1, 3, i-> expectedValues[i]);
		
		assertTrue(expected.equals(result));

	}
	
	
	@Test
	public void testTranspose() {
		Matrix m = new Matrix(2, 3, i->i);
		
		Matrix result = m.transpose();
		
		double[] expectedValues = {0, 3, 1, 4, 2, 5};
		Matrix expected = new Matrix(3, 2, i->expectedValues[i]);
		
		assertTrue(expected.equals(result));
	}
	@Test
	public void testAddIncrement() {
		Matrix m = new Matrix(5, 8, i->random.nextGaussian());
		
		int row = 3;
		int col = 2;
		double inc = 0.0001;
		Matrix result = m.addIncrement(row, col, inc);
		
		double originalValue = m.get(row, col);
		double icrementedValue = result.get(row, col);
		
		assertTrue(Math.abs(icrementedValue - (originalValue + inc)) < 0.0001);
		
		
		
		System.out.println(m);
		System.out.println(result);
		
	}
	@Test
	public void testSoftMax() {
		Matrix m = new Matrix(5, 8, i->random.nextGaussian());
		
		Matrix result = m.softmax();
		
		double[] colSums = new double[8];
		
		result.forEach((row, col, value)->{
			assertTrue(value >= 0 && value <= 1.0);
			colSums[col] += value;
		});
		
		for(var sum: colSums) {
			assertTrue(Math.abs(sum - 1.0) < 0.00001);
		}
	}
	@Test
	public void testSumColumns() {
		Matrix m = new Matrix(4, 5, i->i);
		
		Matrix result = m.sumColumns();
	
		double[] expectedValues = {  +30.00000,   +34.00000,   +38.00000,   +42.00000,   +46.00000};
		Matrix expected = new Matrix(1, 5, i->expectedValues[i]);
		
		assertTrue(expected.equals(result));
	}
	
	@Test
	public void testMultiply() {
		Matrix m1 = new Matrix(2, 3, i->i);
		Matrix m2 = new Matrix(3, 2, i->i);
		
		double[] expectedValues = {10, 13, 28, 40};
		Matrix expected = new Matrix(2, 2, i->expectedValues[i]);

		Matrix result = m1.multiply(m2);
		
		assertTrue(expected.equals(result));
	}
	
	@Test
	public void testMultiplySpeed() {
		
		int rows = 500;
		int cols = 500;
		int mid = 50;
		
		Matrix m1 = new Matrix(rows, mid, i->i);
		Matrix m2 = new Matrix(mid, cols, i->i);
		
		var start = System.currentTimeMillis();
		m1.multiply(m2);
		var end = System.currentTimeMillis();
		
		System.out.printf("Matrix multiplication time taken: %dms\n", end-start);
	}
	
	@Test
	public void testEquals() {
		Matrix m1 = new Matrix(3, 4, i -> 0.5 * (i - 6));
		Matrix m2 = new Matrix(3, 4, i -> 0.5 * (i - 6));
		Matrix m3 = new Matrix(3, 4, i -> 0.5 * (i - 6.2));
		assertTrue(m1.equals(m2));
		assertFalse(m1.equals(m3));
	}
	@Test
	public void testAddMatrices() {
		Matrix m1 = new Matrix(2, 2, i->i);
		Matrix m2 = new Matrix(2, 2, i->i * 1.5);
		Matrix expected = new Matrix(2, 2, i->i * 2.5);
		
		Matrix result = m1.apply((index, value) -> value + m2.get(index));
		assertTrue(result.equals(expected));
		System.out.println(m1);
		System.out.println(m2);
		System.out.println(expected);
		System.out.println(result);
	}
	@Test
	public void testMultiplyDouble() {
		Matrix m = new Matrix(3, 4, i -> 0.5 * (i - 6));	
		
		double x = 0.5;
		Matrix expected = new Matrix(3, 4, i -> x * 0.5 * (i - 6));
		
		Matrix result = m.apply((index, value) -> x * value);
		assertTrue(result.equals(expected));
		assertTrue(Math.abs(result.get(1) + 1.25000) < 0.0001);
	}
	@Test
	public void toStringTest() {
		Matrix m = new Matrix(3, 4, i->i*2);
		String 	text = m.toString();
		System.out.println(text);
		double[] expected = new double[12];
		for(int i=0; i < expected.length; i++) {
			expected[i] = i * 2;
		}
		var rows = text.split("\n");
		
		assertTrue(rows.length == 3);
		int index = 0;
		for(var row : rows) {
			var values = row.split("\\s+");
			for(var textValue: values) {
				if(textValue.length() == 0) {
					continue;
				}
				var doubleValue = Double.valueOf(textValue);
				assertTrue(Math.abs(doubleValue - expected[index]) < 0.0001);
				index++;
			}
		}
		
	}

}
