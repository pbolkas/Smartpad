/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import DoubleLinkedList.*;
import Utilities.Utilities;

/**
 *
 * @author Παύλος
 */
public class Text implements theText {//object of the whole text that contains words and punctuation

    DoubleLinkedList words = new DoubleLinkedList();//dll of words of the Text
    DoubleLinkedList punctuation = new DoubleLinkedList();//dll of the punctuation of the Text
    DoubleLinkedList theText = new DoubleLinkedList();//dll to store all the text together
    Utilities utils = new Utilities();

    public Text() {

    }

    public Text(String text) {

    }

    public Text(DoubleLinkedList words, DoubleLinkedList punctuation, DoubleLinkedList theText) {
        this.words = words;
        this.punctuation = punctuation;
        this.theText = theText;
    }

    public Text seperated(String text) {
        int count = 0;
        text += " ";
        char crnt;
        String word = "";
        for (int i = 0; i < text.length(); i++) {
            crnt = text.charAt(i);
            if (Utilities.charIn(crnt)) {
                if (word.length() > 0) {
                    Word thisWord = new Word(count, word, word.length(), i - word.length(), i - 1, utils.probableWords(new Word(count, word)));
                    this.words.insertLast(thisWord);
                    word = "";
                    this.theText.insertLast(thisWord);
                    count++;
                }
                this.punctuation.insertLast(new Punctuation(count, crnt, i));
                this.theText.insertLast(new Punctuation(count, crnt, i));
                count++;
            } else {
                word += crnt;
            }

        }

        return new Text(this.words, this.punctuation, this.theText);
    }

    public Text substitute(Word theWord, Text theText) {
        DoubleNode node = this.theText.getFirstNode();
        int size = this.theText.size();
        for (int i = 0; i < size; i++) {
            if (node.getItem() instanceof Word) {
                if (((Word) node.getItem()).serial == theWord.serial) {
                    node.setItem(new Word(theWord.serial, theWord.getTheWord()));
                }
            } else if (node.getItem() instanceof Punctuation) {

            }
            node = node.getNext();
        }
        return theText;
    }

    public String getPureText() {
        String text = "";
        int length = this.theText.size();
        DoubleNode node = this.theText.getFirstNode();
        for (int i = 0; i < length; i++) {
            if (node.getItem() instanceof Word) {
                text += ((Word) node.getItem()).theWord;
            } else if (node.getItem() instanceof Punctuation) {
                text += ((Punctuation) node.getItem()).getCharacter();
            }
            node = node.getNext();
        }

        return text.substring(0, text.length() - 1);
    }

    public void instantCorrect() {
        
    }

    public void setWords(DoubleLinkedList dll) {
        this.words = dll;
    }

    public DoubleLinkedList getWords() {
        return this.words;
    }

    public void setPunctuation(DoubleLinkedList dll) {
        this.punctuation = dll;
    }

    public DoubleLinkedList getPunctuation() {
        return this.punctuation;
    }

    public DoubleLinkedList getTheText() {
        return this.theText;
    }

    public void setTheText(DoubleLinkedList theText) {
        this.theText = theText;
    }

    public static void main(String args[]) {
        String text = "The earliest known appearance of the phrase is from The Michigan School Moderator, a "
                + "journal that provided many teachers with education-related news and suggestions for lessons.";
        
        text="palvos";
        Text tt = new Text(text);

        Text sep = tt.seperated(text);

        DoubleLinkedList wdll = sep.getWords();
        DoubleLinkedList pdll = sep.getPunctuation();
        DoubleLinkedList tdll = sep.getTheText();

        wdll.printList();
        //pdll.printList();
        //tdll.printList();

        /*sep.substitute(new Word(0, "pavlos"), sep);

        tdll = sep.getTheText();
        tdll.printList();*/
    }

}
