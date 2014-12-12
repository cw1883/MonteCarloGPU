
import java.util.Arrays;

/**
 * This class implements antitheic method
 * Created by Chao Wei on 2014/12/12.
 */

public class AntitheticVectorGenerator implements RandomVectorGenerator {
	

		private RandomVectorGenerator rvg;
		double[] lastVector;

		public AntitheticVectorGenerator (RandomVectorGenerator rvg){
			this.rvg = rvg;
		}

		@Override
		public double[] getVector() {//return symmetric vectors
			if ( lastVector == null ){
				lastVector = rvg.getVector();
				return lastVector;
			} else {
				double[] tmp =Arrays.copyOf(lastVector, lastVector.length);
				lastVector = null;
				for (int i = 0; i < tmp.length; ++i){ tmp[i] = -tmp[i];}
				return tmp;
			}
		}
		
	}
