package yte.intern.springapplication.student.entity;


import lombok.Getter;
import lombok.experimental.Accessors;
import yte.intern.springapplication.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Accessors(fluent = true) // fluent – A boolean. If true, the getter for pepper is just pepper()
public class Book extends BaseEntity {


    //creatindate, lastmıodified, id falan hep tekrar edilmesin diye ana bir entitye taşınır "BaseEntity"
    @Getter
    private String name;

    private LocalDate publishDate;

    private Integer pageCount;

    protected Book() {
    }

    public Book(final String name, final LocalDate publishDate, final Integer pageCount) {
        this.name = name;
        this.publishDate = publishDate;
        this.pageCount = pageCount;
    }

    public boolean hasSameNameAs(Book book) {
        return name.equals(book.name);
    }
}
