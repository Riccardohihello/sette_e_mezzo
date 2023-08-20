package it.uniparthenope.programmazione3.model;

public class GestoreMazzo {
    public final Mazzo mazzo;

    public GestoreMazzo() {
        mazzo = Mazzo.creaMazzo(); // Esempio, crea il mazzo
    }

    public void mischiaMazzo() {
        mazzo.mischia(); // Esempio, mischia il mazzo
    }

    public Mazzo getMazzo() {
        return mazzo;
   }


}