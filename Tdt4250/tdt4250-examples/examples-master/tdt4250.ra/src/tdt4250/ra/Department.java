/**
 */
package tdt4250.ra;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Department</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link tdt4250.ra.Department#getName <em>Name</em>}</li>
 *   <li>{@link tdt4250.ra.Department#getShortName <em>Short Name</em>}</li>
 *   <li>{@link tdt4250.ra.Department#getStaff <em>Staff</em>}</li>
 *   <li>{@link tdt4250.ra.Department#getCourses <em>Courses</em>}</li>
 *   <li>{@link tdt4250.ra.Department#getResourceAllocations <em>Resource Allocations</em>}</li>
 * </ul>
 *
 * @see tdt4250.ra.RaPackage#getDepartment()
 * @model
 * @generated
 */
public interface Department extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see tdt4250.ra.RaPackage#getDepartment_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link tdt4250.ra.Department#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Short Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Short Name</em>' attribute.
	 * @see #setShortName(String)
	 * @see tdt4250.ra.RaPackage#getDepartment_ShortName()
	 * @model
	 * @generated
	 */
	String getShortName();

	/**
	 * Sets the value of the '{@link tdt4250.ra.Department#getShortName <em>Short Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Short Name</em>' attribute.
	 * @see #getShortName()
	 * @generated
	 */
	void setShortName(String value);

	/**
	 * Returns the value of the '<em><b>Staff</b></em>' containment reference list.
	 * The list contents are of type {@link tdt4250.ra.Person}.
	 * It is bidirectional and its opposite is '{@link tdt4250.ra.Person#getEmployer <em>Employer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Staff</em>' containment reference list.
	 * @see tdt4250.ra.RaPackage#getDepartment_Staff()
	 * @see tdt4250.ra.Person#getEmployer
	 * @model opposite="employer" containment="true"
	 * @generated
	 */
	EList<Person> getStaff();

	/**
	 * Returns the value of the '<em><b>Courses</b></em>' containment reference list.
	 * The list contents are of type {@link tdt4250.ra.Course}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Courses</em>' containment reference list.
	 * @see tdt4250.ra.RaPackage#getDepartment_Courses()
	 * @model containment="true" keys="code"
	 * @generated
	 */
	EList<Course> getCourses();

	/**
	 * Returns the value of the '<em><b>Resource Allocations</b></em>' containment reference list.
	 * The list contents are of type {@link tdt4250.ra.ResourceAllocation}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Allocations</em>' containment reference list.
	 * @see tdt4250.ra.RaPackage#getDepartment_ResourceAllocations()
	 * @model containment="true"
	 * @generated
	 */
	EList<ResourceAllocation> getResourceAllocations();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model personRequired="true" courseRequired="true" factorRequired="true"
	 * @generated
	 */
	void allocateStaffToCourse(Person person, Course course, float factor);

} // Department
