package object;
import DoubleLinkedList.*;
import java.io.Serializable;
public class Word implements theWord {//an object that represents a word inside a text
    
    long serial;
    String theWord;// the word itself without any attributes
    int length;//length attribute of the word
    int begin;// an attribute that shows where this word starts in a text
    int end;// an attribute that shows where this word ends in a text
    DoubleLinkedList prob;// a dll that contains all the probable words that could be changed with this word

    //initialiser that it creates an object with the attributes "the word itself",where it begins, where it ends, and the dll with the probable words
    public Word(String theWord, int begin, int end,DoubleLinkedList dll) {
        this.theWord = theWord;
        this.length = theWord.length();
        this.begin = begin;
        this.end = end;
        this.prob=dll;
    }
    
    public Word(int serial,String theWord,int length,int begin,int end,DoubleLinkedList dll){
        this.serial=serial;
        this.theWord=theWord;
        this.length=length;
        this.begin=begin;
        this.end=end;
        this.prob=dll;
    }
    
    //initialiser that creates an object with the attributes "the word itself",where it begins inside the text, where it ends inside the text
    public Word(String theWord,int begin,int end){
        this.theWord=theWord;
        this.begin=begin;
        this.end=end;
    }

    //initialiser without any parameters
    public Word() {
    }

    //initialiser that creates an object of th word with the "word itself", the dll with the probable words
    public Word(String theWord, DoubleLinkedList dll) {
        this.theWord = theWord;
        this.prob=dll;
    }

    //initialiset that creates a word without any attributes but the word itself
    public Word(String theWord) {
        this.theWord = theWord;
    }
    
    public Word(long serial,String theWord) {
        this.serial=serial;
        this.theWord = theWord;
    }


    public long getSerial() {
        return serial;
    }

    public void setSerial(long serial) {
        this.serial = serial;
    }

    public DoubleLinkedList getProb() {
        return prob;
    }

    public void setProb(DoubleLinkedList prob) {
        this.prob = prob;
    }
    
    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getTheWord() {
        return theWord;
    }

    public void setTheWord(String theWord) {
        this.theWord = theWord;
    }

    public int getLength() {
        return this.theWord.length();
    }

    public void setList(DoubleLinkedList dll) {
        prob=dll;
    }

    public DoubleLinkedList getList() {
        return prob;
    }

    @Override
    public String toString() {
        return "Word{ serial="+serial + ",theWord=" + theWord + ", length=" + length + ", begin=" + begin + ", end=" + end + '}';
    }
}
