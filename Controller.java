package com.internshala.javafx_app;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
	@FXML
	public Label welcomeLabel;
	@FXML
	public ChoiceBox<String> choiceBox;
	@FXML
	public TextField userInputField;
	@FXML
	public Button convertButton;

	private static final String c_to_f_text="Celsius to Farenheit";
	private static final String f_to_c_text="Farenheit to Celsius";

	private boolean isC_to_F=true;

	//when application starts for Controller class, initialize method is executed
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) { //initialize acts as a entry point for controller
		choiceBox.getItems().add(c_to_f_text);
		choiceBox.getItems().add(f_to_c_text);
		choiceBox.setValue(c_to_f_text);
		//lambda expression
		choiceBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
			if(newValue.contains(c_to_f_text)) {
				isC_to_F=true; //if the user has selected "Celsius to Farenheit"
			}
			else {
				isC_to_F=false;//if the user has selected "Farenheit to Celsius"
			}
		});
		//lambda expression
		convertButton.setOnAction((actionEvent) ->{
				convert();
		});


	}

	private void convert() {

		String input=userInputField.getText();//23.6 -> "23.6"
		float enteredTemperature=0.0f;
		try {
			enteredTemperature = Float.parseFloat(input);
		}
		catch(Exception ex){
			warnUser();
			return;
		}
		float newTemperature=0.0f;
		if (isC_to_F){//if the user has selected "Celsius to Farenheit"
			newTemperature=(enteredTemperature * 9/5)+32;
		}
		else{//if the user has selected "Celsius to Farenheit"
			newTemperature=(enteredTemperature -32)*5/9;
		}
		display(newTemperature);
	}

	private void warnUser() {
		Alert alert=new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Occurred");
		alert.setHeaderText("Invalid Temperature Entered");
		alert.setContentText("Please enter a valid temperature");
		alert.show();
	}

	private void display(float newTemperature) {
		String unit=isC_to_F? "F":"C";
		System.out.println("The new Temperature is: "+newTemperature+" "+unit);
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Result");
		alert.setContentText("The new Temperature is: "+newTemperature+" "+unit);
		alert.show();
	}
}
