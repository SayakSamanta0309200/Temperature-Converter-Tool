package com.internshala.javafx_app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class MyMain extends Application
{
	public static void main(String[] args)
	{
		System.out.println("main");
		launch(args);//to launch the application
	}
	//JavaFX lifecycle methods init,start and stop
	@Override
	public void init() throws Exception {
		System.out.println("init");//Initialize the application
		super.init();
	}

	//start method abstract method and part of Application class
	//stage the outermost outline of the application
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		System.out.println("start");//start an application
		//connecting MyMain.java class with app_layot.fxml
		FXMLLoader loader = new FXMLLoader(getClass().getResource("app_layout.fxml"));
		VBox rootNode = loader.load(); //loads root node of fxml file

		MenuBar menuBar=createMenu();
		rootNode.getChildren().add(0,menuBar);

		Scene scene = new Scene(rootNode);//within the scene first element rootNode with width and height

		primaryStage.setScene(scene);//to set the scene
		primaryStage.setTitle("Temperature Converter Tool");//to set the title
		//primaryStage.setResizable(false);//makes application non-resizable
		primaryStage.show();//the show the application
	}
	private MenuBar createMenu()
	{
		//filemenu
		Menu filemenu=new Menu("File");
		MenuItem newmenuItem=new MenuItem("New");
		//Using Event handler:on click EventHandler on newmenuItem, newmenuItem will trigger handle event and will execute statement.
		//using lambda expression.newmenuItem when clicked it will execute the statements
		newmenuItem.setOnAction(actionEvent -> {
			System.out.println("New Menu Item Clicked");
		});
		//to draw line between menu items
		SeparatorMenuItem separatorMenuItem=new SeparatorMenuItem();
		MenuItem quitmenuItem=new MenuItem("Quit");
		//Using Event handler:on click EventHandler on quitmenuItem, quitmenuItem will trigger handle event and will execute statement that will shut down my current application.
		//using lambda expression.quitmenuItem when clicked it will execute the exit()
		quitmenuItem.setOnAction(actionEvent -> {
			Platform.exit();
			System.exit(0);
		});

		filemenu.getItems().addAll(newmenuItem,separatorMenuItem,quitmenuItem);

		//helpmenu
		Menu helpMenu=new Menu("Help");
		MenuItem aboutApp=new MenuItem("About");
		//Using Event handler:on click EventHandler on aboutApp, aboutApp will trigger handle event and will execute statement that will call aboutApp().
		//using lambda expression.aboutApp when clicked it will execute the aboutApp()
		aboutApp.setOnAction(actionEvent -> aboutApp());
		
		helpMenu.getItems().addAll(aboutApp);

		//menu bar
		MenuBar menuBar=new MenuBar();
		menuBar.getMenus().addAll(filemenu,helpMenu);
		return menuBar;
	}

	private void aboutApp() {
		Alert alertDialog=new Alert(Alert.AlertType.INFORMATION);
		alertDialog.setTitle("My first desktop app");
		alertDialog.setHeaderText("Learning JavaFX");
		alertDialog.setContentText("I am just a beginner but soon I will be a pro and start developing applications");
		//to create buttons manually
		ButtonType yesBtn=new ButtonType("Yes");
		ButtonType noBtn=new ButtonType("No");
		alertDialog.getButtonTypes().setAll(yesBtn,noBtn);
		//optional is a container which will contain a object of the buttontype
		Optional<ButtonType> clickBtn=alertDialog.showAndWait();//waits for the result and the result will be click events of the two buttons. Retuns which button will be clicked and based on that performs our own custom action.
		if(clickBtn.isPresent() && clickBtn.get()==yesBtn){
			System.out.println("Yes button clicked");
		}
		else{
			System.out.println("No button clicked");
		}

		alertDialog.show();
	}

	@Override
	public void stop() throws Exception {
		System.out.println("stop");//called when application is stopped and is about to shut down
		super.stop();
	}
}
