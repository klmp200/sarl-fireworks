package io.sarl.demos.fireworks.gui;

import java.util.UUID;

import io.sarl.demos.fireworks.Firework;
import io.sarl.demos.fireworks.Positions;
import io.sarl.demos.fireworks.events.CreateArea;
import io.sarl.demos.fireworks.events.Exit;
import io.sarl.demos.fireworks.events.Freeze;
import io.sarl.demos.fireworks.events.SetupSettings;
import io.sarl.lang.core.Event;
import io.sarl.lang.core.EventListener;
import io.sarl.util.OpenEventSpace;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.util.Duration;

public class FXMLViewerController implements EventListener {
	
	private OpenEventSpace space;
	private final UUID id = UUID.randomUUID();
	
	private boolean launched = false;
	private boolean areaCreated = false;
	
	@FXML private Canvas draw_zone;
	
	@FXML private Label gravity_display;
	@FXML private Label rocket_quantity_display;
	@FXML private Label fire_quantity_display;
	
	@FXML private ScrollBar gravity_input;
	@FXML private ScrollBar rocket_quantity_input;
	@FXML private ScrollBar fire_quantity_input;
	
	@FXML private Button setup_button;
	@FXML private Button launch_button;
	@FXML private Button stop_button;
	
	public void cleanExit(){
		if (this.space != null)
			this.space.emit(new Exit());
	}
	
	public void setGUISpace(OpenEventSpace space) {
		this.space= space;
		this.space.register(this);
	}
	
	public double getGravity() {
		return gravity_input.getValue();
	}
	
	public int getRocketQuantity() {
		return (int) rocket_quantity_input.getValue();
	}
	
	public int getFireQuantity() {
		return (int) fire_quantity_input.getValue();
	}
	
	public void listenAndDraw(Positions grid){
		GraphicsContext gc = draw_zone.getGraphicsContext2D();
		PauseTransition wait = new PauseTransition(Duration.seconds(0.03));
	    wait.setOnFinished((e) -> {
	    	try {
	    		gc.clearRect(0, 0, draw_zone.getWidth(), draw_zone.getHeight());
				grid.getRockets().values().stream().filter(rocket->!rocket.getHidden()).forEach(rocket->{
					gc.setFill(rocket.getColor());
					gc.fillOval(rocket.getPosition().get(0), rocket.getPosition().get(1), 10, 10);
				});
				grid.getFire().values().forEach(fire->{
					gc.setFill(fire.getColor());
					fire.getPositions().forEach(pos->{
						gc.fillOval(pos.get(0), pos.get(1), 5, 5);
					});
				});
	    	} catch (Exception exception) {}
	        wait.playFromStart();
	    });
	    wait.play();
	}
	
	@FXML public void exitApplication(ActionEvent event) {
		space.emit(new Exit());
		Platform.exit();
	}
	
	@FXML protected void actionSetup(){
		SetupSettings event = new SetupSettings(
				this.getRocketQuantity(),
				this.getFireQuantity(),
				this.getGravity(),
				this.draw_zone.getWidth());
		if (!launched){
			launch_button.setDisable(false);
			Firework.main(this);
			launched = true;
			areaCreated = false;
		}
		this.space.emit(event);
	}

	@FXML protected void actionLaunch(){
		launch_button.setDisable(true);
		stop_button.setDisable(false);
		setup_button.setDisable(true);
		if (!areaCreated){
			this.space.emit(new CreateArea());
			this.areaCreated = true;
		} else {
			this.space.emit(new Freeze(false));
		}
	}

	@FXML protected void actionStop(){
		stop_button.setDisable(true);
		launch_button.setDisable(false);
		setup_button.setDisable(false);
		this.space.emit(new Freeze(true));
	} 
	@FXML protected void actionGravityDisplay(){
		gravity_input.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, 
				Number old_val, Number new_val){
					gravity_display.setText(String.format("%.1f", gravity_input.getValue()));
			}
		});
	}

	@FXML protected void actionRocketQuantityDisplay(){
		rocket_quantity_input.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, 
				Number old_val, Number new_val){
					rocket_quantity_display.setText(String.format("%.0f", rocket_quantity_input.getValue()));
			}
		});
		
	}

	@FXML protected void actionFireQuantityDisplay(){
		fire_quantity_input.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, 
				Number old_val, Number new_val){
					fire_quantity_display.setText(String.format("%.0f", fire_quantity_input.getValue()));
			}
		});
		
	}

	@Override
	public UUID getID() {
		// TODO Auto-generated method stub
		
		return this.id;
	}

	@Override
	public void receiveEvent(Event event) {
		/*
		if (event instanceof TestEvent){
			System.out.println("Guy recieved an event " + ((TestEvent) event).message);
			this.space.emit(new TestEventHack());
		}
		*/
	}
}
