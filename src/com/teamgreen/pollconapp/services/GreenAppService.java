package com.teamgreen.pollconapp.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamgreen.pollconapp.entities.EmissionTestCenter;
import com.teamgreen.pollconapp.entities.Incident;
import com.teamgreen.pollconapp.entities.Owner;
import com.teamgreen.pollconapp.entities.RTO;
import com.teamgreen.pollconapp.entities.Registration;
import com.teamgreen.pollconapp.entities.Test;
import com.teamgreen.pollconapp.entities.Vehicle;

@Service("GreenAppService")
public class GreenAppService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		//System.out.println("Setting sesssion factory");
		this.sessionFactory = sessionFactory;
	}

	public List<String> getAllUsers() {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			Query query = session.createQuery("from User");

			@SuppressWarnings({ "unchecked" })
			List<String> list = query.list();

			tx.commit();
			if (!list.isEmpty()) {
				return list;
			}
		} catch (Exception ve) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
		return null;
	}

	public boolean authenticateUser(String role, String userId, String password) {
		//System.out.println("In authenticateUser of service");

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			Query query = session
					.createQuery("from User where userId = :userid and password = :passwd and role = :role");
			query.setParameter("userid", userId);
			query.setParameter("passwd", password);
			query.setParameter("role", role);

			@SuppressWarnings("unchecked")
			List<String> list = query.list();
			//System.out.println("lsit=" + list);
			tx.commit();

			if (!list.isEmpty()) {
				//System.out.println("User found.");

				return true;
			} else {
				return false;
			}
		} catch (Exception ve) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}

		return false;
	}

	@Transactional(readOnly = false)
	public void createRegistration(Vehicle vehicle) {
		getSessionFactory().getCurrentSession().save(vehicle);
	}

	@Transactional(readOnly = true)
	public List<Vehicle> getAllVehicles() {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		List<Vehicle> vehicleList = new ArrayList<>();
		try {
			tx = session.beginTransaction();

			Query query = session.createQuery("from Vehicle");

			@SuppressWarnings({ "unchecked" })
			List<Vehicle> list = query.list();
			//System.out.println("list === " + list);

			for (Vehicle v : list) {
				vehicleList.add(v);
			}

			tx.commit();
			if (!vehicleList.isEmpty()) {
				return vehicleList;
			}
		} catch (Exception ve) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
		return Collections.emptyList();

	}

	
	@Transactional(readOnly = true)
	public List<EmissionTestCenter> getAllTesters() {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		List<EmissionTestCenter> testerList = new ArrayList<>();
		try {
			tx = session.beginTransaction();

			Query query = session.createQuery("from EmissionTestCenter");

			@SuppressWarnings({ "unchecked" })
			List<EmissionTestCenter> list = query.list();
			//System.out.println("list === " + list);

			for (EmissionTestCenter v : list) {
				testerList.add(v);
			}

			tx.commit();
			if (!testerList.isEmpty()) {
				return testerList;
			}
		} catch (Exception ve) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
		return Collections.emptyList();

	}

	@Transactional(readOnly = false)
	public void createRegistration(EmissionTestCenter tester) {
		getSessionFactory().getCurrentSession().save(tester);
	}

	
	@Transactional(readOnly = true)
	public List<Owner> getAllOwners() {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		List<Owner> ownerList = new ArrayList<>();
		try {
			tx = session.beginTransaction();

			Query query = session.createQuery("from Owner");

			@SuppressWarnings({ "unchecked" })
			List<Owner> list = query.list();
			//System.out.println("list === " + list);

			for (Owner v : list) {
				ownerList.add(v);
			}

			tx.commit();
			if (!ownerList.isEmpty()) {
				return ownerList;
			}
		} catch (Exception ve) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
		return Collections.emptyList();

	}

	@Transactional(readOnly = false)
	public void createRegistration(Owner owner) {
		getSessionFactory().getCurrentSession().save(owner);
	}
	
	
	

	@Transactional(readOnly = true)
	public List<Registration> getAllRegistrations() {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		List<Registration> regList = new ArrayList<>();
		try {
			tx = session.beginTransaction();

			Query query = session.createQuery("from Registration");

			@SuppressWarnings({ "unchecked" })
			List<Registration> list = query.list();
			//System.out.println("list === " + list);

			for (Registration v : list) {
				regList.add(v);
			}

			tx.commit();
			if (!regList.isEmpty()) {
				return regList;
			}
		} catch (Exception ve) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
		return Collections.emptyList();

	}

	
	

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false)
	public Owner getOwnerByAadhar(String aadhar) {
		//System.out.println("GET OWNER BY AADHAR");
		List<Owner> list = getSessionFactory().openSession().createQuery("from Owner  where aadharNumber=?").setParameter(0, aadhar)
				.list();

		//System.out.println("lsit =====> "+list);
		
		if (list != null && !list.isEmpty()) {

			Owner existingOwner = (Owner) list.get(0);
			//System.out.println("Owner is: "+existingOwner);
			
			Owner ownerDB = (Owner) getSessionFactory().getCurrentSession().get(Owner.class, existingOwner.getId());
			
			//System.out.println("Owner is: "+ownerDB.getAadharNumber());
			
			return ownerDB;

		} 

		return null;
	}

	
	//NEW OWNER and NEW VEHICLE... to be registered.
	@Transactional(readOnly = false)
	public String createRegistration(Owner owner, String selectedVehicleNumber, Registration registration) {
		int ownerId = addOwner(owner);
		//System.out.println("just added id : " + ownerId); // this is owner id.
		
		Vehicle existingVehicle = getVehicleByVIN(selectedVehicleNumber);
		//System.out.println("existing existingVehicle ===>> "+existingVehicle);
		
		
		Owner ownerDB = (Owner) getSessionFactory().getCurrentSession().get(Owner.class, ownerId);
		registration.setOwner(ownerDB);
		registration.setVehicle(existingVehicle);
		
		
		String regNumber = RandomStringUtils.randomAlphanumeric(6);

		registration.setRegNumber("KA05 " + regNumber); // maybe generated randomly for POC
		String newRegNum = registration.getRegNumber();
		updateRegistration(registration);
		return newRegNum;
	}

	
	//EXISTING OWNER AND NEW VEHICLE REGISTRATION... to be registered
	@Transactional(readOnly = false)
	public String createRegistration(String selectedAadharNumber, String selectedVehicleNumber,Registration registration) {
		
		
		//Already Owner is present in the database.
		//System.out.println("owner already present in the DB");
		
		Owner existingOwner = getOwnerByAadhar(selectedAadharNumber);
		//System.out.println("existing Owner ===>> "+existingOwner);
		
		Vehicle existingVehicle = getVehicleByVIN(selectedVehicleNumber);
		//System.out.println("existing existingVehicle ===>> "+existingVehicle);
		
		registration.setOwner(existingOwner);
		registration.setVehicle(existingVehicle);
		
		String regNumber = RandomStringUtils.randomAlphanumeric(6);

		registration.setRegNumber("KA05 " + regNumber); // maybe generated randomly for POC
		
		updateRegistration(registration);
		
		return null;
	}

	@Transactional(readOnly = false)
	private void updateRegistration(Registration reg) {
		//System.out.println("In update registration");
		getSessionFactory().getCurrentSession().saveOrUpdate(reg);
		
	}


	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false)
	private Vehicle getVehicleByVIN(String selectedVehicleNumber) {
		List<Vehicle> list = getSessionFactory().openSession()
				.createQuery("from Vehicle  where vehicleNumber=?").setParameter(0, selectedVehicleNumber)
				.list();

		if (list != null && !list.isEmpty()) {

			return (Vehicle) list.get(0);

		} 

		return null;
	}
	
	
	@Transactional(readOnly = false)
	public int addOwner(Owner owner) {
		return (int) getSessionFactory().getCurrentSession().save(owner);

	}

	
	
	@Transactional(readOnly = true)
	public List<RTO> getAllRTOs() {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		List<RTO> regList = new ArrayList<>();
		try {
			tx = session.beginTransaction();

			Query query = session.createQuery("from RTO");

			@SuppressWarnings({ "unchecked" })
			List<RTO> list = query.list();
			//System.out.println("list === " + list);

			for (RTO v : list) {
				regList.add(v);
			}

			tx.commit();
			if (!regList.isEmpty()) {
				return regList;
			}
		} catch (Exception ve) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
		return Collections.emptyList();

	}

	@Transactional(readOnly = false)
	public void createRegistration(RTO rto) {
		getSessionFactory().getCurrentSession().save(rto);
	}


	@Transactional(readOnly = true)
	public List<Incident> getAllIncidents() {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		List<Incident> regList = new ArrayList<>();
		try {
			tx = session.beginTransaction();

			Query query = session.createQuery("from Incident");

			@SuppressWarnings({ "unchecked" })
			List<Incident> list = query.list();

			for (Incident v : list) {
				regList.add(v);
			}

			tx.commit();
			if (!regList.isEmpty()) {
				return regList;
			}
		} catch (Exception ve) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
		return Collections.emptyList();

	}
	
	
	
	@Transactional(readOnly = false)
	public void createIncident(String selectedRegNumber, String reason) {
		Incident inc = new Incident();
		inc.setRegNumber(selectedRegNumber);
		inc.setReason(reason);
		getSessionFactory().getCurrentSession().save(inc);
	}

	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false)
	public List<Incident> generateReceipt(String selectedRegNumber) {
		List<Incident> list = getSessionFactory().openSession().createQuery("from Incident where regNumber=?").setParameter(0, selectedRegNumber).list();

		if (list != null && !list.isEmpty()) {

			return list;

		} 

		return null;
		
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false)
	public boolean isEmissionTestPassed(String selectedRegNumber) {
		List<Incident> list = getSessionFactory().openSession().createQuery("from Incident where regNumber=?").setParameter(0, selectedRegNumber).list();

		if (list != null && !list.isEmpty()) {

			return false;

		} 

		return true;
	}

		
	@Transactional(readOnly = true)
	public List<Test> getAllTests() {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		List<Test> regList = new ArrayList<>();
		try {
			tx = session.beginTransaction();

			Query query = session.createQuery("from Test");

			@SuppressWarnings({ "unchecked" })
			List<Test> list = query.list();
			//System.out.println("list === " + list);

			for (Test v : list) {
				regList.add(v);
			}

			tx.commit();
			if (!regList.isEmpty()) {
				return regList;
			}
		} catch (Exception ve) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
		return Collections.emptyList();

	}

	
	//Some dummy code to return the emission test results.
	public String testEmission(Test test, String selectedRegNumber) {
		double testParameter1_CO = test.getTestParameter1();  
		double testParameter2_CO2 = test.getTestParameter2();  
		double testParameter3_HC = test.getTestParameter3();  
		String result = "Test Pass";
		if(testParameter1_CO>1 || (testParameter2_CO2 > 2.5 && testParameter3_HC>=1.3))
		{
			result = "Test Fail"; 
		}
		return result;
	}

	
	@Transactional(readOnly = false)
	public void createTest(Test test, String selectedRegNumber) {
		test.setRegNumber(selectedRegNumber);
		getSessionFactory().getCurrentSession().save(test);
		
	}
	
}
