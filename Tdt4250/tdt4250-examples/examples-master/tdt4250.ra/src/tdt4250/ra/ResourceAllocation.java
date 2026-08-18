/**
 */
package tdt4250.ra;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Allocation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link tdt4250.ra.ResourceAllocation#getPerson <em>Person</em>}</li>
 *   <li>{@link tdt4250.ra.ResourceAllocation#getCourse <em>Course</em>}</li>
 *   <li>{@link tdt4250.ra.ResourceAllocation#getRole <em>Role</em>}</li>
 *   <li>{@link tdt4250.ra.ResourceAllocation#getFactor <em>Factor</em>}</li>
 *   <li>{@link tdt4250.ra.ResourceAllocation#getWorkload <em>Workload</em>}</li>
 * </ul>
 *
 * @see tdt4250.ra.RaPackage#getResourceAllocation()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='roleMustBeForSameCourse'"
 * @generated
 */
public interface ResourceAllocation extends EObject {
	/**
	 * Returns the value of the '<em><b>Person</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link tdt4250.ra.Person#getAllocations <em>Allocations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Person</em>' reference.
	 * @see #setPerson(Person)
	 * @see tdt4250.ra.RaPackage#getResourceAllocation_Person()
	 * @see tdt4250.ra.Person#getAllocations
	 * @model opposite="allocations" keys="name"
	 * @generated
	 */
	Person getPerson();

	/**
	 * Sets the value of the '{@link tdt4250.ra.ResourceAllocation#getPerson <em>Person</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Person</em>' reference.
	 * @see #getPerson()
	 * @generated
	 */
	void setPerson(Person value);

	/**
	 * Returns the value of the '<em><b>Course</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link tdt4250.ra.Course#getAllocations <em>Allocations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Course</em>' reference.
	 * @see #setCourse(Course)
	 * @see tdt4250.ra.RaPackage#getResourceAllocation_Course()
	 * @see tdt4250.ra.Course#getAllocations
	 * @model opposite="allocations"
	 * @generated
	 */
	Course getCourse();

	/**
	 * Sets the value of the '{@link tdt4250.ra.ResourceAllocation#getCourse <em>Course</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Course</em>' reference.
	 * @see #getCourse()
	 * @generated
	 */
	void setCourse(Course value);

	/**
	 * Returns the value of the '<em><b>Role</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Role</em>' reference.
	 * @see #setRole(Role)
	 * @see tdt4250.ra.RaPackage#getResourceAllocation_Role()
	 * @model
	 * @generated
	 */
	Role getRole();

	/**
	 * Sets the value of the '{@link tdt4250.ra.ResourceAllocation#getRole <em>Role</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Role</em>' reference.
	 * @see #getRole()
	 * @generated
	 */
	void setRole(Role value);

	/**
	 * Returns the value of the '<em><b>Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Factor</em>' attribute.
	 * @see #setFactor(float)
	 * @see tdt4250.ra.RaPackage#getResourceAllocation_Factor()
	 * @model
	 * @generated
	 */
	float getFactor();

	/**
	 * Sets the value of the '{@link tdt4250.ra.ResourceAllocation#getFactor <em>Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Factor</em>' attribute.
	 * @see #getFactor()
	 * @generated
	 */
	void setFactor(float value);

	/**
	 * Returns the value of the '<em><b>Workload</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Workload</em>' attribute.
	 * @see tdt4250.ra.RaPackage#getResourceAllocation_Workload()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	float getWorkload();

} // ResourceAllocation
