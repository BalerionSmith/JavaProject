package tableView;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import helper.DbConnect;
import models.Student;

public class TableViewController implements Initializable {

    @FXML
    private TableView<Student> studentsTable;
    @FXML
    private TableColumn<Student, String> idCol;
    @FXML
    private TableColumn<Student, String> nameCol;
    @FXML
    private TableColumn<Student, String> birthCol;
    @FXML
    private TableColumn<Student, String> addressCol;
    @FXML
    private TableColumn<Student, String> emailCol;
    @FXML
    private TableColumn<Student, String> editCol;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Student student = null;


    ObservableList<Student> studentList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
    }

    @FXML
    private void close(MouseEvent event) {

    }

    @FXML
    private void getAddView(MouseEvent event) {

    }

    @FXML
    private void refreshTable() {
        try {
            studentList.clear();
            query = "SELECT * FROM 'student' ";
            preparedStatement = connection.prepareStatement(query);

            while (resultSet.next()){
                studentList.add(new  Student(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDate("birth"),
                        resultSet.getString("adress"),
                        resultSet.getString("email")));
                studentsTable.setItems(studentList);

            }
        }

        catch (SQLException ex) {
            Logger.getLogger(tableView.TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void print(MouseEvent event) {

    }

    private void loadDate() {

        connection = DbConnect.getConnection();
        refreshTable();
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        birthCol.setCellValueFactory(new PropertyValueFactory<>("birth"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

}
