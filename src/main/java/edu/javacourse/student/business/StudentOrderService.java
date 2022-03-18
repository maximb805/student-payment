package edu.javacourse.student.business;

import edu.javacourse.student.dao.StreetRepository;
import edu.javacourse.student.dao.StudentOrderRepository;
import edu.javacourse.student.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentOrderService
{
    @Autowired
    private StudentOrderRepository dao;

    @Autowired
    private StreetRepository sr;


    private static final Logger LOGGER = LoggerFactory.getLogger(StudentOrderService.class);

    @Transactional
    public void testSave() {
        StudentOrder so = new StudentOrder();
        so.setHusband(buildPerson(false));
        so.setWife(buildPerson(true));
        dao.save(so);
    }

    @Transactional
    public void testGet() {
        List<StudentOrder> sos = dao.findAll();
        LOGGER.info(sos.get(0).getWife().getGivenName());
        LOGGER.info(sos.get(0).getHusband().getAddress().getApartment());
        LOGGER.info(sos.get(0).getWife().getAddress().getPostCode());
        LOGGER.info(sos.get(0).getWife().getAddress().getStreet().getStreetName());
        LOGGER.info(sos.get(0).getHusband().getAddress().getStreet().getStreetName());
        LOGGER.info(sos.get(0).getHusband().getPassportNumber());
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
            Street one = sr.getById(1L);
            a.setStreet(one);
            p.setAddress(a);
            p.setPassportNumber("wife_num");
            p.setPassportSerial("wife_seria");
            p.setPassportIssueDate(LocalDate.of(2012, 2, 2));
        } else {
            p.setSurName("Petrov");
            p.setGivenName("Andrey");
            p.setPatronymic("Vladimirovich");

            a.setPostCode("654321");
            a.setBuilding("321");
            a.setExtension("1");
            a.setApartment("12");
            Street one = sr.getById(2L);
            a.setStreet(one);
            p.setAddress(a);
            p.setPassportNumber("husb_num");
            p.setPassportSerial("husb_seria");
            p.setPassportIssueDate(LocalDate.of(2012, 3, 3));
        }
        return p;
    }


}
