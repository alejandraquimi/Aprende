package ec.edu.espol.Main;

import ec.edu.espol.Graph.GraphLA;

/**
 *
 * @author Jocellyn Luna
 */
public class Main {

    public static void main(String[] args) {

        GraphLA<String, String> grafo = new GraphLA<>(true);

        grafo.addVertex("V1");
        grafo.addVertex("V2");
        grafo.addVertex("V3");
        grafo.addVertex("V4");
        grafo.addVertex("V5");
        grafo.addVertex("V6");

        grafo.connect("V1", "V2",null, 3);
        grafo.connect("V1", "V3",null, 4);
        grafo.connect("V3", "V1",null, 4);
        grafo.connect("V1", "V5",null, 8);
        grafo.connect("V2", "V5", null,5);
        grafo.connect("V3", "V5", null,3);
        grafo.connect("V5", "V4", null,7);
        grafo.connect("V5", "V6", null,3);
        grafo.connect("V6", "V4", null,2);
        
        System.out.println(grafo.ComponenetesFuerteConexo());

        //System.out.println(grafo);

    }
}
