package edu.javacourse.student.domain;

import javax.persistence.*;
import java.time.LocalDate;


@Embeddable
public class Adult extends Person
{
    private String passportSerial;
    private String passportNumber;
    private LocalDate passportIssueDate;
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private University university;
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private PassportOffice passportIssueDepartment;
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private RegisterOffice marriageCertificateIssueDepartment;

    public String getPassportSerial() {
        return passportSerial;
    }

    public void setPassportSerial(String passportSerial) {
        this.passportSerial = passportSerial;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public LocalDate getPassportIssueDate() {
        return passportIssueDate;
    }

    public void setPassportIssueDate(LocalDate passportIssueDate) {
        this.passportIssueDate = passportIssueDate;
    }
}
