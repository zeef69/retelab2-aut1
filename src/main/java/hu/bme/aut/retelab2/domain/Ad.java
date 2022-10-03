package hu.bme.aut.retelab2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table
public class Ad {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private int price;
    private Instant creationTime;
    private String updateToken;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public Instant getCreationTime(){ return  creationTime; }
    public void setCreationTime(Instant time){ this.creationTime = time; }

    public String getUpdateToken(){ return updateToken; }
    public void setUpdateToken(String token){ this.updateToken = token;}
}
