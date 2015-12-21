package QuizProgram.Frames.CreationFrames;

import QuizProgram.GameInfo.Questions;
import QuizProgram.KeyValue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by Daniel on 21/12/2015.
 */
public class QuestionAddFrame extends JFrame implements ActionListener {
   private boolean isAdding=true;

   private JButton add,delete;

   private JTextArea questionInput;
   private String question;

   private JTextArea answerInput;

   private JTextPane questionShower;
   private JScrollPane allQuestions;
   private List<String> options;
   private List<String> correct;

   private List<Questions>questions;


   public QuestionAddFrame(String title,List<Questions> pQuestions) throws HeadlessException {
      super(title);

      questionShower=new JTextPane();
      allQuestions=new JScrollPane(questionShower);
      questionShower.setEditable(false);

      questions=pQuestions;
      for(Questions current:questions){
         questionShower.setText(questionShower.getText() + "\n" + current.getQuestion());
      }

      add=new JButton("add a question");
      add.addActionListener(this);
      delete=new JButton("delete a question");
      delete.addActionListener(this);

      questionInput=new JTextArea();
      answerInput=new JTextArea();

      this.setLayout(new GridLayout(0,1));

      this.add(add);
      this.add(delete);
      this.add(allQuestions);

      this.setVisible(true);



   }

   public void actionPerformed(ActionEvent e)
   {
      if(e.getSource().equals(delete))
      {
         isAdding = false;
         String selected=JOptionPane.showInputDialog(this,"please enter the question to delete");

         System.out.println("delete clicked");
         for(Questions current:questions){
            if(current.getQuestion().equals(selected)){
               questions.remove(current);
            }
         }

      }
      else if(e.getSource().equals(add)){
         isAdding = true;

         System.out.println("add clicked");
         question=JOptionPane.showInputDialog(this,"please enter a title for the question");

         showAddingFrame();

      }
   }

   private void showAddingFrame() {

   }
}//class
