package assignment2mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)   // enables Mockito in JUnit 5
public class PriceServiceTest {

    @Mock
    DiscountRepository discountRepository;   // Mockito creates a fake implementation

    PriceService priceService;

    @BeforeEach
    void setup() {
        priceService = new PriceService(discountRepository);  // inject mock
    }

    // TEST 1 — MOB, Price 1000, Discount 10%, Expected 900
    @Test
    void testMobileDiscount() {
        // ARRANGE — tell mock what to return when called
        when(discountRepository.getDiscountPercentage("MOB")).thenReturn(10.0);

        // ACT — call the real service method
        double finalPrice = priceService.calculateFinalPrice(1000, "MOB");

        // ASSERT — verify result
        assertEquals(900.0, finalPrice);

        // VERIFY — confirm mock method was actually called
        verify(discountRepository).getDiscountPercentage("MOB");

        System.out.println("TEST 1 PASSED — MOB final price: " + finalPrice);
    }

    // TEST 2 — LAP, Price 2000, Discount 20%, Expected 1600
    @Test
    void testLaptopDiscount() {
        // ARRANGE
        when(discountRepository.getDiscountPercentage("LAP")).thenReturn(20.0);

        // ACT
        double finalPrice = priceService.calculateFinalPrice(2000, "LAP");

        // ASSERT
        assertEquals(1600.0, finalPrice);

        // VERIFY
        verify(discountRepository).getDiscountPercentage("LAP");

        System.out.println("TEST 2 PASSED — LAP final price: " + finalPrice);
    }

    // BONUS — repository throws exception
    @Test
    void testRepositoryThrowsException() {
        // ARRANGE — tell mock to throw exception
        when(discountRepository.getDiscountPercentage("UNKNOWN"))
            .thenThrow(new RuntimeException("Product code not found"));

        // ACT + ASSERT — verify exception is thrown
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            priceService.calculateFinalPrice(1000, "UNKNOWN");
        });

        assertEquals("Product code not found", exception.getMessage());

        System.out.println("BONUS TEST PASSED — exception: " + exception.getMessage());
    }
}