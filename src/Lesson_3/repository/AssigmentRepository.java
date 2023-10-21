package Lesson_3.repository;

import Lesson_3.entity.assigment.Assigment;

import java.util.ArrayList;
import java.util.List;

abstract public class AssigmentRepository {
    static private List<Assigment> assigmentList;

    static public List<Assigment> getAssigmentList() {
        return assigmentList;
    }

    static public List<Assigment> addAssigment(Assigment assigment) {
        if (assigmentList == null) assigmentList = new ArrayList<>();
        assigmentList.add(assigment);
        return assigmentList;
    }


}
