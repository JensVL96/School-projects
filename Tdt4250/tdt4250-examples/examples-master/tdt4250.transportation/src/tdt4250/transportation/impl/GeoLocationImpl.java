/**
 */
package tdt4250.transportation.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import tdt4250.transportation.GeoLocation;
import tdt4250.transportation.TransportationPackage;
import tdt4250.transportation.util.GeoLoc;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Geo Location</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link tdt4250.transportation.impl.GeoLocationImpl#getName <em>Name</em>}</li>
 *   <li>{@link tdt4250.transportation.impl.GeoLocationImpl#getGeoLoc <em>Geo Loc</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GeoLocationImpl extends MinimalEObjectImpl.Container implements GeoLocation {
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
	 * The default value of the '{@link #getGeoLoc() <em>Geo Loc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeoLoc()
	 * @generated
	 * @ordered
	 */
	protected static final GeoLoc GEO_LOC_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGeoLoc() <em>Geo Loc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeoLoc()
	 * @generated
	 * @ordered
	 */
	protected GeoLoc geoLoc = GEO_LOC_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GeoLocationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TransportationPackage.Literals.GEO_LOCATION;
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
			eNotify(new ENotificationImpl(this, Notification.SET, TransportationPackage.GEO_LOCATION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeoLoc getGeoLoc() {
		return geoLoc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGeoLoc(GeoLoc newGeoLoc) {
		GeoLoc oldGeoLoc = geoLoc;
		geoLoc = newGeoLoc;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TransportationPackage.GEO_LOCATION__GEO_LOC, oldGeoLoc, geoLoc));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TransportationPackage.GEO_LOCATION__NAME:
				return getName();
			case TransportationPackage.GEO_LOCATION__GEO_LOC:
				return getGeoLoc();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TransportationPackage.GEO_LOCATION__NAME:
				setName((String)newValue);
				return;
			case TransportationPackage.GEO_LOCATION__GEO_LOC:
				setGeoLoc((GeoLoc)newValue);
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
			case TransportationPackage.GEO_LOCATION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case TransportationPackage.GEO_LOCATION__GEO_LOC:
				setGeoLoc(GEO_LOC_EDEFAULT);
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
			case TransportationPackage.GEO_LOCATION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case TransportationPackage.GEO_LOCATION__GEO_LOC:
				return GEO_LOC_EDEFAULT == null ? geoLoc != null : !GEO_LOC_EDEFAULT.equals(geoLoc);
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
		result.append(", geoLoc: ");
		result.append(geoLoc);
		result.append(')');
		return result.toString();
	}

} //GeoLocationImpl
