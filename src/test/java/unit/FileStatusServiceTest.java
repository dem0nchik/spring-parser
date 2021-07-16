package unit;

import com.test.spring.dao.FileDao;
import com.test.spring.dao.FileStatusDao;
import com.test.spring.dao.UserDao;
import com.test.spring.models.FileModel;
import com.test.spring.models.UserModel;
import com.test.spring.service.FileStatusServiceImpl;
import com.test.spring.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FileStatusServiceTest {
    @Mock private FileDao fileDao;
    @Mock private FileStatusDao fileStatusDao;
    @Mock private MultipartFile multipartFile;
    @Mock private UserDao userDao;
    @Mock private Authentication authentication;

    private HashMap<String, Integer> map;
    private FileStatusServiceImpl statusService;

    @BeforeEach
    public void init() {
        statusService = new FileStatusServiceImpl(fileDao, fileStatusDao, userDao);
    }

    @Test
    public void shouldThrowExceptionIfMapIsNull() throws IOException {
        map = null;
        doReturn(new UserModel()).when(authentication).getPrincipal();
        doReturn(new FileModel()).when(fileDao).getByFilepath(anyString());
        doReturn(new UserModel()).when(userDao).getUserByUsername(anyString());
        doThrow(new IOException()).when(fileDao).saveResultInFile(null, eq(anyString()));
        Assertions.assertThrows(Exception.class, () -> statusService.addResult(multipartFile, authentication, map));
    }

}
