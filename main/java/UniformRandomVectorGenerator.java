import java.util.Random;

/**
 * This class returns a uniform random vector for normal random vector generation
 * Created by Chao Wei on 2014/12/12.
 */
public class UniformRandomVectorGenerator implements RandomVectorGenerator{


private int N;

        public UniformRandomVectorGenerator(int N){//construction with size N
            this.N = N;
        }

        @Override
        public double[] getVector() {//return a Gaussian random vector
            Random rnd = new Random();
            double[] vector = new double[N];
            for ( int i = 0; i < vector.length; ++i){
                vector[i] = rnd.nextDouble();
            }
            return vector;
        }
}
