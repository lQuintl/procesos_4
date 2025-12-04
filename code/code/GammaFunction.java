/*---------------------------------------------------------------------------*/
/* Program Assignment: Program 3 - PSP 0.1                                   */
/* Name: Rodriguez Quintero Luis Rodolfo                                     */
/* Date: 30/Nov/2025                                                         */
/* Description: Clase GammaFunction: clase con método main para ejecutar la aplicación.*/
/*---------------------------------------------------------------------------*/
public class GammaFunction {

    private final double n;   // Parámetro n almacenado en objeto

    public GammaFunction(double n) {
        this.n = n;           
    }

    public double evaluate(double x) {

        // Si x es negativo, devolvemos 0
        if (x < 0) return 0;

        // Calculamos x^(n-1)
        double potencia = Math.pow(x, n - 1);

        // Calculamos e^(-x)
        double exponencial = Math.exp(-x);

        // Retornamos producto
        return potencia * exponencial;
    }
}
