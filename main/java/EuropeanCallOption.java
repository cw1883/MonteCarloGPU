

import java.util.List;

import org.joda.time.DateTime;

/**
 * This class calculates European call option price
 * Created by Chao Wei on 2014/12/12.
 */

public class EuropeanCallOption implements PayOut{
	
   private double K;
	
	public EuropeanCallOption(double K){//construction
		this.K = K;
	}

	@Override
	public double getPayout(StockPath path) {//compare the last stock price and strike price
		List<Pair<DateTime, Double>> prices = path.getPrices();
		return Math.max(0, prices.get(prices.size()-1).getValue() - K);
	}
}
