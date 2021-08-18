package yte.intern.springdata.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import yte.intern.springdata.entities.Book;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Long> {
    //sorgudan bir şey gelmeme ihtimali varsa Optional Book dönmek daha mantıklı
    Optional<Book> findByTitle(String title);
    List<Book> findByAgeGreaterThanEqual(Long age, Sort sort); //yaşı 15e eşit ve büyük, sıralı
    List<Book> findByPublishDateAfter(LocalDate publishDate, Pageable pageRequest); //kendi projemde bende event listelerken bu tarz yapmalıyım
    List<Book> findByTitleContains(String title); //içinde geçen sözcük bulma
    List<Book> findByAuthorAndAgeGreaterThan(String author, Long age); //author ismi ve yaşa göre bulma
    long countByAuthor(String author); // author adına göre kitap sayısı
    boolean existsByAuthor(String author); //author var mı
    //exists olayı query ile çok zor countına bakıp 0 dan büyükse falan diye düşünülebilir

//query de isim önemli değil
    @Query("select b from Book b where b.title = :title")
    List<Book> findByTitleQuery(@Param("title") String title); //title == title olduğu için parama gerek yok aslında

    @Query("select b from Book b where b.age >= :age") //order by d yapılır ama hard codedan daha güzel bu
    List<Book> findByAgeGreaterThanEqualQuery(Long age, Sort sort);

    @Query("select b from Book b where b.publishDate > :publishDate")
    List<Book> findByPublishDateAfterQuery(LocalDate publishDate, Pageable page);

    @Query("select b from Book b where b.title like %:word%")
    List<Book> findByTitleContainsQuery(String word);

    @Query("select b from Book b where b.author = :author and b.age > :age")
    List<Book> findByAuthorAndAgeGreaterThanQuery(String author, Long age);

    @Query("select count(b) from Book b where b.author = :author")
    long countByAuthorQuery(String author);


}
