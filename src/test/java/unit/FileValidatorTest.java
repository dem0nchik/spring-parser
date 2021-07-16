package unit;

import com.test.spring.parser.FileValidator;
import com.test.spring.parser.ResultValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class FileValidatorTest {
    private FileValidator fileValidator = new FileValidator();

    @Test
    public void shouldReturnErrorIfFileNotExist() {
        ResultValidation rv = new ResultValidation(false, "file is not exist");
        Assertions.assertEquals(rv, (fileValidator.validate(new File(""))));
    }

    @Test
    public void shouldReturnErrorIfIsNotFile() {
        ResultValidation rv = new ResultValidation(false, "is not file");
        Assertions.assertEquals(rv, fileValidator.validate(new File("/")));
    }


    @Test
    public void shouldReturnErrorIfFileEmpty() {
        ResultValidation rv = new ResultValidation(false, "file is empty");
        Assertions.assertEquals(rv, fileValidator.validate(new File("resources/fileEmpty.txt")));
    }

    @Test
    public void shouldReturnTrueValidation() {
        ResultValidation rv = new ResultValidation(true, "");
        Assertions.assertEquals(rv, fileValidator.validate(new File("resources/fileTest.txt")));
    }
}
