package fr.insa_rennes.sdd.javafx.controller;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import fr.insa_rennes.sdd.javafx.view.Rendering;
import fr.insa_rennes.sdd.seam_carving.Picture;
import fr.insa_rennes.sdd.seam_carving.SeamCarver;
import fr.insa_rennes.sdd.seam_carving.SeamCarverDP;
import fr.insa_rennes.sdd.seam_carving.SeamCarverDijkstra;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

public class Controller implements Initializable {
	@FXML
	private StackPane pane;
	@FXML
	private Canvas canvas;
	@FXML
	private ImageView imageView;
	@FXML
	private CheckMenuItem horizontalSeamMenu;
	@FXML
	private CheckMenuItem verticalSeamMenu;
	private GraphicsContext gc;
	private Optional<SeamCarver> seamCarver = Optional.empty();
	private double zoomRatio = 1;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gc = canvas.getGraphicsContext2D();		
		canvas.widthProperty().bind(pane.widthProperty());
		canvas.heightProperty().bind(pane.heightProperty());
		canvas.widthProperty().addListener(this::resize);
		canvas.heightProperty().addListener(this::resize);
		pane.setOnKeyPressed(e -> keyPressed(e));
		pane.setOnKeyPressed(e -> keyPressed(e));
		draw();
	}
	
	private void resize(Observable o) {						
		seamCarver.ifPresent(c -> {
			int w = (int) (canvas.getWidth() / zoomRatio);
			int h = (int) (canvas.getHeight() / zoomRatio);			
			if (w < c.picture().width() || h < c.picture().height()) {
				c.reduceToSize(w, h);
			}
		});
		draw();
	}
	
	private void draw() {	
		pane.requestFocus();
		Rendering.draw(gc, seamCarver, zoomRatio, horizontalSeamMenu.isSelected(), verticalSeamMenu.isSelected());
	}

	private void keyPressed(KeyEvent e) {
		if (e.getCode() == KeyCode.LEFT) {
			seamCarver.ifPresent(c -> c.reduceToSize(c.picture().width() - 1, c.picture().height()));
			draw();
		} else if (e.getCode() == KeyCode.DOWN) {
			seamCarver.ifPresent(c -> c.reduceToSize(c.picture().width(), c.picture().height() - 1));
			draw();
		}
	}
	
	private static final int MAX_SIZE = 300;

	@FXML
	private void loadImage() {	
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Image File");		
		File file = fileChooser.showOpenDialog(pane.getScene().getWindow());
		seamCarver = Optional.ofNullable(file)
				.map(f -> new SeamCarverDP(new Picture(new Image(f.toURI().toString(), MAX_SIZE, MAX_SIZE, true, true))));
		if (seamCarver.isPresent()) {
			Image image = seamCarver.get().picture().image();
			zoomRatio = Math.min(canvas.getWidth() / image.getWidth(), canvas.getHeight() / image.getHeight());
		}
		draw();
	}
	
	@FXML
	public void showVerticalSeam() {
		draw();
	}

	@FXML
	public void showHorizontalSeam() {
		draw();
	}	
/*
	private static void error(String msg) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("An error occured");
		alert.setContentText(msg);
		alert.showAndWait();
	}

	private static Optional<String> dialog(String title, String header, String content) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle(title);
		dialog.setHeaderText(header);
		dialog.setContentText(content);
		return dialog.showAndWait();
	}

	private static void alert(String title, String header, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
	*/

}