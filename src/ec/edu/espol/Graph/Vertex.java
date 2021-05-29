
package ec.edu.espol.Graph;

import java.util.LinkedList;
import java.util.Objects;

/**
 *
 * @author Jocellyn Luna
 */
public class Vertex <V, E> {
    private V data;
    private LinkedList<Edge<E, V>> egdes;
    private boolean visited;
    private Vertex<V, E> antecesor;
    
    public Vertex(V data){
        this.data = data;
        egdes = new LinkedList<>();
        visited = false;
        antecesor = null;
    }

    public Vertex<V, E> getAntecesor() {
        return antecesor;
    }

    public void setAntecesor(Vertex<V, E> antecesor) {
        this.antecesor = antecesor;
    }

    public V getData() {
        return data;
    }
    
    public boolean isVisited(){
        return visited;
    }

    public void setData(V data) {
        this.data = data;
    }

    public void setVisited(boolean visited){
        this.visited = visited;
    }
    
    public LinkedList<Edge<E, V>> getEgdes() {
        return egdes;
    }

    public void setEgdes(LinkedList<Edge<E, V>> egdes) {
        this.egdes = egdes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vertex<V,E> other = (Vertex<V,E>) obj;
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }   
    
    @Override
    public String toString(){
        return data.toString();
    }
}
