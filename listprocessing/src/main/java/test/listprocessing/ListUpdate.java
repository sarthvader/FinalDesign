package test.listprocessing;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

    public class ListUpdate  implements JavaDelegate {
        public ListUpdate(){
            System.out.println("Test.Test()");
        }

        public void execute(DelegateExecution execution) throws Exception
        {
            String tempFileName = execution.getVariable("temp_file_name").toString();

            BufferedReader br1 = null;
            BufferedReader br2 = null;
            String sCurrentLine;
            List<String> list1 = new ArrayList<String>();
            List<String> list2 = new ArrayList<String>();
            br1 = new BufferedReader(new FileReader(tempFileName));
            br2 = new BufferedReader(new FileReader(tempFileName));
            while ((sCurrentLine = br1.readLine()) != null) {
                list1.add(sCurrentLine);
            }
            while ((sCurrentLine = br2.readLine()) != null) {
                list2.add(sCurrentLine);
            }
            List<String> tmpList = new ArrayList<String>(list1);

            System.out.println("content from file 2 which is not there in file 1.");

            tmpList = list2;
            tmpList.removeAll(list1);
            for(int i=0;i<tmpList.size();i++){
            	 FileWriter fw = new FileWriter(tempFileName, true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 bw.newLine();
                 bw.write(tmpList.get(i));
                 bw.flush();
                 bw.close();

                System.out.println(tmpList.get(i)); //content from test2.txt which is not there in test.txt
            }
        }
    }