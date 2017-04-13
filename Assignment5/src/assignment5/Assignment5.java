/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment5;

import java.io.InputStream;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class Assignment5 extends Application {
    protected int n = 0;
    protected int num = 0;
    protected Dish[] dishes;
    protected Label name;
    protected Image image;
    protected Label desc;
    protected Label price;
    protected GridPane grid = new GridPane();
    protected Button btn1 = new Button();
    protected Button btn2 = new Button();
    protected ImageView imageV = new ImageView();
			
    @Override
    public void start(Stage primaryStage) {
        
        final String resourcesPath = "resources/config.txt";
        InputStream stream = Assignment5.class.getResourceAsStream(resourcesPath);
        Scanner input = new Scanner(stream);
	while(input.hasNextLine()){
            String line = input.nextLine();
            num++;
	}
	num = num/4;
        dishes = new Dish[num];
		
	for(int j = 0; j<num; j++){
            dishes[j] = new Dish();
       	}
		
        //grid construcution        
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(50);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(50);
        grid.getColumnConstraints().addAll(column1, column2);
        
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(10);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(70);
        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(10);
        RowConstraints row4 = new RowConstraints();
        row4.setPercentHeight(10);
        grid.getRowConstraints().addAll(row1,row2,row3,row4);
		
		
		
		
	//loop to collects variables from text to array of dishes
        final String resourcesPath2 = "resources/config.txt";
        InputStream stream2 = Assignment5.class.getResourceAsStream(resourcesPath2);
        Scanner input2 = new Scanner(stream2);
	int counter = 0;
        int i = 0;
	String hold;
	String[] part;
	while(input2.hasNextLine()){
            if (counter == 4){
                i++;
		counter = 0;
            }
            hold = input2.nextLine();
            if(hold != null){
                part = hold.split(":");
                switch(part[0]){
                    case "Name": dishes[i].name = part[1]; break;
                    case "Image": dishes[i].image = part[1]; break;
                    case "Description": dishes[i].desc = part[1]; break;
                    case "Price": dishes[i].price = part[1]; break;
                    default: break;
                }
            }
            counter++;
	}
	
	Scene scene = makeScene();
        
	primaryStage.setTitle("Menu Kiosk");
	primaryStage.setScene(scene);
	primaryStage.show();
	
	//button actions
	btn1.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent event) {
                preBtn();
            }
        });
	btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nextBtn();
            }
        });
    }
	
    public void preBtn(){
        if(num>1){
            if(n>0) n--;
            else n=num-1;
            update();
        }
    }
	
    public void nextBtn(){
        if(num>1){
            if(n<num-1)n++;
            else n=0;
            update();
        }
    }

    public Scene makeScene(){
        final String resourcesPath = "resources/"+dishes[0].image;
        InputStream stream = Assignment5.class.getResourceAsStream(resourcesPath);
	// adding of variables from text
        name = new Label(dishes[0].name);
        name.setWrapText(true);
        image = new Image(stream);
        desc = new Label(dishes[0].desc);
        desc.setWrapText(true);
        price = new Label(dishes[0].price);
        price.setWrapText(true);
        
        imageV.setImage(image);
        imageV.setFitWidth(250);
        imageV.setFitHeight(300);
        
        //Button addition
        btn1.setText("Preivous");
        grid.add(btn1,0,3);
        btn2.setText("Next");
        grid.add(btn2,1,3);
        grid.setHalignment(btn2, HPos.RIGHT);    
        
        
        grid.add(name,0,0);
	grid.add(imageV,0,1);
        grid.setValignment(imageV, VPos.TOP);
        grid.add(desc,1,1);
	grid.setValignment(desc, VPos.TOP);
	grid.add(price,1,2);
	grid.setHalignment(price, HPos.CENTER);
        
	Scene scene = new Scene(grid,500,500);
	return scene;
    }
	
    public void update(){
        final String resourcesPath = "resources/"+dishes[n].image;
        InputStream stream = Assignment5.class.getResourceAsStream(resourcesPath);
	name.setText(dishes[n].name);
	image = new Image(stream);
	desc.setText(dishes[n].desc);
	price.setText(dishes[n].price);
        imageV.setImage(image);
    }
	
    public static void main(String[] args) {
        launch(args);
    }
}
