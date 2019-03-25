import java.util.*;

public class SkipNode {
    public int        nodeHeight;
    public int        key;
    public SkipNode[] Nodes;
     
    public static final int MaxNodeValue = 65536;
    public static final int MinNodeValue = 0;
    
    public SkipNode(int k,int h) {
        nodeHeight = h;
        key        = k;
        Nodes   = new SkipNode[h+1];
        Arrays.fill(Nodes, null);
        /*
        for (int x = 1; x <= nodeHeight; x++) 
          Nodes[x] = null;
          */
    }
}
