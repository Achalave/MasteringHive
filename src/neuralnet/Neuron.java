
package neuralnet;

import neuralnet.activationFunctions.ActivationFunction;

/**
 *
 * @author Michael
 */
public class Neuron {
    
    NeuralNetworkData data;
    int layerIndex,neuronIndex;
    
    
    //w1*i1 + w2*i2 + w3*i3 + ... - offset = output
    public Neuron(NeuralNetworkData d, int layerIndex, int neuronIndex){
        data = d;
        this.layerIndex = layerIndex;
        this.neuronIndex = neuronIndex;
    }
    
    /**
     * Takes in a number of inputs and return a single float value based off
     * the internal weights for this node.
     * @param inputs The input list.
     * @return The sum of the product of each weight/input pair plus an offset value.
     * @throws IncorrectNumberOfInputsException 
     * Thrown when an incorrect number of inputs is fed to this method.
     */
    public float applyInputs(float[] inputs) throws IncorrectNumberOfInputsException{
        if(inputs.length != data.getExpectedNumInputs(layerIndex, neuronIndex)){
            throw new IncorrectNumberOfInputsException();
        }
        //Apply the inputs
        float[] weights = data.getNeuronWeights(layerIndex, neuronIndex);
        boolean[] lineValidities = data.getNeuronLineValidity(layerIndex, neuronIndex);
        float output = 0;
        for(int i=0; i<inputs.length; i++){
            if(lineValidities[i]){
                output += inputs[i]*weights[i];
            }
        }
        
        //Apply the offset
        output -= weights[weights.length-1];
        
        //Apply the activation function
        ActivationFunction f = data.getActivationFunction(layerIndex, neuronIndex);
        if(f!=null){
            output = f.function(output);
        }
        
        return output;
    }
    
    
    
}
