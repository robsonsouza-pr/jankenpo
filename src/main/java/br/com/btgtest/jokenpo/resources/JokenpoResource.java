package br.com.btgtest.jokenpo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.btgtest.jokenpo.resources.dto.MoveDTO;
import br.com.btgtest.jokenpo.resources.dto.ResultDTO;
import br.com.btgtest.jokenpo.services.JokenpoService;

@RestController
@RequestMapping(value="play")
public class JokenpoResource {
	
	@Autowired
	private JokenpoService service;
	
	@SuppressWarnings("rawtypes")
	@PostMapping
	public ResponseEntity move( @RequestBody MoveDTO move) {
		
		List<String> errors = service.validate(move);
		
		if(!errors.isEmpty()) {
			return ResponseEntity.badRequest().body(errors);
		}
		
		MoveDTO setted = service.setMove(move);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(setted);
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping
	public ResponseEntity changeMove( @RequestBody MoveDTO move) {
		
		List<String> errors = service.validateChange(move);
		
		if(!errors.isEmpty()) {
			return ResponseEntity.badRequest().body(errors);
		}
		
		MoveDTO changed = service.changeMove(move);
		
		return ResponseEntity.ok(changed); 
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping
	public ResponseEntity deleteMove( @RequestBody MoveDTO move) {
		
		List<String> errors = service.validateChange(move);
		
		if(!errors.isEmpty()) {
			return ResponseEntity.badRequest().body(errors);
		}
		
		service.deleteMove(move);
		
		return ResponseEntity.ok().build(); 
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping
	public ResponseEntity play() {
		
		
		
		if(!service.canPlay()) {
			return ResponseEntity.badRequest().body("You can't play Jakenpo alone, sorry.");
		}
		
		ResultDTO result = service.play();
		
		return ResponseEntity.ok(result);
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value="/getAll")
	public ResponseEntity<List<MoveDTO>> getAll() {
		
		
		List<MoveDTO> all = service.getAll();
		
		return ResponseEntity.ok(all);
	}

}
