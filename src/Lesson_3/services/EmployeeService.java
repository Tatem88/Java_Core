package Lesson_3.services;

import Lesson_3.entity.assigment.Assigment;
import Lesson_3.enums.Skill;
import Lesson_3.enums.Status;
import Lesson_3.entity.person.Employee;
import Lesson_3.repository.AssigmentRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class EmployeeService {
    public List<Assigment> getAssigmentsByEmployee(Employee employee) {
        return AssigmentRepository.getAssigmentList().stream()
                .filter(x -> x.getEmployee() == employee)
                .toList();
    }

    public boolean canStartOrFinishTaskByEmployee(Employee employee, Assigment assigment) {
        boolean result = true;
        if (!employee.equals(assigment.getEmployee())) {
            if (employee.getSkill().equals(Skill.MANAGER)
                    && assigment.getEmployee().getDepartment().getManager().equals(employee)) ;
            else
//                employee = assigment.getEmployee();
                result = false;
//            else
//                throw new RuntimeException("Only the specified employee or manager can start/finish a task");
        }
        return result;
    }

    public void startTaskByEmployee(Employee employee, Assigment assigment) {
        if (canStartOrFinishTaskByEmployee(employee, assigment)) {
            if (!employee.equals(assigment.getEmployee())) employee = assigment.getEmployee();
            Employee finalEmployee = employee;
            AssigmentRepository.getAssigmentList().stream()
                    .filter(x -> x.getEmployee().equals(finalEmployee))
                    .filter(x -> x != assigment)
                    .filter(x -> !x.getStatus().equals(Status.COMPLETE))
                    .forEach(x -> x.setStatus(Status.ON_HOLD));
            finalEmployee.setWorking(true);
            if (assigment.getFactStartDate() == null) assigment.setFactStartDate(LocalDate.now());
            assigment.setStatus(Status.IN_PROGRESS);
        }
    }

    public void finishTaskByEmployee(Employee employee, Assigment assigment) {
        if (canStartOrFinishTaskByEmployee(employee, assigment)) {
            if (!employee.equals(assigment.getEmployee())) employee = assigment.getEmployee();
            employee.setWorking(false);
            assigment.setFactEndDate(LocalDate.now());
            assigment.setStatus(Status.COMPLETE);
            startNextAssigmentByEmployee(employee);
        }
    }

    public void startNextAssigmentByEmployee(Employee employee) {
        if (!employee.isWorking()) {
            List<Assigment> assigmentList = AssigmentRepository.getAssigmentList().stream()
                    .filter(x -> x.getEmployee() == employee)
                    .filter(x -> x.getStatus().equals(Status.ON_HOLD))
                    .max(Comparator.comparingInt(x -> x.getTask().getPriority().getCode()))
                    .stream().toList();
            if (!assigmentList.isEmpty()) startTaskByEmployee(employee, assigmentList.get(0));
        }
    }
}