
package genetics;

import java.util.ArrayList;
import neuralnet.NeuralNetwork;
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
    
    public GeneticFactory(){
        //networks = new ArrayList<>();
    }
    
    
    private void mutateNetworks(ArrayList<NeuralNetworkGeneticWrapper> networks){
        for(NeuralNetworkGeneticWrapper w:networks){
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
    
    private void crossoverNetworks(ArrayList<NeuralNetworkGeneticWrapper> networks,NeuralNetworkGeneticWrapper n1, NeuralNetworkGeneticWrapper n2){
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
    
    private NeuralNetworkGeneticWrapper rouletteSelection(ArrayList<NeuralNetworkGeneticWrapper> networks){
        double slice = Math.random()*totalFitness;
        double fitnessSum = 0;
        
        for(NeuralNetworkGeneticWrapper w:networks){
            fitnessSum += w.getFitness();
            if(fitnessSum >= slice){
                return w;
            }
        }
        return null;
    }
    
    public void breed(ArrayList<NeuralNetworkGeneticWrapper> networks){
        
    }
}
