package QuizProgram.ServerComponents.Points;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Daniel on 16/06/2015.
 */
public class Client {
   public static void main(String[] args)
   {

      PrintWriter toServer;
      Scanner fromClient;
      Scanner keyboard=new Scanner(System.in);
      Socket socket;
      String entered;
      int repeats,scoreInput;

      try {


         socket = new Socket("localhost", 2004);
         fromClient = new Scanner(socket.getInputStream());
         toServer = new PrintWriter(socket.getOutputStream(), true);


         while (true) {
            System.out.println("enter add");
            entered = keyboard.nextLine();
            toServer.println(entered);

            if(entered.equals("add")) {

               System.out.println(fromClient.nextLine());
               //toServer.println("get");
               entered = keyboard.nextLine();

               toServer.println(entered);

            }
            else if(entered.equals("get"))
            {

               System.out.println("now entered get state");
               repeats = fromClient.nextInt();
               System.out.println("number of repeats " + repeats);
               String fromClientFC;
              // fromClient.nextLine();
               fromClientFC=fromClient.nextLine();

               for (int count = 0; count < repeats; count++)
               {

                  fromClientFC=(String)fromClient.nextLine();
                  System.out.println(fromClientFC);
                  fromClientFC=(String)fromClient.nextLine();
                  System.out.println(fromClientFC + "\n");
               }
            }
            else if(entered.equals("update"))
            {
               System.out.println(fromClient.nextLine());
               entered = keyboard.nextLine();
               toServer.println(entered);

               //System.out.println(fromClient.nextLine());
               scoreInput = keyboard.nextInt();
               toServer.println(scoreInput);

            }
            else if(entered.equals("remove"))
            {
               System.out.println(fromClient.nextLine());
               entered = keyboard.nextLine();
               toServer.println(entered);
            }
         }
      }
      catch(Exception ex)
      {
         System.out.println(ex.getMessage());
      }








   }//main
}//class
