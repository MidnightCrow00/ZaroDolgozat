package org.example.nezet;

import org.example.modell.Gyujtemeny;
import org.example.modell.Kategoriak;
import org.example.modell.Szobrok;

import java.io.*;
import java.util.ArrayList;

public class Konzol{
    private static final String EGY_GYUJTEMENY = "egygyujtemeny.dat";
    private ArrayList<String> gyujtemenyek;
    private ArrayList<String> szobrok;

    Gyujtemeny k0 = new Gyujtemeny("Rodin","A gondolkodó", Kategoriak.EREDETI);


    public static void main(String[] args) {
        new Konzol().konzolraIr();
        new Konzol().binFajlKez();
        new Konzol().statisztika();
    }

    private void binFajlKez() {
        fajlbaIr();
    }
    private void statisztika() {

    }

    private void konzolraIr() {
        Szobrok s0 = new Szobrok("Rodin","A gondolkodó",Kategoriak.EREDETI,"Bronz",19);
        Szobrok s2 = new Szobrok("Michel","Dávid",Kategoriak.EREDETI,"Márvány",16);
        Szobrok s3 = new Szobrok("Á","X",Kategoriak.EREDETI,"Réz",18);

        System.out.println("1 műkincs kiírva: " + k0);
        System.out.println("2 műkincs kiírva: " + s0);
        System.out.println("3 műkincs kiírva: " + s2);
        System.out.println("4 műkincs kiírva: " + s3);
        szobrok = new ArrayList<>();
        szobrok.add(String.valueOf(s0));
        szobrok.add(String.valueOf(s2));
        szobrok.add(String.valueOf(s3));
        kiir("Műkincsek listája:");

    }

    private void kiir(String cim) {
        System.out.println(cim);
        szobrok.forEach(System.out::println);
    }

    private void fajlbaIr() {
        kiirGyujetemenyt();
        beolvasGyujetemenyt();
    }

    private void kiirGyujetemenyt() {
        gyujtemenyek = new ArrayList<>();
        gyujtemenyek.add("Rodin");
        try(ObjectOutputStream objKi = new ObjectOutputStream(new FileOutputStream(EGY_GYUJTEMENY))){
            objKi.writeObject(k0);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void beolvasGyujetemenyt() {
        try(ObjectInputStream objBe = new ObjectInputStream(new FileInputStream(EGY_GYUJTEMENY))){
            Gyujtemeny k = (Gyujtemeny) objBe.readObject();
            k.ujIdGeneralas();
            System.out.println("A beolvasott műkincs állapota:");
            System.out.println(k);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


}
