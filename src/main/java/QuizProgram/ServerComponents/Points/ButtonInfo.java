package QuizProgram.ServerComponents.Points;

import QuizProgram.Frames.Team;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Daniel on 07/07/2015.
 */
public class ButtonInfo implements ActionListener
{
   private JFrame serverFrame;
   public Team currentTeam;
   private JButton disconnectButton;
   private JButton getScoreButton;
   private JButton updateScoreButton;
   private JLabel teamInfo;
   private JPanel panel;
   private JPanel mainPanel;

   private void setupButtons()
   {
      disconnectButton=new JButton("disconnect");
      getScoreButton=new JButton("get score");
      updateScoreButton=new JButton("update score");

      getScoreButton.addActionListener(this);
      disconnectButton.addActionListener(this);
      updateScoreButton.addActionListener(this);
   }

   private void setupLabel()
   {
      teamInfo=new JLabel();
      teamInfo.setText(currentTeam.getTeamName());
      //teamInfo.add(disconnectButton);
      //teamInfo.add(getScoreButton);
   }


   public void setupFrame()
   {
      panel=new JPanel(new GridLayout(1,4));
      panel.add(teamInfo);
      panel.add(disconnectButton);
      panel.add(getScoreButton);
      panel.add(updateScoreButton);

      ServerFrame.panelList.add(panel);
     // serverFrame.add(panel);
   }

   public ButtonInfo(JFrame pFrame, Team pTeam)
   {
      currentTeam=pTeam;
      serverFrame=pFrame;

      setupButtons();
      setupLabel();
     // setupFrame();
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      if(e.getSource().equals(getScoreButton))
      {
         JOptionPane.showMessageDialog(null,"team: " + currentTeam.getTeamName() + "\nscore: " + currentTeam.getScore());
      }
      else if(e.getSource().equals(disconnectButton))
      {
         int position = PointsServer.findTeam(currentTeam.getTeamName());
         int count=0;
         boolean found=false;
         if (position != -1)
         {
            TeamThread teamThread = PointsServer.threadsList.get(position);
            try
            {
              // PointsServer.threadsList.remove(position);

               teamThread.socket.close();
               teamThread.toClient.close();
               teamThread.fromClient.close();
               PointsServer.threadsList.remove(position);
               PointsServer.teamsList.remove(position);



               while(count<ServerFrame.teamButtonList.size()&& !found)
               {
                  if(ServerFrame.teamButtonList.get(count).currentTeam.getTeamName().equals(currentTeam.getTeamName()))
                  {
                     ServerFrame.teamButtonList.remove(count);
                     serverFrame.remove(ServerFrame.panelList.get(count));
                     ServerFrame.panelList.remove(count);
                     found=true;
                     System.out.println("deletion completed");
                  }
                  else
                  {
                     count++;
                  }
               }

              // new ServerFrame(ServerFrame.teamButtonList,ServerFrame.panelList);
               JOptionPane.showMessageDialog(null, "team " + currentTeam.getTeamName() + " has been disconected");
               //serverFrame.dispose();
            }
            catch (IOException ex) {}
         }
      }
      else if(e.getSource().equals(updateScoreButton))
      {
         int score=0;
         try {
            score = Integer.parseInt(JOptionPane.showInputDialog(null, "please enter how many points to award team " + currentTeam.getTeamName()));
         }
         catch(InputMismatchException ex)
         {
            score = Integer.parseInt(JOptionPane.showInputDialog(null, "please enter how many points to award team\n(please use a valid number) " + currentTeam.getTeamName()));
         }

         currentTeam.setScore(currentTeam.getScore() + score);
      }

   }
}//class
