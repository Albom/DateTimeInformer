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
	private TextField textYear;

	@FXML
	private TextField textGpsDay;

	@FXML
	private TextField textGpsWeek;

	@FXML
	private TextField textGpsHour;

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

	public void setYear(String text) {
		textYear.setText(text);
	}

	public void setGpsHour(String text) {
		textGpsHour.setText(text);
	}

	public void setGpsDay(String text) {
		textGpsDay.setText(text);
	}

}
