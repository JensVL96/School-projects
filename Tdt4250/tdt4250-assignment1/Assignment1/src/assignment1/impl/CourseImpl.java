/**
 */
package assignment1.impl;

import assignment1.Assignment1Package;
import assignment1.Course;
import assignment1.Department;
import assignment1.Status;
import assignment1.StudyLevel;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Course</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link assignment1.impl.CourseImpl#getCode <em>Code</em>}</li>
 *   <li>{@link assignment1.impl.CourseImpl#getName <em>Name</em>}</li>
 *   <li>{@link assignment1.impl.CourseImpl#getAmountCompulsoryCourses <em>Amount Compulsory Courses</em>}</li>
 *   <li>{@link assignment1.impl.CourseImpl#getStatus <em>Status</em>}</li>
 *   <li>{@link assignment1.impl.CourseImpl#getStudyLevel <em>Study Level</em>}</li>
 *   <li>{@link assignment1.impl.CourseImpl#getDepartment <em>Department</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CourseImpl extends MinimalEObjectImpl.Container implements Course {
	/**
	 * The default value of the '{@link #getCode() <em>Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCode()
	 * @generated
	 * @ordered
	 */
	protected static final String CODE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCode() <em>Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCode()
	 * @generated
	 * @ordered
	 */
	protected String code = CODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getAmountCompulsoryCourses() <em>Amount Compulsory Courses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAmountCompulsoryCourses()
	 * @generated
	 * @ordered
	 */
	protected static final double AMOUNT_COMPULSORY_COURSES_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getAmountCompulsoryCourses() <em>Amount Compulsory Courses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAmountCompulsoryCourses()
	 * @generated
	 * @ordered
	 */
	protected double amountCompulsoryCourses = AMOUNT_COMPULSORY_COURSES_EDEFAULT;

	/**
	 * The default value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected static final Status STATUS_EDEFAULT = Status.COMPULSORY;

	/**
	 * The cached value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected Status status = STATUS_EDEFAULT;

	/**
	 * The default value of the '{@link #getStudyLevel() <em>Study Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStudyLevel()
	 * @generated
	 * @ordered
	 */
	protected static final StudyLevel STUDY_LEVEL_EDEFAULT = StudyLevel.SECOND_DEGREE;

	/**
	 * The cached value of the '{@link #getStudyLevel() <em>Study Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStudyLevel()
	 * @generated
	 * @ordered
	 */
	protected StudyLevel studyLevel = STUDY_LEVEL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDepartment() <em>Department</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDepartment()
	 * @generated
	 * @ordered
	 */
	protected EList<Department> department;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CourseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Assignment1Package.Literals.COURSE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCode() {
		return code;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCode(String newCode) {
		String oldCode = code;
		code = newCode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Assignment1Package.COURSE__CODE, oldCode, code));
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
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Assignment1Package.COURSE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getAmountCompulsoryCourses() {
		return amountCompulsoryCourses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAmountCompulsoryCourses(double newAmountCompulsoryCourses) {
		double oldAmountCompulsoryCourses = amountCompulsoryCourses;
		amountCompulsoryCourses = newAmountCompulsoryCourses;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Assignment1Package.COURSE__AMOUNT_COMPULSORY_COURSES, oldAmountCompulsoryCourses, amountCompulsoryCourses));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatus(Status newStatus) {
		Status oldStatus = status;
		status = newStatus == null ? STATUS_EDEFAULT : newStatus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Assignment1Package.COURSE__STATUS, oldStatus, status));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StudyLevel getStudyLevel() {
		return studyLevel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStudyLevel(StudyLevel newStudyLevel) {
		StudyLevel oldStudyLevel = studyLevel;
		studyLevel = newStudyLevel == null ? STUDY_LEVEL_EDEFAULT : newStudyLevel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Assignment1Package.COURSE__STUDY_LEVEL, oldStudyLevel, studyLevel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Department> getDepartment() {
		if (department == null) {
			department = new EObjectWithInverseResolvingEList.ManyInverse<Department>(Department.class, this, Assignment1Package.COURSE__DEPARTMENT, Assignment1Package.DEPARTMENT__COURSES);
		}
		return department;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Assignment1Package.COURSE__DEPARTMENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDepartment()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Assignment1Package.COURSE__DEPARTMENT:
				return ((InternalEList<?>)getDepartment()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Assignment1Package.COURSE__CODE:
				return getCode();
			case Assignment1Package.COURSE__NAME:
				return getName();
			case Assignment1Package.COURSE__AMOUNT_COMPULSORY_COURSES:
				return getAmountCompulsoryCourses();
			case Assignment1Package.COURSE__STATUS:
				return getStatus();
			case Assignment1Package.COURSE__STUDY_LEVEL:
				return getStudyLevel();
			case Assignment1Package.COURSE__DEPARTMENT:
				return getDepartment();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case Assignment1Package.COURSE__CODE:
				setCode((String)newValue);
				return;
			case Assignment1Package.COURSE__NAME:
				setName((String)newValue);
				return;
			case Assignment1Package.COURSE__AMOUNT_COMPULSORY_COURSES:
				setAmountCompulsoryCourses((Double)newValue);
				return;
			case Assignment1Package.COURSE__STATUS:
				setStatus((Status)newValue);
				return;
			case Assignment1Package.COURSE__STUDY_LEVEL:
				setStudyLevel((StudyLevel)newValue);
				return;
			case Assignment1Package.COURSE__DEPARTMENT:
				getDepartment().clear();
				getDepartment().addAll((Collection<? extends Department>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case Assignment1Package.COURSE__CODE:
				setCode(CODE_EDEFAULT);
				return;
			case Assignment1Package.COURSE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case Assignment1Package.COURSE__AMOUNT_COMPULSORY_COURSES:
				setAmountCompulsoryCourses(AMOUNT_COMPULSORY_COURSES_EDEFAULT);
				return;
			case Assignment1Package.COURSE__STATUS:
				setStatus(STATUS_EDEFAULT);
				return;
			case Assignment1Package.COURSE__STUDY_LEVEL:
				setStudyLevel(STUDY_LEVEL_EDEFAULT);
				return;
			case Assignment1Package.COURSE__DEPARTMENT:
				getDepartment().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case Assignment1Package.COURSE__CODE:
				return CODE_EDEFAULT == null ? code != null : !CODE_EDEFAULT.equals(code);
			case Assignment1Package.COURSE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case Assignment1Package.COURSE__AMOUNT_COMPULSORY_COURSES:
				return amountCompulsoryCourses != AMOUNT_COMPULSORY_COURSES_EDEFAULT;
			case Assignment1Package.COURSE__STATUS:
				return status != STATUS_EDEFAULT;
			case Assignment1Package.COURSE__STUDY_LEVEL:
				return studyLevel != STUDY_LEVEL_EDEFAULT;
			case Assignment1Package.COURSE__DEPARTMENT:
				return department != null && !department.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (code: ");
		result.append(code);
		result.append(", name: ");
		result.append(name);
		result.append(", AmountCompulsoryCourses: ");
		result.append(amountCompulsoryCourses);
		result.append(", status: ");
		result.append(status);
		result.append(", studyLevel: ");
		result.append(studyLevel);
		result.append(')');
		return result.toString();
	}

} //CourseImpl
