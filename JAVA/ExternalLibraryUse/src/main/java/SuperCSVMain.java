package main.java;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

public class SuperCSVMain {

	public static void main(String[] args) {
		//저장할 List 생성
		List<Singer> list = new ArrayList<>();
		//읽을 파일의 경로 생성
		Path  path = Paths.get("./music.csv");
		CellProcessor[] processors = 
			new CellProcessor[] {
				new NotNull(),
				new ParseInt(new NotNull()),
				new ParseDate("yyyy-MM-dd"),
				new Optional(),
				new Optional(),	
			};
			
		//Csv 읽기 위한 경로 생성
		try(ICsvBeanReader beanReader =
			new CsvBeanReader(
					Files.newBufferedReader(path),
					CsvPreference.STANDARD_PREFERENCE)){
			//첫줄 읽어오기
			String [] header = 
					beanReader.getHeader(true);
			System.out.println(Arrays.toString(header));
			
			//데이터 읽어서 list에 추가
			Singer singer1 = null;
			//한 줄씩 읽어서 header에 맞추어 Singer 클래스 타입의 
			//인스턴스를 생성
			while((singer1 = 
					beanReader.read(
						Singer.class, header, processors)) != null) {
				list.add(singer1);
			}
			System.out.println(list);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}		
	}
}