import java.io.Serializable;

public class Node implements Serializable {
    int id;
    int height;
    Node left;
    Node right;

    Node(int _id){
        this.id=_id;

        this.height=1;
    }
}