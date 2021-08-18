package yte.intern.springweb.dto;


import lombok.*;

//json için noargsconstructor gerek
//@Getter
//@Setter
@NoArgsConstructor
//@ToString
@AllArgsConstructor
@Data //jpa entitylerinde kullanmayın asla
//Yukardakiler yerine direkt @Data yazıyoruz, birden fazla şeyi içinde tutuyor
public class Post {

    private Long userId;
    private Long id;
    private String title;
    private String body;



}
