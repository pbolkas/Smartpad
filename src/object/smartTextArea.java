/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;
import DoubleLinkedList.DoubleLinkedList;
import DoubleLinkedList.DoubleNode;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;
import object.Text;

public class smartTextArea extends JTextArea{
    private Text theText;
    
    
    public smartTextArea(){}
    
    public smartTextArea(Text theText) {
        this.theText = theText;
    }

    
    public smartTextArea(Text theText, StyledDocument doc) {
        super(doc);
        this.theText = theText;
        this.setText(theText.getPureText());
    }

    public Text getTheText() {
        return theText;
    }

    public void setTheText(Text theText) {
        this.theText = theText;
    }
    
    
    //update text
    public void updateText(){
        this.setText(this.theText.getPureText());
    }

    //this method changes the word in the right serial with "theWord"
    public void changeTheWrong(long serial, String theWord) {
        this.theText.substitute(serial, theWord);
    }
    
    
    
    
    
}
