/*
 * Our implementation of the treeSearch utility, from Code Fragment 11.3, relies
 * on recursion. For a large unbalanced tree, it is possible that Java’s call
 * stack will reach its limit due to the recursive depth. Give an alternative
 * implementation of that method that does not rely on the use of recursion.

 */

private Position<Entry<K,V>> treeSearch(Position<Entry<K,V>> p, K key){
    Position<Entry<K,V>> temp = p;
    int comp;

    while(!isExternal(temp)){
        comp = compare(key, temp.getElement());
        if(comp == 0){
            return temp;
        }else if(comp < 0){
            temp = left(temp);
        }else{
            temp = right(temp);
        }
    }
    return temp;
}
