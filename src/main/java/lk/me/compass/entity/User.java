package lk.me.compass.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "nic")
    private String nic;

    @Column(name = "status")
    private Integer status;

    @Column(name = "contact_number")
    private String contactNumber;

    @CreationTimestamp
    @Column(name = "created_date", columnDefinition = "TIMESTAMP")
    private Date createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date", columnDefinition = "TIMESTAMP")
    private Date updatedDate;

    @Column(name = "deleted_date", columnDefinition = "TIMESTAMP")
    private Date deletedDate;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role roleId;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<LogData> log_data = new ArrayList<>();

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Invoice> invoice = new ArrayList<>();

    @OneToMany(mappedBy = "id", cascade = CascadeType.REMOVE)
    private List<QuarterReport> quarterReport = new ArrayList<>();

}
