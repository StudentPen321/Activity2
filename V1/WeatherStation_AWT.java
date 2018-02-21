/*
 * Initial Author
 *      Michael J. Lutz
 *
 * Other Contributers
 *      Eleazar Contreras
 
 * Acknowledgements
 */

/*
 * Class for a simple computer based weather station that reports the current
 * temperature (in Celsius) every second. The station is attached to a
 * sensor that reports the temperature as a 16-bit number (0 to 65535)
 * representing the Kelvin temperature to the nearest 1/100th of a degree.
 *
 * This class is implements Runnable so that it can be embedded in a Thread
 * which runs the periodic sensing.
 */

import java.awt.*;

public class WeatherStation_AWT implements Runnable{

   AWTUI gui;// Get object from AWTUI class
   private final KelvinTempSensor sensor;  // Temperature sensor.
   private final long PERIOD = 1000;  // 1 sec = 1000 ms.
   
   public WeatherStation_AWT(AWTUI page){
   
      this.gui = page;
      sensor = new KelvinTempSensor();// Get information from KelvinTempSenor class
      
   }
   
   @Override
   public void run() {// run() requires thread to run this class for constantly changing temperature. 
      
      int reading;
      double celsius; // actual sensor reading.
      double kelvin;  // sensor reading transformed to celsius
      final int KTOC = -27315; // Convert raw Kelvin reading to Celsius
      
         while(true) {
      
         try {
            Thread.sleep(PERIOD);
         
         }catch(Exception e) {} // ignore exceptions
      
         reading = sensor.reading();
         celsius = (reading + KTOC) / 100.0;
         kelvin = reading / 100.0;
      
      
      /*
             * System.out.printf prints formatted data on the output screen.
             *
             * Most characters print as themselves.
             *
             * % introduces a format command that usually applies to the
             * next argument of printf:
             *   *  %6.2f formats the "celsius" (2nd) argument in a field
             *      at least 6 characters wide with 2 fractional digits.
             *   *  The %n at the end of the string forces a new line
             *      of output.
             *   *  %% represents a literal percent character.
             *
             * See docs.oracle.com/javase/tutorial/java/data/numberformat.html
             * for more information on formatting output.
             */
            //
      
      
      
         this.gui.kelvinField.setText(String.format("%6.2f", kelvin));
         this.gui.celsiusField.setText(String.format("%6.2f", celsius));
      }
   }
   
    /*
     * Initial main method.
     *      Create the WeatherStation (Runnable).
     *      Embed the WeatherStation in a Thread.
     *      Start the Thread.
     */
     
   public static void main(String [] args){
      AWTUI otherpage = new AWTUI();//Call object from class 
      WeatherStation_AWT WSA = new WeatherStation_AWT(otherpage);//Put object in this class's parameter 
      Thread thread = new Thread(WSA);
      thread.start();
   }
   

}