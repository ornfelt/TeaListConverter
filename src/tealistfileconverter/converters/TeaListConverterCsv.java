package tealistfileconverter.converters;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import tealistfileconverter.Tea;

/**
 * Converts UTF-8 files containing tea information from and to 
 * CSV (Comma Separated File). Each record is separated with a new line. 
 * Example of how a file could look:<br>
 * Black,Cinnamon,20,With parts of cinnamon.<br>
 * Black,Blueberry,20,With leafs and berries.
 * 
 * @author Thomas Ejnefjäll
 */
public class TeaListConverterCsv extends TeaListConverter {

	@Override
	protected List<Tea> readAndParse(String fileName) throws IOException {
		List<Tea> teaList = new ArrayList<Tea>();
		FileInputStream fis = new FileInputStream(fileName);
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		BufferedReader br = new BufferedReader(isr);

		String line;

		try
		{
			while((line = br.readLine()) != null)
			{
				Tea tea = new Tea();
				String[] teaLine = line.split(",");
				tea.category = teaLine[0];
				tea.name = teaLine[1];
				tea.price = Integer.valueOf(teaLine[2]);
				tea.description = teaLine[3];
				teaList.add(tea);

			}
			br.close();
		} catch (Exception e)
		{			
			throw new IOException("Input file (" + fileName + ") not correct format");
		}		
		return teaList;
	}
	@Override
	protected void parseAndWrite(List<Tea> teaList, String fileName) throws IOException {
		FileOutputStream fos = new FileOutputStream(fileName);
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);

		for(int i = 0; i < teaList.size(); i ++)  
		{
			Tea tea = teaList.get(i);
			bw.write(tea.category + "," + tea.name + "," + tea.price + "," + tea.description);

			if(i + 1 != teaList.size())
			{
				bw.newLine();
			}
		}		
		bw.close();				
	}
}