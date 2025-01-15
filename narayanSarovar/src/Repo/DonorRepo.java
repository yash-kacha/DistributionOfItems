/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repo;

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pojo.donorData;
import pojo.samanData;

public class DonorRepo {

    List<donorData> dd = new ArrayList<>();
    List<samanData> sd = new ArrayList<>();

    List<donorData> dd1 = new ArrayList<>();
    List<donorData> dd2 = new ArrayList<>();

    List<samanData> sd1 = new ArrayList<>();
    List<samanData> sd2 = new ArrayList<>();

    public int getTotalGauSaman(){
        return sd1.size();
    }
    int transport;
    
    public int getTransport()
    {
        return transport;
    }
    
    public List<donorData> getDD() {

        List<String> donor = new ArrayList<>();
        List<String> saman = new ArrayList<>();
        
        List<Integer> gau = new ArrayList<>();
        List<Integer> ann = new ArrayList<>();
        List<Integer> kg = new ArrayList<>();
        List<Integer> bill = new ArrayList<>();
        
        try  
        {  
            File file = new File("D:\\narayan sarovar\\Data.xlsx");   //creating a new file instance  
            FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file  
            //creating Workbook instance that refers to .xlsx file  
            XSSFWorkbook wb = new XSSFWorkbook(fis);   
            XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object  
            Iterator<Row> itr = sheet.iterator();    //iterating over excel file  
            int i=0;
            while (itr.hasNext())                 
            {   
                Row row = itr.next();  
                Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column  
                while (cellIterator.hasNext())   
                {  
                    Cell cell = cellIterator.next();  
                    switch (cell.getCellType())               
                    {  
                        
                        case Cell.CELL_TYPE_STRING:    //field that represents string cell type  
                        {
                            if(i==0)
                            {
                                donor.add(cell.getStringCellValue());  
                            }
                            else if(i==3)
                            {
                                saman.add(cell.getStringCellValue()); 
                            }
                            else if(i==6)
                            {
                                String s = cell.getStringCellValue();
                                transport = Integer.parseInt(s.substring(11)); 
                            }

                        
                        
                        } 
                        break; 
                        case Cell.CELL_TYPE_NUMERIC: 
                        {   
                            if(i==1)
                            //field that represents number cell type  
                            {gau.add((int)cell.getNumericCellValue());}  
                            else if(i==2)
                            {ann.add((int)cell.getNumericCellValue());}

                            else if(i==4)
                            {kg.add((int)cell.getNumericCellValue());}
                            else if(i==5)
                            {bill.add((int)cell.getNumericCellValue());}
                            
                    
                        }
                        
                        break;  
                        default:  
                    } 
                     
                }  
                 i++; 
            }  
        }  
            catch(Exception e)  
        {  
        e.printStackTrace();  
        } 
        
        
        
        int size=donor.size();
        int gauSize = gau.size();
        for(int i=gauSize;i<size;i++)
        {
            gau.add(0);
        }
        
        for(int i =0;i<size;i++)
        {
            dd.add(new donorData(donor.get(i),gau.get(i),ann.get(i)));
        }
        
        size=saman.size();
        for(int i =0;i<size;i++)
        {
            sd.add(new samanData(saman.get(i),kg.get(i),bill.get(i)));
        }
        for(donorData i : dd)
        {
            if(i.getGau()>0) dd1.add(i);
            else dd2.add(i);
        }
        // for(donorData i : dd)
        // {
        //     System.out.println(i.toString());
        // }
        // System.out.println("ddddd\n\n\n");

        dd1.sort(Comparator.comparingDouble(donorData::getGau).thenComparing(donorData::getAnn));
        dd2.sort(Comparator.comparingDouble(donorData::getAnn));



//        for(donorData i : dd)
//        {
//            System.out.println(i.toString());
//        }
//        System.out.println("\n\n\n");
        dd1=dd1.reversed();
        dd2=dd2.reversed();

        dd.clear();
        dd.addAll(dd1);
        dd.addAll(dd2);


//        for(donorData i : dd)
//        {
//            System.out.println(i.toString());
//        }
        
        return dd;
    }
    
    public List<samanData> getSD()
    {
        //List<donorData> dd = new ArrayList<>();



        for(samanData i : sd){
            if(i.getName().contains("g.")) sd1.add(i);
            else sd2.add(i);
        }
        sd1.sort(Comparator.comparingDouble(samanData::getPricePerKg));
        sd2.sort(Comparator.comparingDouble(samanData::getBill));

        sd1=sd1.reversed();
        sd2=sd2.reversed();

        for(int i=0;i<sd1.size();i++)
        {
            sd1.get(i).setName( sd1.get(i).getName().substring(2));
        }
        sd.clear();
        sd.addAll(sd1);
        sd.addAll(sd2);

        return sd;
    }
    
    
}
