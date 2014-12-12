

import org.joda.time.DateTime;

/**
 * This class is the main class
 * Created by Chao Wei on 2014/12/12.
 */

public class MonteCarlo {
	
	public static void main(String[] args){
		
		int N = 1000;//define the size of stock price list
		
		DateTime startDate = new DateTime();//current time
		DateTime endDate = startDate.plusDays(252);//ending time

		UniformRandomVectorGenerator urvg = new UniformRandomVectorGenerator(N);
		NormalRandomGeneratorGPU nrvg = new NormalRandomGeneratorGPU(urvg.getVector());//create normal random vector

		AntitheticVectorGenerator avg = new AntitheticVectorGenerator(nrvg);//create antithetic normal random vector
		GBMStockPath GBM_sp = new GBMStockPath(0.0001, N, 0.01, 152.35, startDate, endDate, avg);//create stock path
		
		EuropeanCallOption ecp = new EuropeanCallOption(165);//create an European call option
		
		AsianCallOption acp = new AsianCallOption(164); //create an Asian call option
		
		SimulationManager sm = new SimulationManager(GBM_sp, acp);//create simulation manager
		double price = sm.getPrice();//get the option price at expiration
		price = price * Math.exp(-0.0001*252);//discount the price to current day
		System.out.println("The option price is " + price);//print the price
		
	}

}
