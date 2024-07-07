package org.example.grafica;

import org.example.Regole;

import javax.swing.*; // Importazione delle classi necessarie per l'interfaccia grafica Swing
import java.awt.*; // Importazione delle classi necessarie per la gestione del layout e degli eventi
import java.awt.event.MouseAdapter; // Importazione della classe necessaria per gestire gli eventi del mouse
import java.awt.event.MouseEvent; // Importazione della classe necessaria per gestire gli eventi del mouse
import java.io.*; // Importazione delle classi necessarie per la gestione dei file e della serializzazione
import java.util.*; // Importazione delle classi necessarie per la gestione delle collezioni

public class MainFrame extends JFrame {
    // Dichiarazione delle variabili per i vari componenti dell'interfaccia grafica
    private JComboBox<Integer> comboNumeroDadi;
    private JComboBox<Integer> comboRighe;
    private JComboBox<Integer> comboColonne;
    private JComboBox<Integer> comboNumeroGiocatori;
    private JComboBox<Integer> comboNumeroScale;
    private JComboBox<Integer> comboNumeroSerpenti;
    // CheckBox per selezionare le regole extra
    private JCheckBox checkDivietoDiSosta;
    private JCheckBox checkDoppioSei;
    private JCheckBox checkLancioUnSoloDado;

    // Dichiarazione delle variabili per memorizzare i valori selezionati dall'utente
    private int numeroScale;
    private int numeroSerpenti;
    private int righe;
    private int colonne;
    private int numeroDadi;
    private int numeroGiocatori;

    // Dichiarazione delle mappe per memorizzare le posizioni delle scale e dei serpenti
    private Map<Integer, Integer> scale;
    private Map<Integer, Integer> serpenti;

    // Costruttore della classe MainFrame
    public MainFrame() {
        super("SCALE E SERPENTI"); // Imposta il titolo della finestra

        // Creazione del pannello principale con un layout GridBagLayout
        JPanel pannelloPrincipale = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints(); // Creazione delle constraint per il layout per disporre i componenti nel container
        gbc.insets = new Insets(10, 10, 10, 10); // Imposta i margini tra i componenti
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER; // Centra i componenti

        // Creazione dei pulsanti per creare e caricare una partita
        JButton bottoneCrea = new JButton("Crea partita");
        JButton bottoneCarica = new JButton("Carica partita");

        // Imposto la stessa dimensione per tutti i pulsanti
        Dimension dimensioneBottone = new Dimension(200, 50);
        bottoneCrea.setPreferredSize(dimensioneBottone);
        bottoneCarica.setPreferredSize(dimensioneBottone);

        // Aggiungo ActionListener ai bottoni per aprire altre schermate al click
        bottoneCrea.addActionListener(e -> apriDialogCreaPartita());

        bottoneCarica.addActionListener(e -> caricaPartita());

        // Aggiungo pulsanti al pannello principale
        pannelloPrincipale.add(bottoneCrea, gbc);
        pannelloPrincipale.add(bottoneCarica, gbc);

        // Aggiungo il pannello principale al frame
        getContentPane().add(pannelloPrincipale);

        // Imposta la dimensione del frame, il comportamento di chiusura e la posizione
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra il frame rispetto allo schermo
        setVisible(true); // Rende il frame visibile
    }

