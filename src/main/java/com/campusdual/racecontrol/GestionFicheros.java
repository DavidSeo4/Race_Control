package com.campusdual.racecontrol;

import com.campusdual.racecontrol.model.Coche;
import com.campusdual.racecontrol.model.Torneo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GestionFicheros {


    public void escribirFichero(List lista, String nombreFichero) {

        try (FileWriter fileWriter = new FileWriter(nombreFichero)) {

            // JSONArray jsonArray = new JSONArray();
            // jsonArray.addAll(lista);
            //fileWriter.write(jsonArray.toJSONString());
            //HashMap<String, List> hash = new HashMap<>();
            //hash.put("Coches", lista);

            JSONObject obj = new JSONObject();

            switch (nombreFichero) {
                case "coches.json":
                    obj.put("Coches", lista);
                    fileWriter.write(String.valueOf(obj));
                    break;
                case "torneos.json":
                    obj.put("Torneos", lista);
                    fileWriter.write(String.valueOf(obj));
                    break;
                case "garajes.json":
                    obj.put("Garajes", lista);
                    fileWriter.write(String.valueOf(obj));
                    break;
                case "carreras.json":
                    obj.put("Carreras", lista);
                    fileWriter.write(String.valueOf(obj));
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List leerFichero(String nombreFichero) {

        try (FileReader fileReader = new FileReader(nombreFichero)) {
            JSONParser jsonParser = new JSONParser();
            JSONArray object2 = (JSONArray) jsonParser.parse(fileReader); //ERROR-> UNEXPECTED CHARACTER AL LEER Y JSON:NULL (ERROR AL ANALIZAR EL ARCHIVO)
            System.out.println(object2.toString());

            JSONObject object = (JSONObject) jsonParser.parse(fileReader);

            List list = new ArrayList(object.entrySet());

            return list;

        } catch (ParseException | FileNotFoundException pe) {
            System.err.println("Error al analizar el archivo JSON: " + pe.getMessage());
            pe.printStackTrace();
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
