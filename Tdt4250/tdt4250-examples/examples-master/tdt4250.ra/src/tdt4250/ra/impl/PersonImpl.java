/**
 */
package tdt4250.ra.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import tdt4250.ra.Department;
import tdt4250.ra.Person;
import tdt4250.ra.RaPackage;
import tdt4250.ra.ResourceAllocation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Person</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link tdt4250.ra.impl.PersonImpl#getName <em>Name</em>}</li>
 *   <li>{@link tdt4250.ra.impl.PersonImpl#getEmployer <em>Employer</em>}</li>
 *   <li>{@link tdt4250.ra.impl.PersonImpl#getAllocations <em>Allocations</em>}</li>
 *   <li>{@link tdt4250.ra.impl.PersonImpl#getWorkload <em>Workload</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PersonImpl extends MinimalEObjectImpl.Container implements Person {
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
	 * The cached value of the '{@link #getAllocations() <em>Allocations</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllocations()
	 * @generated
	 * @ordered
	 */
	protected EList<ResourceAllocation> allocations;

	/**
	 * The default value of the '{@link #getWorkload() <em>Workload</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkload()
	 * @generated
	 * @ordered
	 */
	protected static final float WORKLOAD_EDEFAULT = 0.0F;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PersonImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RaPackage.Literals.PERSON;
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
			eNotify(new ENotificationImpl(this, Notification.SET, RaPackage.PERSON__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Department getEmployer() {
		if (eContainerFeatureID() != RaPackage.PERSON__EMPLOYER) return null;
		return (Department)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEmployer(Department newEmployer, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newEmployer, RaPackage.PERSON__EMPLOYER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEmployer(Department newEmployer) {
		if (newEmployer != eInternalContainer() || (eContainerFeatureID() != RaPackage.PERSON__EMPLOYER && newEmployer != null)) {
			if (EcoreUtil.isAncestor(this, newEmployer))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newEmployer != null)
				msgs = ((InternalEObject)newEmployer).eInverseAdd(this, RaPackage.DEPARTMENT__STAFF, Department.class, msgs);
			msgs = basicSetEmployer(newEmployer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RaPackage.PERSON__EMPLOYER, newEmployer, newEmployer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ResourceAllocation> getAllocations() {
		if (allocations == null) {
			allocations = new EObjectWithInverseResolvingEList<ResourceAllocation>(ResourceAllocation.class, this, RaPackage.PERSON__ALLOCATIONS, RaPackage.RESOURCE_ALLOCATION__PERSON);
		}
		return allocations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @NOT generated
	 */
	@Override
	public float getWorkload() {
		float sum = 0.0f;
		for (ResourceAllocation ras : getAllocations()) {
			sum += ras.getWorkload();
		}
		return sum;
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
			case RaPackage.PERSON__EMPLOYER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetEmployer((Department)otherEnd, msgs);
			case RaPackage.PERSON__ALLOCATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAllocations()).basicAdd(otherEnd, msgs);
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
			case RaPackage.PERSON__EMPLOYER:
				return basicSetEmployer(null, msgs);
			case RaPackage.PERSON__ALLOCATIONS:
				return ((InternalEList<?>)getAllocations()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case RaPackage.PERSON__EMPLOYER:
				return eInternalContainer().eInverseRemove(this, RaPackage.DEPARTMENT__STAFF, Department.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RaPackage.PERSON__NAME:
				return getName();
			case RaPackage.PERSON__EMPLOYER:
				return getEmployer();
			case RaPackage.PERSON__ALLOCATIONS:
				return getAllocations();
			case RaPackage.PERSON__WORKLOAD:
				return getWorkload();
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
			case RaPackage.PERSON__NAME:
				setName((String)newValue);
				return;
			case RaPackage.PERSON__EMPLOYER:
				setEmployer((Department)newValue);
				return;
			case RaPackage.PERSON__ALLOCATIONS:
				getAllocations().clear();
				getAllocations().addAll((Collection<? extends ResourceAllocation>)newValue);
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
			case RaPackage.PERSON__NAME:
				setName(NAME_EDEFAULT);
				return;
			case RaPackage.PERSON__EMPLOYER:
				setEmployer((Department)null);
				return;
			case RaPackage.PERSON__ALLOCATIONS:
				getAllocations().clear();
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
			case RaPackage.PERSON__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case RaPackage.PERSON__EMPLOYER:
				return getEmployer() != null;
			case RaPackage.PERSON__ALLOCATIONS:
				return allocations != null && !allocations.isEmpty();
			case RaPackage.PERSON__WORKLOAD:
				return getWorkload() != WORKLOAD_EDEFAULT;
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
		result.append(')');
		return result.toString();
	}

} //PersonImpl
