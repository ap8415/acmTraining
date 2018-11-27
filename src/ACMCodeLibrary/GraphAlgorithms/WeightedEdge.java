package ACMCodeLibrary.GraphAlgorithms;

public class WeightedEdge {
    int from,to;
    int weight;
    int residual;

    public WeightedEdge(int to, int weight) {
        this.to = to;
        this.weight = weight;
        residual = weight;
    }

    public WeightedEdge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
        residual = weight;
    }
}
