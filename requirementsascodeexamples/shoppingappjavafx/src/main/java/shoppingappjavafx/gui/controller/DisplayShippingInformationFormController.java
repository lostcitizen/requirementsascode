package shoppingappjavafx.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import shoppingappjavafx.domain.ShippingInformation;
import shoppingappjavafx.usecase.userevent.EnterShippingInformation;
import shoppingappjavafx.usecase.userevent.SignalToGoBack;

public class DisplayShippingInformationFormController extends AbstractController{
	private ShippingInformation shippingInformation;
	
    @FXML
    private Button backButton;
	
    @FXML
    private VBox vBox;
	
    @FXML
    private Button confirmButton;
    
    @FXML
    private TextField nameField;
    
    @FXML
    private TextField streetField;
    
    @FXML
    private TextField zipField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField stateField;
    
    @FXML
    private TextField countryField;
    
    @FXML
    void onConfirm(ActionEvent event) {
    	EnterShippingInformation enterShippingInformation =  new EnterShippingInformation(shippingInformation);
    	useCaseModelRunner().reactTo(enterShippingInformation);
    }
    
	@FXML
    void onBack(ActionEvent event) {
		SignalToGoBack goBack = new SignalToGoBack();
    	useCaseModelRunner().reactTo(goBack);
    }
    
	public void displayShippingInformationForm(ShippingInformation shippingInformation) {
    	this.shippingInformation = shippingInformation;
    	
    	nameField.textProperty().bindBidirectional(shippingInformation.nameProperty());
    	streetField.textProperty().bindBidirectional(shippingInformation.streetProperty());
    	zipField.textProperty().bindBidirectional(shippingInformation.zipProperty());
    	cityField.textProperty().bindBidirectional(shippingInformation.cityProperty());
    	stateField.textProperty().bindBidirectional(shippingInformation.stateProperty());
    	countryField.textProperty().bindBidirectional(shippingInformation.countryProperty());
	}
}