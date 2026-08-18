/**
 */
package tdt4250.transportation.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import tdt4250.transportation.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see tdt4250.transportation.TransportationPackage
 * @generated
 */
public class TransportationAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static TransportationPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransportationAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = TransportationPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TransportationSwitch<Adapter> modelSwitch =
		new TransportationSwitch<Adapter>() {
			@Override
			public Adapter caseVehicle(Vehicle object) {
				return createVehicleAdapter();
			}
			@Override
			public Adapter caseVehicleRoute(VehicleRoute object) {
				return createVehicleRouteAdapter();
			}
			@Override
			public Adapter caseGoods(Goods object) {
				return createGoodsAdapter();
			}
			@Override
			public Adapter caseGeoLocation(GeoLocation object) {
				return createGeoLocationAdapter();
			}
			@Override
			public Adapter caseTrackingEvent(TrackingEvent object) {
				return createTrackingEventAdapter();
			}
			@Override
			public Adapter caseTransportation(Transportation object) {
				return createTransportationAdapter();
			}
			@Override
			public Adapter caseGeoLocations(GeoLocations object) {
				return createGeoLocationsAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link tdt4250.transportation.Vehicle <em>Vehicle</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tdt4250.transportation.Vehicle
	 * @generated
	 */
	public Adapter createVehicleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tdt4250.transportation.VehicleRoute <em>Vehicle Route</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tdt4250.transportation.VehicleRoute
	 * @generated
	 */
	public Adapter createVehicleRouteAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tdt4250.transportation.Goods <em>Goods</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tdt4250.transportation.Goods
	 * @generated
	 */
	public Adapter createGoodsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tdt4250.transportation.GeoLocation <em>Geo Location</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tdt4250.transportation.GeoLocation
	 * @generated
	 */
	public Adapter createGeoLocationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tdt4250.transportation.TrackingEvent <em>Tracking Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tdt4250.transportation.TrackingEvent
	 * @generated
	 */
	public Adapter createTrackingEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tdt4250.transportation.Transportation <em>Transportation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tdt4250.transportation.Transportation
	 * @generated
	 */
	public Adapter createTransportationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tdt4250.transportation.GeoLocations <em>Geo Locations</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tdt4250.transportation.GeoLocations
	 * @generated
	 */
	public Adapter createGeoLocationsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //TransportationAdapterFactory
