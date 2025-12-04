/***************************************************************************
 * Programa 5a: Simpson Integration of t Distribution
 * Clase:    SimpsonIntegration
 * Autor:   Luis Rodolfo Rodriguez Quintero
 * Fecha:     4/Diciembre/2025
 *
 * Descrpción:
 *   Encapsula el cálculo del valor de la integral de la distribución t
 *   usando la regla de Simpson, para un DOF y un límite x dados.

 ***************************************************************************/

/**
 * Clase SimpsonIntegration.
 * 
 * Implementa la integración numérica de la distribución t mediante
 * la regla de Simpson, para un número de segmentos dado, un valor x 
 * y unos grados de libertad (DOF).
 */
public class SimpsonIntegration
{

    /*  Atributos

    /** Número de segmentos usados en la integración de Simpson. */
    private int intNumSeg;

    // w = x / num_seg*/
    private double dblW;

    /** Error aceptable*/
    private double dblE;              

    /** Grados de libertad de la distribución t. */
    private int intDOF;

    /** Límite superior de integración (desde 0 hasta dblX). */
    private double dblX;

    /** Arreglo de puntos Xi sobre el intervalo [0, x]. */
    private double[] dblTotXi;

    /** Términos base: 1 + (Xi² / dof). */
    private double[] dblFirstBaseTerms;

    /** Exponente para la función de densidad de t. */
    private double dblExponent;

    /** Coeficiente multiplicador de la función de densidad de t. */
    private double dblCoeff;

    /** Valores evaluados de la función f(Xi) para cada Xi. */
    private double[] dblFxi;

    /** Términos finales f(Xi) con los multiplicadores 1,4,2,...,4,1. */
    private double[] dblFinalTerms;

    /** Valor final de la integral para un número de segmentos dado. */
    private double dblFinalValue;

    /** Objeto auxiliar para cálculo de funciones Gamma. */
    private GammaFunction objGamma;


  
    public SimpsonIntegration(int intDOFIn, double dblXIn) 
    {
        this.intDOF = intDOFIn; // Guarda DOF.
        this.dblX   = dblXIn;   // Guarda límite superior.
        this.dblE   = 0.00001;  // Tolerancia por defecto.
        this.objGamma = new GammaFunction(); // Inicializa helper Gamma.
    }


    /*  Public Simpson Helper Methods                                   */

    /**
     * @param intNumSegIn número de segmentos (debe ser par para Simpson).
     * @param dblXIn      límite superior de integración.
     */
    public void computeW(int intNumSegIn, double dblXIn) 
    {
        this.intNumSeg = intNumSegIn; // Guarda número de segmentos.
        this.dblX = dblXIn;           // Guarda límite superior.
        this.dblW = dblXIn / (double) intNumSegIn; // Calcula ancho w.
    }

    /**
     * @param intNumSegIn número de segmentos de integración.
     */
    public void computeXi(int intNumSegIn) 
    {
        int size = intNumSegIn + 1; // Cantidad de puntos Xi.
        dblTotXi = new double[size]; // Crea arreglo de puntos.

        for (int i = 0; i <= intNumSegIn; i++) // Recorre índices.
        {
            dblTotXi[i] = dblW * (double) i; // Xi = i * w.
        }
    }

    /**
     * @param intNumSegIn   número de segmentos.
     * @param dblTotXiIn    arreglo de puntos Xi.
     * @param intDOFIn      grados de libertad.
     */
    public void computeFirstBaseTerms(int intNumSegIn,
                                      double[] dblTotXiIn,
                                      int intDOFIn) 
    {
        int size = intNumSegIn + 1; // Tamaño de arreglos.
        dblFirstBaseTerms = new double[size]; // Inicializa arreglo.

        for (int i = 0; i <= intNumSegIn; i++) // Recorre cada Xi.
        {
            double xi = dblTotXiIn[i]; // Valor Xi local.
            dblFirstBaseTerms[i] = 1.0 + (xi * xi) / (double) intDOFIn; // base term.
        }
    }

