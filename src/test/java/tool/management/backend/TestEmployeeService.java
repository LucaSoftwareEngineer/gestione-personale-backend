package tool.management.backend;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tool.management.backend.services.EmployeeService;

public class TestEmployeeService {

    @Autowired
    EmployeeService employeeService;

    @Test
    public void testEmployeeGetAll() {
        try {
            //employeeService.employeeGetByAll();
        } catch (Exception e) {
            System.out.println("problema sul metodo employeeGetAll");
        }
    }

}
