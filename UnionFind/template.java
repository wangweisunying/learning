class UnionFind{
    int[] f;
    UnionFind(int n){
        
        f = new int[n];
        //init f; 
        

    }



    public int find(int x){
        if(f[x] == x){
            return x;
        }
        return f[x] = find(f[x]);
    }
    public void union(int A , int B){
        rootA = find(A);
        rootB = find(B);
        if(rootA != rootB){
            f[rootA] = rootB;
            //do something here;
        }
    }
 

}