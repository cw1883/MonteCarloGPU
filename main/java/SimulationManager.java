
/**
 * This class manages the simulation process
 * Created by Chao Wei on 2014/12/12.
 */

public class SimulationManager {
	
	private StockPath sp;
	private PayOut po;
	
	public SimulationManager(StockPath sp, PayOut po){// construction with input stock path and payout 
		this.sp = sp;
		this.po = po;
	}
	
	public double getPrice(){
		int count = 0;//simulation counts
		
		StatsCollector sc = new StatsCollector();
		
		double payout = 0;
		
			do{
			
				payout = po.getPayout(sp);
				sc.addStats(payout);
				count++;
				double sigma_squared = sc.getSigmaSquared();
				//System.out.println(payout);
				//System.out.println(sigma_squared);
				//System.out.println(9*sigma_squared/0.0001);
				//System.out.println(count);
				//System.out.println("-------------------------------------------");
				count++;
				if(sigma_squared !=0 && (double)3.0625*sigma_squared/0.0001 < count) break;//simulation stops with probability of 96% the estimation error is less than 1%
			
			
			}while(true);	
		
		//System.out.println(sc.getMean());
		//System.out.println(count);
		return sc.mean;
		
	}

}
