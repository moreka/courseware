package controllers;

import models.academics.Course;
import models.academics.OfferedCourse;
import models.user.Professor;
import models.user.Student;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import play.mvc.*;

import views.html.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

public class Application extends Controller {

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public Result init() throws ParseException {

        Student st = new Student();
        st.departmentNo = 44;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        st.birthDate = sdf.parse("31/12/1992");

        st.id = 90106103L;
        st.name = "محمد رضا کریمی";
        st.password = "ajax";

        st.save();

        Professor pf = new Professor();
        pf.departmentNo = 33;
        pf.birthDate = sdf.parse("01/01/1980");
        pf.id = 123424L;
        pf.name = "Jafar Habibi";
        pf.password = "pass";
        pf.save();

        Course course = new Course();
        course.courseId = 40100L;
        course.title = "معماری نرم‌افزار";
        course.save();

        OfferedCourse offeredCourse = new OfferedCourse();
        offeredCourse.course = course;
        offeredCourse.groupId = 1;
        offeredCourse.finalExamTime = sdf.parse("12/12/2013");
        offeredCourse.lecturer = pf;
        offeredCourse.lectureTime = "har roooze!";
        offeredCourse.room = "CE 204";
        offeredCourse.semester = "93-94-1";
        offeredCourse.save();

        offeredCourse.addStudent(st);

        return ok("Finished");
    }

    public Result readEdu() throws IOException {
        FileInputStream file = new FileInputStream(new File("/Users/moreka/Downloads/edu-sample-excel-v3.xlsx"));

        //Create Workbook instance holding reference to .xlsx file
        Workbook workbook = new XSSFWorkbook(file);

        //Get first/desired sheet from the workbook
        Sheet sheet = workbook.getSheetAt(0);

        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext())
        {
            Row row = rowIterator.next();
            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext())
            {
                Cell cell = cellIterator.next();
                //Check the cell type and format accordingly
                switch (cell.getCellType())
                {
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getNumericCellValue() + "t");
                        break;
                    case Cell.CELL_TYPE_STRING:
                        System.out.print(cell.getStringCellValue() + "t");
                        break;
                }
            }
            System.out.println("");
        }
        file.close();
        return ok("done");
    }
}
