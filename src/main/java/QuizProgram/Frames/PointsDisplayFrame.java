package QuizProgram.Frames;

import QuizProgram.KeyValue;
import QuizProgram.ServerComponents.Points.ButtonInfo;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Daniel on 08/07/2015.
 */
public class PointsDisplayFrame extends JFrame
{
   private ArrayList<Team>teamList;
   private JPanel[]panelList;
   private JLabel[]teamNameList;
   private JLabel[]teamScoreList;

   private void setupDisplayPanels()
   {
      for(int count=0;count<panelList.length;count++)
      {
         panelList[count]=new JPanel(new GridLayout(0,2));
         teamNameList[count]=new JLabel(teamList.get(count).getTeamName());
         teamScoreList[count]=new JLabel("" + teamList.get(count).getScore());
      }

      for(int count=0;count<panelList.length;count++)
      {
         panelList[count].add(teamNameList[count]);
         panelList[count].add(teamScoreList[count]);
         this.add(panelList[count]);
      }
   }

   private void getTeams()
   {
      int repeats;
      PrintWriter toServer=KeyValue.getToServer();
      Scanner fromServer=KeyValue.getFromServer();


      toServer.println("get");


      try {
         repeats=fromServer.nextInt();
         String teamName;
         int score;

         fromServer.nextLine();


         for (int count = 0; count < repeats; count++)
         {

            teamName =  fromServer.nextLine();
            score = fromServer.nextInt();
            fromServer.nextLine();

            teamList.add(new Team(teamName, score));
            // fromClientFC=(String)fromServer.nextLine();
           // fromServer.nextLine();

         }

      }
      catch(InputMismatchException ex)
      {

      }
   }

   private void findTeam()
   {
      int count=0;
      boolean found=false;

      while(count<teamList.size() && !found)
      {
         if(teamList.get(count).getTeamName().equals(KeyValue.team.getTeamName()))
         {
            KeyValue.team=teamList.get(count);
         }
      }
   }

   public PointsDisplayFrame(String title) throws HeadlessException
   {
      super(title);

      int numberOfTeams;
      teamList=new ArrayList<Team>();

      getTeams();


     numberOfTeams =teamList.size();

      panelList=new JPanel[numberOfTeams];
      teamNameList=new JLabel[numberOfTeams];
      teamScoreList=new JLabel[numberOfTeams];
      setupDisplayPanels();



      this.setLayout(new GridLayout(0,1));
      //this.setLayout(new FlowLayout());

      this.pack();
      this.setVisible(true);
   }
}//class
