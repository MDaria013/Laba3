package com.company;

import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewGUI extends JFrame{
    private JPanel panel1;
    private JPanel panel2;
    private JTree tree1;
    private JButton выбратьФайлButton;
    private JButton показатьДанныеButton;
    private JScrollPane scrollPane;
    Reader reader = new Reader();
    ArrayList<Reactors> Reactors = new ArrayList<>();
    private JFileChooser fileChooser = new JFileChooser("C:\\Users\\User\\IdeaProjects\\Laba3.1\\file");


    public NewGUI() {
        DefaultMutableTreeNode treeNode1 = new DefaultMutableTreeNode("Reactors");
        tree1.setModel(new DefaultTreeModel(treeNode1));
        scrollPane.setViewportView(tree1);

        выбратьФайлButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int returnvalue = fileChooser.showOpenDialog(NewGUI.this);
                    if (returnvalue == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        try {
                            Reactors = reader.Read(fileChooser.getSelectedFile());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(JFrame.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ParseException ex) {
                            Logger.getLogger(JFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                } catch (java.awt.HeadlessException HeadlessException) {
                    JOptionPane.showMessageDialog(null, "Not found", "Error", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });


        показатьДанныеButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    reader.Tree(tree1, Reactors);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Данные о реакторах не записаны", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


    }

    public JPanel getJPanel(){
        return  panel1;
    }




}
