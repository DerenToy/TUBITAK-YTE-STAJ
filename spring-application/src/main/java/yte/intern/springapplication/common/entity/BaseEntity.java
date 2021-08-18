package yte.intern.springapplication.common.entity;


import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@EntityListeners(AuditingEntityListener.class) // version, created, lastmodified çalışmasını sağlar
@MappedSuperclass // diğer alt entitylerin bundan extend etmesini sağlicaz
@Accessors(fluent = true)
@Getter
public abstract  class BaseEntity { //bu abstract olacak çünkü böyle bir tablo yok


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version // arttırma işini vs bu hallediyor
    private Long version; // entityler her güncellendiğinde (ilişki değişme alan değişme) version alanı 1 arttırılır locking
    //için önemli .. back ende aynı anda gelme işi race condition olayı--iki clientın aynı anda bişey değiştirmeye çalışması

    // okuduğunda version 1, yazarken 2 yse işlemi iptal eder. -> soft locking

    @CreatedDate
    private LocalDateTime creationDate; //ne zaman oluşturuldu -- bir problem çıktığında kullanılan şeyler bunlar debugging
    @LastModifiedDate
    private LocalDateTime lastModifiedDate; // en son ne zaman modified edildi,, asla kendi business logicimiz tarafından
    // kullanılmamalı.. eğer etkinlik başlangıç tarihi falansa bunlar kullanılmamallı asla.. versionı da ellemeyin.
    // child entitylerinde bunlar yokmuş gibi varsay !! görevleri farklı bunların ! db de hata çözmek için



    //equal ve hashcodeların yazılması istenir.. bunun için de id gerekli, o yüzden burda override ederiz
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
