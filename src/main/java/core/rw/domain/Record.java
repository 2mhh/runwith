//package core.rw.domain;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//
//@Getter
//@NoArgsConstructor
//@RequiredArgsConstructor
//@Entity
//public class Record {
//
//    @Id
//    @GeneratedValue
//    @Column(name = "record_id")
//    private Long id;
//
//
//    @OneToOne(mappedBy = "record", fetch = FetchType.LAZY)
//    private Member member;
//
//
//    private float averageSpeed;
//
//    private int win;
//    private int loss;
//
//}
