package com.albom.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MainWindowController {

	@FXML
	private TextField textDateTime;

	@FXML
	private TextField textDateTimeUtc;

	@FXML
	private TextField textTimestamp;

	@FXML
	private TextField textDayOfYear;

	@FXML
	private TextField textGpsWeek;

	public void setDateTime(String text) {
		textDateTime.setText(text);
	}

	public void setDateTimeUtc(String text) {
		textDateTimeUtc.setText(text);
	}

	public void setTimeStamp(String text) {
		textTimestamp.setText(text);
	}

	public void setDayOfYear(String text) {
		textDayOfYear.setText(text);
	}

	public void setGpsWeek(String text) {
		textGpsWeek.setText(text);
	}

}
