import com.nativelibs4java.opencl.*;
import org.bridj.Pointer;

import static org.bridj.Pointer.allocateDoubles;

/**
 * This class creates normal random vector using GPU
 * Created by Chao Wei on 2014/12/12.
 */
public class NormalRandomGeneratorGPU implements RandomVectorGenerator{

     private double[] uv;//uniform random vector

     NormalRandomGeneratorGPU(double[] uv){
        this.uv = uv;
    }

    @Override
    public  double[] getVector() {
        final int n = uv.length; // length of vector
        double[] gaussian = new double[n];
        CLPlatform clPlatform = JavaCL.listPlatforms()[0];
        CLDevice device = clPlatform.getBestDevice();

        //System.out.println("*** New device *** ");
        //System.out.println("Vendor: " + device.getVendor());
        //System.out.println("Name: " + device.getName() );
        //System.out.println("Type: " + device.getType() );

        CLContext context = JavaCL.createContext(null, device);
        CLQueue queue = context.createDefaultQueue();

        //Use Box–Muller transform to generate Normal Random Vector
        String src ="#pragma OPENCL EXTENSION cl_khr_fp64: enable \n" +
                "__kernel void normal_generator(__global double* a, __global double* b, int n) \n" +
                "{\n" +
                "    int i = get_global_id(0);\n" +
                "    if (i >= n / 2)\n" +
                "        return;\n" +
                "\n" +
                "    b[2*i] = sqrt(-2*log(a[2*i]))*cos(2*3.1416*a[2*i+1]);\n" +
                "    b[2*i+1] = sqrt(-2*log(a[2*i]))*sin(2*3.1416*a[2*i+1]);\n" +
                "}";
        CLProgram program = context.createProgram(src);
        program.build();

        CLKernel kernel = program.createKernel("normal_generator");

        //initialize pointer
        final Pointer<Double>
                aPtr = allocateDoubles(n);

        //set pointer to the vector
        for (int i = 0; i < n; i++) {
            aPtr.set(i, uv[i]);
        }

        CLBuffer<Double>
                a = context.createDoubleBuffer(CLMem.Usage.Input, aPtr);

        // Create an OpenCL output buffer :
        CLBuffer<Double> b = context.createDoubleBuffer(CLMem.Usage.Output, n);
        kernel.setArgs(a, b, n);
        CLEvent event = kernel.enqueueNDRange(queue, new int[]{n}, new int[]{128});
        final Pointer<Double> bPtr = b.read(queue, event);
        gaussian = bPtr.getDoubles();

        //release the space
        a.release();
        b.release();
        aPtr.release();
        bPtr.release();
        return gaussian;
    }

}
