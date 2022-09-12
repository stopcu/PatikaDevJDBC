package stopcu.main;

import java.sql.SQLException;

import stopcu.db.DbConnection;
import stopcu.db.entity.Student;

public class Main {
    public static void main(String[] args) {
        DbConnection dbConnection = new DbConnection();
        try {
            dbConnection.connect();
            System.out.println(dbConnection.getAllStudents().toString());

            dbConnection.addStudent(new Student((short) 0, "Stopcu", (short) 3));
            System.out.println(dbConnection.getAllStudents().toString());

            dbConnection.updateStudentName((short)1,"Mehmet");
            System.out.println(dbConnection.getAllStudents().toString());

            dbConnection.deleteStudent((short)4);
            System.out.println(dbConnection.getAllStudents().toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
