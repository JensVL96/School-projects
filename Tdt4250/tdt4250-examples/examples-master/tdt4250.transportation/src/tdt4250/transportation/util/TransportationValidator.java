/**
 */
package tdt4250.transportation.util;

import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

import tdt4250.transportation.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see tdt4250.transportation.TransportationPackage
 * @generated
 */
public class TransportationValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final TransportationValidator INSTANCE = new TransportationValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "tdt4250.transportation";

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
	public TransportationValidator() {
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
	  return TransportationPackage.eINSTANCE;
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
			case TransportationPackage.VEHICLE:
				return validateVehicle((Vehicle)value, diagnostics, context);
			case TransportationPackage.VEHICLE_ROUTE:
				return validateVehicleRoute((VehicleRoute)value, diagnostics, context);
			case TransportationPackage.GOODS:
				return validateGoods((Goods)value, diagnostics, context);
			case TransportationPackage.GEO_LOCATION:
				return validateGeoLocation((GeoLocation)value, diagnostics, context);
			case TransportationPackage.TRACKING_EVENT:
				return validateTrackingEvent((TrackingEvent)value, diagnostics, context);
			case TransportationPackage.TRANSPORTATION:
				return validateTransportation((Transportation)value, diagnostics, context);
			case TransportationPackage.GEO_LOCATIONS:
				return validateGeoLocations((GeoLocations)value, diagnostics, context);
			case TransportationPackage.EGEO_LOC:
				return validateEGeoLoc((GeoLoc)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateVehicle(Vehicle vehicle, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(vehicle, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateVehicleRoute(VehicleRoute vehicleRoute, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(vehicleRoute, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(vehicleRoute, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(vehicleRoute, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(vehicleRoute, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(vehicleRoute, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(vehicleRoute, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(vehicleRoute, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(vehicleRoute, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(vehicleRoute, diagnostics, context);
		if (result || diagnostics != null) result &= validateVehicleRoute_noOverweight(vehicleRoute, diagnostics, context);
		if (result || diagnostics != null) result &= validateVehicleRoute_planIncludesGoodsOriginAndDestination(vehicleRoute, diagnostics, context);
		if (result || diagnostics != null) result &= validateVehicleRoute_actualRouteIncreasingTimestamps(vehicleRoute, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the noOverweight constraint of '<em>Vehicle Route</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String VEHICLE_ROUTE__NO_OVERWEIGHT__EEXPRESSION = "self.goods.weight->sum() <= self.vehicle.capacity";

	/**
	 * Validates the noOverweight constraint of '<em>Vehicle Route</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateVehicleRoute_noOverweight(VehicleRoute vehicleRoute, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(TransportationPackage.Literals.VEHICLE_ROUTE,
				 vehicleRoute,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/acceleo/query/1.0",
				 "noOverweight",
				 VEHICLE_ROUTE__NO_OVERWEIGHT__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * The cached validation expression for the planIncludesGoodsOriginAndDestination constraint of '<em>Vehicle Route</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String VEHICLE_ROUTE__PLAN_INCLUDES_GOODS_ORIGIN_AND_DESTINATION__EEXPRESSION = "self.plan->includesAll(self.goods.origin->union(self.goods.destination))";

	/**
	 * Validates the planIncludesGoodsOriginAndDestination constraint of '<em>Vehicle Route</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateVehicleRoute_planIncludesGoodsOriginAndDestination(VehicleRoute vehicleRoute, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(TransportationPackage.Literals.VEHICLE_ROUTE,
				 vehicleRoute,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/acceleo/query/1.0",
				 "planIncludesGoodsOriginAndDestination",
				 VEHICLE_ROUTE__PLAN_INCLUDES_GOODS_ORIGIN_AND_DESTINATION__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * Validates the actualRouteIncreasingTimestamps constraint of '<em>Vehicle Route</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateVehicleRoute_actualRouteIncreasingTimestamps(VehicleRoute vehicleRoute, DiagnosticChain diagnostics, Map<Object, Object> context) {
		for (int i = 1; i < vehicleRoute.getActualRoute().size(); i++) {
			if (vehicleRoute.getActualRoute().get(i - 1).getTimestamp() > vehicleRoute.getActualRoute().get(i).getTimestamp()) {
				if (diagnostics != null) {
					diagnostics.add
					(createDiagnostic
							(Diagnostic.ERROR,
									DIAGNOSTIC_SOURCE,
									0,
									"_UI_GenericConstraint_diagnostic",
									new Object[] { "actualRouteIncreasingTimestamps", getObjectLabel(vehicleRoute, context) },
									new Object[] { vehicleRoute },
									context));
				}
				return false;
			}
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGoods(Goods goods, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(goods, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGeoLocation(GeoLocation geoLocation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(geoLocation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTrackingEvent(TrackingEvent trackingEvent, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(trackingEvent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTransportation(Transportation transportation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(transportation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGeoLocations(GeoLocations geoLocations, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(geoLocations, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEGeoLoc(GeoLoc eGeoLoc, DiagnosticChain diagnostics, Map<Object, Object> context) {
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

} //TransportationValidator
