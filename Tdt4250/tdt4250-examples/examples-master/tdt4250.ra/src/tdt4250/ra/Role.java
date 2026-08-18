/**
 */
package tdt4250.ra;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Role</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link tdt4250.ra.Role#getCourse <em>Course</em>}</li>
 *   <li>{@link tdt4250.ra.Role#getName <em>Name</em>}</li>
 *   <li>{@link tdt4250.ra.Role#getWorkload <em>Workload</em>}</li>
 * </ul>
 *
 * @see tdt4250.ra.RaPackage#getRole()
 * @model
 * @generated
 */
public interface Role extends EObject {
	/**
	 * Returns the value of the '<em><b>Course</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link tdt4250.ra.Course#getRoles <em>Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Course</em>' container reference.
	 * @see #setCourse(Course)
	 * @see tdt4250.ra.RaPackage#getRole_Course()
	 * @see tdt4250.ra.Course#getRoles
	 * @model opposite="roles" transient="false"
	 * @generated
	 */
	Course getCourse();

	/**
	 * Sets the value of the '{@link tdt4250.ra.Role#getCourse <em>Course</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Course</em>' container reference.
	 * @see #getCourse()
	 * @generated
	 */
	void setCourse(Course value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see tdt4250.ra.RaPackage#getRole_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link tdt4250.ra.Role#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Workload</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Workload</em>' attribute.
	 * @see #setWorkload(float)
	 * @see tdt4250.ra.RaPackage#getRole_Workload()
	 * @model
	 * @generated
	 */
	float getWorkload();

	/**
	 * Sets the value of the '{@link tdt4250.ra.Role#getWorkload <em>Workload</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Workload</em>' attribute.
	 * @see #getWorkload()
	 * @generated
	 */
	void setWorkload(float value);

} // Role
