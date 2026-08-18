/**
 */
package assignment1;

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
 *   <li>{@link assignment1.Course#getCode <em>Code</em>}</li>
 *   <li>{@link assignment1.Course#getName <em>Name</em>}</li>
 *   <li>{@link assignment1.Course#getAmountCompulsoryCourses <em>Amount Compulsory Courses</em>}</li>
 *   <li>{@link assignment1.Course#getStatus <em>Status</em>}</li>
 *   <li>{@link assignment1.Course#getStudyLevel <em>Study Level</em>}</li>
 *   <li>{@link assignment1.Course#getDepartment <em>Department</em>}</li>
 * </ul>
 *
 * @see assignment1.Assignment1Package#getCourse()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='MeetsNecessaryCompulsoryCoursesRequirements'"
 *        annotation="1.0 MeetsNecessaryCompulsoryCoursesRequirements='AmountCompulsoryCourses = self.NumberOfCompulsoryCourses '"
 * @generated
 */
public interface Course extends EObject {
	/**
	 * Returns the value of the '<em><b>Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Code</em>' attribute.
	 * @see #setCode(String)
	 * @see assignment1.Assignment1Package#getCourse_Code()
	 * @model required="true"
	 * @generated
	 */
	String getCode();

	/**
	 * Sets the value of the '{@link assignment1.Course#getCode <em>Code</em>}' attribute.
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
	 * @see assignment1.Assignment1Package#getCourse_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link assignment1.Course#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Amount Compulsory Courses</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Amount Compulsory Courses</em>' attribute.
	 * @see #setAmountCompulsoryCourses(double)
	 * @see assignment1.Assignment1Package#getCourse_AmountCompulsoryCourses()
	 * @model
	 * @generated
	 */
	double getAmountCompulsoryCourses();

	/**
	 * Sets the value of the '{@link assignment1.Course#getAmountCompulsoryCourses <em>Amount Compulsory Courses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Amount Compulsory Courses</em>' attribute.
	 * @see #getAmountCompulsoryCourses()
	 * @generated
	 */
	void setAmountCompulsoryCourses(double value);

	/**
	 * Returns the value of the '<em><b>Status</b></em>' attribute.
	 * The literals are from the enumeration {@link assignment1.Status}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Status</em>' attribute.
	 * @see assignment1.Status
	 * @see #setStatus(Status)
	 * @see assignment1.Assignment1Package#getCourse_Status()
	 * @model required="true"
	 * @generated
	 */
	Status getStatus();

	/**
	 * Sets the value of the '{@link assignment1.Course#getStatus <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' attribute.
	 * @see assignment1.Status
	 * @see #getStatus()
	 * @generated
	 */
	void setStatus(Status value);

	/**
	 * Returns the value of the '<em><b>Study Level</b></em>' attribute.
	 * The literals are from the enumeration {@link assignment1.StudyLevel}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Study Level</em>' attribute.
	 * @see assignment1.StudyLevel
	 * @see #setStudyLevel(StudyLevel)
	 * @see assignment1.Assignment1Package#getCourse_StudyLevel()
	 * @model required="true"
	 * @generated
	 */
	StudyLevel getStudyLevel();

	/**
	 * Sets the value of the '{@link assignment1.Course#getStudyLevel <em>Study Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Study Level</em>' attribute.
	 * @see assignment1.StudyLevel
	 * @see #getStudyLevel()
	 * @generated
	 */
	void setStudyLevel(StudyLevel value);

	/**
	 * Returns the value of the '<em><b>Department</b></em>' reference list.
	 * The list contents are of type {@link assignment1.Department}.
	 * It is bidirectional and its opposite is '{@link assignment1.Department#getCourses <em>Courses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Department</em>' reference list.
	 * @see assignment1.Assignment1Package#getCourse_Department()
	 * @see assignment1.Department#getCourses
	 * @model opposite="courses" derived="true"
	 * @generated
	 */
	EList<Department> getDepartment();

} // Course
