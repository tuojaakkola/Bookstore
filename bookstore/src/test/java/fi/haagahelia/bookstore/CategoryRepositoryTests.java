package fi.haagahelia.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;

@DataJpaTest
public class CategoryRepositoryTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void createNewCategory() {
        Category category = new Category("Test Category");
        categoryRepository.save(category);

        assertThat(category.getId()).isNotNull();
    }

    @Test
    public void deleteCategory() {
        Category category = new Category("Test Category");
        categoryRepository.save(category);
        categoryRepository.deleteById(category.getId());

        assertThat(categoryRepository.findById(category.getId())).isEmpty();
    }

    @Test
    public void findCategoryByName() {
        Category category = new Category("Test Category");
        categoryRepository.save(category);
        Category foundCategory = categoryRepository.findByName("Test Category").get(0);

        assertThat(foundCategory).isEqualTo(category);
    }
}
