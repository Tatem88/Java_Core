package lesson_4.repository;

import lesson_4.model.Assigment;

import java.util.ArrayList;
import java.util.List;

abstract public class AssigmentRepository {
    static private List<Assigment> assigmentList;

    static public List<Assigment> getAssigmentList() {
        return assigmentList;
    }

    static public void addAssigment(Assigment assigment) {
        if (assigmentList == null) assigmentList = new ArrayList<>();
        assigmentList.add(assigment);
    }


}
