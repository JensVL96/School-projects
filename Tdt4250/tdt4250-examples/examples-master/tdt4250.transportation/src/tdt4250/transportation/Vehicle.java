/**
 */
package tdt4250.transportation;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Vehicle</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link tdt4250.transportation.Vehicle#getCapacity <em>Capacity</em>}</li>
 *   <li>{@link tdt4250.transportation.Vehicle#getRoutes <em>Routes</em>}</li>
 * </ul>
 *
 * @see tdt4250.transportation.TransportationPackage#getVehicle()
 * @model
 * @generated
 */
public interface Vehicle extends EObject {
	/**
	 * Returns the value of the '<em><b>Capacity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Capacity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Capacity</em>' attribute.
	 * @see #setCapacity(double)
	 * @see tdt4250.transportation.TransportationPackage#getVehicle_Capacity()
	 * @model
	 * @generated
	 */
	double getCapacity();

	/**
	 * Sets the value of the '{@link tdt4250.transportation.Vehicle#getCapacity <em>Capacity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Capacity</em>' attribute.
	 * @see #getCapacity()
	 * @generated
	 */
	void setCapacity(double value);

	/**
	 * Returns the value of the '<em><b>Routes</b></em>' containment reference list.
	 * The list contents are of type {@link tdt4250.transportation.VehicleRoute}.
	 * It is bidirectional and its opposite is '{@link tdt4250.transportation.VehicleRoute#getVehicle <em>Vehicle</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Routes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Routes</em>' containment reference list.
	 * @see tdt4250.transportation.TransportationPackage#getVehicle_Routes()
	 * @see tdt4250.transportation.VehicleRoute#getVehicle
	 * @model opposite="vehicle" containment="true"
	 * @generated
	 */
	EList<VehicleRoute> getRoutes();

} // Vehicle
