package neuralnet;

/**
 *
 * @author Michael Haertling
 */
public class NeuralNetwork {

    private final NeuralNetworkData data;
    private NeuralLayer[] layers;

    public NeuralNetwork(int numInputs, int numOutputs, int hiddenLayers) {
        data = new NeuralNetworkData(numInputs, numOutputs, hiddenLayers);
        generateNetwork();
    }

    public NeuralNetwork(NeuralNetworkData d) {
        data = d;
        generateNetwork();
    }

    private void generateNetwork() {
        layers = new NeuralLayer[data.getNumberOfLayers()];
        //Generate the layers
        for (int i = 0; i < layers.length; i++) {
            layers[i] = new NeuralLayer(data, i);
        }
    }

    public float[] applyInputs(float[] inputs) throws IncorrectNumberOfInputsException {
        for (int i = 0; i < layers.length; i++) {
            inputs = layers[i].applyInputs(inputs);
        }
        return inputs;
    }

    public NeuralNetworkData getNetworkData() {
        return data;
    }

    public void setOutputRange(int index, int min, int max) throws RangeNotAdjustableException {
        data.setOutputRange(index, min, max);
    }
}
