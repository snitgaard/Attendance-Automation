/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.BE.Student;
import attendance.automation.gui.Model.Model;
import attendance.automation.gui.Model.ModelException;
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
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author The Cowboys
 */
public class TeacherClassController implements Initializable
{

    private Model model;
    private Student selectedStudent;
    private StudentAttendanceController studentAttendanceController;
    private JFXButton classButton;
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
    @FXML
    private ImageView btn_close;
    @FXML
    private AnchorPane ancMain;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            model = new Model();
        } catch (IOException ex)
        {
            Logger.getLogger(TeacherClassController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nameTable.setCellValueFactory(new PropertyValueFactory<>("name"));
        classTable.setCellValueFactory(new PropertyValueFactory<>("email"));
        attendanceTable.setCellValueFactory(new PropertyValueFactory<>("attendance"));
    }

    /**
     * This method makes sure that we have the correct data with us, into the class. It also sets a lot of the relevant data.
     *
     * @param model
     * @param controller
     * @param selectedClass
     * @param classButton
     * @throws SQLException
     * @throws IOException
     * @throws ParseException
     * @throws ModelException
     */
    public void ApplyImportantData(Model model, TeacherMainController controller, Class selectedClass, JFXButton classButton) throws SQLException, IOException, ParseException, ModelException
    {
        this.model = model;
        this.classButton = classButton;
        attendanceView.setItems(model.getAllStudentsClass(classButton.getText()));
        setAverageAttendance();
        studentOverview();
        attendanceView.getSelectionModel().setCellSelectionEnabled(true);
    }

    /**
     * Loads the student overview view if the teacher double clicks on a student in a class list.
     *
     * @throws IOException
     * @throws SQLException
     */
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
                        studentcontroller.ApplyImportantData(model, studentAttendanceController, selectedStudent);
                    } catch (SQLException ex)
                    {
                        Logger.getLogger(TeacherClassController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ModelException ex)
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

    /**
     * Calculates the average attendance of a class and formats the average.
     *
     * @throws SQLException
     * @throws ParseException
     * @throws ModelException
     */
    private void setAverageAttendance() throws SQLException, ParseException, ModelException
    {
        double totalAttendance = 0;
        double averageAttendance = 0;
        for (int i = 0; i < model.getAllStudentsClass(classButton.getText()).size(); i++)
        {
            totalAttendance += model.getAllStudentsClass(classButton.getText()).get(i).getAttendance();
        }
        averageAttendance = totalAttendance / model.getAllStudentsClass(classButton.getText()).size();
        DecimalFormat df = new DecimalFormat("#.##");
        averageLabel.setText(df.format((averageAttendance)) + " %");
        attendanceBar.setProgress(averageAttendance / 100);
    }

    /**
     * Closes the stage
     *
     * @param event
     */
    @FXML
    private void close_app(MouseEvent event)
    {
        System.exit(0);
    }

    /**
     * Minimizes the app
     *
     * @param event
     */
    @FXML
    private void minimize_app(MouseEvent event)
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    /**
     * Shows the teacher main view
     *
     * @throws IOException
     */
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

    /**
     * @throws IOException
     */
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
