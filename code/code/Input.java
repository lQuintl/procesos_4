/*---------------------------------------------------------------------------*/
/* Program Assignment: Program 3 - PSP 0.1                                   */
/* Name: Rodriguez Quintero Luis Rodolfo                                     */
/* Date: 30/Nov/2025                                                         */
/* Description: Clase Input: clase con método main para ejecutar la aplicación.*/
/*---------------------------------------------------------------------------*/

import java.io.*;

public class Input {

    public static double[] read(String filename) throws Exception {

        // Variables donde almacenaremos lo que encontremos
        Double n = null;        
        Integer segments = null;  

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String line;

            // Leemos cada línea del archivo
            while ((line = br.readLine()) != null) {

                // Quitamos espacios al inicio y fin
                line = line.trim();

                // Ignoramos líneas vacías o de comentario
                if (line.isEmpty() || line.startsWith("#")) continue;

                // Dividimos clave=valor
                String[] parts = line.split("=", 2);

                if (parts.length != 2)
                    throw new Exception("Línea inválida: " + line);

                String key = parts[0].trim().toLowerCase();
                String val = parts[1].trim();

                // Procesamos cada clave
                if (key.equals("n")) {
                    n = Double.parseDouble(val);
                } else if (key.equals("segments")) {
                    segments = Integer.parseInt(val);
                }
            }

        } catch (IOException e) {
            throw new Exception("No se pudo leer input.txt: " + e.getMessage());
        }

        // Validaciones finales
        if (n == null) throw new Exception("Falta parámetro n");
        if (segments == null) throw new Exception("Falta parámetro segments");
        if (segments <= 0 || segments % 2 != 0)
            throw new Exception("segments debe ser par > 0");

        return new double[] { n, segments };
    }
}
