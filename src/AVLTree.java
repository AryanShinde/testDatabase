import java.io.*;
import java.util.ArrayList;

class Node2 extends Node{
    int data;
    String name;
    Node2(int _id,int _data,String name) {
        super(_id);
        this.data=_data;
        this.name=name;
    }
}
class Node3 extends Node{
    String div;
    int numOfStudents;

    Node3(int _id,String _div,int _numOfStudents) {
        super(_id);
        this.div=_div;
        this.numOfStudents=_numOfStudents;
    }
}
class masterNode {

}
public class AVLTree {
    Node root;
//    public AVLTree(Node node) {
//        this.root=node;
//    }

    private int getHeight(Node node){
        if(node==null){
            return 0;
        }
        int height=node.height;
        return node.height;
    }
    private Node leftRotate(Node x){
        Node y=x.right;
        Node t2=y.left;

        y.left=x;
        x.right=t2;
        x.height=Integer.max(getHeight(x.left),getHeight(x.right))+1;
        y.height=Integer.max(getHeight(y.left),getHeight(y.right))+1;
        return y;
    }
    private Node rightRotate(Node x){
        Node y=x.left;
        Node t2=y.right;

        y.right=x;
        x.left=t2;
        x.height=Integer.max(getHeight(x.left),getHeight(x.right))+1;
        y.height=Integer.max(getHeight(y.left),getHeight(y.right))+1;
        return y;
    }
    public Node insert(Node node,int data,int id,String name,String div,int num){

        if(node==null){
            Node temp=null;
            if(!name.equals("")){ //means its node two
                temp=new Node2(id,data,name);
            }
            if(num!=-1){
                temp=new Node3(id,div,num);
            }
            if(root==null){
                root=temp;
            }
            return temp;
        }

        if(id< node.id){
            node.left=insert(node.left,data,id,name,div,num);
        }else if(id>node.id){
            node.right=insert(node.right,data,id,name,div,num);
        }else{
            return node;
        }
        node.height=Integer.max(getHeight(node.left),getHeight(node.right))+1;
        int balance=getBalance(node);
        if(balance>1 && node.left.id>id){
            return rightRotate(node);
        }
        if(balance>1 && node.left.id<id){
           node.left=leftRotate(node.left);
           return rightRotate(node);
        }
        if(balance<-1 && node.right.id<id){
            return leftRotate(node);
        }
        if(balance<-1 && node.right.id>id){
            node.right=rightRotate(node.right);
            return leftRotate(node);
        }
        return node;

    }

    public void printTree1(Node2 root){
        if(root==null){
            return;
        }
        System.out.println(root.data);
        printTree1((Node2)root.left);
        printTree1((Node2)root.right);

    }

    public void printTree2(Node3 root){
        if(root==null){
            return;
        }
        System.out.println(root.numOfStudents);
        printTree2((Node3)root.left);
        printTree2((Node3)root.right);

    }

    private int getBalance(Node node) {
        return getHeight(node.left)-getHeight(node.right);
    }
    private void serializeData(Node root){
        try
        {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream("f.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(root);

            out.close();
            file.close();

            System.out.println("Object has been serialized");

        }

        catch(Exception e)
        {
            System.out.println(e);
        }


    }

    private Node decerializeData(){
        Node root=null;
        try
        {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("f.txt");
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            root = (Node)in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");
        }

        catch(Exception ex)
        {
            System.out.println("IOException is caught");
        }
        return root;
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
//        tree.root = tree.insert(tree.root, 5, 2, "aryan","",-1);
//        tree.root = tree.insert(tree.root, 10, 3, "shinde","",-1);
//        tree.root = tree.insert(tree.root, 15, 4, "bruh","",-1);
//
//
//        System.out.println("before serializing");
//        tree.printTree1((Node2)tree.root);

//        tree.serializeData(tree.root);
//        System.out.println("after serializing");
        tree.root=tree.decerializeData();
        tree.printTree1((Node2)tree.root);


//        System.out.println("dgbd\n\n");
//        tree1.printTree2((Node3)tree1.root);
    }
}
