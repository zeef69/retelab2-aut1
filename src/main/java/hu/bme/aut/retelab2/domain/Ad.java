package hu.bme.aut.retelab2.domain;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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

    @ElementCollection
    private List<String> tagList = new ArrayList<>();

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

    public List<String> getTagList() {return tagList; }
    public void setTagList(List<String> tagList) { this.tagList = tagList;}
}
