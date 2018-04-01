
package test.listprocessing;

import org.apache.commons.io.FilenameUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class ListPostCheck implements JavaDelegate {
	public void execute (DelegateExecution execution) throws AddressException, MessagingException {
		//check the file type		
		String tempFileName = execution.getVariable("temp_file_name").toString();
		File f = new File(tempFileName);
		
		String ext = FilenameUtils.getExtension(tempFileName);
		System.out.println(ext);
		
		if(ext.compareTo("pdf") == 0 && f.exists() && !f.isDirectory()) {
			execution.setVariable("correct_filetype", true);
			System.out.println("correct_filetpye=true");
			
		}
		else {
			execution.setVariable("correct_filtpye", false);
			System.out.println("corect_filetype=false");	
			}
	}
	
	private static File File(String string) {
		return null;
	}
}