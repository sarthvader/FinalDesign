package test.listprocessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.UUID;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.FileValue;

public class ProcessListDelegate implements JavaDelegate
{
	private final static Logger LOGGER = Logger.getLogger("PROCESS-LIST");
	
	public void execute(DelegateExecution execution) throws Exception
	{
		LOGGER.info("Processing list");
		
		ByteArrayInputStream inputList = (ByteArrayInputStream) execution.getVariable("inputList");
		
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try
		{
			br = new BufferedReader(new InputStreamReader(inputList));
			while ((line = br.readLine()) != null)
			{
				sb.append(line + "\n");
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (br != null)
			{
				try
				{
					br.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

		LOGGER.info("The input file as a string is: " + sb.toString());

		File tempFolder = FileUtils.getTempDirectory();
		String guidTempFileName = UUID.randomUUID().toString();
		String tempFileName = tempFolder.toString() +"/" +guidTempFileName +".txt";
        LOGGER.info("Temporary file:" +tempFileName);
        execution.setVariable("temp_file_name", tempFileName);

        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileName));
        
        writer.write(sb.toString());
        writer.flush();
        writer.close();
    	
	}
}