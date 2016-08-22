package DoubleLinkedList;

import object.*;

public class DoubleLinkedList implements List {

    private DoubleNode firstNode, lastNode;

    public DoubleNode getFirstNode() {
        return firstNode;
    }

    @Override
    public void setFirstNode(DoubleNode firstNode) {
        this.firstNode = firstNode;
    }

    public DoubleNode getLastNode() {
        return lastNode;
    }

    @Override
    public void setLastNode(DoubleNode lastNode) {
        this.lastNode = lastNode;
    }

    public DoubleLinkedList() {
        firstNode = lastNode = null;
    }

    @Override
    public boolean isEmpty() {
        return firstNode == null;
    }

    @Override
    public int size() {
        DoubleNode current = firstNode;
        int i = 0;
        while (current != null) {
            i++;
            current = current.getNext();
        }
        return i;
    }

    @Override
    public void insertFirst(Object data) {
        if (isEmpty()) {
            lastNode = firstNode = new DoubleNode(data, null, null);
        } else {
            DoubleNode newNode = new DoubleNode(data, firstNode, null);
            firstNode.setPrevious(newNode);
            firstNode = newNode;
        }
    }

    @Override
    public void insertAny(Object data, int position) throws NoSuchListPositionException {
        if (position == 0) {
            insertFirst(data);
        } else if (position == size()) {
            insertLast(data);
        } else if (position < 0 || position > size()) {
            throw new NoSuchListPositionException("This position does not exist");
        } else {
            DoubleNode current = firstNode;
            int i = 0;
            while (current != null) {
                if (i == position) {
                    DoubleNode newNode = new DoubleNode(data, current.getNext(), current);
                    current.getNext().setPrevious(newNode);
                    current.setNext(newNode);
                    break;
                }
                current = current.getNext();
                i++;
            }
        }
    }

    @Override
    public void insertLast(Object data) {
        if (isEmpty()) {
            lastNode = firstNode = new DoubleNode(data, null, null);
        } else {
            DoubleNode newNode = new DoubleNode(data, null, lastNode);
            lastNode.setNext(newNode);
            lastNode = newNode;
        }
    }

    @Override
    public Object removeFirst() throws ListEmptyException {
        Object removeItem;
        if (isEmpty()) {
            throw new ListEmptyException("Empty list");
        }
        removeItem = firstNode.getItem();
        if (firstNode == lastNode) {
            firstNode = lastNode = null;
        } else {
            DoubleNode b = firstNode.getNext();
            firstNode.setNext(null);
            b.setPrevious(null);
            firstNode = b;
        }
        return removeItem;
    }

    @Override
    public Object removeLast() throws ListEmptyException {
        Object removeItem;
        if (isEmpty()) {
            throw new ListEmptyException("Emty list");
        }
        removeItem = lastNode.getItem();
        if (firstNode == lastNode) {
            firstNode = lastNode = null;
        } else {
            DoubleNode b = lastNode.getPrevious();
            lastNode.setPrevious(null);
            b.setNext(null);
            lastNode = b;
        }
        return removeItem;
    }

    @Override
    public void printList() {
        DoubleNode current = firstNode;
        while (current != null) {
            System.out.println(current.getItem());
            current = current.getNext();
        }
    }

    public boolean exists(Object toAdd) {
        DoubleNode current = firstNode;
        while (current != null) {
            if (((Word)current.getItem()).getTheWord().equals(toAdd)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
    
    public static void main(String args[]) {
        DoubleLinkedList dll = new DoubleLinkedList();

        dll.insertFirst("a");
        dll.insertFirst("e");
        dll.insertFirst("d");
        dll.insertFirst("c");
        dll.insertFirst("b");
        
        System.out.println(dll.exists("a"));
    }

}
