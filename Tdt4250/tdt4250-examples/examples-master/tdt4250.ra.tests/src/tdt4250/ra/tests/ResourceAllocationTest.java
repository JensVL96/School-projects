/**
 */
package tdt4250.ra.tests;

import org.junit.Assert;

import junit.framework.TestCase;

import junit.textui.TestRunner;
import tdt4250.ra.RaFactory;
import tdt4250.ra.ResourceAllocation;
import tdt4250.ra.Role;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Resource Allocation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 *   <li>{@link tdt4250.ra.ResourceAllocation#getWorkload() <em>Workload</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class ResourceAllocationTest extends TestCase {

	/**
	 * The fixture for this Resource Allocation test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceAllocation fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ResourceAllocationTest.class);
	}

	/**
	 * Constructs a new Resource Allocation test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceAllocationTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Resource Allocation test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(ResourceAllocation fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Resource Allocation test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceAllocation getFixture() {
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
		setFixture(RaFactory.eINSTANCE.createResourceAllocation());
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
	 * Tests the '{@link tdt4250.ra.ResourceAllocation#getWorkload() <em>Workload</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tdt4250.ra.ResourceAllocation#getWorkload()
	 * @generated NOT
	 */
	public void testGetWorkload() {
		ResourceAllocation ra = getFixture();
		ra.setFactor(0.8f);
		Assert.assertEquals(0.8f, ra.getWorkload(), 0.0);

		Role role = RaFactory.eINSTANCE.createRole();
		role.setWorkload(0.5f);
		ra.setRole(role);
		Assert.assertEquals(0.8f * 0.5f, ra.getWorkload(), 0.0);
	}

} //ResourceAllocationTest
