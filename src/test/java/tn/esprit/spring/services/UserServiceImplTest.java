package tn.esprit.spring.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.UserRepository;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository; // Mocked dependency

    @InjectMocks
    private UserServiceImpl userService; // Class under test

    public UserServiceImplTest() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testAddUser() {
        // Arrange
        User user = new User("John", "Doe", new Date(), Role.ADMINISTRATEUR);

        // Mocking the repository save behavior
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        User createdUser = userService.addUser(user);

        // Assert
        Assertions.assertNotNull(createdUser, "The created user should not be null");
        Assertions.assertEquals("Doe", createdUser.getLastName(), "The user's last name should be Doe");
        // Verify that the role is correct
        Assertions.assertEquals(Role.ADMINISTRATEUR, createdUser.getRole(), "The user's role should be ADMINISTRATEUR");
    }
}
