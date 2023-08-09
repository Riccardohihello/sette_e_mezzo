package it.uniparthenope.programmazione3;

public interface Observer {
    public void update(String label, String args);
    public void partecipanti(Giocatore giocatori,int size);

}