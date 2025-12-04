/***************************************************************************
 * Programa 5a: Simpson Integration of t Distribution
 * Clase:    Logic
 * Autor:   Luis Rodolfo Rodriguez Quintero
 * Fecha:     4/Diciembre/2025
 *
 *   - Usa SimpsonIntegration, GammaFunction y Output.
 ***************************************************************************/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Logic
{

    /*  Atributos

    /** Número de segmentos a usar en la integración (debe ser par). */
    private int intNumSeg;   

    /** Error máximo aceptable para la aproximación numérica. */
    private double dblE;     

    /** Grados de libertad de la distribución t. */
    private int intDOF;      

    /** Límite superior de integración (valor de x). */
    private double dblX;     



    //Metodos publicos

    public void logic1a() 
    {
        final String inFile  = "input.txt";  // Nombre del archivo de entrada.
        final String outFile = "result.txt"; // Nombre del archivo de salida.

        StringBuilder outBuffer = new StringBuilder(); // Buffer para salida.
        BufferedReader reader = null; // Lector a usar.
        String line; // Línea leída.

        // Parámetros iniciales.
        dblE = 0.00001; // Tolerancia de error.
        intNumSeg = 10; // Número inicial de segmentos (par).

        try
        {
            reader = new BufferedReader(new FileReader(inFile)); // Abrir archivo.

            while ((line = reader.readLine()) != null) // Leer línea por línea.
            {
                line = line.trim(); // Quitar espacios alrededor.

                // Ignorar líneas vacías o que comiencen con '#'.
                if (line.length() == 0 || line.startsWith("#"))
                {
                    continue; // Salta a la siguiente iteración.
                }

                // Tokenizar por espacios (x y dof).
                String[] parts = line.split("\\s+"); // Divide la línea.
                if (parts.length < 2) // Si faltan tokens, ignorar.
                {
                    continue; // Línea mal formada.
                }

                // Parseo de los parámetros de entrada.
                dblX = Double.parseDouble(parts[0]); // Lee x.
                intDOF = Integer.parseInt(parts[1]); // Lee dof.

                // Objeto encargado de la integración para estos parámetros.
                SimpsonIntegration simpson = new SimpsonIntegration(intDOF, dblX); // Crea objeto.

                // Primera aproximación con el número inicial de segmentos.
                int currentSeg = intNumSeg; // Número actual de segmentos.
                double prevVal = simpson.computeFinalValue(currentSeg); // Calcula aproximación.

                double currVal; // Valor actual en la iteración.

                // Repetir refinando (duplicando segmentos) hasta la tolerancia.
                while (true)
                {
                    currentSeg *= 2; // Duplica resolución.
                    currVal = simpson.computeFinalValue(currentSeg); // Recalcula.

                    if (Math.abs(currVal - prevVal) <= dblE) // Si la diferencia es menor que E.
                    {
                        break; // Condición de paro.
                    }

                    prevVal = currVal; // Actualiza valor previo.
                }

                double p = currVal; // Probabilidad final.

                // Formatear línea de salida.
                String outLine = String.format("x = %.4f   dof = %d   p = %.5f%n",
                                               dblX, intDOF, p); // Formato como en original.

                outBuffer.append(outLine); // Acumula.
                System.out.print(outLine); // Muestra por consola.
            }

            // Cerrar lector si fue abierto.
            if (reader != null)
            {
                reader.close(); // Cierra recurso.
            }

            // Escribir todo el buffer al archivo de salida.
            Output writer = new Output(); // Crea Output.
            writer.writeData(outFile, outBuffer.toString()); // Escribe archivo.

        }
        catch (IOException e) // Errores de archivo.
        {
            System.err.println("Error al leer archivo de entrada: " + e.getMessage());
        }
        catch (NumberFormatException e) // Errores en parseo numérico.
        {
            System.err.println("Error en formato numérico de test.txt: " + e.getMessage());
        }
    }
}
