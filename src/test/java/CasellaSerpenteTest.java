import org.example.caselle.CasellaSerpente;
import org.example.principale.Giocatore;
import org.example.principale.Tabellone;
import org.example.principale.Regole;
import org.example.principale.Partita;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import javax.swing.JTextArea;

class CasellaSerpenteTest {

    private Giocatore giocatore;
    private Tabellone tabellone;

    @BeforeAll
    public static void setUpBeforeAll() {
        System.out.println("-------TEST INIZIATI-------");
    }

    @BeforeEach
    void setup() {
        giocatore = new Giocatore("Test");
        Regole regole = new Regole.Builder(1, 10, 10, 1)
                .scale(Collections.emptyMap())
                .serpenti(Collections.emptyMap())
                .build();
        tabellone = new Tabellone(regole);
        Partita.setAreaTestoTurni(new JTextArea()); // Mock JTextArea
    }

    @Test
    @DisplayName("Test Creazione CasellaSerpente Valida")
    void testCreazioneCasellaSerpenteValida() {
        CasellaSerpente casella = new CasellaSerpente(10, 5);
        assertNotNull(casella, "La casella serpente dovrebbe essere creata");
    }

    @Test
    @DisplayName("Test Creazione CasellaSerpente Non Valida")
    void testCreazioneCasellaSerpenteNonValida() {
        assertThrows(RuntimeException.class, () -> new CasellaSerpente(5, 10), "Dovrebbe lanciare una RuntimeException");
    }

    @Test
    @DisplayName("Test Effetto CasellaSerpente")
    void testEffettoCasellaSerpente() {
        CasellaSerpente casella = new CasellaSerpente(10, 5);
        giocatore.setCasella(casella);
        casella.effetto(giocatore, null, 100, 0, tabellone); // Passiamo null come strategia del dado per semplicità
        assertEquals(5, giocatore.getCasella().getNumeroCasella(), "Il giocatore dovrebbe essere spostato alla casella 5");
    }

    @AfterEach
    public void dopoIlSingolo() {
        System.out.println("SINGOLO TEST EFFETTUATO");
    }

    @AfterAll
    public static void fine() {
        System.out.println("-------TEST FINITI-------");
    }
}
