package Utilities;

import jpl.*;//library to connect with prolog
import object.*;//library of objects punctuation,text,word
import DoubleLinkedList.*;//library for the doublelinkedlist implementation
import java.awt.Component;
import java.awt.Image;//library to show images
import java.io.*;//input output library
import javax.swing.ImageIcon;//library for the icons
import javax.swing.JTextArea;//library for the area where tha user types
import jpl.fli.Prolog;//library for prolog connection


public class Utilities {

    
    //being used
    //takes a word from the text and makes all the necessary work to check it with prolog and returns it corrected or not
    public String checkOne(String word) {
        return makeItReady(checkWithPl(makeItList(word)));
    }

    //being used
    public static ImageIcon turnImage(ImageIcon i, Component l) {
        //turns the image to the size of the label so that it can be shown the whole
        Image im = i.getImage();
        Image newImg = im.getScaledInstance(l.getWidth(), l.getHeight(), java.awt.Image.SCALE_SMOOTH);

        ImageIcon newIcon = new ImageIcon(newImg);
        return newIcon;
    }

    //under construction
    public static void setPrintView(Component p) {
        int width = p.getWidth();
        int height = p.getHeight();

        int newWidth = width - (width * 20 / 100);
        int newLeftPos = (width * 20 / 100) / 2;
        p.setSize(newWidth, height);
        p.setBounds(newLeftPos, p.getY(), newWidth, height);

    }

    //being used
    //checks for any punctuation to take ti apart the text
    public static boolean charIn(char toCheck) {
        char punct[] = {'.', ' ', '?', '!', ',', '/', ';', '/', '(', ')', '[', ']', '{', '}', '\'', '~', '@', '#',
            '$', '%', '^', '&', '*', '-', '_', '+', '=', '<', '>', '"', '|', '\n', '«', '»', ':', '€', '©', '®'};
        for (int i = 0; i < punct.length; i++) {
            if (punct[i] == toCheck) {
                return true;
            }
        }
        return false;

    }

    //being used
    public String thirdCor(String initText) {
        if (initText.equalsIgnoreCase("")) {
            return initText;
        }
        System.out.println(initText);
        initText += " ";
        DoubleLinkedList text = new DoubleLinkedList();
        String word = "";
        for (int i = 0; i < initText.length(); i++) {
            char cur = initText.charAt(i);
            if (charIn(cur)) {
                if (word.length() > 0) {
                    text.insertLast(new Word(word));
                }
                text.insertLast(new Punctuation(cur, i));
                word = "";
            } else {
                word += cur;
            }
        }
        String newText = "";
        text.removeLast();
        DoubleNode node = text.getFirstNode();
        for (int i = 0; i < text.size(); i++) {
            if (node.getItem() instanceof Word) {

                node.setItem(new Word(makeItReady(checkWithPl(makeItList(((Word) (node.getItem())).getTheWord())))));
                newText += ((Word) node.getItem()).getTheWord();
            } else if (node.getItem() instanceof Punctuation) {
                newText += (((Punctuation) node.getItem()).getCharacter());
            }

            node = node.getNext();
        }
        text.printList();
        System.out.println(newText);
        return newText;
    }

