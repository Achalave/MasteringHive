package neuralnet;

import java.util.ArrayList;
import neuralnet.activationFunctions.ActivationFunction;
import neuralnet.activationFunctions.RangedActivationFunction;

/**
 *
 * @author Michael
 */
public class Neuron implements NeuralReceptor {

    ArrayList<NeuralLink> linksOut;
    ActivationFunction func;
    float outputValue = 0;

    //w1*i1 + w2*i2 + w3*i3 + ... - offset = output
    public Neuron(ArrayList<NeuralLink> links, ActivationFunction f) {
        linksOut = links;
        func = f;
    }

    public Neuron(ArrayList<NeuralLink> links) {
        linksOut = links;
    }

    public Neuron(ActivationFunction f) {
        func = f;
    }

    /**
     * Pushes the output value through every link associated with this neuron
     * after running the value through the activation function associated with
     * this node. The output value is then reset to 0.
     *
     * @return The value that was pushed through the network.
     */
    public float pushOutput() {
        //Store the output in a temporary variable and send it through the 
        //activation function, if instantiated
        float output;
        if (func != null) {
            output = func.function(outputValue);
        } else {
            output = outputValue;
        }
        outputValue = 0;

        if (linksOut != null) {
            for (NeuralLink link : linksOut) {
                link.pushValue(output);
            }
        }

        return output;
    }

    /**
     * Retrieves the output value after it has been processed by the activation
     * function, which is then reset back to 0.
     *
     * @return The output value of this node
     */
    public float retrieveOutput() {
        //Store the output in a temporary variable and send it through the 
        //activation function, if instantiated
        float output;
        if (func != null) {
            output = func.function(outputValue);
        } else {
            output = outputValue;
        }
        outputValue = 0;

        return output;
    }

    /**
     * Retrieves the output value after it has been processed by the activation
     * function without resetting the output variable.
     *
     * @return The current output value.
     */
    public float sampleOutput() {
        if (func != null) {
            return func.function(outputValue);
        } else {
            return outputValue;
        }

    }

    @Override
    public void incrementOutput(float inc) {
        outputValue += inc;
    }

    public void setLinksOut(ArrayList<NeuralLink> linksOut) {
        this.linksOut = linksOut;
    }

    public void setFunc(ActivationFunction func) {
        this.func = func;
    }

    public ArrayList<NeuralLink> getLinksOut() {
        return linksOut;
    }

    public ActivationFunction getFunc() {
        return func;
    }

    public void addNeuralLink(NeuralLink link) {
        linksOut.add(link);
    }

    public void setOutputRange(float min, float max) throws RangeNotAdjustableException {
        if (!(func instanceof RangedActivationFunction)) {
            throw new RangeNotAdjustableException();
        }
        ((RangedActivationFunction) func).setRange(min, max);
    }

}
