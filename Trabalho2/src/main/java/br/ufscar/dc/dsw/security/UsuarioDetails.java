package br.ufscar.dc.dsw.security;

import java.util.Arrays;
import java.util.Collection;
 
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.ufscar.dc.dsw.domain.Usuario;
 
@SuppressWarnings("serial")
public class UsuarioDetails implements UserDetails {
 
    private Usuario usuario;
     
    public UsuarioDetails(Usuario usuario) {
        this.usuario = usuario;
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(usuario.getRole());
        return Arrays.asList(authority);
    }
 
    @Override
    public String getPassword() {
        return usuario.getPassword();
    }
 
    @Override
    public String getUsername() {
        return usuario.getUsername();
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    public long getId() {
        return usuario.getId();
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }

	public Usuario getUsuario() {
		return usuario;
	}

    public boolean hasRole(String role) {
        return getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals(role));
    }
    
}