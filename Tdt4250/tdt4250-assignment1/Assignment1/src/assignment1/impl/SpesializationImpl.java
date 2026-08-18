/**
 */
package assignment1.impl;

import assignment1.Assignment1Package;
import assignment1.Semester;
import assignment1.Spesialization;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Spesialization</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link assignment1.impl.SpesializationImpl#getName <em>Name</em>}</li>
 *   <li>{@link assignment1.impl.SpesializationImpl#getLowerLevelCourseLimit <em>Lower Level Course Limit</em>}</li>
 *   <li>{@link assignment1.impl.SpesializationImpl#getNumberOfCompulsoryCourses <em>Number Of Compulsory Courses</em>}</li>
 *   <li>{@link assignment1.impl.SpesializationImpl#getSemester <em>Semester</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SpesializationImpl extends MinimalEObjectImpl.Container implements Spesialization {
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
	 * The default value of the '{@link #getLowerLevelCourseLimit() <em>Lower Level Course Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLowerLevelCourseLimit()
	 * @generated
	 * @ordered
	 */
	protected static final int LOWER_LEVEL_COURSE_LIMIT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLowerLevelCourseLimit() <em>Lower Level Course Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLowerLevelCourseLimit()
	 * @generated
	 * @ordered
	 */
	protected int lowerLevelCourseLimit = LOWER_LEVEL_COURSE_LIMIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getNumberOfCompulsoryCourses() <em>Number Of Compulsory Courses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumberOfCompulsoryCourses()
	 * @generated
	 * @ordered
	 */
	protected static final int NUMBER_OF_COMPULSORY_COURSES_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNumberOfCompulsoryCourses() <em>Number Of Compulsory Courses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumberOfCompulsoryCourses()
	 * @generated
	 * @ordered
	 */
	protected int numberOfCompulsoryCourses = NUMBER_OF_COMPULSORY_COURSES_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSemester() <em>Semester</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSemester()
	 * @generated
	 * @ordered
	 */
	protected EList<Semester> semester;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SpesializationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Assignment1Package.Literals.SPESIALIZATION;
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
			eNotify(new ENotificationImpl(this, Notification.SET, Assignment1Package.SPESIALIZATION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLowerLevelCourseLimit() {
		return lowerLevelCourseLimit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLowerLevelCourseLimit(int newLowerLevelCourseLimit) {
		int oldLowerLevelCourseLimit = lowerLevelCourseLimit;
		lowerLevelCourseLimit = newLowerLevelCourseLimit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Assignment1Package.SPESIALIZATION__LOWER_LEVEL_COURSE_LIMIT, oldLowerLevelCourseLimit, lowerLevelCourseLimit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNumberOfCompulsoryCourses() {
		return numberOfCompulsoryCourses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumberOfCompulsoryCourses(int newNumberOfCompulsoryCourses) {
		int oldNumberOfCompulsoryCourses = numberOfCompulsoryCourses;
		numberOfCompulsoryCourses = newNumberOfCompulsoryCourses;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Assignment1Package.SPESIALIZATION__NUMBER_OF_COMPULSORY_COURSES, oldNumberOfCompulsoryCourses, numberOfCompulsoryCourses));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Semester> getSemester() {
		if (semester == null) {
			semester = new EObjectContainmentEList<Semester>(Semester.class, this, Assignment1Package.SPESIALIZATION__SEMESTER);
		}
		return semester;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Assignment1Package.SPESIALIZATION__SEMESTER:
				return ((InternalEList<?>)getSemester()).basicRemove(otherEnd, msgs);
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
			case Assignment1Package.SPESIALIZATION__NAME:
				return getName();
			case Assignment1Package.SPESIALIZATION__LOWER_LEVEL_COURSE_LIMIT:
				return getLowerLevelCourseLimit();
			case Assignment1Package.SPESIALIZATION__NUMBER_OF_COMPULSORY_COURSES:
				return getNumberOfCompulsoryCourses();
			case Assignment1Package.SPESIALIZATION__SEMESTER:
				return getSemester();
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
			case Assignment1Package.SPESIALIZATION__NAME:
				setName((String)newValue);
				return;
			case Assignment1Package.SPESIALIZATION__LOWER_LEVEL_COURSE_LIMIT:
				setLowerLevelCourseLimit((Integer)newValue);
				return;
			case Assignment1Package.SPESIALIZATION__NUMBER_OF_COMPULSORY_COURSES:
				setNumberOfCompulsoryCourses((Integer)newValue);
				return;
			case Assignment1Package.SPESIALIZATION__SEMESTER:
				getSemester().clear();
				getSemester().addAll((Collection<? extends Semester>)newValue);
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
			case Assignment1Package.SPESIALIZATION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case Assignment1Package.SPESIALIZATION__LOWER_LEVEL_COURSE_LIMIT:
				setLowerLevelCourseLimit(LOWER_LEVEL_COURSE_LIMIT_EDEFAULT);
				return;
			case Assignment1Package.SPESIALIZATION__NUMBER_OF_COMPULSORY_COURSES:
				setNumberOfCompulsoryCourses(NUMBER_OF_COMPULSORY_COURSES_EDEFAULT);
				return;
			case Assignment1Package.SPESIALIZATION__SEMESTER:
				getSemester().clear();
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
			case Assignment1Package.SPESIALIZATION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case Assignment1Package.SPESIALIZATION__LOWER_LEVEL_COURSE_LIMIT:
				return lowerLevelCourseLimit != LOWER_LEVEL_COURSE_LIMIT_EDEFAULT;
			case Assignment1Package.SPESIALIZATION__NUMBER_OF_COMPULSORY_COURSES:
				return numberOfCompulsoryCourses != NUMBER_OF_COMPULSORY_COURSES_EDEFAULT;
			case Assignment1Package.SPESIALIZATION__SEMESTER:
				return semester != null && !semester.isEmpty();
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
		result.append(" (name: ");
		result.append(name);
		result.append(", LowerLevelCourseLimit: ");
		result.append(lowerLevelCourseLimit);
		result.append(", NumberOfCompulsoryCourses: ");
		result.append(numberOfCompulsoryCourses);
		result.append(')');
		return result.toString();
	}

} //SpesializationImpl
