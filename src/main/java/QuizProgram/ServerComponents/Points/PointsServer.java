package QuizProgram.ServerComponents.Points;

import QuizProgram.Frames.Team;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Daniel on 16/06/2015.
 */
public class PointsServer
{
   private static int lastArrayPosition=0;
   protected static ArrayList<TeamThread>threadsList;
   protected static ArrayList<Team>teamsList;
   protected static PrintWriter toClient;
   protected static Scanner fromClient;
 //  protected static ServerSocket serverSocket;
 //  protected static Socket socket;

   public static synchronized void setupList()
   {
      teamsList=new ArrayList<Team>();
      threadsList=new ArrayList<TeamThread>();
   }

   public static int findTeam(String pName)
   {
      int count=0;

      while(count<teamsList.size())
      {
         if(teamsList.get(count).getTeamName().equals(pName))
         {
            return count;
         }
         count++;
      }
      return -1;
   }

   public static synchronized ArrayList<Team> getTeams()
   {
      return teamsList;
   }

   public static synchronized void addTeam(String teamName)
   {
      teamsList.add(new Team(teamName));
   }

   public static synchronized void addTeam(String teamName, int initialScore)
   {
      teamsList.add(new Team(teamName,initialScore));
   }

   public static synchronized void addTeamScore(String teamName, int addScore)
   {
      boolean found=false;
      int count=0;
      String currentTeamName="";
      Team currentTeam;

      while(!found && count<teamsList.size())
      {
        currentTeam=teamsList.get(count);
         currentTeamName=currentTeam.getTeamName();

         if(currentTeamName.equals(teamName))
         {
            currentTeam.setScore(currentTeam.getScore()+addScore);
            found=true;
         }
      }
   }

   public static void removeTeam(String pName)
   {
     int position=findTeam(pName);

      if(position != -1) {
         System.out.println("found team name is " + PointsServer.teamsList.get(position).getTeamName());
         teamsList.remove(position);
         threadsList.remove(position);
         lastArrayPosition--;

      }
      else
      {
         System.out.println("the team " + pName + " was not found to remove");
      }
   }

   public static void setupServer()
   {

         try {
            System.out.println("setting up server");
            ServerSocket serverSocket = new ServerSocket(2004);

            while(true)
            {
               System.out.println("waiting for connection");
               Socket socket = serverSocket.accept();
               System.out.println("connection established");
               threadsList.add(new TeamThread(socket));
               threadsList.get(lastArrayPosition).start();
               lastArrayPosition++;
            }


         } catch (Exception ex) {

         }
      }


   }