    // Metodo per aprire la finestra di dialogo per creare una partita
    private void apriDialogCreaPartita() {
        JDialog dialog = new JDialog(this, "Crea Partita", true);
        dialog.setLayout(new BorderLayout()); // Imposta il layout della finestra di dialogo

        // Crea un pannello per l'input con un layout GridLayout
        JPanel pannelloInput = new JPanel(new GridLayout(0, 2, 10, 10));

        // Definisco le regole

        // Array di stringhe per le etichette dei campi di input
        String[] etichette = {
                "Numero righe", "Numero colonne", "Numero giocatori", "Numero dadi"
        };

        // Ciclo per aggiungere i campi di input al pannello
        for (String etichetta : etichette) {
            pannelloInput.add(new JLabel(etichetta));
            Integer[] opzioni;
            switch (etichetta) {
                case "Numero righe":
                    opzioni = new Integer[]{6, 7, 8, 9, 10}; // Opzioni per il numero di righe
                    comboRighe = new JComboBox<>(opzioni); // Crea una combo box con le opzioni
                    pannelloInput.add(comboRighe); // Aggiunge la combo box al pannello
                    break;
                case "Numero colonne":
                    opzioni = new Integer[]{6, 7, 8, 9, 10}; // Opzioni per il numero di colonne
                    comboColonne = new JComboBox<>(opzioni); // Crea una combo box con le opzioni
                    pannelloInput.add(comboColonne); // Aggiunge la combo box al pannello
                    break;
                case "Numero giocatori":
                    opzioni = new Integer[]{2, 3, 4, 5, 6}; // Opzioni per il numero di giocatori
                    comboNumeroGiocatori = new JComboBox<>(opzioni); // Crea una combo box con le opzioni
                    pannelloInput.add(comboNumeroGiocatori); // Aggiunge la combo box al pannello
                    break;
                case "Numero dadi":
                    opzioni = new Integer[]{1, 2}; // Opzioni per il numero di dadi
                    comboNumeroDadi = new JComboBox<>(opzioni); // Crea una combo box con le opzioni
                    pannelloInput.add(comboNumeroDadi); // Aggiunge la combo box al pannello
                    break;
            }
        }

        dialog.add(pannelloInput, BorderLayout.CENTER); // Aggiunge il pannello di input alla finestra di dialogo

        addDraggableFeature(dialog); // Aggiunge la funzionalità di trascinamento alla finestra di dialogo

        JButton bottoneAvanti = new JButton("Avanti"); // Crea un pulsante "Avanti"
        bottoneAvanti.addActionListener(e -> {
            // Recupera i valori selezionati dall'utente
            righe = (Integer) comboRighe.getSelectedItem();
            colonne = (Integer) comboColonne.getSelectedItem();
            numeroDadi = (Integer) comboNumeroDadi.getSelectedItem();
            numeroGiocatori = (Integer) comboNumeroGiocatori.getSelectedItem();
            dialog.dispose(); // Chiude la finestra di dialogo
            apriDialogScaleSerpenti(); // Apre la finestra di dialogo per il numero di scale e serpenti
        });

        dialog.add(bottoneAvanti, BorderLayout.SOUTH); // Aggiunge il pulsante "Avanti" alla finestra di dialogo
        dialog.setSize(400, 300); // Imposta la dimensione della finestra di dialogo
        dialog.setLocationRelativeTo(this); // Centra la finestra di dialogo rispetto al frame principale
        dialog.setVisible(true); // Rende la finestra di dialogo visibile
    }

    // Metodo per aprire la finestra di dialogo per il numero di scale e serpenti
    private void apriDialogScaleSerpenti() {
        JDialog dialog = new JDialog(this, "Numero di Scale e Serpenti", true); // Crea una finestra di dialogo modale
        dialog.setLayout(new BorderLayout()); // Imposta il layout della finestra di dialogo

        // Crea un pannello per l'input con un layout GridLayout
        JPanel pannelloInput = new JPanel(new GridLayout(0, 2, 10, 10));

        // Aggiunge i campi di input per il numero di scale e serpenti
        pannelloInput.add(new JLabel("Numero di scale:"));
        Integer[] opzioniScaleSerpenti = {0, 1, 2, 3, 4, 5, 6, 7, 8}; // Opzioni per il numero di scale e serpenti
        comboNumeroScale = new JComboBox<>(opzioniScaleSerpenti); // Crea una combo box con le opzioni
        pannelloInput.add(comboNumeroScale); // Aggiunge la combo box al pannello

        pannelloInput.add(new JLabel("Numero di serpenti:"));
        comboNumeroSerpenti = new JComboBox<>(opzioniScaleSerpenti); // Crea una combo box con le opzioni
        pannelloInput.add(comboNumeroSerpenti); // Aggiunge la combo box al pannello

        dialog.add(pannelloInput, BorderLayout.CENTER); // Aggiunge il pannello di input alla finestra di dialogo

        addDraggableFeature(dialog); // Aggiunge la funzionalità di trascinamento alla finestra di dialogo

        JButton bottoneAvanti = new JButton("Avanti"); // Crea un pulsante "Avanti"
        bottoneAvanti.addActionListener(e -> {
            // Recupera i valori selezionati dall'utente
            numeroScale = (Integer) comboNumeroScale.getSelectedItem();
            numeroSerpenti = (Integer) comboNumeroSerpenti.getSelectedItem();
            dialog.dispose(); // Chiude la finestra di dialogo
            apriDialogPosizioniScaleSerpenti(); // Apre la finestra di dialogo per le posizioni delle scale e dei serpenti
        });

        dialog.add(bottoneAvanti, BorderLayout.SOUTH); // Aggiunge il pulsante "Avanti" alla finestra di dialogo
        dialog.setSize(400, 200); // Imposta la dimensione della finestra di dialogo
        dialog.setLocationRelativeTo(this); // Centra la finestra di dialogo rispetto al frame principale
        dialog.setVisible(true); // Rende la finestra di dialogo visibile
    }

