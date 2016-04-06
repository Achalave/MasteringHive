package neuralnet;

import neuralnet.activationFunctions.ActivationFunction;
import neuralnet.activationFunctions.RangedActivationFunction;

/**
 *
 * @author Michael
 */
public class NeuralNetworkData {

    private float[][][] weights;
    private boolean[][][] lineValidity;
    private ActivationFunction[][] activationFunctions;
    private int totalNumNodes;
    private int totalNumLines;

    public NeuralNetworkData() {
        
    }

    public NeuralNetworkData(int numInputs, int numOutputs, int hiddenLayers, ActivationFunction[][] funcs) {
        this.populateWithRandomData(numInputs, numOutputs, hiddenLayers);
        activationFunctions = funcs;
    }

    public NeuralNetworkData(int numInputs, int numOutputs, int hiddenLayers) {
        this.populateWithRandomData(numInputs, numOutputs, hiddenLayers);
        //Generate activation functions
        activationFunctions = new ActivationFunction[hiddenLayers+1][];
        //Have no functions for the hidden layers by default
        //Generate AFs for the output layer
        activationFunctions[hiddenLayers] = new ActivationFunction[numOutputs];
        for(int i=0; i<numOutputs; i++){
            
        }
    }

    public final void populateWithRandomData(int numInputs, int numOutputs, int hiddenLayers) {
        totalNumNodes = numInputs * hiddenLayers + numOutputs;
        totalNumLines = numInputs * (hiddenLayers + 1);

        //There are hiddenLayers + 1(output layer) layers
        //There are numInputs neurons in hidden layers
        weights = new float[hiddenLayers + 1][][];
        lineValidity = new boolean[hiddenLayers + 1][][];

        //Instantiate neurons in the hidden layers
        for (int l = 0; l < hiddenLayers; l++) {
            this.instantiateLayerWeights(l, numInputs, numInputs);
            this.instantiateLayerLineValidities(l);
        }

        //Instantiate neurons in the output layer
        this.instantiateLayerWeights(hiddenLayers, numOutputs, numInputs);
        this.instantiateLayerLineValidities(hiddenLayers);
    }

    private void instantiateLayerWeights(int layer, int numNeurons, int numInputs) {
        weights[layer] = new float[numNeurons][];
        for (int n = 0; n < numNeurons; n++) {
            instantiateNeuronWeights(layer, n, numInputs);
        }
    }

    private void instantiateNeuronWeights(int layer, int neuron, int numInputs) {
        //Account for the offset with +1
        weights[layer][neuron] = new float[numInputs + 1];

        //Randomly populate numInputs
        for (int i = 0; i < weights[layer][neuron].length; i++) {
            weights[layer][neuron][i] = (float) (Math.random() - Math.random());
        }
    }

    private void instantiateLayerLineValidities(int layer) {
        int nodes = weights[layer].length;
        this.lineValidity[layer] = new boolean[nodes][];
        for (int n = 0; n < nodes; n++) {
            this.instantiateNeuronLineValidities(layer, n);
        }
    }

    private void instantiateNeuronLineValidities(int layer, int neuron) {
        lineValidity[layer][neuron] = new boolean[weights[layer][neuron].length - 1];
        //Set every line to true by default
        for (int i = 0; i < lineValidity[layer][neuron].length; i++) {
            lineValidity[layer][neuron][i] = true;
        }
    }

    public int getTotalNumNodes() {
        return totalNumNodes;
    }

    public int getTotalNumLines() {
        return totalNumLines;
    }

    public boolean[] getNeuronLineValidity(int layer, int neuron) {
        return lineValidity[layer][neuron];
    }

    public float[] getNeuronWeights(int layer, int neuron) {
        return weights[layer][neuron];
    }

    public boolean[][] getLayerValidities(int layer) {
        return lineValidity[layer];
    }

    public float[][] getLayerWeights(int layer) {
        return weights[layer];
    }

    public int getExpectedNumInputs(int layer, int neuron) {
        return weights[layer][neuron].length - 1;
    }

