package QuizProgram.Frames.CreationFrames;

import QuizProgram.GameInfo.Questions;
import QuizProgram.KeyValue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Daniel on 16/12/2014.
 */


public class MainQuiz {
   public static Scanner keyboard = new Scanner(System.in);
   public static String file = KeyValue.QUESTIONPATH;

   public static void print(String message) {
      System.out.println(message);
   }

   public static void printToFile(List<Questions> pQuestions) {
      PrintWriter writer = null;
      boolean open = false;
      try {
         writer = new PrintWriter(file);
         open = true;
      } catch (FileNotFoundException ex) {
         print("file " + file + " was not found to write to");
         print(ex.getMessage());
         open = false;
      }

      try {
         if (open) {

            for (int count = 0; count < pQuestions.size(); count++) {

               writer.println(pQuestions.get(count).toString());
            }

            print("file written to and now closing");
            writer.close();
            open = false;
         }

      } catch (Exception ex) {
         print(ex.getMessage());
      }


   }

   public static void readFromFile(List<Questions> pList) {
      final String OPTION = KeyValue.OPTION, QUESTION = KeyValue.QUESTION, ANSWER = KeyValue.ANSWER, END = KeyValue.END;
      FileReader reader;
      List<String> list = new ArrayList<String>();
      Scanner input = null;
      String header = "", title = "";
      int currentNumber = 0;
      File file=new File("src\\Resources\\TextFiles\\Questions");

      try {
         reader = new FileReader(file);
         input = new Scanner(reader);
      } catch (FileNotFoundException ex) {
         print("file " + file + " not found");
         print(ex.getMessage());
      }

      try {

         while (input.hasNextLine())
         {
            pList.add(new Questions());


            while (input.hasNextLine() || !header.equalsIgnoreCase(END))
            {
               header = (String) input.nextLine();
               if (!header.equals(END))
               {

                  if (header.equals(QUESTION))
                  {
                     title = header;
                  }
                  else if (header.equals(OPTION))
                  {
                     title = header;
                  }
                  else if (header.equals(ANSWER))
                  {
                     title = header;
                  }

                  if (title.equals(QUESTION) && !header.equals(title))
                  {
                     pList.get(currentNumber).setQuestion(header);
                  }
                  else if (title.equals(OPTION) && !header.equals(title))
                  {
                     pList.get(currentNumber).addOptions(header);
                  }
                  else if (title.equals(ANSWER) && !header.equals(title))
                  {
                     pList.get(currentNumber).addAnswers(header);
                  }


               } else if (header.equals(END) && input.hasNextLine()) {
                  //print("a question was added to the list");
                  pList.add(new Questions());
                  currentNumber++;
               }
            }




         }
      }
      catch (Exception ex)
      {
         print(ex.getMessage());
         print("there was an error happened");
      }


   }


   public static void main(String[] args) {
      Questions currentQuestion;
      int choiceNumber = 0;
      int currentNumber = 0;
      int numberOfQuestions = 5;
      String input;
      List<Questions> theQuestions = new ArrayList<Questions>();
      List<String> myAnswers = new ArrayList<String>();
      List<String> myOptions = new ArrayList<String>();
      String question;


      System.out.println("please enter an option from the below list");
      input = keyboard.nextLine();

      readFromFile(theQuestions);

      do {

         if (input.equalsIgnoreCase("ADD")) {
            print("please enter a question for the quiz");
            question = keyboard.nextLine();


            print("enter an Option to add or enter done");
            input = keyboard.nextLine();


            while (myOptions.size() <= 0 || !input.equalsIgnoreCase("DONE"))
            {

               myOptions.add(input);
               currentNumber++;
               print("enter another option to add or enter done");
               input = keyboard.nextLine();
            }

            while(input==null)
            {
               print("sorry you must enter a valid entry");
               input=keyboard.nextLine();
            }

            for (int count = 0; count < myOptions.size(); count++) {
               print((count) + ":" + myOptions.get(count));
            }

            print("enter a number of your options to add to answers or enter -1");

            do {
               choiceNumber = keyboard.nextInt();
               if (choiceNumber >= 0 && choiceNumber < myOptions.size())
               {
                  myAnswers.add(myOptions.get(choiceNumber));
               }
            } while (myAnswers.size() <= 0 || choiceNumber >= 0);


            Questions newQuestion = new Questions(question, myOptions, myAnswers);
            theQuestions.add(newQuestion);
         }

         print("please enter either exit or add to add more questions");
         input = keyboard.nextLine();


      } while (!input.equalsIgnoreCase("EXIT"));

      printToFile(theQuestions);
      print("let's ask some questions now");


      for (int count = 0; count < theQuestions.size(); count++) {
         currentQuestion = theQuestions.get(count);
         print(theQuestions.get(count).getQuestion());
         for (int sec = 0; sec < currentQuestion.getOptions().size(); sec++) {
            print(sec + ":" + currentQuestion.getOptions().get(sec));
         }
         choiceNumber = keyboard.nextInt();


         while (choiceNumber > currentQuestion.getOptions().size() || choiceNumber < 0) {
            print("sorry you entered an invalid number please try again");
            choiceNumber = keyboard.nextInt();
         }
         if (currentQuestion.doesListContain(currentQuestion.getAnswers(), currentQuestion.getOptions().get(choiceNumber))) {
            print("congratulations your answer of " + currentQuestion.getOptions().get(choiceNumber) + " was right");
         } else {
            print("sorry your answer of " + currentQuestion.getOptions().get(choiceNumber) + " was wrong");
         }
      }

      printToFile(theQuestions);
   }
}





