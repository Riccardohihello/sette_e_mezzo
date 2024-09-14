package it.uniparthenope.sette_e_mezzo.memento;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Caretaker {
    // classe per gestire i salvataggi
    private final String saveFilePath = "src/main/resources/it/uniparthenope/sette_e_mezzo/storico_partite/";
    private final List<Memento> mementoList = loadFromDisk(saveFilePath);


    public void add(Memento memento) throws IOException {
        for (int i=0;i<mementoList.size();i++)
            if (mementoList.get(i).getSettings().getSaveDateTime().equals(memento.getSettings().getSaveDateTime())) {
                System.out.println("Partita uguale trovata.");
                mementoList.remove(i);
                break;
            }
        memento.getSettings().setSaveDateTime();
        mementoList.add(memento);
        saveOnDisk(saveFilePath);
    }

    public List<Memento> getMementoList() {return mementoList;}

    // salvataggio dei memento
    public void saveOnDisk(String path) throws IOException {

        File directory = new File(path);
        if (!directory.exists())
            if (directory.mkdirs())
                System.out.println("Directory creata: " + path);
            else
                throw new IOException("Impossibile creare la directory: " + path);

        if (!mementoList.isEmpty())
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path + "/Partita_" + mementoList.size() + ".txt"))) {
                oos.writeObject(mementoList.get(mementoList.size() - 1));
                System.out.println("Partita salvata con successo.");
            } catch (IOException e) {
                System.err.println("Errore durante il salvataggio della partita: " + e.getMessage());
                e.printStackTrace();
            }
        else
            System.out.println("Nessuna partita da salvare. La lista dei memento è vuota.");
    }
    public List<Memento> loadFromDisk(String path) {

        List<Memento> mementoList = new ArrayList<>();

        File file = new File(path);

        File[] files = file.listFiles();
        if(files != null)
            for (int i = 0; i < Objects.requireNonNull(files).length; i++)
                if (files[i].isFile())
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(files[i]))) {
                        mementoList.add((Memento) ois.readObject());
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Impossibile caricare il file: " + e);
                    }
        return mementoList;
    }

}
