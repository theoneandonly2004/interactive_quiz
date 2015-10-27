package QuizProgram.ServerComponents.Points;

import QuizProgram.Frames.Team;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Daniel on 16/06/2015.
 */
public class TeamThread extends Thread {
   protected PrintWriter toClient;
   protected Scanner fromClient;
   protected Socket socket;

   public TeamThread() {
   }

   public TeamThread(Socket pSocket) {
      //super(name);
      // toClient=pWriter;
      // fromClient=pReader;

      socket = pSocket;
      System.out.println("threads setup");
   }

   private void addTeam() {
      String teamName;
      ArrayList<Team> teamList;

    //  toClient.println("team now being added please enter a team Name");
      teamName = fromClient.nextLine();
      PointsServer.addTeam(teamName);
      teamList = PointsServer.getTeams();
      System.out.println(teamList.size());
   }



   private void getTeam() {

      ArrayList<Team> teamList;
      System.out.println("now retrieving requested info");

      teamList = PointsServer.getTeams();
      System.out.println("number of repeats " + teamList.size());
      toClient.println(teamList.size());       //repeats for client side

      //toClient.println("now retrieving and sending requested data");
      for (int count = 0; count < teamList.size(); count++) {
         System.out.println("running loop now with " + count + " repetitions");

         // toClient.println(teamList.size());
         toClient.println(teamList.get(count).getTeamName());
         toClient.println(teamList.get(count).getScore());

         //System.out.println(teamList.size());
         System.out.println(teamList.get(count).getTeamName());
         System.out.println(teamList.get(count).getScore());

      }
   }

   private void updateTeam() {
      String name;
      int teamPosition, newScore;
     // toClient.println("please enter a team name");
      name = fromClient.nextLine();

    //  toClient.println("please enter a new team score");
      newScore = fromClient.nextInt();
      // fromClient.nextLine();

      teamPosition = PointsServer.findTeam(name);

      if (teamPosition != -1) {
         PointsServer.teamsList.get(teamPosition).setScore(newScore);
      }
   }


   @Override
   public void run() {
      super.run();
      String clientInput = "";
      ArrayList<Team> teamList = null;
      Random rand = new Random();
      String teamName;
      int score;
      boolean continueing = true;

      try {
         toClient = new PrintWriter(socket.getOutputStream(), true);
         fromClient = new Scanner(socket.getInputStream());
        System.out.println(socket.getInetAddress().toString());
      } catch (Exception ex) {}


         //teamList.add(new Team("my team"));


         while (continueing) {
            // try {


            //sleep(rand.nextInt(10000));

            if (teamList == null) {
               teamList = PointsServer.getTeams();
            }

            try {
               clientInput = fromClient.nextLine();

               if (clientInput.equals("get") && teamList != null) {
                  getTeam();
               }

               if (clientInput.equals("add")) {
                  addTeam();
               }

               if (clientInput.equals("update")) {
                  updateTeam();
               }

               if(clientInput.equals("flush"))
               {
                  toClient.flush();
               }

               if (clientInput.equals("remove")) {
                  String name;
                  int teamPosition;
                  toClient.println("please enter a team name to remove");
                  name = fromClient.nextLine();
                  PointsServer.removeTeam(name);
               }

               clientInput = null;

               // clientInput=null;


               // } catch (Exception ex) {
               //System.out.println(ex.getMessage());
            } catch (Exception error) {
               fromClient.close();
               System.out.println("thread now closed");
               clientInput = "end";
               continueing = false;
            }
         }
      }
   }


