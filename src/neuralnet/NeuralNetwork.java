package neuralnet;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import neuralnet.activationFunctions.RangedActivationFunction;

/**
 *
 * @author Michael Haertling
 */
public class NeuralNetwork {

    private NeuralLayer[] layers;
    private static Constructor defaultOutputAF;
    
    private int expectedNumInputs;

    public NeuralNetwork(int numInputs, int numOutputs, int hiddenLayers) {
        this.expectedNumInputs = numInputs;
        generateNetwork(numInputs, numOutputs, hiddenLayers);
    }

    private void generateNetwork(int numInputs, int numOutputs, int hiddenLayers) {
        layers = new NeuralLayer[hiddenLayers + 1];
        //Fill out the layers array
        try {
            //Fill out the hidden layers
            for (int i = 0; i < hiddenLayers; i++) {
                layers[i] = new NeuralLayer(numInputs, null);
            }
            
            //If needed, fill out the default activation function field
            if(defaultOutputAF == null){
                defaultOutputAF = RangedActivationFunction.class.getConstructor();
            }
            
            //Fill out the output layer
            layers[hiddenLayers] = new NeuralLayer(numOutputs,defaultOutputAF);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(NeuralNetwork.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Sends the inputs through the entire network and returns an array of outputs.
     * @param inputs The array of inputs to be pushed through the network.
     * @return The outputs calculated by the network.
     * @throws IncorrectNumberOfInputsException
     * Thrown if the network is expecting a different number of inputs.
     */
    public float[] applyInputs(float[] inputs) throws IncorrectNumberOfInputsException {
        //Make sure there is a valid number of inputs
        if(inputs.length != this.expectedNumInputs){
            throw new IncorrectNumberOfInputsException();
        }
        
        //Place the inputs into the network
        Neuron[] tempLayer = layers[0].getNeurons();
        for(Neuron n:tempLayer){
            for(float in:inputs){
                n.incrementOutput(in);
            }
        }
        
        //Propigate the outputs through the hidden layers
        for(int i=1; i<layers.length-1; i++){
            layers[i].pushOutputs();
        }
        
        //Get the output stored in the output layer
        tempLayer = layers[layers.length-1].getNeurons();
        float[] outputs = new float[layers.length];
        for(int i=0; i<tempLayer.length;i++){
            outputs[i] = tempLayer[i].retrieveOutput();
        }
        
        return outputs;
    }

    /**
     * Sets a specific output to be confined between a given range.
     * @param index The index of the output node to be set.
     * @param min The minimum value to be output by this node.
     * @param max The maximum value to be output by this node.
     * @throws RangeNotAdjustableException 
     * Thrown if the selected output node's ActivationFunction does not support range adjustment.
     */
    public void setOutputRange(int index, int min, int max) throws RangeNotAdjustableException {
        Neuron[] outputLayer = layers[layers.length-1].getNeurons();
        outputLayer[index].setOutputRange(min, max);
    }
}
