/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.BE.Teacher;
import attendance.automation.gui.Model.Model;
import attendance.automation.gui.Model.ModelException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.text.ParseException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author The Cowboys
 */
public class TeacherMainController implements Initializable
{
    private Model model;
    private LoginController controller;
    private Class selectedClass;
    private JFXButton classButton;
    private Teacher selectedTeacher;
    private double prefButtonHeight = 30.3;
    private int prefButtonWidth = 68;
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
            model = new Model();
            generateClassButtons();
        } catch (IOException | SQLException | ParseException | ModelException ex)
        {
            Logger.getLogger(TeacherMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
     * Minimizes the stage
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
     * Opens the Teacher class view
     *
     * @throws IOException
     * @throws SQLException
     * @throws ParseException
     * @throws ModelException
     */
    private void showTeacherClass() throws IOException, SQLException, ParseException, ModelException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/TeacherClass.fxml"));
        Parent root = fxmlLoader.load();

        TeacherClassController taecherClassController = fxmlLoader.getController();
        taecherClassController.ApplyImportantData(model, this, selectedClass, classButton);
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
     * Opens the create course view
     *
     * @param event
     * @throws IOException
     */
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

    /**
     * This method makes sure that we have the correct data with us, into the class. It also sets a lot of the relevant data.
     *
     * @param model
     * @param controller
     * @param selectedTeacher
     */
    void ApplyImportantData(Model model, LoginController controller, Teacher selectedTeacher)
    {
        this.model = model;
        this.controller = controller;
        this.selectedTeacher = selectedTeacher;
    }

    /**
     * Generates class buttons based on the amount of classes found in the database.
     * Sets the mouse click event to open a stage when you click on the class button,
     * and sets button sizes.
     *
     * @throws SQLException
     * @throws IOException
     * @throws ParseException
     * @throws ModelException
     */
    public void generateClassButtons() throws SQLException, IOException, ParseException, ModelException
    {
        for (int i = 1; i < model.getAllClasses().size() + 1; i++)
        {
            classButton = new JFXButton();
            classButtons.add(classButton);
            classButton.setText(model.getClassName(i));

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendance/automation/gui/view/TeacherClass.fxml"));
            Parent root = fxmlLoader.load();
            TeacherClassController teacherClassController = fxmlLoader.getController();
            teacherClassController.ApplyImportantData(model, this, selectedClass, classButton);

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
        classButton.setPrefHeight(prefButtonHeight);
        classListView.setPrefHeight(classButtons.size() * classButton.getPrefHeight());
        classListView.setPrefWidth(prefButtonWidth);
        classListView.setItems(classButtons);
    }
}
