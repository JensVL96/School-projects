/**
 */
package tdt4250.ra.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>ra</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class RaTests extends TestSuite {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(suite());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Test suite() {
		TestSuite suite = new RaTests("ra Tests");
		suite.addTestSuite(DepartmentTest.class);
		suite.addTestSuite(PersonTest.class);
		suite.addTestSuite(ResourceAllocationTest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RaTests(String name) {
		super(name);
	}

} //RaTests
