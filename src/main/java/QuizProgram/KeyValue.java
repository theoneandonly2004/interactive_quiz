package QuizProgram;

import QuizProgram.Frames.GameSelectFrame;
import QuizProgram.Frames.Team;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

/**
 * Created by Daniel on 19/12/2014.
 */
public class KeyValue
{
   public static Team team;
   public static GameSelectFrame selectFrame;
   private static PrintWriter toServer;
   private static Scanner fromServer;
   private static Socket socket;
   public static final String RESOURCEPATH= "src\\Resources\\TextFiles\\";
   public static final String IMAGEPATH= "src/Resources/Images/";
   public static final String QUESTIONPATH=RESOURCEPATH+"Questions";
   public static final String fileBeginning= "src/QuizProgram/Profiles/";
   public static final String OPTION="OPTIONS",QUESTION="QUESTION",ANSWER="ANSWERS",END="END";
   public static final String FILEPATH="FILEPATH";
   //public static String address="localhost";

   public static void setupServer()
   {
      final int PORT=2004;

      InetAddress localAddress;
      System.out.println("entered setup server");
      String address=(String)JOptionPane.showInputDialog(null,"please enter the IP address\n \t\tof the server");



      int selected;


      if(address.equals(""))
      {
         address="localhost";
      }

      try {
         socket = new Socket(address, PORT);

         System.out.println(address);
         if(socket.isConnected())
         {
            System.out.println("connected");
            toServer = new PrintWriter(socket.getOutputStream(), true);
            fromServer = new Scanner(socket.getInputStream());
         }
         else
         {
            JOptionPane.showConfirmDialog(null,"the server may be down \n try again?");
         }


      }catch(IOException ex)
      {
         selected=JOptionPane.showConfirmDialog(null,"try again? Possible issues may include\n 1)invalid address \n 2) the server may be down");

         if(selected==JOptionPane.YES_OPTION)
         {
            setupServer();
         }
         else
         {
           selected=JOptionPane.showConfirmDialog(null,"are you sure? this will close the program");

            if(selected==JOptionPane.YES_OPTION)
            {
               System.exit(0);
            }
            else
            {
               setupServer();
            }
            System.exit(0);
         }

      }
   }

   public static PrintWriter getToServer() {
      return toServer;
   }

   public static Scanner getFromServer() {
      return fromServer;
   }

   public static Socket getSocket() {
      return socket;
   }
}//class
