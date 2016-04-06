
package main;

import java.util.Arrays;
import neuralnet.IncorrectNumberOfInputsException;
import neuralnet.NeuralNetwork;
import neuralnet.NeuralNetworkData;
import neuralnet.activationFunctions.RangedActivationFunction;

/**
 *
 * @author Michael
 */
public class Tester {
    
    public static void main(String[] args) throws IncorrectNumberOfInputsException{
        
    }
    
    public void testNetwork() throws IncorrectNumberOfInputsException{
        NeuralNetwork net = new NeuralNetwork(new NeuralNetworkData(10,5,2));
        float[] inputs = {1,0,3,5,2,10,3,4,5,2};
        System.out.println(Arrays.toString(net.applyInputs(inputs)));
    }
    
    public void testNetworkData(){
        NeuralNetworkData data = new NeuralNetworkData(10,5,2);
        System.out.println("Sizes:");
        int[][] sizes = data.getSizeMatrix();
        for(int i=0; i<sizes.length; i++){
            System.out.println(Arrays.toString(sizes[i]));
        }
        
        System.out.println("\nLayer 1:");
        float[][] layer = data.getLayerWeights(0);
        for(float[] node:layer){
            System.out.println(Arrays.toString(node));
        }
        System.out.println("\n");
        for(boolean[] node:data.getLayerValidities(0)){
            System.out.println(Arrays.toString(node));
        }
    }
    
}
