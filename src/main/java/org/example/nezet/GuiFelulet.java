package org.example.nezet;

import org.example.modell.GuiConfigModell;
import org.example.modell.Gyujtemeny;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GuiFelulet {
    JFrame frame;
    private JMenuItem mnuBeolvas;
    private JMenuItem mnuMentes;
    private JMenuItem mnuKilepes;


    private JComboBox comboBox1;
    private JButton ujSzoborButton;
    private JButton masolasButton;
    private JList list1;
    private JCheckBox mozgatCheckBox;
    private JPanel panel;

    public static void main(String[] args) {
        new GuiFelulet();
    }
    public GuiFelulet() {
        ini();
        mnuBeolvas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jf = new JFileChooser(new File(System.getProperty("user.dir")));
                if(jf.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
                    File fajl = jf.getSelectedFile();
                    try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fajl))) {
                        GuiConfigModell g=(GuiConfigModell) ois.readObject();
                        mozgatCheckBox.setSelected(g.isChbMozgat());
                        DefaultComboBoxModel<String> dlm = (DefaultComboBoxModel<String>) comboBox1.getModel();
                        for (String s :g.getComboSzovegek()){
                            dlm.addElement(s);
                        }
                    }catch (FileNotFoundException ex){
                        throw new RuntimeException();
                    }catch (IOException ex){
                        throw new RuntimeException();
                    }catch (ClassNotFoundException ex){
                        throw new RuntimeException();
                    }
                }
            }
        });
        mnuMentes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jf = new JFileChooser(new File(System.getProperty("user.dir")));
                if(jf.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
                    File fajl = jf.getSelectedFile();
                    try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fajl))) {
                        GuiConfigModell modell = new GuiConfigModell();
                        modell.setChbMozgat(mozgatCheckBox.isSelected());

                        List<String> cl = new ArrayList<>();
                        for (int i = 0; i < comboBox1.getItemCount(); i++) {
                            cl.add((String) comboBox1.getItemAt(i));
                        }
                        modell.setComboSzovegek(cl);
                        List<String> ll = new ArrayList<>();
                        ListModel lm = list1.getModel();
                        for (int i = 0; i < lm.getSize(); i++) {
                            ll.add((String) lm.getElementAt(i));
                        }
                        modell.setListSzovegek(ll);
                        oos.writeObject(modell);
                    }catch (FileNotFoundException ex){
                        System.err.println("mentés: Nincs meg a fájl: " + ex.getMessage());
                        ex.printStackTrace();
                    }catch (IOException ex){
                        System.err.println("mentés: I/O hiba: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            }
        });
        mnuKilepes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = "Biztos bezárod?";
                String cim = "Kilépés";
                int jp = JOptionPane.showConfirmDialog(null, msg, cim, JOptionPane.YES_NO_OPTION);
                if (jp == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        ujSzoborButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               comboBox1.addItem("Ismeretlen:Nike/márvány -2.sz");
            }
        });

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String elem = (String) comboBox1.getSelectedItem();
                DefaultListModel<String> lm = (DefaultListModel<String>) list1.getModel();
                lm.addElement(elem);
                if (mozgatCheckBox.isSelected()){
                    comboBox1.removeItem(comboBox1.getSelectedItem());
                }
            }
        });

        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getClickCount() == 2){ //dupla klikk
                    /* törlés list-ből */
                    String elem = (String) list1.getSelectedValue();
                    DefaultListModel dlm = (DefaultListModel) list1.getModel();
                    dlm.removeElement(elem);
                }
            }
        });

    }

    private void ini() {
        frame = new JFrame("Dolgozat gui");
        frame.setContentPane(panel);
        frame.setSize(350, 250);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        //Jlist
        DefaultListModel<String> lm = new DefaultListModel<>();
        list1.setModel(lm);

        //Menü
        mnuBeolvas = new JMenuItem("Beolvasás");
        mnuMentes = new JMenuItem("Mentés");
        mnuKilepes = new JMenuItem("Kilépés");
        JMenu mnu = new JMenu("Menük");
        mnu.add(mnuBeolvas);
        mnu.add(new JSeparator());
        mnu.add(mnuMentes);
        mnu.add(new JSeparator());
        mnu.add(mnuKilepes);
        JMenuBar mnuBar = new JMenuBar();
        mnuBar.add(mnu);
        frame.setJMenuBar(mnuBar);
        frame.setVisible(true);
    }
}
