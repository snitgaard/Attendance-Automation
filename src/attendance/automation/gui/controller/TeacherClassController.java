/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.BE.Student;
import attendance.automation.gui.Model.StudentModel;
import attendance.automation.gui.Model.TeacherModel;
import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author The Best Group
 */
public class TeacherClassController implements Initializable
{

    private StudentModel studentModel;
    private TeacherModel teacherModel;
    private Student selectedStudent;
    private StudentAttendanceController studentAttendanceController;
    private TeacherMainController controller;
    private Class selectedClass;
    private JFXButton classButton;
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
    @FXML
    private Label averageLabel;
    @FXML
    private ProgressBar attendanceBar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        teacherModel = new TeacherModel();
        nameTable.setCellValueFactory(new PropertyValueFactory<>("name"));
        classTable.setCellValueFactory(new PropertyValueFactory<>("email"));
        attendanceTable.setCellValueFactory(new PropertyValueFactory<>("attendance"));
    }

    public void ApplyImportantData(StudentModel studentModel, TeacherMainController controller, Class selectedClass, JFXButton classButton) throws SQLException, IOException
    {
        this.studentModel = studentModel;
        this.controller = controller;
        this.selectedClass = selectedClass;
        this.classButton = classButton;
//        int realUserData = Integer.parseInt(classButton.getText().substring(10));
        attendanceView.setItems(studentModel.getAllStudentsClass(classButton.getText()));
        setAverageAttendance();
        studentOverview();
        attendanceView.getSelectionModel().setCellSelectionEnabled(true);
    }

    public void studentOverview() throws IOException, SQLException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/StudentAttendanceOverview.fxml"));
        Parent root = fxmlLoader.load();
        StudentAttendanceOverviewController studentcontroller = fxmlLoader.getController();

        attendanceView.setRowFactory(tv ->
        {
            TableRow<Student> row = new TableRow<>();
            row.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 2 && (!row.isEmpty()))
                {
                    Student selectedStudent = row.getItem();
                    try
                    {
                        studentcontroller.ApplyImportantData(studentModel, studentAttendanceController, selectedStudent);
                    } catch (SQLException ex)
                    {
                        Logger.getLogger(TeacherClassController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    StudentAttendanceOverviewController c = fxmlLoader.getController();
                    Stage stage = new Stage();
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initStyle(StageStyle.TRANSPARENT);
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
                    stage.setAlwaysOnTop(true);
                    stage.setTitle("Student Attendance Overview");
                    if (root.getScene() == null)
                    {
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                    } else
                    {
                        stage.setScene(root.getScene());
                    }
                    stage.setResizable(false);
                    stage.show();
                }
            });
            return row;
        });

    }

    private void setAverageAttendance() throws SQLException
    {
        double totalAttendance = 0;
        double averageAttendance = 0;
        for (int i = 0; i < studentModel.getAllStudentsClass(classButton.getText()).size(); i++)
        {
            totalAttendance += studentModel.getAllStudentsClass(classButton.getText()).get(i).getAttendance();
        }

        averageAttendance = totalAttendance / studentModel.getAllStudentsClass(classButton.getText()).size();
        DecimalFormat df = new DecimalFormat("#.##");
        averageLabel.setText(df.format((averageAttendance)) + " %");
        attendanceBar.setProgress(averageAttendance / 100);
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
}
