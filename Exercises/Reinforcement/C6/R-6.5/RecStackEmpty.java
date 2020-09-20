public void recStackEmpty(Stack S){
    if(S.top() != null){
        S.pop();
        recStackEmpty(S);
    }
}
