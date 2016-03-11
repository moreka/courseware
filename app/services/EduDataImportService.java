package services;

import models.academics.Course;
import models.academics.OfferedCourse;
import models.user.BasicUser;
import models.user.Professor;
import models.user.Student;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.DateHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class EduDataImportService {

    private static EduDataImportService _instance = null;

    private EduDataImportService() { }

    public static EduDataImportService getInstance() {
        if (_instance == null)
            _instance = new EduDataImportService();
        return _instance;
    }

    public void importData(File _file) throws IOException, ReflectiveOperationException {
        FileInputStream file = new FileInputStream(_file);
        Workbook workbook = new XSSFWorkbook(file);

        extractPersonsFromSheet(workbook.getSheetAt(0), Student.class);
        extractPersonsFromSheet(workbook.getSheetAt(1), Professor.class);
        extractCourseLecturerDataFromSheet(workbook.getSheetAt(2));
        extractStudentCourseMappingFromSheet(workbook.getSheetAt(3));

        file.close();
    }

    private void extractStudentCourseMappingFromSheet(Sheet sheet) {
        Iterator<Row> iterator = sheet.rowIterator();
        iterator.next();

        while(iterator.hasNext()) {
            try {
                Row row = iterator.next();

                Long off_id = (long) row.getCell(0).getNumericCellValue();
                String stu_id = Long.toString((long) row.getCell(1).getNumericCellValue());

                OfferedCourse offeredCourse = OfferedCourse.find.byId(off_id);
                Student student = Student.find.byId(stu_id);

                offeredCourse.addStudent(student);
            }
            catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    private void extractPersonsFromSheet(Sheet sheet, Class<? extends BasicUser> _class)
            throws ReflectiveOperationException
    {
        Iterator<Row> iterator = sheet.rowIterator();
        iterator.next();

        while(iterator.hasNext()) {
            try {
                Row row = iterator.next();
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);

                BasicUser user = _class.newInstance();
                user.id = Long.toString((long) row.getCell(0).getNumericCellValue());
                user.name = row.getCell(1).getStringCellValue();
                user.password = row.getCell(2).getStringCellValue();
                user.birthDate = DateHelper.getDate(row.getCell(3).getStringCellValue());

                int dep_num = (int) row.getCell(4).getNumericCellValue();
                if (user instanceof Professor)
                    ((Professor) user).departmentNo = dep_num;
                else if (user instanceof Student)
                    ((Student) user).departmentNo = dep_num;

                user.save();
            }
            catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    private void extractCourseLecturerDataFromSheet(Sheet sheet) {
        Iterator<Row> iterator = sheet.rowIterator();
        iterator.next();

        while(iterator.hasNext()) {
            try {
                Row row = iterator.next();

                Course course = new Course();
                course.courseId = (long) row.getCell(1).getNumericCellValue();
                course.title = row.getCell(2).getStringCellValue();
                course.departmentNo = (int) row.getCell(9).getNumericCellValue();
                course.save();

                String pf_id = Long.toString((long) row.getCell(3).getNumericCellValue());
                Professor pf = Professor.find.byId(pf_id);

                OfferedCourse offeredCourse = new OfferedCourse();
                offeredCourse.id = (long) row.getCell(0).getNumericCellValue();
                offeredCourse.lectureTime = row.getCell(4).getStringCellValue();
                offeredCourse.finalExamTime = DateHelper.getDateTime(row.getCell(5).getStringCellValue());
                offeredCourse.semester = row.getCell(6).getStringCellValue();
                offeredCourse.groupId = (int) row.getCell(7).getNumericCellValue();
                offeredCourse.room = row.getCell(8).getStringCellValue();

                offeredCourse.course = course;
                offeredCourse.lecturer = pf;

                offeredCourse.save();
            }
            catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
}
