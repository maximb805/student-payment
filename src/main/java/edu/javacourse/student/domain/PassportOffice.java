package edu.javacourse.student.domain;

import javax.persistence.*;

@Entity
@Table(name = "jc_passport_office")
public class PassportOffice
{
    @Id
    @Column(name = "passport_office_id")
    private Long passportOfficeId;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "passport_office_area_id")
    private CountryArea passportOfficeArea;
    @Column(name = "passport_office_name")
    private String passportOfficeName;

    public Long getPassportOfficeId() {
        return passportOfficeId;
    }

    public void setPassportOfficeId(Long passportOfficeId) {
        this.passportOfficeId = passportOfficeId;
    }

    public CountryArea getPassportOfficeArea() {
        return passportOfficeArea;
    }

    public void setPassportOfficeArea(CountryArea passportOfficeArea) {
        this.passportOfficeArea = passportOfficeArea;
    }

    public String getPassportOfficeName() {
        return passportOfficeName;
    }

    public void setPassportOfficeName(String passportOfficeName) {
        this.passportOfficeName = passportOfficeName;
    }
}
