package hu.bme.aut.retelab2.controller;

import hu.bme.aut.retelab2.ForbiddenException;
import hu.bme.aut.retelab2.SecretGenerator;
import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/ads")
public class AdController {

    @Autowired
    private AdRepository adRepository;

    @PostMapping
    public Ad create(@RequestBody Ad ad){
        ad.setId(null);
        ad.setCreationTime(Instant.now());
        return adRepository.save(ad);
    }

    @GetMapping
    public List<Ad> getByPrice(@RequestParam(required = false, defaultValue = "0") int min,
                               @RequestParam(required = false, defaultValue = "10000000") int max){
        List<Ad> adList =  adRepository.findAdBetweenValues(min, max);
        adList.forEach(ad -> ad.setUpdateToken("null"));
        return adList;
    }

    @PutMapping
    public Ad update(@RequestBody Ad updated) throws ForbiddenException {
        return adRepository.update(updated);
    }


}
