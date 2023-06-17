package uy.edu.um.adt.binarytree;


import uy.edu.um.prog2.adt.BinarySearchTree.MyBST;
import uy.edu.um.prog2.adt.BinarySearchTree.MyBSTImpl;
import exceptions.NodeAlreadyExists;
import exceptions.NodeNotFound;
import uy.edu.um.prog2.adt.BinarySearchTree.NodeBST;
import uy.edu.um.prog2.adt.linkedlist.MyList;

import org.junit.jupiter.api.Test;
import static java.lang.Integer.valueOf;
import static org.junit.Assert.*;

public class tests {

    @Test
    public void testInsert(){

        MyBST<Integer, Integer> laPlantaEnCuestion = new MyBSTImpl(null);


        try {
            laPlantaEnCuestion.insert(3,3);
            laPlantaEnCuestion.insert(21, 21);
            laPlantaEnCuestion.insert(11, 11);
            laPlantaEnCuestion.insert(-1, -1);
            laPlantaEnCuestion.insert(4, 4);
            laPlantaEnCuestion.insert(18, 18);
        } catch (NodeAlreadyExists e) {
            throw new RuntimeException(e);
        }


        MyList<NodeBST> colValues = laPlantaEnCuestion.inOrder();

        assertEquals(valueOf(-1), colValues.get(0).getKey());
        assertEquals(valueOf(3), colValues.get(1).getKey());
        assertEquals(valueOf(4), colValues.get(2).getKey());
        assertEquals(valueOf(11), colValues.get(3).getKey());
        assertEquals(valueOf(18), colValues.get(4).getKey());
        assertEquals(valueOf(21), colValues.get(5).getKey());

    }

    @Test
    public void testDelete() {

        MyBST<Integer,Integer> laPlantaEnCuestion = new MyBSTImpl(null);


        try {
            laPlantaEnCuestion.insert(3,3);
            laPlantaEnCuestion.insert(21, 21);
            laPlantaEnCuestion.insert(11, 11);
            laPlantaEnCuestion.insert(-1, -1);
            laPlantaEnCuestion.insert(4, 4);
            laPlantaEnCuestion.insert(18, 18);
        } catch (NodeAlreadyExists e) {
            throw new RuntimeException(e);
        }

        try {
            laPlantaEnCuestion.delete(3);
            laPlantaEnCuestion.delete(-1);
            laPlantaEnCuestion.delete(18);

        } catch (NodeNotFound e) {
            throw new RuntimeException(e);
        }


        MyList<NodeBST> colValues = laPlantaEnCuestion.inOrder();


        assertEquals(valueOf(4), colValues.get(0).getKey());
        assertEquals(valueOf(11), colValues.get(1).getKey());
        assertEquals(valueOf(21), colValues.get(2).getKey());

    }

    @Test
    public void otroTestDelete() {

        MyBSTImpl laPlantaEnCuestion = new MyBSTImpl(null);


        try {
            laPlantaEnCuestion.insert(3, 3);
            laPlantaEnCuestion.insert(-1, -1);
            laPlantaEnCuestion.insert(4, 4);
            laPlantaEnCuestion.insert(18, 18);
        } catch (NodeAlreadyExists e) {
            throw new RuntimeException(e);
        }

        try {
            laPlantaEnCuestion.delete(3);
            laPlantaEnCuestion.delete(-1);
            laPlantaEnCuestion.delete(18);
            laPlantaEnCuestion.delete(4);
        } catch (NodeNotFound e) {
            throw new RuntimeException(e);
        }


        MyList<NodeBST> colValues = laPlantaEnCuestion.inOrder();

        assertEquals(0, colValues.size());

    }

    @Test
    public void testFind() {
        MyBST<Integer, Integer> oTree = new MyBSTImpl(null);

        try {
            oTree.insert(3, 3);
            oTree.insert(21, 21);
            oTree.insert(11, 11);
            oTree.insert(-1, -1);
            oTree.insert(4, 4);
            oTree.insert(18, 18);


            oTree.delete(3);
            oTree.delete(-1);
            oTree.delete(18);

        } catch (NodeAlreadyExists | NodeNotFound e) {
            throw new RuntimeException(e);
        }


        Integer oTemp = null;

//  Este test no lo pongo porque cuando no encuentra el nodo le puse para que tire una excepcion y no devuelva null
//        try {
//            oTemp = oTree.find(3);
//        } catch (NodeNotFound e) {
//            throw new RuntimeException(e);
//        }
//
//        assertNull(oTemp);

        try {
            oTemp = oTree.find(21);
        } catch (NodeNotFound e) {
            throw new RuntimeException(e);
        }

        assertEquals(valueOf(21), oTemp);

    }

    @Test
    public void testContains() {

        MyBST<Integer, Integer> oTree = new MyBSTImpl(null);

        try {
            oTree.insert(3, 3);
            oTree.insert(21, 21);
            oTree.insert(11, 11);
            oTree.insert(-1, -1);
            oTree.insert(4, 4);
            oTree.insert(18, 18);
        } catch (NodeAlreadyExists e) {
            throw new RuntimeException(e);
        }

        assertFalse(oTree.contains(33));
        assertTrue(oTree.contains(-1));
    }

}
