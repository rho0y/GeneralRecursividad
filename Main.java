import java.util.Arrays;
import java.util.Comparator;

/**
 * Clase principal de demostración.
 *
 * Muestra 4 casos de uso distintos de BusquedaBinaria<T>:
 *   1. Array de enteros (Integer)
 *   2. Array de Strings (orden alfabético)
 *   3. Array de objetos personalizados (Producto), buscando por precio
 *   4. Array con duplicados: primera y última ocurrencia
 *
 * Para ejecutar desde consola (en el directorio que contiene los .java):
 *   javac *.java
 *   java Main
 */
public class Main {

    public static void main(String[] args) {

        // Creamos instancias de BusquedaBinaria para cada tipo de dato
        BusquedaBinaria<Integer>  busquedaInt  = new BusquedaBinaria<>();
        BusquedaBinaria<String>   busquedaStr  = new BusquedaBinaria<>();
        BusquedaBinaria<Producto> busquedaProd = new BusquedaBinaria<>();

        encabezado("BÚSQUEDA BINARIA RECURSIVA — DEMO");

        // ── CASO 1: Array de enteros ──────────────────────────────────────────
        encabezado("CASO 1: Enteros");
        Integer[] numeros = {2, 5, 8, 12, 16, 23, 38, 45, 67, 89, 100};
        System.out.println("Array: " + Arrays.toString(numeros));

        imprimir("Buscar 23",
                busquedaInt.buscar(numeros, 23, Comparator.naturalOrder())); // → 5

        imprimir("Buscar 99 (no existe)",
                busquedaInt.buscar(numeros, 99, Comparator.naturalOrder())); // → -1

        // ── CASO 2: Array de Strings ──────────────────────────────────────────
        encabezado("CASO 2: Strings (orden alfabético)");
        String[] palabras = {"árbol", "casa", "cielo", "luna", "mar", "sol", "viento"};
        System.out.println("Array: " + Arrays.toString(palabras));

        imprimir("Buscar \"luna\"",
                busquedaStr.buscar(palabras, "luna", Comparator.naturalOrder())); // → 3

        imprimir("Buscar \"fuego\" (no existe)",
                busquedaStr.buscar(palabras, "fuego", Comparator.naturalOrder())); // → -1

        // ── CASO 3: Objetos personalizados (Producto) ─────────────────────────
        encabezado("CASO 3: Objetos personalizados — Producto por precio");

        // Array ya ordenado por precio ascendente (requisito de la búsqueda binaria)
        Producto[] productos = {
            new Producto("Lápiz",    0.50),
            new Producto("Cuaderno", 2.99),
            new Producto("Mochila",  15.00),
            new Producto("Tablet",   299.99),
            new Producto("Laptop",   899.00),
        };

        System.out.println("Productos disponibles:");
        for (int i = 0; i < productos.length; i++) {
            System.out.printf("  [%d] %s%n", i, productos[i]);
        }

        // Definimos cómo comparar dos Productos: por su campo precio
        Comparator<Producto> porPrecio = Comparator.comparingDouble(p -> p.precio);

        // Creamos un Producto "dummy" solo para llevar el precio que buscamos
        int idx = busquedaProd.buscar(productos, new Producto("?", 15.00), porPrecio);
        System.out.printf("Buscar precio $15.00 → índice %d → %s%n", idx, idx >= 0 ? productos[idx] : "no encontrado");

        idx = busquedaProd.buscar(productos, new Producto("?", 50.00), porPrecio);
        System.out.printf("Buscar precio $50.00 → índice %d (no existe)%n", idx);

        // ── CASO 4: Duplicados ────────────────────────────────────────────────
        encabezado("CASO 4: Duplicados — primera y última ocurrencia");
        Integer[] conDuplicados = {1, 3, 3, 3, 5, 7, 7, 9};
        System.out.println("Array: " + Arrays.toString(conDuplicados));

        int primera = busquedaInt.primeraOcurrencia(conDuplicados, 3, Comparator.naturalOrder());
        int ultima  = busquedaInt.ultimaOcurrencia(conDuplicados,  3, Comparator.naturalOrder());

        System.out.println("Primera ocurrencia de 3 → índice: " + primera); // → 1
        System.out.println("Última  ocurrencia de 3 → índice: " + ultima);  // → 3

        // ── CASO LÍMITE: búsqueda en array vacío ──────────────────────────────
        encabezado("CASO LÍMITE: Array vacío");
        Integer[] vacio = {};
        imprimir("Buscar 5 en array vacío",
                busquedaInt.buscar(vacio, 5, Comparator.naturalOrder())); // → -1
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Métodos auxiliares de presentación en consola
    // ─────────────────────────────────────────────────────────────────────────

    /** Imprime un encabezado de sección con separadores visuales. */
    private static void encabezado(String titulo) {
        System.out.println("\n══════════════════════════════════════════");
        System.out.println("  " + titulo);
        System.out.println("══════════════════════════════════════════");
    }

    /** Imprime el resultado de una búsqueda con formato consistente. */
    private static void imprimir(String descripcion, int indice) {
        String resultado = indice >= 0 ? "índice " + indice : "no encontrado (-1)";
        System.out.printf("%-35s → %s%n", descripcion, resultado);
    }
}
