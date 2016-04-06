
package neuralnet.activationFunctions;

/**
 *
 * @author Michael
 */
public class NegToPosOneActivationFunction implements ActivationFunction {

    /*
    Uses a shifted and stretched sigmoid function
     */

    final double response;

    public NegToPosOneActivationFunction(double res) {
        response = res;
    }

    @Override
    public float function(float input) {
        return (float) (2 / (1 + Math.exp(-input / response))) - 1;
    }

    @Override
    public ActivationFunction clone() {
        return new NegToPosOneActivationFunction(response);
    }

}
