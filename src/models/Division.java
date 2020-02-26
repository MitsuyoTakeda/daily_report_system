package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "division")
@NamedQueries({
    @NamedQuery(
            name  = "getAllDivisions",
            query = "SELECT d FROM Division AS d ORDER BY d.code"
    ),
    @NamedQuery(
            name  = "getDivisionsCount",
            query = "SELECT COUNT(d) FROM Division AS d"
    ),
    @NamedQuery(
            name = "getDivisionName",
            query = "SELECT d.name FROM Division AS d WHERE d.code = :code"
    ),
    @NamedQuery(
            name  = "codeDuplicateCheck",
            query = "SELECT COUNT(d) FROM Division AS d WHERE d.code = :code"
    ),
    @NamedQuery(
            name  = "nameDuplicateCheck",
            query = "SELECT COUNT(d) FROM Division AS d WHERE d.name = :name")
})
public class Division {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "delete_flag", nullable = false)
    private Integer delete_flag;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(Integer delete_flag) {
        this.delete_flag = delete_flag;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}
