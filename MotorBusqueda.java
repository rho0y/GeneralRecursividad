import java.util.Comparator;

/**
 * Motor interno de la búsqueda binaria recursiva.
 *
 * Esta clase contiene ÚNICAMENTE la lógica recursiva pura:
 * no valida entradas, no tiene estado, no depende del tipo de dato.
 *
 * Principio de responsabilidad única (SRP): esta clase hace solo una cosa
 * → ejecutar el algoritmo de búsqueda binaria recursiva.
 *
 * Es de acceso package-private (sin modificador public) porque solo debe
 * ser usada internamente por BusquedaBinaria<T>.
 *
 * @param <T> El tipo de elemento sobre el que opera el algoritmo.
 */
class MotorBusqueda<T> {

    /**
     * Búsqueda binaria recursiva estándar: encuentra UNA ocurrencia del objetivo.
     *
     * Algoritmo (divide y vencerás):
     *   1. Si inicio > fin → el elemento no está → retorna -1  (CASO BASE: fallo)
     *   2. Calcula el punto medio del rango actual.
     *   3. Si array[medio] == objetivo → retorna medio          (CASO BASE: éxito)
     *   4. Si array[medio] >  objetivo → busca en la mitad izquierda  (RECURSIÓN)
     *   5. Si array[medio] <  objetivo → busca en la mitad derecha    (RECURSIÓN)
     *
     * Complejidad temporal: O(log n) — cada llamada descarta la mitad del rango.
     * Complejidad espacial: O(log n) — profundidad máxima de la pila recursiva.
     *
     * @param array      Array ORDENADO donde se busca.
     * @param objetivo   Elemento que se desea encontrar.
     * @param inicio     Límite izquierdo del rango actual (inclusive).
     * @param fin        Límite derecho del rango actual (inclusive).
     * @param comparator Lógica de comparación entre elementos.
     * @return Índice del objetivo en el array, o -1 si no se encontró.
     */
    int buscar(T[] array, T objetivo, int inicio, int fin, Comparator<T> comparator) {

        // ── CASO BASE: rango vacío → el elemento no existe en el array ──
        if (inicio > fin) {
            return -1;
        }

        // Cálculo seguro del medio: evita overflow que ocurriría con (inicio + fin) / 2
        // cuando inicio y fin son valores enteros muy grandes.
        int medio = inicio + (fin - inicio) / 2;

        // Comparamos el elemento central con el objetivo usando el Comparator
        // resultado < 0 → array[medio] < objetivo
        // resultado = 0 → array[medio] = objetivo  ← encontrado
        // resultado > 0 → array[medio] > objetivo
        int resultado = comparator.compare(array[medio], objetivo);

        // ── CASO BASE: encontramos el elemento ──
        if (resultado == 0) {
            return medio;
        }

        // ── CASO RECURSIVO: buscar en la mitad izquierda ──
        // El elemento del medio es MAYOR que el objetivo,
        // así que el objetivo solo puede estar a la izquierda del medio.
        if (resultado > 0) {
            return buscar(array, objetivo, inicio, medio - 1, comparator);
        }

        // ── CASO RECURSIVO: buscar en la mitad derecha ──
        // El elemento del medio es MENOR que el objetivo,
        // así que el objetivo solo puede estar a la derecha del medio.
        return buscar(array, objetivo, medio + 1, fin, comparator);
    }

    /**
     * Variante recursiva que encuentra la PRIMERA ocurrencia del objetivo.
     *
     * Cuando encuentra una coincidencia NO se detiene: guarda el índice
     * y sigue buscando hacia la IZQUIERDA para ver si hay una aparición anterior.
     *
     * @param ultimoEncontrado Mejor índice hallado hasta ahora (se acumula entre llamadas).
     * @return Índice de la primera ocurrencia, o -1 si no existe.
     */
    int primeraOcurrencia(T[] array, T objetivo, int inicio, int fin,
                           Comparator<T> comparator, int ultimoEncontrado) {
        // Caso base: rango agotado → retornamos el mejor índice acumulado
        if (inicio > fin) {
            return ultimoEncontrado;
        }

        int medio = inicio + (fin - inicio) / 2;
        int cmp = comparator.compare(array[medio], objetivo);

        if (cmp == 0) {
            // Coincidencia encontrada → guardamos este índice y seguimos hacia la IZQUIERDA
            return primeraOcurrencia(array, objetivo, inicio, medio - 1, comparator, medio);
        } else if (cmp > 0) {
            // El medio es mayor → el objetivo (si existe) está a la izquierda
            return primeraOcurrencia(array, objetivo, inicio, medio - 1, comparator, ultimoEncontrado);
        } else {
            // El medio es menor → el objetivo (si existe) está a la derecha
            return primeraOcurrencia(array, objetivo, medio + 1, fin, comparator, ultimoEncontrado);
        }
    }

    /**
     * Variante recursiva que encuentra la ÚLTIMA ocurrencia del objetivo.
     *
     * Cuando encuentra una coincidencia NO se detiene: guarda el índice
     * y sigue buscando hacia la DERECHA para ver si hay una aparición posterior.
     *
     * @param ultimoEncontrado Mejor índice hallado hasta ahora (se acumula entre llamadas).
     * @return Índice de la última ocurrencia, o -1 si no existe.
     */
    int ultimaOcurrencia(T[] array, T objetivo, int inicio, int fin,
                          Comparator<T> comparator, int ultimoEncontrado) {
        // Caso base: rango agotado → retornamos el mejor índice acumulado
        if (inicio > fin) {
            return ultimoEncontrado;
        }

        int medio = inicio + (fin - inicio) / 2;
        int cmp = comparator.compare(array[medio], objetivo);

        if (cmp == 0) {
            // Coincidencia encontrada → guardamos este índice y seguimos hacia la DERECHA
            return ultimaOcurrencia(array, objetivo, medio + 1, fin, comparator, medio);
        } else if (cmp > 0) {
            return ultimaOcurrencia(array, objetivo, inicio, medio - 1, comparator, ultimoEncontrado);
        } else {
            return ultimaOcurrencia(array, objetivo, medio + 1, fin, comparator, ultimoEncontrado);
        }
    }
}
