package com.loopwiki.loginregisterwithsqlite;

public class NotasVo {
    private String codigo;
    private String titulo;
    private String descripcion;
    private String fecha;

    public NotasVo() {
    }

    public NotasVo(String codigo, String titulo, String descripcion, String fecha) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public String getCodigo() { return codigo; }

    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getFecha() { return fecha; }

    public void setFecha(String fecha) { this.fecha = fecha; }
}
