package com.quickboard.resourceprofile.subscription.entity;

import com.quickboard.resourceprofile.common.entity.BaseEntity;
import com.quickboard.resourceprofile.profile.entity.Profile;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subscriptions",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"profile_id", "board_id"})
        }
)
@NoArgsConstructor
@Getter
public class Subscription extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @Column(name = "board_id", nullable = false)
    private Long boardId;

    @Builder
    public Subscription(Profile profile, Long boardId) {
        this.profile = profile;
        this.boardId = boardId;
    }
}
