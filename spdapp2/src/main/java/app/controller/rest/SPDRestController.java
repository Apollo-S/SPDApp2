package app.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import app.entity.SPD;
import app.repository.SPDRepository;

@RestController
@Transactional
public class SPDRestController {
	
	@Autowired
	private SPDRepository spdRepository;
	
	@RequestMapping(value = "/spd/", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<SPD>> getAllSpd() {
		List<SPD> spds = spdRepository.findAll();
		if(spds.size() == 0) {
			return new ResponseEntity<List<SPD>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<SPD>>(spds, HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value = "/spd/update/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<Void> updateSpd(@PathVariable("id") int id, @RequestBody SPD spd) {
		spd.setId(id);
		spd.setVersion(spdRepository.getOne(id).getVersion());
		spd = spdRepository.save(spd);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.OK);
	}

}
