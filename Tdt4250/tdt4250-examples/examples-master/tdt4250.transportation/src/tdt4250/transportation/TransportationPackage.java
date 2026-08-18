/**
 */
package tdt4250.transportation;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see tdt4250.transportation.TransportationFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore validationDelegates='http://www.eclipse.org/acceleo/query/1.0'"
 * @generated
 */
public interface TransportationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "transportation";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "platform:/plugin/tdt4250.transportation/model/transportation.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "transportation";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TransportationPackage eINSTANCE = tdt4250.transportation.impl.TransportationPackageImpl.init();

	/**
	 * The meta object id for the '{@link tdt4250.transportation.impl.VehicleImpl <em>Vehicle</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tdt4250.transportation.impl.VehicleImpl
	 * @see tdt4250.transportation.impl.TransportationPackageImpl#getVehicle()
	 * @generated
	 */
	int VEHICLE = 0;

	/**
	 * The feature id for the '<em><b>Capacity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VEHICLE__CAPACITY = 0;

	/**
	 * The feature id for the '<em><b>Routes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VEHICLE__ROUTES = 1;

	/**
	 * The number of structural features of the '<em>Vehicle</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VEHICLE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Vehicle</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VEHICLE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link tdt4250.transportation.impl.VehicleRouteImpl <em>Vehicle Route</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tdt4250.transportation.impl.VehicleRouteImpl
	 * @see tdt4250.transportation.impl.TransportationPackageImpl#getVehicleRoute()
	 * @generated
	 */
	int VEHICLE_ROUTE = 1;

	/**
	 * The feature id for the '<em><b>Goods</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VEHICLE_ROUTE__GOODS = 0;

	/**
	 * The feature id for the '<em><b>Plan</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VEHICLE_ROUTE__PLAN = 1;

	/**
	 * The feature id for the '<em><b>Actual Route</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VEHICLE_ROUTE__ACTUAL_ROUTE = 2;

	/**
	 * The feature id for the '<em><b>Vehicle</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VEHICLE_ROUTE__VEHICLE = 3;

	/**
	 * The feature id for the '<em><b>Total Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VEHICLE_ROUTE__TOTAL_WEIGHT = 4;

	/**
	 * The number of structural features of the '<em>Vehicle Route</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VEHICLE_ROUTE_FEATURE_COUNT = 5;

	/**
	 * The operation id for the '<em>Receive Tracking Info</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VEHICLE_ROUTE___RECEIVE_TRACKING_INFO__DOUBLE_DOUBLE = 0;

	/**
	 * The number of operations of the '<em>Vehicle Route</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VEHICLE_ROUTE_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link tdt4250.transportation.impl.GoodsImpl <em>Goods</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tdt4250.transportation.impl.GoodsImpl
	 * @see tdt4250.transportation.impl.TransportationPackageImpl#getGoods()
	 * @generated
	 */
	int GOODS = 2;

	/**
	 * The feature id for the '<em><b>Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOODS__WEIGHT = 0;

	/**
	 * The feature id for the '<em><b>Origin</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOODS__ORIGIN = 1;

	/**
	 * The feature id for the '<em><b>Destination</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOODS__DESTINATION = 2;

	/**
	 * The number of structural features of the '<em>Goods</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOODS_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Goods</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOODS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link tdt4250.transportation.impl.GeoLocationImpl <em>Geo Location</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tdt4250.transportation.impl.GeoLocationImpl
	 * @see tdt4250.transportation.impl.TransportationPackageImpl#getGeoLocation()
	 * @generated
	 */
	int GEO_LOCATION = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_LOCATION__NAME = 0;

	/**
	 * The feature id for the '<em><b>Geo Loc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_LOCATION__GEO_LOC = 1;

	/**
	 * The number of structural features of the '<em>Geo Location</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_LOCATION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Geo Location</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_LOCATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link tdt4250.transportation.impl.TrackingEventImpl <em>Tracking Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tdt4250.transportation.impl.TrackingEventImpl
	 * @see tdt4250.transportation.impl.TransportationPackageImpl#getTrackingEvent()
	 * @generated
	 */
	int TRACKING_EVENT = 4;

	/**
	 * The feature id for the '<em><b>Location</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKING_EVENT__LOCATION = 0;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKING_EVENT__TIMESTAMP = 1;

	/**
	 * The number of structural features of the '<em>Tracking Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKING_EVENT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Tracking Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACKING_EVENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link tdt4250.transportation.impl.TransportationImpl <em>Transportation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tdt4250.transportation.impl.TransportationImpl
	 * @see tdt4250.transportation.impl.TransportationPackageImpl#getTransportation()
	 * @generated
	 */
	int TRANSPORTATION = 5;

	/**
	 * The feature id for the '<em><b>Vehicles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSPORTATION__VEHICLES = 0;

	/**
	 * The feature id for the '<em><b>Locations</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSPORTATION__LOCATIONS = 1;

	/**
	 * The number of structural features of the '<em>Transportation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSPORTATION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Transportation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSPORTATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link tdt4250.transportation.impl.GeoLocationsImpl <em>Geo Locations</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tdt4250.transportation.impl.GeoLocationsImpl
	 * @see tdt4250.transportation.impl.TransportationPackageImpl#getGeoLocations()
	 * @generated
	 */
	int GEO_LOCATIONS = 6;

	/**
	 * The feature id for the '<em><b>Locations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_LOCATIONS__LOCATIONS = 0;

	/**
	 * The number of structural features of the '<em>Geo Locations</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_LOCATIONS_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Geo Locations</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEO_LOCATIONS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '<em>EGeo Loc</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tdt4250.transportation.util.GeoLoc
	 * @see tdt4250.transportation.impl.TransportationPackageImpl#getEGeoLoc()
	 * @generated
	 */
	int EGEO_LOC = 7;


	/**
	 * Returns the meta object for class '{@link tdt4250.transportation.Vehicle <em>Vehicle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Vehicle</em>'.
	 * @see tdt4250.transportation.Vehicle
	 * @generated
	 */
	EClass getVehicle();

	/**
	 * Returns the meta object for the attribute '{@link tdt4250.transportation.Vehicle#getCapacity <em>Capacity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Capacity</em>'.
	 * @see tdt4250.transportation.Vehicle#getCapacity()
	 * @see #getVehicle()
	 * @generated
	 */
	EAttribute getVehicle_Capacity();

	/**
	 * Returns the meta object for the containment reference list '{@link tdt4250.transportation.Vehicle#getRoutes <em>Routes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Routes</em>'.
	 * @see tdt4250.transportation.Vehicle#getRoutes()
	 * @see #getVehicle()
	 * @generated
	 */
	EReference getVehicle_Routes();

	/**
	 * Returns the meta object for class '{@link tdt4250.transportation.VehicleRoute <em>Vehicle Route</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Vehicle Route</em>'.
	 * @see tdt4250.transportation.VehicleRoute
	 * @generated
	 */
	EClass getVehicleRoute();

	/**
	 * Returns the meta object for the containment reference list '{@link tdt4250.transportation.VehicleRoute#getGoods <em>Goods</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Goods</em>'.
	 * @see tdt4250.transportation.VehicleRoute#getGoods()
	 * @see #getVehicleRoute()
	 * @generated
	 */
	EReference getVehicleRoute_Goods();

	/**
	 * Returns the meta object for the reference list '{@link tdt4250.transportation.VehicleRoute#getPlan <em>Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Plan</em>'.
	 * @see tdt4250.transportation.VehicleRoute#getPlan()
	 * @see #getVehicleRoute()
	 * @generated
	 */
	EReference getVehicleRoute_Plan();

	/**
	 * Returns the meta object for the containment reference list '{@link tdt4250.transportation.VehicleRoute#getActualRoute <em>Actual Route</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Actual Route</em>'.
	 * @see tdt4250.transportation.VehicleRoute#getActualRoute()
	 * @see #getVehicleRoute()
	 * @generated
	 */
	EReference getVehicleRoute_ActualRoute();

	/**
	 * Returns the meta object for the container reference '{@link tdt4250.transportation.VehicleRoute#getVehicle <em>Vehicle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Vehicle</em>'.
	 * @see tdt4250.transportation.VehicleRoute#getVehicle()
	 * @see #getVehicleRoute()
	 * @generated
	 */
	EReference getVehicleRoute_Vehicle();

	/**
	 * Returns the meta object for the attribute '{@link tdt4250.transportation.VehicleRoute#getTotalWeight <em>Total Weight</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Total Weight</em>'.
	 * @see tdt4250.transportation.VehicleRoute#getTotalWeight()
	 * @see #getVehicleRoute()
	 * @generated
	 */
	EAttribute getVehicleRoute_TotalWeight();

	/**
	 * Returns the meta object for the '{@link tdt4250.transportation.VehicleRoute#receiveTrackingInfo(double, double) <em>Receive Tracking Info</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Receive Tracking Info</em>' operation.
	 * @see tdt4250.transportation.VehicleRoute#receiveTrackingInfo(double, double)
	 * @generated
	 */
	EOperation getVehicleRoute__ReceiveTrackingInfo__double_double();

	/**
	 * Returns the meta object for class '{@link tdt4250.transportation.Goods <em>Goods</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Goods</em>'.
	 * @see tdt4250.transportation.Goods
	 * @generated
	 */
	EClass getGoods();

	/**
	 * Returns the meta object for the attribute '{@link tdt4250.transportation.Goods#getWeight <em>Weight</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Weight</em>'.
	 * @see tdt4250.transportation.Goods#getWeight()
	 * @see #getGoods()
	 * @generated
	 */
	EAttribute getGoods_Weight();

	/**
	 * Returns the meta object for the reference '{@link tdt4250.transportation.Goods#getOrigin <em>Origin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Origin</em>'.
	 * @see tdt4250.transportation.Goods#getOrigin()
	 * @see #getGoods()
	 * @generated
	 */
	EReference getGoods_Origin();

	/**
	 * Returns the meta object for the reference '{@link tdt4250.transportation.Goods#getDestination <em>Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Destination</em>'.
	 * @see tdt4250.transportation.Goods#getDestination()
	 * @see #getGoods()
	 * @generated
	 */
	EReference getGoods_Destination();

	/**
	 * Returns the meta object for class '{@link tdt4250.transportation.GeoLocation <em>Geo Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Geo Location</em>'.
	 * @see tdt4250.transportation.GeoLocation
	 * @generated
	 */
	EClass getGeoLocation();

	/**
	 * Returns the meta object for the attribute '{@link tdt4250.transportation.GeoLocation#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see tdt4250.transportation.GeoLocation#getName()
	 * @see #getGeoLocation()
	 * @generated
	 */
	EAttribute getGeoLocation_Name();

	/**
	 * Returns the meta object for the attribute '{@link tdt4250.transportation.GeoLocation#getGeoLoc <em>Geo Loc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Geo Loc</em>'.
	 * @see tdt4250.transportation.GeoLocation#getGeoLoc()
	 * @see #getGeoLocation()
	 * @generated
	 */
	EAttribute getGeoLocation_GeoLoc();

	/**
	 * Returns the meta object for class '{@link tdt4250.transportation.TrackingEvent <em>Tracking Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tracking Event</em>'.
	 * @see tdt4250.transportation.TrackingEvent
	 * @generated
	 */
	EClass getTrackingEvent();

	/**
	 * Returns the meta object for the reference '{@link tdt4250.transportation.TrackingEvent#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Location</em>'.
	 * @see tdt4250.transportation.TrackingEvent#getLocation()
	 * @see #getTrackingEvent()
	 * @generated
	 */
	EReference getTrackingEvent_Location();

	/**
	 * Returns the meta object for the attribute '{@link tdt4250.transportation.TrackingEvent#getTimestamp <em>Timestamp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timestamp</em>'.
	 * @see tdt4250.transportation.TrackingEvent#getTimestamp()
	 * @see #getTrackingEvent()
	 * @generated
	 */
	EAttribute getTrackingEvent_Timestamp();

	/**
	 * Returns the meta object for class '{@link tdt4250.transportation.Transportation <em>Transportation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transportation</em>'.
	 * @see tdt4250.transportation.Transportation
	 * @generated
	 */
	EClass getTransportation();

	/**
	 * Returns the meta object for the containment reference list '{@link tdt4250.transportation.Transportation#getVehicles <em>Vehicles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Vehicles</em>'.
	 * @see tdt4250.transportation.Transportation#getVehicles()
	 * @see #getTransportation()
	 * @generated
	 */
	EReference getTransportation_Vehicles();

	/**
	 * Returns the meta object for the containment reference '{@link tdt4250.transportation.Transportation#getLocations <em>Locations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Locations</em>'.
	 * @see tdt4250.transportation.Transportation#getLocations()
	 * @see #getTransportation()
	 * @generated
	 */
	EReference getTransportation_Locations();

	/**
	 * Returns the meta object for class '{@link tdt4250.transportation.GeoLocations <em>Geo Locations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Geo Locations</em>'.
	 * @see tdt4250.transportation.GeoLocations
	 * @generated
	 */
	EClass getGeoLocations();

	/**
	 * Returns the meta object for the containment reference list '{@link tdt4250.transportation.GeoLocations#getLocations <em>Locations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Locations</em>'.
	 * @see tdt4250.transportation.GeoLocations#getLocations()
	 * @see #getGeoLocations()
	 * @generated
	 */
	EReference getGeoLocations_Locations();

	/**
	 * Returns the meta object for data type '{@link tdt4250.transportation.util.GeoLoc <em>EGeo Loc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>EGeo Loc</em>'.
	 * @see tdt4250.transportation.util.GeoLoc
	 * @model instanceClass="tdt4250.transportation.util.GeoLoc"
	 * @generated
	 */
	EDataType getEGeoLoc();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TransportationFactory getTransportationFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link tdt4250.transportation.impl.VehicleImpl <em>Vehicle</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tdt4250.transportation.impl.VehicleImpl
		 * @see tdt4250.transportation.impl.TransportationPackageImpl#getVehicle()
		 * @generated
		 */
		EClass VEHICLE = eINSTANCE.getVehicle();

		/**
		 * The meta object literal for the '<em><b>Capacity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VEHICLE__CAPACITY = eINSTANCE.getVehicle_Capacity();

		/**
		 * The meta object literal for the '<em><b>Routes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VEHICLE__ROUTES = eINSTANCE.getVehicle_Routes();

		/**
		 * The meta object literal for the '{@link tdt4250.transportation.impl.VehicleRouteImpl <em>Vehicle Route</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tdt4250.transportation.impl.VehicleRouteImpl
		 * @see tdt4250.transportation.impl.TransportationPackageImpl#getVehicleRoute()
		 * @generated
		 */
		EClass VEHICLE_ROUTE = eINSTANCE.getVehicleRoute();

		/**
		 * The meta object literal for the '<em><b>Goods</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VEHICLE_ROUTE__GOODS = eINSTANCE.getVehicleRoute_Goods();

		/**
		 * The meta object literal for the '<em><b>Plan</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VEHICLE_ROUTE__PLAN = eINSTANCE.getVehicleRoute_Plan();

		/**
		 * The meta object literal for the '<em><b>Actual Route</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VEHICLE_ROUTE__ACTUAL_ROUTE = eINSTANCE.getVehicleRoute_ActualRoute();

		/**
		 * The meta object literal for the '<em><b>Vehicle</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VEHICLE_ROUTE__VEHICLE = eINSTANCE.getVehicleRoute_Vehicle();

		/**
		 * The meta object literal for the '<em><b>Total Weight</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VEHICLE_ROUTE__TOTAL_WEIGHT = eINSTANCE.getVehicleRoute_TotalWeight();

		/**
		 * The meta object literal for the '<em><b>Receive Tracking Info</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation VEHICLE_ROUTE___RECEIVE_TRACKING_INFO__DOUBLE_DOUBLE = eINSTANCE.getVehicleRoute__ReceiveTrackingInfo__double_double();

		/**
		 * The meta object literal for the '{@link tdt4250.transportation.impl.GoodsImpl <em>Goods</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tdt4250.transportation.impl.GoodsImpl
		 * @see tdt4250.transportation.impl.TransportationPackageImpl#getGoods()
		 * @generated
		 */
		EClass GOODS = eINSTANCE.getGoods();

		/**
		 * The meta object literal for the '<em><b>Weight</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GOODS__WEIGHT = eINSTANCE.getGoods_Weight();

		/**
		 * The meta object literal for the '<em><b>Origin</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GOODS__ORIGIN = eINSTANCE.getGoods_Origin();

		/**
		 * The meta object literal for the '<em><b>Destination</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GOODS__DESTINATION = eINSTANCE.getGoods_Destination();

		/**
		 * The meta object literal for the '{@link tdt4250.transportation.impl.GeoLocationImpl <em>Geo Location</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tdt4250.transportation.impl.GeoLocationImpl
		 * @see tdt4250.transportation.impl.TransportationPackageImpl#getGeoLocation()
		 * @generated
		 */
		EClass GEO_LOCATION = eINSTANCE.getGeoLocation();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GEO_LOCATION__NAME = eINSTANCE.getGeoLocation_Name();

		/**
		 * The meta object literal for the '<em><b>Geo Loc</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GEO_LOCATION__GEO_LOC = eINSTANCE.getGeoLocation_GeoLoc();

		/**
		 * The meta object literal for the '{@link tdt4250.transportation.impl.TrackingEventImpl <em>Tracking Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tdt4250.transportation.impl.TrackingEventImpl
		 * @see tdt4250.transportation.impl.TransportationPackageImpl#getTrackingEvent()
		 * @generated
		 */
		EClass TRACKING_EVENT = eINSTANCE.getTrackingEvent();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACKING_EVENT__LOCATION = eINSTANCE.getTrackingEvent_Location();

		/**
		 * The meta object literal for the '<em><b>Timestamp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRACKING_EVENT__TIMESTAMP = eINSTANCE.getTrackingEvent_Timestamp();

		/**
		 * The meta object literal for the '{@link tdt4250.transportation.impl.TransportationImpl <em>Transportation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tdt4250.transportation.impl.TransportationImpl
		 * @see tdt4250.transportation.impl.TransportationPackageImpl#getTransportation()
		 * @generated
		 */
		EClass TRANSPORTATION = eINSTANCE.getTransportation();

		/**
		 * The meta object literal for the '<em><b>Vehicles</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSPORTATION__VEHICLES = eINSTANCE.getTransportation_Vehicles();

		/**
		 * The meta object literal for the '<em><b>Locations</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSPORTATION__LOCATIONS = eINSTANCE.getTransportation_Locations();

		/**
		 * The meta object literal for the '{@link tdt4250.transportation.impl.GeoLocationsImpl <em>Geo Locations</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tdt4250.transportation.impl.GeoLocationsImpl
		 * @see tdt4250.transportation.impl.TransportationPackageImpl#getGeoLocations()
		 * @generated
		 */
		EClass GEO_LOCATIONS = eINSTANCE.getGeoLocations();

		/**
		 * The meta object literal for the '<em><b>Locations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GEO_LOCATIONS__LOCATIONS = eINSTANCE.getGeoLocations_Locations();

		/**
		 * The meta object literal for the '<em>EGeo Loc</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tdt4250.transportation.util.GeoLoc
		 * @see tdt4250.transportation.impl.TransportationPackageImpl#getEGeoLoc()
		 * @generated
		 */
		EDataType EGEO_LOC = eINSTANCE.getEGeoLoc();

	}

} //TransportationPackage
