package org.example.grafica;

import org.example.Regole;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class MainFrame extends JFrame {

    // ComboBox per selezionare le opzioni delle caselle
    private JComboBox<Integer> comboCasellePescaCarta;
    private JComboBox<Integer> comboNumeroDadi;
    private JComboBox<Integer> comboRighe;
    private JComboBox<Integer> comboColonne;
    private JComboBox<Integer> comboNumeroGiocatori;
    private JComboBox<Integer> comboCasellaPanchina;
    private JComboBox<Integer> comboCasellaLocanda;
    private JComboBox<Integer> comboCasellaMolla;
    private JComboBox<Integer> comboCasellaDadi;
    // CheckBox per selezionare le regole extra
    private JCheckBox checkDivietoDiSosta;
    private JCheckBox checkDoppioSei;
    private JCheckBox checkLancioUnSoloDado;

    public MainFrame() {
        super("SCALE E SERPENTI");

        // Creo un pannello principale con GridBagLayout
        JPanel pannelloPrincipale = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints(); //per disporre i componenti nel container
        gbc.insets = new Insets(10, 10, 10, 10); // Margini tra i bottoni
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;

        // Creo pulsanti
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

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //centrare rispetto allo schermo
        setVisible(true);
    }

    private void apriDialogCreaPartita() {
        JDialog dialog = new JDialog(this, "Crea Partita", true);
        dialog.setLayout(new BorderLayout());

        JPanel pannelloInput = new JPanel(new GridLayout(0, 2, 10, 10));

        // Definisco le regole
        String[] etichette = {
                "Numero righe", "Numero colonne", "Numero giocatori", "Numero dadi",
                "Caselle panchina", "Caselle locanda", "Caselle molla", "Caselle dadi",
                "Caselle pesca una carta"
        };

        for (String etichetta : etichette) {
            pannelloInput.add(new JLabel(etichetta));
            Integer[] opzioni;
            switch (etichetta) {
                case "Numero righe":
                    opzioni = new Integer[]{6, 7, 8, 9, 10};
                    comboRighe = new JComboBox<>(opzioni);
                    pannelloInput.add(comboRighe);
                    break;
                case "Numero colonne":
                    opzioni = new Integer[]{6, 7, 8, 9, 10};
                    comboColonne = new JComboBox<>(opzioni);
                    pannelloInput.add(comboColonne);
                    break;
                case "Numero giocatori":
                    opzioni = new Integer[]{2, 3, 4, 5, 6};
                    comboNumeroGiocatori = new JComboBox<>(opzioni);
                    pannelloInput.add(comboNumeroGiocatori);
                    break;
                case "Numero dadi":
                    opzioni = new Integer[]{1, 2};
                    comboNumeroDadi = new JComboBox<>(opzioni);
                    pannelloInput.add(comboNumeroDadi);
                    break;
                case "Caselle panchina":
                    opzioni = new Integer[]{0, 1, 2, 3, 4};
                    comboCasellaPanchina = new JComboBox<>(opzioni);
                    pannelloInput.add(comboCasellaPanchina);
                    break;
                case "Caselle locanda":
                    opzioni = new Integer[]{0, 1, 2, 3, 4};
                    comboCasellaLocanda = new JComboBox<>(opzioni);
                    pannelloInput.add(comboCasellaLocanda);
                    break;
                case "Caselle molla":
                    opzioni = new Integer[]{0, 1, 2, 3, 4};
                    comboCasellaMolla = new JComboBox<>(opzioni);
                    pannelloInput.add(comboCasellaMolla);
                    break;
                case "Caselle dadi":
                    opzioni = new Integer[]{0, 1, 2, 3, 4};
                    comboCasellaDadi = new JComboBox<>(opzioni);
                    pannelloInput.add(comboCasellaDadi);
                    break;
                case "Caselle pesca una carta":
                    opzioni = new Integer[]{0, 1, 2, 3, 4};
                    comboCasellePescaCarta = new JComboBox<>(opzioni);
                    pannelloInput.add(comboCasellePescaCarta);

            }
        }

        dialog.add(pannelloInput, BorderLayout.CENTER);

        JButton bottoneRegoleExtra = new JButton("Regole Extra");
        bottoneRegoleExtra.addActionListener(e -> apriDialogRegoleExtra());
        dialog.add(bottoneRegoleExtra, BorderLayout.SOUTH);

        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void apriDialogRegoleExtra() {
        JDialog dialogRegoleExtra = new JDialog(this, "Regole Extra", true);
        dialogRegoleExtra.setLayout(new GridLayout(0, 1, 10, 10));

        checkDivietoDiSosta = new JCheckBox("Divieto di sosta");
        checkDoppioSei = new JCheckBox("Doppio sei");
        checkLancioUnSoloDado = new JCheckBox("Lancio di un solo dado");

        // Abilitare/Disabilitare i campi in base alle condizioni
        int casellePescaCartaValue = (Integer) comboCasellePescaCarta.getSelectedItem();
        checkDivietoDiSosta.setVisible(casellePescaCartaValue != 0);

        int numeroDadiValue = (Integer) comboNumeroDadi.getSelectedItem();
        boolean dueDadi = numeroDadiValue == 2;
        checkDoppioSei.setVisible(dueDadi);
        checkLancioUnSoloDado.setVisible(dueDadi);

        dialogRegoleExtra.add(checkDivietoDiSosta);
        dialogRegoleExtra.add(checkDoppioSei);
        dialogRegoleExtra.add(checkLancioUnSoloDado);

        JButton bottoneSalva = new JButton("Salva");
        bottoneSalva.addActionListener(e -> {
            Regole regole = salvaRegoleExtra();
            salvaRegoleSuFile(regole);
            dialogRegoleExtra.dispose(); //chiudere il dialog e liberare le risorse
        });
        dialogRegoleExtra.add(bottoneSalva);

        dialogRegoleExtra.pack();
        dialogRegoleExtra.setLocationRelativeTo(this);
        dialogRegoleExtra.setVisible(true);
    }

    private Regole salvaRegoleExtra() {
        int righe = (Integer) comboRighe.getSelectedItem();
        int colonne = (Integer) comboColonne.getSelectedItem();
        int numeroDadi = (Integer) comboNumeroDadi.getSelectedItem();
        int numeroGiocatori = (Integer) comboNumeroGiocatori.getSelectedItem();
        int casellaPanchina = (Integer) comboCasellaPanchina.getSelectedItem();
        int casellaLocanda = (Integer) comboCasellaLocanda.getSelectedItem();
        int casellaDadi = (Integer) comboCasellaDadi.getSelectedItem();
        int casellaMolla = (Integer) comboCasellaMolla.getSelectedItem();
        int casellaPesca = (Integer) comboCasellePescaCarta.getSelectedItem();
        boolean divietoDiSosta = checkDivietoDiSosta.isSelected();
        boolean doppioSei = checkDoppioSei.isSelected();
        boolean unDadoAllaFine = checkLancioUnSoloDado.isSelected();

        return new Regole(righe, colonne, numeroDadi, numeroGiocatori, casellaPanchina, casellaLocanda,
                casellaDadi, casellaMolla, casellaPesca, divietoDiSosta, doppioSei, unDadoAllaFine);
    }

    private void salvaRegoleSuFile(Regole regole) {
        JFileChooser fileChooser = new JFileChooser();//finestra di dialogo per selezionare file o dir
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (FileOutputStream fileOut = new FileOutputStream(fileToSave);
                 ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(regole);
                JOptionPane.showMessageDialog(this, "Regole salvate con successo.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Errore durante il salvataggio delle regole.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void caricaPartita() {
        JFileChooser fileChooser = new JFileChooser();
        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();
            try (FileInputStream fileIn = new FileInputStream(fileToOpen);
                 ObjectInputStream in = new ObjectInputStream(fileIn)) {
                Regole regole = (Regole) in.readObject();
                JOptionPane.showMessageDialog(this, "Regole caricate con successo.");
                apriDialogModalitaGioco(regole);
            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Errore durante il caricamento delle regole.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void apriDialogModalitaGioco(Regole regole) {
        JDialog dialog = new JDialog(this, "Modalità di Gioco", true);
        dialog.setLayout(new BorderLayout());

        JPanel pannello = new JPanel(new GridLayout(2, 1, 10, 10));

        JButton bottoneAutomatica = new JButton("Automatica");
        bottoneAutomatica.addActionListener(e -> {
            dialog.dispose();
            SwingUtilities.invokeLater(() -> new PartitaFrame(regole, true,this));
        });

        JButton bottoneManuale = new JButton("Manuale");
        bottoneManuale.addActionListener(e -> {
            dialog.dispose();
            SwingUtilities.invokeLater(() -> new PartitaFrame(regole, false,this));
        });

        pannello.add(bottoneAutomatica);
        pannello.add(bottoneManuale);

        dialog.add(pannello, BorderLayout.CENTER);
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

}
