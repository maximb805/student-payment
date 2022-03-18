package edu.javacourse.student.business;

import edu.javacourse.student.dao.*;
import edu.javacourse.student.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentOrderService {
    @Autowired
    private StudentOrderRepository studentOrderRepository;

    @Autowired
    private StreetRepository streetRepository;

    @Autowired
    private PassportOfficeRepository passportOfficeRepository;

    @Autowired
    private RegisterOfficeRepository registerOfficeRepository;

    @Autowired
    private StudentOrderStatusRepository studentOrderStatusRepository;

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private StudentOrderChildRepository studentOrderChildRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentOrderService.class);

    @Transactional
    public void testSave() {
        StudentOrder so = new StudentOrder();
        so.setHusband(buildPerson(false));
        so.setWife(buildPerson(true));
        so.setStudentOrderDate(LocalDateTime.now());
        so.setStudentOrderStatusId(studentOrderStatusRepository.getById(1L));
        so.setRegisterOffice(registerOfficeRepository.getById(2L));
        so.setMarriageDate(LocalDate.of(2015, 2, 2));
        so.setMarriageCertificateNumber("987125254");
        studentOrderRepository.save(so);

        StudentOrderChild soc = buildChild(so);
        studentOrderChildRepository.save(soc);
    }

    @Transactional
    public void testGet() {
        List<StudentOrder> sos = studentOrderRepository.findAll();
        LOGGER.info("Имя жены: " + sos.get(0).getWife().getGivenName());
        LOGGER.info("Квартира мужа: " + sos.get(0).getHusband().getAddress().getApartment());
        LOGGER.info("Почтовый индекс жены: " + sos.get(0).getWife().getAddress().getPostCode());
        LOGGER.info("Улица жены: " + sos.get(0).getWife().getAddress().getStreet().getStreetName());
        LOGGER.info("Улица мужа: " + sos.get(0).getHusband().getAddress().getStreet().getStreetName());
        LOGGER.info("Номер паспорта мужа: " + sos.get(0).getHusband().getPassportNumber());
        LOGGER.info("Номер паспорта жены: " + sos.get(0).getWife().getPassportNumber());
        LOGGER.info("Университет мужа: " + sos.get(0).getHusband().getUniversity().getUniversityName());
        LOGGER.info("Паспортный стол жены: " + sos.get(0).getWife().getPassportIssueDepartment().getPassportOfficeName());
        LOGGER.info("ЗАГС: " + sos.get(0).getRegisterOffice().getRegisterOfficeName());
        LOGGER.info("Студенческий номер жены: " + sos.get(0).getWife().getStudentNumber());
        LOGGER.info("Имя ребенка: " + sos.get(0).getStudentOrderChild().get(0).getStudentChild().getGivenName());
        LOGGER.info("Сертификат ребенка: " + sos.get(0).getStudentOrderChild().get(0).getStudentChild().getBirthCertificateNumber());
    }

    public Adult buildPerson(boolean wife) {
        Adult p = new Adult();
        Address a = new Address();
        p.setDateOfBirth(LocalDate.now());
        if (wife) {
            p.setSurName("Petrova");
            p.setGivenName("Irina");
            p.setPatronymic("Pavlovna");

            a.setPostCode("123456");
            a.setBuilding("123");
            a.setExtension("2");
            a.setApartment("21");
            Street one = streetRepository.getById(1L);
            a.setStreet(one);
            p.setAddress(a);
            p.setPassportNumber("wife_num");
            p.setPassportSerial("wife_seria");
            p.setPassportIssueDate(LocalDate.of(2012, 2, 2));
            p.setPassportIssueDepartment(passportOfficeRepository.getById(2L));

            p.setUniversity(universityRepository.getById(2L));
            p.setStudentNumber("12345678");
        } else {
            p.setSurName("Petrov");
            p.setGivenName("Andrey");
            p.setPatronymic("Vladimirovich");

            a.setPostCode("654321");
            a.setBuilding("321");
            a.setExtension("1");
            a.setApartment("12");
            Street one = streetRepository.getById(2L);
            a.setStreet(one);
            p.setAddress(a);
            p.setPassportNumber("husb_num");
            p.setPassportSerial("husb_seria");
            p.setPassportIssueDate(LocalDate.of(2012, 3, 3));
            p.setPassportIssueDepartment(passportOfficeRepository.getById(3L));

            p.setUniversity(universityRepository.getById(1L));
            p.setStudentNumber("87654321");
        }
        return p;
    }

    public StudentOrderChild buildChild(StudentOrder so) {
        StudentOrderChild soc = new StudentOrderChild();
        Child p = new Child();
        Address a = new Address();
        soc.setStudentOrder(so);
        p.setSurName("Petrov");
        p.setGivenName("Vasya");
        p.setPatronymic("Andreevich");
        p.setDateOfBirth(LocalDate.now());

        a.setPostCode("123456");
        a.setBuilding("123");
        a.setExtension("2");
        a.setApartment("21");
        Street one = streetRepository.getById(1L);
        a.setStreet(one);
        p.setAddress(a);
        p.setRegisterOffice(registerOfficeRepository.getById(2L));
        p.setBirthCertificateDate(LocalDate.of(2018, 3, 3));
        p.setBirthCertificateNumber("birth25412");

        soc.setStudentChild(p);
        return soc;
    }
}
