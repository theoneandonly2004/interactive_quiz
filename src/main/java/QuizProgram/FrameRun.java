package QuizProgram;

import QuizProgram.Frames.*;
import QuizProgram.GameInfo.ImageRound;
import QuizProgram.GameInfo.Questions;
import QuizProgram.Profiles.UserProfile;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Daniel on 11/01/2015.
 */
public class FrameRun
{
   private static String profileLocation=KeyValue.fileBeginning;
   private static File userFile;
   private static UserProfile userProfile;


   public static void main(String[] args)
   {
      System.out.println("hi");
      ArrayList<Questions>questionsList=new ArrayList<Questions>();
      MainQuiz.readFromFile(questionsList);

      ArrayList<ImageRound>imageRoundList=new ArrayList<ImageRound>();

      File file=new File("src\\Resources\\Images");

      //ClassLoader classLoader = FrameRun.class.getClassLoader();
      //File file = new File(classLoader.getResource("").getPath());
      //file=file.getParentFile();
      //File images=new File(file.getAbsolutePath() + "\\Images");

      //File file=select.getFile();


      File[]fileList=file.listFiles();
      FileNameExtensionFilter filter=new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");

      if(fileList != null) {
         for (File f : fileList) {
            if (!f.isDirectory() && filter.accept(f)) {
               String fileName = f.getName();
               fileName = fileName.substring(0, fileName.length() - 4);
               String imagePath = f.getPath();
               imageRoundList.add(new ImageRound(imagePath, fileName, "who is this"));
            }
         }
      }

      KeyFrameItems.setupMenu();
      KeyValue.setupServer();

      GameSelectFrame frame=new GameSelectFrame("a title",questionsList,imageRoundList);





      //MyFrame frame=new MyFrame("this is my frame");
     //ProfileFrame frame=new ProfileFrame("user profile");
     // SignInFrame frmae=new SignInFrame("sign in");



     // QuestionFrame frame=new QuestionFrame(options.get(0).getQuestion(),options,0);





   /*
      ArrayList<ImageRound>round=new ArrayList<ImageRound>();
      File file=new File(KeyValue.IMAGEPATH);
      File[]fileList=file.listFiles();


      if(fileList != null)
      {
         for (int count = 0; count < fileList.length; count++)
         {
            String fileName = fileList[count].getName();
            fileName = fileName.substring(0, fileName.length() - 4);
            String imagePath = fileList[count].getPath();
            round.add(new ImageRound(imagePath, fileName, "who is this"));
         }
      }

     // Information.print(round.get(0).toString());
     // PictureGameSelect frame=new PictureGameSelect("title",round,0);
      ImageGameFrame frame=new ImageGameFrame("Who is this?",round,0);

      //MainMenuFrame frame=new MainMenuFrame("main frame");  */
   }//main
}//class
