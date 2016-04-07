
package neuralnet.activationFunctions;

/**
 *
 * @author Michael
 */
public class ZeroToOneActivationFunction implements ActivationFunction{
    final double response;
    
    public ZeroToOneActivationFunction(){
        response = 1;
    }
    
    public ZeroToOneActivationFunction(double res){
        response = res;
    }
    
    @Override
    public float function(float input){
        return (float)(1/(1+Math.exp(-input/response)));
    }

    @Override
    public ActivationFunction clone() {
        return new ZeroToOneActivationFunction(response);
    }
}
