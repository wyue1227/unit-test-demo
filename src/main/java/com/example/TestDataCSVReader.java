package com.example;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class TestDataCSVReader {

    private static final String _BASE_DIRECTORY = "src/test/java/";

    private Map<Integer, ArrayList<String>> mapData = null;

    private Class clazz = null;

    private boolean isUTF8 = true;

    TestDataCSVReader(Class clazz) {
        this.clazz = clazz;
    }

    TestDataCSVReader(Class clazz, boolean isUTF8) {
        this.clazz = clazz;
        this.isUTF8 = isUTF8;
    }

    public Map<Integer, ArrayList<String>> getData(String fileName) {

        readCSVFile(fileName);
        return mapData;
    }

    private void readCSVFile(String fileName) {
        mapData = new HashMap<>();

        String packageName = clazz.getName().replace(".", "/");
        String filePath = _BASE_DIRECTORY + packageName + "Data/" + fileName + ".csv";

        try {
            Reader file = new FileReader(filePath);
            CSVFormat format = CSVFormat.DEFAULT.builder().build();
            Iterable<CSVRecord> datas = format.parse(file);
            for (CSVRecord rowData : datas) {
                List<String> dataList = rowData.toList();

                for (int i = 0; i < dataList.size(); i++) {
                    ArrayList<String> tmpList = mapData.get(i);
                    if (tmpList == null) {
                        tmpList = new ArrayList<>();
                    }
                    tmpList.add(formatCell(rowData.get(i)));
                    mapData.put(i, tmpList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String formatCell(String cellValue) {
        if (cellValue == null || cellValue.toLowerCase() == "null") {
            return null;
        } else if (cellValue == "\"\"") {
            return "";
        } else {
            return cellValue;
        }
    }
}