package QuizProgram.ServerComponents.Points;

import QuizProgram.Frames.Team;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Daniel on 07/07/2015.
 */
public class ServerFrame extends JFrame implements ActionListener
{
   public static ArrayList<ButtonInfo>teamButtonList;
   public static ArrayList<JPanel>panelList;
   private JButton refreshButton;

   private JPanel mainPanel;
   int currentPosition=0;


   public void cycleButtonsList()
   {
      for(int count=0;count<teamButtonList.size();count++)
      {
         teamButtonList.get(count).setupFrame();
      }

   }

   public void refreshList()
   {
      if(PointsServer.teamsList.size()>0) {
         for (int count = 0; count < PointsServer.teamsList.size(); count++) {
            teamButtonList.add(new ButtonInfo(this, PointsServer.teamsList.get(count)));
         }
      }


   }

   private void setupPanels()
   {
     // this.setLayout(new GridLayout());
      for(int count=0;count< panelList.size();count++)
      {
         this.add(panelList.get(count));
      }
   }

   private void clearPanel()
   {
      for(int count=0;count<panelList.size();count++) {
         this.remove(panelList.get(count));
      }
   }

   public ServerFrame(ArrayList<ButtonInfo>pButtonList,ArrayList<JPanel>pPanels) throws HeadlessException
   {
      super();
      this.setLayout(new GridLayout(0,1));
      teamButtonList=pButtonList;
      panelList=pPanels;
      refreshButton=new JButton("refresh");
      mainPanel=new JPanel(new GridLayout(1,3));
      refreshButton.addActionListener(this);
      mainPanel.add(refreshButton);
      //this.setLayout(new FlowLayout());
      this.add(mainPanel);
      this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
     // clearPanel();
     // refreshList();
      //cycleButtonsList();
      setupPanels();

      this.pack();
      this.setVisible(true);

   }

   public ServerFrame(String title) throws HeadlessException
   {
      super(title);
      this.setLayout(new GridLayout(0,1));
      teamButtonList=new ArrayList<ButtonInfo>();
      panelList=new ArrayList<JPanel>();
      refreshButton=new JButton("refresh");
      mainPanel=new JPanel(new GridLayout(1,3));
      refreshButton.addActionListener(this);
      mainPanel.add(refreshButton);
      //this.setLayout(new FlowLayout());
      this.add(mainPanel);
      this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      this.pack();
      this.setVisible(true);

     // cycleButtonsList();

   }


   @Override
   public void actionPerformed(ActionEvent e)
   {
      if(e.getSource().equals(refreshButton))
      {
        // if(PointsServer.teamsList.size()>0)
        // {
            pack();
            clearPanel();
            teamButtonList=new ArrayList<ButtonInfo>();
            panelList=new ArrayList<JPanel>();
            refreshList();
            cycleButtonsList();
           // setupPanels();
            new ServerFrame(teamButtonList,panelList);
            this.dispose();




           // repaint();

        // }
      }


      }

   @Override
   public void paint(Graphics g) {
      super.paint(g);


   }

   @Override
   public void update(Graphics g) {
      super.update(g);
   }
}

