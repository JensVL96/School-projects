/**
 */
package tdt4250.ra.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import tdt4250.ra.Course;
import tdt4250.ra.Department;
import tdt4250.ra.Person;
import tdt4250.ra.RaFactory;
import tdt4250.ra.RaPackage;
import tdt4250.ra.ResourceAllocation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Department</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link tdt4250.ra.impl.DepartmentImpl#getName <em>Name</em>}</li>
 *   <li>{@link tdt4250.ra.impl.DepartmentImpl#getShortName <em>Short Name</em>}</li>
 *   <li>{@link tdt4250.ra.impl.DepartmentImpl#getStaff <em>Staff</em>}</li>
 *   <li>{@link tdt4250.ra.impl.DepartmentImpl#getCourses <em>Courses</em>}</li>
 *   <li>{@link tdt4250.ra.impl.DepartmentImpl#getResourceAllocations <em>Resource Allocations</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DepartmentImpl extends MinimalEObjectImpl.Container implements Department {
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
	 * The default value of the '{@link #getShortName() <em>Short Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortName()
	 * @generated
	 * @ordered
	 */
	protected static final String SHORT_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getShortName() <em>Short Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortName()
	 * @generated
	 * @ordered
	 */
	protected String shortName = SHORT_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getStaff() <em>Staff</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStaff()
	 * @generated
	 * @ordered
	 */
	protected EList<Person> staff;

	/**
	 * The cached value of the '{@link #getCourses() <em>Courses</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCourses()
	 * @generated
	 * @ordered
	 */
	protected EList<Course> courses;

	/**
	 * The cached value of the '{@link #getResourceAllocations() <em>Resource Allocations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceAllocations()
	 * @generated
	 * @ordered
	 */
	protected EList<ResourceAllocation> resourceAllocations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DepartmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RaPackage.Literals.DEPARTMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RaPackage.DEPARTMENT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getShortName() {
		return shortName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setShortName(String newShortName) {
		String oldShortName = shortName;
		shortName = newShortName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RaPackage.DEPARTMENT__SHORT_NAME, oldShortName, shortName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Person> getStaff() {
		if (staff == null) {
			staff = new EObjectContainmentWithInverseEList<Person>(Person.class, this, RaPackage.DEPARTMENT__STAFF, RaPackage.PERSON__EMPLOYER);
		}
		return staff;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Course> getCourses() {
		if (courses == null) {
			courses = new EObjectContainmentEList<Course>(Course.class, this, RaPackage.DEPARTMENT__COURSES);
		}
		return courses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ResourceAllocation> getResourceAllocations() {
		if (resourceAllocations == null) {
			resourceAllocations = new EObjectContainmentEList<ResourceAllocation>(ResourceAllocation.class, this, RaPackage.DEPARTMENT__RESOURCE_ALLOCATIONS);
		}
		return resourceAllocations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @NOT generated
	 */
	@Override
	public void allocateStaffToCourse(Person person, Course course, float factor) {
		ResourceAllocation resourceAllocation = RaFactory.eINSTANCE.createResourceAllocation();
		resourceAllocation.setPerson(person);
		resourceAllocation.setCourse(course);
		resourceAllocation.setFactor(factor);
		getResourceAllocations().add(resourceAllocation);
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
			case RaPackage.DEPARTMENT__STAFF:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getStaff()).basicAdd(otherEnd, msgs);
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
			case RaPackage.DEPARTMENT__STAFF:
				return ((InternalEList<?>)getStaff()).basicRemove(otherEnd, msgs);
			case RaPackage.DEPARTMENT__COURSES:
				return ((InternalEList<?>)getCourses()).basicRemove(otherEnd, msgs);
			case RaPackage.DEPARTMENT__RESOURCE_ALLOCATIONS:
				return ((InternalEList<?>)getResourceAllocations()).basicRemove(otherEnd, msgs);
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
			case RaPackage.DEPARTMENT__NAME:
				return getName();
			case RaPackage.DEPARTMENT__SHORT_NAME:
				return getShortName();
			case RaPackage.DEPARTMENT__STAFF:
				return getStaff();
			case RaPackage.DEPARTMENT__COURSES:
				return getCourses();
			case RaPackage.DEPARTMENT__RESOURCE_ALLOCATIONS:
				return getResourceAllocations();
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
			case RaPackage.DEPARTMENT__NAME:
				setName((String)newValue);
				return;
			case RaPackage.DEPARTMENT__SHORT_NAME:
				setShortName((String)newValue);
				return;
			case RaPackage.DEPARTMENT__STAFF:
				getStaff().clear();
				getStaff().addAll((Collection<? extends Person>)newValue);
				return;
			case RaPackage.DEPARTMENT__COURSES:
				getCourses().clear();
				getCourses().addAll((Collection<? extends Course>)newValue);
				return;
			case RaPackage.DEPARTMENT__RESOURCE_ALLOCATIONS:
				getResourceAllocations().clear();
				getResourceAllocations().addAll((Collection<? extends ResourceAllocation>)newValue);
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
			case RaPackage.DEPARTMENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case RaPackage.DEPARTMENT__SHORT_NAME:
				setShortName(SHORT_NAME_EDEFAULT);
				return;
			case RaPackage.DEPARTMENT__STAFF:
				getStaff().clear();
				return;
			case RaPackage.DEPARTMENT__COURSES:
				getCourses().clear();
				return;
			case RaPackage.DEPARTMENT__RESOURCE_ALLOCATIONS:
				getResourceAllocations().clear();
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
			case RaPackage.DEPARTMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case RaPackage.DEPARTMENT__SHORT_NAME:
				return SHORT_NAME_EDEFAULT == null ? shortName != null : !SHORT_NAME_EDEFAULT.equals(shortName);
			case RaPackage.DEPARTMENT__STAFF:
				return staff != null && !staff.isEmpty();
			case RaPackage.DEPARTMENT__COURSES:
				return courses != null && !courses.isEmpty();
			case RaPackage.DEPARTMENT__RESOURCE_ALLOCATIONS:
				return resourceAllocations != null && !resourceAllocations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case RaPackage.DEPARTMENT___ALLOCATE_STAFF_TO_COURSE__PERSON_COURSE_FLOAT:
				allocateStaffToCourse((Person)arguments.get(0), (Course)arguments.get(1), (Float)arguments.get(2));
				return null;
		}
		return super.eInvoke(operationID, arguments);
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
		result.append(", shortName: ");
		result.append(shortName);
		result.append(')');
		return result.toString();
	}

} //DepartmentImpl
