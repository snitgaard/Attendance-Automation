/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.BE.Course;
import attendance.automation.BE.Student;
import attendance.automation.gui.Model.Model;
import attendance.automation.gui.Model.ModelException;
import com.jfoenix.controls.JFXProgressBar;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author The Cowboys
 */
public class StudentAttendanceOverviewController implements Initializable {

    private StudentAttendanceController controller;
    private Student selectedStudent;

    private Model model;

    @FXML
    private LineChart<String, Number> attendanceChart;
    @FXML
    private Label studentName;
    @FXML
    private Label studentEducation;
    @FXML
    private ImageView btn_close;
    @FXML
    private JFXProgressBar progressBar;
    @FXML
    private Label studentAttendancePercentage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try
        {
            model = new Model();
        } catch (IOException ex)
        {
            Logger.getLogger(StudentAttendanceOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    /**
     * This method makes sure that we have the correct data with us, into the class. It also sets a lot of the relevant data.
     * @param model
     * @param controller
     * @param selectedStudent
     * @throws SQLException
     * @throws ModelException 
     */
    public void ApplyImportantData(Model model, StudentAttendanceController controller, Student selectedStudent) throws SQLException, ModelException {
        this.model = model;
        this.controller = controller;
        this.selectedStudent = selectedStudent;
        attendanceChart.getData().clear();

        progressBar.setProgress(selectedStudent.getAttendance() / 100);
        DecimalFormat df = new DecimalFormat("#.##");
        studentAttendancePercentage.setText(df.format(selectedStudent.getAttendance()) + " %");
        updateDynamicData();
        buildLineChart();
    }

    /**
     * A line chart is defined, x axis as "days" y axis as "attendance". 
     * 6 lists are then created, 1 for all the courses, and the rest for the weekdays (monday-friday).
     * Current time is defined, and checks if the weekdays of the courses match any of weekdays, and adds the result to the empty result lists created earlier. 
     * 5 different for-loops are then created, again corresponding to the days of the week. It counts attendance on each of the weekdays by using the resultlists
     * created and checking which courses are on the different days.
     * Finally, the attendance is calculated and formatted to be percentages and the results are then added to the linechart data.
     * @throws SQLException
     * @throws ModelException 
     */
    private void buildLineChart() throws SQLException, ModelException {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Days");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Attendance");

        XYChart.Series data = new XYChart.Series();
        data.setName("Attendance Chart");

        List<Course> courseIds = model.getAllCourses();
        List<Course> resultMonday = new ArrayList<>();
        List<Course> resultTuesday = new ArrayList<>();
        List<Course> resultWednesday = new ArrayList<>();
        List<Course> resultThursday = new ArrayList<>();
        List<Course> resultFriday = new ArrayList<>();

        LocalDate todaysDate = LocalDate.now();

        for (Course courses : courseIds) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localCourseDate = LocalDate.parse(courses.getCourseDate(), formatter);
            if (courses.getWeekDay() != null && localCourseDate.isBefore(todaysDate) || localCourseDate.equals(todaysDate)) {

                if (courses.getWeekDay().equals("Monday")) {
                    resultMonday.add(courses);
                } else if (courses.getWeekDay().equals("Tuesday")) {
                    resultTuesday.add(courses);
                } else if (courses.getWeekDay().equals("Wednesday")) {
                    resultWednesday.add(courses);
                } else if (courses.getWeekDay().equals("Thursday")) {
                    resultThursday.add(courses);
                } else if (courses.getWeekDay().equals("Friday")) {
                    resultFriday.add(courses);
                }
            }
        }
        double attendedMondayCounter = 0;
        double attendedTuesdayCounter = 0;
        double attendedWednesdayCounter = 0;
        double attendedThursdayCounter = 0;
        double attendedFridayCounter = 0;

        for (int i = 0; i < resultMonday.size(); i++) {
            if (model.getAllCourseIds(resultMonday.get(i).getCourseId(), selectedStudent.getId()) == 1) {
                attendedMondayCounter++;
                
            }
        }

        for (int i = 0; i < resultTuesday.size(); i++) {
            if (model.getAllCourseIds(resultTuesday.get(i).getCourseId(), selectedStudent.getId()) == 1) {
                attendedTuesdayCounter++;
            }
        }

        for (int i = 0; i < resultWednesday.size(); i++) {
            if (model.getAllCourseIds(resultWednesday.get(i).getCourseId(), selectedStudent.getId()) == 1) {
                attendedWednesdayCounter++;
            }
        }

        for (int i = 0; i < resultThursday.size(); i++) {
            if (model.getAllCourseIds(resultThursday.get(i).getCourseId(), selectedStudent.getId()) == 1) {
                attendedThursdayCounter++;
            }
        }

        for (int i = 0; i < resultFriday.size(); i++) {
            if (model.getAllCourseIds(resultFriday.get(i).getCourseId(), selectedStudent.getId()) == 1) {
                attendedFridayCounter++;
            }
        }

        double mondayPercentage = attendedMondayCounter / resultMonday.size() * 100;
        double tuesdayPercentage = attendedTuesdayCounter / resultTuesday.size() * 100;
        double wednesdayPercentage = attendedWednesdayCounter / resultWednesday.size() * 100;
        double thursdayPercentage = attendedThursdayCounter / resultThursday.size() * 100;
        double fridayPercentage = attendedFridayCounter / resultFriday.size() * 100;

        //Provide data
        data.getData().add(new XYChart.Data("Monday", mondayPercentage));
        data.getData().add(new XYChart.Data("Tuesday", tuesdayPercentage));
        data.getData().add(new XYChart.Data("Wednesday", wednesdayPercentage));
        data.getData().add(new XYChart.Data("Thursday", thursdayPercentage));
        data.getData().add(new XYChart.Data("Friday", fridayPercentage));

        attendanceChart.getData().add(data);
    }

    /**
     * Dynamically updates the data corresponding to which student is using the program.
     */
    private void updateDynamicData() {
        studentName.setText(selectedStudent.getName());
        studentEducation.setText(selectedStudent.getStudentEducation());
    }
   
    /**
     * Closes the current stage
     * @param event 
     */
    @FXML
    private void close_app(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    /**
     * Minimizes the stage
     * @param event 
     */
    @FXML
    private void minimize_app(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
}
