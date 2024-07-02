package org.example.grafica;

import org.example.Partita;
import org.example.Regole;
import org.example.Tabellone;

import javax.swing.*;
import java.awt.*;

public class PartitaFrame extends JFrame {

    private JPanel pannelloTabellone; // Pannello per il tabellone di gioco
    private JTextArea areaTestoTurni; // Area di testo per mostrare le informazioni sui turni
    private JButton bottoneLanciaDadi; // Bottone per lanciare i dadi (solo per modalità manuale)
    private Regole regole; // Regole del gioco
    private Partita partita; // Partita corrente

    public PartitaFrame(Regole regole, boolean automatica) {
        this.regole = regole;
        setTitle("Scale e Serpenti - Partita in corso");
        setLayout(new BorderLayout());

        creaTabellone();

        // Aggiungere il box di testo per le informazioni sul turno
        areaTestoTurni = new JTextArea();
        areaTestoTurni.setEditable(false);
        areaTestoTurni.setLineWrap(true);  // Abilita il wrapping delle linee
        areaTestoTurni.setWrapStyleWord(true);  // Rende il wrapping più leggibile
        JScrollPane scrollPane = new JScrollPane(areaTestoTurni);
        scrollPane.setPreferredSize(new Dimension(300, 600));
        add(scrollPane, BorderLayout.EAST);

        bottoneLanciaDadi = new JButton("Lancia Dadi");
        bottoneLanciaDadi.addActionListener(e -> partita.avanzaTurnoManuale());
        bottoneLanciaDadi.setVisible(!automatica); // Mostra il bottone solo se la modalità è manuale
        add(bottoneLanciaDadi, BorderLayout.SOUTH);

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Tabellone tabellone = new Tabellone(regole);
        partita = Partita.getInstance(tabellone, automatica, areaTestoTurni);
        setVisible(true); // Rende la finestra visibile
        partita.avviaPartita();
    }

    private void creaTabellone() {
        int righe = regole.getRighe();
        int colonne = regole.getColonne();
        pannelloTabellone = new JPanel(new GridLayout(righe, colonne));
        for (int i = 0; i < righe * colonne; i++) {
            JPanel cella = new JPanel();
            cella.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            // Personalizzare la cella in base al tipo di casella
            personalizzaCella(cella, i);

            pannelloTabellone.add(cella);
        }
        add(pannelloTabellone, BorderLayout.CENTER);
    }

    private void personalizzaCella(JPanel cella, int indice) {
        //TODO
        cella.setBackground(Color.WHITE);
        JLabel etichetta = new JLabel(String.valueOf(indice + 1));
        cella.add(etichetta);
    }

    public void aggiornaInfoTurno(String info) {
        areaTestoTurni.append(info + "\n");
    }
}
