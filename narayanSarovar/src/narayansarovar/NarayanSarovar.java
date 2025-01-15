package narayansarovar;

import Repo.DonorRepo;
import Servise.Opration;
import java.util.List;

import Servise.optest;
import pojo.Result;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.io.IOException;
public class NarayanSarovar {

    public static void main(String[] args) {

//       optest t1 = new optest();
//       List<Result> result = t1.getResult();
//
//
//       for(int i=0;i<result.size();i++)
//       {
//
//           System.out.println(result.get(i).getName());
//           System.out.print("Gaushala : Rs."+result.get(i).getGau()+"\t ");
//           for(int j=0;j<result.get(i).getDataG().size();j++)
//           {
//               System.out.print(result.get(i).getDataG().get(j).getName()+" :"+result.get(i).getDataG().get(j).getKg()+"Kg ");
//           }
//           System.out.println();
//
//
//           System.out.print("Annkshetra : Rs."+result.get(i).getAnn()+"\t ");
//           for(int j=0;j<result.get(i).getDataA().size();j++)
//           {
//               System.out.print(result.get(i).getDataA().get(j).getName()+" :"+result.get(i).getDataA().get(j).getKg()+"Kg ");
//           }
//           System.out.println("\n");
//       }

         Opration opration = new Opration();
         List<Result> result = opration.getResult();

         String pathtodoc = "D:\\narayan sarovar\\oldData\\2025.docx";

         XWPFDocument document = new XWPFDocument();

         // Create a paragraph
         XWPFParagraph paragraph = document.createParagraph();
         XWPFRun run = paragraph.createRun();

         for(int i=0;i<result.size();i++)
         {

             System.out.println(result.get(i).getName());
             run.setText(result.get(i).getName());
             run.addBreak();
             System.out.print("Gaushala : Rs."+result.get(i).getGau()+"\t ");
             run.setText("Gaushala : Rs." + result.get(i).getGau() + "\t ");
             for(int j=0;j<result.get(i).getDataG().size();j++)
             {

                 System.out.print(result.get(i).getDataG().get(j).getName()+" :"+result.get(i).getDataG().get(j).getKg()+"Kg ");
                 run.setText(result.get(i).getDataG().get(j).getName() + " :" + result.get(i).getDataG().get(j).getKg() + "Kg ");
             }
             System.out.println();
             run.addBreak();

             System.out.print("Annkshetra : Rs."+result.get(i).getAnn()+"\t ");
             run.setText("Annkshetra : Rs." + result.get(i).getAnn() + "\t ");
             for(int j=0;j<result.get(i).getDataA().size();j++)
             {
                 System.out.print(result.get(i).getDataA().get(j).getName()+" :"+result.get(i).getDataA().get(j).getKg()+"Kg ");
                 run.setText(result.get(i).getDataA().get(j).getName() + " :" + result.get(i).getDataA().get(j).getKg() + "Kg ");
             }
             run.addBreak();

             System.out.println("\n");
             run.addBreak();
         }
         try (FileOutputStream out = new FileOutputStream(pathtodoc)) {
             document.write(out);
             System.out.println("File created successfully.");
         } catch (IOException e) {
             e.printStackTrace();
         }


    }
}
