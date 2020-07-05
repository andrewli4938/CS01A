
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;


/** A quick test of playing sounds.
 *
 * This code plays the sound three times if run with JDK 1.4 but only once 
 * if played with JDK 1.5.
 *
 * @author Byron Weber Becker */
public class SoundTest
{
	public synchronized void test()
	{	try 
	   {  URL url = new URL("file:/" + System.getProperty("user.dir") + "/ringin.wav");
			System.out.println(url);
			
	   	AudioClip sound = Applet.newAudioClip(url);


	   	sound.play();
	   	this.wait(1000);
	   	sound.play();
	   	this.wait(1000);
	   	sound.play();
	   	this.wait(1000);
	   }
	   catch (Exception ex) 
	   {	ex.printStackTrace();
	   }
	}
	
	
	public static void main(String[] args)
   {  
	   SoundTest s = new SoundTest();
	   s.test();
   }
}
