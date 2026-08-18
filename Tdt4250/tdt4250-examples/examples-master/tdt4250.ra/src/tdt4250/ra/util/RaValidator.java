/**
 */
package tdt4250.ra.util;

import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

import tdt4250.ra.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see tdt4250.ra.RaPackage
 * @generated
 */
public class RaValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final RaValidator INSTANCE = new RaValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "tdt4250.ra";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RaValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return RaPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case RaPackage.DEPARTMENT:
				return validateDepartment((Department)value, diagnostics, context);
			case RaPackage.COURSE:
				return validateCourse((Course)value, diagnostics, context);
			case RaPackage.ROLE:
				return validateRole((Role)value, diagnostics, context);
			case RaPackage.PERSON:
				return validatePerson((Person)value, diagnostics, context);
			case RaPackage.RESOURCE_ALLOCATION:
				return validateResourceAllocation((ResourceAllocation)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDepartment(Department department, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(department, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCourse(Course course, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(course, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(course, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(course, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(course, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(course, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(course, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(course, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(course, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(course, diagnostics, context);
		if (result || diagnostics != null) result &= validateCourse_courseShouldntHaveTooLittleStaff(course, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the courseShouldntHaveTooLittleStaff constraint of '<em>Course</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String COURSE__COURSE_SHOULDNT_HAVE_TOO_LITTLE_STAFF__EEXPRESSION = "self.allocations.factor->sum() >= 0.8";

	/**
	 * Validates the courseShouldntHaveTooLittleStaff constraint of '<em>Course</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCourse_courseShouldntHaveTooLittleStaff(Course course, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(RaPackage.Literals.COURSE,
				 course,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/acceleo/query/1.0",
				 "courseShouldntHaveTooLittleStaff",
				 COURSE__COURSE_SHOULDNT_HAVE_TOO_LITTLE_STAFF__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRole(Role role, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(role, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePerson(Person person, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(person, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(person, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(person, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(person, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(person, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(person, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(person, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(person, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(person, diagnostics, context);
		if (result || diagnostics != null) result &= validatePerson_personShouldntHaveTooMuchToDo(person, diagnostics, context);
		return result;
	}

	/**
	 * Validates the personShouldntHaveTooMuchToDo constraint of '<em>Person</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @NOT generated
	 */
	public boolean validatePerson_personShouldntHaveTooMuchToDo(Person person, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (person.getWorkload() > 1.2) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						((person.getWorkload() > 1.7 ? Diagnostic.ERROR : Diagnostic.WARNING),
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "personShouldntHaveTooMuchToDo", getObjectLabel(person, context) },
						 new Object[] { person },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateResourceAllocation(ResourceAllocation resourceAllocation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(resourceAllocation, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(resourceAllocation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(resourceAllocation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(resourceAllocation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(resourceAllocation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(resourceAllocation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(resourceAllocation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(resourceAllocation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(resourceAllocation, diagnostics, context);
		if (result || diagnostics != null) result &= validateResourceAllocation_roleMustBeForSameCourse(resourceAllocation, diagnostics, context);
		return result;
	}

	/**
	 * Validates the roleMustBeForSameCourse constraint of '<em>Resource Allocation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateResourceAllocation_roleMustBeForSameCourse(ResourceAllocation resourceAllocation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		Role role = resourceAllocation.getRole();
		if (role != null && role.getCourse() != resourceAllocation.getCourse()) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "roleMustBeForSameCourse", getObjectLabel(resourceAllocation, context) },
						 new Object[] { resourceAllocation },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //RaValidator
