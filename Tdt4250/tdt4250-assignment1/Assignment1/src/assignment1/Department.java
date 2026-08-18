/**
 */
package assignment1;

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
 *   <li>{@link assignment1.Department#getName <em>Name</em>}</li>
 *   <li>{@link assignment1.Department#getCourses <em>Courses</em>}</li>
 *   <li>{@link assignment1.Department#getSpesialization <em>Spesialization</em>}</li>
 * </ul>
 *
 * @see assignment1.Assignment1Package#getDepartment()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='AllSemestersAccountedFor'"
 * @generated
 */
public interface Department extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see assignment1.Assignment1Package#getDepartment_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link assignment1.Department#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Courses</b></em>' reference list.
	 * The list contents are of type {@link assignment1.Course}.
	 * It is bidirectional and its opposite is '{@link assignment1.Course#getDepartment <em>Department</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Courses</em>' reference list.
	 * @see assignment1.Assignment1Package#getDepartment_Courses()
	 * @see assignment1.Course#getDepartment
	 * @model opposite="Department"
	 * @generated
	 */
	EList<Course> getCourses();

	/**
	 * Returns the value of the '<em><b>Spesialization</b></em>' containment reference list.
	 * The list contents are of type {@link assignment1.Spesialization}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Spesialization</em>' containment reference list.
	 * @see assignment1.Assignment1Package#getDepartment_Spesialization()
	 * @model containment="true" upper="4"
	 * @generated
	 */
	EList<Spesialization> getSpesialization();

} // Department
