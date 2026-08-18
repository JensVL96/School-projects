/**
 */
package tdt4250.transportation;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Vehicle Route</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link tdt4250.transportation.VehicleRoute#getGoods <em>Goods</em>}</li>
 *   <li>{@link tdt4250.transportation.VehicleRoute#getPlan <em>Plan</em>}</li>
 *   <li>{@link tdt4250.transportation.VehicleRoute#getActualRoute <em>Actual Route</em>}</li>
 *   <li>{@link tdt4250.transportation.VehicleRoute#getVehicle <em>Vehicle</em>}</li>
 *   <li>{@link tdt4250.transportation.VehicleRoute#getTotalWeight <em>Total Weight</em>}</li>
 * </ul>
 *
 * @see tdt4250.transportation.TransportationPackage#getVehicleRoute()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='noOverweight planIncludesGoodsOriginAndDestination actualRouteIncreasingTimestamps'"
 *        annotation="http://www.eclipse.org/acceleo/query/1.0 noOverweight='self.goods.weight-&gt;sum() &lt;= self.vehicle.capacity' planIncludesGoodsOriginAndDestination='self.plan-&gt;includesAll(self.goods.origin-&gt;union(self.goods.destination))'"
 * @generated
 */
public interface VehicleRoute extends EObject {
	/**
	 * Returns the value of the '<em><b>Goods</b></em>' containment reference list.
	 * The list contents are of type {@link tdt4250.transportation.Goods}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Goods</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Goods</em>' containment reference list.
	 * @see tdt4250.transportation.TransportationPackage#getVehicleRoute_Goods()
	 * @model containment="true"
	 * @generated
	 */
	EList<Goods> getGoods();

	/**
	 * Returns the value of the '<em><b>Plan</b></em>' reference list.
	 * The list contents are of type {@link tdt4250.transportation.GeoLocation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Plan</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Plan</em>' reference list.
	 * @see tdt4250.transportation.TransportationPackage#getVehicleRoute_Plan()
	 * @model
	 * @generated
	 */
	EList<GeoLocation> getPlan();

	/**
	 * Returns the value of the '<em><b>Actual Route</b></em>' containment reference list.
	 * The list contents are of type {@link tdt4250.transportation.TrackingEvent}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actual Route</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actual Route</em>' containment reference list.
	 * @see tdt4250.transportation.TransportationPackage#getVehicleRoute_ActualRoute()
	 * @model containment="true"
	 * @generated
	 */
	EList<TrackingEvent> getActualRoute();

	/**
	 * Returns the value of the '<em><b>Vehicle</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link tdt4250.transportation.Vehicle#getRoutes <em>Routes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Vehicle</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vehicle</em>' container reference.
	 * @see #setVehicle(Vehicle)
	 * @see tdt4250.transportation.TransportationPackage#getVehicleRoute_Vehicle()
	 * @see tdt4250.transportation.Vehicle#getRoutes
	 * @model opposite="routes" transient="false"
	 * @generated
	 */
	Vehicle getVehicle();

	/**
	 * Sets the value of the '{@link tdt4250.transportation.VehicleRoute#getVehicle <em>Vehicle</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vehicle</em>' container reference.
	 * @see #getVehicle()
	 * @generated
	 */
	void setVehicle(Vehicle value);

	/**
	 * Returns the value of the '<em><b>Total Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Total Weight</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Total Weight</em>' attribute.
	 * @see tdt4250.transportation.TransportationPackage#getVehicleRoute_TotalWeight()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	double getTotalWeight();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void receiveTrackingInfo(double latitude, double longitude);

} // VehicleRoute
