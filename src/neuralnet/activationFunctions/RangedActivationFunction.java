
package neuralnet.activationFunctions;

/**
 *
 * @author Michael
 */
public final class RangedActivationFunction implements ActivationFunction{
    float response;
    float stretch;
    float shift;
    
    public RangedActivationFunction(float min, float max, float res){
        response = res;
        setRange(min,max);
    }
    
    public void setRange(float min, float max){
        stretch = (min-max);
        if(stretch == 0){
            stretch = 0.001f;
        }
        shift = min;
    }
    
    @Override
    public float function(float input){
        return (stretch/(1+(float)Math.exp(-input/response)))+shift;
    }

    @Override
    public ActivationFunction clone() {
        return new ZeroToOneActivationFunction(response);
    }
}
