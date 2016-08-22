/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import object.Text;
import object.Word;

/**
 *
 * @author Παύλος Μπόλκας
 */
public class MListen implements MouseListener {

    public JPopupMenu menu;
    public Text text;

    public MListen(JPopupMenu menu, Text text) {
        this.menu = menu;
        this.text = text;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        e.getLocationOnScreen();

        JMenuItem item = (JMenuItem) menu.getComponentAt(e.getPoint());
        System.out.println(item.getText());
        //Word w = ((Word) item.getClientProperty(((Word))));

        // System.out.println(w);
        if (e.getButton() == 3) {
            menu.setVisible(false);

        } else if (e.getButton() == 1) {

            Object w1 = item.getClientProperty(item.getText());
            if (w1 instanceof Word) {

               // System.out.println("Begin=" + w.getBegin() + " End=" + w.getEnd());
                JTextArea a = new JTextArea();

            }
            System.out.print(((Word) w1).getTheWord());

            menu.setVisible(false);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        /*e.getLocationOnScreen();
         JMenuItem item = (JMenuItem) menu.getComponentAt(e.getPoint());

         String w = ((Word) item.getClientProperty(item.getText())).getTheWord();

         System.out.println(w);
         System.out.println("pressed");
         */
    }

    @Override
    public void mouseReleased(MouseEvent e) {/*
         e.getLocationOnScreen();
         JMenuItem item = (JMenuItem) menu.getComponentAt(e.getPoint());

         String w = ((Word) item.getClientProperty(item.getText())).getTheWord();
         System.out.println(w);
         System.out.println("released");
         //System.out.println(e.getX()+"=x   |   "+e.getY()+"=y             |released");*/

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Point p = e.getLocationOnScreen();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        e.getLocationOnScreen();
        //System.out.println(e.getX()+"=x   |   "+e.getY()+"=y             |exited");
    }
}
