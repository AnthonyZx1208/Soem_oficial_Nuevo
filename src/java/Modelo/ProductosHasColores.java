package Modelo;

public class ProductosHasColores {

    private int productoIdProducto;
    private int coloresIdColor;
    private int tallaIdTalla;
    private int cantidadDisponible;

    public int getProductoIdProducto() {
        return productoIdProducto;
    }

    public void setProductoIdProducto(int productoIdProducto) {
        this.productoIdProducto = productoIdProducto;
    }

    public int getColoresIdColor() {
        return coloresIdColor;
    }

    public void setColoresIdColor(int coloresIdColor) {
        this.coloresIdColor = coloresIdColor;
    }

    public int getTallaIdTalla() {
        return tallaIdTalla;
    }

    public void setTallaIdTalla(int tallaIdTalla) {
        this.tallaIdTalla = tallaIdTalla;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
}
