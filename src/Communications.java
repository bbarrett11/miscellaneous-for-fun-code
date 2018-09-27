import java.io.*;
import java.util.*;
import gnu.io.*;

public class Communications {
static Enumeration portList;
static CommPortIdentifier portId;
static String messageString = "color FF00FFEND";
static SerialPort serialPort;
static OutputStream outputStream;
static BufferedReader inputStream;


public static void main(String[] args) throws InterruptedException {
    portList = CommPortIdentifier.getPortIdentifiers();


    while (portList.hasMoreElements()) {

        portId = (CommPortIdentifier) portList.nextElement();
        if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {

             if (portId.getName().equals("COM1")) {

                try {
                    serialPort = (SerialPort)
                        portId.open("SimpleWriteApp", 2000);
                } catch (PortInUseException e) {System.out.println("err");}
                try {
                    outputStream = serialPort.getOutputStream();
                    inputStream = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));

                } catch (IOException e) {System.out.println("err1");}
                try {
                    serialPort.setSerialPortParams(96000,
                        SerialPort.DATABITS_8,
                        SerialPort.STOPBITS_1,
                        SerialPort.PARITY_NONE);
                } catch (UnsupportedCommOperationException  e) {System.out.println("err2");}
                try {
                    Thread.sleep(4000);
                   for(int h =0; h < 100;h++)
                    {
                	    BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\Users\\Student\\Desktop\\NEx.csv")));
                	    reader.readLine();
                	    while(true)
                	    {
                	    	if(reader.ready())
                	    	{
                	    	String[] s = reader.readLine().split(", ");
                	    	System.out.println(s[12]);
                	    	if(new Long(s[12])>220)
                            	outputStream.write(57);
                	    		
                	    	}
                	    }

                    }
        outputStream.close();
        inputStream.close();
        serialPort.close();

                } catch (IOException e) {System.out.println("err3 "+e.getMessage());}
            }
        }
    }
}
}