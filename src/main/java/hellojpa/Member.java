package hellojpa;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Entity
//public class Member extends BaseEntity {
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Embedded
    private Period workPeriod;

    @AttributeOverrides({
            @AttributeOverride(name="city", column=@Column(name="WORK_CITY")),
            @AttributeOverride(name="street", column=@Column(name="WORK_STREET")),
            @AttributeOverride(name="zipcode", column=@Column(name="WORK_ZIPCODE"))
    })
    @Embedded
    private Address homeAddress;

    @Embedded
    private Address workAddress;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "TEAM_ID")
//    private Team team;
}
