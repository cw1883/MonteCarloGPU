

import java.util.LinkedList;
import java.util.List;

import org.joda.time.DateTime;
/**
 * This class return a geometric brownian stock path
 * Created by Chao Wei on 2014/12/12.
 */

public class GBMStockPath implements StockPath{
	
	private double rate;
	private double sigma;
	private double S0;
	private int N;
	private DateTime startDate;
	private DateTime endDate;
	private RandomVectorGenerator rvg;
	
	//construction
	public GBMStockPath(double rate, int N,
			double sigma, double S0,
			DateTime startDate, DateTime endDate,
			RandomVectorGenerator rvg){
		this.startDate = startDate;
		this.endDate = endDate;
		this.rate = rate;
		this.S0 = S0;
		this.sigma = sigma;
		this.N = N;
		this.rvg = rvg;
	}

	@Override
	public List<Pair<DateTime, Double>> getPrices() {
		double[] n = rvg.getVector();
		DateTime current = new DateTime(startDate.getMillis());
		long delta = (endDate.getMillis() - startDate.getMillis())/N;
		double delta_day = (double) delta/86400000;//measure delta by days
		double delta_root_day = Math.sqrt(delta_day);
		List<Pair<DateTime, Double>> path = new LinkedList<Pair<DateTime,Double>>();//a list of stock prices
		path.add(new Pair<DateTime, Double>(current, S0));
		for ( int i= 0; i < N; i++){// geometric Brownian motion stock prices
			current = current.plusMillis((int) delta);
			path.add(new Pair<DateTime, Double>(current, 
					path.get(path.size()-1).getValue()*Math.exp((rate-sigma*sigma/2)*delta_day +sigma * n[i]*delta_root_day)));
		}
		return path;
	}
}
