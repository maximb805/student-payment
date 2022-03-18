package edu.javacourse.student.domain;

import javax.persistence.*;

@Entity
@Table(name = "jc_register_office")
public class RegisterOffice
{
    @Id
    @Column(name = "register_office_id")
    private Long registerOfficeId;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "register_office_area_id")
    private CountryArea registerOfficeArea;
    @Column(name = "register_office_name")
    private String registerOfficeName;

    public Long getRegisterOfficeId() {
        return registerOfficeId;
    }

    public void setRegisterOfficeId(Long registerOfficeId) {
        this.registerOfficeId = registerOfficeId;
    }

    public CountryArea getRegisterOfficeArea() {
        return registerOfficeArea;
    }

    public void setRegisterOfficeArea(CountryArea registerOfficeArea) {
        this.registerOfficeArea = registerOfficeArea;
    }

    public String getRegisterOfficeName() {
        return registerOfficeName;
    }

    public void setRegisterOfficeName(String registerOfficeName) {
        this.registerOfficeName = registerOfficeName;
    }
}
