package webshop;

public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void saleProduct(long id, int amount) {
        if (productRepository.findProductById(id).getStock() >= amount) {
            productRepository.updateProductStock(id, amount);
        } else {
            throw new IllegalArgumentException("Not enough on stock");
        }
    }
}
