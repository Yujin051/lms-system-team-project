package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@NoArgsConstructor
@AllArgsConstructor
@Table(name="lect_plan")
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class LectPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="plan_id", updatable = false)
    private long planId;

    @OneToOne
    @JoinColumn(name="lect_id")
    private LectInfo lect;

    @Column(name="plan_des")
    private String planDes;

    @Column(name="plan_book")
    private String planBook;

    @Column(name="check_score")
    private long checkScore;

    @Column(name="assign_score")
    private long assignScore;

    @Column(name="test_score")
    private long testScore;

    @Builder
    public LectPlan(LectInfo lect, String planDes, String planBook, long checkScore, long assignScore, long testScore) {
        this.lect = lect;
        this.planDes = planDes;
        this.planBook = planBook;
        this. checkScore = checkScore;
        this.assignScore = assignScore;
        this.testScore = testScore;
    }
}

