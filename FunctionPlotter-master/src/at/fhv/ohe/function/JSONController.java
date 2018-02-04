package at.fhv.ohe.function;

import at.fhv.ohe.function.exceptions.IllegalFunctionDataExceptionVersion;
import at.fhv.ohe.function.exceptions.IllegalFunctionFormatException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * An Controller for Saving and Loading Functions
 * <p>
 * Created by OliverHeil on 04.06.2017.
 */
public class JSONController {
    private static final double _version = 1.01;

    /**
     * Save all given functions in an File
     *
     * @param functions - The Functions that should be saved
     * @param filename  - The file where the Functions should be saved
     * @throws IOException
     */
    public static void saveFunctions(Function[] functions, File filename) throws IOException {
        LinkedList<JSONObject> list = new LinkedList<>();

        for (Function function : functions) {
            JSONObject obj = new JSONObject();
            obj.put("func", function.getFunctionString());      //TODO Wie bekommt man die Warnungen Weg???
            obj.put("visible", function.isVisible());
            list.add(obj);
        }

        JSONObject obj = new JSONObject();
        obj.put("header", "FunctionPlotter Data");
        obj.put("version", _version);
        obj.put("functions", list);

        try (FileWriter fileWriter = new FileWriter(filename)) {
            obj.writeJSONString(fileWriter);
        }
    }

    /**
     * Load all Functions out of an given File
     *
     * @param file - The File where the Functions are
     * @return - The Functions
     * @throws ParseException
     * @throws IOException
     * @throws IllegalFunctionDataExceptionVersion
     * @throws IllegalFunctionFormatException
     */
    public static Function[] loadFunctions(File file) throws ParseException, IOException, IllegalFunctionDataExceptionVersion, IllegalFunctionFormatException {
        String lines = "";

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();

            while (line != null) {
                lines = lines.concat(line);
                line = br.readLine();
            }
        }

        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(lines);

        if (!obj.get("version").equals(_version)) {
            throw new IllegalFunctionDataExceptionVersion("Wrong Version Number");
        }

        JSONArray jsonArray = (JSONArray) obj.get("functions");
        ArrayList<String> functionStringlist = new ArrayList<>();
        ArrayList<Boolean> visibleStringlist = new ArrayList<>();

        for (Object jsonObj : jsonArray) {
            functionStringlist.add(((JSONObject) jsonObj).get("func").toString());
            visibleStringlist.add((Boolean) ((JSONObject) jsonObj).get("visible"));
        }

        List<Function> functions = Collections.synchronizedList(new ArrayList<Function>());
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (int i = 0; i < functionStringlist.size(); i++) {
            String s = functionStringlist.get(i);
            Boolean v = visibleStringlist.get(i);
            Runnable worker = new FuncionCreateTask(functions, s, v);
            executor.execute(worker);
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        return functions.toArray(new Function[functions.size()]);
    }
}
