package lk.me.compass.entity;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="quarter_data")
public class QuarterData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "artist_name")
    private String artistName;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "platform")
    private String platform;

    @Column(name = "revenue")
    private Double revenue;

    @Column(name = "status")
    private Integer status;

    @OneToMany(mappedBy = "id", cascade = CascadeType.REMOVE)
    private List<QuarterReport> quarterReport = new ArrayList<>();


}
