package com.company;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by swethakolalapudi on 10/10/15.
 */
public class ExcelWriter {
    // we will use this class to write out an excel spreadsheet using the library called POI

    // Poi is an apache library, which you can download from the internet.
    // Java libraries are distributed in the form of '.jar' files , which tehn
    // need to be added to the project

    private OneDayMarketAction m_odma;
    // this is a member variable with the market action day

    public ExcelWriter(OneDayMarketAction odma) {
        this.m_odma=odma;
    }
    // a constructor

    public void createFile(String excelFileName){
        // Here is what we need to do
        // 1. Get the list of tickers that moved the most today (can do using the getSortedMovers()
        // method from the OneDayMarketAction object
        List<OneDayMarketAction.OneTickerOneDay> otod = m_odma.getSortedMovers();

        // 2. Next, create a data structure with the information that we wish to write to excel

        // this is a bit complicated
        // 2.a create an excel workboo data structure  (in Poi)
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 2. b create an excel worksheet inside this workbook (in Poi)
        HSSFSheet worksheet = workbook.createSheet("Summary");

        // 2. c get the data we wish to write in the form of rows (this can be done using simple arrays)
        Map<String,Object[]> dataInRows = new HashMap<>();
        // Ok - this looks a bit peculiar. The reason we create a map (set of key-value pairs)
        // where the key is a string and the value is an object array is because Poi needs data
        // in the form of 'Object'
        // The type 'Object' is the base class for all java reference types. So any type of data
        // could be put in an array of objects. This is not very type-sae, but here there is no
        // choice but do so.

        // key row=number, value=object array of values to write to the excel sheet
        dataInRows.put("1", new Object[] {"Ticker","Close","Prev Close", "%Change"});
        // header row. Btw, notice that the Excel rows are indexed starting from 1, not from 0
        dataInRows.put("2", new Object[] {
                otod.get(0).getM_ticker(),// Ticker
                otod.get(0).getM_close(), // Closing Price
                otod.get(0).getM_prevClose(), // Previous day closing price
                otod.get(0).getPctChange()});// Daily % move
        // what did we do here? We inserted a key-value pair into the map 'dataInRows'
        // key = "1", value = Array of objects to be written into Excel

        // What are these values? They are the Ticker, Closing Price, Previous Day's closing price and the
        // Daily % Move

        // Why do we do the get(0)? Its because the list 'otod' is returned by the method 'getSortedMovers', which means
        //that it is in the order of movers (smallest i.e. biggest fallers, to largest, ie biggest gainers)
        dataInRows.put("3", new Object[] {
                otod.get(1).getM_ticker(),// Ticker
                otod.get(1).getM_close(), // Closing Price
                otod.get(1).getM_prevClose(), // Previous day closing price
                otod.get(1).getPctChange()});// Daily % move
        dataInRows.put("4", new Object[] {
                otod.get(2).getM_ticker(),// Ticker
                otod.get(2).getM_close(), // Closing Price
                otod.get(2).getM_prevClose(), // Previous day closing price
                otod.get(2).getPctChange()});// Daily % move
        // repeat the same for the next 2 largest movers

        // 2d. write the values to the cells of the Excel worksheet (in Poi)

        // Ok, the way to do this is
        // 2d i. get the rows 1-by-1
        for (int rowNum = 0; rowNum<4; rowNum++){
            String key = (rowNum + 1) + "";
            // convert the row number into the key in the data structure we just created

            // 2d ii. for each row create a Poi row object

            Row row = worksheet.createRow(rowNum+1);
            Object[] values = dataInRows.get(key);

            // 2d. iii for each cell in each row create a Poi cell object from the Poi row object

            int cellNum=0;
            for (Object oneObject : values){
                Cell cell = row.createCell(cellNum++);
                // hmmm... this presents a challenge. We need to cast the objects back to their specific
                // types for the poi to work.
                if(oneObject instanceof String){
                    cell.setCellValue((String) oneObject);
                     } else if(oneObject instanceof Double){
                    cell.setCellValue((Double) oneObject);
                    }  else if(oneObject instanceof Date){
                    cell.setCellValue((Date)oneObject);
                    } else if (oneObject instanceof Boolean){
                    cell.setCellValue((Boolean) oneObject);
                }

                // what did we jsut do? We made use of two new features here.
                // 1. instanceof is a way of asking a variable 'what type are you?'
                // for instance, the conditional test (oneObject instanceof Boolean)
                // will return true if the object is a boolen variable, and false otherwise
                // 2. The other interesting thing we did was to 'cast' objects from one type to another
                // The bit (Boolean) oneObject is telling Java "Please treat the variable oneObject as a boolean variable

                // what if its actually not a boolean variable? In that case when the code runs, an error would
                // be thrown , called a ClassCastException
            }

        }


        // 3. Use Poi to actually write this data to Excel
        // Ok this is relatively straightforward file-handling stuff

        try{
            FileOutputStream fos = new FileOutputStream(new File(excelFileName));
            workbook.write(fos);
            fos.close();
            System.out.println("Excel writen successfully");

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }


    }

}
