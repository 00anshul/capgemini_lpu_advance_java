package assignment2mockito;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PriceServiceTest {

    @Test
    void testCalculateFinalPrice_Mobile() {

        DiscountRepository repo = mock(DiscountRepository.class);

        when(repo.getDiscountPercentage("MOB"))
                .thenReturn(10.0);

        PriceService service = new PriceService(repo);

        double result = service.calculateFinalPrice(1000, "MOB");

        assertEquals(900, result);

        verify(repo).getDiscountPercentage("MOB");
    }

    @Test
    void testCalculateFinalPrice_Laptop() {

        DiscountRepository repo = mock(DiscountRepository.class);

        when(repo.getDiscountPercentage("LAP"))
                .thenReturn(20.0);

        PriceService service = new PriceService(repo);

        double result = service.calculateFinalPrice(2000, "LAP");

        assertEquals(1600, result);

        verify(repo).getDiscountPercentage("LAP");
    }

    @Test
    void testRepositoryException() {

        DiscountRepository repo = mock(DiscountRepository.class);

        when(repo.getDiscountPercentage("MOB"))
                .thenThrow(new RuntimeException("Repository error"));

        PriceService service = new PriceService(repo);

        assertThrows(RuntimeException.class, () ->
                service.calculateFinalPrice(1000, "MOB"));
    }
}
