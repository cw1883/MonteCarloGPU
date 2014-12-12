
/**
 * This class is an interface for different kinds of stock path
 * Created by Chao Wei on 2014/12/12.
 */

import java.util.List;
import org.joda.time.DateTime;

public interface StockPath {
	
	public List<Pair<DateTime,Double>> getPrices();
	
}
