package maps;

import linkedpositionallist.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnsortedTableMap<K,V> extends AbstractMap<K,V>{
    private PositionalList<MapEntry<K,V>> table = new LinkedPositionalList<>();

    public UnsortedTableMap(){

    }

    private Position<MapEntry<K,V>> findIndex(K key){
        for(Position<MapEntry<K,V>> p : table.positions()){
              if(p.getElement().getKey().equals(key)){
                return p;
            }
        }
        return null;
    }

    public int size(){
        return table.size();
    }

    public V get(K key){
        Position<MapEntry<K,V>> j = findIndex(key);
        if(j == null){
            return null;
        }
        return j.getElement().getValue();
    }

    public V put(K key, V value){
        Position<MapEntry<K,V>> j = findIndex(key);
        if(j == null){
            table.addFirst(new MapEntry<>(key,value));
            return null;
        }else{
            return j.getElement().setValue(value);
        }
    }

    public V remove(K key){
        Position<MapEntry<K,V>> j = findIndex(key);
        if(j == null){
            return null;
        }
        return table.remove(j).getValue();
    }

    private class EntryIterator implements Iterator<Entry<K,V>>{
        private Position<MapEntry<K,V>> cursor = table.first();
        private Position<MapEntry<K,V>> recent = null;


        public boolean hasNext(){
            return (cursor != null);
        }

        public Entry<K,V> next(){
            if(cursor == null){
                throw new NoSuchElementException();
            }
            recent = cursor;
            cursor = table.after(cursor);
            return recent.getElement();
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    private class EntryIterable implements Iterable<Entry<K,V>>{
        public Iterator<Entry<K,V>> iterator(){
            return new EntryIterator();
        }
    }

    public Iterable<Entry<K,V>> entrySet(){
        return new EntryIterable();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("{");

        for(Entry<K,V> e : entrySet()){
            sb.append("(" + e.getKey() + "," + e.getValue() + ")");
        }
        sb.append("}");
        return sb.toString();
    }
}
