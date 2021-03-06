package com.example.userapp.api;

import com.example.userapp.appuser.AppUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Avatar {
    @Id
    @SequenceGenerator(
            name = "id_sequence",
            sequenceName = "id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "id_sequence"
    )
    private long id;
    @Lob
    private byte[] file;
    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    public Avatar(byte[] file, AppUser appUser) {
        this.file = file;
        this.appUser = appUser;
    }

    public Avatar() {

    }
}
