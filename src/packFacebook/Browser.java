
package packFacebook;

import java.sql.SQLException;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.scope.ScopeBuilder;

import com.restfb.types.User;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import packGestores.GestorUsuarios;
import packModelo.BarBestial;
import packModelo.EnumColor;
import packModelo.JugadorReal;
import packModelo.Partida;

public class Browser extends Region {

  public static final String SUCCESS_URL = "https://www.facebook.com/connect/login_success.html";
  final WebView browser = new WebView();
  final WebEngine webEngine = browser.getEngine();
  private String code;

  private final String appId;

  private final String appSecret;

  public Browser(String appId, String appSecret) {
    this.appId = appId;
    this.appSecret = appSecret;
    // add the web view to the scene
    getChildren().add(browser);
  }

  public void showLogin() {
    DefaultFacebookClient facebookClient = new DefaultFacebookClient(Version.LATEST);
    ScopeBuilder scopes = new ScopeBuilder();
    String loadUrl = facebookClient.getLoginDialogUrl(appId, SUCCESS_URL, scopes);
    webEngine.load(loadUrl + "&display=popup&response_type=code");
    webEngine.getLoadWorker().stateProperty().addListener(
      (ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) -> {
        if (newValue != Worker.State.SUCCEEDED) {
          return;
        }

        String myUrl = webEngine.getLocation();

        if ("https://www.facebook.com/dialog/close".equals(myUrl)) {
          System.out.println("dialog closed");
          System.exit(0);
        }

        if (myUrl.startsWith(SUCCESS_URL)) {
          int pos = myUrl.indexOf("code=");
          code = myUrl.substring(pos + "code=".length());
          FacebookClient.AccessToken token = facebookClient.obtainUserAccessToken(appId, appSecret, SUCCESS_URL, code);
          System.out.println("Accesstoken: " + token.getAccessToken());
          System.out.println("Expires: " + token.getExpires());
          
         FacebookClient  facebookClient1 = new DefaultFacebookClient(token.getAccessToken(), Version.LATEST);
         User user =  facebookClient1.fetchObject("me", User.class, Parameter.with("fields","name,email"));
    
         try {
			if (!GestorUsuarios.getGestorUsuarios().existeUsuario(user.getEmail())){
				 BarBestial.getBarBestial().registrarUsuario(user.getEmail(), user.getName(),null);
			 } else{
				Partida.getMiPartida().anadirJugador(new JugadorReal(user.getEmail(),user.getName(), EnumColor.AZUL));
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
       }
      });
  }
}