
package genetics;

import neuralnet.NeuralNetworkData;

/**
 *
 * @author Michael
 */
public class NeuralNetworkGeneticWrapper {
    
    NeuralNetworkData data;
    float fitness;
    
    public NeuralNetworkGeneticWrapper(NeuralNetworkData d){
        data = d;
    }
    
    public NeuralNetworkData getNetworkData(){
        return data;
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
