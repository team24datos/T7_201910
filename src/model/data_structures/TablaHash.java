package model.data_structures;

public interface TablaHash<Key extends Comparable<Key>, Value> {
	
	public void	put(Key key, Value value);
	public Value get(Key key);
	public Value delete(Key key);
	/*Iterator<K>	keys()
	{
		
	}*/

}
