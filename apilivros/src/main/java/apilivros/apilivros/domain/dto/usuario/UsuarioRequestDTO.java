package apilivros.apilivros.domain.dto.usuario;

import java.util.List;

import apilivros.apilivros.domain.model.Livro;

public class UsuarioRequestDTO {
    private String nome;
    private String email;
    private String senha;
    private List<Livro> livros;
    
    public List<Livro> getLivros() {
        return livros;
    }
    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }


}
