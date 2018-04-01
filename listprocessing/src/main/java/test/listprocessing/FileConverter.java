package test.listprocessing;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.test.annotations.WrapToTest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
 
@WrapToTest
public class FileConverter implements JavaDelegate {
 
    public void execute(DelegateExecution execution) throws IOException {
    	String tempFileName = execution.getVariable("temp_file_name").toString();

    	new FileConverter().createPdf(tempFileName);
    }
 
    public void createPdf(String tempFileName) throws IOException {
    	File tempFolder = FileUtils.getTempDirectory();
    	String dest = tempFolder.toString() + "/ouptut.pdf";
    	
    	File file = new File(tempFileName);
        file.getParentFile().mkdirs();
    	
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        Document document = new Document(pdf)
            .setTextAlignment(TextAlignment.JUSTIFIED);
        BufferedReader br = new BufferedReader(new FileReader(tempFileName));
        String line;
        PdfFont normal = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        while ((line = br.readLine()) != null) {
            document.add(new Paragraph(line).setFont(normal));
        }
        document.close();
    }
}