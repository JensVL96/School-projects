/**
 */
package assignment1;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Semester</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link assignment1.Semester#getSeason <em>Season</em>}</li>
 *   <li>{@link assignment1.Semester#getYear <em>Year</em>}</li>
 *   <li>{@link assignment1.Semester#getCourses <em>Courses</em>}</li>
 * </ul>
 *
 * @see assignment1.Assignment1Package#getSemester()
 * @model
 * @generated
 */
public interface Semester extends EObject {
	/**
	 * Returns the value of the '<em><b>Season</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Season</em>' attribute.
	 * @see #setSeason(String)
	 * @see assignment1.Assignment1Package#getSemester_Season()
	 * @model required="true"
	 * @generated
	 */
	String getSeason();

	/**
	 * Sets the value of the '{@link assignment1.Semester#getSeason <em>Season</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Season</em>' attribute.
	 * @see #getSeason()
	 * @generated
	 */
	void setSeason(String value);

	/**
	 * Returns the value of the '<em><b>Year</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Year</em>' attribute.
	 * @see #setYear(String)
	 * @see assignment1.Assignment1Package#getSemester_Year()
	 * @model required="true"
	 * @generated
	 */
	String getYear();

	/**
	 * Sets the value of the '{@link assignment1.Semester#getYear <em>Year</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Year</em>' attribute.
	 * @see #getYear()
	 * @generated
	 */
	void setYear(String value);

	/**
	 * Returns the value of the '<em><b>Courses</b></em>' containment reference list.
	 * The list contents are of type {@link assignment1.Course}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Courses</em>' containment reference list.
	 * @see assignment1.Assignment1Package#getSemester_Courses()
	 * @model containment="true"
	 * @generated
	 */
	EList<Course> getCourses();

} // Semester
