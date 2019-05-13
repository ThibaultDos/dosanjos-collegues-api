package dev.colleguesapi.security;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.colleguesapi.controller.dto.Utilisateur;
import dev.colleguesapi.repos.UtilisateurRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private UtilisateurRepository utilisateurRepository;

	public UserDetailsServiceImpl(UtilisateurRepository utilisateurRepository) {
		this.utilisateurRepository = utilisateurRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Utilisateur utilisateurTrouve = this.utilisateurRepository.findByNomUtilisateur(username)
				.orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

		return new User(utilisateurTrouve.getNomUtilisateur(), utilisateurTrouve.getMotDePasse(),
				utilisateurTrouve.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
	}

}