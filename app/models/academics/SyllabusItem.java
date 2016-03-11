package models.academics;

import com.avaje.ebean.Model;
import models.IUniqueID;

import javax.persistence.*;
import java.util.List;

@Entity
public class SyllabusItem extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public SyllabusItem parent;

    @OneToMany(mappedBy = "parent")
    public List<SyllabusItem> children;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public OfferedCourse offeredCourse;

    public String title;

    public Integer itemOrder;

    public static Finder<Long, SyllabusItem> find = new Finder<>(SyllabusItem.class);

}
