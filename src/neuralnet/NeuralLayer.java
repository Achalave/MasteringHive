
package neuralnet;

/**
 *
 * @author Michael
 */

//w1*i1 + w2*i2 + w3*i3 + ... - offset = output
public class NeuralLayer {
        
    private final Neuron[] neurons;
    
    public NeuralLayer(NeuralNetworkData data, int layerNumber){
        neurons = new Neuron[data.getNumberOfNeuronsInLayer(layerNumber)];
        //Generate the neurons
        for(int i=0; i<neurons.length; i++){
            neurons[i] = new Neuron(data,layerNumber,i);
        }
    }
    
    public float[] applyInputs(float[] inputs) throws IncorrectNumberOfInputsException{
        float[] outputs = new float[neurons.length];
        for(int i=0; i<neurons.length; i++){
            outputs[i] = neurons[i].applyInputs(inputs);
        }
        return outputs;
    }
    
}
