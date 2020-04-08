/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.BE.Teacher;
import attendance.automation.gui.Model.ClassesModel;
import attendance.automation.gui.Model.CourseModel;
import attendance.automation.gui.Model.StudentModel;
import attendance.automation.gui.Model.TeacherModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author The Best Group
 */
public class TeacherMainController implements Initializable
{

    CourseModel courseModel;

    private TeacherModel teacherModel;
    private LoginController controller;
    private ClassesModel classesModel;
    private Teacher selectedTeacher;
    private Class selectedClass;
    private JFXButton classButton;
    private StudentModel studentModel;
    private ObservableList<JFXButton> classButtons = FXCollections.observableArrayList();
    @FXML
    private ImageView btn_close;
    @FXML
    private AnchorPane ancMain;
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private ImageView btn_minimize;
    @FXML
    private JFXListView<JFXButton> classListView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            classesModel = new ClassesModel();
            studentModel = new StudentModel();
            generateClassButtons();
        } catch (IOException ex)
        {
            Logger.getLogger(TeacherMainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(TeacherMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void showTeacherCourse() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/TeacherCourse.fxml"));
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

    private void showTeacherClass() throws IOException, SQLException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/TeacherClass.fxml"));
        Parent root = fxmlLoader.load();

        TeacherClassController taecherClassController = fxmlLoader.getController();
        taecherClassController.ApplyImportantData(studentModel, this, selectedClass, classButton);
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

    @FXML
    private void createCourse(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/CourseWindow.fxml"));
        Parent root = fxmlLoader.load();

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

    void ApplyImportantData(TeacherModel teacherModel, LoginController controller, Teacher selectedTeacher)
    {
        this.teacherModel = teacherModel;
        this.controller = controller;
        this.selectedTeacher = selectedTeacher;
    }

    public void generateClassButtons() throws SQLException, IOException
    {
        for (int i = 1; i < classesModel.getAllClasses().size() + 1; i++)
        {
            classButton = new JFXButton();
            classButtons.add(classButton);
            classButton.setText(classesModel.getClassName(i));

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/TeacherClass.fxml"));
            Parent root = fxmlLoader.load();
            TeacherClassController teacherClassController = fxmlLoader.getController();
            teacherClassController.ApplyImportantData(studentModel, this, selectedClass, classButton);

            classButton.setOnMouseClicked(event
                    ->
            {
                {

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

            });
        }

        Comparator<JFXButton> sortByName = (JFXButton b1, JFXButton b2) -> b1.getText().compareTo(b2.getText());
        Collections.sort(classButtons, sortByName);
        classListView.setItems(classButtons);
        classButton.setPrefHeight(30.3);
        classListView.setPrefHeight(classButtons.size() * classButton.getPrefHeight());
        classListView.setPrefWidth(68);
        classListView.setItems(classButtons);

    }

}
