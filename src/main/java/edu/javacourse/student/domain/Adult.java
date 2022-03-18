package edu.javacourse.student.domain;

import javax.persistence.*;
import java.time.LocalDate;


@Embeddable
public class Adult extends Person
{
    private String passportSerial;
    private String passportNumber;
    private LocalDate passportIssueDate;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private University university;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private PassportOffice passportIssueDepartment;
    private String studentNumber;

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

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public PassportOffice getPassportIssueDepartment() {
        return passportIssueDepartment;
    }

    public void setPassportIssueDepartment(PassportOffice passportIssueDepartment) {
        this.passportIssueDepartment = passportIssueDepartment;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }
}
