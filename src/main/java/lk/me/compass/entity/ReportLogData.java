package lk.me.compass.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

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
@Entity(name="report_log_data")
public class ReportLogData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "IP_address")
    private String ipAddress;

    @Column(name = "status")
    private Integer status;
    
    @CreationTimestamp
    @Column(name = "download_date", columnDefinition = "TIMESTAMP")
    private Date downloadDate;

    @OneToMany(mappedBy = "id", cascade = CascadeType.REMOVE)
    private List<QuarterReport> quarterReport = new ArrayList<>();

}
