/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.BE.Student;
import attendance.automation.gui.Model.StudentModel;
import com.jfoenix.controls.JFXProgressBar;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author The Best Group
 */
public class StudentAttendanceOverviewController implements Initializable
{
    
    private StudentModel studentModel;
    private StudentAttendanceController controller;
    private Student selectedStudent;

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
    public void initialize(URL url, ResourceBundle rb)
    {   
        buildLineChart();
        
    }
    
    public void ApplyImportantData(StudentModel studentModel, StudentAttendanceController controller, Student selectedStudent) throws SQLException {
        this.studentModel = studentModel;
        this.controller = controller;
        this.selectedStudent = selectedStudent;

        progressBar.setProgress(selectedStudent.getAttendance() / 100);
        studentAttendancePercentage.setText(selectedStudent.getAttendance() + " %");
        
        System.out.println("Inde i studentAtteandaceController" + this.selectedStudent);
        System.out.println(selectedStudent.getAttendance() + "what is going on");

    }

    private void buildLineChart()
    {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Days");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Attendance");

        XYChart.Series data = new XYChart.Series();
        data.setName("Attendance Chart");

        //Provide data
        data.getData().add(new XYChart.Data("Monday", 10));
        data.getData().add(new XYChart.Data("Tuesday", 67));
        data.getData().add(new XYChart.Data("Wednesday", 75));
        data.getData().add(new XYChart.Data("Thursday", 100));
        data.getData().add(new XYChart.Data("Friday", 36));

        attendanceChart.getData().add(data);
    }
    
    private void updateDynamicData()
    {
        
    }

    @FXML
    private void close_app(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void minimize_app(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
    
    
}
