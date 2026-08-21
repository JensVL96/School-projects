/**
 */
package assignment1;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Spesialization</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link assignment1.Spesialization#getName <em>Name</em>}</li>
 *   <li>{@link assignment1.Spesialization#getLowerLevelCourseLimit <em>Lower Level Course Limit</em>}</li>
 *   <li>{@link assignment1.Spesialization#getNumberOfCompulsoryCourses <em>Number Of Compulsory Courses</em>}</li>
 *   <li>{@link assignment1.Spesialization#getSemester <em>Semester</em>}</li>
 * </ul>
 *
 * @see assignment1.Assignment1Package#getSpesialization()
 * @model
 * @generated
 */
public interface Spesialization extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see assignment1.Assignment1Package#getSpesialization_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link assignment1.Spesialization#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Lower Level Course Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lower Level Course Limit</em>' attribute.
	 * @see #setLowerLevelCourseLimit(int)
	 * @see assignment1.Assignment1Package#getSpesialization_LowerLevelCourseLimit()
	 * @model
	 * @generated
	 */
	int getLowerLevelCourseLimit();

	/**
	 * Sets the value of the '{@link assignment1.Spesialization#getLowerLevelCourseLimit <em>Lower Level Course Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lower Level Course Limit</em>' attribute.
	 * @see #getLowerLevelCourseLimit()
	 * @generated
	 */
	void setLowerLevelCourseLimit(int value);

	/**
	 * Returns the value of the '<em><b>Number Of Compulsory Courses</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Number Of Compulsory Courses</em>' attribute.
	 * @see #setNumberOfCompulsoryCourses(int)
	 * @see assignment1.Assignment1Package#getSpesialization_NumberOfCompulsoryCourses()
	 * @model
	 * @generated
	 */
	int getNumberOfCompulsoryCourses();

	/**
	 * Sets the value of the '{@link assignment1.Spesialization#getNumberOfCompulsoryCourses <em>Number Of Compulsory Courses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number Of Compulsory Courses</em>' attribute.
	 * @see #getNumberOfCompulsoryCourses()
	 * @generated
	 */
	void setNumberOfCompulsoryCourses(int value);

	/**
	 * Returns the value of the '<em><b>Semester</b></em>' containment reference list.
	 * The list contents are of type {@link assignment1.Semester}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Semester</em>' containment reference list.
	 * @see assignment1.Assignment1Package#getSpesialization_Semester()
	 * @model containment="true" upper="4"
	 * @generated
	 */
	EList<Semester> getSemester();

} // Spesialization
