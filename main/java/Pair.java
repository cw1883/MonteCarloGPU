/**
 * This class creates an key value pair
 * Created by Chao Wei on 2014/12/12.
 */

public class Pair<K, V> {
	private K key;
	private V value;
	
	public Pair(K key, V value){
		this.key = key;
		this.value = value;
	}
	
	public K getKey(){ return key;}
	public V getValue(){ return value;}
	
}

