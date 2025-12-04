/***************************************************************************
 * Programa 5a :  Simpson Integration of t Distribution
 * Clase:    App
 * Autor:   Luis Rodolfo Rodriguez Quintero
 * Fecha:   4/Diciembre/2025
 *
 * Descripción:Punto de entrada del programa. 
 * Crea el objeto Logic y dispara el procesamiento principal.
 ***************************************************************************/

public class App
{

    /**
     * Método principal (main) del programa.
     *
     * Este método actúa como punto de entrada de la aplicación. Su única
     * responsabilidad es:
     *
     * 1.Declarar una instancia de la clase Logic.
     * 2.Inicializar el objeto Logic.
     * 3.Invocar el método logic1a(), que contiene el flujo principal del programa.
     */
    public static void main(String[] args)
    {
        // Crear y ejecutar la lógica principal del programa.
        Logic controller = new Logic(); // Instancia de la clase que orquesta el flujo.
        controller.logic1a();            // Ejecuta el procesamiento principal.
    }
}
