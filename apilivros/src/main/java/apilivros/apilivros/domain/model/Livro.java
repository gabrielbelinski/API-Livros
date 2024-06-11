package apilivros.apilivros.domain.model;

public class Livro {
    private String Autor;
    private String Titulo;
    private String Editora; 
    private String  Cidade;
    private int  Ano;
    public String getAutor() {
        return Autor;
    }
    public void setAutor(String autor) {
        Autor = autor;
    }
    public String getTitulo() {
        return Titulo;
    }
    public void setTitulo(String titulo) {
        Titulo = titulo;
    }
    public String getEditora() {
        return Editora;
    }
    public void setEditora(String editora) {
        Editora = editora;
    }
    public String getCidade() {
        return Cidade;
    }
    public void setCidade(String cidade) {
        Cidade = cidade;
    }
    public int getAno() {
        return Ano;
    }
    public void setAno(int ano) {
        Ano = ano;
    }


}
