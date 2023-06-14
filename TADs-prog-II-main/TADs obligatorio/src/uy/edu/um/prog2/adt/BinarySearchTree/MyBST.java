package uy.edu.um.prog2.adt.BinarySearchTree;

import exceptions.NodeAlreadyExists;
import exceptions.NodeNotFound;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;

public interface MyBST<K extends Comparable<K>, T> {

    T find(K key) throws NodeNotFound;
    void insert (K key, T data) throws NodeAlreadyExists;
    void delete (K key) throws NodeNotFound;
    void size();
    MyLinkedListImpl inOrder();
    MyLinkedListImpl<NodeBST> postOrder();
    MyLinkedListImpl preOrder();
    Boolean contains(Comparable key);
}