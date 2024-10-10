# Sette E Mezzo Demo
![Java](https://img.shields.io/badge/Java-17-blue.svg)
![Maven](https://img.shields.io/badge/Maven-3.6.3-blue.svg)
![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)
![Issues](https://img.shields.io/github/issues/Riccardohihello/sette_e_mezzo)

**Sette e Mezzo** è una simulazione del classico gioco di carte italiano, sviluppato in Java. Il progetto implementa la logica di gioco, la gestione dei giocatori (sia umani che computerizzati) e include funzionalità come il salvataggio e il caricamento delle sessioni di gioco.

## Indice

- [Installazione](##installazione)
- [Guida Rapida](##guida-rapida)
- [Regole del Gioco](##regole-del-gioco)
- [Funzionalità](##funzionalità)
- [Struttura del Progetto](##struttura-del-progetto)
- [Licenza](##licenza)

## Installazione
### Prerequisiti

- **Java JDK 11** o superiore installato sul tuo sistema
- **Apache Maven** installato (vedi sotto)
- **IDE** raccomandato: IntelliJ IDEA

#### Installare Maven
Per installare Maven, segui questi passaggi:

**Linux**:
``` bash
sudo apt update
sudo apt install maven
```

macOS (utilizzando Homebrew):

``` bash
brew install maven
```

Windows: Scarica Maven da [qui](https://maven.apache.org/download.cgi) e segui la guida di installazione.

### Clonare il Repository

Per ottenere il codice sorgente, clona questo repository utilizzando Git:

```bash
git clone https://github.com/Riccardohihello/sette_e_mezzo.git
```

### Compilare
```bash
cd sette_e_mezzo
mvn clean install
mvn dependency:copy-dependencies
```
### Eseguire

Dopo aver compilato il progetto, puoi eseguire il gioco dalla cartella principale:
``` bash
mvn javafx:run
```
Oppure con:
``` bash
java --module-path target/dependency --add-modules javafx.controls,javafx.fxml -cp target/sette_e_mezzo-1.0-SNAPSHOT.jar it.uniparthenope.sette_e_mezzo.Main
```

## Guida Rapida del gioco
1. **Nuova Partita**: Avvia una nuova partita, puoi selezionare il numero di giocatori e modificarne i nomi.
2. **Salva/Carica Partita**: Puoi salvare lo stato del gioco e riprendere da dove avevi lasciato.
3. **Statistiche Computer**: Visualizza quante vittorie ha ottenuto il computer.
4. **Statistiche**: Alla fine della partita, vengono mostrate le statistiche dei giocatori.

## Struttura del Progetto

``` less
src/
└── main/
    └── java/
        └── it/uniparthenope/sette_e_mezzo/
            ├── Game        // Logica principale del gioco
            ├── Player      // Rappresenta un giocatore
            ├── Deck        // Gestisce il mazzo di carte
            ├── Card        // Rappresenta una singola carta
            ├── GameUI      // Interfaccia utente
            ├── Memento     // Gestisce il salvataggio/caricamento dello stato del gioco
            └── Main.java   // Punto di ingresso del programma

```

## © Licenza
Questo progetto è distribuito sotto licenza [MIT](https://choosealicense.com/licenses/mit/). Consulta il file LICENSE per maggiori informazioni.
