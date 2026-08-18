/**
 */
package assignment1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Study Level</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see assignment1.Assignment1Package#getStudyLevel()
 * @model
 * @generated
 */
public enum StudyLevel implements Enumerator {
	/**
	 * The '<em><b>Second Degree</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SECOND_DEGREE_VALUE
	 * @generated
	 * @ordered
	 */
	SECOND_DEGREE(0, "SecondDegree", "SecondDegree"),

	/**
	 * The '<em><b>Third Year Course</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #THIRD_YEAR_COURSE_VALUE
	 * @generated
	 * @ordered
	 */
	THIRD_YEAR_COURSE(1, "ThirdYearCourse", "ThirdYearCourse");

	/**
	 * The '<em><b>Second Degree</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SECOND_DEGREE
	 * @model name="SecondDegree"
	 * @generated
	 * @ordered
	 */
	public static final int SECOND_DEGREE_VALUE = 0;

	/**
	 * The '<em><b>Third Year Course</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #THIRD_YEAR_COURSE
	 * @model name="ThirdYearCourse"
	 * @generated
	 * @ordered
	 */
	public static final int THIRD_YEAR_COURSE_VALUE = 1;

	/**
	 * An array of all the '<em><b>Study Level</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final StudyLevel[] VALUES_ARRAY =
		new StudyLevel[] {
			SECOND_DEGREE,
			THIRD_YEAR_COURSE,
		};

	/**
	 * A public read-only list of all the '<em><b>Study Level</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<StudyLevel> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Study Level</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static StudyLevel get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			StudyLevel result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Study Level</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static StudyLevel getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			StudyLevel result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Study Level</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static StudyLevel get(int value) {
		switch (value) {
			case SECOND_DEGREE_VALUE: return SECOND_DEGREE;
			case THIRD_YEAR_COURSE_VALUE: return THIRD_YEAR_COURSE;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private StudyLevel(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //StudyLevel
