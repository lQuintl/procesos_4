/**
 * Logic.java
 * Se encarga del flujo matemático principal.
 */
public class Logic {

    private final double n;         // Parámetro para Gamma(n)
    private final int segments;     // Cantidad de segmentos para Simpson

    public Logic(double n, int segments) {
        this.n = n;                 
        this.segments = segments;   
    }

    public double run() {

        // Creamos la función Gamma(n)
        GammaFunction gamma = new GammaFunction(n);

        // Intervalo donde calcularemos la integral
        double a = 0.0;    
        double b = 20.0;   

        // Llamamos a la regla de Simpson
        double result = SimpsonIntegration.integrate(gamma, a, b, segments);

        return result;
    }
}
