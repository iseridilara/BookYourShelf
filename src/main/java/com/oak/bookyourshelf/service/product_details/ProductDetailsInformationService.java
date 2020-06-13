package com.oak.bookyourshelf.service.product_details;

import com.oak.bookyourshelf.model.Product;
import com.oak.bookyourshelf.model.User;
import com.oak.bookyourshelf.repository.CartItemRepository;
import com.oak.bookyourshelf.repository.UserRepository;
import com.oak.bookyourshelf.repository.product_details.ProductDetailsInformationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailsInformationService {

    final ProductDetailsInformationRepository productDetailsInformationRepository;
    final UserRepository userRepository;
    final CartItemRepository cartItemRepository;

    public ProductDetailsInformationService(ProductDetailsInformationRepository productDetailsInformationRepository,
                                            UserRepository userRepository, CartItemRepository cartItemRepository) {
        this.productDetailsInformationRepository = productDetailsInformationRepository;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public Product get(int id) {
        return productDetailsInformationRepository.findById(id).get();
    }

    public void save(Product product) {
        productDetailsInformationRepository.save(product);
    }

    public Product getByBarcode(String barcode) {
        return productDetailsInformationRepository.findProductByBarcode(barcode);
    }

    public Product getByISBN(String isbn) {
        return productDetailsInformationRepository.findBookByISBN(isbn);
    }

    public void deleteProduct(int id) {
        userRepository.removeAllWishlistByProductId(id);
        List<User> userList = (List<User>) userRepository.findAll();
        for (User user : userList) {
            user.getShoppingCart().removeIf(cartItem -> cartItem.getProduct().getProductId() == id);
            user.getOrders().forEach(order -> order.getProducts().removeIf(cartItem -> cartItem.getProduct().getProductId() == id));
            userRepository.save(user);
        }
        cartItemRepository.removeAllByProductId(id);
        productDetailsInformationRepository.deleteById(id);
    }
}
