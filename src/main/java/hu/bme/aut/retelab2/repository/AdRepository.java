package hu.bme.aut.retelab2.repository;

import hu.bme.aut.retelab2.ForbiddenException;
import hu.bme.aut.retelab2.SecretGenerator;
import hu.bme.aut.retelab2.domain.Ad;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AdRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Ad save(Ad ad) {
        ad.setUpdateToken(SecretGenerator.generate());
        return em.merge(ad);
    }

    public List<Ad> findAdBetweenValues(int min, int max){
        return em.createQuery(   "SELECT a FROM Ad a " +
                                    "WHERE a.price BETWEEN ?1 AND ?2", Ad.class)
                .setParameter(1,min)
                .setParameter(2,max)
                .getResultList();
    }

    @Transactional
    public Ad update(Ad newAd) throws ForbiddenException{
        Ad updatedAd = em.find(Ad.class, newAd.getId());
        if(updatedAd.getUpdateToken().equals(newAd.getUpdateToken())){
            return save(newAd);
        }
        throw new ForbiddenException();
    }
}
