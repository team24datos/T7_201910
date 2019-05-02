package model.data_structures;

import java.util.Iterator;

public class Graph<K extends Comparable<K>, V, A extends Comparable<A>>
{
	private int numVertices;
	private int numArcos;
	private TablaHash<K, Vertice> vertices;
	private LinkedList<Arco> arcos;
	
	public Graph() 
	{
		numVertices=0;
		numArcos=0;
		vertices= new TablaHash<K, Vertice>(); 
		
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
		Vertice nuevoVertice = new Vertice(idVertex, infoVertex);
		vertices.put(idVertex, nuevoVertice);
		numVertices++;
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
	

	private class Vertice<K,V>
	{
		K id;
		V info;
		LinkedList adyacentes;
		boolean marcado;
		public Vertice(K pKey, V pInfo)
		{
			id = pKey;
			info = pInfo;
			adyacentes = new LinkedList<Arco>();
			marcado = false;
		}
		
	}
	
	private class Arco implements Comparable<Arco>
	{
		A info;

		@Override
		public int compareTo(Graph<K, V, A>.Arco arg0) 
		{
			// TODO Auto-generated method stub
			return 0;
		}
	}
	
}
