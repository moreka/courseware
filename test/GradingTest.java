import exceptions.AuthenticationError;
import models.academics.OfferedCourse;
import models.user.Student;
import org.junit.*;
import services.EduDataImportService;
import services.EduSystemGateway;
import services.OfferedCourseService;

import java.io.File;
import java.io.IOException;


public class GradingTest {

    private void setUp() {
        try {
            File file = EduSystemGateway.getInstance().getCurrentSemesterData();
            EduDataImportService.getInstance().importData(file);

            OfferedCourse course = OfferedCourseService.getInstance().getCourse(1L);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSubmission() {
        setUp();
    }

}