    // Metodo per aprire la finestra di dialogo per le posizioni delle scale e dei serpenti
    private void apriDialogPosizioniScaleSerpenti() {
        JDialog dialog = new JDialog(this, "Posizioni di Scale e Serpenti", true); // Crea una finestra di dialogo modale
        dialog.setLayout(new BorderLayout()); // Imposta il layout della finestra di dialogo

        // Crea un pannello per l'input con un layout GridLayout
        JPanel pannelloInput = new JPanel(new GridLayout(0, 3, 10, 10));

        // Aggiunge le etichette per i campi di input
        JLabel labelInizio = new JLabel("Inizio");
        JLabel labelFine = new JLabel("Fine");

        pannelloInput.add(new JLabel("")); // Etichetta vuota per allineare correttamente i campi
        pannelloInput.add(labelInizio); // Aggiunge l'etichetta "Inizio"
        pannelloInput.add(labelFine); // Aggiunge l'etichetta "Fine"

        // Creazione degli array di combo box per le posizioni delle scale e dei serpenti
        JComboBox<Integer>[] scaleInizioBoxes = new JComboBox[numeroScale];
        JComboBox<Integer>[] scaleFineBoxes = new JComboBox[numeroScale];
        JComboBox<Integer>[] serpentiInizioBoxes = new JComboBox[numeroSerpenti];
        JComboBox<Integer>[] serpentiFineBoxes = new JComboBox[numeroSerpenti];

        // Genera le opzioni valide per le posizioni delle scale e dei serpenti
        Integer[] scaleOptions = generateScaleOptions();
        Integer[] serpentiOptions = generateSerpentiOptions();

        // Aggiunge le combo box per le posizioni delle scale
        for (int i = 0; i < numeroScale; i++) {
            pannelloInput.add(new JLabel("Scala " + (i + 1))); // Etichetta per ogni scala
            scaleInizioBoxes[i] = new JComboBox<>(scaleOptions); // Combo box per la posizione di inizio
            scaleFineBoxes[i] = new JComboBox<>(scaleOptions); // Combo box per la posizione di fine
            pannelloInput.add(scaleInizioBoxes[i]); // Aggiunge la combo box al pannello
            pannelloInput.add(scaleFineBoxes[i]); // Aggiunge la combo box al pannello
        }

        // Aggiunge le combo box per le posizioni dei serpenti
        for (int i = 0; i < numeroSerpenti; i++) {
            pannelloInput.add(new JLabel("Serpente " + (i + 1))); // Etichetta per ogni serpente
            serpentiInizioBoxes[i] = new JComboBox<>(serpentiOptions); // Combo box per la posizione di inizio
            serpentiFineBoxes[i] = new JComboBox<>(serpentiOptions); // Combo box per la posizione di fine
            pannelloInput.add(serpentiInizioBoxes[i]); // Aggiunge la combo box al pannello
            pannelloInput.add(serpentiFineBoxes[i]); // Aggiunge la combo box al pannello
        }

        dialog.add(pannelloInput, BorderLayout.CENTER); // Aggiunge il pannello di input alla finestra di dialogo

        addDraggableFeature(dialog); // Aggiunge la funzionalità di trascinamento alla finestra di dialogo

        JButton bottoneAvanti = new JButton("Avanti"); // Crea un pulsante "Avanti"
        bottoneAvanti.addActionListener(e -> {
            try {
                // Crea le mappe per memorizzare le posizioni delle scale e dei serpenti
                Map<Integer, Integer> scale = new HashMap<>();
                Map<Integer, Integer> serpenti = new HashMap<>();
                Set<Integer> occupiedPositions = new HashSet<>(); // Set per tenere traccia delle posizioni occupate

                // Recupera e verifica le posizioni delle scale
                for (int i = 0; i < numeroScale; i++) {
                    int inizio = (Integer) scaleInizioBoxes[i].getSelectedItem();
                    int fine = (Integer) scaleFineBoxes[i].getSelectedItem();

                    if (inizio >= fine) {
                        throw new IllegalArgumentException("Scala " + (i + 1) + ": la posizione di inizio deve essere minore della posizione di fine.");
                    }
                    if (occupiedPositions.contains(inizio) || occupiedPositions.contains(fine)) {
                        throw new IllegalArgumentException("Scala " + (i + 1) + ": posizioni duplicate.");
                    }


                    scale.put(inizio, fine); // Aggiunge la posizione alla mappa delle scale
                    occupiedPositions.add(inizio); // Aggiunge la posizione di inizio al set delle posizioni occupate
                    occupiedPositions.add(fine); // Aggiunge la posizione di fine al set delle posizioni occupate
                }

                // Recupera e verifica le posizioni dei serpenti
                for (int i = 0; i < numeroSerpenti; i++) {
                    int inizio = (Integer) serpentiInizioBoxes[i].getSelectedItem();
                    int fine = (Integer) serpentiFineBoxes[i].getSelectedItem();

                    if (inizio <= fine) {
                        throw new IllegalArgumentException("Serpente " + (i + 1) + ": la posizione di inizio deve essere maggiore della posizione di fine.");
                    }
                    if (occupiedPositions.contains(inizio) || occupiedPositions.contains(fine)) {
                        throw new IllegalArgumentException("Serpente " + (i + 1) + ": posizioni duplicate.");
                    }


                    serpenti.put(inizio, fine); // Aggiunge la posizione alla mappa dei serpenti
                    occupiedPositions.add(inizio); // Aggiunge la posizione di inizio al set delle posizioni occupate
                    occupiedPositions.add(fine); // Aggiunge la posizione di fine al set delle posizioni occupate
                }

                this.scale = scale; // Salva la mappa delle scale nella variabile di istanza
                this.serpenti = serpenti; // Salva la mappa dei serpenti nella variabile di istanza
                dialog.dispose(); // Chiude la finestra di dialogo
                apriDialogCaselleSpeciali(); // Apre la finestra di dialogo per le caselle speciali
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage()); // Mostra un messaggio di errore se ci sono posizioni non valide
            }
        });

