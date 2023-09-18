package it.uniparthenope.programmazione3.observerPattern;

import it.uniparthenope.programmazione3.model.StatistichePartita;
public interface Observer {

     void update(StatistichePartita o, String label);

}