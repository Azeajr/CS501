Node current = head;
Node previous = head;
while(current.getNext() != null){
    previous=current;
    current=current.getNext();
}

return previous;
