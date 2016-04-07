
package genetics;

import java.util.ArrayList;
import neuralnet.GeneticNeuralNetwork;
import neuralnet.NeuralNetworkData;

/**
 *
 * @author Michael
 */
public class GeneticFactory {
    
    //ArrayList<NeuralNetworkGeneticWrapper> networks;
    float maxPerturbation;
    float mutationRate;
    
    float totalFitness;
    
    boolean removeLinks;
    boolean addLinks;
    
    public GeneticFactory(){
        
    }
    
    
    private void mutateNetworks(ArrayList<GeneticNeuralNetwork> networks){
        for(GeneticNeuralNetwork w:networks){
            NeuralNetworkData data = w.getNetworkData();
            mutateNetwork(data);
        }
    }
    
    private void mutateNetwork(NeuralNetworkData data){
        for(int l=0; l<data.getNumberOfLayers(); l++){
            mutateLayer(data.getLayerWeights(l));
        }
    }
    
    private void mutateLayer(float[][] layer){
        for(float[] neuron:layer){
            mutateNeuron(neuron);
        }
    }
    
    private void mutateNeuron(float[] neuron){
        for(int i=0;i<neuron.length;i++){
            if(Math.random()<mutationRate){
                neuron[i] += (Math.random()-Math.random())*maxPerturbation;
            }
        }
    }
    
    private void crossoverNetworks(ArrayList<GeneticNeuralNetwork> networks,GeneticNeuralNetwork n1, GeneticNeuralNetwork n2){
        float[][][] w1 = n1.getNetworkData().getData();
        float[][][] w2 = n2.getNetworkData().getData();
        
        int split = (int)(Math.random()*n1.getNetworkData().getTotalNumLines());
        
        int x=0,y=0,z=0;
        while(split-->0){
            float temp = w1[x][y][z];
            w1[x][y][z] = w2[x][y][z];
            w2[x][y][z] = temp;
        }
    }
    
    private GeneticNeuralNetwork rouletteSelection(ArrayList<GeneticNeuralNetwork> networks){
        double slice = Math.random()*totalFitness;
        double fitnessSum = 0;
        
        for(GeneticNeuralNetwork w:networks){
            fitnessSum += w.getFitness();
            if(fitnessSum >= slice){
                return w;
            }
        }
        return null;
    }
    
    public void breed(ArrayList<GeneticNeuralNetwork> networks){
        
    }
}
