
package com.albom.application;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

import javax.imageio.ImageIO;

import com.albom.gui.MainWindowController;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DateTimeInformerApplication extends Application {

	private SystemTray tray;
	private TrayIcon trayIcon;
	private Stage stage = null;
	private MainWindowController controller = null;
	private final String PROGRAM_NAME = "DateTime Informer v0.1";

	public static void main(String[] args) {
		try {
			launch(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createTrayIcon() {

		if (!SystemTray.isSupported()) {
			System.out.println("SystemTray is not supported");
			return;
		}

		Image image = null;
		try {
			image = ImageIO.read(getClass().getResource("/ui/images/calendar-clock.png"));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		trayIcon = new TrayIcon(image, PROGRAM_NAME, createMenu());
		tray = SystemTray.getSystemTray();

		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			e.printStackTrace();
			return;
		}

		trayIcon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						if (stage.isShowing()) {
							stage.hide();
						} else {

							setDateTime();

							stage.show();

							Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
							stage.setX(primScreenBounds.getWidth() - stage.getWidth());
							stage.setY(primScreenBounds.getHeight() - stage.getHeight());

						}
					}
				});

			}
		});

	}

	private void setDateTime() {
		Instant now = Instant.now();
		LocalDateTime sys = LocalDateTime.ofInstant(now, ZoneId.systemDefault());
		LocalDateTime utc = LocalDateTime.ofInstant(now, ZoneId.of("Z"));
		controller.setDateTime(sys.toString());
		controller.setDateTimeUtc(utc.toString());
		controller.setTimeStamp(String.valueOf(utc.atZone(ZoneId.of("Z")).toEpochSecond()));
		controller.setDayOfYear(String.valueOf(utc.get(ChronoField.DAY_OF_YEAR)));
		controller.setGpsWeek(
				String.valueOf(ChronoUnit.DAYS.between(LocalDate.of(1980, 1, 13), utc.toLocalDate()) / 7 + 1));
	}

	private PopupMenu createMenu() {
		PopupMenu popup = new PopupMenu();
		MenuItem exitItem = new MenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
		popup.add(exitItem);
		return popup;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		stage = primaryStage;

		GridPane pane = null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/mainWnd.fxml"));
			pane = (GridPane) loader.load();
			controller = (MainWindowController) loader.getController();
		} catch (IOException e) {
			System.out.println(e);
		}

		Scene scene = new Scene(pane, 320, 200);
		stage.setTitle(PROGRAM_NAME);
		stage.setScene(scene);
		stage.initStyle(StageStyle.UTILITY);
		stage.setAlwaysOnTop(true);

		createTrayIcon();
		Platform.setImplicitExit(false);

	}

	@Override
	public void stop() {
		tray.remove(trayIcon);
	}

}