package org.example.nezet;

import org.example.modell.Gyujtemeny;
import org.example.modell.Kategoriak;

import java.io.*;
import java.util.ArrayList;

public class Konzol{
    private static final String EGY_GYUJTEMENY = "egygyujtemeny.dat";
    private ArrayList<String> gyujtemenyek;

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
        System.out.println("1 műkincs kiírva: " + k0);
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
