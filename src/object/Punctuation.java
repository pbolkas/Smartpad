/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;
/**
 *
 * @author Παύλος Μπόλκας
 */
public class Punctuation implements punctuate {//object that represents a punctuation in the text
    long serial;
    private char character;//what is the ascii character of the punctuation
    private int position;//where is the position inside the text

    //initialiser of the object where it must be given the character and the position of the punctuation
    public Punctuation(char character, int position) {
        this.character = character;
        this.position = position;
    }
    public Punctuation(int serial,char character, int position){
        this.serial=serial;
        this.character=character;
        this.position=position;
    }

    public long getSerial() {
        return serial;
    }

    public void setSerial(long serial) {
        this.serial = serial;
    }
    
    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    
    @Override
    public String toString() {
        return "Punctuation{ serial="+serial + ",character='" + character + "', position='" + position + "'}";
    }
    
    
}
