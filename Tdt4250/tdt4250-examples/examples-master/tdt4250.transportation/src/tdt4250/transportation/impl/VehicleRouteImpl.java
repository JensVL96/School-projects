/**
 */
package tdt4250.transportation.impl;

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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import tdt4250.transportation.GeoLocation;
import tdt4250.transportation.Goods;
import tdt4250.transportation.TrackingEvent;
import tdt4250.transportation.TransportationFactory;
import tdt4250.transportation.TransportationPackage;
import tdt4250.transportation.Vehicle;
import tdt4250.transportation.VehicleRoute;
import tdt4250.transportation.util.GeoLoc;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Vehicle Route</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link tdt4250.transportation.impl.VehicleRouteImpl#getGoods <em>Goods</em>}</li>
 *   <li>{@link tdt4250.transportation.impl.VehicleRouteImpl#getPlan <em>Plan</em>}</li>
 *   <li>{@link tdt4250.transportation.impl.VehicleRouteImpl#getActualRoute <em>Actual Route</em>}</li>
 *   <li>{@link tdt4250.transportation.impl.VehicleRouteImpl#getVehicle <em>Vehicle</em>}</li>
 *   <li>{@link tdt4250.transportation.impl.VehicleRouteImpl#getTotalWeight <em>Total Weight</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VehicleRouteImpl extends MinimalEObjectImpl.Container implements VehicleRoute {
	/**
	 * The cached value of the '{@link #getGoods() <em>Goods</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGoods()
	 * @generated
	 * @ordered
	 */
	protected EList<Goods> goods;

	/**
	 * The cached value of the '{@link #getPlan() <em>Plan</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPlan()
	 * @generated
	 * @ordered
	 */
	protected EList<GeoLocation> plan;

	/**
	 * The cached value of the '{@link #getActualRoute() <em>Actual Route</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualRoute()
	 * @generated
	 * @ordered
	 */
	protected EList<TrackingEvent> actualRoute;

	/**
	 * The default value of the '{@link #getTotalWeight() <em>Total Weight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTotalWeight()
	 * @generated
	 * @ordered
	 */
	protected static final double TOTAL_WEIGHT_EDEFAULT = 0.0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VehicleRouteImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TransportationPackage.Literals.VEHICLE_ROUTE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Goods> getGoods() {
		if (goods == null) {
			goods = new EObjectContainmentEList<Goods>(Goods.class, this, TransportationPackage.VEHICLE_ROUTE__GOODS);
		}
		return goods;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GeoLocation> getPlan() {
		if (plan == null) {
			plan = new EObjectResolvingEList<GeoLocation>(GeoLocation.class, this, TransportationPackage.VEHICLE_ROUTE__PLAN);
		}
		return plan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TrackingEvent> getActualRoute() {
		if (actualRoute == null) {
			actualRoute = new EObjectContainmentEList<TrackingEvent>(TrackingEvent.class, this, TransportationPackage.VEHICLE_ROUTE__ACTUAL_ROUTE);
		}
		return actualRoute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Vehicle getVehicle() {
		if (eContainerFeatureID() != TransportationPackage.VEHICLE_ROUTE__VEHICLE) return null;
		return (Vehicle)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVehicle(Vehicle newVehicle, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newVehicle, TransportationPackage.VEHICLE_ROUTE__VEHICLE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVehicle(Vehicle newVehicle) {
		if (newVehicle != eInternalContainer() || (eContainerFeatureID() != TransportationPackage.VEHICLE_ROUTE__VEHICLE && newVehicle != null)) {
			if (EcoreUtil.isAncestor(this, newVehicle))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newVehicle != null)
				msgs = ((InternalEObject)newVehicle).eInverseAdd(this, TransportationPackage.VEHICLE__ROUTES, Vehicle.class, msgs);
			msgs = basicSetVehicle(newVehicle, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TransportationPackage.VEHICLE_ROUTE__VEHICLE, newVehicle, newVehicle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public double getTotalWeight() {
		double sum = 0;
		for (Goods goods : getGoods()) {
			sum += goods.getWeight();
		}
		return sum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void receiveTrackingInfo(double latitude, double longitude) {
		GeoLoc geoLoc = new GeoLoc(latitude, longitude);
		TrackingEvent tr = TransportationFactory.eINSTANCE.createTrackingEvent();
		GeoLocation geoLocation = TransportationFactory.eINSTANCE.createGeoLocation();
		geoLocation.setGeoLoc(geoLoc);
		tr.setLocation(geoLocation);
		getActualRoute().add(tr);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TransportationPackage.VEHICLE_ROUTE__VEHICLE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetVehicle((Vehicle)otherEnd, msgs);
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
			case TransportationPackage.VEHICLE_ROUTE__GOODS:
				return ((InternalEList<?>)getGoods()).basicRemove(otherEnd, msgs);
			case TransportationPackage.VEHICLE_ROUTE__ACTUAL_ROUTE:
				return ((InternalEList<?>)getActualRoute()).basicRemove(otherEnd, msgs);
			case TransportationPackage.VEHICLE_ROUTE__VEHICLE:
				return basicSetVehicle(null, msgs);
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
			case TransportationPackage.VEHICLE_ROUTE__VEHICLE:
				return eInternalContainer().eInverseRemove(this, TransportationPackage.VEHICLE__ROUTES, Vehicle.class, msgs);
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
			case TransportationPackage.VEHICLE_ROUTE__GOODS:
				return getGoods();
			case TransportationPackage.VEHICLE_ROUTE__PLAN:
				return getPlan();
			case TransportationPackage.VEHICLE_ROUTE__ACTUAL_ROUTE:
				return getActualRoute();
			case TransportationPackage.VEHICLE_ROUTE__VEHICLE:
				return getVehicle();
			case TransportationPackage.VEHICLE_ROUTE__TOTAL_WEIGHT:
				return getTotalWeight();
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
			case TransportationPackage.VEHICLE_ROUTE__GOODS:
				getGoods().clear();
				getGoods().addAll((Collection<? extends Goods>)newValue);
				return;
			case TransportationPackage.VEHICLE_ROUTE__PLAN:
				getPlan().clear();
				getPlan().addAll((Collection<? extends GeoLocation>)newValue);
				return;
			case TransportationPackage.VEHICLE_ROUTE__ACTUAL_ROUTE:
				getActualRoute().clear();
				getActualRoute().addAll((Collection<? extends TrackingEvent>)newValue);
				return;
			case TransportationPackage.VEHICLE_ROUTE__VEHICLE:
				setVehicle((Vehicle)newValue);
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
			case TransportationPackage.VEHICLE_ROUTE__GOODS:
				getGoods().clear();
				return;
			case TransportationPackage.VEHICLE_ROUTE__PLAN:
				getPlan().clear();
				return;
			case TransportationPackage.VEHICLE_ROUTE__ACTUAL_ROUTE:
				getActualRoute().clear();
				return;
			case TransportationPackage.VEHICLE_ROUTE__VEHICLE:
				setVehicle((Vehicle)null);
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
			case TransportationPackage.VEHICLE_ROUTE__GOODS:
				return goods != null && !goods.isEmpty();
			case TransportationPackage.VEHICLE_ROUTE__PLAN:
				return plan != null && !plan.isEmpty();
			case TransportationPackage.VEHICLE_ROUTE__ACTUAL_ROUTE:
				return actualRoute != null && !actualRoute.isEmpty();
			case TransportationPackage.VEHICLE_ROUTE__VEHICLE:
				return getVehicle() != null;
			case TransportationPackage.VEHICLE_ROUTE__TOTAL_WEIGHT:
				return getTotalWeight() != TOTAL_WEIGHT_EDEFAULT;
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
			case TransportationPackage.VEHICLE_ROUTE___RECEIVE_TRACKING_INFO__DOUBLE_DOUBLE:
				receiveTrackingInfo((Double)arguments.get(0), (Double)arguments.get(1));
				return null;
		}
		return super.eInvoke(operationID, arguments);
	}

} //VehicleRouteImpl
