package com.mjc.school.repository.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "authors")
@EntityListeners(AuditingEntityListener.class)
public class Author implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "create_date", nullable = false)
    @CreatedDate
    private LocalDateTime createDate;

    @Column(name = "last_update_date", nullable = false)
    @LastModifiedDate
    private LocalDateTime lastUpdateDate;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<News> news = new ArrayList<>();

    public Author() {
    }

    public Author(String name, LocalDateTime createDate, LocalDateTime lastUpdateDate, List<News> news) {
        this.name = name;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.news = news;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id.equals(author.id) && name.equals(author.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
