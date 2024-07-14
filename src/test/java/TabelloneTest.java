import org.example.principale.Tabellone;
import org.example.principale.Regole;
import org.example.principale.Partita;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Collections;
import javax.swing.JTextArea;

import static org.junit.jupiter.api.Assertions.*;

class TabelloneTest {

    private Tabellone tabellone;

    @BeforeAll
    public static void setUpBeforeAll() {
        System.out.println("-------TEST INIZIATI-------");
    }

    @BeforeEach
    void setup() {
        Regole regole = new Regole.Builder(1, 10, 10, 1)
                .scale(Collections.emptyMap())
                .serpenti(Collections.emptyMap())
                .build();
        tabellone = new Tabellone(regole);
        Partita.setAreaTestoTurni(new JTextArea());
    }

    @Test
    @DisplayName("Test Inizializzazione Tabellone")
    void testInizializzazioneTabellone() {
        assertNotNull(tabellone, "Il tabellone dovrebbe essere inizializzato");
    }

    @ParameterizedTest
    @MethodSource("caselleProvider")
    @DisplayName("Test Aggiunta Caselle Speciali")
    void testAggiuntaCaselleSpeciali(int posizione, String tipo) {
        tabellone.getCasella(posizione).setTipo(tipo);

        assertEquals(tipo, tabellone.getCasella(posizione).toString(), "La casella dovrebbe essere di tipo " + tipo);
    }

    static List<Object[]> caselleProvider() {
        return List.of(
                new Object[]{5, "serpente"},
                new Object[]{10, "scala"},
                new Object[]{15, "pesca"}
        );
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
