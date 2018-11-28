package com.talabat.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import com.talabat.utilities.TestUtil;
import com.talabat.utilities.WebEventListener;

/* This is aclass with basic methods and functionalities to be used in the entire project*/
public class TestBase {
	
 public static WebDriver DRIVER;
 public static Properties prop;
 public static Map<String,String> DataMap;
 public static EventFiringWebDriver edriver;
 public static WebEventListener eventListener;
	
	public TestBase() {
		//initialize prop file in constructor and load in global variable		
		FileInputStream fis;
		try {
			fis = new FileInputStream("C:/Users/shadm/Documents/f2/talabatTest/src/main/java/com/talabat/config/config.properties");
			prop = new Properties(); 
			prop.load(fis);
		} 
		catch (FileNotFoundException fe) {
			// TODO Auto-generated catch block
			fe.printStackTrace();
		}
		catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	 
	
	public static void initializaton() {
		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
		    DRIVER=new ChromeDriver();
		}
		
		edriver=new EventFiringWebDriver(DRIVER);
		eventListener=new WebEventListener();
		edriver.register(eventListener);
		DRIVER=edriver;
		
		DRIVER.manage().window().maximize();
		DRIVER.manage().deleteAllCookies();
		DRIVER.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
		DRIVER.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		DRIVER.get(prop.getProperty("url"));
		//Alert alert=DRIVER.switchTo().alert();
		//alert.dismiss();
	}
	
	public static boolean checkWebElementForAction(WebElement we){
		if(we.isDisplayed()==true){
			if(we.isEnabled()==true){
				return true;
			}else{
				System.out.println("web Element not enabled");
				return false;
			}
			}else{
				System.out.println("web Element not visible");
		}
		return false;
	}		
	
	public void selectByText(WebElement weObj,String optionText) throws IOException{
		
		if(checkWebElementForAction(weObj)==true){
		Select selObj=new Select(weObj);
		selObj.selectByVisibleText(optionText);
		}
	}
	
	public static String getConfiurationValue(String configName) throws IOException{
		String 	configValue=prop.getProperty(configName);
		//System.out.println("in get config vale method"+configValue);
		return configValue;	
		}
	
	private static Workbook getWorkBook(String filePath) throws IOException{
		FileInputStream fis=new FileInputStream(filePath);
		String[] strArr=filePath.split("\\.");
		String strExt=strArr[1];
		//System.out.println(strExt);
		Workbook wkObj=null;
		if(strExt.equalsIgnoreCase("xlsx")){
			wkObj = new XSSFWorkbook(fis);
		}else{
			wkObj=new HSSFWorkbook(fis);
		}return wkObj;
	}
	
	public static int getColumnNumberByCoulmnName(Sheet sheetObj,String argColumnName){
		Row headerRow=sheetObj.getRow(0);
		int columnCount=headerRow.getLastCellNum();
		int desiredColumnNumber=0;
		for(int i=0;i<=columnCount-1;i++){
			Cell cellObj=headerRow.getCell(i);
			String colName=cellObj.getStringCellValue();
			if(colName.equalsIgnoreCase(argColumnName)==true){
				desiredColumnNumber=i;
				break;
			}
		}return desiredColumnNumber;
	}
	
	public static List<Integer> getAllMatchedRowNumberList(String argTestCaseId) throws IOException{
		List<Integer> liData=new ArrayList<Integer>();
		try{
			
				String tdPath=getConfiurationValue("TestDataPath");
				//System.out.println(tdPath);
				Workbook WbkObj=getWorkBook(tdPath);
				Sheet sheetObj= WbkObj.getSheet("AllRestaurantsPg");
				//System.out.println("1111");
				//System.out.println("sheet name is"+sheetObj.getSheetName());
				int tcIdColumnNumber=getColumnNumberByCoulmnName(sheetObj, "TestCaseId");
				//System.out.println("column no. is : "+tcIdColumnNumber);
				int rowCount=sheetObj.getLastRowNum();
				//System.out.println("row count is : "+rowCount);
				
				for(int i=1;i<=rowCount;i++){
					Row rowObj=sheetObj.getRow(i);
					Cell cellObj = rowObj.getCell(tcIdColumnNumber);
					String cellValue=cellObj.getStringCellValue();
					//System.out.println("cell value is : "+cellValue);
					if(cellValue.equalsIgnoreCase(argTestCaseId)){
						liData.add(i);
					}
				}
				}
		catch(FileNotFoundException fe){
			fe.getMessage();
		}
		catch(Exception e){
			return null;
		}
		
		return liData;
	}
	
	
	public static void getTestCaseDatapoi(int desiredRowNumber) throws IOException{
		String tdPath=getConfiurationValue("TestDataPath");
		//System.out.println("in poi method"+tdPath);
		Workbook WbkObj=getWorkBook(tdPath);
		Sheet sheetObj= WbkObj.getSheet("AllRestaurantsPg");
		//System.out.println("in poi method,sheet name is:  "+sheetObj.getSheetName());
		MissingCellPolicy mcp=Row.MissingCellPolicy.CREATE_NULL_AS_BLANK;
		DataMap = new HashMap<String,String>();
		//System.out.println("desiredRowNumber is::  "+desiredRowNumber);
		Row rowObj=sheetObj.getRow(desiredRowNumber);
		int colCount=rowObj.getLastCellNum();
		//System.out.println("col count is:: "+colCount);
		int startDataNumber=getColumnNumberByCoulmnName(sheetObj, "FeldName1");
		//System.out.println("start data column num is:: "+startDataNumber);
		String dataKeyValue="";
		for(int i=startDataNumber;i<colCount-1;i=i+2){
			String dataKeyName=rowObj.getCell(i,mcp).getStringCellValue();
			if(dataKeyName.equals("")==false){
				Cell cellObj=rowObj.getCell(i+1,mcp);
				if(cellObj.getCellType()==CellType.STRING){
					dataKeyValue=rowObj.getCell(i+1,mcp).getStringCellValue();
				}else{
					Double dblValue=rowObj.getCell(i+1,mcp).getNumericCellValue();
					Integer intValue=dblValue.intValue();
					dataKeyValue=intValue.toString();
					}
				DataMap.put(dataKeyName, dataKeyValue);
			}
					
		}
		}
	
	public void getSnapshot() throws IOException{
		
		String destFilePath="Results\\Screenshots\\";
		File destFile=new File(destFilePath+System.currentTimeMillis()+".png");
		File srcFile= ((TakesScreenshot)DRIVER).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, destFile);
		
	}
	
}

