/**
 */
package tdt4250.transportation.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import tdt4250.transportation.GeoLocation;
import tdt4250.transportation.GeoLocations;
import tdt4250.transportation.Goods;
import tdt4250.transportation.TrackingEvent;
import tdt4250.transportation.Transportation;
import tdt4250.transportation.TransportationFactory;
import tdt4250.transportation.TransportationPackage;
import tdt4250.transportation.Vehicle;
import tdt4250.transportation.VehicleRoute;

import tdt4250.transportation.util.GeoLoc;
import tdt4250.transportation.util.TransportationValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TransportationPackageImpl extends EPackageImpl implements TransportationPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vehicleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vehicleRouteEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass goodsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass geoLocationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass trackingEventEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transportationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass geoLocationsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType eGeoLocEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see tdt4250.transportation.TransportationPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TransportationPackageImpl() {
		super(eNS_URI, TransportationFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link TransportationPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TransportationPackage init() {
		if (isInited) return (TransportationPackage)EPackage.Registry.INSTANCE.getEPackage(TransportationPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredTransportationPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		TransportationPackageImpl theTransportationPackage = registeredTransportationPackage instanceof TransportationPackageImpl ? (TransportationPackageImpl)registeredTransportationPackage : new TransportationPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theTransportationPackage.createPackageContents();

		// Initialize created meta-data
		theTransportationPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theTransportationPackage,
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return TransportationValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theTransportationPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TransportationPackage.eNS_URI, theTransportationPackage);
		return theTransportationPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVehicle() {
		return vehicleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVehicle_Capacity() {
		return (EAttribute)vehicleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVehicle_Routes() {
		return (EReference)vehicleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVehicleRoute() {
		return vehicleRouteEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVehicleRoute_Goods() {
		return (EReference)vehicleRouteEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVehicleRoute_Plan() {
		return (EReference)vehicleRouteEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVehicleRoute_ActualRoute() {
		return (EReference)vehicleRouteEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVehicleRoute_Vehicle() {
		return (EReference)vehicleRouteEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVehicleRoute_TotalWeight() {
		return (EAttribute)vehicleRouteEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getVehicleRoute__ReceiveTrackingInfo__double_double() {
		return vehicleRouteEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGoods() {
		return goodsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGoods_Weight() {
		return (EAttribute)goodsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGoods_Origin() {
		return (EReference)goodsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGoods_Destination() {
		return (EReference)goodsEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGeoLocation() {
		return geoLocationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeoLocation_Name() {
		return (EAttribute)geoLocationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeoLocation_GeoLoc() {
		return (EAttribute)geoLocationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTrackingEvent() {
		return trackingEventEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTrackingEvent_Location() {
		return (EReference)trackingEventEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTrackingEvent_Timestamp() {
		return (EAttribute)trackingEventEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTransportation() {
		return transportationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransportation_Vehicles() {
		return (EReference)transportationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransportation_Locations() {
		return (EReference)transportationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGeoLocations() {
		return geoLocationsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGeoLocations_Locations() {
		return (EReference)geoLocationsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getEGeoLoc() {
		return eGeoLocEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransportationFactory getTransportationFactory() {
		return (TransportationFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		vehicleEClass = createEClass(VEHICLE);
		createEAttribute(vehicleEClass, VEHICLE__CAPACITY);
		createEReference(vehicleEClass, VEHICLE__ROUTES);

		vehicleRouteEClass = createEClass(VEHICLE_ROUTE);
		createEReference(vehicleRouteEClass, VEHICLE_ROUTE__GOODS);
		createEReference(vehicleRouteEClass, VEHICLE_ROUTE__PLAN);
		createEReference(vehicleRouteEClass, VEHICLE_ROUTE__ACTUAL_ROUTE);
		createEReference(vehicleRouteEClass, VEHICLE_ROUTE__VEHICLE);
		createEAttribute(vehicleRouteEClass, VEHICLE_ROUTE__TOTAL_WEIGHT);
		createEOperation(vehicleRouteEClass, VEHICLE_ROUTE___RECEIVE_TRACKING_INFO__DOUBLE_DOUBLE);

		goodsEClass = createEClass(GOODS);
		createEAttribute(goodsEClass, GOODS__WEIGHT);
		createEReference(goodsEClass, GOODS__ORIGIN);
		createEReference(goodsEClass, GOODS__DESTINATION);

		geoLocationEClass = createEClass(GEO_LOCATION);
		createEAttribute(geoLocationEClass, GEO_LOCATION__NAME);
		createEAttribute(geoLocationEClass, GEO_LOCATION__GEO_LOC);

		trackingEventEClass = createEClass(TRACKING_EVENT);
		createEReference(trackingEventEClass, TRACKING_EVENT__LOCATION);
		createEAttribute(trackingEventEClass, TRACKING_EVENT__TIMESTAMP);

		transportationEClass = createEClass(TRANSPORTATION);
		createEReference(transportationEClass, TRANSPORTATION__VEHICLES);
		createEReference(transportationEClass, TRANSPORTATION__LOCATIONS);

		geoLocationsEClass = createEClass(GEO_LOCATIONS);
		createEReference(geoLocationsEClass, GEO_LOCATIONS__LOCATIONS);

		// Create data types
		eGeoLocEDataType = createEDataType(EGEO_LOC);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(vehicleEClass, Vehicle.class, "Vehicle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVehicle_Capacity(), ecorePackage.getEDouble(), "capacity", null, 0, 1, Vehicle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVehicle_Routes(), this.getVehicleRoute(), this.getVehicleRoute_Vehicle(), "routes", null, 0, -1, Vehicle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(vehicleRouteEClass, VehicleRoute.class, "VehicleRoute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getVehicleRoute_Goods(), this.getGoods(), null, "goods", null, 0, -1, VehicleRoute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVehicleRoute_Plan(), this.getGeoLocation(), null, "plan", null, 0, -1, VehicleRoute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVehicleRoute_ActualRoute(), this.getTrackingEvent(), null, "actualRoute", null, 0, -1, VehicleRoute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVehicleRoute_Vehicle(), this.getVehicle(), this.getVehicle_Routes(), "vehicle", null, 0, 1, VehicleRoute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getVehicleRoute_TotalWeight(), ecorePackage.getEDouble(), "totalWeight", null, 0, 1, VehicleRoute.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		EOperation op = initEOperation(getVehicleRoute__ReceiveTrackingInfo__double_double(), null, "receiveTrackingInfo", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDouble(), "latitude", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDouble(), "longitude", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(goodsEClass, Goods.class, "Goods", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGoods_Weight(), ecorePackage.getEDouble(), "weight", null, 0, 1, Goods.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGoods_Origin(), this.getGeoLocation(), null, "origin", null, 0, 1, Goods.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGoods_Destination(), this.getGeoLocation(), null, "destination", null, 0, 1, Goods.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(geoLocationEClass, GeoLocation.class, "GeoLocation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGeoLocation_Name(), ecorePackage.getEString(), "name", null, 0, 1, GeoLocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeoLocation_GeoLoc(), this.getEGeoLoc(), "geoLoc", null, 0, 1, GeoLocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(trackingEventEClass, TrackingEvent.class, "TrackingEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTrackingEvent_Location(), this.getGeoLocation(), null, "location", null, 0, 1, TrackingEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTrackingEvent_Timestamp(), ecorePackage.getELong(), "timestamp", null, 0, 1, TrackingEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transportationEClass, Transportation.class, "Transportation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTransportation_Vehicles(), this.getVehicle(), null, "vehicles", null, 0, -1, Transportation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransportation_Locations(), this.getGeoLocation(), null, "locations", null, 0, 1, Transportation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(geoLocationsEClass, GeoLocations.class, "GeoLocations", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGeoLocations_Locations(), this.getGeoLocation(), null, "locations", null, 0, -1, GeoLocations.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize data types
		initEDataType(eGeoLocEDataType, GeoLoc.class, "EGeoLoc", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
		// http://www.eclipse.org/acceleo/query/1.0
		create_1Annotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEcoreAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore";
		addAnnotation
		  (this,
		   source,
		   new String[] {
			   "validationDelegates", "http://www.eclipse.org/acceleo/query/1.0"
		   });
		addAnnotation
		  (vehicleRouteEClass,
		   source,
		   new String[] {
			   "constraints", "noOverweight planIncludesGoodsOriginAndDestination actualRouteIncreasingTimestamps"
		   });
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/acceleo/query/1.0</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void create_1Annotations() {
		String source = "http://www.eclipse.org/acceleo/query/1.0";
		addAnnotation
		  (vehicleRouteEClass,
		   source,
		   new String[] {
			   "noOverweight", "self.goods.weight->sum() <= self.vehicle.capacity",
			   "planIncludesGoodsOriginAndDestination", "self.plan->includesAll(self.goods.origin->union(self.goods.destination))"
		   });
	}

} //TransportationPackageImpl
