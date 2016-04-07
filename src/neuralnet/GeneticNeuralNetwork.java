
package neuralnet;

/**
 *
 * @author Michael
 */
public class GeneticNeuralNetwork extends NeuralNetwork{
    
    private float fitness;

    public GeneticNeuralNetwork(int numInputs, int numOutputs, int hiddenLayers) {
        super(numInputs, numOutputs, hiddenLayers);
    }

    public GeneticNeuralNetwork(NeuralNetworkData d) {
        super(d);
    }
    
    public void setFitness(float f){
        fitness = f;
    }
    
    public void clearFitness(){
        fitness = 0;
    }
    
    public void incrementFitness(float f){
        fitness += f;
    }
    
    public float getFitness(){
        return fitness;
    }
}