    //method that will probably find correct words of another word
    public  DoubleLinkedList probableWords(Word w) {
        
        try {
            DoubleLinkedList list = new DoubleLinkedList();//new list that will return tha list with the probable words
            Query q = new Query("consult", new Term[]{new Atom("..\\Smartpad\\cor.pl")});
            System.out.println("consult " + (q.query() ? "succeeded" : "failed"));

            String toAdd = "";
            String text = makeItList(w.getTheWord());
            String rule = "cword(" + text + ",X).";

            Query q1 = new Query(rule);
            java.util.Hashtable result = q1.oneSolution();
            if (q1.hasSolution()) {
                System.out.println("returned unchanged");
                return list;
            }

            rule = "swapletters(" + text + ",X).";
            q1 = new Query(rule);
            result = q1.oneSolution();
            if (q1.hasSolution()) {
                toAdd = makeItReady(result.get("X").toString());
                if (!(list.exists(toAdd))) {
                    list.insertFirst(new Word(w.getSerial(),toAdd,w.getLength(), w.getBegin(), w.getEnd()));
                    
                }
            }

            rule = "exchangeletters(" + text + ",X).";
            q1 = new Query(rule);
            result = q1.oneSolution();
            if (q1.hasSolution()) {
                toAdd = makeItReady(result.get("X").toString());
                if (!(list.exists(toAdd))) {
                    list.insertFirst(new Word(w.getSerial(),toAdd,w.getLength(), w.getBegin(), w.getEnd()));
                }
            }

            rule = "correct(" + text + ",X).";
            q1 = new Query(rule);
            result = q1.oneSolution();
            if (q1.hasSolution()) {
                toAdd = makeItReady(result.get("X").toString());
                if (!(list.exists(toAdd))) {
                    list.insertFirst(new Word(w.getSerial(),toAdd,w.getLength(), w.getBegin(), w.getEnd()));
                }
            }

            rule = "one_more(" + text + ",X).";
            q1 = new Query(rule);
            result = q1.oneSolution();
            if (q1.hasSolution()) {
                toAdd = makeItReady(result.get("X").toString());
                if (!(list.exists(toAdd))) {
                    list.insertFirst(new Word(w.getSerial(),toAdd,w.getLength(), w.getBegin(), w.getEnd()));
                }
            }

            rule = "two_more(" + text + ",X).";
            q1 = new Query(rule);
            result = q1.oneSolution();
            if (q1.hasSolution()) {
                toAdd = makeItReady(result.get("X").toString());
                if (!(list.exists(toAdd))) {
                    list.insertFirst(new Word(w.getSerial(),toAdd,w.getLength(), w.getBegin(), w.getEnd()));
                }
            }

            rule = "char_after(" + text + ",X).";
            q1 = new Query(rule);
            result = q1.oneSolution();

            if (q1.hasSolution()) {
                toAdd = makeItReady(result.get("X").toString());
                if (!(list.exists(toAdd))) {
                    list.insertFirst(new Word(w.getSerial(),toAdd,w.getLength(), w.getBegin(), w.getEnd()));
                }
            }
            return list;
        }catch(Exception e){
            System.out.println("Exception in Utilities.probableWords(Word w)");
            return null;
        }
    }

    //expect to be fast

    public DoubleLinkedList fifthEditionCorrection(String initText) {

        DoubleLinkedList text = new DoubleLinkedList();
        DoubleLinkedList probs = new DoubleLinkedList();

        boolean exists = false;
        initText += " ";
        String word = "";
        String toAdd = "";
        Query q = new Query("consult", new Term[]{new Atom("..\\Smartpad\\cor.pl")});
        String s = ("consult " + (q.query() ? "succeeded" : "failed"));

        for (int i = 0; i < initText.length(); i++) {
            exists = false;
            char cur = initText.charAt(i);
            if (charIn(cur)) {
                if (word.length() > 0) {
                    Word w0 = new Word(word, i - word.length(), i);

                    String rule = "cword(" + makeItList(word) + ",X).";

                    Query q1 = new Query(rule);
                    java.util.Hashtable result = q1.oneSolution();
                    if (q1.hasSolution()) {
                        exists = true;
                    }
                    if (exists == false) {
                        rule = "swapletters(" + makeItList(word) + ",X).";
                        q1 = new Query(rule);
                        result = q1.oneSolution();
                        if (q1.hasSolution()) {
                            toAdd = makeItReady(result.get("X").toString());
                            if (!(probs.exists(toAdd))) {
                                probs.insertFirst(new Word(toAdd, w0.getBegin(), w0.getEnd()));
                            }
                        }
                        rule = "exchangeletters(" + makeItList(word) + ",X).";
                        q1 = new Query(rule);
                        result = q1.oneSolution();
                        if (q1.hasSolution()) {
                            toAdd = makeItReady(result.get("X").toString());
                            if (!(probs.exists(toAdd))) {
                                probs.insertFirst(new Word(toAdd, w0.getBegin(), w0.getEnd()));
                            }
                        }

                        rule = "correct(" + makeItList(word) + ",X).";
                        q1 = new Query(rule);
                        result = q1.oneSolution();
                        if (q1.hasSolution()) {
                            toAdd = makeItReady(result.get("X").toString());
                            if (!(probs.exists(toAdd))) {
                                probs.insertFirst(new Word(toAdd, w0.getBegin(), w0.getEnd()));
                            }
                        }

                        rule = "one_more(" + makeItList(word) + ",X).";
                        q1 = new Query(rule);
                        result = q1.oneSolution();
                        if (q1.hasSolution()) {
                            toAdd = makeItReady(result.get("X").toString());
                            if (!(probs.exists(toAdd))) {
                                probs.insertFirst(new Word(toAdd, w0.getBegin(), w0.getEnd()));
                            }
                        }

                        rule = "two_more(" + makeItList(word) + ",X).";
                        q1 = new Query(rule);
                        result = q1.oneSolution();
                        if (q1.hasSolution()) {
                            toAdd = makeItReady(result.get("X").toString());
                            if (!(probs.exists(toAdd))) {
                                probs.insertFirst(new Word(toAdd, w0.getBegin(), w0.getEnd()));
                            }
                        }

                        rule = "char_after(" + makeItList(word) + ",X).";
                        q1 = new Query(rule);
                        result = q1.oneSolution();

                        if (q1.hasSolution()) {
                            toAdd = makeItReady(result.get("X").toString());
                            if (!(probs.exists(toAdd))) {
                                probs.insertFirst(new Word(toAdd, w0.getBegin(), w0.getEnd()));
                            }
                        }
                    }
                    w0.setList(probs);
                    text.insertLast(w0);
                }
                probs = new DoubleLinkedList();
                text.insertLast(new Punctuation(cur, i));
                word = "";
            } else {
                word += cur;
            }
        }
        text.removeLast();

        return text;
    }

