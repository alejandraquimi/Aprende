package ec.edu.espol.Graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author Jocellyn Luna
 */
public class GraphLA<V, E> {

    private LinkedList<Vertex<V, E>> vertices;
    private boolean directed;

    public GraphLA(boolean directed) {
        this.vertices = new LinkedList<>();
        this.directed = directed;
    }

    public boolean estaVacio() {
        return vertices.isEmpty();
    }

    public boolean addVertex(V data) {
        Vertex<V, E> v = new Vertex<>(data);
        return (data == null || vertices.contains(v)) ? false : vertices.add(v);
    }
    
   
    /*private Vertex<V,E> searchVertex(V data){
        for(Vertex<V,E> v:vertices){
            if(v.getData().equals(data)) return v;
            
        }
        return null;
    }*/
 
    
        private void cleanVertexes(){
        for(Vertex<V,E> v:vertices)
            v.setVisited(false);
    }
    public GraphLA<V,E> reverse(){
        GraphLA<V,E> graph=new GraphLA<>(directed);
        if(estaVacio()) return graph;
        for(Vertex<V,E>v: vertices){
            Vertex<V,E> v1=new Vertex<>(v.getData());
            graph.vertices.add(v1);
        }
        for(Vertex<V,E>v: vertices){
            for(Edge<E,V> e: v.getEgdes()){
                graph.connect(e.getDestino().getData(), e.getOrigen().getData(),null,e.getPeso());
            }
          
        }
        return graph;
        
    }
    
    public List<V> bfs(V inicio){
        LinkedList<V> l=new LinkedList<>();
        
        Vertex<V,E> v=buscarVertice(inicio);
        if(v==null) return l;
        
        Queue<Vertex<V,E>> q=new LinkedList<>();
        q.offer(v);
        v.setVisited(true);
        
        while(!q.isEmpty()){
            v=q.poll();
            l.add(v.getData());
            
            for(Edge<E,V> e: v.getEgdes()){
                v=e.getDestino();
                if(!v.isVisited()){
                    q.offer(v);
                    v.setVisited(true);
                            
                }
            }
        }
        cleanVertexes();
        return l;
        
        
    }
    
    
    public List<List<V>> ComponenetesFuerteConexo(){
        List<List<V>> componentes=new LinkedList<>();
        GraphLA<V,E> graph=this.reverse();
        for(Vertex<V,E> v: vertices){
            if(!v.isVisited()){
                HashSet<V> setGraph=new HashSet<>(this.bfs(v.getData()));
                
                setGraph.retainAll(graph.bfs(v.getData()));
                
                List<V> componente=new LinkedList<>(setGraph);
                //componente.setVisitedVertex();
               
                    componentes.add(componente);
                    
                   
                }
                
               
                
                
                
            
        }
        cleanVertexes();
        return componentes;
    }
    
    public ArrayList<Vertex<V,E>> recorridoAnchura(Vertex<V,E> vertice){
        if(vertice==null) return null;
        else{
            ArrayList<Vertex<V,E>> recorridos=new ArrayList<>();
            vertice.setVisited(true);
            Queue<Vertex<V,E>> c=new LinkedList<>();
            recorridos.add(vertice);
            c.add(vertice);
            while(!c.isEmpty()){
                Vertex<V,E> tmpVertice=c.poll();
                recorridos.add(tmpVertice);
                
                //tmpVertice.setVisited(true);
                LinkedList<Edge<E,V>> ad=tmpVertice.getEgdes();
                for(Edge<E,V> v:ad){
                    Vertex<V,E> verticeAd=v.getDestino();
                    if(!verticeAd.isVisited()){
                        recorridos.add(verticeAd);
                        c.add(verticeAd);
                        verticeAd.setVisited(true);
                    }
                            
                    
                }
            }
            cleanVertexes();
            return recorridos;
        }
        
    }
    
    
    

