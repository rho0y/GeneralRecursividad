/**
 * Modelo de ejemplo que representa un producto con nombre y precio.
 *
 * Esta clase sirve para demostrar que BusquedaBinaria<T> puede trabajar
 * con cualquier objeto personalizado, no solo tipos primitivos.
 *
 * En un proyecto real, aquí irían tus propias clases: Empleado, Cliente,
 * Pedido, etc. Solo necesitas definir cómo compararlos (ver Main.java).
 */
public class Producto {

    // Atributos del producto
    public final String nombre;
    public final double precio;

    /**
     * Constructor principal.
     *
     * @param nombre Nombre del producto.
     * @param precio Precio del producto (debe ser >= 0).
     */
    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    /**
     * Representación en texto del producto, útil para imprimir en consola.
     * Formato: Producto{nombre='Laptop', precio=$899.00}
     */
    @Override
    public String toString() {
        return String.format("Producto{nombre='%s', precio=$%.2f}", nombre, precio);
    }
}
