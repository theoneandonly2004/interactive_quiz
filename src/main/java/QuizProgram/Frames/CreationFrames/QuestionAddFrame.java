package QuizProgram.Frames.CreationFrames;

import QuizProgram.GameInfo.Questions;
import QuizProgram.KeyValue;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
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

      options = new ArrayList<String>();
      correct = new ArrayList<String>();
      questionShower=new JTextPane();
      allQuestions=new JScrollPane(questionShower);

      questionShower.setEditable(false);
      this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

      questions=pQuestions;
      for(int count=0;count<questions.size();count++){
         questionShower.setText(questionShower.getText() + "\n" + questions.get(count).getQuestion());
      }

      add=new JButton("add a question");
      add.addActionListener(this);
      delete=new JButton("delete a question");
      delete.addActionListener(this);

      questionInput=new JTextArea();
      answerInput=new JTextArea();


      this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));

      this.add(add);
      this.add(delete);
      this.add(allQuestions);

      this.setVisible(true);
      this.pack();



   }

   public void actionPerformed(ActionEvent e)
   {
      if(e.getSource().equals(delete))
      {
         isAdding = false;
         String selected=JOptionPane.showInputDialog(this,"please enter the question to delete");

         System.out.println("delete clicked");
         for(Questions current:questions){
            if(current.getQuestion().equalsIgnoreCase(selected)){
               System.out.println(current.getQuestion() + " size of : " + questions.size());
               questions.remove(current);
               System.out.println(current.getQuestion() + " size of : " + questions.size());
               break;
            }
         }
         MainQuiz.printToFile(questions);
        updateTextarea(questionShower);
      }
      else if(e.getSource().equals(add)){
         isAdding = true;

         System.out.println("add clicked");
         question=JOptionPane.showInputDialog(this,"please enter a title for the question");

         if(question != null) {
            if (!question.equals("")) {
               showAddingFrame(question);
               updateTextarea(questionShower);
            }
         }

      }
   }

   private void updateTextarea(JTextPane pTextarea){
      pTextarea.setText("");
      for(Questions current:questions){
         pTextarea.setText(questionShower.getText() + "\n" + current.getQuestion());
      }
   }

   private void showAddingFrame(String pQuestion) {
      boolean isEmpty = false;

      options = new ArrayList<String>();
      correct = new ArrayList<String>();
      while(!isEmpty){

         String selected =JOptionPane.showInputDialog(this,"please enter a possible answer");

         if(selected == null || selected.equals("")){
            isEmpty = true;
         }
         else{
            options.add(selected);
         }
      }

      isEmpty = false;
      String all = "";
      for(int count=0;count<options.size();count++){
         all += count + " " + options.get(count) + "\n";
      }

      while(!isEmpty){

         String selected =JOptionPane.showInputDialog(this,all + "please enter the number of the correct answer");

         if(selected == null|| selected.equals("")){
            isEmpty = true;
         }
         else{
            try
            {
               correct.add(options.get(Integer.parseInt(selected)));
            }
            catch(Exception ex){
               JOptionPane.showMessageDialog(this,"invalid number entered");
            }

         }
      }

      Questions addedQuestion = new Questions(question,options,correct);
      all = "";
      questions.add(addedQuestion);
      MainQuiz.printToFile(questions);
   }
}//class
