/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfconverter;

/**
 *
 * @author TT
 */

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class Convertor {
   
   PdfReader reader = null;
   
   public String readData(String file)
   {
       StringBuilder sb = new StringBuilder();
       try{
            reader = new PdfReader(file);
            PdfReaderContentParser parser = new PdfReaderContentParser(reader);
            TextExtractionStrategy strategy;
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
		strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
		sb.append(strategy.getResultantText());
            }
                
       }
       catch(IOException e){
           e.printStackTrace();
       }
       finally{
           reader.close();
       }
       return sb.toString();
   }   
   
   
    public boolean writeData(String file,String sb)
    {
        int n = file.lastIndexOf(".");
        String doc_file = file.substring(0,n+1)+"docx";
        try(FileOutputStream out = new FileOutputStream(new File(doc_file)))
        {
            XWPFDocument document= new XWPFDocument();
            XWPFParagraph paragraph = document.createParagraph();
            String[] data = sb.split("\n");
            for(int i=0;i<data.length;i++){
                XWPFRun run = paragraph.createRun();
                run.setText(data[i]);
                if(i!=(data.length-1))
                    run.addBreak();
            }
            
            document.write(out);
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
   
}
