package com.quickboard.resourceprofile.profile.entity;

import com.quickboard.resourceprofile.common.entity.BaseEntity;
import com.quickboard.resourceprofile.profile.enums.Gender;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDate;

@Entity
@Table(name = "profiles")
@DynamicInsert
@NoArgsConstructor
@Getter
public class Profile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Setter
    private String nickname;

    @Column(name = "first_name")
    @Setter
    private String firstName;

    @Column(name = "last_name")
    @Setter
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Setter
    private Gender gender;

    @Setter
    private LocalDate birthdate;

    @Column(name = "account_id", unique = true)
    private Long accountId;

    @Builder
    public Profile(String nickname, String firstName, String lastName, Gender gender, LocalDate birthdate, Long accountId) {
        this.nickname = nickname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthdate = birthdate;
        this.accountId = accountId;
    }
}
