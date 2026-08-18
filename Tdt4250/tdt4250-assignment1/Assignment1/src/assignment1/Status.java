/**
 */
package assignment1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Status</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see assignment1.Assignment1Package#getStatus()
 * @model
 * @generated
 */
public enum Status implements Enumerator {
	/**
	 * The '<em><b>Compulsory</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COMPULSORY_VALUE
	 * @generated
	 * @ordered
	 */
	COMPULSORY(1, "Compulsory", "Compulsory"),

	/**
	 * The '<em><b>Atleast One</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ATLEAST_ONE_VALUE
	 * @generated
	 * @ordered
	 */
	ATLEAST_ONE(2, "AtleastOne", "AtleastOne"),

	/**
	 * The '<em><b>Elective Coordinated</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ELECTIVE_COORDINATED_VALUE
	 * @generated
	 * @ordered
	 */
	ELECTIVE_COORDINATED(3, "ElectiveCoordinated", "ElectiveCoordinated"),

	/**
	 * The '<em><b>Elective Not Coordinated</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ELECTIVE_NOT_COORDINATED_VALUE
	 * @generated
	 * @ordered
	 */
	ELECTIVE_NOT_COORDINATED(4, "ElectiveNotCoordinated", "ElectiveNotCoordinated");

	/**
	 * The '<em><b>Compulsory</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COMPULSORY
	 * @model name="Compulsory"
	 * @generated
	 * @ordered
	 */
	public static final int COMPULSORY_VALUE = 1;

	/**
	 * The '<em><b>Atleast One</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ATLEAST_ONE
	 * @model name="AtleastOne"
	 * @generated
	 * @ordered
	 */
	public static final int ATLEAST_ONE_VALUE = 2;

	/**
	 * The '<em><b>Elective Coordinated</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ELECTIVE_COORDINATED
	 * @model name="ElectiveCoordinated"
	 * @generated
	 * @ordered
	 */
	public static final int ELECTIVE_COORDINATED_VALUE = 3;

	/**
	 * The '<em><b>Elective Not Coordinated</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ELECTIVE_NOT_COORDINATED
	 * @model name="ElectiveNotCoordinated"
	 * @generated
	 * @ordered
	 */
	public static final int ELECTIVE_NOT_COORDINATED_VALUE = 4;

	/**
	 * An array of all the '<em><b>Status</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final Status[] VALUES_ARRAY =
		new Status[] {
			COMPULSORY,
			ATLEAST_ONE,
			ELECTIVE_COORDINATED,
			ELECTIVE_NOT_COORDINATED,
		};

	/**
	 * A public read-only list of all the '<em><b>Status</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<Status> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Status</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Status get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Status result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Status</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Status getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Status result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Status</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Status get(int value) {
		switch (value) {
			case COMPULSORY_VALUE: return COMPULSORY;
			case ATLEAST_ONE_VALUE: return ATLEAST_ONE;
			case ELECTIVE_COORDINATED_VALUE: return ELECTIVE_COORDINATED;
			case ELECTIVE_NOT_COORDINATED_VALUE: return ELECTIVE_NOT_COORDINATED;
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
	private Status(int value, String name, String literal) {
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
	
} //Status
