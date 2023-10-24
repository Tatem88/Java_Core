package lesson_4.services;

import lesson_4.model.Assigment;
import lesson_4.model.Employee;
import lesson_4.model.Skill;
import lesson_4.model.Task;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    //  private String fileName;

    public void fileWriter(List<String> dats, String fileName, boolean append) {
        File file = new File(fileName);
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (String s : dats) {
                fileWriter.write(s.toString() + "\n");
                fileWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fileWriterEmployee(List<Employee> employees) {
        String fileName = "employees.txt";
        File file = new File(fileName);
        StringBuilder employee = new StringBuilder();
        List<String> employeeStringBuilder = new ArrayList<>();
        for (Employee empl : employees) {
            employee.append(empl.getLastName()).append(";")
                    .append(empl.getFirstName()).append(";")
                    .append(empl.getBirthDate()).append(";")
                    .append(empl.getSalary()).append(";")
                    .append(empl.getSkill()).append(";")
                    .append(empl.getId()).append(";");
            employeeStringBuilder.add(employee.toString());
            employee.delete(0,employee.length());

        }
        fileWriter(employeeStringBuilder, fileName, false);
    }

    public void fileWriterAssigment(List<Assigment> assigments) {
        String fileName = "assigments.txt";
        File file = new File(fileName);
        StringBuilder assigment = new StringBuilder();
        List<String> assigmentStringBuilder = new ArrayList<>();
        for (Assigment ass : assigments) {
            assigment.append(ass.getEmployee()).append(";")
                    .append(ass.getStatus()).append(";")
                    .append(ass.getTask()).append(";")
                    .append(ass.getId()).append(";");
            assigmentStringBuilder.add(assigment.toString());
        }
        fileWriter(assigmentStringBuilder, fileName, false);
    }

    public void fileWriterTask(List<Task> tasks) {
        String fileName = "tasks.txt";
        File file = new File(fileName);
        StringBuilder task = new StringBuilder();
        List<String> taskStringBuilder = new ArrayList<>();
        for (Task tas : tasks) {
            task.append(tas.getName()).append(";")
                    .append(tas.getCreateDate()).append(";")
                    .append(tas.getDueDate()).append(";")
                    .append(tas.getSkill()).append(";")
                    .append(tas.getPriority()).append(";")
                    .append(tas.getLength()).append(";")
                    .append(tas.getId()).append(";");
            taskStringBuilder.add(task.toString());
        }
        fileWriter(taskStringBuilder, fileName, false);
    }

    public List<String> fileReader(String fileName) {
        List<String> result = new ArrayList<>();
        try (FileReader fileReader = new FileReader(fileName)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.read() != -1) {
                result.add(bufferedReader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Employee> fileReaderEmployee() {
        List<Employee> employees = new ArrayList<>();
        List<String> tempEmployee = fileReader("employees.txt");
        String[] temp;
        for (String str : tempEmployee) {
            temp = str.split(";");
            String lastname = temp[0];
            String firstName = temp[1];
            String[] tempLocalDate = temp[2].split("-");
            LocalDate birthDay = LocalDate.of(Integer.parseInt(tempLocalDate[0]), Integer.parseInt(tempLocalDate[1])
                    , Integer.parseInt(tempLocalDate[2]));
            double salary = Double.parseDouble(temp[3]);
            Skill skill = Skill.ENGINEER;
            if (temp[4].equals(Skill.HR.toString()))
                skill = Skill.HR;
            if (temp[4].equals(Skill.MANAGER.toString()))
                skill = Skill.MANAGER;
            if (temp[4].equals(Skill.ENGINEER.toString()))
                skill = Skill.ENGINEER;
            if (temp[4].equals(Skill.DIRECTOR.toString()))
                skill = Skill.DIRECTOR;
            int id = Integer.parseInt(temp[5]);
            Employee employee = new Employee(lastname,firstName,birthDay,salary,skill,id);
            employees.add(employee);
        }
        return employees;
    }
}
