package packFacebook;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Facebook extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Facebook Login");
		String appId = "726061564446077";
	    String appSecret = "01f510e45530ee03fe20062b736a4ded";
	    Browser facebookBrowser = new Browser(appId, appSecret);
	    Scene scene = new Scene(facebookBrowser, 900, 600, Color.web("#666970"));
	    primaryStage.setScene(scene);
		primaryStage.setResizable(false);
	    primaryStage.show();
	    facebookBrowser.showLogin();
	}
}
