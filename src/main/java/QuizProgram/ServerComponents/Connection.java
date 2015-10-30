package QuizProgram.ServerComponents;

import QuizProgram.ServerComponents.Points.TeamThread;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Daniel on 03/07/2015.
 */
public class Connection
{
   public Socket socket;
   public ServerSocket serverSocket;
   public PrintWriter toServer;
   public Scanner fromServer;
   public TeamThread thread;

   public Connection()
   {
      try
      {
         serverSocket=new ServerSocket(2004);
         System.out.println("looking for connection");
         socket=serverSocket.accept();
         System.out.println("connected");
         toServer=new PrintWriter(socket.getOutputStream());
         fromServer=new Scanner(socket.getInputStream());

         thread=new TeamThread(socket);
         thread.start();

      }catch(Exception ex){}
   }
}//class
