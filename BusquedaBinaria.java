import java.util.Comparator;

/**
 * API pública de la Búsqueda Binaria Recursiva Genérica.
 *
 * Esta clase es la FACHADA (patrón Facade): expone métodos simples y claros
 * al código cliente, ocultando la complejidad interna de MotorBusqueda<T>.
 *
 * Responsabilidades de esta clase:
 *   1. Validar las entradas antes de iniciar la búsqueda.
 *   2. Delegar la lógica recursiva a MotorBusqueda<T>.
 *   3. Proveer una API limpia y fácil de usar.
 *
 * Uso básico:
 * <pre>
 *   BusquedaBinaria<Integer> busqueda = new BusquedaBinaria<>();
 *   int indice = busqueda.buscar(array, 42, Comparator.naturalOrder());
 * </pre>
 *
 * REQUISITO: el array debe estar ORDENADO antes de llamar a cualquier método.
 *
 * @param <T> El tipo de elemento que se va a buscar.
 */
public class BusquedaBinaria<T> {

    /**
     * Motor interno que ejecuta la recursión.
     * Se instancia una sola vez y se reutiliza en todos los métodos.
     */
    private final MotorBusqueda<T> motor = new MotorBusqueda<>();

    // ─────────────────────────────────────────────────────────────────────────
    // BÚSQUEDA ESTÁNDAR
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Busca el objetivo en el array y retorna su índice.
     *
     * Si hay duplicados, puede retornar cualquier ocurrencia (no necesariamente
     * la primera ni la última). Para controlar eso, usa primeraOcurrencia()
     * o ultimaOcurrencia().
     *
     * @param array      Array ORDENADO donde se buscará.
     * @param objetivo   Elemento que se desea encontrar.
     * @param comparator Lógica de comparación. Usa Comparator.naturalOrder()
     *                   para tipos con orden natural (Integer, String, etc.).
     * @return Índice del objetivo, o -1 si no existe en el array.
     * @throws IllegalArgumentException si el array o el objetivo son null.
     */
    public int buscar(T[] array, T objetivo, Comparator<T> comparator) {
        validar(array, objetivo);
        // Delegamos al motor con el rango completo del array
        return motor.buscar(array, objetivo, 0, array.length - 1, comparator);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // BÚSQUEDA CON DUPLICADOS
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Busca la PRIMERA ocurrencia del objetivo en el array.
     *
     * Útil cuando el array contiene elementos repetidos y necesitas
     * saber dónde comienza la secuencia de ese valor.
     *
     * @param array      Array ORDENADO donde se buscará.
     * @param objetivo   Elemento que se desea encontrar.
     * @param comparator Lógica de comparación.
     * @return Índice de la primera ocurrencia, o -1 si no existe.
     */
    public int primeraOcurrencia(T[] array, T objetivo, Comparator<T> comparator) {
        validar(array, objetivo);
        // Iniciamos con ultimoEncontrado = -1 (ninguna coincidencia aún)
        return motor.primeraOcurrencia(array, objetivo, 0, array.length - 1, comparator, -1);
    }

    /**
     * Busca la ÚLTIMA ocurrencia del objetivo en el array.
     *
     * Útil cuando el array contiene elementos repetidos y necesitas
     * saber dónde termina la secuencia de ese valor.
     *
     * @param array      Array ORDENADO donde se buscará.
     * @param objetivo   Elemento que se desea encontrar.
     * @param comparator Lógica de comparación.
     * @return Índice de la última ocurrencia, o -1 si no existe.
     */
    public int ultimaOcurrencia(T[] array, T objetivo, Comparator<T> comparator) {
        validar(array, objetivo);
        // Iniciamos con ultimoEncontrado = -1 (ninguna coincidencia aún)
        return motor.ultimaOcurrencia(array, objetivo, 0, array.length - 1, comparator, -1);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // VALIDACIÓN INTERNA
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Valida que el array y el objetivo no sean null.
     * Se llama antes de cada operación de búsqueda para evitar NullPointerException.
     *
     * @throws IllegalArgumentException si alguno de los argumentos es null.
     */
    private void validar(T[] array, T objetivo) {
        if (array == null) {
            throw new IllegalArgumentException("El array no puede ser null.");
        }
        if (objetivo == null) {
            throw new IllegalArgumentException("El objetivo de búsqueda no puede ser null.");
        }
    }
}
