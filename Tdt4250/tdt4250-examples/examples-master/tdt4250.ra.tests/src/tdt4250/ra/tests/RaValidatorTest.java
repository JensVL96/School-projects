package tdt4250.ra.tests;

import org.eclipse.acceleo.query.delegates.AQLValidationDelegate;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator.ValidationDelegate;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tdt4250.ra.Course;
import tdt4250.ra.Person;
import tdt4250.ra.RaFactory;
import tdt4250.ra.ResourceAllocation;
import tdt4250.ra.Role;

public class RaValidatorTest {

	@Before
	public void setUp() {
		// register AQL (an OCL implementation) constraint support
		ValidationDelegate.Registry.INSTANCE.put("http://www.eclipse.org/acceleo/query/1.0", new AQLValidationDelegate());
	}
	
	@Test
	public void test_validatePerson_personShouldntHaveTooMuchToDo() {
		Person p = RaFactory.eINSTANCE.createPerson();
		allocateResource(p.getAllocations(), 0.5f);
		checkDiagnostic(p, Diagnostic.OK);
		allocateResource(p.getAllocations(), 0.8f);
		checkDiagnostic(p, Diagnostic.WARNING);
		allocateResource(p.getAllocations(), 0.5f);
		checkDiagnostic(p, Diagnostic.ERROR);
	}

	@Test
	public void test_validateCourse_courseShouldntHaveTooLitteStaff() {
		Course c = RaFactory.eINSTANCE.createCourse();
		allocateResource(c.getAllocations(), 0.5f);
		checkDiagnostic(c, Diagnostic.ERROR);
		allocateResource(c.getAllocations(), 0.4f);
		checkDiagnostic(c, Diagnostic.OK);
	}
	
	private void checkDiagnostic(EObject root, int severity) {
		Diagnostic diagnostics = Diagnostician.INSTANCE.validate(root);
		Assert.assertTrue(diagnostics.getSeverity() == severity);
	}

	private void allocateResource(EList<ResourceAllocation> allocations, float factor) {
		ResourceAllocation ra1 = RaFactory.eINSTANCE.createResourceAllocation();
		ra1.setFactor(factor);
		allocations.add(ra1);
	}
	
	@Test
	public void test_validateResourceAllocation_roleMustBeForSameCourse() {
		Course c1 = RaFactory.eINSTANCE.createCourse();
		Role c1r = RaFactory.eINSTANCE.createRole();
		c1.getRoles().add(c1r);
		Course c2 = RaFactory.eINSTANCE.createCourse();
		Role c2r = RaFactory.eINSTANCE.createRole();
		c2.getRoles().add(c2r);

		ResourceAllocation ra = RaFactory.eINSTANCE.createResourceAllocation();
		ra.setCourse(c1);
		ra.setRole(c1r);
		checkDiagnostic(ra, Diagnostic.OK);

		ra.setRole(c2r);
		checkDiagnostic(ra, Diagnostic.ERROR);
	}
}
