package QuizProgram;

import QuizProgram.GameInfo.ImageRound;
import QuizProgram.GameInfo.Questions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Daniel on 11/03/2015.
 */
public class Information
{
   public static void print(String pMessage)
   {
      System.out.println(pMessage);
   }

   public static void printToFile(ArrayList<ImageRound> pQuestions,String pFile) {
      PrintWriter writer = null;
      boolean open = false;
      String file=pFile;
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
            for (int count = 0; count < pQuestions.size(); count++)
            {
               print(pQuestions.get(count).toString());
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

   public static void readFromFile(ArrayList<ImageRound> pList,String pFile)
   {

      final String OPTION = KeyValue.OPTION, QUESTION = KeyValue.QUESTION, ANSWER = KeyValue.ANSWER, END = KeyValue.END;
      final String FILEPATH=KeyValue.FILEPATH;
      FileReader reader;
      Scanner input = null;
      String file=pFile;
      String header = "", title = "";
      int currentNumber = 0;





      try {
         reader = new FileReader(file);
         input = new Scanner(reader);
      } catch (FileNotFoundException ex) {
         print("file " + file + " not found");
         print(ex.getMessage());
      }

      try {
         print("now attempting to check if there is a line");
         while (input.hasNextLine()) {

               pList.add(new ImageRound());
               print("a new question was added");

               while (input.hasNextLine() || !header.equalsIgnoreCase(END)) {
                  header = (String) input.nextLine();
                  if (!header.equals(END)) {
                     print(header);
                     if (header.equals(QUESTION)) {
                        title = header;
                     } else if (header.equals(OPTION)) {
                        title = header;
                     } else if (header.equals(ANSWER)) {
                        title = header;
                     } else if (header.equals(FILEPATH)) {
                        title = header;
                     }


                      if (title.equals(ANSWER) && !header.equals(title))
                      {
                        pList.get(currentNumber).setAnswers(header);
                      }
                      else if (title.equals(FILEPATH) && !header.equals(title))
                      {
                        pList.get(currentNumber).setFilePath(header);
                      }
                      else if (title.equals(QUESTION) && !header.equals(title))
                      {
                         pList.get(currentNumber).setQuestion(header);
                      }



                  } else if (header.equals(END) && input.hasNextLine()) {
                     //print("a question was added to the list");
                     pList.add(new ImageRound());
                     currentNumber++;
                  }
               }


               print("all inputs read from file");

            }

      }
      catch (Exception ex)
      {
         print(ex.getMessage());
         print("there was an error happened");
      }

      print("end of file called");
   }

}//class
