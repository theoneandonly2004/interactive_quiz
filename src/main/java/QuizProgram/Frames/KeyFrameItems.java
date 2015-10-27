package QuizProgram.Frames;

import javax.swing.*;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;

/**
 * Created by Daniel on 04/07/2015.
 */
public class KeyFrameItems
{
   public static JMenuBar bar=new JMenuBar();
   public static JMenu menu=new JMenu("options");
   public static JMenuItem points=new JMenuItem("get scores");

   public static void setupMenu()
   {
      bar.add(menu);
      points.addMenuKeyListener(new MenuKeyListener() {
         @Override
         public void menuKeyTyped(MenuKeyEvent e)
         {

         }


         @Override
         public void menuKeyPressed(MenuKeyEvent e)
         {
            if(e.getSource().equals(points))
            {
               System.out.println("hi you hit get score");
            }
         }

         @Override
         public void menuKeyReleased(MenuKeyEvent e) {

         }
      });

      menu.add(points);
   }



}//class
