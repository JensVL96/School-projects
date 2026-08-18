/**
 */
package tdt4250.transportation.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import tdt4250.transportation.*;
import tdt4250.transportation.util.GeoLoc;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TransportationFactoryImpl extends EFactoryImpl implements TransportationFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TransportationFactory init() {
		try {
			TransportationFactory theTransportationFactory = (TransportationFactory)EPackage.Registry.INSTANCE.getEFactory(TransportationPackage.eNS_URI);
			if (theTransportationFactory != null) {
				return theTransportationFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TransportationFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransportationFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case TransportationPackage.VEHICLE: return createVehicle();
			case TransportationPackage.VEHICLE_ROUTE: return createVehicleRoute();
			case TransportationPackage.GOODS: return createGoods();
			case TransportationPackage.GEO_LOCATION: return createGeoLocation();
			case TransportationPackage.TRACKING_EVENT: return createTrackingEvent();
			case TransportationPackage.TRANSPORTATION: return createTransportation();
			case TransportationPackage.GEO_LOCATIONS: return createGeoLocations();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case TransportationPackage.EGEO_LOC:
				return createEGeoLocFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case TransportationPackage.EGEO_LOC:
				return convertEGeoLocToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Vehicle createVehicle() {
		VehicleImpl vehicle = new VehicleImpl();
		return vehicle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VehicleRoute createVehicleRoute() {
		VehicleRouteImpl vehicleRoute = new VehicleRouteImpl();
		return vehicleRoute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Goods createGoods() {
		GoodsImpl goods = new GoodsImpl();
		return goods;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeoLocation createGeoLocation() {
		GeoLocationImpl geoLocation = new GeoLocationImpl();
		return geoLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TrackingEvent createTrackingEvent() {
		TrackingEventImpl trackingEvent = new TrackingEventImpl();
		return trackingEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Transportation createTransportation() {
		TransportationImpl transportation = new TransportationImpl();
		return transportation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeoLocations createGeoLocations() {
		GeoLocationsImpl geoLocations = new GeoLocationsImpl();
		return geoLocations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public GeoLoc createEGeoLocFromString(EDataType eDataType, String initialValue) {
		int pos = initialValue.indexOf(' ');
		if (pos < 0) {
			return null;
		}
		try {
			return new GeoLoc(Double.valueOf(initialValue.substring(0, pos)), Double.valueOf(initialValue.substring(pos + 1)));
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String convertEGeoLocToString(EDataType eDataType, Object instanceValue) {
		if (instanceValue == null) {
			return "";
		}
		GeoLoc geoLoc = (GeoLoc) instanceValue;
		return geoLoc.latitude + " " + geoLoc.longitude;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransportationPackage getTransportationPackage() {
		return (TransportationPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static TransportationPackage getPackage() {
		return TransportationPackage.eINSTANCE;
	}

} //TransportationFactoryImpl