    public int[][] getSizeMatrix() {
        int[][] sizes = new int[weights.length][];
        for (int i = 0; i < sizes.length; i++) {
            sizes[i] = new int[weights[i].length];
            for (int k = 0; k < sizes[i].length; k++) {
                sizes[i][k] = weights[i][k].length;
            }
        }
        return sizes;
    }

    public int getNumberOfLayers() {
        return weights.length;
    }

    public int getNumberOfNeuronsInLayer(int layer) {
        return weights[layer].length;
    }

    public ActivationFunction getActivationFunction(int layer, int neuron) {
        ActivationFunction[] f = activationFunctions[layer];
        if(f == null){
            return null;
        }
        return f[neuron];
    }

    public float[][][] getData() {
        return weights;
    }

    
    /**
     * Clones all the data in this data class into the specified NeuralNetworkData object.
     * Arrays are reused as much as possible to improve performance.
     * @param d The NeuralNetworkData object to be overridden with the data from this object.
     */
    public void cloneData(NeuralNetworkData d) {
        copyWeights(d.weights);
        copyLineValidities(d.lineValidity);
        copyActivationFunctions(d.activationFunctions);
    }

    /**
     * Copies the weights stored in this class to the weights in this object into
     * the specified float matrix.
     * @param copy The matrix to clone to.
     */
    private void copyWeights(float[][][] copy) {
        //Only create a new list if it is needed
        if (weights == null || copy.length != weights.length) {
            weights = new float[copy.length][][];
        }
        //for each layer
        for (int l = 0; l < copy.length; l++) {
            if (weights[l] == null || copy[l].length != weights[l].length) {
                weights[l] = new float[copy[l].length][];
            }
            //for each node
            for (int n = 0; n < copy[l].length; n++) {
                if (weights[l][n] == null || copy[l][n].length != weights[l][n].length) {
                    weights[l][n] = new float[copy[l][n].length];
                }
                //for each weight
                for (int w = 0; w < copy[l][n].length; w++) {
                    weights[l][n][w] = copy[l][n][w];
                }
            }
        }
    }

    /**
     * Copies the line validities stored in this class to the weights in this object into
     * the specified float matrix.
     * @param copy The matrix to clone to.
     */
    private void copyLineValidities(boolean[][][] copy) {
        //Only create a new list if it is needed
        if (copy.length != lineValidity.length) {
            lineValidity = new boolean[copy.length][][];
        }
        //for each layer
        for (int l = 0; l < copy.length; l++) {
            if (lineValidity[l] == null || copy[l].length != lineValidity[l].length) {
                lineValidity[l] = new boolean[copy[l].length][];
            }
            //for each node
            for (int n = 0; n < copy[l].length; n++) {
                if (lineValidity[l][n] == null || copy[l][n].length != lineValidity[l][n].length) {
                    lineValidity[l][n] = new boolean[copy[l][n].length];
                }
                //for each weight
                for (int w = 0; w < copy[l][n].length; w++) {
                    lineValidity[l][n][w] = copy[l][n][w];
                }
            }
        }
    }
    
    /**
     * Copies the ActivationFunctions stored in this class to the weights in this object into
     * the specified float matrix.
     * @param functions The matrix to clone to.
     */
    private void copyActivationFunctions(ActivationFunction[][] functions){
        if(functions.length != activationFunctions.length){
            functions = new ActivationFunction[activationFunctions.length][];
        }
        for(int i=0; i<activationFunctions.length; i++){
            if(functions[i] == null || functions[i].length != activationFunctions[i].length){
                functions[i] = new ActivationFunction[activationFunctions[i].length];
            }
            for(int k=0; k<activationFunctions[i].length; k++){
                functions[i][k] = activationFunctions[i][k].clone();
            }
        }
    }
    
    public void setOutputRange(int index, int min, int max) throws RangeNotAdjustableException{
        ActivationFunction af = this.getActivationFunction(this.getNumberOfLayers()-1, index);
        if(!(af instanceof RangedActivationFunction)){
            throw new RangeNotAdjustableException();
        }
        ((RangedActivationFunction)af).setRange(min, max);
    }
}
