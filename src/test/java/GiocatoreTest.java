import org.example.principale.Giocatore;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GiocatoreTest {

    private static Giocatore giocatore;

    @BeforeAll
    public static void setUpBeforeAll() {
        System.out.println("-------TEST INIZIATI-------");
        giocatore = new Giocatore("P1");
    }

    @BeforeEach
    public void aggiorna() {
        giocatore.setTurniDaSaltare(0);
    }

    @Test
    @DisplayName("Test Nome Giocatore")
    public void testNomeGiocatore() {
        assertEquals("P1", giocatore.getNome(), "Il nome del giocatore dovrebbe essere P1");
    }

    @ParameterizedTest
    @MethodSource("turniDaSaltareProvider")
    @DisplayName("Test Incrementa Turni Da Saltare")
    public void testIncrementaTurniDaSaltare(int initialTurni, int expectedTurni) {
        giocatore.setTurniDaSaltare(initialTurni);
        giocatore.setTurniDaSaltare(giocatore.getTurniDaSaltare() + 1);
        assertEquals(expectedTurni, giocatore.getTurniDaSaltare(), "Il giocatore dovrebbe saltare " + expectedTurni + " turni");
    }

    public static List<Object[]> turniDaSaltareProvider() {
        return List.of(
                new Object[]{0, 1},
                new Object[]{1, 2},
                new Object[]{2, 3}
        );
    }

    @RepeatedTest(3)
    @DisplayName("Test Giocatore Ripetuto")
    public void testGiocatoreRipetuto() {
        giocatore.setTurniDaSaltare(0); // Reset turni
        giocatore.setTurniDaSaltare(giocatore.getTurniDaSaltare() + 1);
        assertEquals(1, giocatore.getTurniDaSaltare(), "Il giocatore dovrebbe saltare 1 turno");
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
