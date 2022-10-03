package hu.bme.aut.retelab2.controller;

import hu.bme.aut.retelab2.ForbiddenException;
import hu.bme.aut.retelab2.SecretGenerator;
import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
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
    @GetMapping("/{tag}")
    public List<Ad> getByTag(@PathVariable String tag){
        List<Ad> adList =  adRepository.findByTag(tag);
        adList.forEach(ad -> ad.setUpdateToken("null"));
        return adRepository.findByTag(tag);
    }

    @Transactional
    @Scheduled(fixedDelay= 6000)
    public void deleteExpiredAd() {
        List<Ad> expiredList = adRepository.deleteExpiredAd();
        expiredList.forEach(ad -> adRepository.deleteById(ad.getId()));

    }

}
