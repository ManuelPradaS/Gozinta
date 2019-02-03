import java.util.ArrayList;
import java.lang.*;
import java.util.*;

class Node{
    long key;
    ArrayList<Long> children;

    public Node() {
    }

    public Node(long key, ArrayList<Long> children) {
        this.key = key;
        this.children = children;
    }

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public ArrayList<Long> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Long> children) {
        this.children = children;
    }
}

public class Gozinta {

    static  ArrayList<Long> dividers = new ArrayList<>();

    static TreeMap<Long, ArrayList<Long>> gozintaGraph = new TreeMap<>();

    static ArrayList<ArrayList<Long>> chainList = new ArrayList<ArrayList<Long>>();

    private static ArrayList<Long> temporalPath =new ArrayList<Long>();

    public static void setTemporalPath(ArrayList<Long> temporalPath) {
        Gozinta.temporalPath = temporalPath;
    }

    public static ArrayList<Long> getTemporalPath() {
        return temporalPath;
    }


    static boolean isConected(long a,long c){
        if (c%a==0){return true;}
        else  return false;

    }

    static boolean isDivider(long N,long a){

        if ( N%a==0){return true;}
        else return false;
    }

    static void setDividers (long N){

        for (long i = 1; i <= N/2 ; i++) {

            if (isDivider(N,i)){
                dividers.add(i);
            }
        }
    }

    static void setConections(long N,int dividerIndex){
        long divider=dividers.get(dividerIndex);
        ArrayList<Long> children = new ArrayList<>();

        for (int k = dividerIndex+1; k<dividers.size(); k++) if (isConected(divider, dividers.get(k))) children.add( dividers.get(k));
        children.add(N);
        Node currentNode = new Node(divider,children);
        gozintaGraph.put(currentNode.key,currentNode.children);
    }

    static void createGraph(long N){

        for (int i = 0; i <dividers.size() ; i++) setConections(N, i);

    }

    static void clearCurrentPath(){
        ArrayList<Long> temporalPath =new ArrayList<>();
        setTemporalPath(temporalPath);
        long base=1;
        temporalPath.add(base);
    }


    static void searchDepth(long firstNode, long endNode)  {

        if (firstNode==endNode){
            temporalPath.add(endNode);
            chainList.add(temporalPath);
            clearCurrentPath();
        }
         else {
            for (long child : gozintaGraph.get(firstNode)) {
                temporalPath.add(firstNode);
                searchDepth(child,endNode);
                temporalPath.remove(firstNode);
            }

        }

    }

    static long readLong()throws InputMismatchException  {
        long number=-1;
        Scanner readLine =new Scanner(System.in);
        try {
           return  readLine.nextLong();
        } catch (InputMismatchException e) {
            System.out.println("Input is not a number");
        }
       return number;
    }

    public static void main(String[] args) {
        long initialNode=1;
        long destinationNode=1;

       do {
           System.out.println("Enter n>0:");
           destinationNode=readLong();
       }while (destinationNode<=0);

        setDividers(destinationNode);
        createGraph(destinationNode);
        searchDepth(initialNode,destinationNode);

        System.out.println("Number of chains g(n):"+chainList.size());
        System.out.println("Gozinta chains for n="+ destinationNode+":");
        System.out.println(chainList);
    }


}
