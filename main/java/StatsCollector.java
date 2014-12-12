
/**
 * This class calculates needed statistics for simulation
 * Created by Chao Wei on 2014/12/12.
 */

public class StatsCollector {
	
	double sum;
	double mean;
	double sum_of_square;
	double sigma_squared;
	int N;
	
	public double getMean(){
		return mean;
	}
	
	public void addStats(double x){//add a double and recalculate mean and variance
		N++;
		sum = sum + x;
		mean = sum/N;
		sum_of_square = sum_of_square + x*x;
		sigma_squared = sum_of_square/N - mean * mean;
	}
	
	public double getSigmaSquared(){
		return sigma_squared;
	}

}
