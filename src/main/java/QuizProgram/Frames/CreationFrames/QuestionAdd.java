package QuizProgram.Frames.CreationFrames;

import QuizProgram.GameInfo.Questions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by danielB on 04/11/2015.
 */
public class QuestionAdd{
    public static void main(String[]args){
        List<Questions>questionsList=new ArrayList<Questions>();
        MainQuiz.readFromFile(questionsList);

        QuestionAddFrame frame=new QuestionAddFrame("add questions",questionsList);


    }
}
