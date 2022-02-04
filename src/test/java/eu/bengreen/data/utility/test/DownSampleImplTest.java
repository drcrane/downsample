package eu.bengreen.data.utility.test;

import eu.bengreen.data.utility.DownSampleImpl;

/**
 * Creates a simple data set that is generally regular but throws in some exceptions to test the algorithm.
 * Running the example DownSampleDemo will show the data using JFreeChart.
 * @author Benjamin Green
 *
 */
public class DownSampleImplTest {
	static Number[][] raw;
	static Number[][] downsampled;
	
	static {
		raw = createRawData();
		downsampled = performDownSampling();
	}
	
	public static Number[][] createRawData() {
		Number[][] data = new Number[8000][];
		for (int i = 0; i < data.length; i ++) {
			Number[] sample = new Number[2];
			sample[0] = ((long)i + 1000000L);
			sample[1] = (Math.sin((double)i / 300)) * 300;
			data[i] = sample;
		}
		data[430][1] = 50.0D;
		data[1500][1] = 0.0D;
		data[2000][1] = 50.0D;
		raw = data;
		return raw;
	}
	
	public static Number[][] performDownSampling() {
		Number[][] data = DownSampleImpl.largestTriangleThreeBucketsTime(raw, 200);
		downsampled = data;
		return downsampled;
	}
	
	public static Number[][] getRaw() {
		return raw;
	}
	
	public static Number[][] getDownSampled() {
		return downsampled;
	}
}
