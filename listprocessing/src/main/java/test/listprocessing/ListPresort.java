package test.listprocessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

 
class Address
{
    int jobID;
    int docnum;
    String fullName;
    String address1;
    String address2;
    String address3;
    String SCZ;
    int PC;
     
    public Address(int jobID, int docnum, String fullName, 
    				String address1, String address2, String address3, String SCZ, int PC) 
    {
        this.jobID = jobID;
        this.docnum = docnum;
        this.fullName = fullName;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.SCZ = SCZ;
        this.PC = PC;
    }
}
 
 
//SCZCompare Class to compare the SCZs
 
class SCZCompare implements Comparator<Address>
{
    public int compare(Address s1, Address s2)
    {
    	return s1.SCZ.compareTo(s2.SCZ);
    }
}
 
public class ListPresort implements JavaDelegate
{
    public void execute(DelegateExecution execution)throws IOException
    {
    	String tempFileName = execution.getVariable("temp_file_name").toString();
		
        //Creating BufferedReader object to read the input text file
         
        BufferedReader reader = new BufferedReader(new FileReader(tempFileName));
         
        //Creating ArrayList to hold Address objects
         
        ArrayList<Address> addressRecords = new ArrayList<Address>();
         
        //Reading Address records one by one
         
        String currentLine = reader.readLine();
         
        while (currentLine != null)
        {
            String[] addressDetail = currentLine.split("~");
            
            int jobID = Integer.valueOf(addressDetail[0]);
            int docnum = Integer.valueOf(addressDetail[1]);
            String fullName = addressDetail[2];
            String address1 = addressDetail[3];
            String address2 = addressDetail[4];
            String address3 = addressDetail[5];
            String SCZ = addressDetail[6];
            int PC = Integer.valueOf(addressDetail[7]);
         
            //Creating Address object for every address record and adding it to ArrayList
             
            addressRecords.add(new Address(jobID, docnum, fullName, address1, address2, address3, SCZ, PC));
             
            currentLine = reader.readLine();
        }
         
        //Sorting ArrayList addressesRecords based on SCZ
         
        Collections.sort(addressRecords, new SCZCompare());
         
        //Creating BufferedWriter object to write into output text file
         
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileName));
         
        //Writing every addressRecords into output text file
         
        for (Address address : addressRecords) 
        {
            writer.write(address.jobID);
            writer.write("~"+address.docnum);
            writer.write("~"+address.fullName);
            writer.write("~"+address.address1);
            writer.write("~"+address.address2);
            writer.write("~"+address.address3);
            writer.write("~"+address.SCZ);
            writer.write("~"+address.PC);
            writer.append("\n");
        }
         
        //Closing the resources
         
        reader.close();
         
        writer.close();
    }
}