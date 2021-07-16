package unit;

import com.test.spring.dao.UserDao;
import com.test.spring.models.UserModel;
import com.test.spring.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserDao userDao;
    @Mock
    private PasswordEncoder passwordEncoder;

    private UserServiceImpl userService;
    private static UserModel userModel;

    @BeforeAll
    public static void prepareTestData() {
        userModel = new UserModel(1, "name", "pass");
    }

    @BeforeEach
    public void init() {
        userService = new UserServiceImpl(userDao, passwordEncoder);
    }

    @Test
    public void shouldReturnUserByUsername() {
        when(userDao.getUserByUsername(any(String.class))).thenReturn(userModel);
        UserModel resultUserModel = userService.getOne("name");
        Assertions.assertEquals(userModel.getId(), resultUserModel.getId());
    }

    @Test
    public void shouldReturnListOfUsers() {
        when(userDao.getAll()).thenReturn(new ArrayList<UserModel>());
        List<UserModel> list = userService.getAll();
        Assertions.assertEquals(new ArrayList<UserModel>(), list);
    }

    @Test
    public void shouldReturnFalseIfUserExist() {
        when(userDao.getUserByUsername(any(String.class))).thenReturn(userModel);
        Assertions.assertFalse(userService.add(userModel));
    }

    @Test
    public void shouldReturnTrueIfUserExist() {
        doReturn(null).when(userDao).getUserByUsername(anyString());
        Assertions.assertTrue(userService.add(userModel));
    }
}
