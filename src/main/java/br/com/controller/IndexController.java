package br.com.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.model.Usuario;
import br.com.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/usuario")
public class IndexController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Usuario> init(@PathVariable(value = "id") Long id) {

		Optional<Usuario> usuario = usuarioRepository.findById(id);

		return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
	}

	@GetMapping(value = "/", produces = "application/json") 
	public ResponseEntity<List<Usuario>> usuario() {

		List<Usuario> list = (List<Usuario>) usuarioRepository.findAll();

		return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
	}

	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {

		for (int i = 0; i < usuario.getTelefones().size(); i++) {
			usuario.getTelefones().get(i).setUsuario(usuario);

		}

		Usuario usuariosalvo = usuarioRepository.save(usuario);

		return new ResponseEntity<Usuario>(usuariosalvo, HttpStatus.OK);

	}

	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario) {

		for (int i = 0; i < usuario.getTelefones().size(); i++) {
			usuario.getTelefones().get(i).setUsuario(usuario);

		}

		Usuario usuariosalvo = usuarioRepository.save(usuario);

		return new ResponseEntity<Usuario>(usuariosalvo, HttpStatus.OK);

	}

	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String delete(@PathVariable("id") Long id) {

		usuarioRepository.deleteById(id);

		return "ok";

	}

}
