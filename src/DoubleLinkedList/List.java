package DoubleLinkedList;
public interface List {

    void insertAny(Object data, int position) throws NoSuchListPositionException;

    void insertFirst(Object data);

    void insertLast(Object data);

    //void insertSorted(Object data);

    boolean isEmpty();

    //boolean isSorted();

    void printList();

    //Object removeAny(int am) throws ListEmptyException;

    Object removeFirst() throws ListEmptyException;

    Object removeLast() throws ListEmptyException;

    void setFirstNode(DoubleNode firstNode);

    void setLastNode(DoubleNode lastNode);

    int size();

    //void sortTheList();
    
}
class ListEmptyException extends RuntimeException{
    public ListEmptyException(String e){
        super(e);
    }
}
class NoSuchListPositionException extends RuntimeException{
    public NoSuchListPositionException(String e){
        super(e);
    }
}
