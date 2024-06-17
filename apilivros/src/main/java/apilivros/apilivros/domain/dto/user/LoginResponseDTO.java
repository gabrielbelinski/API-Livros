package apilivros.apilivros.domain.dto.user;

public class LoginResponseDTO {
    private String token;
    private UserResponseDTO usuario;
    
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public UserResponseDTO getUsuario() {
        return usuario;
    }
    public void setUsuario(UserResponseDTO usuario) {
        this.usuario = usuario;
    }
}
