package unit;

import com.test.spring.dao.FileDao;
import com.test.spring.dao.FileResultDao;
import com.test.spring.models.FileResultModel;
import com.test.spring.service.FileResultsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FileResultsServiceTest {
    @Mock
    private FileResultDao fileResultDao;
    @Mock
    private FileDao fileDao;

    private FileResultsServiceImpl fileResultsService;

    private static FileResultModel fileResultModel;

    @BeforeAll
    public static void prepareTestData() {
        fileResultModel = new FileResultModel(
                        new Date(),
                        "user",
                        "file.txt",
                        3444,
                        "resources/results/502f7df6-35d9-481a-9cd9-414d1643cb24.obj"
                );
    }

    @BeforeEach
    public void init() {
        fileResultsService = new FileResultsServiceImpl(fileResultDao, fileDao);
    }

    @Test
    public void shouldReturnAllResults() {
        when(fileResultDao.getAllResults()).thenReturn(new ArrayList<FileResultModel>());

        List<FileResultModel> results = fileResultsService.getAllResults();

        Assertions.assertEquals(new ArrayList<FileResultModel>(), results);
    }

    @Test
    public void shouldReturnMapOfResults() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("file", 8);

        when(fileDao.getResultFromFile("resources/results/502f7df6-35d9-481a-9cd9-414d1643cb24.obj"))
                .thenReturn(map);

        HashMap<String, Integer> retrieved = fileResultsService.getResultsFromFile(fileResultModel.getFilepath());

        Assertions.assertEquals(map, retrieved);
    }
}
