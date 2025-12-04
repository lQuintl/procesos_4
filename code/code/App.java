/**
 * App.java
 * Punto de entrada del programa.
 * Cada línea está comentada para explicar el flujo completo del programa.
 */
public class App {

    public static void main(String[] args) {

        // Declaramos variables donde guardaremos los valores leídos desde input.txt
        double n;        // Valor n para Gamma(n)
        int segments;    // Cantidad de segmentos para Simpson

        try {
            // Llamamos a Input.read para leer y validar el archivo input.txt
            double[] params = Input.read("input.txt");  
            // El arreglo params contiene dos valores: params[0] = n, params[1] = segments
            n = params[0];                
            segments = (int) params[1];  
        } catch (Exception e) {
            // Si ocurre cualquier error al leer input.txt, lo mostramos y terminamos ejecución
            System.out.println("Error leyendo input.txt: " + e.getMessage());
            return;
        }

        // Creamos el objeto Logic, encargado de ejecutar el cálculo principal
        Logic logic = new Logic(n, segments);

        // Variable donde guardaremos el resultado final
        double result;

        try {
            // Ejecutamos el cálculo de Gamma(n)
            result = logic.run();        
        } catch (Exception e) {
            // Si ocurre cualquier error matemático, lo mostramos
            System.out.println("Error durante cálculo: " + e.getMessage());
            return;
        }

        try {
            // Guardamos el resultado en output.txt
            Output.save("output.txt", n, segments, result);
        } catch (Exception e) {
            // Si ocurre un error escribiendo archivo, lo reportamos
            System.out.println("Error escribiendo output.txt: " + e.getMessage());
            return;
        }

        // Finalmente, informamos al usuario que todo salió bien
        System.out.println("Cálculo finalizado. Resultado escrito en output.txt");
    }
}
