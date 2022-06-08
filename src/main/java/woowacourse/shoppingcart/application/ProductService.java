package woowacourse.shoppingcart.application;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woowacourse.shoppingcart.application.dto.ProductDetailServiceResponse;
import woowacourse.shoppingcart.application.dto.ProductSaveServiceRequest;
import woowacourse.shoppingcart.dao.ProductDao;
import woowacourse.shoppingcart.domain.Product;
import woowacourse.shoppingcart.exception.InvalidProductException;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductService {

    private final ProductDao productDao;

    public ProductService(final ProductDao productDao) {
        this.productDao = productDao;
    }

    public Long addProduct(final ProductSaveServiceRequest productRequest) {
        return productDao.save(productRequest.toEntity());
    }

    public ProductDetailServiceResponse findProductById(final Long productId) {
        final Product product = productDao.findProductById(productId)
                .orElseThrow(InvalidProductException::new);
        return ProductDetailServiceResponse.from(product);
    }

    public List<Product> findProducts(final int page, final int limit) {
        final int startIndex = limit * (page - 1);
        return productDao.findProducts(startIndex, limit);
    }

    public void deleteProductById(final Long productId) {
        int effectedRowCount = productDao.delete(productId);
        if (effectedRowCount == 0) {
            throw new InvalidProductException();
        }
    }
}
