package uz.seteam.sqlite.repostroy;

import org.hibernate.Session;
import uz.seteam.sqlite.entity.Company;
import uz.seteam.sqlite.utils.HibernateUtils;

import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {


    public List<Company> getAllCompany(){
        Session session = null;
        List<Company> companies = new ArrayList<Company>();
        try{
            session = HibernateUtils.openSession();
            companies = session.createQuery("select c from Company c").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        return companies;
    }

    public void createCompany(Company company){
        Session session =null;
        try{
            session = HibernateUtils.openSession();
            session.getTransaction().begin();
            session.save(company);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(session !=null && session.isOpen()){
                session.close();
            }
        }

    }

    public Company getCompanyById(Long id){
        Session session = null;
        Company companyById = null;
        try{
            session = HibernateUtils.openSession();
            try{
                companyById = session.find(Company.class, id);
                companyById.getDragList().forEach(System.out::println);
            } catch (NullPointerException e) {
                System.out.println("No company with id" + id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        return companyById;
    }

    public void updateCompany(Company company){
        Session session = null;
        try{
            session = HibernateUtils.openSession();
            session.getTransaction().begin();
            session.merge(company);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public void deleteCompany(Long id){
        Session session = null;
        try{
            session = HibernateUtils.openSession();
            Company companyToDelete = session.find(Company.class , id);
            session.getTransaction().begin();
            session.remove(companyToDelete);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }


}
