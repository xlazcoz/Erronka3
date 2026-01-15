import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.Test;
import java.util.*;

public class BiltegiAplikazioaTest {

    // --- CP1: KASU ZUZENA ---
    @Test
    public void testCP1() {
        // Apalategia sortu 
        Apalategia apalategia = new Apalategia("Biltegia", 5, 5);
        
        // Produktua sortu
        Map<String, String> atributuak = new HashMap<>();
        atributuak.put("taila", "M");
        Produktua p = new Produktua("111", "Kamiseta", atributuak);

        Gelaxka gelaxka = apalategia.getGelaxka(3, 3);
        gelaxka.produktuaGehitu(p, 10);

        // Konprobatu
        assertEquals(10, gelaxka.getProduktuak().get(p));
    }

    // --- CP5: ERRENKADA NEGATIBOA ---
    @Test
    public void testCP5() {
        Apalategia apalategia = new Apalategia("B", 5, 5);
    
        // Test hau BERDEA (ONDO) aterako da errorea ematen badu.
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            apalategia.getGelaxka(-5, 1);
        });
    }

    // --- CP6: ERRENKADA HANDIEGIA ---
    @Test
    public void testCP6() {
        Apalategia apalategia = new Apalategia("B", 5, 5);
        
        // 6. errenkada ez da existitzen (max 4). Errorea eman behar du.
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            apalategia.getGelaxka(6, 1);
        });
    }

    // --- CP7: ZUTABEA NEGATIBOA (-1) ---
    @Test
    public void testCP7() {
        Apalategia apalategia = new Apalategia("B", 5, 5);
        
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            apalategia.getGelaxka(1, -1);
        });
    }

    // --- CP8: ZUTABEA HANDIEGIA ---
    @Test
    public void testCP8() {
        Apalategia apalategia = new Apalategia("B", 5, 5);
        
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            apalategia.getGelaxka(1, 6);
        });
    }
}
