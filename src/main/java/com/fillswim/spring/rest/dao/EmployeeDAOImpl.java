package com.fillswim.spring.rest.dao;

import com.fillswim.spring.rest.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Специализированная аннотация для DAO, аналогичная @Component
public class EmployeeDAOImpl implements EmployeeDAO {

    private final SessionFactory sessionFactory;

    public EmployeeDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
//    @Transactional // Отвечает за открытие и закрытие транзакции // Перемещается в Service
    public List<Employee> getAllEmployees() {

        Session session = sessionFactory.getCurrentSession();

        // Запрос всех работников в одну строку
        List<Employee> employees = session.createQuery("from Employee", Employee.class).getResultList();

        // Запрос всех работников в несколько строк
//        Query<Employee> query = session.createQuery("from Employee", Employee.class);
//        List<Employee> employees = query.getResultList();

        return employees;
    }

    // Метод или сохраняет нового работника или обновляет старого
    @Override
    public void saveEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();

        // Работник будет сохраняться в БД, если id у него 0, в ином случае он будет обновляться
        session.saveOrUpdate(employee);
    }

    @Override
    public Employee getEmployee(int id) {

        Session session = sessionFactory.getCurrentSession();

        Employee employee = session.get(Employee.class, id);

        return employee;
    }

    @Override
    public void deleteEmployee(int id) {

        Session session = sessionFactory.getCurrentSession();

        Query<Employee> query = session.createQuery("delete from Employee where id =:employeeId");
        query.setParameter("employeeId", id);

        query.executeUpdate();
    }
}
