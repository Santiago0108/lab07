package co.edu.escuelaing.cvds.lab7.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Id;
import jakarta.persistence.Persistence;
import jakarta.persistence.Table;
import jakarta.persistence.Query;



@Entity
@Table(name = "CONFIGURATION")
public class Configuration {
    @Id
    @Column(name = "PROPIEDAD")
    private String propiedad;
    @Column(name = "VALOR")
    private String valor;

    public Configuration() {
    }

    public Configuration(String propiedad, String valor) {
        this.propiedad = propiedad;
        this.valor = valor;
    }

    public String getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(String propiedad) {
        this.propiedad = propiedad;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((propiedad == null) ? 0 : propiedad.hashCode());
        result = prime * result + ((valor == null) ? 0 : valor.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Configuration other = (Configuration) obj;
        if (propiedad == null) {
            if (other.propiedad != null)
                return false;
        } else if (!propiedad.equals(other.propiedad))
            return false;
        if (valor == null) {
            if (other.valor != null)
                return false;
        } else if (!valor.equals(other.valor))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Configuration [propiedad = " + propiedad + ", valor = " + valor + "]";
    }

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @Column(name = "EMPLOYEE_ID")
    private String employee_ID;
    @Column(name = "FIRST_NAME")
    private String first_name;
    @Column(name = "LAST_NAME")
    private String last_name;
    @Column(name = "ROLE")
    private String role;
    @Column(name = "SALARY")
    private Double salary;
}

public static Employee getEmployeeByNameOrLastName(String nameOrLastName) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EmployeePU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("SELECT e FROM Employee e WHERE e.first_name = :nameOrLastName OR e.last_name = :nameOrLastName");
            query.setParameter("nameOrLastName", nameOrLastName);
            Employee employee = (Employee) query.getSingleResult();
            entityManager.getTransaction().commit();
            return employee;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return null;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
    
}