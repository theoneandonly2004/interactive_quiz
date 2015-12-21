package QuizProgram.ServerComponents.Points;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Daniel on 17/06/2015.
 */
public class Server
{
   public static final int PORT=2004;
   public static void main(String[] args)  throws IOException
   {
      new ServerFrame("frame");
      PointsServer.setupList();
      PointsServer.setupServer();

   }//main

   public void runServer() throws IOException
   {
      int currentArrayPosition=0;
      ArrayList<TeamThread>teamList=new ArrayList<TeamThread>();
      ServerSocket serverSocket=new ServerSocket(PORT);
      System.out.println("now searching for valid client");

      while(true) {
         Socket socket = serverSocket.accept();
        teamList.add(new TeamThread(socket));

      }
   }
}//class
