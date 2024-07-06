package org.example.grafica;

import org.example.Partita;
import org.example.Regole;
import org.example.Tabellone;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PartitaFrame extends JFrame {

    private final Regole regole; // Regole del gioco
    private final Partita partita; // Partita corrente
    public static final Map<Integer, String> caselleSpeciali = new HashMap<>();

    public PartitaFrame(Regole regole, boolean automatica) {
        this.regole = regole;
        setTitle("Scale e Serpenti - Partita in corso");
        setLayout(new BorderLayout());

        // Inizializza il box di testo per le informazioni sul turno
        // Area di testo per mostrare le informazioni sui turni
        JTextArea areaTestoTurni = new JTextArea();
        areaTestoTurni.setEditable(false);
        areaTestoTurni.setLineWrap(true);  // Abilita il wrapping delle linee
        areaTestoTurni.setWrapStyleWord(true);  // Rende il wrapping più leggibile
        JScrollPane scrollPane = new JScrollPane(areaTestoTurni);
        scrollPane.setPreferredSize(new Dimension(300, 600));
        add(scrollPane, BorderLayout.EAST);

        // Inizializza il tabellone e la partita
        Tabellone tabellone = new Tabellone(regole);
        partita = Partita.getInstance(tabellone, automatica, areaTestoTurni);

        creaTabellone();

        // Bottone per lanciare i dadi (solo per modalità manuale)
        JButton bottoneLanciaDadi = new JButton("Lancia Dadi");
        bottoneLanciaDadi.addActionListener(_ -> partita.avanzaTurnoManuale());
        bottoneLanciaDadi.setVisible(!automatica); // Mostra il bottone solo se la modalità è manuale
        add(bottoneLanciaDadi, BorderLayout.SOUTH);

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true); // Rende la finestra visibile
        partita.avviaPartita();
    }

    private void creaTabellone() {
        int righe = regole.getRighe();
        int colonne = regole.getColonne();
        // Pannello per il tabellone di gioco
        JPanel pannelloTabellone = new JPanel(new GridLayout(righe, colonne));
        for (int i = 1; i <= righe * colonne; i++) {
            JPanel cella = new JPanel();
            cella.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            cella.setLayout(new BoxLayout(cella, BoxLayout.Y_AXIS)); // Usa BoxLayout per disporre gli elementi verticalmente

            // Personalizzare la cella in base al tipo di casella
            personalizzaCella(cella, i);

            pannelloTabellone.add(cella);
        }
        add(pannelloTabellone, BorderLayout.CENTER);
    }

    private void personalizzaCella(JPanel cella, int indice) {

        JLabel etichettaNumero = new JLabel(String.valueOf(indice));
        JLabel etichettaTipo = new JLabel();

        if(caselleSpeciali.containsKey(indice)) { //è una casella speciale
            String tipo = caselleSpeciali.get(indice);
            switch (tipo){
                case "scala":
                    cella.setBackground(Color.green);
                    etichettaTipo.setText("scala");
                    break;
                case "serpente":
                    cella.setBackground(Color.red);
                    etichettaTipo.setText("serpente");
                    break;
                case "pesca":
                    cella.setBackground(Color.yellow);
                    etichettaTipo.setText("pesca");
                    break;
                case "panchina":
                    cella.setBackground(Color.magenta);
                    etichettaTipo.setText("panchina");
                    break;
                case "locanda":
                    cella.setBackground(Color.orange);
                    etichettaTipo.setText("locanda");
                    break;
                case "molla":
                    cella.setBackground(Color.blue);
                    etichettaTipo.setText("molla");
                    break;
                case "dadi":
                    cella.setBackground(Color.cyan);
                    etichettaTipo.setText("dadi");
                    break;
            }
        }
        else { //casella base
            cella.setBackground(Color.white);
            etichettaTipo.setText("Base");
        }

        cella.add(etichettaNumero);
        cella.add(etichettaTipo);
    }
}
