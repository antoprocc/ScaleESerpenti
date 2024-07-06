package org.example.grafica;

import org.example.Giocatore;
import org.example.Partita;
import org.example.Regole;
import org.example.Tabellone;
import org.example.observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PartitaFrame extends JFrame implements Observer {
    private final Regole regole; // Regole del gioco
    private final Partita partita; // Partita corrente
    private final JPanel pannelloTabellone; // Pannello per il tabellone di gioco
    public static final Map<Integer, String> caselleSpeciali = new HashMap<>();
    private final Map<String, JLabel> pedineGiocatori;

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

        pannelloTabellone = new JPanel(new GridLayout(regole.getRighe(), regole.getColonne()));
        pedineGiocatori = new HashMap<>();

        creaTabellone();
        inizializzaPedineGiocatori();

        // Bottone per lanciare i dadi (solo per modalità manuale)
        JButton bottoneLanciaDadi = new JButton("Lancia Dadi");
        bottoneLanciaDadi.addActionListener(e -> partita.avanzaTurnoManuale());
        bottoneLanciaDadi.setVisible(!automatica); // Mostra il bottone solo se la modalità è manuale
        add(bottoneLanciaDadi, BorderLayout.SOUTH);

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true); // Rende la finestra visibile
        partita.avviaPartita();
        partita.aggiungiOsservatoreAGiocatori(this);
    }

    private void creaTabellone() {
        int righe = regole.getRighe();
        int colonne = regole.getColonne();
        for (int i = 1; i <= righe * colonne; i++) {
            JPanel cella = new JPanel();
            cella.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            cella.setLayout(new BoxLayout(cella, BoxLayout.Y_AXIS));

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

    private void inizializzaPedineGiocatori() {
        for (Giocatore giocatore : partita.getGiocatori()) {
            JLabel pedina = new JLabel(giocatore.getNome());
            pedineGiocatori.put(giocatore.getNome(), pedina);

            // Posizioniamo la pedina nella cella iniziale
            JPanel cellaIniziale = (JPanel) pannelloTabellone.getComponent(0);
            cellaIniziale.add(pedina);
        }
    }

    @Override
    public void update(int posizione, String nomeGiocatore) {
        SwingUtilities.invokeLater(() -> {
            JLabel pedina = pedineGiocatori.get(nomeGiocatore);
            if (pedina == null) {
                pedina = new JLabel(nomeGiocatore);
                pedineGiocatori.put(nomeGiocatore, pedina);
            }

            // Rimuovi la pedina da tutte le celle
            for (Component componente : pannelloTabellone.getComponents()) {
                if (componente instanceof JPanel) {
                    JPanel cella = (JPanel) componente;
                    cella.remove(pedina);
                    cella.revalidate();
                    cella.repaint();
                }
            }

            // Aggiungi la pedina alla nuova cella
            JPanel nuovaCella = (JPanel) pannelloTabellone.getComponent(posizione - 1);
            nuovaCella.add(pedina);
            nuovaCella.revalidate();
            nuovaCella.repaint();
        });
    }

}
