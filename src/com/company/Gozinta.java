package com.company;


import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeMap;

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


    static TreeMap<Long, ArrayList<Long>> grafo = new TreeMap<Long, ArrayList<Long>>();

    static TreeMap<Long, Stack<Long>> grafo2 = new TreeMap<Long, Stack<Long>>();

    static ArrayList<ArrayList<Long>> chainList = new ArrayList<ArrayList<Long>>();

    static  ArrayList<Long> divisores = new ArrayList<Long>();

    static ArrayList<Node>  Graph = new ArrayList<Node>();

    Stack<Long> visited= new Stack<Long>();

    static boolean isConected(long a,long c){
        if (c%a==0){return true;}
        else  return false;

    }

    static boolean isDivisor(long N,long a){

        if ( N%a==0){return true;}
        else return false;
    }


    static void addBegin(long N){
        long base =1;
        ArrayList<Long> result = new  ArrayList<Long>();
        result.add(base);
        result.add(N);
        chainList.add(result);
    }

    static void addSimple(long N,long a){
        long base =1;
        ArrayList<Long> result = new  ArrayList<Long>();
        result.add(base);
        result.add(a);
        result.add(N);
        chainList.add(result);
    }

    static void addTemporal(long N,ArrayList<Long> temporal){
        temporal.add(N);
        chainList.add(temporal);
    }

    static void addNodes(long a,long N){
        ArrayList<Long> children = new ArrayList<Long>();

        for (long k = a+1; k<= N/2; k++) {

            if (isConected(a,k)){

                children.add(k);

            }
            else{

            }
        }
        Node actual = new Node(a,children);

        Graph.add(actual);

    }
    static void createGraph(){

        for (Node node:Graph) {
            grafo.put(node.key,node.children);

        }

    }

    static void createGraph2(){

        for (Node node:Graph) {
            Stack<Long> stack= new Stack<Long>();
            for (int i = 0; i <node.children.size() ; i++) {
                stack.push(node.getChildren().get(i));
            }
            grafo2.put(node.key,stack);

        }

    }


    static void makePath(long N, ArrayList<Long> temporal){

        long current=temporal.get(temporal.size()-1);

        if (grafo2.get(current).isEmpty()){

            addTemporal(N,temporal);

        }

         else {

            for (long child : grafo2.get(current)) {

                temporal.add(child);
                makePath(N, temporal);

//                grafo2.get(current).pop();
//               temporal.remove(temporal.size()-1);
//                makePath(N, temporal);
            }
        }

    }



    public static void main(String[] args) {
        long N=12;
        long base =1;
        addBegin(N);


        for (long i = 2; i <= N/2 ; i++) {
            if (isDivisor(N,i)){
                divisores.add(i);
                addSimple(N,i);
            }

        }

        for (long divisor:divisores) {
            addNodes(divisor,N);
        }

        createGraph2();


        for (long divisor:divisores) {
            ArrayList<Long> temporal = new  ArrayList<Long>();
            temporal.add(base);
            temporal.add(divisor);

            makePath(N,temporal);

        }

//        System.out.println(grafo.get(prueba).toString());
        System.out.println(chainList);
        System.out.println(chainList.size());
    }








}
