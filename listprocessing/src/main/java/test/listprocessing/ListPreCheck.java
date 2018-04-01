package test.listprocessing;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ListPreCheck implements JavaDelegate  {
	public void execute(DelegateExecution execution) throws Exception
	{
		//check the file type
        String tempFileName = execution.getVariable("temp_file_name").toString();
		File f = new File(tempFileName);

        // Note: As the file is being uploaded, we only have access to the file content, not the original file name
        // so this test will always be true because we are forcing the TXT file extension in the ProcessListDelegate
		String ext = FilenameUtils.getExtension(tempFileName);
		System.out.println("The extension for the file is:" +ext);
		
		if(ext.compareTo("txt") == 0 && f.exists() && !f.isDirectory()) {
			execution.setVariable("correct_filetype", true);
			System.out.println("correct_filetype=true");
		}
		else {
			execution.setVariable("correct_filetype", false);
			System.out.println("correct_filetype=false");
			}
	}

	private static File File(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
