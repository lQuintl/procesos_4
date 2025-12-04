/**
 * GammaFunction.java
 * Implementa el integrando f(x) = x^(n-1) * e^(-x)
 */
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