        dialog.add(bottoneAvanti, BorderLayout.SOUTH); // Aggiunge il pulsante "Avanti" alla finestra di dialogo
        dialog.setSize(400, 400); // Imposta la dimensione della finestra di dialogo
        dialog.setLocationRelativeTo(this); // Centra la finestra di dialogo rispetto al frame principale
        dialog.setVisible(true); // Rende la finestra di dialogo visibile
    }

    private int calcolaRiga(int posizione, int colonne) {
        return (posizione - 1) / colonne;
    }

    // Metodo per generare le opzioni per le posizioni delle scale
    private Integer[] generateScaleOptions() {
        int maxPosition = righe * colonne;
        Integer[] options = new Integer[maxPosition - 2]; // Array di opzioni per le posizioni delle scale
        for (int i = 2; i < maxPosition; i++) {
            options[i - 2] = i; // Aggiunge le posizioni valide all'array
        }
        return options; // Ritorna l'array di opzioni
    }

    // Metodo per generare le opzioni per le posizioni dei serpenti
    private Integer[] generateSerpentiOptions() {
        int maxPosition = righe * colonne;
        Integer[] options = new Integer[maxPosition - 2]; // Array di opzioni per le posizioni dei serpenti
        for (int i = 2; i < maxPosition; i++) {
            options[i - 2] = i; // Aggiunge le posizioni valide all'array
        }
        return options; // Ritorna l'array di opzioni
    }


    // Metodo per aprire la finestra di dialogo per le caselle speciali
    private void apriDialogCaselleSpeciali() {
        JDialog dialog = new JDialog(this, "Caselle Speciali", true); // Crea una finestra di dialogo modale
        dialog.setLayout(new BorderLayout()); // Imposta il layout della finestra di dialogo

        // Crea un pannello per l'input con un layout GridLayout
        JPanel pannelloInput = new JPanel(new GridLayout(0, 2, 10, 10));

        // Array di stringhe per le etichette dei campi di input
        String[] etichette = {
                "Caselle panchina", "Caselle locanda", "Caselle molla", "Caselle dadi", "Caselle pesca una carta"
        };

        // Array di combo box per i campi di input delle caselle speciali
        JComboBox<Integer>[] comboCaselleSpeciali = new JComboBox[etichette.length];
        Integer[] opzioni = {0, 1, 2, 3, 4}; // Opzioni per il numero di caselle speciali

        // Ciclo per aggiungere i campi di input al pannello
        for (int i = 0; i < etichette.length; i++) {
            pannelloInput.add(new JLabel(etichette[i])); // Aggiunge un'etichetta
            comboCaselleSpeciali[i] = new JComboBox<>(opzioni); // Crea una combo box con le opzioni
            pannelloInput.add(comboCaselleSpeciali[i]); // Aggiunge la combo box al pannello
        }

        dialog.add(pannelloInput, BorderLayout.CENTER); // Aggiunge il pannello di input alla finestra di dialogo

        addDraggableFeature(dialog); // Aggiunge la funzionalità di trascinamento alla finestra di dialogo

        JButton bottoneAvanti = new JButton("Avanti"); // Crea un pulsante "Avanti"
        bottoneAvanti.addActionListener(e -> {
            try {
                // Recupera i valori selezionati dall'utente
                int casellaPanchina = (Integer) comboCaselleSpeciali[0].getSelectedItem();
                int casellaLocanda = (Integer) comboCaselleSpeciali[1].getSelectedItem();
                int casellaMolla = (Integer) comboCaselleSpeciali[2].getSelectedItem();
                int casellaDadi = (Integer) comboCaselleSpeciali[3].getSelectedItem();
                int casellaPesca = (Integer) comboCaselleSpeciali[4].getSelectedItem();

                dialog.dispose(); // Chiude la finestra di dialogo
                apriDialogRegoleExtra(casellaPanchina, casellaLocanda, casellaMolla, casellaDadi, casellaPesca); // Apre la finestra di dialogo per le regole extra
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Errore nell'inserimento delle caselle speciali."); // Mostra un messaggio di errore se c'è un problema
            }
        });

        dialog.add(bottoneAvanti, BorderLayout.SOUTH); // Aggiunge il pulsante "Avanti" alla finestra di dialogo
        dialog.setSize(400, 300); // Imposta la dimensione della finestra di dialogo
        dialog.setLocationRelativeTo(this); // Centra la finestra di dialogo rispetto al frame principale
        dialog.setVisible(true); // Rende la finestra di dialogo visibile
    }

    // Metodo per aprire la finestra di dialogo per le regole extra
    private void apriDialogRegoleExtra(int casellaPanchina, int casellaLocanda, int casellaMolla, int casellaDadi, int casellaPesca) {
        JDialog dialogRegoleExtra = new JDialog(this, "Regole Extra", true); // Crea una finestra di dialogo modale
        dialogRegoleExtra.setLayout(new GridLayout(0, 1, 10, 10)); // Imposta il layout della finestra di dialogo

        // Crea le checkbox per le regole extra
        checkDivietoDiSosta = new JCheckBox("Divieto di sosta");
        checkDoppioSei = new JCheckBox("Doppio sei");
        checkLancioUnSoloDado = new JCheckBox("Lancio di un solo dado");

        // Aggiunge le checkbox alla finestra di dialogo
        dialogRegoleExtra.add(checkDivietoDiSosta);
        dialogRegoleExtra.add(checkDoppioSei);
        dialogRegoleExtra.add(checkLancioUnSoloDado);

        addDraggableFeature(dialogRegoleExtra); // Aggiunge la funzionalità di trascinamento alla finestra di dialogo

        JButton bottoneSalva = new JButton("Salva"); // Crea un pulsante "Salva"
        bottoneSalva.addActionListener(e -> {
            // Salva le regole extra e le posizioni delle caselle speciali
            Regole regole = salvaRegoleExtra(casellaPanchina, casellaLocanda, casellaMolla, casellaDadi, casellaPesca);
            salvaRegoleSuFile(regole); // Salva le regole su file
            dialogRegoleExtra.dispose(); // Chiude la finestra di dialogo
        });

        dialogRegoleExtra.add(bottoneSalva); // Aggiunge il pulsante "Salva" alla finestra di dialogo

        dialogRegoleExtra.pack(); // Imposta la dimensione della finestra di dialogo
        dialogRegoleExtra.setLocationRelativeTo(this); // Centra la finestra di dialogo rispetto al frame principale
        dialogRegoleExtra.setVisible(true); // Rende la finestra di dialogo visibile
    }

    // Metodo per salvare le regole extra
    private Regole salvaRegoleExtra(int casellaPanchina, int casellaLocanda, int casellaMolla, int casellaDadi, int casellaPesca) {
        // Recupera i valori delle checkbox
        boolean divietoDiSosta = checkDivietoDiSosta.isSelected();
        boolean doppioSei = checkDoppioSei.isSelected();
        boolean unDadoAllaFine = checkLancioUnSoloDado.isSelected();

        // Crea un nuovo oggetto Regole con i valori selezionati
        return new Regole(righe, colonne, numeroDadi, numeroGiocatori, casellaPanchina, casellaLocanda, casellaDadi, casellaMolla, casellaPesca, divietoDiSosta, doppioSei, unDadoAllaFine, scale, serpenti);

    }

    // Metodo per salvare le regole su file
    private void salvaRegoleSuFile(Regole regole) {
        JFileChooser fileChooser = new JFileChooser();//finestra di dialogo per selezionare file o dir
        int userSelection = fileChooser.showSaveDialog(this); // Mostra la finestra di dialogo

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile(); // Ottiene il file selezionato dall'utente
            try (FileOutputStream fileOut = new FileOutputStream(fileToSave);
                 ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(regole); // Scrive l'oggetto Regole nel file
                JOptionPane.showMessageDialog(this, "Regole salvate con successo."); // Mostra un messaggio di successo
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Errore durante il salvataggio delle regole.", "Errore", JOptionPane.ERROR_MESSAGE); // Mostra un messaggio di errore
            }
        }
    }

    // Metodo per caricare una partita da file
    private void caricaPartita() {
        JFileChooser fileChooser = new JFileChooser(); // Crea una finestra di dialogo per selezionare il file
        int userSelection = fileChooser.showOpenDialog(this); // Mostra la finestra di dialogo

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile(); // Ottiene il file selezionato dall'utente
            try (FileInputStream fileIn = new FileInputStream(fileToOpen);
                 ObjectInputStream in = new ObjectInputStream(fileIn)) {
                Regole regole = (Regole) in.readObject(); // Legge l'oggetto Regole dal file
                JOptionPane.showMessageDialog(this, "Regole caricate con successo."); // Mostra un messaggio di successo
                apriDialogModalitaGioco(regole); // Apre la finestra di dialogo per selezionare la modalità di gioco
            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Errore durante il caricamento delle regole.", "Errore", JOptionPane.ERROR_MESSAGE); // Mostra un messaggio di errore
            }
        }
    }

    // Metodo per aprire la finestra di dialogo per selezionare la modalità di gioco
    private void apriDialogModalitaGioco(Regole regole) {
        JDialog dialog = new JDialog(this, "Modalità di Gioco", true);
        dialog.setLayout(new BorderLayout()); // Imposta il layout della finestra di dialogo

        // Crea un pannello per i pulsanti con un layout GridLayout
        JPanel pannello = new JPanel(new GridLayout(2, 1, 10, 10));

        JButton bottoneAutomatica = new JButton("Automatica"); // Crea un pulsante "Automatica"
        bottoneAutomatica.addActionListener(e -> {
            dialog.dispose(); // Chiude la finestra di dialogo
            SwingUtilities.invokeLater(() -> new PartitaFrame(regole, true, this)); // Crea una nuova partita in modalità automatica
        });

        JButton bottoneManuale = new JButton("Manuale"); // Crea un pulsante "Manuale"
        bottoneManuale.addActionListener(e -> {
            dialog.dispose(); // Chiude la finestra di dialogo
            SwingUtilities.invokeLater(() -> new PartitaFrame(regole, false, this)); // Crea una nuova partita in modalità manuale
        });

        // Aggiunge i pulsanti al pannello
        pannello.add(bottoneAutomatica);
        pannello.add(bottoneManuale);

        dialog.add(pannello, BorderLayout.CENTER); // Aggiunge il pannello alla finestra di dialogo
        dialog.setSize(300, 200); // Imposta la dimensione della finestra di dialogo
        dialog.setLocationRelativeTo(this); // Centra la finestra di dialogo rispetto al frame principale
        dialog.setVisible(true); // Rende la finestra di dialogo visibile
    }

    // Metodo per aggiungere la funzionalità di trascinamento alle finestre di dialogo
    private void addDraggableFeature(JDialog dialog) {
        final Point[] compCoords = new Point[1]; // Array per memorizzare le coordinate del mouse
        dialog.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                compCoords[0] = e.getPoint(); // Memorizza le coordinate del mouse quando viene premuto il tasto sinistro
            }
        });
        dialog.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point currCoords = e.getLocationOnScreen(); // Ottiene le coordinate attuali del mouse
                dialog.setLocation(currCoords.x - compCoords[0].x, currCoords.y - compCoords[0].y); // Sposta la finestra di dialogo in base al movimento del mouse
            }
        });
    }

}
