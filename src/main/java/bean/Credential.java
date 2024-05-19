package bean;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.validation.constraints.NotBlank;
import org.primefaces.context.PrimeFacesContext;
import service.AuthenticationService;

import java.io.Serializable;

@Named
@SessionScoped
public class Credential implements Serializable {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    AuthenticationService authService = new AuthenticationService();

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public @NotBlank String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank String username) {
        this.username = username;
    }

    public String login() {
        PrimeFacesContext facesContext;
        try {
            return authService.login(getUsername(), getPassword());
        } catch (RuntimeException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed.","Error: check username and password to find issues"));
            return "404"; // Redirect to error page
        }
    }
}