    /**
     * @param intDOFIn grados de libertad.
     */
    public void computeExponent(int intDOFIn) 
    {
        dblExponent = -((double) intDOFIn + 1.0) / 2.0; // Calcula exponente.
    }

    /**
     * @param intDOFIn grados de libertad.
     */
    public void computeCoefficient(int intDOFIn) 
    {
        double nu1 = ((double) intDOFIn + 1.0) / 2.0; // (nu+1)/2
        double nu2 = ((double) intDOFIn) / 2.0;      // nu/2

        double top = objGamma.computeDblGamma(nu1); // Numerador Gamma((nu+1)/2)
        double bottom = Math.sqrt(intDOFIn * Math.PI) * objGamma.computeDblGamma(nu2); // Denominador

        dblCoeff = top / bottom; // Coeficiente final.
    }

    /**
     * @param intNumSegIn número de segmentos.
     */
    public void computeFxi(int intNumSegIn) 
    {
        int size = intNumSegIn + 1; // Tamaño de arreglo.
        dblFxi = new double[size]; // Inicializa.

        for (int i = 0; i <= intNumSegIn; i++) // Recorre índices.
        {
            dblFxi[i] = dblCoeff * Math.pow(dblFirstBaseTerms[i], dblExponent); // Evalúa f(xi).
        }
    }

    /**
     * Aplica los multiplicadores de Simpson (1,4,2,4,...,4,1) a cada
     * valor f(Xi), generando los términos finales a sumar.
     *
     * @param intNumSegIn número de segmentos.
     */
    public void computeFinalTerms(int intNumSegIn) 
    {
        int size = intNumSegIn + 1; // Tamaño de arreglo.
        dblFinalTerms = new double[size]; // Inicializa.

        for (int i = 0; i <= intNumSegIn; i++) // Recorre cada término.
        {
            int mult; // Multiplicador según posición.
            if (i == 0 || i == intNumSegIn) // Extremos.
            {
                mult = 1; // Extremos multiplicador 1.
            }
            else if (i % 2 == 1) // Índices impares.
            {
                mult = 4; // Impares -> 4.
            }
            else // Índices pares (no extremos).
            {
                mult = 2; // Pares -> 2.
            }

            dblFinalTerms[i] = (double) mult * dblFxi[i]; // Guarda término final.
        }
    }

    /**
     * Método principal de esta clase: calcula el valor final de la integral
     * con la regla de Simpson para un número de segmentos dado.
     * @param intNumSegIn número de segmentos (debe ser par).
     * @return valor aproximado de la integral desde 0 hasta x.
     */
    public double computeFinalValue(int intNumSegIn) 
    {
        double sum = 0.0; // Acumulador de la suma.

        // Secuencia de cálculos tal como en el diseño original.
        computeW(intNumSegIn, dblX); // Calcula w.
        computeXi(intNumSegIn); // Genera Xi.
        computeFirstBaseTerms(intNumSegIn, dblTotXi, intDOF); // base terms.
        computeExponent(intDOF); // exponente.
        computeCoefficient(intDOF); // coeficiente.
        computeFxi(intNumSegIn); // evalúa f(xi).
        computeFinalTerms(intNumSegIn); // aplica multiplicadores.

        // Suma todos los términos finales.
        for (int i = 0; i <= intNumSegIn; i++) // Recorre todo el arreglo.
        {
            sum += dblFinalTerms[i]; // Acumula.
        }

        // Aplica el factor (w / 3) según la regla de Simpson.
        dblFinalValue = (dblW / 3.0) * sum; // Valor final.

        return dblFinalValue; // Devuelve aproximación.
    }

    /**
     * Retorna el último valor de integral calculado.
     *
     * @return valor final de la integral almacenado en dblFinalValue.
     */
    public double getFinalValue() 
    {
        return dblFinalValue; // Devuelve valor almacenado.
    }
}
