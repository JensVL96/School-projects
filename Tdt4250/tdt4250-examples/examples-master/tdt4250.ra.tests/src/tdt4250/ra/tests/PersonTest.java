/**
 */
package tdt4250.ra.tests;

import junit.framework.TestCase;
import junit.textui.TestRunner;
import tdt4250.ra.Person;
import tdt4250.ra.RaFactory;
import tdt4250.ra.ResourceAllocation;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Person</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 *   <li>{@link tdt4250.ra.Person#getWorkload() <em>Workload</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class PersonTest extends TestCase {

	/**
	 * The fixture for this Person test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Person fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(PersonTest.class);
	}

	/**
	 * Constructs a new Person test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PersonTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Person test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(Person fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Person test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Person getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(RaFactory.eINSTANCE.createPerson());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

	/**
	 * Tests the '{@link tdt4250.ra.Person#getWorkload() <em>Workload</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tdt4250.ra.Person#getWorkload()
	 * @NOT generated
	 */
	public void testGetWorkload() {
		Person p = getFixture();
		assertEquals(0.0f, p.getWorkload());
		ResourceAllocation ra1 = RaFactory.eINSTANCE.createResourceAllocation();
		ra1.setFactor(0.5f);
		p.getAllocations().add(ra1);
		assertEquals(0.5f, p.getWorkload());
		ResourceAllocation ra2 = RaFactory.eINSTANCE.createResourceAllocation();
		ra2.setFactor(0.3f);
		p.getAllocations().add(ra2);

		assertEquals(0.5f + 0.3f, p.getWorkload());
	}

	
} //PersonTest
