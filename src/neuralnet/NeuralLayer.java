
package neuralnet;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import neuralnet.activationFunctions.ActivationFunction;

/**
 *
 * @author Michael
 */

//w1*i1 + w2*i2 + w3*i3 + ... - offset = output
public class NeuralLayer {
        
    private final Neuron[] neurons;
    
    public NeuralLayer(int numNeurons, Constructor funcs, Object... intargs) 
            throws InstantiationException, IllegalAccessException, 
            IllegalArgumentException, InvocationTargetException{
        neurons = new Neuron[numNeurons];
        //Generate the neurons
        for(int i=0; i<neurons.length; i++){
            ActivationFunction f = null; 
            if(funcs != null){
                f = (ActivationFunction)funcs.newInstance(intargs);
            }
            neurons[i] = new Neuron(f);
        }
    }
    
    /**
     * Adds a link, with a random weight, between every neuron in this layer to
     * every neuron in nextLayer.
     * @param nextLayer The layer to completely connect to.
     */
    public void completelyOutputTo(NeuralLayer nextLayer){
        //Add links going from every neuron in this layer to every neuron in nextLayer
        Neuron[] nextNeurons = nextLayer.neurons;
        for(Neuron n:neurons){
            for(Neuron nn: nextNeurons){
                NeuralLink link = new NeuralLink(nn);
                n.addNeuralLink(link);
            }
        }
    }

    public Neuron[] getNeurons() {
        return neurons;
    }
    
    /**
     * Calls the pushOutput() function of every neuron in this layer. This effectively
     * pushes the output of every node through the associated NeuralLinks.
     */
    public void pushOutputs(){
        for(Neuron n:neurons){
            n.pushOutput();
        }
    }
    
}
