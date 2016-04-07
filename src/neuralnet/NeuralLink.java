
package neuralnet;

/**
 *
 * @author Michael
 */
public class NeuralLink {
    float weight;
    NeuralReceptor out;
    
    public NeuralLink(NeuralReceptor r, float weight){
        this.weight = weight;
        out = r;
    }
    
    public NeuralLink(NeuralReceptor r){
        this.weight = generateRandomWeight();
        out = r;
    }
    
    public void pushValue(float val){
        out.incrementOutput(val*weight);
    }
    
    private float generateRandomWeight(){
        return (float) (Math.random() - Math.random());
    }
}
