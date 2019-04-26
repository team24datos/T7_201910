package model.data_structures;

import java.util.Iterator;

public class Graph<K extends Comparable<K>, V, A extends Comparable<A>>
{
	private final int numVertices;
	private int numArcos;
	private TablaHash<K, Vertex> vertices;
	private LinkedList<Arc> arcos;
	
	public Graph() 
	{
		numVertices=0;
		numArcos=0;
		vertices= new TablaHash<K, Vertex>(); 
		
	}
	
	public int V()
	{
		return numVertices;
	}

	public int E()
	{
		return numArcos;
	}
	
	public void addVertex(K idVertex, V infoVertex)
	{
		
	}

	public void addEdge(K idVertexIni, K idVertexFin, A infoArc)
	{
		
	}
	
	public V getInfoVertex(K idVertex)
	{
		return null;
	}
	
	public void setInfoVertex(K idVertex, V infoVertex)
	{
		
	}
	
	public A getInfoArc( K idVertexIni, K idVertexFin)
	{
		return null;
	}
	
	public void setInfoArc( K idVertexIni, K idVertexFin, A infoArc)
	{
		
	}
	
	public Iterator<K> adj(K idVertex)
	{
		return null;
	}
	

	private class Vertex<K,V>
	{
		K id;
		V info;
		LinkedList adyacentes;
		
	}
	
	private class Arc implements Comparable<Arc>
	{
		A info;

		@Override
		public int compareTo(Graph<K, V, A>.Arc arg0) 
		{
			// TODO Auto-generated method stub
			return 0;
		}
	}
	
}
