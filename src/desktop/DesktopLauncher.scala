package desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import core.MyGdxGame

object DesktopLauncher {
  def main(args: Array[String]){
    val config = new LwjglApplicationConfiguration();
    config.foregroundFPS = 60;
    config.backgroundFPS = 60;
    config.title = "Ultra Simple Pong"
    new LwjglApplication(new MyGdxGame(), config);
  }
}