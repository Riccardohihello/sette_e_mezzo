package it.uniparthenope.programmazione3.memento;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Caretaker {
    private final String saveFilePath = "storico_partite/";
    private final List<Memento> mementoList = loadFromDisk(saveFilePath);


    public void add(Memento memento) throws IOException {
        System.out.println("Size:: " + mementoList.size());

        for (int i=0;i<mementoList.size();i++) {
            System.out.println("X: "+mementoList.get(i).getSettings().getSaveDateTime().toString() + " è uguale a : "+ memento.getSettings().getSaveDateTime().toString() + " ? ");

            if (mementoList.get(i).getSettings().getSaveDateTime().equals(memento.getSettings().getSaveDateTime())) {
                System.out.println("Partita uguale trovata.");
                mementoList.remove(i);
                break;
            }
        }
        memento.getSettings().setSaveDateTime();
        mementoList.add(memento);
        saveOnDisk(saveFilePath);
    }

    public Memento getMemento(int index) {
        return mementoList.get(index);
    }
    public List<Memento> getMementoList() {return mementoList;}

    public void saveOnDisk(String path) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path + "Partita_"+(mementoList.size())+".txt"))) {
            oos.writeObject(mementoList.get(mementoList.size()-1));
        }
    }
    public List<Memento> loadFromDisk(String path) {

        List<Memento> mementoList = new ArrayList<>();

        File file = new File(path);

        File[] files = file.listFiles();
        if(files != null) {
            for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
                if (files[i].isFile()) { //this line weeds out other directories/folders
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(files[i]))) {
                        mementoList.add((Memento) ois.readObject());
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return mementoList;
    }

}
