package stopcu.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import stopcu.db.entity.Student;

public class DbConnection {

    private Connection connection;
    private final String url = "jdbc:postgresql://localhost:55000/university";
    private final String user = "postgres";
    private final String password = "postgrespw";

    public DbConnection() {

    }

    public void connect() throws SQLException {

        connection = DriverManager.getConnection(url, user, password);

    }

    public ArrayList<Student> getAllStudents() throws SQLException {

        String quey = "SELECT * FROM student;";

        try (Statement statement = connection.createStatement();) {

            ResultSet resultSet = statement.executeQuery(quey);

            ArrayList<Student> students = new ArrayList<>();

            while (resultSet.next()) {

                students.add(new Student(resultSet.getShort("student_id"), resultSet.getString("student_name"),
                        resultSet.getShort("student_class")));

            }

            return students;

        } catch (SQLException e) {

            throw e;
        }

    }

    public boolean addStudent(Student student) throws SQLException {

        /*String query = "INSERT INTO student (student_name, student_class) VALUES (" + student.getName() + ", "
                + student.getClassId() + ");";*/
        String prepreadQuery = "INSERT INTO student (student_name, student_class) VALUES (?,?)";

        /*
         * try (Statement statement = connection.createStatement()) {
         * return statement.executeUpdate(query) == 0 ? false : true;
         * } catch (Exception e) {
         * throw e;
         * }
         */

        try (PreparedStatement statement = connection.prepareStatement(prepreadQuery);) {

            statement.setString(1, student.getName());
            statement.setShort(2, student.getClassId());
            return statement.executeUpdate() == 0 ? false : true;
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean updateStudentName(short id, String name) throws SQLException {

        String prepreadQuery = "UPDATE student SET student_name=? WHERE student_id=?;";

        try (PreparedStatement statement = connection.prepareStatement(prepreadQuery);) {

            statement.setString(1, name);
            statement.setShort(2, id);
            return statement.executeUpdate() == 0 ? false : true;
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean deleteStudent(short id) throws SQLException {

        String prepreadQuery = "DELETE FROM student WHERE student_id=?;";

        try (PreparedStatement statement = connection.prepareStatement(prepreadQuery);) {
            statement.setShort(1, id);
            return statement.executeUpdate() == 0 ? false : true;
        } catch (Exception e) {
            throw e;
        }
    }

}
