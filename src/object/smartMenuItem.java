package object;

import DoubleLinkedList.DoubleLinkedList;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;

/**
 *
 * @author Pavlos
 */
public class smartMenuItem extends JMenuItem{
    
    private Word prob;
    private int previousLength;
    public smartMenuItem(Word prob, String text) {
        super(text);
        this.prob = prob;
        
    }

    public smartMenuItem(Word word, String theWord, int length) {
        super(theWord);
        this.prob=word;
        previousLength=length;
        
    }

    public int getPreviousLength() {
        return previousLength;
    }

    public void setPreviousLength(int previousLength) {
        this.previousLength = previousLength;
    }
    

    public Word getProb() {
        return prob;
    }

    public void setProb(Word prob) {
        this.prob = prob;
    }
    
    public String toString(){
        return this.prob.theWord;
    }
}
