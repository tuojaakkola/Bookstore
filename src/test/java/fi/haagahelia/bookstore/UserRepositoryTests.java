package fi.haagahelia.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import fi.haagahelia.bookstore.domain.Users;
import fi.haagahelia.bookstore.domain.UserRepository;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Test
    public void createNewUser() {
        
        Users user = new Users("testuser", passwordEncoder.encode("password"), "USER");
        userRepository.save(user);
        
        assertThat(user.getId()).isNotNull();
    }

    @Test
    public void deleteUser() {
        
        Users user = new Users("testuser", passwordEncoder.encode("password"), "USER");
        userRepository.save(user);
        userRepository.deleteById(user.getId());
        
        assertThat(userRepository.findById(user.getId())).isEmpty();
    }

    @Test
    public void findUserByUsername() {
       
       Users user = new Users("testuser", passwordEncoder.encode("password"), "USER");
       userRepository.save(user);
       Users foundUser = userRepository.findByUsername("testuser");
       
       assertThat(foundUser).isNotNull();
       assertThat(foundUser.getUsername()).isEqualTo("testuser");
    }

}
