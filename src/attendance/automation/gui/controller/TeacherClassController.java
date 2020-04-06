/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.BE.Student;
import attendance.automation.gui.Model.StudentModel;
import attendance.automation.gui.Model.TeacherModel;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author The Best Group
 */
public class TeacherClassController implements Initializable
{

    StudentModel studentModel = new StudentModel();
    private TeacherModel teacherModel;
    @FXML
    private ImageView btn_close;
    @FXML
    private AnchorPane ancMain;
    private Label nameFour;
    private Label nameTwo;
    private Label nameOne;
    private Label nameThree;
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private TableView<Student> attendanceView;
    @FXML
    private TableColumn<Student, String> nameTable;
    @FXML
    private TableColumn<Student, String> classTable;
    @FXML
    private TableColumn<Student, Double> attendanceTable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        studentOverview();
        generateClasses();
        teacherModel = new TeacherModel();
        
        attendanceView.setItems(studentModel.getAllStudents());
        nameTable.setCellValueFactory(new PropertyValueFactory<>("name"));
        classTable.setCellValueFactory(new PropertyValueFactory<>("email"));
        attendanceTable.setCellValueFactory(new PropertyValueFactory<>("attendance"));
        studentList();
    }

    public void studentList()
    {
        studentModel.getAllStudents().addListener((ListChangeListener<Student>) c ->
        {
            while (c.next())
            {
                if (c.wasRemoved() || c.wasAdded())
                {
                    attendanceView.refresh();
                }
            }
        });

    }

    @FXML
    private void close_app(MouseEvent event)
    {
        System.exit(0);
    }

    @FXML
    private void minimize_app(MouseEvent event)
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void showTeacherMain() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/TeacherMain.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage1 = (Stage) ancMain.getScene().getWindow();
        stage1.close();
        Object c = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();

        root.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
    }

    private void showTeacherStudent() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/TeacherStudent.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage1 = (Stage) ancMain.getScene().getWindow();
        stage1.close();
        Object c = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();

        root.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
    }

    private void generateClasses()
    {
        //TODO
    }

    private void studentOverview()
    {
    }
}
