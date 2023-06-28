package Entities;

public class Libro {


    private String ISBN;
    private String nombre;
    private String autor;
    private String categoria;
    private int cantidadPaginas;
    private int stock;

    public Libro(String ISBN, String nombre, String autor, String categoria, int cantidadPaginas, int stock) {
        this.ISBN = ISBN;
        this.nombre = nombre;
        this.autor = autor;
        this.categoria = categoria;
        this.cantidadPaginas = cantidadPaginas;
        this.stock = stock;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCantidadPaginas() {
        return cantidadPaginas;
    }

    public void setCantidadPaginas(int cantidadPaginas) {
        this.cantidadPaginas = cantidadPaginas;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
