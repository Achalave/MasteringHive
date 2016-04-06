
package neuralnet.activationFunctions;

/**
 *
 * @author Michael
 */
public interface ActivationFunction {
    public float function(float input);
    public ActivationFunction clone();
}
