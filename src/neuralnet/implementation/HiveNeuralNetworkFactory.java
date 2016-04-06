
package neuralnet.implementation;

import neuralnet.NeuralNetwork;
import neuralnet.NeuralNetworkData;
import neuralnet.activationFunctions.ActivationFunction;
import neuralnet.activationFunctions.NegToPosOneActivationFunction;
import neuralnet.activationFunctions.ZeroToOneActivationFunction;

/**
 *
 * @author Michael
 */
public class HiveNeuralNetworkFactory implements NeuralNetworkFactory{

    private static ActivationFunction zeroToOne;
    private static ActivationFunction negToPosOne;
    
    ActivationFunction[][] funcs;
    int numInputs;
    int numOutputs;
    int hiddenLayers;
    
    public HiveNeuralNetworkFactory(){
        //Instantiate the activation functions if needed
        if(zeroToOne == null){
            zeroToOne = new ZeroToOneActivationFunction(1);
        }
        if(negToPosOne == null){
            negToPosOne = new NegToPosOneActivationFunction(1);
        }
        
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //Defaults should be set from a properties file in the future
        
        //Idea for later
        //11 Pieces for each - 22 maximum board width/height - 22 x 22 board size
        //484 maximum places
        
        //0-10: Friendly pieces
        //11-21: Enemy pieces
        numInputs = 22;
        
        //Piece placement - Piece movement
        //1: Should move    [0,1]
        //2: Move number    [0,10]
        //3: Move to        [0,484]
        //4: Should place   [0,1]
        //5: Piece to place [0,10]
        //5: Place at       [0,484]
        numOutputs = 5;
        
        hiddenLayers = 2;
        
        funcs = new ActivationFunction[hiddenLayers+1][];
        //Set the hidden layer activation functions
        for(int i=0; i< funcs.length-1;i++){
            funcs[i] = new ActivationFunction[numInputs];
        }
        
        //Set the output layer activation functions
        funcs[hiddenLayers] = new ActivationFunction[numOutputs];
        for(int i=0; i<numOutputs; i++){
            funcs[i] = null;
        }
    }
    
    @Override
    public NeuralNetwork generateNeuralNetwork() {
        return new NeuralNetwork(new NeuralNetworkData(numInputs,numOutputs,hiddenLayers,funcs));
    }
    
    
    
}
