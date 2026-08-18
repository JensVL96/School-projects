/**
 */
package tdt4250.transportation;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see tdt4250.transportation.TransportationPackage
 * @generated
 */
public interface TransportationFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TransportationFactory eINSTANCE = tdt4250.transportation.impl.TransportationFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Vehicle</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Vehicle</em>'.
	 * @generated
	 */
	Vehicle createVehicle();

	/**
	 * Returns a new object of class '<em>Vehicle Route</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Vehicle Route</em>'.
	 * @generated
	 */
	VehicleRoute createVehicleRoute();

	/**
	 * Returns a new object of class '<em>Goods</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Goods</em>'.
	 * @generated
	 */
	Goods createGoods();

	/**
	 * Returns a new object of class '<em>Geo Location</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Geo Location</em>'.
	 * @generated
	 */
	GeoLocation createGeoLocation();

	/**
	 * Returns a new object of class '<em>Tracking Event</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tracking Event</em>'.
	 * @generated
	 */
	TrackingEvent createTrackingEvent();

	/**
	 * Returns a new object of class '<em>Transportation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Transportation</em>'.
	 * @generated
	 */
	Transportation createTransportation();

	/**
	 * Returns a new object of class '<em>Geo Locations</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Geo Locations</em>'.
	 * @generated
	 */
	GeoLocations createGeoLocations();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TransportationPackage getTransportationPackage();

} //TransportationFactory