    public boolean connect(V origen, V destino, E data,int peso) {
        if (origen == null || destino == null) {
            return false;
        }

        Vertex<V, E> vo = buscarVertice(origen);
        Vertex<V, E> vd = buscarVertice(destino);

        if (vo == null || vd == null) {
            return false;
        }

        Edge<E, V> e = new Edge<>(vo, vd,data, peso);

        if (vo.getEgdes().contains(e)) {
            return false;
        }

        vo.getEgdes().add(e);
        if (!directed) {
            Edge<E, V> e1 = new Edge<>(vd, vo,data, peso);
            vd.getEgdes().add(e1);
        }
        return true;
    }

    private Vertex<V, E> buscarVertice(V data) {
        for (Vertex<V, E> v : vertices) {
            if (v.getData().equals(data)) {
                return v;
            }
        }
        return null;
    }

    public int getOutDegree(V data) {
        if (data == null) {
            return -1;
        }

        Vertex<V, E> v = buscarVertice(data);
        return (v == null) ? -1 : v.getEgdes().size();
    }

    public int getInDegree(V data) {
        Vertex<V, E> vertex = buscarVertice(data);
        if (vertex == null) {
            return -1;
        }

        int grado = 0;

        for (Vertex<V, E> v : vertices) {
            for (Edge<E, V> e : v.getEgdes()) {
                if (e.getDestino().equals(vertex)) {
                    grado++;
                }
            }

        }
        return grado;
    }

    @Override
    public String toString() {
        StringBuilder v = new StringBuilder();
        v.append(" v={");

        StringBuilder a = new StringBuilder();
        a.append(" a={");

        for (Vertex<V, E> vertex : vertices) {
            v.append(vertex.getData());
            v.append(", ");
            for (Edge<E, V> e : vertex.getEgdes()) {
                a.append(e.toString());
                a.append(", ");
            }
        }
        if (!vertices.isEmpty()) {
            v.delete(v.length() - 2, v.length());
        }
        if (a.length() > 4) {
            a.delete(a.length() - 2, a.length());
        }

        v.append("}");
        a.append("}");
        return v.toString() + "\n" + a.toString();
    }

    public boolean removerVertice(V data) {
        if (data == null) {
            return false;
        }

        Vertex<V, E> v = buscarVertice(data);
        if (v == null) {
            return false;
        }

        for (Vertex<V, E> vertex : vertices) {
            Iterator<Edge<E, V>> l = vertex.getEgdes().iterator();
            while (l.hasNext()) {
                Edge<E, V> e = l.next();
                if (e.getDestino().equals(v) || e.getOrigen().equals(v)) {
                    l.remove();
                }
            }
        }

        v.setData(null);
        v.setEgdes(null);
        vertices.remove(v);
        return true;

    }

    public boolean removerArco(V origen, V destino) {
        if (origen == null || destino == null) {
            return false;
        }

        Vertex<V, E> vo = buscarVertice(origen);
        Vertex<V, E> vd = buscarVertice(destino);

        if (vo == null || vd == null) {
            return false;
        }

        LinkedList<Edge<E, V>> edges = vo.getEgdes();

        Iterator<Edge<E, V>> l = edges.iterator();

        while (l.hasNext()) {
            Edge<E, V> e = l.next();

            if (e.getDestino().equals(vd)) {
                l.remove();
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof GraphLA)) {
            return false;
        }

        GraphLA<V, E> other = (GraphLA<V, E>) o;

        if (this.vertices.size() != other.vertices.size()) {
            return false;
        }

        Set<V> s1 = new HashSet<>();
        s1.addAll((Collection<V>) vertices);

        s1.removeAll((Collection<V>) other.vertices);
        if (!s1.isEmpty()) {
            return false;
        }

        for (Vertex<V, E> v : vertices) {
            Vertex<V, E> vOther = other.buscarVertice(v.getData());

            Set<Edge<E, V>> s2 = new HashSet<>();
            s2.addAll((Collection<Edge<E, V>>) v.getEgdes());

            s2.removeAll((Collection<Edge<E, V>>) vOther.getEgdes());
            if (!s2.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.vertices);
        hash = 53 * hash + (this.directed ? 1 : 0);
        return hash;
    }
    
}
