

import java.util.List;

import org.joda.time.DateTime;
/**
 * This class calculates Asian call option price
 * Created by Chao Wei on 2014/12/12.
 */

public class AsianCallOption implements PayOut{
	
    private double K;
	
	public AsianCallOption(double K){//construction
		this.K = K;
	}

	@Override
	public double getPayout(StockPath path) {
		List<Pair<DateTime, Double>> prices = path.getPrices();
		double sum = 0;
		for(Pair<DateTime, Double> price : prices){//calculate average stock price
			
			 sum += (double) price.getValue();
		}
		
		double average = sum/prices.size();

		return Math.max(0, average - K);//compare with strike price
	}
}
