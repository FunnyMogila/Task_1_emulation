import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DatabaseEmulator {
    private Map<String, Department> departments;

    public DatabaseEmulator() {
        this.departments = new HashMap<>();
    }

    public void addDepartment(String name) {
        Department newDepartment = new Department(name);
        departments.put(name, newDepartment);
        System.out.println("Отдел добавлен: " + name);
    }

    public void removeDepartment(String name) {
        if (departments.containsKey(name)) {
            departments.remove(name);
            System.out.println("Отдел удален: " + name);
        } else {
            System.out.println("Отдел с именем " + name + " не существует.");
        }
    }

    public void showAllDepartments() {
        System.out.println("Список всех отделов:");
        for (Department department : departments.values()) {
            System.out.println(department);
        }
    }

    public void addEmployee(String deptName, String fullName, int age, double salary) {
        if (departments.containsKey(deptName)) {
            Employee newEmployee = new Employee(fullName, age, salary);
            departments.get(deptName).addEmployee(newEmployee);
            System.out.println("Сотрудник добавлен в отдел " + deptName);
        } else {
            System.out.println("Отдел с именем " + deptName + " не существует.");
        }
    }

    public void removeEmployee(String deptName, String employeeFullName) {
        if (departments.containsKey(deptName)) {
            Department department = departments.get(deptName);
            List<Employee> employees = department.getEmployees();

            for (Employee employee : employees) {
                if (employee.getFullName().equals(employeeFullName)) {
                    department.removeEmployee(employee);
                    System.out.println("Сотрудник " + employeeFullName + " удален из отдела " + deptName);
                    return;
                }
            }

            System.out.println("Сотрудник " + employeeFullName + " не найден в отделе " + deptName);
        } else {
            System.out.println("Отдел с именем " + deptName + " не существует.");
        }
    }

    public void showAllEmployeesInDepartment(String deptName) {
        if (departments.containsKey(deptName)) {
            Department department = departments.get(deptName);
            List<Employee> employees = department.getEmployees();

            System.out.println("Список сотрудников в отделе " + deptName + ":");
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        } else {
            System.out.println("Отдел с именем " + deptName + " не существует.");
        }
    }

    public void showDepartmentSalary(String deptName) {
        if (departments.containsKey(deptName)) {
            Department department = departments.get(deptName);
            double departmentSalary = department.getDepartmentSalary();
            System.out.println("Сумма зарплат в отделе " + deptName + ": " + departmentSalary);
        } else {
            System.out.println("Отдел с именем " + deptName + " не существует.");
        }
    }

    public static void main(String[] args) {
        DatabaseEmulator database = new DatabaseEmulator();
        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.println("1. Добавить отдел");
            System.out.println("2. Удалить отдел");
            System.out.println("3. Показать все отделы");
            System.out.println("4. Добавить сотрудника");
            System.out.println("5. Удалить сотрудника");
            System.out.println("6. Показать всех сотрудников в отделе");
            System.out.println("7. Показать сумму зарплат в отделе");
            System.out.println("8. Выход");
            System.out.println("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Введите название отдела:");
                    String departmentName = scanner.nextLine();
                    database.addDepartment(departmentName);
                    break;

                case 2:
                    System.out.println("Введите название отдела для удаления:");
                    String departmentToDelete = scanner.nextLine();
                    database.removeDepartment(departmentToDelete);
                    break;

                case 3:
                    database.showAllDepartments();
                    break;

                case 4:
                    System.out.println("Введите название отдела:");
                    String deptName = scanner.nextLine();
                    System.out.println("Введите ФИО сотрудника:");
                    String fullName = scanner.nextLine();
                    System.out.println("Введите возраст сотрудника:");
                    int age = scanner.nextInt();
                    System.out.println("Введите ЗП сотрудника:");
                    double salary = scanner.nextDouble();
                    database.addEmployee(deptName, fullName, age, salary);
                    break;

                case 5:
                    System.out.println("Введите название отдела:");
                    String deptToRemoveEmployee = scanner.nextLine();
                    System.out.println("Введите ФИО сотрудника для удаления:");
                    String employeeToRemove = scanner.nextLine();
                    database.removeEmployee(deptToRemoveEmployee, employeeToRemove);
                    break;

                case 6:
                    System.out.println("Введите название отдела:");
                    String deptToShowEmployees = scanner.nextLine();
                    database.showAllEmployeesInDepartment(deptToShowEmployees);
                    break;

                case 7:
                    System.out.println("Введите название отдела:");
                    String deptToShowSalary = scanner.nextLine();
                    database.showDepartmentSalary(deptToShowSalary);
                    break;

                case 8:
                    System.out.println("Программа завершена.");
                    System.exit(0);

                default:
                    System.out.println("Некорректный ввод. Пожалуйста, выберите существующее действие.");
            }
        }
    }

}