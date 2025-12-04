/**
 * SimpsonIntegration.java
 * Implementación detallada de la Regla de Simpson compuesta.
 */
public class SimpsonIntegration {

    public static double integrate(GammaFunction f, double a, double b, int segments) {

        // Validamos que segments sea par
        if (segments <= 0 || segments % 2 != 0)
            throw new IllegalArgumentException("segments debe ser par > 0");

        // Calculamos tamaño de paso
        double h = (b - a) / segments;

        // Suma inicial: f(a) + f(b)
        double sum = f.evaluate(a) + f.evaluate(b);

        // Recorremos cada punto intermedio
        for (int i = 1; i < segments; i++) {

            double x = a + i * h;       
            double fx = f.evaluate(x);  

            // Si i es par → multiplicamos por 2
            if (i % 2 == 0) sum += 2 * fx;
            // Si i es impar → multiplicamos por 4
            else sum += 4 * fx;
        }

        // Regla final: sum * (h/3)
        return sum * (h / 3.0);
    }
}
