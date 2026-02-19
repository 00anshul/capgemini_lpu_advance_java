package assignment2mockito;

public class PriceService {

    private DiscountRepository discountRepository;

    public PriceService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public double calculateFinalPrice(double price, String productCode) {

        double discount =
                discountRepository.getDiscountPercentage(productCode);

        return price - (price * discount / 100);
    }
}
