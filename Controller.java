package com.wang.myDB;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller  {
	
	@FXML
	private ChoiceBox<AlbumSearchType> choiceBoxSearch;
	
	@FXML
	private ListView<Album> listView;
	
	@FXML
	private TextField tfTitle;
	
	@FXML
	private TextField tfScore;

	@FXML
	private ChoiceBox<AlbumGenreType> choiceBoxGenre;
	
	@FXML
	private TextField tfReleased;
	
	@FXML
	private TextField tfArtist;
	
	@FXML
	private TextField tfNation;
	
	@FXML
	private Button btAdd;
	
	@FXML
	private Button btCancel;
	
	private Library model;
	
	public Controller(){}
	
	public Controller(Library model, Stage primaryStage){
		
		this.model = model;
		primaryStage.setOnCloseRequest(e -> model.close());
		
	}
	
	public void initialize(){
		
		choiceBoxSearch.getItems().setAll(AlbumSearchType.values());
		choiceBoxSearch.getSelectionModel().selectFirst();
		listView.getItems().setAll(model.showAll());
		choiceBoxGenre.getItems().setAll(AlbumGenreType.values());
		choiceBoxGenre.getSelectionModel().selectFirst();
		
	}
	
	@FXML
	public void onSearch(ActionEvent event){
		String textForSearch = ((TextField)event.getSource()).getText();
		listView.getItems().setAll(model.search(choiceBoxSearch.getValue(), textForSearch));
		
	}
	
	public void clearText() {
		tfTitle.clear();
		tfScore.clear();
		choiceBoxGenre.getSelectionModel().selectFirst();
		tfReleased.clear();
		tfArtist.clear();
		tfNation.clear();
		
	}
	
	@FXML
	private void handleButtonAction(ActionEvent event) {
		
		
		if(event.getSource() == btAdd){
			String title = tfTitle.getText();
			int score = Integer.parseInt(tfScore.getText());
			String genre = choiceBoxGenre.getValue().toString();
			int released = Integer.parseInt(tfReleased.getText());
			String artistsSequence = tfArtist.getText();
			String[] artists = artistsSequence.split(";");
			String nationSequence = tfNation.getText();
			String[] nations = nationSequence.split(";");
			System.out.println(title);
			System.out.println(score);
			System.out.println(released);
			System.out.println(genre);
			for(int i = 0; i < artists.length; i ++){
			System.out.println(artists[i]);
			System.out.println(nations[i]);
			}
			
			model.addNewAlbum(title, score, genre,released, artists,nations);
			
			this.clearText();
		}
		else{
			/**
			 * Clear all the text/choice in the frame, when user click "Cancel" button
			 */
			this.clearText();
			
		}
		
		
	}

	

	
	
	/*public void onAdd(ActionEvent event){
		//model.addAlbum(listView.getSelectionModel().getSelectedItem().getUniqueID());
		Stage stage;
		Parent target = null;
		if(event.getSource()==addBtn){
		try {
			stage = new Stage();
			target = FXMLLoader.load(getClass().getResource("addWindow.fxml"));
			stage.setScene(new Scene(target)); //将场景载入舞台；  
			stage.showAndWait(); //显示窗口；  
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		}
		
	}*/
	/*
	public void onDelete(){
		model.deleteAlbum(listView.getSelectionModel().getSelectedItem().getUniqueID());
	}
*/
}
