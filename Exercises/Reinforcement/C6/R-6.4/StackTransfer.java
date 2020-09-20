public void transfer(Stack S, Stack T){
    while(S.top() != null){
        T.push(S.pop());
    }
}