    //active
    //this method takes a word and passes it through a prolog file to take it corrected or not?
    public String checkWithPl(String text) {
        Query q1 = new Query("consult", new Term[]{new Atom("..\\Smartpad\\cor.pl")});//calls a prolog file 'cor.pl' where the dictionary and the ai methods are implemented

        //if the connection with the prolog file is successfull then it will show "succeded" else it will show "failed"
        String s = ("consult " + (q1.query() ? "succeeded" : "failed"));

        
        //it checks the word with the prolog method "cword" to see if it already is a correct word so that it should not be changed
        String query = "cword(" + text + ",X).";
        //create a Query object to call the prolog mechanism with the query String 
        Query q = new Query(query);
        //it asks prolog if there is a solution for the query, if there is one it stores the same word inside a Hashtable res
        java.util.Hashtable res = q.oneSolution();
        //if the word is right has a solution (condition is true)
        if (q.hasSolution()) {
            //it returns the same word because it is true
            return res.get("X").toString();
        } else {//else it tries to find the correct word inside tha prolog "dictionary"
            //create a query to see if there are just two letters side by side in wrong position eg: paul pual it will return paul if this word exist in te prolog file
            query = "swapletters(" + text + ",X).";
            //creates a new instance of the Query object to call the query that we made before
            q = new Query(query);
            //stores the result into a result set
            res = q.oneSolution();
            //if therer is a result
            if (q.hasSolution()) {
                //it will return the "X" that means the corrected word
                return res.get("X").toString();
            } else {//else it will seek another solution
                //create a new query that seeks for two letters mixed together but not side by side eg: paul, plua
                query = "exchangeletters(" + text + ",X).";
                //create a Query object to check the query we made before
                q = new Query(query);
                //store the result inside a resultset
                res = q.oneSolution();
                //if there is a solution to the query
                if (q.hasSolution()) {
                    //returns the "X" with the word corrected
                    return res.get("X").toString();
                } else {//else it seeks for another solution
                    //create a new query to see if there is an unknown letter inside the word that shouldn't be in the word at all eg: paul, pail it will return paul
                    query = "correct(" + text + ",X).";
                    //creates new instance of Query to query the query we made before
                    q = new Query(query);
                    //stores the result of the query to resultset
                    res = q.oneSolution();
                    //if there was a result 
                    if (q.hasSolution()) {
                        //return the word corrected with the right letter in the place of the wrong
                        return res.get("X").toString();
                    } else {//else seek for another solution
                        //create a prolog query to check if the word has one more letter than it should had eg: paul, paulo, it will return paul
                        query = "one_more(" + text + ",X).";
                        //create instance of Query object to make the query
                        q = new Query(query);
                        //store the solution inside the result set
                        res = q.oneSolution();
                        //if the query had a solution
                        if (q.hasSolution()) {
                            //return the "X" that prolog returned
                            return res.get("X").toString();
                        } else {//else seek again
                            //create query to check if the word contains two extra letters eg: paul, pasulo, will return paul
                            query = "two_more(" + text + ",X).";
                            //create instance of Query that will query the query we made before
                            q = new Query(query);
                            //store the result into the resultset
                            res = q.oneSolution();
                            //if there was a result
                            if (q.hasSolution()) {
                                //return the corrected word
                                return res.get("X").toString();
                            } else {//else seek for another solution
                                //check if there are mixed letters inside the word
                                query = "char_after(" + text + ",X).";
                                //create instance of Query to query prolog
                                q = new Query(query);
                                //store the result into resultset
                                res = q.oneSolution();
                                //if thereis a solution
                                if (q.hasSolution()) {
                                    //return the corrected word
                                    return res.get("X").toString();
                                } else {//else return the word itself as there were not any corrections
                                    return text;
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    /**
     * This method turns a word from the file to a list so that it can be
     * checked from prolog
     *
     * @param preList
     * @return
     */
    //works
    public String makeItList(String preList) {
        //creates a string that starts with the character '[' adds every character as 'e' and closes this prolog list with the character ']'
        String make_word_list = "['";
        for (int i = 0; i < preList.length(); i++) {
            if (i < preList.length() - 1) {
                make_word_list += preList.charAt(i) + "','";
            } else if (i == preList.length() - 1) {
                make_word_list += preList.charAt(i) + "']";
            }
        }
        return make_word_list;//returns a word like it is a list in prolog eg "paul" = ['p','a','u','l']
    }

    /**
     * This method removes all the useless things from the list that prolog
     * returns and makes it a string
     *
     * @param pre
     * @return
     */
    //works
    //this method removes all the characters that added for the check with the prolog mechanism and it returns a pure word
    public static String makeItReady(String pre) {
        String newS = "";
        for (int i = 0; i < pre.length(); i++) {
            if ((pre.charAt(i) != '(')
                    && (pre.charAt(i) != ')')
                    && (pre.charAt(i) != '[')
                    && (pre.charAt(i) != ']')
                    && (pre.charAt(i) != '.')
                    && (pre.charAt(i) != '\'')
                    && (pre.charAt(i) != ',')
                    && (pre.charAt(i) != ' ')) {
                newS += pre.charAt(i);
            }
        }
        return newS;
    }

    /**
     * This method inserts a new word to the .pl file
     *
     * @param theWord
     */
    //works
    public void insertToFile(String theWord) {
        String toIns = "word(";
        toIns += makeItList(theWord);
        toIns += ").";
        toIns = toIns.trim();

        Query q1 = new Query("consult", new Term[]{new Atom("..\\Smartpad\\cor.pl")});

        String s = ("consult " + (q1.query() ? "succeeded" : "failed"));
        try {
            Thread.sleep(250);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        Atom list = new Atom(toIns);

        Term word = new Compound("adding", new Term[]{list});

        Query q = new Query(word);

        java.util.Hashtable result = q.oneSolution();

        System.out.println(result);

    }

    // is made to create libraries do NOT remove
    public void insertManyWords(String initText) {
        if (initText.equalsIgnoreCase("")) {
            return;
        }
        initText += " ";
        DoubleLinkedList text = new DoubleLinkedList();
        String word = "";
        for (int i = 0; i < initText.length(); i++) {
            char cur = initText.charAt(i);
            if (charIn(cur)) {
                if (word.length() > 0) {
                    text.insertLast(new Word(word));
                }
                text.insertLast(new Punctuation(cur, i));
                word = "";
            } else {
                word += cur;
            }
        }
        text.removeLast();
        DoubleNode node = text.getFirstNode();
        String word1 = "";
        try {
            FileWriter fw = new FileWriter("language.pl");

            for (int i = 0; i < text.size(); i++) {
                if (node.getItem() instanceof Word) {
                    word1 = "word(";
                    word1 += makeItList((((Word) node.getItem()).getTheWord()));
                    word1 += ").";
                    fw.write(word1);
                    fw.write("\n");

                }

                node = node.getNext();
            }
            fw.close();
        } catch (IOException ioe) {
            System.out.println(ioe.toString());
        }
        Prolog p = new Prolog();
    }

    public static void main(String args[]) {
        Utilities u = new Utilities();
        //u.textToList("palvos teh");
        Prolog p = new Prolog();
        System.out.println(System.getenv());
    }
}
