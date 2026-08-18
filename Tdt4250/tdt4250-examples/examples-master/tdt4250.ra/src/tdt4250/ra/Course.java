/**
 */
package tdt4250.ra;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Course</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link tdt4250.ra.Course#getCode <em>Code</em>}</li>
 *   <li>{@link tdt4250.ra.Course#getName <em>Name</em>}</li>
 *   <li>{@link tdt4250.ra.Course#getAllocations <em>Allocations</em>}</li>
 *   <li>{@link tdt4250.ra.Course#getRoles <em>Roles</em>}</li>
 * </ul>
 *
 * @see tdt4250.ra.RaPackage#getCourse()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='courseShouldntHaveTooLittleStaff'"
 *        annotation="http://www.eclipse.org/acceleo/query/1.0 courseShouldntHaveTooLittleStaff='self.allocations.factor-&gt;sum() &gt;= 0.8'"
 * @generated
 */
public interface Course extends EObject {
	/**
	 * Returns the value of the '<em><b>Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Code</em>' attribute.
	 * @see #setCode(String)
	 * @see tdt4250.ra.RaPackage#getCourse_Code()
	 * @model
	 * @generated
	 */
	String getCode();

	/**
	 * Sets the value of the '{@link tdt4250.ra.Course#getCode <em>Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Code</em>' attribute.
	 * @see #getCode()
	 * @generated
	 */
	void setCode(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see tdt4250.ra.RaPackage#getCourse_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link tdt4250.ra.Course#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Allocations</b></em>' reference list.
	 * The list contents are of type {@link tdt4250.ra.ResourceAllocation}.
	 * It is bidirectional and its opposite is '{@link tdt4250.ra.ResourceAllocation#getCourse <em>Course</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allocations</em>' reference list.
	 * @see tdt4250.ra.RaPackage#getCourse_Allocations()
	 * @see tdt4250.ra.ResourceAllocation#getCourse
	 * @model opposite="course"
	 * @generated
	 */
	EList<ResourceAllocation> getAllocations();

	/**
	 * Returns the value of the '<em><b>Roles</b></em>' containment reference list.
	 * The list contents are of type {@link tdt4250.ra.Role}.
	 * It is bidirectional and its opposite is '{@link tdt4250.ra.Role#getCourse <em>Course</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Roles</em>' containment reference list.
	 * @see tdt4250.ra.RaPackage#getCourse_Roles()
	 * @see tdt4250.ra.Role#getCourse
	 * @model opposite="course" containment="true"
	 * @generated
	 */
	EList<Role> getRoles();

} // Course
