package cars;
import javax.swing.JFrame;
import javax.sound.sampled.*;
import java.io.IOException;
import java.io.File;
class MusicThread extends Thread {

  private static void PlayMusic(String location) {

    try {
      File musicPath = new File(location);
      if (musicPath.exists()) {
        AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInput);
        clip.start();

      } else {
        System.out.println("Can't find file");
      }
    } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
      System.out.println(e);
    }
  }
  @Override
  public void run() {
    String filepath = "C:\\Users\\HP\\OneDrive - nitj.ac.in\\Desktop\\CarRacingGame\\src\\cars\\rockit_1.wav";

    MusicThread.PlayMusic(filepath);
  }
}
public class Cars extends JFrame {

  public static void execute() throws IOException, InterruptedException {

    JFrame app = new JFrame("ASSETTO CORSA");
    work w = new work();
    app.add(w);
    app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    app.setSize(650, 720);
    app.setVisible(true);
  }
  public static void main(String args[]) throws IOException, InterruptedException {
    Cars.execute();
  }

}
